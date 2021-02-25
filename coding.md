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

#### 商品服务-电商概念-SPU&SKU&规格参数&销售属性

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





### 仓储服务api





