# 美团

## 1.mysql 日志文件有哪些，分别介绍下作用
* 错误日志
* 查询日志：查询日志里面记录了数据库执行的所有命令，不管语句是否正确，都会被记录
* 慢查询日志：慢查询日志记录的慢查询不仅仅是执行比较慢的SELECT语句，还有INSERT，DELETE，UPDATE，CALL等DML操作，只要超过了指定时间，都可以称为"慢查询"，被记录到慢查询日志中
* redo log：记录事务执行后的状态，用来恢复未写入磁盘的数据（data file）的已成功事务更新的数据。
* undo log：两个作用：提供回滚和多个行版本控制(MVCC)
* binlog：MySQL的二进制日志（binary log）是一个二进制文件，主要记录所有数据库表结构变更（例如CREATE、ALTER TABLE…）以及表数据修改（INSERT、UPDATE、DELETE…）的所有操作。binlog作用：恢复数据、复制数据、审计

## 2.你们项目为什么用 redis，快在哪，怎么保证高性能，高并发的
redis 实现高并发主要依靠主从架构，一主多从，一般来说，很多项目其实就足够了，单主用来写入数据，单机几万 QPS，多从用来查询数据，多个从实例可以提供每秒 10w 的 QPS。  
如果想要在实现高并发的同时，容纳大量的数据，那么就需要 redis 集群，使用 redis 集群之后，可以提供每秒几十万的读写并发。

## 3.redis 字典结构，hash 冲突怎么办，rehash，负载因子
* Redis 中的 Hash和 Java的 HashMap 更加相似,都是数组+链表的结构.当发生 hash 碰撞时将会把元素追加到链表上
* 我们先来了解下 hash 的内部结构.第一维是数组,第二维是链表.组成一个 hashtable.
* 在 Java 中 HashMap 扩容是个很耗时的操作,需要去申请新的数组，扩容的成本并不低，因为需要遍历一个时间复杂度为O(n)的数组，并且为其中的每个enrty进行hash计算。加入到新数组中
* 为了追求高性能,Redis 采用了渐进式 rehash 策略.这也是 hash 中最重要的部分.
* redis在扩容的时候执行 rehash 策略会保留新旧两个 hashtable 结构，查询时也会同时查询两个 hashtable.Redis会将旧 hashtable 中的内容一点一点的迁移到新的 hashtable 中,当迁移完成时,就会用新的 hashtable 取代之前的.当 hashtable 移除了最后一个元素之后,这个数据结构将会被删除.
* 数据搬迁的操作放在 hash 的后续指令中,也就是来自客户端对 hash 的指令操作.一旦客户端后续没有指令操作这个 hash.Redis就会使用定时任务对数据主动搬迁.
* 在 Redis 的实现里，扩容缩容有三条规则：1.当 Redis 没有进行 BGSAVE 相关操作，且 负载因子>1的时候进行扩容，扩容为原数组大小的2倍；2.当负载因子>5的时候，强行进行扩容；3.当负载因子<0.1的时候，进行缩容。
## 4.jvm 了解哪些参数，用过哪些指令
* -XX:+UseG1GC：表示启用了G1垃圾收集器；
* -Xms等价于-XX:InitialHeapSize：初始堆大小
* -Xmx等价于-XX:MaxHeapSize：最大堆大小
* -Xss等价于-XX:ThreadStackSize：线程堆栈大小

* jps：用来显示本地的java进程，可以查看本地运行着几个java程序，并显示他们的进程号。
* jmap：dump操作

## 5.zookeeper 的基本原理，数据模型，znode 类型，应用场景有哪些

## 6.一个热榜功能怎么设计，怎么设计缓存，如何保证缓存和数据库的一致性

## 7.容器化技术了解么，主要解决什么问题，原理是什么

## 8.算法：对于一个字符串，计算其中最长回文子串的长度

## 9.redis 集群，为什么是 16384，哨兵模式，选举过程，会有脑裂问题么，raft 算法，优缺点

## 10.jvm 类加载器，自定义类加载器，双亲委派机制，优缺点，tomcat 类加载机制
为什么要设计双亲委派机制：  
①：沙箱安全机制：自己写的java.lang.String.class类不会被加载，这样便可以防止核心 API库被随意篡改  
②：避免类的重复加载：当父亲已经加载了该类时，就没有必要子ClassLoader再加载一 次，保证被加载类的唯一性

## 11.tomcat 热部署，热加载了解么，怎么做到的

