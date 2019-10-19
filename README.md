#### SpringBoot功能模块
##### spring-boot-curd

使用springBoot实现基本的crud功能，页面使用thymeleaf引擎模板。Restful编程风格。
包括：资源国际化功能的实现，基本登录功能的实现，数据增删改查功能的实现，三大组件的注册和使用(servlet,filter,listener),异常的处理等。

##### SpringBoot-data

使用SpringBoot整合Druid,MyBatis,redis。

SpringBoot整合MyBatis，数据库为MySql，连接池为Druid。提供了两种方式，一种是基于注解的方式。另一种为利用配置文件的方式。

增加redis的配置，包括redis的连接，redis序列化器的使用，redis发布订阅模式的使用，使用Lua脚本，redis缓存的管理。

提供了java中利用注解(@CachePut,@CacheEvict等)使用redis缓存的例子。