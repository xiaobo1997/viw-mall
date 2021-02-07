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



5. 使用人人开源搭建后台管理系统
   1. git clone xxx 
   2. 删除git
   3. 导入项目
   4. 根据数据库执行不同的sql脚本，直接新创建后台数据库执行sql