## 12.cms 收集器过程，g1 收集器原理，怎么实现可预测停顿的，region 的大小，结构
cms收集器过程：  
(1).初始标记, 需要 STW (Stop The World)  
(2).并发标记  
(3).重新标记, 需要 STW (Stop The World), 修正并发标记期间对象的标记变动  
(4).并发清除  

G1运作步骤：  
1、初始标记(stop the world事件 CPU停顿只处理垃圾)；  
2、并发标记(与用户线程并发执行)；  
3、最终标记(stop the world事件 ,CPU停顿处理垃圾)；  
4、筛选回收(stop the world事件 根据用户期望的GC停顿时间回收)(注意：CMS 在这一步不需要stop the world)  

与其他GC收集器相比，G1具备如下特点：  
1、并行于并发：G1能充分利用CPU、多核环境下的硬件优势，使用多个CPU（CPU或者CPU核心）来缩短stop-The-World停顿时间。部分其他收集器原本需要停顿Java线程执行的GC动作，G1收集器仍然可以通过并发的方式让java程序继续执行。  
2、分代收集：虽然G1可以不需要其他收集器配合就能独立管理整个GC堆，但是还是保留了分代的概念。它能够采用不同的方式去处理新创建的对象和已经存活了一段时间，熬过多次GC的旧对象以获取更好的收集效果。  
3、空间整合：与CMS的“标记--清理”算法不同，G1从整体来看是基于“标记整理”算法实现的收集器；从局部上来看是基于“复制”算法实现的。  
4、可预测的停顿：这是G1相对于CMS的另一个大优势，降低停顿时间是G1和ＣＭＳ共同的关注点，但Ｇ１除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为M毫秒的时间片段内，

region大小：  
XX:G1HeapRegionSize；  
MIN_REGION_SIZE：允许的最小的REGION_SIZE，即1M，不可能比1M还小；  
MAX_REGION_SIZE：允许的最大的REGION_SIZE，即32M，不可能比32M更大；限制最大REGION_SIZE是为了考虑GC时的清理效果；  

## 13.内存溢出，内存泄漏遇到过么，什么场景产生的，怎么解决的

## 14.锁升级过程，轻量锁可以变成偏向锁么，偏向锁可以变成无锁么，自旋锁，对象头结构，锁状态变化过程

## 15.kafka 重平衡，重启服务怎么保证 kafka 不发生重平衡，有什么方案
kafka如何避免重平衡：  
第一类非必要 Rebalance 是因为未能及时发送心跳，导致 Consumer 被“踢出”Group 而引发的。因此，你需要仔细地设置 session.timeout.ms 和 heartbeat.interval.ms 的值。  
第二类非必要 Rebalance 是 Consumer 消费时间过长导致的。  

## 16.怎么理解分布式和微服务，为什么要拆分服务，会产生什么问题，怎么解决这些问题

## 17.你们用的什么消息中间件，kafka，为什么用 kafka，高吞吐量，怎么保证高吞吐量的，设计模型，零拷贝
总的来说Kafka快的原因：  
1、partition顺序读写，充分利用磁盘特性，这是基础；  
2、Producer生产的数据持久化到broker，采用mmap文件映射，实现顺序的快速写入；  
3、Customer从broker读取数据，采用sendfile，将磁盘文件读到OS内核缓冲区后，直接转到socket buffer进行网络发送。  

mmap 和 sendfile总结：  
1、都是Linux内核提供、实现零拷贝的API；  
2、sendfile 是将读到内核空间的数据，转到socket buffer，进行网络发送；  
3、mmap将磁盘文件映射到内存，支持读和写，对内存的操作会反映在磁盘文件上。  
RocketMQ 在消费消息时，使用了 mmap。kafka 使用了 sendFile。  

## 18.算法 1：给定一个长度为 N 的整形数组 arr，其中有 N 个互不相等的自然数 1-N，请实现 arr 的排序，但是不要把下标 0∼N−1 位置上的数通过直接赋值的方式替换成 1∼N
```java
public static void sort(int[] arr) {
    if (arr == null || arr.length < 2) {
        return;
    }
    int tmp = 0;
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] != i + 1) {
            tmp = arr[arr[i] - 1];
            arr[arr[i] - 1] = arr[i];
            arr[i] = tmp;
        }
    }
}
```
## 19.算法 2：判断一个树是否是平衡二叉树

