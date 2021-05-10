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

## 10.mybatis逻辑分页和物理分页的区别？
https://www.cnblogs.com/ljstudy/p/14468632.html

## 11.mybatis延迟加载怎么实现的?
https://blog.csdn.net/shfqbluestone/article/details/52888144?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.vipsorttest&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.vipsorttest

## 12.mybatis executor 三级缓存分别介绍下？
https://blog.csdn.net/weixin_39758041/article/details/110588841

## 13.SpringbootApplication 介绍下？
https://www.cnblogs.com/niugang0920/p/12197085.html

## 14.Spring Boot为啥要基于Spring实现？
https://blog.csdn.net/xunjiushi9717/article/details/99564539

## 15.什么是IoC自动装配？为啥要用Spring IoC?就不能自己new吗？
https://blog.csdn.net/zjh_746140129/article/details/84487589

为什么不使用new来创建实例？1.增加耦合，会访问到内部属性，2.更新的地方比较多，难于维护

## 16.Spring Bean的生命周期流程？
https://blog.csdn.net/w_linux/article/details/80086950

https://www.cnblogs.com/weixy3/p/14088458.html

https://www.cnblogs.com/zrtqsk/p/3735273.html

## 17.Spring AOP的实现？
https://blog.csdn.net/zzti_erlie/article/details/106976850

https://zhuanlan.zhihu.com/p/222466171

https://blog.csdn.net/dadiyang/article/details/82920139
    
## 18.请介绍下jdk 8里面的cms和g1? 标记复制、标记整理；增量标记漏标。
https://blog.csdn.net/u010310183/article/details/102790573

http://www.mianshigee.com/question/10052hfb

## 19.线程的基本状态，Thread下的 yield、sleep、wait的区别？
https://blog.csdn.net/qq_43322057/article/details/105884484

## 20.synchronized锁升级流程?
https://blog.csdn.net/tongdanping/article/details/79647337?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.vipsorttest&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.vipsorttest

## 21.请介绍下IO多路复用，epoll是怎么实现的？
https://www.cnblogs.com/liqiangwei/p/14354144.html

## 22.Netty Reactor模型？
https://blog.csdn.net/hollis_chuang/article/details/115274546

https://www.jianshu.com/p/d95638039a66

## 23.Netty堆外内存需要自己清理吗，怎么清理？请详细描述下零拷贝是怎么实现的？PhatomReference
netty对外内存清理：netty对外内存，可以自动回收（堆内的DirectByteBuffer对象被GC时，它背后的堆外内存也会被回收），也可以主动回收（从DirectByteBuffer里取出Cleaner，然后调用它的clean()就行）；https://blog.csdn.net/dfdsggdgg/article/details/51557644

netty零拷贝：https://www.jianshu.com/p/6da82ae13e9f，https://blog.csdn.net/weixin_44367006/article/details/106578474

PhatomReference：虚引用

## 24.请介绍下倒排索引，倒排列表用fst为啥不用B+Tree？
正排索引是从文档到关键字的映射（已知文档求关键字），倒排索引是从关键字到文档的映射（已知关键字求文档）

lucene从4开始大量使用的数据结构是FST（Finite State Transducer）。FST有两个优点：1）空间占用小。通过对词典中单词前缀和后缀的重复利用，压缩了存储空间；2）查询速度快。O(len(str))的查询时间复杂度。

https://www.cnblogs.com/bonelee/p/6226185.html

## 25.elasticsearch常见面试题
https://segmentfault.com/a/1190000037450984

https://www.nosuchfield.com/2019/03/18/How-Elasticsearch-elected-the-master/

## 26.ThreadLocal是怎么实现的?
https://www.cnblogs.com/huanshilang/p/12204703.html

https://zhuanlan.zhihu.com/p/166259550

## 27.强引用和弱引用的区别？发生gc时会怎么处理？WeakReference Gc的时候会回收掉
https://blog.csdn.net/qq_41050869/article/details/104201072

## 28.设计模式：模版和策略模式，描述下工厂模式？
模板模式：https://blog.csdn.net/Sirius_hly/article/details/104910228

策略模式：https://blog.csdn.net/weixin_41835916/article/details/81903774

工厂模式：https://blog.csdn.net/southjeff/article/details/79877412

单例模式：双重检查，静态内部类；https://blog.csdn.net/bupttulongming/article/details/103218282

## 29.算法:double的n次方；链表倒数第k个节点？
double的n次方

https://www.cnblogs.com/gl-developer/p/6475399.html
```java
public double myPow(double x, int n) {
    if (n == 0) {
        return 1;
    }
    if (n == 1) {
        return x;
    }
    if (n == -1) {
        return 1 / x;
    }
    double tmp = myPow(x, n >> 1);
    tmp *= tmp;
    if ((n & 1) == 1) {
        tmp *= x;
    }
    return tmp;
}
```
链表倒数第k个节点
```java
public ListNode getKthFromEnd(ListNode head, int k) {
    ListNode front=head;
    ListNode res=head;
    for (int i = 0; i < k; i++) {
        front=front.next;
    }
    while (front!=null){
        front=front.next;
        res=res.next;
    }
    return res;
}
```

