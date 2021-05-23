## 1. 排序算法了解哪些，快排，快排复杂度，优化，堆排序，建堆过程
https://blog.csdn.net/Dustin_CDS/article/details/79721261  

## 2. 反射了解么，原理是什么
https://www.cnblogs.com/yougewe/p/10125073.html  
反射的实现原理：  
1. 反射类及反射方法的获取，都是通过从列表中搜寻查找匹配的方法，所以查找性能会随类的大小方法多少而变化；  
2. 每个类都会有一个与之对应的Class实例，从而每个类都可以获取method反射方法，并作用到其他实例身上；  
3. 反射也是考虑了线程安全的，放心使用；  
4. 反射使用软引用relectionData缓存class信息，避免每次重新从jvm获取带来的开销；  
5. 反射调用多次生成新代理Accessor, 而通过字节码生存的则考虑了卸载功能，所以会使用独立的类加载器；  
6. 当找到需要的方法，都会copy一份出来，而不是使用原来的实例，从而保证数据隔离；  
7. 调度反射方法，最终是由jvm执行invoke0()执行；  

## 3. treemap 和 linkdedhashmap 区别，实现原理
https://blog.csdn.net/xzh109/article/details/104340573  

## 4. jvm 类加载的过程讲讲，符号引用是什么，哪些情况会发生初始化
https://blog.csdn.net/qq_41489540/article/details/115494281  
加载、验证、准备、解析、初始化  -> 使用  ->卸载  
符号引用是用一组符号描述所引用的目标;直接引用是指向目标的指针  
https://zhuanlan.zhihu.com/p/355422415  

## 5. spring 的循环依赖，怎么解决的，为什么需要加个三级缓存，二级不行么
https://juejin.cn/post/6882266649509298189  
测试证明，二级缓存也是可以解决循环依赖的。为什么 Spring 不选择二级缓存，而要额外多添加一层缓存呢？  
如果 Spring 选择二级缓存来解决循环依赖的话，那么就意味着所有 Bean 都需要在实例化完成之后就立马为其创建代理，而 Spring 的设计原则是在 Bean 初始化完成之后才为其创建代理。所以，Spring 选择了三级缓存。但是因为循环依赖的出现，导致了 Spring 不得不提前去创建代理，因为如果不提前创建代理对象，那么注入的就是原始对象，这样就会产生错误。  

## 6. springboot 有什么特点，相比与 spring，了解 springboot 的自动装配的一个原理么
https://blog.csdn.net/qq_38526573/article/details/107084943  

## 7. kafka 支持事务么，你们项目中有使用么，它的原理是什么
https://www.jianshu.com/p/64c93065473e  

## 8. 怎么统计一亿用户的日活，hyperloglog 有什么缺点，bitmap 不行么
https://segmentfault.com/a/1190000022308645  

## 9. 算法：求一个环形链表的环的长度
https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode-solution/  
https://www.cnblogs.com/xudong-bupt/p/3667729.html  

## 1. redis 的几种数据类型，你们用过哪些，zset 有用来做什么

## 2. 垃圾收集器，cms 垃圾收集过程，为什么停顿时间短，有什么缺点，concurrent mode failure 怎么办，内存碎片怎么解决，为什么不用标记整理法

## 3. 线程池原理，核心参数，线程数设置，参数动态调整后变化过程，Tomcat 线程池原理，常用的线程池，你们一般使用哪种，为什么，会有什么问题，线程抛异常怎么办，阻塞队列原理

## 4. 做过分库分表么，为什么要分库分表，会有什么问题，多少数据适合分库分表，跨库，聚合操作怎么做

## 6. 算法：给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * node中是否包含p或q
     *
     * @param node
     * @param p
     * @param q
     * @return
     */
    private boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (this.resNode != null) {
            return false;
        }

        if (node == null) {
            return false;
        }

        boolean left = dfs(node.left, p, q);
        boolean right = dfs(node.right, p, q);

        if ((left && right) || ((left || right) && (node.val == p.val || node.val == q.val))) {
            this.resNode = node;
            return false;
        }

        return left || right || node.val == p.val || node.val == q.val;
    }

    private TreeNode resNode;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return this.resNode;
    }
}
```


## 1. nio 讲讲，实现原理，优缺点
https://www.cnblogs.com/kma-3/p/9625443.html  
https://blog.csdn.net/h2604396739/article/details/82534253  

## 2. 了解 netty 么，讲讲 netty 的设计模型，架构，使用场景

## 3. zookeeper 读写数据过程
