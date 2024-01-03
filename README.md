# 如何部署项目到服务器？

## 前端

下载 nginx：http://nginx.org/en/download.html

打开前端项目，执行 npm run build，将生成的 dist 文件夹拷贝进 nginx\html 下。

配置 nginx 服务器默认端口：nginx\conf\nginx.conf，listen 80 -> listen 8081。

在 nginx.conf 文件，添加一条 location 配置，同时将原来默认的 location 注释掉：

```
#location / {
#    root   html;
#    index  index.html index.htm;
#}

location / {
     try_files $uri $uri/ /index.html;
}
```

配置完成后，运行 nginx 根目录下的 nginx.exe，访问 http://localhost:8081/。

## 后端

将项目打包成 jar 包：mvn install

到 jar 包对应的目录下执行：java -jar wj-0.0.1-SNAPSHOT.jar

## 安装指南

### Ubuntu安装MySQL

sudo apt-get install mysql-server

设置root密码：sudo mysql_secure_installation

查看mysql服务状态：sudo systemctl status mysql

启动mysql服务：sudo systemctl start mysql

停止mysql服务：sudo systemctl stop mysql

查看3306端口是否有服务在监听：sudo netstat -tulnp | grep 3306

查看mysql进程：ps aux | grep mysql

登录到mysql：sudo mysql -u root -p

查询mysql版本：SELECT VERSION();

### Docker安装MySQL

拉取MySQL镜像：docker pull mysql

运行MySQL容器：docker run --name wj-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql

### 创建前端项目

安装nvm：curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash

安装node和npm：nvm install 20.10.0

npm修改成淘宝镜像源：npm config set registry https://registry.npmmirror.com

安装vue-cli：npm install -g vue-cli

以webpack为模板生成项目：vue init webpack wj-vue

安装依赖、编译运行：npm Install && npm run dev

访问：http://localhost:8080

安装axios：npm install axios@0.25.0 --save

### 创建后端项目

初始化模版项目：start.spring.io（maven、jdk17、依赖）

运行项目：./mvnw spring-boot:run

打包：mvn package

运行jar包：java -jar target/wj-0.0.1-SNAPSHOT.jar