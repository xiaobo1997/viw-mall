[TOC]



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





## 后台管理系统商品服务api



利用脚手架编写菜单

1.

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210208235727645.png" alt="image-20210208235727645" style="zoom:50%;" />

2.

<img src="https://xiaoboblog-bucket.oss-cn-hangzhou.aliyuncs.com/blog/image-20210208235806961.png" alt="image-20210208235806961" style="zoom:50%;" />

编写商品服务分类接口



编写商品服务网关统一配置 和解决跨域的配置

1.通过过滤路径重写+解决跨域 实现后台管理页面的访问





