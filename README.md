# Spring-aop-Authority
用SpringAop控制权限
## 注意
本项目使用了redis作为缓存，大家请提前搭建好redis服务器，小编这里使用的是redis单机版
## 系统模块说明
com.spring.mybatis 业务模块 <br>
com.spring.security 权限安全管理模块 <br>
com.spring.mybatis.componet redis模块
## 运用技术
SpringMvc+Spring+Mybatis<br>
mysql+redis
## sql脚本
创建名为itoo_test的数据库，执行sql文件下的sql脚本
## 启动项目
导入项目后，项目中内置tomcat插件，将项目启动起来后，输入访问localhost:8080/login访问主页，输入用户名：admin 密码：随便写一个

