## 1. tcp 和 udp 的区别，tcp 怎么保证可靠连接的，出现网络拥塞怎么解决
tcp特点：面向连接，仅支持单播传输，面向字节流，可靠传输，提供阻塞控制，提供全双工通信  
udp特点：面向无连接，有单播、多播、广播功能，面向报文，不可靠性，头部开销小、传输数据报文很高效  
https://www.cnblogs.com/fundebug/p/differences-of-tcp-and-udp.html  

TCP 是通过下面几个特性保证数据传输的可靠性：序列号和确认应答信号，超时重发控制，连接管理，滑动窗口控制，流量控制，拥塞控制  
https://cloud.tencent.com/developer/article/1591989  

拥塞控制的四个阶段：慢启动，拥塞避免，快速重传，快速恢复  
https://blog.csdn.net/hyman_yx/article/details/52065418  

## 2. tcp 和 udp 的报文结构了解么
https://www.cnblogs.com/HKUI/p/13889031.html  

## 4. 你们建表会定义自增 id 么，为什么，自增 id 用完了怎么办
为什么要设置自增主键 id ：  
1.可以唯一标识一行数据，在 InnoDB 构建索引树的时候会使用主键。  
2.自增 id 是顺序的，可以保证索引树上的数据比较紧凑，有更高的空间利用率以及减少数据页的分裂合并等操作，提高效率。  
3.一般使用手机号、身份证号作为主键等并不能保证顺序性。  
4.流水号一般相对较长，比如 28 位，32 位等，过长的话会二级索引占用空间较多。同时为了业务需求，流水号具有一定的随机性。  
https://cloud.tencent.com/developer/article/1737952  

自增id用完怎么办：https://blog.csdn.net/qq_36761831/article/details/100136057  

## 5. 一般你们怎么建 mysql 索引，基于什么原则，遇到过索引失效的情况么，怎么优化的
https://www.cnblogs.com/yizhiamumu/p/9055531.html  

索引失效优化：https://blog.csdn.net/wuseyukui/article/details/72312574  

## 6. jvm 内存结构，堆结构，栈结构，a+b 操作数栈过程，方法返回地址什么时候回收，程序计数器什么时候为空
https://www.huaweicloud.com/articles/4799df506d5b9ae8bd0a5cb5247723b5.html  
https://www.cnblogs.com/myseries/p/12871398.html  

## 7. redis 实现分布式锁，还有其他方式么，zookeeper 怎么实现，各有什么有缺点，你们为什么用 redis 实现

## 8. 算法：返回一个树的左视图
https://blog.csdn.net/sinat_36231857/article/details/88740525  

## 1. spring 你比较了解哪方面，讲讲，生命周期，bean 创建过程
https://www.cnblogs.com/theRhyme/p/11057233.html  
https://zebinh.github.io/2020/07/SpringStartupAndIoCAndAop/  
https://juejin.cn/post/6847902217777709063  

**重要** https://www.jianshu.com/p/1dec08d290c1  

## 2. 使用过事务么，遇到过事务失效的情况么，原因是什么

## 3. springboot 是怎么加载类的，通过什么方式
https://www.cnblogs.com/xxzhuang/p/11194559.html  

## 4. 什么对象会进入老年代，eden 和 survivor 比例可以调整么，参数是什么，调整后会有什么问题
https://zhuanlan.zhihu.com/p/83804324  

## 5. 微信朋友圈设计，点赞，评论功能实现，拉黑呢，redis 数据没了怎么办

## 7. 算法：给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表
https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode-solution/  

## 1. es 倒排索引，原理，lucene，分词，分片，副本
https://stor.51cto.com/art/202012/634521.htm  

## 2. es 写数据原理，数据实时么，为什么不实时，会丢数据么，segment，cache，buffer，translog 关系
https://blog.csdn.net/R_P_J/article/details/82254494  

## 3. es 深度分页，优化
https://cloud.tencent.com/developer/article/1676915?from=information.detail.es%E6%B7%B1%E5%BA%A6%E5%88%86%E9%A1%B5%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88  

## 5. 算法：验证二叉搜索树
```java
class Solution {
     //前一个节点
    TreeNode pre = null;
    //假设为true
    boolean ans = true;

    public boolean isValidBST(TreeNode root) {
        inOrder(root);
        return this.ans;
    }

    private void inOrder(TreeNode node) {
        //node为null或者为非二叉搜索树，快速返回
        if (node == null || !this.ans) {
            return;
        }

        //左
        inOrder(node.left);
        //根
        if (pre == null) {
            pre = node;
        } else {
            if (pre.val >= node.val) {
                this.ans = false;
            }
            pre = node;
        }
        //右
        inOrder(node.right);
    }
}
```