```text
recur(root) 函数：
返回值：
当节点root 左 / 右子树的深度差 \leq 1≤1 ：则返回当前子树的深度，即节点 root 的左 / 右子树的深度最大值 +1+1 （ max(left, right) + 1 ）；
当节点root 左 / 右子树的深度差 > 2>2 ：则返回 -1−1 ，代表 此子树不是平衡树 。
终止条件：
当 root 为空：说明越过叶节点，因此返回高度 00 ；
当左（右）子树深度为 -1−1 ：代表此树的 左（右）子树 不是平衡树，因此剪枝，直接返回 -1−1 ；
```
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }
    
    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if(left == -1) return -1;
        int right = recur(root.right);
        if(right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
```


## 20.Innodb 的结构了解么，磁盘页和缓存区是怎么配合，以及查找的，缓冲区和磁盘数据不一致怎么办，mysql 突然宕机了会出现数据丢失么

## 21.redis 字符串实现，sds 和 c 区别，空间预分配

## 22.redis 有序集合怎么实现的，跳表是什么，往跳表添加一个元素的过程，添加和获取元素，获取分数的时间复杂度，为什么不用红黑树，红黑树有什么特点，左旋右旋操作

## 23.io 模型了解么，多路复用，selete，poll，epoll，epoll 的结构，怎么注册事件，et 和 lt 模式

## 24.怎么理解高可用，如何保证高可用，有什么弊端，熔断机制，怎么实现
高可用三大利器：  
1.熔断:在服务的依赖调用中，被调用方出现故障时，出于自我保护的目的，调用方会主动停止调用，并根据业务需要进行相应处理。调用方这种主动停止调用的行为我们称之为熔断。  
2.限流:限流是针对服务请求数量的一种自我保护机制，当请求数量超出服务的处理能力时，会自动丢弃新来的请求。  
3.降级:降级是通过开关配置将某些不重要的业务功能屏蔽掉，以提高服务处理能力。在大促场景中经常会对某些服务进行降级处理，大促结束之后再进行复原。  

## 25.对于高并发怎么看，怎么算高并发，你们项目有么，如果有会产生什么问题，怎么解决

## 26.算法：给定一个二叉树，请计算节点值之和最大的路径的节点值之和是多少，这个路径的开始节点和结束节点可以是二叉树中的任意节点

题目分析:
这道题是求树的路径和的题目，不过和平常不同的是这里的路径不仅可以从根到某一个结点，而且路径可以从左子树某一个结点，然后到达右子树的结点，就像题目中所说的可以起始和终结于任何结点。在这里树没有被看成有向图，而是被当成无向图来寻找路径。因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果。在这里，函数的返回值定义为以自己为根的一条从根到子结点的最长路径（这里路径就不是当成无向图了，必须往单方向走）。这个返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可。这样一来，一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。而返回值则是自己的值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）。在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新。算法的本质还是一次树的遍历，所以复杂度是O(n)。而空间上仍然是栈大小O(logn)。

```java
import java.util.*;
 
/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 * }
 */
 
public class Solution {
    /**
     * 
     * @param root TreeNode类 
     * @return int整型
     思路：对于最大路径和：节点可能是负的，因此开始dfs的节点不一定是根节点，结束的节点也不一定是叶子结点
         1.根
         2.左+根  或者 右+根 （MAX（左子树最大路径和，右子树最大路径和）+根）
         3.左+右+根 （找到左子树最大路径和 + 右子树最大路径和 + 根）
     */
    private int maxs=Integer.MIN_VALUE; //记录路径最大和
    public int maxPathSum (TreeNode root) {
        if(root==null)
            return 0;
        getMax(root);
        return maxs;
    }
    public int getMax(TreeNode root){
        if(root==null){
            return 0;
        }
        
        int leftMax = getMax(root.left); //左孩子的最大路径和
        int rightMax = getMax(root.right);//右孩子的最大路径和
        //1.根节点+左右孩子的最大路径和
        int sum = root.val;
        if(leftMax>0){
            sum+=leftMax;
        }
        if(rightMax>0){
            sum+=rightMax;
        }
        if(maxs<sum){
            maxs=sum;
        }
        //2.在合法路径中，除了根节点的左右子树可同时包含，其余结点的左右子树只需保留一个。（通过这一行来更新maxs）
        return Math.max(leftMax,rightMax)>0? (Math.max(leftMax,rightMax)+root.val):root.val;
        
    }
}
```
## 27.算法：求一个 float 数的立方根，牛顿迭代法
```java
//二分法
public static double kaiGen(double input){
    double min = 0;
    double max = input;
    double mid = 0;
    
    while((max - min) > 0.001){   //精度
        mid = (max + min) / 2;
        if((mid * mid * mid) > input){
            max = mid;
        }
        else if((mid * mid * mid) < input){
            min = mid;
        }
        else{
            return mid;
        }
    }
    return max; //当输入为0时直接返回
}
```