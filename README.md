# active-todo-list-sever
高主动性的todo清单的服务器源代码

## 介绍

​	“高主动性的to do清单”参考当今市场上流行的一些任务清单的优点，并弥补其缺陷，提升使用者做事效率将成为“高主动性的to do清单”的当务之急，增强用户在当今这个竞争激烈的残酷社会的竞争力和生存能力，也是“高主动性的to do清单”当仁不让的重任。

## 服务端架构图

![](https://chengweijun.oss-cn-hangzhou.aliyuncs.com/img/高主动性todo清单架构图.png)

| 服务              | 作用                                            |
| ----------------- | ----------------------------------------------- |
| gateway           | 统一网关                                        |
| user-service      | 用户账号相关服务                                |
| user-sync-service | 用户数据同步相关服务                            |
| backstage         | 后台对接服务                                    |
| Mysql             | 存放用户账号、代办事项数据                      |
| InfluxDB          | 存放Mysql数据变更记录                           |
| canal-sever       | alibaba/canal，负责Mysql执行日志的获取和解析    |
| canal-client      | 对接canal-sever，负责将日志解析结果存入influxDB |



## 数据同步原理

**服务器端基于数据库日志实现数据库同步**

![](https://chengweijun.oss-cn-hangzhou.aliyuncs.com/img/基于日志的数据库同步原理.png)

> 在上图中：
>
> ​	**canal-sever**是阿里巴巴开源的Mysql数据库日志获取与解析服务，
>
> ​	**canal-client**是用于对接**canal-sever**，获取解析结果并将数据库的变更以（行id，表名，变更类型，用户id）的格式存入**influxDB**中
>
> ​	同步服务器（**user-sync-service**）从**influxDB**中根据用户id与时间戳获取用户上次同步以来的数据变更，去重（每一行数据只取最后一次变更），然后从**Mysql**中获取并填充数据（对于增、改类型变更），最后返回给客户端

**客户端基于数据库触发器实现数据库同步**

![](https://chengweijun.oss-cn-hangzhou.aliyuncs.com/img/基于触发器的数据库同步原理.png)

> 客户端变更记录与数据存放在在同一个数据库中