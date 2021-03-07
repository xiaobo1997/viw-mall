package com.viw.viwmall.search.thread;

import com.alibaba.nacos.client.utils.JSONUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/7 15:51
 * @description:
 */
public class CompletableFutureTest1 {

    public static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main-start");
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println("ok");
//        }, service);
        /**
         * 方法完成后的感知
         */
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("OK");
//            return "value";
//        }, service).whenComplete((result,exception)->{
//            //没法修改返回数据
//            System.out.println("成功完成。执行结果是"+result+"。异常是:"+exception);
//        }).exceptionally(throwable -> {
//            //出现异常 执行下面异常处理，可以感知异常并处理异常
//            String message = throwable.getMessage();
//            // 返回结果，可以是我们的业务执行默认处理结果 比如
//            return message;
//        });
//        System.out.println(future.get());

        /**
         * 方法执行完成后的处理，
         */
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("OK");
//            String s = "结果";
//            return s;
//        }, service).handle((result, exception) -> {
//            if (exception != null) {
//                return "异常，业务处理";
//            }
//            if (result !=null) {
//                return "没异常业务处理："+result;
//            }
//            return result;
//        });
//        System.out.println(future.get());

        // 1有结果  但2没结果
//        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
//            // 任务1
//            System.out.println("OK");
//            String s = "1结果";
//            return s;
//        }, service).thenRunAsync(()->{
//            // 任务2
//            System.out.println("2执行");
//        },service);
//        CompletableFuture.supplyAsync(() -> {
//            // 任务1
//            System.out.println("OK");
//            String s = "1结果";
//            return s;
//        }, service).thenAcceptAsync(res->{
//            // 任务2
//            System.out.println("2执行"+res);
//        },service);


//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            // 任务1
//            System.out.println("OK");
//            String s = "1结果";
//            return s;
//        }, service).thenApplyAsync(res -> {
//            // 任务2
//            System.out.println("2执行。上一个结果：" + res);
//            return "任务2的返回值";
//        }, service);
//        System.out.println(future.get());

        /**
         * 两个都完成
         */
        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务1结束：" );
            return i;
        }, service);

        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());


            try {
                Thread.sleep(3000);
                System.out.println("任务2结束：" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }, service);
//             任务12完成后执行 runAfterBothAsync不能感知前面结果
//        future01.runAfterBothAsync(future02,()->{
//            System.out.println("任务3开始...");
//        },executor);
//         void accept(T t, U u); thenAcceptBothAsync感知前面结果
//        future01.thenAcceptBothAsync(future02,(f1,f2)->{
//         可以获取前面1 2的结果
//            System.out.println("任务3开始...之前的结果："+f1+"--》"+f2);
//        },executor);
//        R apply(T t, U u);
//         thenCombineAsync合并任务 并感知获取前面结果
//        CompletableFuture<String> future = future01.thenCombineAsync(future02, (f1, f2) -> {
//            return f1 + "：" + f2 + " -> Haha";
//        }, executor);


        /**
         * 两个任务，只要有一个完成，我们就执行任务3
         * runAfterEitherAsync：不感知结果，自己没有返回值
         * acceptEitherAsync：感知结果，自己没有返回值
         * applyToEitherAsync：感知结果，自己有返回值
         */
//        future01.runAfterEitherAsync(future02,()->{
//            System.out.println("任务3开始...之前的结果：");
//        },service);
////        void accept(T t);
//        future01.acceptEitherAsync(future02,(res)->{
//            System.out.println("任务3开始...之前的结果："+res);
//        },service);
//        CompletableFuture<String> future = future01.applyToEitherAsync(future02, res -> {
//            System.out.println("任务3开始...之前的结果：" + res);
//            return res.toString() + "->哈哈";
//        }, service);

        /**
         * 多任务
         */
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的图片信息");
            return "hello.jpg";
        },service);

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的属性");
            return "黑色+256G";
        },service);

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("查询商品介绍");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为";
        },service);

        //等待都完成
      //  CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
        // 其中一个完成
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        anyOf.get();


        System.out.println("main-end");
    }
}
