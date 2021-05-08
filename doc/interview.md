## 1.mysql事物隔离级别
https://blog.csdn.net/m0_48795607/article/details/112881436?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242
https://blog.csdn.net/weixin_33969103/article/details/113629655

## 2.分布式事务实现方式
https://www.cnblogs.com/crazymakercircle/p/14375424.html

TCC和可靠消息最终一致性方案是在生产中最常用。
一个要求强一致，一个要求最终一致。
TCC用于强一致主要用于核心模块，例如交易/订单等。最终一致方案一般用于边缘模块例如库存，通过mq去通知，保证最终一致性，也可以业务解耦。
面试中如果你真的被问到，可以分场景回答：
（1）对于那些特别严格的场景，用的是 TCC 来保证强一致性；
准备好例子：你找一个严格要求数据绝对不能错的场景（如电商交易交易中的资金），可以回答使用成熟的如中间件如阿里分布式事务seata组件。
（2）对于数据一致性要求没有那些特别严格的场景，可以回答使用可靠消息最终一致性方案，如果基于 RocketMQ 来实现了分布式事务框架，也
基于ActiveMQ，RabbitMQ, RocketMQ等，自己开发一个可靠消息服务，收到消息之后，尝试投递到MQ，如果投递失败，重试投递。
准备好例子：你找一个严格对数据一致性要求没有那么严格的场景，如电商订单插入之后要调用库存服务更新库存，库存数据没有那么严格，比如少一点点也行，只需要保障最终一致性即可。

## 3.mq选型，为啥要用kafka，kafka优势，有没有其他的替代方案
https://blog.csdn.net/weixin_40778497/article/details/107912755

https://www.jianshu.com/p/18c45f23f859

RocketMQ，是阿里开源的，经过阿里的生产环境的超高并发、高吞吐的考验，性能卓越，同时还支持分布式事务等特殊场景。
而且RocketMQ是基于Java语言开发的，适合深入阅读源码，有需要可以站在源码层面解决线上生产问题，包括源码的二次开发和改造。

另外就是Kafka。Kafka提供的消息中间件的功能明显较少一些，相对上述几款MQ中间件要少很多。
但是Kafka的优势在于专为超高吞吐量的实时日志采集、实时数据同步、实时数据计算等场景来设计。
因此Kafka在大数据领域中配合实时计算技术（比如Spark Streaming、Storm、Flink）使用的较多。但是在传统的MQ中间件使用场景中较少采用。

## 4.商品秒杀，全流程，怎么实现，有哪些要点
https://www.cnblogs.com/diegodu/p/9244955.html

https://blog.csdn.net/weixin_41563161/article/details/106088859?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_baidulandingword-0&spm=1001.2101.3001.4242

https://zhuanlan.zhihu.com/p/164707091

https://blog.csdn.net/weixin_35681869/article/details/107587749

要点总结：
1、前端三板斧【扩容】【限流】【静态化】
2、后端两条路【内存】+【排队】

## 5.扣减库存，怎么防止锁竞争冲突
https://blog.csdn.net/qq315737546/article/details/76850173

## 6.redis数据结构，每种数据结构的应用场景
https://blog.csdn.net/xinzhifu1/article/details/104350271

## 7.redis script脚本
https://blog.csdn.net/weixin_41384860/article/details/115003786

## 8.分布式锁
https://blog.csdn.net/leilei107/article/details/106066740

## 9.项目流程介绍，核心点


