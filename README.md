
<!-- TOC -->

- [架构和开发环境](#架构和开发环境)
  - [架构图](#架构图)
  - [基础环境搭建](#基础环境搭建)
    - [基础软件](#基础软件)
    - [开发环境](#开发环境)
    - [使用人人开源搭建后台管理系统](#使用人人开源搭建后台管理系统)
    - [搭建人人开源后台管理系统页面](#搭建人人开源后台管理系统页面)
    - [使用人人开源逆向工程](#使用人人开源逆向工程)
    - [测试基础环境](#测试基础环境)
      - [测试nacos](#测试nacos)
      - [测试feign](#测试feign)
      - [测试配置中心](#测试配置中心)
      - [测试配置中心命名空间和配置分组](#测试配置中心命名空间和配置分组)
      - [测试加载多个配置集](#测试加载多个配置集)
      - [测试网关](#测试网关)
  - [前端技术栈](#前端技术栈)
    - [后台管理系统前端环境](#后台管理系统前端环境)
  - [后台管理系统api](#后台管理系统api)
    - [商品服务api](#商品服务api)
    - [仓储服务api](#仓储服务api)
  - [ES](#es)
    - [概念](#概念)
    - [安装](#安装)
    - [使用](#使用)
    - [进阶](#进阶)
      - [两种查询方式](#两种查询方式)
      - [Query DSL](#query-dsl)
      - [match全文检索](#match全文检索)
      - [filter过滤](#filter过滤)
      - [term](#term)
      - [aggregations（执行聚合）](#aggregations执行聚合)
      - [mapping映射(后续不支持，索引下面不带类型)](#mapping映射后续不支持索引下面不带类型)
    - [IK分词](#ik分词)
    - [自定义扩展分词器](#自定义扩展分词器)
    - [安装nginx](#安装nginx)
    - [Elasticsearch-Rest-Client](#elasticsearch-rest-client)
    - [商品上架-sku在es中的存储](#商品上架-sku在es中的存储)
  - [客户端首页TODO](#客户端首页todo)
  - [性能压测](#性能压测)
    - [jmeter](#jmeter)
    - [性能监控-jvisualvm](#性能监控-jvisualvm)
    - [性能优化-模拟内存崩溃处理](#性能优化-模拟内存崩溃处理)
    - [简单优化接口](#简单优化接口)
  - [缓存和分布式锁](#缓存和分布式锁)
    - [本地缓存和分布式缓存](#本地缓存和分布式缓存)
      - [本地缓存](#本地缓存)
      - [redis](#redis)
      - [缓存-内存泄漏问题的解决和用户-堆外内存](#缓存-内存泄漏问题的解决和用户-堆外内存)
      - [缓存穿透，雪崩，击穿](#缓存穿透雪崩击穿)
      - [加锁解决穿透](#加锁解决穿透)
      - [本地锁在分布式场景下的问题-分布式锁](#本地锁在分布式场景下的问题-分布式锁)
    - [分布式锁-redisson](#分布式锁-redisson)
      - [使用](#使用-1)
      - [测试redission-lock+看门狗](#测试redission-lock看门狗)
      - [redission--读写锁测试](#redission--读写锁测试)
      - [分布式闭锁-CDL](#分布式闭锁-cdl)
      - [信号量](#信号量)
    - [缓存一致性](#缓存一致性)
    - [spring-cache](#spring-cache)
  - [搭建客户端页面检索](#搭建客户端页面检索)
  - [异步线程池](#异步线程池)
    - [thread&threadpool](#threadthreadpool)
    - [CompletableFuture异步编排](#completablefuture异步编排)
      - [1.创建异步对象 启动任务](#1创建异步对象-启动任务)
      - [2.回调和异常感知](#2回调和异常感知)
      - [3.handle最终处理](#3handle最终处理)
      - [4.线程串行化](#4线程串行化)
      - [5.两任务组合](#5两任务组合)
      - [一个完成(A,B,C  A或B完成一个执行C)](#一个完成abc--a或b完成一个执行c)
      - [7.多任务组合](#7多任务组合)
  - [商品详情](#商品详情)
  - [认证授权(Oauth2+JWT)](#认证授权oauth2jwt)
    - [用户登录注册](#用户登录注册)
    - [Oauth2&社交登录](#oauth2社交登录)
    - [微博登录](#微博登录)
    - [分布式session](#分布式session)
    - [springsession解决分布式session和子域共享](#springsession解决分布式session和子域共享)
    - [springsession原理](#springsession原理)
    - [单点登录](#单点登录)
    - [JWT](#jwt)
  - [购物车模块](#购物车模块)
  - [MQ](#mq)
    - [搭建](#搭建)
    - [boot+rabbitmq](#bootrabbitmq)
    - [rabbitmqlistener&rabbithandler](#rabbitmqlistenerrabbithandler)
    - [rabbitmq消息可靠性](#rabbitmq消息可靠性)
    - [消费端](#消费端)
  - [订单模块](#订单模块)
    - [搭建](#搭建-1)
    - [springsession搭建session共享](#springsession搭建session共享)
    - [订单](#订单)
    - [接口幂等性](#接口幂等性)
      - [解决方案](#解决方案)
    - [通过令牌解决订单确认页接口幂等](#通过令牌解决订单确认页接口幂等)
  - [分布式事务](#分布式事务)
    - [本地事务](#本地事务)
    - [为什么有分布式事务](#为什么有分布式事务)
    - [CAP和BASE](#cap和base)
      - [raft算法&paxos算法](#raft算法paxos算法)
      - [raft问题](#raft问题)
      - [base](#base)
      - [强一致性、弱一致性、最终一致性](#强一致性弱一致性最终一致性)
    - [分布式事务解决方案](#分布式事务解决方案)
      - [2PC](#2pc)
      - [3pc](#3pc)
      - [柔性事务-TCC 事务补偿型方案](#柔性事务-tcc-事务补偿型方案)
      - [柔性事务-最大努力通知型方案](#柔性事务-最大努力通知型方案)
      - [柔性事务-可靠消息+最终一致性方案（异步确保型）](#柔性事务-可靠消息最终一致性方案异步确保型)
    - [springcloud-alibaba-Seata](#springcloud-alibaba-seata)
      - [使用环境准备](#使用环境准备)
      - [整合](#整合)
    - [并发场景分布式事务](#并发场景分布式事务)
    - [订单服务(分布式事务完善)](#订单服务分布式事务完善)
    - [RabbitMQ延时队列-最终一致性](#rabbitmq延时队列-最终一致性)
    - [延时队列实现库存解锁](#延时队列实现库存解锁)
    - [订单-消息丢失-消息积压-重复等解决](#订单-消息丢失-消息积压-重复等解决)
      - [丢失](#丢失)
      - [重复](#重复)
      - [积压](#积压)
  - [支付(完善订单)](#支付完善订单)
    - [支付宝](#支付宝)
    - [模拟运行](#模拟运行)
      - [配置说明](#配置说明)
    - [内网穿透](#内网穿透)
    - [整合支付](#整合支付)
  - [秒杀模块和技术分析](#秒杀模块和技术分析)
    - [1.**后台添加商品**](#1后台添加商品)
    - [2.**定时任务&分布式任务调度**](#2定时任务分布式任务调度)
      - [定时任务](#定时任务)
      - [分布式任务调度](#分布式任务调度)
    - [3.时间日期处理](#3时间日期处理)
    - [4.商品上架](#4商品上架)
    - [5.幂等性保证](#5幂等性保证)
    - [6.查询秒杀商品和商品页面数据渲染](#6查询秒杀商品和商品页面数据渲染)
    - [](#)
    - [7.秒杀系统设计](#7秒杀系统设计)
    - [](#-1)
  - [sentinel](#sentinel)
    - [使用](#使用-2)
    - [引入&概念](#引入概念)
    - [整合springboot](#整合springboot)
    - [自定义流控响应](#自定义流控响应)
    - [sentinel全服务引入&流控](#sentinel全服务引入流控)
    - [溶断降级](#溶断降级)
    - [自定义保护资源](#自定义保护资源)
    - [网关流控](#网关流控)
  - [Sleuth+Zipkin 服务链路追踪](#sleuthzipkin-服务链路追踪)
    - [整合sleuth](#整合sleuth)
    - [整合zipkin](#整合zipkin)
    - [持久化](#持久化)

<!-- /TOC -->

# 架构和开发环境



## 架构图



![image-20210202152828738](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210202152828738.png)



## 基础环境搭建



### 基础软件

准备centos 7

1.安装docker

2.安装mysql

```
docker run -p 3306:3306 --name mysqlmall1 -v /mydata/mysql/log:/var/log/mysql -v /mydata/mysql/data:/var/lib/mysql -v /mydata/mysql/conf:/etc/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
```



修改配置文件

```
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci' init_connect='SET NAMES utf8' character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve
```



3.安装redis

```
mkdir -p /mydata/redis/conf

touch redis.conf
```

```
docker run -p 6379:6379 --name redismall1 -v /mydata/redis/data:/data -v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf -d redis:5.0.9 redis-server /etc/redis/redis.conf
```

```
需要修改什么配置文件 直接修改 mydata redis redis.conf即可
```

```
docker restart redismall1
```

### 开发环境

1. jdk  1.8 +

2. maven  3.6

   1. 修改配置文件setting.xml  1.8编译项目

   2. ```
      <profile>
      <id>jdk-1.8</id>
      <activation>
      <activeByDefault>true</activeByDefault>
      <jdk>1.8</jdk>
      </activation>
      <properties>
      <maven.compiler.source>1.8</maven.compiler.source>
      <maven.compiler.target>1.8</maven.compiler.target>
      <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
      </properties>
      </profile>
      ```

      

3. idea 安装插件

   1. lombok
   2. mybatisx



4. vscode



### 使用人人开源搭建后台管理系统

1. git clone xxx 
2. 删除git
3. 导入项目
4. 根据数据库执行不同的sql脚本，直接新创建后台数据库执行sql



### 搭建人人开源后台管理系统页面



1.git clone  xxx

2.删除git文件

3.导入项目

4.查看源

```
npm config get registry
npm config set registry https://registry.npm.taobao.org
```

问题：

Cannot find module 'node-sass'

```
cnpm install node-sass@latest
```

node-sass@4.13.1 postinstall: `node scripts/build.js`

```
npm config set sass_binary_site=https://npm.taobao.org/mirrors/node-sass
npm install
```

```
或者 cnpm install 


// 安装cnpm命令,不会改变npm的源
npm install -g cnpm --registry=https://registry.npm.taobao.org

//使用
cnpm install
```

5.依赖下载好以后  启动  `npm run dev`

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210207125018654.png" alt="image-20210207125018654" style="zoom:50%;" />



### 使用人人开源逆向工程

1. 使用人人开源逆向工程
2. git clone xxx
3. 参考文档修改配置文件 数据源信息，生成模块名信息，公共的类 在 人人开源 fast 里面找到，
4. 启动主配置类即可

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210207170905107.png" alt="image-20210207170905107" style="zoom:50%;" />

### 测试基础环境

1.把重复的代码都通过逆向工程生成了



#### 测试nacos

![image-20210207231106473](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210207231106473.png)

#### 测试feign

用户模块调用优惠券模块



![image-20210207231135943](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210207231135943.png)

#### 测试配置中心

1.依赖

2.创建配置文件  模块名.properties

3.使用   `@RefreshScope`   `@Value("${xxx.xxx}")`



#### 测试配置中心命名空间和配置分组

1.命名空间：环境隔离，可以以开发环境，测试环境，生产环境做隔离，也可以以服务名称做隔离

```
#spring.cloud.nacos.config.namespace=
```

2.配置集：

3.配置集id：

4.配置分组：不同环境用不同配置组，比如日常运行用一组，双十一用一组

```
#spring.cloud.nacos.config.group=
```



==每一个服务使用自己的命名空间，然后使用不同的配置分组==



#### 测试加载多个配置集

把以前的application.properties 配置文件拆分

```properties
spring.cloud.nacos.config.ext-config[0].data-id=datasource.properties
spring.cloud.nacos.config.ext-config[0].group=dev
spring.cloud.nacos.config.ext-config[0].refresh=true

spring.cloud.nacos.config.ext-config[1].data-id=other.properties
spring.cloud.nacos.config.ext-config[1].group=dev
spring.cloud.nacos.config.ext-config[1].refresh=true
```



==完整配置内容==



![image-20210207235113577](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210207235113577.png)

```properties

#spring.application.name=viwmall-coupon
#spring.cloud.nacos.config.server-addr=192.168.199.185:8848
#spring.cloud.nacos.config.namespace=16ffcdfe-4e67-46ba-a35a-3b2ea841e184
#spring.cloud.nacos.config.group=dev
#spring.cloud.nacos.config.ext-config[0].data-id=datasource.properties
#spring.cloud.nacos.config.ext-config[0].group=dev
#spring.cloud.nacos.config.ext-config[0].refresh=true
#
#spring.cloud.nacos.config.ext-config[1].data-id=other.properties
#spring.cloud.nacos.config.ext-config[1].group=dev
#spring.cloud.nacos.config.ext-config[1].refresh=true
```



#### 测试网关



## 前端技术栈

项目中需要的前端技术栈, 



https://gitee.com/xiaobo97/simple-viw-web/blob/master/edupaas_perent/%E9%A1%B9%E7%9B%AE0-1%E8%AF%A6%E7%BB%86%E6%96%87%E6%A1%A3.md

1.vscode

2.ES6

3.Node.js

4.vue

5.babel

6.webpack



### 后台管理系统前端环境



1.安装  `npm install webpakc -g`

2.安装脚手架  `npm install -g@vue/cli-init`  如果不行就先执行  `npm install -g @vue/cli`

3.新创建个文件夹 存放vue初始文件目录

`vue init webpack vue-demo`   vue-demo是的项目名

![image-20210208122221028](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210208122221028.png)

5.`npm run dev`  

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210208123525950.png" alt="image-20210208123525950" style="zoom:50%;" />

![image-20210208123537185](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210208123537185.png)

6.vscode 配置代码片段

```js
{
	"生成 vue 模板": {
		"prefix": "vue",
		"body": [
		"<template>",
		"<div></div>",
		"</template>",
		"",
"<script>",
"//这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json文件，图片文件等等）",
"//例如：import 《组件名称》 from '《组件路径》';",
"",
"export default {",
"//import 引入的组件需要注入到对象中才能使用",
"components: {},",
"props: {},",
"data() {",
"//这里存放数据",
"return {",
"",
"};",
"},",
"//计算属性 类似于 data 概念",
"computed: {},",
"//监控 data 中的数据变化",
"watch: {},",
"//方法集合",
"methods: {",
"",
"},",
"//生命周期 - 创建完成（可以访问当前 this 实例）",
"created() {",
"",
"},",
"//生命周期 - 挂载完成（可以访问 DOM 元素）",
"mounted() {",
"",
"},",
"beforeCreate() {}, //生命周期 - 创建之前",
"beforeMount() {}, //生命周期 - 挂载之前",
"beforeUpdate() {}, //生命周期 - 更新之前",
"updated() {}, //生命周期 - 更新之后",
"beforeDestroy() {}, //生命周期 - 销毁之前",
"destroyed() {}, //生命周期 - 销毁完成",
"activated() {}, //如果页面有 keep-alive 缓存功能，这个函数会触发",
"}",
"</script>",
"<style lang='scss' scoped>",
"//@import url($3); 引入公共 css 类",
"$4",
"</style>"
],
"description": "生成 vue 模板"
}
}
```





## 后台管理系统api



### 商品服务api



利用脚手架编写菜单

1.

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210208235727645.png" alt="image-20210208235727645" style="zoom:50%;" />

2.

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210208235806961.png" alt="image-20210208235806961" style="zoom:50%;" />



**编写商品服务三级分类接口**



​	编写商品服务网关统一配置 和解决跨域的配置

​	1.通过过滤路径重写+解决跨域 实现后台管理页面的访问



​	编写三级分类 增删改查api



​	编写三级分类页面展示

![image-20210222211919116](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210222211919116.png)

​	编写三级分类增加分类

​	编写三级分类修改分类

​	编写三级分类修改可拖拽节点功能页面效果

​	编写三级分类批量修改功能

​	编写三级分类批量拖拽节点提交保存功能

​	哪些三级分类节点批量删除功能



**编写商品服务品牌管理功能**

​	编写新增品牌管理菜单页面

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223013302176.png" alt="image-20210223013302176" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223013342548.png" alt="image-20210223013342548" style="zoom:50%;" />

==使用人人开源逆向工程生成的公共增删改查vue模板来作为品牌管理的页面快速构建==

通过 `renren-generator` 工程为我们生成的 ![image-20210223210219624](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223210219624.png)

然后解压，把下面这两个放到对应的目录下

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223210357570.png" alt="image-20210223210357570" style="zoom:50%;" />

![image-20210223211227826](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223211227826.png)

可以看到生成的页面

![image-20210223211248006](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223211248006.png)

然后放开权限，就可以看到页面中的增删改查按纽了，然后就可以正常的增删改查品牌了，如果我们要自定义开发的话就需要自己去开发，这个是人人开源为我们生成好的。

当然也可以在这个基础上做二次开发，修改，页面基本功能满足了后端开发人员需要的页面要求了，

![image-20210223211543379](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223211543379.png)

![image-20210223211556486](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223211556486.png)



​	编写逆向工程生成的品牌管理页面中状态显示开关<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210223215633890.png" alt="image-20210223215633890" style="zoom:50%;" />

​	编写品牌管理logn文件上传功能，

使用阿里oss签名直传(这个之前写过了，都是重复的代码，粘贴复制就完事了，大体是一样的。)，这个签名的意思是，用户请求上传后我们服务器响应一段签名，然后阿里oss验证这个签名是否是我们的，是否合法，满足就允许用户上传。

https://github.com/alibaba/aliyun-spring-boot/tree/master/aliyun-spring-boot-samples/aliyun-oss-spring-boot-sample

使用 spring-cloud-alibaba-oss 封装的ali-sdk-oss，不使用原生上传。只需要配置和少量代码即可

```
代码如下
pom.xml
<!--        封装的ali oss sdk-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alicloud-oss</artifactId>
        </dependency>
        
测试代码
spring.cloud.alicloud.access-key=xxx
spring.cloud.alicloud.secret-key=xxxx
spring.cloud.alicloud.oss.endpoint=oss-cn-hangzhou.aliyuncs.com
配置文件
 @Test
    public void uploadImg() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\doc\\1.png");
        ossClient.putObject("xiaobo-project","001.png",fileInputStream);
        ossClient.shutdown();
        System.out.println("OK");
    }
```

​	编写第三方oss上传服务的获取服务端签名功能

==总结，先向服务器发请求要签名，要到了浏览器再发给oss请求上传文件，这样就减少了服务器的压力和瓶颈==



​	编写品牌新增功能和表单校验和自定义校验器

​	编写品牌管理后台 JSR303数据校验功能代码 完善后台数据校验

如controller  api方法上使用 `@Vaile` + 实体类属性 `@NotNull` 实现 后台数据校验

```
 @RequestMapping("/save")
    public R save(@Valid @RequestBody BrandEntity brand){
		brandService.save(brand);
        return R.ok();
    }
```

```
@NotBlank  //数据校验
	private String name;
```

需要自带的数据校验规则，也可以自定义数据校验规则

![image-20210224232348172](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210224232348172.png)

​	**编写后台管理系统统一异常处理**

使用`@ControllerAdvice`

在公共模块使用enum统一异常Code,方便前后端调试。

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210224234920889.png" alt="image-20210224234920889" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210224234934743.png" alt="image-20210224234934743" style="zoom:50%;" />

​	**编写后台管理商品服务模块 品牌管理 JSR303  分组校验**

比如下面这种情况，场景不同数据校验规则不同，使用分组数据校验

```java
/**
	 * 品牌id
	 */
	@NotNull(message = "修改必须指定品牌id")
	@Null(message = "新增不能指定id")
	@TableId
	private Long brandId;
```

```java
	@NotNull(message = "修改必须指定品牌id",groups = {UpdateGroup.class}) // 修改组，id不能为空
	@Null(message = "新增不能指定id",groups = {AddGroup.class})
```

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210224235846349.png" alt="image-20210224235846349" style="zoom:50%;" />

使用 `@Validated` 

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210225000042485.png" alt="image-20210225000042485" style="zoom:50%;" />



​	**编写商品服务 品牌管理 JSR303 自定义数据校验注解 **

1.编写自定义数据校验注解

2.编写自定义数据校验器

3.关联自定义的数据校验注解和自定义数据校验器

​	

  **商品服务-电商概念-SPU&SKU&规格参数&销售属性**

spu-规格参数，比如小米8，小米6，属于手机规格，他们的主要规格属性是一样的，值不一样，我们可以根据这是规格参数作为条件查询

sku-销售属性，比如小米8，不同颜色，不同内存 库存是不一样的，

一个商品的存放的表关系

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210225005134427.png" alt="image-20210225005134427" style="zoom:50%;" />



<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210225005248729.png" alt="image-20210225005248729" style="zoom:50%;" />



​	**编写商品服务 属性分组维护 前端公共组件和父子组件**

vue父子组件，点击父组件，子组件感知，

 父子组件-点击category  attrgroup感知，子组件给父组件传递数据

```
/**
 * 父子组件传递数据
 * 1)、子组件给父组件传递数据，事件机制；
 *    子组件给父组件发送一个事件，携带上数据。
 * // this.$emit("事件名",携带的数据...)
 */
```

​	<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210225224704953.png" alt="image-20210225224704953" style="zoom:50%;" />![image-20210225224814973](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210225224814973.png)

​	点击电子书，右边查询出电子书属性，点击第三级分类 查询指定属性



​	**编写商品服务  属性分组  获取属性分组功能代码 前台代码和后台查询代码**



​	**编写商品服务前台页面 平台属性  属性分组新增  和级联选择器代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210225225108111.png" alt="image-20210225225108111" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210225225122746.png" alt="image-20210225225122746" style="zoom:50%;" />

![image-20210225230315440](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210225230315440.png)

​	

​		**编写商品服务前台页面 平台属性   属性分组修改  和级联选择器回显代码**



​		**编写品牌管理  品牌分类关联和级联数据回显**

一个品牌多个分类，一个分类多个品牌

![image-20210226002720902](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210226002720902.png)

​		**编写品牌管理  品牌分类关联 更新数据时 级联更新**

​		**编写平台属性-规格参数新增和封装VO代码**

PO：对应表中的一条记录，持久化对象

DO：领域对象，从现实世界中抽取的业务实体

TO：数据传输对象，如服务与服务之间传输数据封装的对象

DTO：数据传输对象

VO：一般是view object 用来业务层之间传输封装的数据，如业务层处理数据封装VO 响应给前端

BO：业务对象，主要是调用DAO,封装PO VO 组成BO，如简历，个人信息PO，工作经历PO，封装成简历就是BO

POJO：传统java对象 POJO是PO DO DTO VO的统称

DAO：数据访问对象 如mapper



​	**编写平台属性-规格参数列表查询后台代码**

​	**编写平台属性-规格参数列表修改代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227091714205.png" alt="image-20210227091714205" style="zoom:50%;" />

​	**编写平台属性-销售属性增删改查等维护代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227095402985.png" alt="image-20210227095402985" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227095417738.png" alt="image-20210227095417738" style="zoom:50%;" />



​	**编写分组与属性关联的功能，获取属性分组的关联的所有属性列表**

`/product/attrgroup/{attrgroupId}/attr/relation`

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227095657145.png" alt="image-20210227095657145" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227101203477.png" alt="image-20210227101203477" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227101213952.png" alt="image-20210227101213952" style="zoom:50%;" />

​	**编写平台属性-查询属性分组没有关联的其他属性**

​	**编写平台属性-添加属性 和属性分组关联关系的代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227105245610.png" alt="image-20210227105245610" style="zoom:50%;" />



​	**编写商品服务-维护新增商品部分接口和 会员服务维护所有用户会员等级**

五个步骤

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227111425270.png" alt="image-20210227111425270" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227121505889.png" alt="image-20210227121505889" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227121555949.png" alt="image-20210227121555949" style="zoom:50%;" />



​	**新增商品--获取分类关联的品牌**

​	**编写获取分类下所有分组和关联属性**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227141802941.png" alt="image-20210227141802941" style="zoom:50%;" />

​	**编写商品增加 VO和增加spu代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227142243923.png" alt="image-20210227142243923" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227142336860.png" alt="image-20210227142336860" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227142418000.png" alt="image-20210227142418000" style="zoom:50%;" />

抽取添加商品VO

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227143001973.png" alt="image-20210227143001973" style="zoom:50%;" />

生成实体类  把json转实体类  利用工具

![image-20210227143153965](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227143153965.png)



​	**编写-商品添加-保存sku基本信息代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210227161219375.png" alt="image-20210227161219375" style="zoom:50%;" />

​	**编写 open feign 调用保存优惠券服务**

​	**编写完善添加商品代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210228010127921.png" alt="image-20210228010127921" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210228010155529.png" alt="image-20210228010155529" style="zoom:50%;" />

==TODO：需要处理各种商品添加场景，分布式事务等等一系列==



​	**编写商品管理-spu检索功能代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210228012005481.png" alt="image-20210228012005481" style="zoom:50%;" />



​	**编写商品管理-sku检索功能代码**

![image-20210228012826333](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210228012826333.png)





### 仓储服务api



​		**编写仓储服务列表功能和服务配置功能(网关等)**

​		**编写库存api列表和采购单需求api代码**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210228224449275.png" alt="image-20210228224449275" style="zoom:50%;" />

新增采购需求，再根据需求去采购商品，自动添加到库存

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210228224634364.png" alt="image-20210228224634364" style="zoom:50%;" />



​	**编写合并采购需求代码**

采购流程

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210228225037861.png" alt="image-20210228225037861" style="zoom:50%;" />

​		**编写领取采购单id代码**



​		**仓库服务---编写完成采购完整代码**

​		

​		**编写商品服务-商品管理-SPU规格维护代码**



## ES

### 概念

http://www.xiaobo.life/archives/775/

https://gitee.com/xiaobo97/viv-notes/tree/master/%E5%90%8E%E7%AB%AF%E7%9F%A5%E8%AF%86/%E6%90%9C%E7%B4%A2%E5%BC%95%E6%93%8E/solr

### 安装

docker安装ES和kibana

`docker pull kibana:7.7.0`

![image-20210301002806819](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301002806819.png)

```
mkdir -p /mydata/elasticsearch/config
mkdir -p /mydata/elasticsearch/data
```

启动不了 换256m

```
docker run --name elasticsearch-viwmall1 -p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms64m -Xmx512m" \
-v /mydata/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-v /mydata/elasticsearch/data:/usr/share/elasticsearch/data \
-v /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
-d elasticsearch:7.7.0
```

```
docker run --name elasticsearch-viwmall1 -p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms64m -Xmx128m" \
-v /mydata/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-v /mydata/elasticsearch/data:/usr/share/elasticsearch/data \
-v /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
-d elasticsearch:7.7.0
```

![image-20210301003642727](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301003642727.png)

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301005951491.png" alt="image-20210301005951491" style="zoom:50%;" />

启动kibana

```
docker run --name kibana -e ELASTICSEARCH_HOSTS=http://121.xx.xxx.xxx:9200 -p 5601:5601 \
-d kibana:7.7.0
```

![image-20210301011111368](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301011111368.png)





### 使用

put&post 

get&乐观锁

乐观锁： `?if_seq_no=1&if_primary_tern=1`

删除数据：

bulk批量导入：post请求 `http:ip/xxx/xxx/_bulk`  两行为一个操作，上面是index，下面是保存的数据体

```
{"index":{"_id":"1"}}
{"name": "John Doe" }
{"index":{"_id":"2"}}
{"name": "Jane Doe" }
```

![image-20210301014156043](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301014156043.png)



### 进阶

测试数据

https://raw.githubusercontent.com/elastic/elasticsearch/master/docs/src/test/resources/accounts.json

![image-20210301014824809](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301014824809.png)

官方api文档 https://www.elastic.co/guide/en/elasticsearch/reference/7.7/index.html

#### 两种查询方式

1.REST request URI 发送搜索参数（uri+检索参数）

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301233319296.png" alt="image-20210301233319296" style="zoom:50%;" />

2.REST request body 来发送它们（uri+请求体）

**Query DSL**

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301233401466.png" alt="image-20210301233401466" style="zoom:50%;" />



#### Query DSL

1.语法例子

```json
GET bank/_search
{
  "query": {
    "match_all": {}
  },
  "from": 0,
  "size": 5,
  "sort": [
    {
      "account_number": {
        "order": "desc"
      }
    }
  ]
}
```

```
   "account_number":   --- 字段
        "order": "desc" --  什么规则 ： 具体参数规则值
```

#### match全文检索

1.

![image-20210301233954566](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301233954566.png)

2.将需要匹配的值当成一个整体单词（不分词）进行检索

![image-20210301234104356](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301234104356.png)

3.multi_match【多字段匹配】

![image-20210301234414160](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301234414160.png)

4.bool复合查询

![image-20210301234534175](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301234534175.png)



![image-20210301234651048](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301234651048.png)

![image-20210301234839850](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301234839850.png)



#### filter过滤

1.

![image-20210301234959225](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301234959225.png)

#### term

1.非文本字段用term  match会分词

![image-20210301235131343](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301235131343.png)

#### aggregations（执行聚合）

可以执行查询和多个聚合，

聚合语法

```
"aggregations" : {
    "<aggregation_name>" : {
        "<aggregation_type>" : {
            <aggregation_body>
        }
        [,"meta" : {  [<meta_data_body>] } ]?
        [,"aggregations" : { [<sub_aggregation>]+ } ]?
    }
    [,"<aggregation_name_2>" : { ... } ]*
}
```

聚合类型

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301235725259.png" alt="image-20210301235725259" style="zoom:50%;" />





1.

aggs 开始聚合

group_by_state聚合名称

terms 聚合类型

field 聚合字段

size=0 只看聚合结果

![image-20210301235549536](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210301235549536.png)

2.聚合再聚合再聚合

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302000425179.png" alt="image-20210302000425179" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302000439649.png" alt="image-20210302000439649" style="zoom:50%;" />



#### mapping映射(后续不支持，索引下面不带类型)

Mapping 是用来定义一个文档（document），以及它所包含的属性（field）是如何存储和 索引的。



1.

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302000629277.png" alt="image-20210302000629277" style="zoom:50%;" />

2. 默认给我们测试数据的类型 

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302000938004.png" alt="image-20210302000938004" style="zoom:50%;" />

3 创建映射

![image-20210302001146447](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302001146447.png)

4.添加新的字段映射

```
PUT /my-index/_mapping
{ "properties": { "employee-id": { "type": "keyword", "index": false
}
}
}
```

5.更新映射--》更新必须创建新的索引进行数据迁移

```
先创建出 new_twitter 的正确映射。然后使用如下方式进行数据迁移
POST _reindex [固定写法]
{ "source": { "index": "twitter"
},"dest": { "index": "new_twitter"
}
}
将旧索引的 type 下的数据进行迁移
POST _reindex
{ "source": {
"index": "twitter", "type": "tweet"
},"dest": { "index": "tweets"
}
}
```



### IK分词

1.IK版本跟着ES版本走

https://github.com/medcl/elasticsearch-analysis-ik/releases/tag/v7.7.0



2.正确docker启动es时 映射了文件夹 plugins

![image-20210302001900815](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302001900815.png)

![image-20210302001945990](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302001945990.png)

但是容器里面很多命令操作不了，需要下载很多东西，比如我前面遇到了很多问题



```
//下载 
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.7.0/elasticsearch-analysis-ik-7.7.0.zip

```

unzip 解压

![image-20210302012122165](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302012122165.png)

![image-20210302013356359](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302013356359.png)



docker内部查看plugins

![image-20210302013036892](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302013036892.png)

`dockers restart 482` 重启容器

访问 5601的kibana

测试

![image-20210302013522795](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302013522795.png)

### 自定义扩展分词器

### 安装nginx

自定义扩展分词器用

1.

```
 随便启动一个 nginx 实例，只是为了复制出配置
 docker run -p 80:80 --name nginx -d nginx:1.10
 将容器内的配置文件拷贝到当前目录：docker container cp nginx:/etc/nginx .  别忘了后面的点
 修改文件名称：mv nginx conf 把这个 conf 移动到/mydata/nginx 下
 终止原容器：docker stop nginx
 执行命令删除原容器：docker rm $ContainerId
 创建新的 nginx；执行以下命令
```

![image-20210302014840276](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302014840276.png)

![image-20210302014624981](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302014624981.png)

```
docker run -p 80:80 --name nginx-viwmall \
-v /mydata/nginx/html:/usr/share/nginx/html \
-v /mydata/nginx/logs:/var/log/nginx \
-v /mydata/nginx/conf:/etc/nginx \
-d nginx:1.10
```

![image-20210302014912447](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302014912447.png)

![image-20210302014940747](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302014940747.png)

![image-20210302015059173](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302015059173.png)

创建分词txt

![image-20210302015241103](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302015241103.png)

这个fenci.txt作为我们的远程分词库

修改配置文件

![image-20210302015443556](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302015443556.png)

`vi IKAnalyzer.cfg.xml`

![image-20210302015609767](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302015609767.png)

重启ES

==以后新的单词就直接在fenci.txt添加即可==

### Elasticsearch-Rest-Client

1.客户端

![image-20210302020221683](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302020221683.png)

java rest client 基于 9200

java-api 基于 9300

2.

https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.7/index.html

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302020526718.png" alt="image-20210302020526718" style="zoom: 50%;" />

3.使用

[Java High Level REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.7/java-rest-high.html)



4.新建工程

使用spring初始化向导

![image-20210302020830282](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302020830282.png)

![image-20210302021009959](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302021009959.png)

5.依赖

```xml
<!--        ES ==================  -->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>7.7.0</version>
            <exclusions>
                <exclusion>
                    <groupId>org.elasticsearch</groupId>
                    <artifactId>elasticsearch</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.elasticsearch.client</groupId>
                    <artifactId>elasticsearch-rest-client</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>elasticsearch</artifactId>
            <version>7.7.0</version>
        </dependency>
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-client</artifactId>
            <version>7.7.0</version>
        </dependency>
<!--        ===================================-->
```





5.看文档初始化

![image-20210302215746918](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302215746918.png)



6.测试

![image-20210302222915346](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302222915346.png)

```java
 @Test
    public void indexDate() throws Exception{
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");//数据的id
//        indexRequest.source("userName","zhangsan","age",18,"gender","男");
        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(18);
        user.setGender("男");
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);//要保存的内容
        //执行操作
        IndexResponse index = client.index(indexRequest, ViwmallElasticSearchConfig.COMMON_OPTIONS);

        //提取有用的响应数据
        System.out.println(index);
    }
           //IndexResponse[index=users,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]

```

![image-20210302223809204](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302223809204.png)



<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302224432024.png" alt="image-20210302224432024" style="zoom:50%;" />



### 商品上架-sku在es中的存储



1. 设计商品存在ES中的索引结构类型

```
PUT product
{
  "mappings": {
    "properties": {
      "skuId": {
        "type": "long"
      },
      "spuId": {
        "type": "keyword"
      },
      "skuTitle": {
        "type": "text",
        "analyzer": "ik_smart"
      },
      "skuPrice": {
        "type": "keyword"
      },
      "skuImg": {
        "type": "keyword",
        "index": false,
        "doc_values": false
      },
      "saleCount": {
        "type": "long"
      },
      "hasStock": {
        "type": "boolean"
      },
      "hotScore": {
        "type": "long"
      },
      "brandId": {
        "type": "long"
      },
      "catalogId": {
        "type": "long"
      },
      "brandName": {
        "type": "keyword",
        "index": false,
        "doc_values": false
      },
      "brandImg": {
        "type": "keyword",
        "index": false,
        "doc_values": false
      },
      "catalogName": {
        "type": "keyword",
        "index": false,
        "doc_values": false
      },
      "attrs": {
        "type": "nested",
        "properties": {
          "attrId": {
            "type": "long"
          },
          "attrName": {
            "type": "keyword",
            "index": false,
            "doc_values": false
          },
          "attrValue": {
            "type": "keyword"
          }
        }
      }
    }
  }
}
```

![image-20210303000944258](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303000944258.png)

2. ES数据类型netsted类型的场景

![image-20210302235934954](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210302235934954.png)



3. 根据前面的ES中的商品模型构造基本商品上架TO



## 客户端首页TODO



1. 使用thymeleaf搭建首页

引入thymeleaf依赖

```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

静态资源放 resource  模板放templates

![image-20210303230009603](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303230009603.png)

2. 

引 devtools 依赖 然后 ctrl+F9 可以快速看修改的内容



3. nginx-搭建客户端访问环境-反向代理

==反向代理配置==

分有域名和没域名(不过DNS，在本地直接配置)的情况

利用![image-20210303233704484](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303233704484.png)

解压管理员打开 可以看到当前域名映射规则



<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303233822255.png" alt="image-20210303233822255" style="zoom:50%;" />

新增方案

![image-20210303234202996](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303234202996.png)

默认访问的是 nginx的index页面

![image-20210303234434927](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303234434927.png)



访问流程，相当于本地添加了域名-ip 的映射规则，然后nginx把请求转网关，网关再处理

然后需要让nginx开启配置好反向代理 所有viwmall.com 请求转商品模块服务

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303234323064.png" alt="image-20210303234323064" style="zoom:50%;" />

修改nginx配置文件

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303234948590.png" alt="image-20210303234948590" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210303235103810.png" alt="image-20210303235103810" style="zoom:50%;" />



4. nginx-负载均衡到网关

配置了个上游服务地址配置

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304000456978.png" alt="image-20210304000456978" style="zoom:50%;" />

修改con.d中的配置文件，让网关处理我们的请求

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304000705992.png" alt="image-20210304000705992" style="zoom:50%;" />

修改成下面的

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304000741365.png" alt="image-20210304000741365" style="zoom:50%;" />

最后是这样的

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304000830747.png" alt="image-20210304000830747" style="zoom:50%;" />

整体流程，

![image-20210304001009484](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304001009484.png)

然后配置网关gatewary

==配置nginx，防止丢失信息，如host cookie==

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304002217321.png" alt="image-20210304002217321" style="zoom:50%;" />





## 性能压测

### jmeter

1.

```
响应时间（Response Time: RT）
响应时间指用户从客户端发起一个请求开始，到客户端接收到从服务器端返回的响
应结束，整个过程所耗费的时间。
 HPS（Hits Per Second） ：每秒点击次数，单位是次/秒。
 TPS（Transaction per Second）：系统每秒处理交易数，单位是笔/秒。
 QPS（Query per Second）：系统每秒处理查询次数，单位是次/秒。
对于互联网业务中，如果某些业务有且仅有一个请求连接，那么 TPS=QPS=HPS，一
般情况下用 TPS 来衡量整个业务流程，用 QPS 来衡量接口查询次数，用 HPS 来表
示对服务器单击请求。
 无论 TPS、QPS、HPS,此指标是衡量系统处理能力非常重要的指标，越大越好，根据经
验，一般情况下：
金融行业：1000TPS~50000TPS，不包括互联网化的活动
保险行业：100TPS~100000TPS，不包括互联网化的活动
制造行业：10TPS~5000TPS
互联网电子商务：10000TPS~1000000TPS
互联网中型网站：1000TPS~50000TPS
互联网小型网站：500TPS~10000TPS
 最大响应时间（Max Response Time） 指用户发出请求或者指令到系统做出反应（响应）
的最大时间。
 最少响应时间（Mininum ResponseTime） 指用户发出请求或者指令到系统做出反应（响
应）的最少时间。
 90%响应时间（90% Response Time） 是指所有用户的响应时间进行排序，第 90%的响
应时间。
 从外部看，性能测试主要关注如下三个指标
吞吐量：每秒钟系统能够处理的请求数、任务数。
响应时间：服务处理一个请求或一个任务的耗时。
错误率：一批请求中结果出错的请求所占比例。
```

2. 模拟测试 www.baidu.com



关于jmeter在 viv-notes中的tools

安装好 cmd 直接输入 jmeter启动

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304012151683.png" alt="image-20210304012151683" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304012209748.png" alt="image-20210304012209748" style="zoom:50%;" />



3. JMeter Address Already in use 错误解决

```
windows 本身提供的端口访问机制的问题。
Windows 提供给 TCP/IP 链接的端口为 1024-5000，并且要四分钟来循环回收他们。就导致
我们在短时间内跑大量的请求时将端口占满了。
1.cmd 中，用 regedit 命令打开注册表
2.在 HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Tcpip\Parameters 下，
1 .右击 parameters，添加一个新的 DWORD，名字为 MaxUserPort
2 .然后双击 MaxUserPort，输入数值数据为 65534，基数选择十进制（如果是分布式运
行的话，控制机器和负载机器都需要这样操作哦）
3. 修改配置完毕之后记得重启机器才会生效
https://support.microsoft.com/zh-cn/help/196271/when-you-try-to-connect-from-tcp-ports-grea
ter-than-5000-you-receive-t
TCPTimedWaitDelay：30
```

![image-20210304013319127](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304013319127.png)

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304013416411.png" alt="image-20210304013416411" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304013540559.png" alt="image-20210304013540559" style="zoom:50%;" />



### 性能监控-jvisualvm

1. CMD直接输入 jvisualvm

![image-20210304014815103](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304014815103.png)

2.安装插件查看gc情况



<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304015323035.png" alt="image-20210304015323035" style="zoom:50%;" />

查看 jdk版本 我的是271

![image-20210304015552033](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304015552033.png)

选择这个

https://blog.csdn.net/weixin_38750084/article/details/103755502

https://blog.csdn.net/ailice001/article/details/82852280?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_baidulandingword-1&spm=1001.2101.3001.4242

![image-20210304015634460](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304015634460.png)

..剩下的百度

![image-20210304020327139](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304020327139.png)

3. 测试中间件对性能的影响- `docker stats`

直接给服务器nginx 发请求 测试，单测nginx

`docker stats` 

![image-20210304020155366](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304020155366.png)

50个线程，-Xms512m

```
压测内容 压测线程数 吞吐量/s 90%响应时间 99%响应时间
Nginx 50 2335 11 944
Gateway 50 10367 8 31
简单服务 50 11341 8 17
首页一级菜单渲染 50 270(db,thymeleaf) 267 365
首页渲染（开缓存） 50 290 251 365
首页渲染（开缓存、
优化数据库、关日
志）
50 700 105 183
三级分类数据获取 50 2(db)/8(加索引) ... .. 三级分类（优化业
务）
50 111 571 896
三 级 分 类 （ 使 用
redis 作为缓存）
50 411 153 217
首页全量数据获取 50 7(静态资源)
Nginx+Gateway 50
Gateway+简单服务 50 3126 30 125
全链路 50 800 88 310
```

==中间件越多，性能损失越大，大多都损失在网络交互了；==



4. 简单优化吞吐量



5. nginx 动静分离

1.流程

![image-20210304021436360](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304021436360.png)

2.把静态资源放到nginxhtml目录下

![image-20210304221816163](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304221816163.png)

![image-20210304221907928](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304221907928.png)

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304222553706.png" alt="image-20210304222553706" style="zoom:50%;" />

就相当于静态资源直接就放在nginx默认的html下，访问结束 http://ip/static/xxx/xxx.js 就可以访问到

![image-20210304223101510](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304223101510.png)



### 性能优化-模拟内存崩溃处理

1.设置工程运行 vm

![image-20210304224025170](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304224025170.png)

2.jmeter启动200个线程请求



3.通过 jvisualvm 的gc插件 观察

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304224112163.png" alt="image-20210304224112163" style="zoom:50%;" />

然后就会 ==OOM==



重新设置  重新启动 重新连接

![image-20210304224328256](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304224328256.png)

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210304224400838.png" alt="image-20210304224400838" style="zoom:50%;" />



### 简单优化接口

1.重代码层面去优化

2.



## 缓存和分布式锁



### 本地缓存和分布式缓存

#### 本地缓存



#### redis

1.配置

2.使用redis优化我们的接口

#### 缓存-内存泄漏问题的解决和用户-堆外内存

1.

![image-20210305002853050](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305002853050.png)

2.

boot操作redis客户端新版是 lettuce，lettuce底层使用的是netty， 服务内存设置小就会出现上面的情况，使用使用jedis这种老版客户端

#### 缓存穿透，雪崩，击穿

1.

缓存穿透是指查询一个一定不存在的数据，由于缓存是不命中，将去查询数据库，但是数 据库也无此记录，我们没有将这次查询的 null 写入缓存，这将导致这个不存在的数据每次 请求都要到存储层去查询，失去了缓存的意义。  在流量大时，可能 DB 就挂掉了，要是有人利用不存在的 key 频繁攻击我们的应用，这就是 漏洞。

2.

缓存雪崩是指在我们设置缓存时采用了相同的过期时间，导致缓存在某一时刻同时失 效，请求全部转发到 DB，DB 瞬时压力过重雪崩。



3.

对于一些设置了过期时间的 key，如果这些 key 可能会在某些时间点被超高并发地访问， 是一种非常“热点”的数据。  这个时候，需要考虑一个问题：如果这个 key 在大量请求同时进来前正好失效，那么所 有对这个 key 的数据查询都落到 db，我们称为缓存击穿。

#### 加锁解决穿透

1.



#### 本地锁在分布式场景下的问题-分布式锁

1.

本地锁只能针对本地服务。集群分布式场景不适合

2.基本流程

![image-20210305005410860](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305005410860.png)

3.实现原理

用的redis setnx命令

http://www.redis.cn/commands/setnx.html

如redis命令行命令是  `set lock aaa nx`

![image-20210305005521745](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305005521745.png)

![image-20210305010624014](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305010624014.png)

上图的问题可能出现下面的情况，

![image-20210305010758272](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305010758272.png)

只设置过期时间可能出现的问题

![image-20210305010922045](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305010922045.png)

==加锁同时设置过期时间，这需要是一个原子操作==

```java
   //setIfAbsent 如果缺少key 。 设置key来保存字符串value和过期timeout。加锁并同时设置过期时间
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 300, TimeUnit.SECONDS);
```

==删锁的问题==

![image-20210305011317007](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305011317007.png)

进阶

可能出现删别人的锁的情况

![image-20210305011335078](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305011335078.png)

![image-20210305011826190](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305011826190.png)

```java
 // 最后调用业务处理逻辑就解锁
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                //删除锁 通过uuid来保证删自己的锁 成功返回1 不成功返回0
                Long lock1 = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class)
                        , Arrays.asList("lock"), uuid);
```

==加锁保证原子性，解锁保证原子性==

==还要考虑锁的续期==

==最后就是分布式场景只有一个服务查询了数据库==

### 分布式锁-redisson

#### 使用

https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8

使用redissionclient api来操作redis 分布式锁

1.官方文档给出的java分布式redis锁

![image-20210305012612405](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305012612405.png)

2.

https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95

![image-20210305012822093](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305012822093.png)

3.

```xml
 <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson</artifactId>
            <version>3.12.0</version>
        </dependency>
```

4.配置redission

官方文档：

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305232430077.png" alt="image-20210305232430077" style="zoom:50%;" />

单node redis

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305232528671.png" alt="image-20210305232528671" style="zoom:50%;" />

#### 测试redission-lock+看门狗

1.

```
   RLock lock = redisson.getLock("my-lock");

        //2、加锁
        lock.lock(); //阻塞式等待。默认加的锁都是30s时间。
        .....
        lock.unlock();
```

2.

![image-20210305234653408](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305234653408.png)

![image-20210305234639053](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305234639053.png)

3.

==两个服务，其中一个服务关了，另外一个在默认超时时间以后都没有释放锁(默认的ttl)，已关闭的那个服务自动释放锁，其他服务继续获取锁==

==看门狗：锁的自动续期，业务执行需30s，而锁只有10s，==

我们自己指定lock的超时时间 就没有看门狗，使用默认的 lock.lock()就有看门狗

![image-20210305235022154](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210305235022154.png)

4.

解决死锁代码：

```java
 //1、如果我们传递了锁的超时时间，就发送给redis执行脚本，进行占锁，默认超时就是我们指定的时间
        //2、如果我们未指定锁的超时时间，就使用30 * 1000【LockWatchdogTimeout看门狗的默认时间】;
        //    只要占锁成功，就会启动一个定时任务【重新给锁设置过期时间，新的过期时间就是看门狗的默认时间】,每隔10s都会自动再次续期，续成30s
        //    internalLockLeaseTime【看门狗时间】 / 3,10s  看门狗时间默认是30 相当于 10s 续期
```

```java
private <T> RFuture<Long> tryAcquireAsync(long leaseTime, TimeUnit unit, long threadId) {
    if (leaseTime != -1L) {
        return this.tryLockInnerAsync(leaseTime, unit, threadId, RedisCommands.EVAL_LONG);
    } else {
        RFuture<Long> ttlRemainingFuture = this.tryLockInnerAsync(this.commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout(), TimeUnit.MILLISECONDS, threadId, RedisCommands.EVAL_LONG);
        ttlRemainingFuture.onComplete((ttlRemaining, e) -> {
            if (e == null) {
                if (ttlRemaining == null) {
                    this.scheduleExpirationRenewal(threadId);
                }

            }
        });
        return ttlRemainingFuture;
    }
}
```

==如果我们没有指定就使用看门狗的默认时间，也是后面续期的时间==

==重新设置过期时间，==

```java
// 重新设置超时时间
private void scheduleExpirationRenewal(long threadId) {
        RedissonLock.ExpirationEntry entry = new RedissonLock.ExpirationEntry();
        RedissonLock.ExpirationEntry oldEntry = (RedissonLock.ExpirationEntry)EXPIRATION_RENEWAL_MAP.putIfAbsent(this.getEntryName(), entry);
        if (oldEntry != null) {
            oldEntry.addThreadId(threadId);
        } else {
          // 传入线程id
            entry.addThreadId(threadId);
            this.renewExpiration();
        }

    }
```



```java
private void renewExpiration() {
        RedissonLock.ExpirationEntry ee = (RedissonLock.ExpirationEntry)EXPIRATION_RENEWAL_MAP.get(this.getEntryName());
        if (ee != null) {
          // new 了一个定时任务  执行lua设置超时时间
          // 续期 多久执行=  看门狗时间 / 3  
            Timeout task = this.commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
                public void run(Timeout timeout) throws Exception {
                    RedissonLock.ExpirationEntry ent = (RedissonLock.ExpirationEntry)RedissonLock.EXPIRATION_RENEWAL_MAP.get(RedissonLock.this.getEntryName());
                    if (ent != null) {
                        Long threadId = ent.getFirstThreadId();
                        if (threadId != null) {
                            RFuture<Boolean> future = RedissonLock.this.renewExpirationAsync(threadId);
                            future.onComplete((res, e) -> {
                                if (e != null) {
                                    RedissonLock.log.error("Can't update lock " + RedissonLock.this.getName() + " expiration", e);
                                } else {
                                    if (res) {
                                        RedissonLock.this.renewExpiration();
                                    }

                                }
                            });
                        }
                    }
                }
            }, this.internalLockLeaseTime / 3L, TimeUnit.MILLISECONDS);
            ee.setTimeout(task);
        }
    }
```

如下图

自动续期



```java
  //2、加锁
        lock.lock();
        ...
        ....
         try{
            System.out.println("加锁成功，执行业务...{}"+Thread.currentThread().getId());
            Thread.sleep(30000);
        }catch (Exception e){

        }finally {
            //3、解锁   如果解锁代码没有运行，redisson会不会出现死锁
            System.out.println("释放锁...{}"+Thread.currentThread().getId());
            lock.unlock();
        }
```

![image-20210306000757655](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210306000757655.png)



#### redission--读写锁测试

1.



2.

提前放key

![image-20210306001732459](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210306001732459.png)

3.

![image-20210306002012925](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210306002012925.png)

#### 分布式闭锁-CDL

1.

相当于juc中的珊栏

2.

```java
 public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.trySetCount(5);
        door.await(); //等待闭锁都完成
		
        return "都到齐了。准备开门";
    }

    public String gogogo(@PathVariable("id") Long id){
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.countDown();//计数减一；

        return id+"人还没有对齐";
    }
```

#### 信号量

1.

和多线程中线程通信的信号量一样，

2.

```java
    
public String go() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
        park.release();//释放一个车位
//        Semaphore semaphore = new Semaphore(5);
//        semaphore.release(); 释放
//        semaphore.acquire();抢占
        return "ok";
    }
    
    
        /**
     * 车库停车，
     * 3车位 调用一次 -1  为0 就等待 释放
     * 信号量也可以用作分布式限流；
     */
    public String park() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
//        park.acquire();//获取一个信号，获取一个值,占一个车位，阻塞抢占
        boolean b = park.tryAcquire(); // 尝试抢占位置
        if(b){
            //执行业务
        }else {
            return "error";
        }
        return "ok=>"+b;
    }
```

### 缓存一致性

redis没有保证强一致性。

使用最终一致性

面试经常问的，深入问还是复杂的！

1.双写一致性

![image-20210306010402891](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210306010402891.png)

2.解决方案

```java
无论是双写模式还是失效模式，都会导致缓存的不一致问题。即多个实例同时更新会出事。怎么办？
• 1、如果是用户纬度数据（订单数据、用户数据），这种并发几率非常小，不用考虑这个问题，缓存数据加
上过期时间，每隔一段时间触发读的主动更新即可
• 2、如果是菜单，商品介绍等基础数据，也可以去使用canal订阅binlog的方式。
• 3、缓存数据+过期时间也足够解决大部分业务对于缓存的要求。
• 4、通过加锁保证并发读写，写写的时候按顺序排好队。读读无所谓。所以适合使用读写锁。（业务不关心
脏数据，允许临时脏数据可忽略）；
```



### spring-cache

https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache

spring cache 通过注解 简化 cache管理就开发

`CacheManager`  和 `Cache`

![image-20210306013659327](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210306013659327.png)

1.

```
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
```

2.

![image-20210306014244251](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210306014244251.png)

```
 @Cacheable: Triggers cache population.：触发将数据保存到缓存的操作
 *          @CacheEvict: Triggers cache eviction.：触发将数据从缓存删除的操作
 *          @CachePut: Updates the cache without interfering with the method execution.：不影响方法执行更新缓存
 *          @Caching: Regroups multiple cache operations to be applied on a method.：组合以上多个操作
 *          @CacheConfig: Shares some common cache-related settings at class-level.：在类级别共享缓存的相同配置
 *          1）、开启缓存功能 @EnableCaching
 *          2）、只需要使用注解就能完成缓存操作
```



3.@Cacheable的设置-读模式

https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-annotations-cacheable

默认设置：

自定义设置：



4.@CacheEvict

缓存失效模式



5.@Caching

组合多个缓存组合操作

```java
    @Caching(evict = {
            @CacheEvict(value = "category",key = "'getLevel1Categorys'"),
            @CacheEvict(value = "category",key = "'getCatalogJson'")
    })
    ....
```

value = "category" 指定操作的是 category分区

![image-20210306195230068](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210306195230068.png)

6.@CachePut

返回的结果放一放到缓存中



7.原理和不足

```java
Spring-Cache的不足；
          1）、读模式：
               缓存穿透：查询一个null数据。解决：缓存空数据；ache-null-values=true
               缓存击穿：大量并发进来同时查询一个正好过期的数据。解决：加锁；？默认是无加锁的;sync = true（加锁，解决击穿）
               缓存雪崩：大量的key同时过期。解决：加随机时间。加上过期时间。：spring.cache.redis.time-to-live=3600000
           2）、写模式：（缓存与数据库一致）
               1）、读写加锁。
               2）、引入Canal，感知到MySQL的更新去更新数据库
               3）、读多写多，直接去数据库查询就行
         总结：
          常规数据（读多写少，即时性，一致性要求不高的数据）；完全可以使用Spring-Cache；写模式（只要缓存的数据有过期时间就足够了）
          特殊数据：特殊设计
```







## 搭建客户端页面检索



**页面配置：**

1.nginx动静分离

静态资源放nginx 中

2.修改nginx 配置文件

search.viwmall.com 转 nginx 转 网关 找真实服务

![image-20210306202349463](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210306202349463.png)

3.配置网关gateway

**设置页面路由跳转：**

1.

**设置搜索参数模型VO抽取**

1.分析用户在首页会输入哪些参数来检索商品

2.检索的条件模型	

```
检索条件&排序条件
 全文检索：skuTitle
 排序：saleCount、hotScore、skuPrice
 过滤：hasStock、skuPrice 区间、brandId、catalogId、attrs
 聚合：attrs
完整的 url 参数
keyword=小米&sort=saleCount_desc/asc&hasStock=0/1&skuPrice=400_1900&brandId=1
&catalogId=1&attrs=1_3G:4G:5G&attrs=2_骁龙 845&attrs=4_高清屏
```

3.抽取检索结果模型



4.编写ES中去查询的DSL

就是前面ES中的 编写的查询DSL语句，相当于各种sql，

根据用户在页面输入的各种检索参数 动态去查询各种商品检索结果

==注意nested类型的list数据查询，需要用==

​	https://www.elastic.co/guide/en/elasticsearch/reference/7.9/query-dsl-nested-query.html

==搜索词highlight高亮==

==总结：目前需要 模糊匹配，过滤（按照属性，分类，匹配，价格区间，库存），排序，分页，高亮，聚合分析==

5.聚合分析

![image-20210307075748662](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307075748662.png)



netsted嵌入式的聚合

![image-20210307080057270](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307080057270.png)



6.测试检索ES商品 编写的DSL



7.测试检索ES商品  需要对检索结果的数据封装



8.测试对检索结果的VO数据封装的正确性

将响应数据封装成我们前端要的格式

9.页面渲染和检索商品查询条件动态渲染

10.导航页面渲染和分页渲染

11.页面排序

12.页面排序字段回显

13.页面价格区间检索

14.面包屑导航功能

![image-20210307104614163](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307104614163.png)



15.条件删除(取消面包屑)和URL编码问题

16.页面条件筛选级联



## 异步线程池

### thread&threadpool

1.线程

```java
方式 1 和方式 2：主进程无法获取线程的运算结果。不适合当前场景
方式 3：主进程可以获取线程的运算结果，但是不利于控制服务器中的线程资源。可以导致
服务器资源耗尽。
方式 4：通过如下两种方式初始化线程池
Executors.newFiexedThreadPool(3);
//或者
new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit unit, workQueue, threadFactory, handler);
```

2.线程池

```
Executors.newFiexedThreadPool(3);
```



```java
  /**
         * 七大参数
         * corePoolSize:[5] 核心线程数[一直存在除非（allowCoreThreadTimeOut）]; 线程池，创建好以后就准备就绪的线程数量，就等待来接受异步任务去执行。
         *        5个  Thread thread = new Thread();  thread.start();
         * maximumPoolSize:[200] 最大线程数量;  控制资源
         * keepAliveTime:存活时间。如果当前的线程数量大于core数量。
         *      释放空闲的线程（maximumPoolSize-corePoolSize）。只要线程空闲大于指定的keepAliveTime；
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

public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              RejectedExecutionHandler handler) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), handler);
    }new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit unit, workQueue, threadFactory, handler);
```



### CompletableFuture异步编排

#### 1.创建异步对象 启动任务

![image-20210307160058774](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307160058774.png)



runAsync:

```java
// 
public static ExecutorService service = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        System.out.println("main-start");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("ok");
        }, service);
        System.out.println("main-end");
    }
//
main-start
main-end
ok
```

supplyAsync:

```java
   public static ExecutorService service = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main-start");
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println("ok");
//        }, service);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("OK");
            return "value";
        }, service);
        System.out.println("main-end");
        System.out.println(future.get());
    }
```



#### 2.回调和异常感知



![image-20210307160948295](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307160948295.png)

whenComplete 和exceptionally 

whenComplete 可以处理正常和异常的计算结果，exceptionally 处理异常情况。 whenComplete 和 whenCompleteAsync 的区别： whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。 whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池 来进行执行。



==’方法不以 Async 结尾，意味着 Action 使用相同的线程执行，而 Async 可能会使用其他线程 执行（如果是使用相同的线程池，也可能会被同一个线程选中执行）==

```java
  public static ExecutorService service = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main-start");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("OK");
            return "value";
        }, service).whenComplete((result,exception)->{
            System.out.println("成功完成。执行结果是"+result+"。异常是:"+exception);
        });
        System.out.println("main-end");
        System.out.println(future.get());
    }

//
main-start
OK
成功完成。执行结果是value。异常是:null
main-end
value
```

获取异常结果：

```
 public static ExecutorService service = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main-start");
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println("ok");
//        }, service);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("OK");
            return "value";
        }, service).whenComplete((result,exception)->{
            //没法修改返回数据
            System.out.println("成功完成。执行结果是"+result+"。异常是:"+exception);
        }).exceptionally(throwable -> {
            //出现异常 执行下面异常处理，可以感知异常并处理异常
            String message = throwable.getMessage();
            // 返回结果，可以是我们的业务执行默认处理结果 比如
            return message;
        });
        System.out.println("main-end");
        System.out.println(future.get());
    }
}

```



#### 3.handle最终处理

![image-20210307162526238](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307162526238.png)

1. handle可以处理方法执行完成的后续处理，改变返回值

```java
  CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("OK");
            String s = "结果";
            return s;
        }, service).handle((result, exception) -> {
            if (exception != null) {
                return "异常，业务处理";
            }
            if (result !=null) {
                return "没异常业务处理："+result;
            }
            return result;
        });
        System.out.println(future.get());
        System.out.println("main-end");

//
main-start
OK
没异常业务处理：结果
main-end

```

#### 4.线程串行化

1.

![image-20210307162547647](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307162547647.png)

```java
 /**
         * 线程串行化
         * 1）、thenRun：不能获取到上一步的执行结果，无返回值
         *  .thenRunAsync(() -> {
         *             System.out.println("任务2启动了...");
         *         }, executor);
         * 2）、thenAcceptAsync;能接受上一步结果，但是无返回值
         * 3）、thenApplyAsync：;能接受上一步结果，有返回值
         */
```

```java
thenApply 方法：当一个线程依赖另一个线程时，获取上一个任务返回的结果，并返回当前
任务的返回值。
thenAccept 方法：感知消费处理结果。接收任务的处理结果，并消费处理，无返回结果。
thenRun 方法：只要上面的任务执行完成，就开始执行 thenRun，只是处理完任务后，执行
thenRun 的后续操作
带有 Async 默认是异步执行的。开启新线程，不加的是 和之前同一个线程
```

thenrun:

```java
    // 1有结果  但2没结果
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            // 任务1
            System.out.println("OK");
            String s = "1结果";
            return s;
        }, service).thenRunAsync(()->{
            // 任务2
            System.out.println("2执行");
        },service);
        System.out.println("main-end"+future2.get());
    }
//
main-start
OK
2执行
main-endnull
  
  //感知上一个结果，但是无返回
   CompletableFuture.supplyAsync(() -> {
            // 任务1
            System.out.println("OK");
            String s = "1结果";
            return s;
        }, service).thenAcceptAsync(res->{
            // 任务2
            System.out.println("2执行"+res);
        },service);


// 感知 上一个。并有返回值
  CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 任务1
            System.out.println("OK");
            String s = "1结果";
            return s;
        }, service).thenApplyAsync(res -> {
            // 任务2
            System.out.println("2执行。上一个结果：" + res);
            return "任务2的返回值";
        }, service);
        System.out.println(future.get());
//
main-start
OK
2执行。上一个结果：1结果
任务2的返回值
main-end
```



#### 5.两任务组合

1.

![image-20210307163808553](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307163808553.png)

![image-20210307163819142](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307163819142.png)

```
thenCombine：组合两个 future，获取两个 future 的返回结果，并返回当前任务的返回值
thenAcceptBoth：组合两个 future，获取两个 future 任务的返回结果，然后处理任务，没有
返回值。
runAfterBoth：组合两个 future，不需要获取 future 的结果，只需两个 future 处理完任务后，
处理该任务。
```

```java
        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务1结束：" );
            return i;
        }, executor);

        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());

            try {
                Thread.sleep(3000);
                System.out.println("任务2结束：" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }, executor);
             任务12完成后执行 runAfterBothAsync不能感知前面结果
        future01.runAfterBothAsync(future02,()->{
            System.out.println("任务3开始...");
        },executor);
         void accept(T t, U u); thenAcceptBothAsync感知前面结果
        future01.thenAcceptBothAsync(future02,(f1,f2)->{
         可以获取前面1 2的结果
            System.out.println("任务3开始...之前的结果："+f1+"--》"+f2);
        },executor);
        R apply(T t, U u);
         thenCombineAsync合并任务 并感知获取前面结果
        CompletableFuture<String> future = future01.thenCombineAsync(future02, (f1, f2) -> {
            return f1 + "：" + f2 + " -> Haha";
        }, executor);
```

#### 一个完成(A,B,C  A或B完成一个执行C)



```java
        /**
         * 两个任务，只要有一个完成，我们就执行任务3
         * runAfterEitherAsync：不感知结果，自己没有返回值
         * acceptEitherAsync：感知结果，自己没有返回值
         * applyToEitherAsync：感知结果，自己有返回值
         */
        future01.runAfterEitherAsync(future02,()->{
            System.out.println("任务3开始...之前的结果：");
        },service);
//        void accept(T t);
        future01.acceptEitherAsync(future02,(res)->{
            System.out.println("任务3开始...之前的结果："+res);
        },service);
        CompletableFuture<String> future = future01.applyToEitherAsync(future02, res -> {
            System.out.println("任务3开始...之前的结果：" + res);
            return res.toString() + "->哈哈";
        }, service);

//
任务1线程：12
任务1结束：
任务2线程：13
任务3开始...之前的结果：
任务3开始...之前的结果：2
main-end
任务3开始...之前的结果：2
任务2结束：
```

#### 7.多任务组合

1.

![image-20210307172021768](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307172021768.png)

2，

```
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
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
        // 其中一个完成
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        anyOf.get();
```



## 商品详情

**详情数据**

**查询详情**

**sku组合切换**

1.修改nginx

2.修改网关

3.controller

4.商品详情模型抽取

5.封装显示属性组合

6.详情页渲染

7.销售销售渲染

不同sku组合 切换不同的sku

8.商品销售--sku组合切换

==9.异步编排优化==



## 认证授权(Oauth2+JWT)



### 用户登录注册

**环境搭建**

1.

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210307223442789.png" alt="image-20210307223442789" style="zoom:50%;" />

2.

网关配置和nginx动静分离



**注册功能和短信验证码：**

1.

阿里云短信服务

2.

验证码接口防刷

3.用户注册：

JSR303用户注册数据校验

注册VO模型抽取

4.异常机制



5.MD5&MD5盐值加密



```java
   // 利用spring 的密码加密工具， 加密解密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //$2a$10$4IP4F/2iFO2gbSvQKyJzGuI3RhU5Qdtr519KsyoXGAy.b7WT4P1RW
        //$2a$10$iv6H6nqQ/NWOMkzgZSJdPeMOBGbn0ayhZ9WAewOk0ssWScSHOgsAW
        String encode = passwordEncoder.encode("123456");

        boolean matches = passwordEncoder.matches("123456", "$2a$10$4IP4F/2iFO2gbSvQKyJzGuI3RhU5Qdtr519KsyoXGAy.b7WT4P1RW");

```

6.注册完成



7.登录模块



### Oauth2&社交登录



**Oauth2:**

1.流程

![image-20210308231336511](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210308231336511.png)



### 微博登录

https://open.weibo.com/wiki/%E6%8E%88%E6%9D%83%E6%9C%BA%E5%88%B6

1.

![image-20210308232322701](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210308232322701.png)

2.

需要开发者信息和认证

3.

。。。

4.可以通过accesskey调用微博接口获取用户信息

![image-20210308233840085](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210308233840085.png)



==引导用户微博登录获取code，通过code调用微博api获取accesskey，通过accesskey通过微博开发的api获取用户信息==

5.社交登录回调

自己处理微博授权后得到code后的业务逻辑，如通过code获取accesskey

==通过code获取授权码==

### 分布式session

1.原生session共享

![image-20210309002905467](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309002905467.png)



2.分布式session问题

![image-20210309003056512](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309003056512.png)



3.分布式session解决

方案1

![image-20210309003704082](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309003704082.png)

方案2

springsession解决

![image-20210309004312903](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309004312903.png)

### springsession解决分布式session和子域共享

https://docs.spring.io/spring-session/docs/2.3.2.RELEASE/reference/html5/

1.依赖

```
 <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>

```

2.配置文件

```
spring.session.store-type=redis
```

3.启动类

`@EnableRedisHttpSession`

需要取出的工程  在启动类上 `@EnableRedisHttpSession`

4.

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309005008595.png" alt="image-20210309005008595" style="zoom:50%;" />

5.自定义springsession

https://docs.spring.io/spring-session/docs/2.3.2.RELEASE/reference/html5/#api-cookieserializer-bean

### springsession原理

```
/**
 * SpringSession 核心原理
 * 1)、@EnableRedisHttpSession导入RedisHttpSessionConfiguration配置
 *      1、给容器中添加了一个组件
 *          SessionRepository = 》》》【RedisOperationsSessionRepository】==》redis操作session。session的增删改查封装类
 *      2、SessionRepositoryFilter == 》Filter： session'存储过滤器；每个请求过来都必须经过filter
 *          1、创建的时候，就自动从容器中获取到了SessionRepository；
 *          2、原始的request，response都被包装。SessionRepositoryRequestWrapper，SessionRepositoryResponseWrapper
 *          3、以后获取session。request.getSession();
 *          //SessionRepositoryRequestWrapper
 *          4、wrappedRequest.getSession();===> SessionRepository 中获取到的。
 *
 *   装饰者模式；
 *
 *  自动延期；redis中的数据也是有过期时间。
 *
 *
 *
 *
 */
```

![image-20210309013751893](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309013751893.png)



### 单点登录

1.

开源服务端

https://gitee.com/xuxueli0323/xxl-sso?_from=gitee_search

2.流程

![image-20210309224410439](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309224410439.png)



3.

https://gitee.com/xiaobo97/simple-viw-web/blob/master/edupaas_perent/%E9%A1%B9%E7%9B%AE0-1%E8%AF%A6%E7%BB%86%E6%96%87%E6%A1%A3.md#%E5%8D%95%E7%82%B9%E7%99%BB%E5%BD%95



### JWT

1.



2.步骤

```java
步骤：
- 1、用户登录
- 2、服务的认证，通过后根据 secret 生成 token
- 3、将生成的 token 返回给浏览器
- 4、用户每次请求携带 token
- 5、服务端利用秘钥解读 jwt 签名，判断签名有效后，从 Payload 中获取用户信息
- 6、处理请求，返回响应结果
因为 JWT 签发的 token 中已经包含了用户的身份信息，并且每次请求都会携带，这样服务的
就无需保存用户信息，甚至无需去数据库查询，完全符合了 Rest 的无状态规范。
```

3.授权流程

![image-20210309235941256](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309235941256.png)

4.问题

```java
 我们不建议使用 jwt+cookie 代替 session+cookie 机制，jwt 更适合 restful api
 jwt token 泄露了怎么办？
 这个问题可以不考虑，因为 session+cookie 同样泄露了 cookie 的 jsessionid 也会有
这个问题
 我们可以遵循以下规范减少风险
 使用 https 加密应用
 返 回 jwt 给 客 户 端 时 设 置 httpOnly=true 并 且 使 用 cookie 而 不 是
LocalStorage 存储 jwt，防止 XSS 攻击和 CSRF 攻击
 secret 如果泄露会导致大面积风险
 定期更新
 Secret 设计可以和用户关联起来，每个用户不一样。防止全用一个 secret
 注销和修改密码
 传统的 session+cookie 方案用户点击注销，服务端清空 session 即可，因为状态
保存在服务端。我们不害怕注销后的假登录
 Jwt 会有问题。用户如果注销了或者修改密码了。恶意用户还使用之前非法盗取来
的 token，可以在不重新登录的情况下继续使用
 可以按程度使用如下设计，减少一定的风险
 清空客户端的 cookie，这样用户访问时就不会携带 jwt，服务端就认为用
户需要重新登录。这是一个典型的假注销，对于用户表现出退出的行为，
实际上这个时候携带对应的 jwt 依旧可以访问系统。
 清空或修改服务端的用户对应的 secret，这样在用户注销后，jwt 本身不
变，但是由于 secret 不存在或改变，则无法完成校验。这也是为什么将
secret 设计成和用户相关的原因
 借助第三方存储，管理 jwt 的状态，可以以 jwt 为 key，去 redis 校验
存在性。但这样，就把无状态的 jwt 硬生生变成了有状态了，违背了 jwt
的初衷。实际上这个方案和 session 都差不多了。
 修改密码则略微有些不同，假设号被到了，修改密码（是用户密码，不是
jwt 的 secret）之后，盗号者在原 jwt 有效期之内依旧可以继续访问系
统，所以仅仅清空 cookie 自然是不够的，这时，需要强制性的修改 secret
 续签问题
 传统的 cookie 续签方案一般都是框架自带的，session 有效期 30 分钟，30
分钟内如果有访问，session 有效期被刷新至 30 分钟。而 jwt 本身的 payload
之中也有一个 exp 过期时间参数，来代表一个 jwt 的时效性，而 jwt 想延期
这个 exp 就有点身不由己了，因为 payload 是参与签名的，一旦过期时间被
修改，整个 jwt 串就变了，jwt 的特性天然不支持续签！
 可如下解决，但都不是完美方案
 每次请求刷新 jwt：简单暴力，性能低下，浪费资源。
 只要快要过期的时候刷新 jwt：jwt 最后的几分钟，换新一下。但是如果
用户连续操作了 27 分钟，只有最后的 3 分钟没有操作，导致未刷新
jwt，就很难受。
 完 善 refreshToken ： 借 鉴 oauth2 的 设 计 ， 返 回 给 客 户 端 一 个
refreshToken，允许客户端主动刷新 jwt。这样做，还不如用 oauth2
 使用 redis 记录独立的过期时间:jwt 作为 key，在 redis 中保存过期时间，
每次使用在 redis 中续期，如果 redis 没有就认为过期。但是这样做，还不
如用 session+cookie
```



## 购物车模块



**基础环境：**

1.

![image-20210309231047209](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210309231047209.png)

**购物车需求分析:**

```java
- 用户可以在登录状态下将商品添加到购物车【用户购物车/在线购物车】
- 放入数据库
- mongodb
- 放入 redis（采用）
登录以后，会将临时购物车的数据全部合并过来，并清空临时购物车；
- 用户可以在未登录状态下将商品添加到购物车【游客购物车/离线购物车/临时购物车】
- 放入 localstorage（客户端存储，后台不存）
- cookie
- WebSQL
- 放入 redis（采用）
浏览器即使关闭，下次进入，临时购物车数据都在
```



**购物车数据模型抽取：**

1.

```
的购物车结构是一个双层 Map：Map<String,Map<String,String>>
- 第一层 Map，Key 是用户 id
- 第二层 Map，Key 是购物车中商品 id，值是购物项数据
```

2.



**购物车ThreadLocal身份标识**

mvc拦截器+cookie存放user-key，判断用户是否登录，是否是临时用户

**页面环境搭建：**



**添加购物车：**



**合并和获取购物车：**



**选中购物车：**



## MQ

### 搭建

https://gitee.com/xiaobo97/viv-notes/blob/master/%E5%90%8E%E7%AB%AF%E7%9F%A5%E8%AF%86/%E6%B6%88%E6%81%AF%E9%98%9F%E5%88%97/RabbitMQ/RabbitMQ01-%E5%9F%BA%E7%A1%80.md



1.spring支持

```java
Spring支持
• spring-jms提供了对JMS的支持
• spring-rabbit提供了对AMQP的支持
• 需要ConnectionFactory的实现来连接消息代理
• 提供JmsTemplate、RabbitTemplate来发送消息
• @JmsListener（JMS）、@RabbitListener（AMQP）注解在方法上监听消息
代理发布的消息
• @EnableJms、@EnableRabbit开启支持
Spring Boot自动配置
• JmsAutoConfiguration
• RabbitAutoConfiguration
```



2.rabbitmq工作流程和概念

![image-20210310224421870](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210310224421870.png)



3.docker按照rabbitmq

```
docker pull rabbitmq:3.8.9-management
```

```
docker run -d --name mallrabbitmq1 -p 5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15671:15671 -p 15672:15672 rabbitmq:3.8.9-management
```



4369, 25672 (Erlang发现&集群端口) 5672, 5671 (AMQP端口) 15672 (web管理后台端口) 61613, 61614 (STOMP协议端口) 1883, 8883 (MQTT协议端口)

访问 15672端口



### boot+rabbitmq



1.

```
1. 引入 spring-boot-starter-amqp
2. application.yml配置
3. 测试RabbitMQ
1. AmqpAdmin：管理组件
2. RabbitTemplate：消息发送处理组件
3. @RabbitListener 监听消息的方法可以有三种参数（不分数量，顺序）
• Object content, Message message, Channel channel
```



2.依赖和配置文件

```
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>

```



3.测试

创建交换机，创建队列，绑定队列，发送消息，获取信息，对象序列化和反序列化



### rabbitmqlistener&rabbithandler

1.监听消息

@RabbitListener: 类+方法上（监听哪些队列即可）

2.区分处理不同消息

@RabbitHandler：标在方法上（重载区分不同的消息）

处理不同的对象的消息

![image-20210310235445056](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210310235445056.png)



### rabbitmq消息可靠性

1.

![image-20210311002750826](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210311002750826.png)

![image-20210311003602255](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210311003602255.png)

==P->B==

```
spring.rabbitmq.publisher-confirms=true
• 在创建 connectionFactory 的时候设置 PublisherConfirms(true) 选项，开启
confirmcallback 。
• CorrelationData：用来表示当前消息唯一性。
• 消息只要被 broker 接收到就会执行 confirmCallback，如果是 cluster 模式，需要所有
broker 接收到才会调用 confirmCallback。
• 被 broker 接收到只能表示 message 已经到达服务器，并不能保证消息一定会被投递
到目标 queue 里。所以需要用到接下来的 returnCallback 。
```

==E->Q==

```
spring.rabbitmq.publisher-returns=true
• spring.rabbitmq.template.mandatory=true
• confrim 模式只能保证消息到达 broker，不能保证消息准确投递到目标 queue 里。在有
些业务场景下，我们需要保证消息一定要投递到目标 queue 里，此时就需要用到
return 退回模式。
• 这样如果未能投递到目标 queue 里将调用 returnCallback ，可以记录下详细到投递数
据，定期的巡检或者自动纠错都需要这些数据。
```

==Q->C==

```
•消费者获取到消息，成功处理，可以回复Ack给Broker
•basic.ack用于肯定确认；broker将移除此消息
•basic.nack用于否定确认；可以指定broker是否丢弃此消息，可以批量
•basic.reject用于否定确认；同上，但不能批量
•默认自动ack，消息被消费者收到，就会从broker的queue中移除
•queue无消费者，消息依然会被存储，直到消费者消费
•消费者收到消息，默认会自动ack。但是如果无法确定此消息是否被处理完成，
或者成功处理。我们可以开启手动ack模式
•消息处理成功，ack()，接受下一个消息，此消息broker就会移除
•消息处理失败，nack()/reject()，重新发送给其他人进行处理，或者容错处理后ack
•消息一直没有调用ack/nack方法，broker认为此消息正在被处理，不会投递给别人，此时客户
端断开，消息不会被broker移除，会投递给别人
```





2.定制rabbitmq

```
 /**
     * 定制RabbitTemplate
     * 1、服务器收到消息就回调
     *      1、spring.rabbitmq.publisher-confirms=true
     *      2、设置确认回调ConfirmCallback
     * 2、消息正确抵达队列进行回调
     *      1、 spring.rabbitmq.publisher-returns=true
     *          spring.rabbitmq.template.mandatory=true
     *      2、设置确认回调ReturnCallback
   
```



```
# 开启发送端确认
spring.rabbitmq.publisher-confirms=true
# 返回端确认
spring.rabbitmq.publisher-returns=true
# 消息到队列  异步执行优先回调returns
spring.rabbitmq.template.mandatory=true
```

编写回调函数

```java
  //设置消息确认回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            /**
             *
             * 1、只要消息抵达Broker就ack=true
             * @param correlationData 当前消息的唯一关联数据（这个是消息的唯一id）
             * @param ack  消息是否成功收到
             * @param cause 失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

                /**
                 * 1、做好消息确认机制（pulisher，consumer【手动ack】）
                 * 2、每一个发送的消息都在数据库做好记录。定期将失败的消息再次发送一遍
                 */
                //服务器收到了；
                //修改消息的状态
                System.out.println("confirm...correlationData["+correlationData+"]==>ack["+ack+"]==>cause["+cause+"]");
            }
        });
        
        
          //设置消息抵达队列的确认回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 只要消息没有投递给指定的队列，就触发这个失败回调
             * @param message   投递失败的消息详细信息
             * @param replyCode 回复的状态码
             * @param replyText 回复的文本内容
             * @param exchange  当时这个消息发给哪个交换机
             * @param routingKey 当时这个消息用哪个路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                //报错误了。修改数据库当前消息的状态->错误。
                System.out.println("Fail Message["+message+"]==>replyCode["+replyCode+"]==>replyText["+replyText+"]===>exchange["+exchange+"]===>routingKey["+routingKey+"]");
            }
        });
```



### 消费端



1.

```
  *
     * 3、消费端确认（保证每个消息被正确消费，此时才可以broker删除这个消息）。
     *      spring.rabbitmq.listener.simple.acknowledge-mode=manual 手动签收
     *      1、默认是自动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息
     *          问题：
     *              我们收到很多消息，自动回复给服务器ack，只有一个消息处理成功，宕机了。就会发生消息丢失；
     *              消费者手动确认模式。只要我们没有明确告诉MQ，货物被签收。没有Ack，
     *                  消息就一直是unacked状态。即使Consumer宕机。消息不会丢失，会重新变为Ready，下一次有新的Consumer连接进来就发给他
     *      2、如何签收:
     *          channel.basicAck(deliveryTag,false);签收；业务成功完成就应该签收
     *          channel.basicNack(deliveryTag,false,true);拒签；业务失败，拒签
     */
```



```
# 设置ack手动确认（不手动确认的消息不算消费成功）
spring.rabbitmq.listener.simple.acknowledge-mode=manual
```

2.消息ack确认签收和拒绝签收







## 订单模块



### 搭建

1.页面环境

nginx静态资源，网关，页面，



### springsession搭建session共享



### 订单

1.订单流程

![image-20210312224845004](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210312224845004.png)



2.订单跳转登录拦截

编写拦截器

编写webmvc配置类，让拦截器起作用

==点击跳转订单请求 如果用户没有登录就拦截请求 重定向让用户去登录，如果在请求中获取到了用户的登录信息说明用户登录了==



3.订单确认页模型抽取

![image-20210313003607786](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313003607786.png)

![image-20210313003618363](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313003618363.png)

```
可以发现订单结算页，包含以下信息：
1. 收货人信息：有更多地址，即有多个收货地址，其中有一个默认收货地址
2. 支付方式：货到付款、在线支付，不需要后台提供
3. 送货清单：配送方式及商品列表（根据购物车选中的 skuId 到数据库中查询）
4. 优惠：查询用户领取的优惠券及可用积分
```



4.订单确认页面数据获取

相关远程调用代码



5.Feign远程调用丢失请求头问题



![image-20210313015656424](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313015656424.png)

==解决办法：拦截器请求增强远程调用==

```java
@Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor(){
            /**
             *
             * @param template 真正发送的请求
             */
            @Override
            public void apply(RequestTemplate template) {
                System.out.println("feign远程调用之前先执行apply方法");
                /**
                 * 1、RequestContextHolder拿到刚进来的这个请求   当前toTrade中
                 * 的HttpServletRequest请求的所有属性 {@link com.viw.viwmall.order.web.OrderWebController#toTrade(Model, HttpServletRequest)}
                 */
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if(attributes!=null){
                    System.out.println("RequestInterceptor线程...."+Thread.currentThread().getId());
                    HttpServletRequest request = attributes.getRequest(); //老请求
                    if(request != null){
                        //同步请求头数据，Cookie 因为远程服务丢失了cookie无法得到其实用户已经登录的cookie
                        String cookie = request.getHeader("Cookie");
                        //给新请求同步了老请求的cookie
                        template.header("Cookie",cookie);
                    }
                }
            }
        };
    }
```







异步编排feign丢失上下文（不是同一个线程的threadLocal）

![image-20210313122241175](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313122241175.png)

![image-20210313015603556](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313015603556.png)



6.订单确认页渲染



7.订单系统调用库存系统查询运费



### 接口幂等性



1.幂等性:

接口幂等性就是用户对于同一操作发起的一次请求或者多次请求的结果是一致的，不会因 为多次点击而产生了副作用；

2.

```java
以 SQL 为例，有些操作是天然幂等的。
SELECT * FROM table WHER id=?，无论执行多少次都不会改变状态，是天然的幂等。
UPDATE tab1 SET col1=1 WHERE col2=2，无论执行成功多少次状态都是一致的，也是幂等操作。
delete from user where userid=1，多次操作，结果一样，具备幂等性
insert into user(userid,name) values(1,'a') 如 userid 为唯一主键，即重复操作上面的业务，只
会插入一条用户数据，具备幂等性。
UPDATE tab1 SET col1=col1+1 WHERE col2=2，每次执行的结果都会发生变化，不是幂等的。
insert into user(userid,name) values(1,'a') 如 userid 不是主键，可以重复，那上面业务多次操
作，数据都会新增多条，不具备幂等性。
```

#### 解决方案

**1.token机制**

```
1、服务端提供了发送 token 的接口。我们在分析业务的时候，哪些业务是存在幂等问题的，
就必须在执行业务前，先去获取 token，服务器会把 token 保存到 redis 中。
2、然后调用业务接口请求时，把 token 携带过去，一般放在请求头部。
3、服务器判断 token 是否存在 redis 中，存在表示第一次请求，然后删除 token,继续执行业
务。
4、如果判断 token 不存在 redis 中，就表示是重复操作，直接返回重复标记给 client，这样
就保证了业务代码，不被重复执行。
```

问题：

```
危险性：
1、先删除 token 还是后删除 token；
(1) 先删除可能导致，业务确实没有执行，重试还带上之前 token，由于防重设计导致，
请求还是不能执行。
(2) 后删除可能导致，业务处理成功，但是服务闪断，出现超时，没有删除 token，别
人继续重试，导致业务被执行两边
(3) 我们最好设计为先删除 token，如果业务调用失败，就重新获取 token 再次请求。
2、Token 获取、比较和删除必须是原子性
(1) redis.get(token) 、token.equals、redis.del(token)如果这两个操作不是原子，可能导
致，高并发下，都 get 到同样的数据，判断都成功，继续业务并发执行
(2) 可以在 redis 使用 lua 脚本完成这个操作
if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end
```

**2.锁**

数据库悲观锁和乐观锁，还有业务层的分布式锁

3.**数据库唯一约束**

- 数据库唯一约束

  ```
  插入数据，应该按照唯一索引进行插入，比如订单号，相同的订单就不可能有两条记录插入。
  我们在数据库层面防止重复。
  这个机制是利用了数据库的主键唯一约束的特性，解决了在 insert 场景时幂等问题。但主键
  的要求不是自增的主键，这样就需要业务生成全局唯一的主键。
  如果是分库分表场景下，路由规则要保证相同请求下，落地在同一个数据库和同一表中，要
  不然数据库主键约束就不起效果了，因为是不同的数据库和表主键不相关。
  ```

  

- redis set 防重

  ```
  很多数据需要处理，只能被处理一次，比如我们可以计算数据的 MD5 将其放入 redis 的 set，
  每次处理数据，先看这个 MD5 是否已经存在，存在就不处理。
  ```



**4.防重表**

```
使用订单号 orderNo 做为去重表的唯一索引，把唯一索引插入去重表，再进行业务操作，且
他们在同一个事务中。这个保证了重复请求时，因为去重表有唯一约束，导致请求失败，避
免了幂等问题。这里要注意的是，去重表和业务表应该在同一库中，这样就保证了在同一个
事务，即使业务操作失败了，也会把去重表的数据回滚。这个很好的保证了数据一致性。
```



5.**全局请求唯一 id**

```
调用接口时，生成一个唯一 id，redis 将数据保存到集合中（去重），存在即处理过
```

如feign每次远程调用带着唯一id

### 通过令牌解决订单确认页接口幂等

1.订单流程

![image-20210313142117509](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313142117509.png)



```java
			 xxxxxx
        //TODO 5、防重令牌  接口幂等
        String token = UUID.randomUUID().toString().replace("-", "");
        // 服务器存一个令牌
        redisTemplate.opsForValue().set(OrderConstant.USER_ORDER_TOKEN_PREFIX + memberRespVo.getId(), token, 30, TimeUnit.MINUTES);
        //给页面返回一个令牌
        confirmVo.setOrderToken(token);
```



2.下单操作 原子验令牌

lua脚本

3.构建订单

4.根据订单号构建订单项

5.订单验价

6.保存订单数据

7.远程锁库存

==失败回滚==





## 分布式事务



![image-20210313215645101](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313215645101.png)



### 本地事务

1.本地事务在分布式事务出现的问题

```
的单体应用中，我们多个业务操作使用同一条连接操作不同的数据表，一旦有异常，
我们可以很容易的整体回滚；
```

![image-20210313220456457](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313220456457.png)

==不是同一个连接，操作的都不是同一个数据库，下面出现异常，上面远程调用无法感知==

2.ACID和mysql事务隔离级别



3.事务传播

```
1、PROPAGATION_REQUIRED：如果当前没有事务，就创建一个新事务，如果当前存在事务，
就加入该事务，该设置是最常用的设置。
2、PROPAGATION_SUPPORTS：支持当前事务，如果当前存在事务，就加入该事务，如果当
前不存在事务，就以非事务执行。
3、PROPAGATION_MANDATORY：支持当前事务，如果当前存在事务，就加入该事务，如果
当前不存在事务，就抛出异常。
4、PROPAGATION_REQUIRES_NEW：创建新事务，无论当前存不存在事务，都创建新事务。
5、PROPAGATION_NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当
前事务挂起。
6、PROPAGATION_NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。
7、PROPAGATION_NESTED：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，
则执行与 PROPAGATION_REQUIRED 类似的操作。
```

4.springboot事务问题

```java
 * 本地事务失效问题
 * 同一个对象内事务方法互调默认失效，原因 绕过了代理对象，事务使用代理对象来控制的
 * 解决：使用代理对象来调用事务方法
 *   1）、引入aop-starter;spring-boot-starter-aop；引入了aspectj
 *   2）、@EnableAspectJAutoProxy(exposeProxy = true)；开启 aspectj 动态代理功能。以后所有的动态代理都是aspectj创建的（即使没有接口也可以创建动态代理）。
 *          对外暴露代理对象
 *   3）、本类互调用调用对象
 *      OrderServiceImpl orderService = (OrderServiceImpl) AopContext.currentProxy();
 *          orderService.b();
 *          orderService.c();
```



```
在同一个类里面，编写两个方法，内部调用的时候，会导致事务设置失效。原因是没有用到
代理对象的缘故。
解决：
0）、导入 spring-boot-starter-aop
1）、@EnableTransactionManagement(proxyTargetClass = true)
2）、@EnableAspectJAutoProxy(exposeProxy=true)
3）、AopContext.currentProxy() 调用方法
```



proxyTargetClass = true 对外暴露代理对象



### 为什么有分布式事务

分布式系统经常出现的异常 机器宕机、网络异常、消息丢失、消息乱序、数据错误、不可靠的 TCP、存储数据丢失...

### CAP和BASE



```
CAP 原则又称 CAP 定理，指的是在一个分布式系统中
 一致性（Consistency）：
 在分布式系统中的所有数据备份，在同一时刻是否同样的值。（等同于所有节点访
问同一份最新的数据副本）
 可用性（Availability）
 在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。（对数据
更新具备高可用性）
 分区容错性（Partition tolerance）
 大多数分布式系统都分布在多个子网络。每个子网络就叫做一个区（partition）。
分区容错的意思是，区间通信可能失败。比如，一台服务器放在中国，另一台服务
器放在美国，这就是两个区，它们之间可能无法通信。
CAP 原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾。
```

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313222105448.png" alt="image-20210313222105448" style="zoom:50%;" />

​	

```
一般来说，分区容错无法避免，因此可以认为 CAP 的 P 总是成立。CAP 定理告诉我们，
剩下的 C 和 A 无法同时做到。
```

#### raft算法&paxos算法



1.raft（CP）

http://thesecretlivesofdata.com/raft/

https://github.com/raft/raft.github.io

如何一个node都有 一个身份， 随从，领导，候选人

一致性通过 选举领导+复制日志

两个超时时间：一个心跳一个选举时间

领导选举：

复制日志	

2.paxos

#### raft问题

对于多数大型互联网应用的场景，主机众多、部署分散，而且现在的集群规模越来越大，所 以节点故障、网络故障是常态，而且要保证服务可用性达到 99.99999%（N 个 9），即保证 P 和 A，舍弃 C。



#### base

base是对 CAP 理论的延伸，思想是即使无法做到强一致性（CAP 的一致性就是强一致性），但可 以采用适当的采取弱一致性，即最终一致性。

```
BASE 是指
 基本可用（Basically Available）
 基本可用是指分布式系统在出现故障的时候，允许损失部分可用性（例如响应时间、
功能上的可用性），允许损失部分可用性。需要注意的是，基本可用绝不等价于系
统不可用。
 响应时间上的损失：正常情况下搜索引擎需要在 0.5 秒之内返回给用户相应的
查询结果，但由于出现故障（比如系统部分机房发生断电或断网故障），查询
结果的响应时间增加到了 1~2 秒。
 功能上的损失：购物网站在购物高峰（如双十一）时，为了保护系统的稳定性，
部分消费者可能会被引导到一个降级页面。
 软状态（ Soft State）
 软状态是指允许系统存在中间状态，而该中间状态不会影响系统整体可用性。分布
式存储中一般一份数据会有多个副本，允许不同副本同步的延时就是软状态的体
现。mysql replication 的异步复制也是一种体现。
 最终一致性（ Eventual Consistency）
 最终一致性是指系统中的所有数据副本经过一定时间后，最终能够达到一致的状
态。弱一致性和强一致性相反，最终一致性是弱一致性的一种特殊情况。
```



#### 强一致性、弱一致性、最终一致性



```
从客户端角度，多进程并发访问时，更新过的数据在不同进程如何获取的不同策略，决定了
不同的一致性。对于关系型数据库，要求更新过的数据能被后续的访问都能看到，这是强一
致性。如果能容忍后续的部分或者全部访问不到，则是弱一致性。如果经过一段时间后要求
能访问到更新后的数据，则是最终一致性
```



### 分布式事务解决方案



#### 2PC

```
数据库支持的 2PC【2 phase commit 二阶提交】，又叫做 XA Transactions。
MySQL 从 5.5 版本开始支持，SQL Server 2005 开始支持，Oracle 7 开始支持。
其中，XA 是一个两阶段提交协议，该协议分为以下两个阶段：
第一阶段：事务协调器要求每个涉及到事务的数据库预提交(precommit)此操作，并反映是
否可以提交. 第二阶段：事务协调器要求每个数据库提交数据。
其中，如果有任何一个数据库否决此次提交，那么所有数据库都会被要求回滚它们在此事务
中的那部分信息。
```

问题：

```
XA 协议比较简单，而且一旦商业数据库实现了 XA 协议，使用分布式事务的成本也比较
低。
 XA 性能不理想，特别是在交易下单链路，往往并发量很高，XA 无法满足高并发场景
 XA 目前在商业数据库支持的比较理想，在 mysql 数据库中支持的不太理想，mysql 的
XA 实现，没有记录 prepare 阶段日志，主备切换回导致主库与备库数据不一致。
 许多 nosql 也没有支持 XA，这让 XA 的应用场景变得非常狭隘。
 也有 3PC，引入了超时机制（无论协调者还是参与者，在向对方发送请求后，若长时间
未收到回应则做出相应处理）
```

#### 3pc



#### 柔性事务-TCC 事务补偿型方案

刚性事务：遵循 ACID 原则，强一致性。 

柔性事务：遵循 BASE 理论，最终一致性；



一个业务接口处理逻辑 需要写三个方法，

一个是 准备阶段。一个是调用阶段，一个是回滚阶段

![image-20210313225153077](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313225153077.png)

```
一阶段 prepare 行为：调用 自定义 的 prepare 逻辑。
二阶段 commit 行为：调用 自定义 的 commit 逻辑。
二阶段 rollback 行为：调用 自定义 的 rollback 逻辑。
所谓 TCC 模式，是指支持把 自定义 的分支事务纳入到全局事务的管理中。
```



#### 柔性事务-最大努力通知型方案

```
按规律进行通知，不保证数据一定能通知成功，但会提供可查询操作接口进行核对。这种
方案主要用在与第三方系统通讯时，比如：调用微信或支付宝支付后的支付结果通知。这种
方案也是结合 MQ 进行实现，例如：通过 MQ 发送 http 请求，设置最大通知次数。达到通
知次数后即不再通知。
案例：银行通知、商户通知等（各大交易业务平台间的商户通知：多次通知、查询校对、对
账文件），支付宝的支付成功异步回调
```

#### 柔性事务-可靠消息+最终一致性方案（异步确保型）



### springcloud-alibaba-Seata



#### 使用环境准备

1.http://seata.io/zh-cn/blog/seata-quick-start.html



2.seata的三个角色

![image-20210313225926328](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210313225926328.png)

==TC是全局事务管理器，TM是管理当前大事务管理器，RM是每一个调用的小事务==



3.创建一个undolog日志表

```sql
-- auto-generated definition
create table undo_log
(
    id            bigint auto_increment
        primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info longblob     not null,
    log_status    int          not null,
    log_created   datetime     not null,
    log_modified  datetime     not null,
    ext           varchar(100) null,
    constraint ux_undo_log
        unique (xid, branch_id)
)
    charset = utf8;
```

4.安装全局事务server

![image-20210314010336356](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314010336356.png)

![image-20210314010351562](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314010351562.png)

#### 整合

```

 *  Seata控制分布式事务
 *  1）、每一个微服务先必须创建 undo_log；
 *  2）、安装事务协调器；seata-server： https://github.com/seata/seata/releases
 *  3）、整合
 *      1、导入依赖 spring-cloud-starter-alibaba-seata  seata-all-0.7.1
 *      2、解压并启动seata-server；
 *          registry.conf: 注册中心配置； 修改registry type=nacos
 *          file.conf：
 *      3、所有想要用到分布式事务的微服务使用seata DataSourceProxy代理自己的数据源
 *      4、每个微服务，都必须导入
 *              registry.conf
 *              file.conf  vgroup_mapping.{application.name}-fescar-service-group = "default"
 *      5、启动测试分布式事务
 *      6、给分布式大事务的入口标注@GlobalTransactional
 *      7、每一个远程的小事务用 @Transactional
```

1.依赖

```
<dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
        </dependency>
```

==注意版本。是0.7还是0.9还是1.0，配置不一样==

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314011249852.png" alt="image-20210314011249852" style="zoom:50%;" />

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314011302170.png" alt="image-20210314011302170" style="zoom:50%;" />

![image-20210314011533950](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314011533950.png)

![image-20210314011606344](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314011606344.png)

seata配置信息文件默认是file.conf

![image-20210314011640577](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314011640577.png)

file.conf的事务日志(这里以文件存储，db存储还需要去创建表)

![image-20210314011856628](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314011856628.png)



启动nacos

启动seata

![image-20210314012802806](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314012802806.png)



使用  ` @GlobalTransactional`

配置seata代理数据源 在我们涉及到分布式事务的模块

```java
@Configuration
public class MySeataConfig {

    @Autowired
    DataSourceProperties dataSourceProperties;

    /**
     * 需要seata代理数据源
     * @param dataSourceProperties
     * @return
     */
    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties){
        //properties.initializeDataSourceBuilder().type(type).build();
        HikariDataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if (StringUtils.hasText(dataSourceProperties.getName())) {
            dataSource.setPoolName(dataSourceProperties.getName());
        }
        return new DataSourceProxy(dataSource);
    }
}
```

涉及到的每一个服务复制下面两个文件

![image-20210314013840350](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314013840350.png)

![image-20210314013925597](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314013925597.png)

修改seata分组配置名字

file.conf

![image-20210314014236053](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314014236053.png)

如果这里不改就改application.properties

![image-20210314014500119](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314014500119.png)



![image-20210314014717640](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314014717640.png)

有了分布式事务，就相当于，可以控制整体的分布式事务，优惠券异常，订单系统也会回滚，不减库存



==seata默认使用AT模式，AT模式有场景限制，不适高并发场景，AT相当于2PC的变种==

==并且AT加了很多锁和隔离==

下单不适合 seata分布式事务，这种是高并发场景



### 并发场景分布式事务

```
 //为了保证高并发。库存服务自己回滚。可以发消息给库存服务；
                //库存服务本身也可以使用自动解锁模式  消息
```



1.最大努力通知



2.最终一致性+可靠消息(异步方案)-采用这个



消息队列流程

![image-20210314021152673](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314021152673.png)

==保证最终一致性，==

==用到了延时队列 去解锁库存==









### 订单服务(分布式事务完善)



### RabbitMQ延时队列-最终一致性



1.

![image-20210314021836445](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314021836445.png)



2.

```
消息的TTL就是消息的存活时间。
• RabbitMQ可以对队列和消息分别设置TTL。
• 对队列设置就是队列没有消费者连着的保留时间，也可以对每一个单独的消息做单独的
设置。超过了这个时间，我们认为这个消息就死了，称之为死信。
• 如果队列设置了，消息也设置了，那么会取小的。所以一个消息如果被路由到不同的队
列中，这个消息死亡的时间有可能不一样（不同的队列设置）。这里单讲单个消息的
TTL，因为它才是实现延迟任务的关键。可以通过设置消息的expiration字段或者x- message-ttl属性来设置时间，两者是一样的效果。
```



3.DXL

```
• 一个消息在满足如下条件下，会进死信路由，记住这里是路由而不是队列，
一个路由可以对应很多队列。（什么是死信）
• 一个消息被Consumer拒收了，并且reject方法的参数里requeue是false。也就是说不
会被再次放在队列里，被其他消费者使用。（basic.reject/ basic.nack）requeue=false
• 上面的消息的TTL到了，消息过期了。
• 队列的长度限制满了。排在前面的消息会被丢弃或者扔到死信路由上
• Dead Letter Exchange其实就是一种普通的exchange，和创建其他
exchange没有两样。只是在某一个设置Dead Letter Exchange的队列中有
消息过期了，会自动触发消息的转发，发送到Dead Letter Exchange中去。
• 我们既可以控制消息在一段时间后变成死信，又可以控制变成死信的消息
被路由到某一个指定的交换机，结合二者，其实就可以实现一个延时队列
```

队列A的消息长时间(TTL-30分钟)没有被消费，我们把这些消息经过exchange放到另外一个队列B中让别的消费者监听去消费 ，B收到的消息就是A的TTL 30分钟以后的消息

![image-20210314023014115](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314023014115.png)



4.代码实现

流程

![image-20210314023434437](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314023434437.png)

命名规范以后：

![image-20210314023615468](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314023615468.png)

==整体流程就是，生产者生产消息到交换机，经过指定route key到 死消队列一段时间 经过route key匹配 回到了 交换机，然后交换机再把这些消息经过指定route key到另外一个普通队列 等待消费者消费，形成了一个延时的效果==

```java
 @RabbitListener
    public void listenerOrder(OrderEntity entity, Channel channel, Message message){
        System.out.println("收到过期的消息，准备关闭订单："+entity);
    }

    /**
     //@Bean Binding，Queue，Exchange
     * 容器中的 Binding，Queue，Exchange 都会自动创建（RabbitMQ没有的情况）
     * RabbitMQ 只要有队列 重复启动。@Bean声明属性发生变化也不会覆盖
     * @return
     *
     * 死信队列
     */
    @Bean
    public Queue orderDelayQueue() {
        Map<String,Object> arguments = new HashMap<>();
        /**
         * 队列A的参数
         * x-dead-letter-exchange: order-event-exchange
         * x-dead-letter-routing-key: order.release.order
         * x-message-ttl: 60000
         */
        arguments.put("x-dead-letter-exchange","order-event-exchange");
        arguments.put("x-dead-letter-routing-key","order.release.order");
        arguments.put("x-message-ttl",60000);
        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        Queue queue = new Queue("order.delay.queue", true, false, false,arguments);
        return queue;
    }

    /**
     * 延时队列  普通队列B
     * @return
     */
    @Bean
    public Queue orderReleaseOrderQueue() {
        Queue queue = new Queue("order.release.order.queue", true, false, false);
        return queue;
    }

    /**
     * 延时交换机
     * @return Topic类型的交换机
     */
    @Bean
    public Exchange orderEventExchange() {
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        return new TopicExchange("order-event-exchange",true,false);
    }

    /**
     * 延时队列中的  order.delay.queue 和 order-event-exchange 的 bind
     * @return
     */
    @Bean
    public Binding orderCreateOrderBingding() {
        //String destination, DestinationType destinationType, String exchange, String routingKey,
        //			Map<String, Object> arguments
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order",
                null);
    }

    /**
     * 延时队列中的  order.release.order.queue 和 order-event-exchange 的 bind
     * @return
     */
    @Bean
    public Binding orderReleaseOrderBingding() {
        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order",
                null);
    }

```



### 延时队列实现库存解锁

1.

![](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314021152673.png)

==只需要监听消费处理 stock.delay.queue 的消息 就已经是延时以后的消息了==

2.创建相关交换机和队列

3.监听库存解锁

下订失败，库存锁了，需要重新给MQ发信息实现自动解锁库存(加回去)

4.解锁业务逻辑

5.定时关单功能

```
1.订单定时自动关单 库存自动解锁
2.订单系统给库存系统延时队列发消息 解锁库存
3.利用MQ最终一致性-高并发场景+系统异构
```



### 订单-消息丢失-消息积压-重复等解决

#### 丢失

```
• 1、消息丢失
• 消息发送出去，由于网络问题没有抵达服务器
• 做好容错方法（try-catch），发送消息可能会网络失败，失败后要有重试机
制，可记录到数据库，采用定期扫描重发的方式
• 做好日志记录，每个消息状态是否都被服务器收到都应该记录
• 做好定期重发，如果消息没有发送成功，定期去数据库扫描未成功的消息进
行重发
• 消息抵达Broker，Broker要将消息写入磁盘（持久化）才算成功。此时Broker尚
未持久化完成，宕机。
• publisher也必须加入确认回调机制，确认成功的消息，修改数据库消息状态。
• 自动ACK的状态下。消费者收到消息，但没来得及消息然后宕机
• 一定开启手动ACK，消费成功才移除，失败或者没来得及处理就noAck并重
新入队
```

```
  /**
                 * 1、做好消息确认机制（pulisher，consumer【手动ack】）
                 * 2、做好补偿，每一个发送的消息都在数据库做好记录。定期将失败的消息再次发送一遍
                 */
```



日志表

```sql
CREATE TABLE `mq_message` (
`message_id` char(32) NOT NULL, `content` text, `to_exchane` varchar(255) DEFAULT NULL, `routing_key` varchar(255) DEFAULT NULL, `class_type` varchar(255) DEFAULT NULL, `message_status` int(1) DEFAULT '0' COMMENT '0-新建 1-已发送 2-错误抵达 3-已抵达', `create_time` datetime DEFAULT NULL, `update_time` datetime DEFAULT NULL, PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
```



#### 重复

```
• 2、消息重复
• 消息消费成功，事务已经提交，ack时，机器宕机。导致没有ack成功，Broker的消息
重新由unack变为ready，并发送给其他消费者
• 消息消费失败，由于重试机制，自动又将消息发送出去
• 成功消费，ack时宕机，消息由unack变为ready，Broker又重新发送
• 消费者的业务消费接口应该设计为幂等性的。比如扣库存有
工作单的状态标志
• 使用防重表（redis/mysql），发送消息每一个都有业务的唯
一标识，处理过就不用处理
• rabbitMQ的每一个消息都有redelivered字段，可以获取是否
是被重新投递过来的，而不是第一次投递过来的
```

#### 积压

```
• 3、消息积压
• 消费者宕机积压
• 消费者消费能力不足积压
• 发送者发送流量太大
• 上线更多的消费者，进行正常消费
• 上线专门的队列消费服务，将消息先批量取出来，记录数据库，离线慢慢处理
```





## 支付(完善订单)



### 支付宝

1.

https://opendocs.alipay.com/open/270/105898

需要营业执照等等下证明

2.使用沙箱环境联调

![image-20210314174555058](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314174555058.png)

https://opendocs.alipay.com/open/200/105311

3.使用官方demo

https://opendocs.alipay.com/open/270/106291

![image-20210314174654752](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314174654752.png)

下载的demo

![image-20210314174758316](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314174758316.png)

4.运行demo

### 模拟运行

#### 配置说明



1.关键配置信息

```
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";
```

2.

![image-20210314180008157](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314180008157.png)

![image-20210314180020143](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314180020143.png)

3.

![image-20210314180614379](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314180614379.png)



4.

https://openhome.alipay.com/platform/appDaily.htm

![image-20210314180956399](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314180956399.png)

5.配置支付宝公钥和自己的私钥

![image-20210314181522713](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314181522713.png)

6.配置帐号

![image-20210314181810122](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314181810122.png)

7.启动项目 模拟支付

![image-20210314182204755](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314182204755.png)



### 内网穿透

```
1、简介
内网穿透功能可以允许我们使用外网的网址来访问主机；
正常的外网需要访问我们项目的流程是：
1、买服务器并且有公网固定 IP
2、买域名映射到服务器的 IP
3、域名需要进行备案和审核
2、使用场景
1、开发测试（微信、支付宝）
2、智慧互联
3、远程控制
4、私有云
3、内网穿透的几个常用软件
1、natapp：https://natapp.cn/ 优惠码：022B93FD（9 折）[仅限第一次使用]
2、续断：www.zhexi.tech 优惠码：SBQMEA（95 折）[仅限第一次使用]
3、花生壳：https://www.oray.com/
```

1.

![image-20210314183000925](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314183000925.png)

2.

![image-20210314183633384](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314183633384.png)

3.修改回调

==就是申请以后内网穿透服务商分配的ip，这样外网也可以访问我们的本机了==

```

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://内网穿透服务商分配给我们的ip/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://内网穿透服务商分配给我们的ip/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

```



### 整合支付

1.sdk

```

        <!-- https://mvnrepository.com/artifact/com.alipay.sdk/alipay-sdk-java -->
<!--        导入支付宝的SDK-->
        <dependency>
            <groupId>com.alipay.sdk</groupId>
            <artifactId>alipay-sdk-java</artifactId>
            <version>4.9.28.ALL</version>
        </dependency>

```

2.配置



![image-20210314184818249](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314184818249.png)

![image-20210314185058560](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314185058560.png)



VO

![image-20210314184809852](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314184809852.png)

3.支付成功回调



4.会员订单列表页面数据渲染



5.异步通知内网穿透数据渲染



![image-20210314194349493](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314194349493.png)

![image-20210314195031679](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314195031679.png)



6.支付成功

7.收单

![image-20210314200756667](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314200756667.png)



## 秒杀模块和技术分析



```
秒杀具有瞬间高并发的特点，针对这一特点，必须要做限流 + 异步 + 缓存（页面静态化）
+ 独立部署。
限流方式：
1. 前端限流，一些高并发的网站直接在前端页面开始限流，例如：小米的验证码设计
2. nginx 限流，直接负载部分请求到错误的静态页面：令牌算法 漏斗算法
3. 网关限流，限流的过滤器
4. 代码中使用分布式信号量
5. rabbitmq 限流（能者多劳：chanel.basicQos(1)），保证发挥所有服务器的性能。
```



流程

![image-20210314224649999](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314224649999.png)



### 1.**后台添加商品**



### 2.**定时任务&分布式任务调度**



#### 定时任务

1.cron表达式

百度去生成	

2.springboot整合定时任务+异步任务

####  分布式任务调度



### 3.时间日期处理



### 4.商品上架



### 5.幂等性保证



### 6.查询秒杀商品和商品页面数据渲染

### 

### 7.秒杀系统设计



1.

![image-20210314234341197](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314234341197.png)

2.

![image-20210314234352899](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314234352899.png)

3.秒杀流程

![image-20210314235809861](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210314235809861.png)

4.

![image-20210315000454757](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315000454757.png)

### 





## sentinel

```
什么是熔断
A 服务调用 B 服务的某个功能，由于网络不稳定问题，或者 B 服务卡机，导致功能时
间超长。如果这样子的次数太多。我们就可以直接将 B 断路了（A 不再请求 B 接口），凡是
调用 B 的直接返回降级数据，不必等待 B 的超长执行。 这样 B 的故障问题，就不会级联影
响到 A。
什么是降级
整个网站处于流量高峰期，服务器压力剧增，根据当前业务情况及流量，对一些服务和
页面进行有策略的降级[停止服务，所有的调用直接返回降级数据]。以此缓解服务器资源的
的压力，以保证核心业务的正常运行，同时也保持了客户和大部分客户的得到正确的相应。
异同：
相同点：
1、为了保证集群大部分服务的可用性和可靠性，防止崩溃，牺牲小我
2、用户最终都是体验到某个功能不可用
不同点：
1、熔断是被调用方故障，触发的系统主动规则
2、降级是基于全局考虑，停止一些正常服务，释放资源
什么是限流
对打入服务的请求流量进行控制，使服务能够承担不超过自己能力的流量压力
```



### 使用

Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

1.

https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D

2.

![image-20210315002139824](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315002139824.png)

3.hystrix和sentinel区别

![image-20210315012550331](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315012550331.png)

### 引入&概念

1.

https://github.com/alibaba/Sentinel/wiki/%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8

2.规则

```
1.定义资源
2.定义规则
3.检验规则是否生效
```



### 整合springboot

```


/**
 * 1、整合Sentinel
 *   1）、导入依赖 spring-cloud-starter-alibaba-sentinel
 *   2）、下载sentinel的控制台
 *   3）、配置sentinel控制台地址信息
 *   4) 、在控制台调整参数。【默认所有的流控设置保存在内存中，重启失效】
 *
 *
 * 2、每一个微服务都导入 actuator ()；并配合management.endpoints.web.exposure.include=*
 * 3、自定义sentinel流控返回数据
 *
 * 4、使用Sentinel来保护feign远程调用：熔断；
 *    1）、调用方的熔断保护：feign.sentinel.enabled=true
 *    2）、调用方手动指定远程服务的降级策略。远程服务被降级处理。触发我们的熔断回调方法
 *    3）、超大浏览的时候，必须牺牲一些远程服务。在服务的提供方（远程服务）指定降级策略；
 *      提供方是在运行。但是不运行自己的业务逻辑，返回的是默认的降级数据（限流的数据），
 *
 * 5、自定义受保护的资源
 *   1）、代码
 *    try(Entry entry = SphU.entry("seckillSkus")){
 *        //业务逻辑
 *    }
 *     catch(Execption e){}
 *
 *   2）、基于注解。
 *   @SentinelResource(value = "getCurrentSeckillSkusResource",blockHandler = "blockHandler")
 *
 *   无论是1,2方式一定要配置被限流以后的默认返回.
 *   url请求可以设置统一返回:WebCallbackManager
 *
 *
 */
```



1.依赖

```
   <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
```

2.查看sentinel核心 版本

![image-20210315013647370](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315013647370.png)

根据核心版本去限制相应的jar

![image-20210315013829828](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315013829828.png)



![image-20210315014014309](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315014014309.png)



![image-20210315014122969](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315014122969.png)



3.发起请求

![image-20210315014450122](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315014450122.png)

4.可以自定义流控规则，默认是controller 请求路径名

### 自定义流控响应



1.依赖

```
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

2.配置和自定义返回数据

```
# endpoints 暴露信息
management.endpoints.web.exposure.include=*

```

```java
 public SeckillSentinelConfig(){
        WebCallbackManager.setUrlBlockHandler(new UrlBlockHandler(){
            @Override
            public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
                R error = R.error(BizCodeEnume.TOO_MANY_REQUEST.getCode(), BizCodeEnume.TOO_MANY_REQUEST.getMsg());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().write(JSON.toJSONString(error));
            }
        });
    }
```

### sentinel全服务引入&流控

1.为每一个服务都引入sentinel

```
# sentinel
#transport.port 服务消息传输端口
spring.cloud.sentinel.transport.port=8719
# transport.dashboard 控制台地址
spring.cloud.sentinel.transport.dashboard=localhost:8333
# endpoints 暴露信息
management.endpoints.web.exposure.include=*
```





2.流量控制(参考文档)

https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6

![image-20210315234319235](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315234319235.png)



### 溶断降级

1.熔断

对feign熔断

导openfeign 依赖

```
# 开启feign sentinel
feign.sentinel.enabled=true
```



**调用方的熔断保护，不让服务崩溃**

然后调用方实现feign接口的fallback，作为本地熔断方法的保护措施

```
@FeignClient(value = "viwmall-seckill",fallback = SeckillFeignServiceFallBack.class) // fallback = SeckillFeignServiceFallBack.class失败的调用类的执行方法
public interface SeckillFeignService {

    @GetMapping("/sku/seckill/{skuId}")
    R getSkuSeckillInfo(@PathVariable("skuId") Long skuId);
}

```

```
@Slf4j
@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckillInfo(Long skuId) {
        log.info("熔断方法调用...getSkuSeckillInfo");
        return R.error(BizCodeEnume.TOO_MANY_REQUEST.getCode(),BizCodeEnume.TOO_MANY_REQUEST.getMsg());
    }
}
```



==在调用方手动指定降级策略==

![image-20210315235646038](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210315235646038.png)

如RT=1 ，时间窗口=10，表示 openfeign 调用不响应，10s 后再重试，



==设置远程服务提供方限制==





### 自定义保护资源

1.代码 try-catch 自定义

```java

        try (Entry entry = SphU.entry("seckillSkus")) {
            Set<String> keys = redisTemplate.keys(SESSIONS_CACHE_PREFIX + "*");
            for (String key : keys) {
              xxxxx
              受保护的业务代码.....
        } catch (BlockException e) {
            log.error("资源被限流,{}", e.getMessage());
        }
```

![image-20210316002629852](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316002629852.png)

2.注解



```java
// blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，而 fallback 函数会针对所有类型的异常。  
@SentinelResource(value = "getCurrentSeckillSkusResource",blockHandler = "blockHandler")
    @Override
    public List<SecKillSkuRedisTo> getCurrentSeckillSkus() {
    
    }


  public  List<SecKillSkuRedisTo> blockHandler(BlockException e){
        log.error("getCurrentSeckillSkusResource被限流了..");
        return null;
    }
```

blockHandler注解还可以指定降级溶断 时的调用方法

==注意要编写方法被限制后返回前端的数据是什么==





----





==getCurrentSeckillSkusResource和seckillSkus相当于不同的流控资源，可以分别设置==

==只要有资源我们就可以做流控降级==

### 网关流控

1.

sentinel和网关的整合

```
<dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-sentinel-gateway</artifactId>
            <version>2.1.0.RELEASE</version>
        </dependency>
```

2.网关的流控配置

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316004436504.png" alt="image-20210316004436504" style="zoom:50%;" />

![image-20210316004525099](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316004525099.png)

3.可以属性匹配

![image-20210316004753716](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316004753716.png)

3.api组

![image-20210316004838135](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316004838135.png)



4.自定义网关流控返回

```
@Configuration
public class SentinelGatewayConfig {

    //TODO 响应式编程
    //GatewayCallbackManager
    public SentinelGatewayConfig(){
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler(){
            //网关限流了请求，就会调用此回调  Mono Flux
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {

                R error = R.error(BizCodeEnume.TOO_MANY_REQUEST.getCode(), BizCodeEnume.TOO_MANY_REQUEST.getMsg());
                String errJson = JSON.toJSONString(error);

//                Mono<String> aaa = Mono.just("aaa");
                Mono<ServerResponse> body = ServerResponse.ok().body(Mono.just(errJson), String.class);
                return body;
            }
        });

//        FlowRule flowRule = new FlowRule();
//        flowRule.setRefResource("gulimall_seckill_route");
////        flowRule.set
//        FlowRuleManager.loadRules(Arrays.asList(flowRule));
    }

}

```





## Sleuth+Zipkin 服务链路追踪

https://cloud.spring.io/spring-cloud-static/spring-cloud-sleuth/2.1.3.RELEASE/single/spring-cloud-sleuth.html

1.

```
Span（跨度）：基本工作单元，发送一个远程调度任务 就会产生一个 Span，Span 是一
个 64 位 ID 唯一标识的，Trace 是用另一个 64 位 ID 唯一标识的，Span 还有其他数据信
息，比如摘要、时间戳事件、Span 的 ID、以及进度 ID。
 Trace（跟踪）：一系列 Span 组成的一个树状结构。请求一个微服务系统的 API 接口，
这个 API 接口，需要调用多个微服务，调用每个微服务都会产生一个新的 Span，所有
由这个请求产生的 Span 组成了这个 Trace。
 Annotation（标注）：用来及时记录一个事件的，一些核心注解用来定义一个请求的开
始和结束 。这些注解包括以下：
 cs - Client Sent -客户端发送一个请求，这个注解描述了这个 Span 的开始
 sr - Server Received -服务端获得请求并准备开始处理它，如果将其 sr 减去 cs 时间戳
便可得到网络传输的时间。
 ss - Server Sent （服务端发送响应）–该注解表明请求处理的完成(当请求返回客户
端)，如果 ss 的时间戳减去 sr 时间戳，就可以得到服务器请求的时间。
 cr - Client Received （客户端接收响应）-此时 Span 的结束，如果 cr 的时间戳减去
cs 时间戳便可以得到整个请求所消耗的时间。
```

2.流程图

![image-20210316010606740](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316010606740.png)



### 整合sleuth

https://cloud.spring.io/spring-cloud-static/spring-cloud-sleuth/2.1.3.RELEASE/single/spring-cloud-sleuth.html

1.

```
  <!--        链路追踪-->
                <dependency>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-starter-sleuth</artifactId>
                </dependency>
```

2.

```
logging.level.org.springframework.cloud.openfeign=debug
logging.level.org.springframework.cloud.sleuth=debug
```



### 整合zipkin

1.安装

```
 docker run -d -p 9411:9411 openzipkin/zipkin --name mallzipkin
```

2.依赖

```
<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

3.配置

```
spring.zipkin.base-url=http://47.103.197.6:9411/
spring.zipkin.discovery-client-enabled=false
spring.zipkin.sender.type=web
spring.sleuth.sampler.probability=1
```

4.

![image-20210316013304615](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316013304615.png)

![image-20210316013359210](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316013359210.png)

![image-20210316013500112](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316013500112.png)



![image-20210316014210009](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316014210009.png)



![image-20210316014250523](https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210316014250523.png)

### 持久化

1.docker方式

```
docker run --env STORAGE_TYPE=elasticsearch --env ES_HOSTS=192.168.56.10:9200
openzipkin/zipkin-dependencies
```



