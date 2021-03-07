package com.viw.viwmall.search.thread;

import java.util.concurrent.*;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/7 14:52
 * @description:
 */
public class MySynThread1 {


      // ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,360000,new TimeUnit());
    public static     ExecutorService service = Executors.newFixedThreadPool(10);


    //callable futuretask
    public static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            return "OK";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread( futureTask).start();
       // System.out.println(futureTask.get());
//        service.execute( );

        //当前系统中池只有一两个，每个异步任务，提交给线程池让他自己去执行就行
        /**
         * 七大参数
         * corePoolSize:[5] 核心线程数[一直存在除非（allowCoreThreadTimeOut）]; 线程池，创建好以后就准备就绪的线程数量，就等待来接受异步任务去执行。
         *        5个  Thread thread = new Thread();  thread.start();
         * maximumPoolSize:[200] 最大线程数量;  控制资源
         * keepAliveTime:存活时间。如果当前的线程数量大于core数量。释放空闲的线程（maximumPoolSize-corePoolSize）。只要线程空闲大于指定的keepAliveTime；
         * unit:时间单位
         * BlockingQueue<Runnable> workQueue:阻塞队列。如果任务有很多，就会将目前多的任务放在队列里面。
         *              只要有线程空闲，就会去队列里面取出新的任务继续执行。
         * threadFactory:线程的创建工厂。
         * RejectedExecutionHandler handler:如果队列满了，按照我们指定的拒绝策略拒绝执行任务
         *
         *
         *
         * 工作顺序:
         * 1)、线程池创建，准备好core数量的核心线程，准备接受任务
         * 1.1、core满了，就将再进来的任务放入阻塞队列中。空闲的core就会自己去阻塞队列获取任务执行
         * 1.2、阻塞队列满了，就直接开新线程执行，最大只能开到max指定的数量
         * 1.3、max满了就用RejectedExecutionHandler拒绝任务
         * 1.4、max都执行完成，有很多空闲.在指定的时间keepAliveTime以后，释放max-core这些线程
         *
         *      new LinkedBlockingDeque<>()：默认是Integer的最大值。内存不够
         *
         * 一个线程池 core 7； max 20 ，queue：50，100并发进来怎么分配的；
         * 7个会立即得到执行，50个会进入队列，再开13个进行执行。剩下的30个就使用拒绝策略。
         * 如果不想抛弃还要执行。CallerRunsPolicy；
         *
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
//        Executors.newCachedThreadPool() core是0，所有都可回收
//        Executors.newFixedThreadPool() 固定大小，core=max；都不可回收
//        Executors.newScheduledThreadPool() 定时任务的线程池
//        Executors.newSingleThreadExecutor() 单线程的线程池，后台从队列里面获取任务，挨个执行
        //
    }
}
