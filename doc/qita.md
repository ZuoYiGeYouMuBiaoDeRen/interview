## 1.kafka避免重复消费
https://blog.csdn.net/u010899985/article/details/109137763  

## 2.dubbo序列化
https://blog.csdn.net/u013076044/article/details/96632661  
hessian，  
总结：  
序列化技术和反序列化技术可应用于网络传输、持久化等场景  
在dubbo中，只需要实现ObjectInput和ObjectOutput，并且创建一个Serialization的实现， 即可个性化定制实现Dubbo序列化  

## 3.redis zset实现，map实现
https://segmentfault.com/a/1190000037473381  
为什么采用跳表，而不使用哈希表或平衡树实现呢：  
skiplist和各种平衡树（如AVL、红黑树等）的元素是有序排列的，而哈希表不是有序的。因此，在哈希表上只能做单个key的查找，不适宜做范围查找。所谓范围查找，指的是查找那些大小在指定的两个值之间的所有节点。  
在做范围查找的时候，平衡树比skiplist操作要复杂。在平衡树上，我们找到指定范围的小值之后，还需要以中序遍历的顺序继续寻找其它不超过大值的节点。如果不对平衡树进行一定的改造，这里的中序遍历并不容易实现。而在skiplist上进行范围查找就非常简单，只需要在找到小值之后，对第1层链表进行若干步的遍历就可以实现。  
平衡树的插入和删除操作可能引发子树的调整，逻辑复杂，而skiplist的插入和删除只需要修改相邻节点的指针，操作简单又快速。  
从内存占用上来说，skiplist比平衡树更灵活一些。一般来说，平衡树每个节点包含2个指针（分别指向左右子树），而skiplist每个节点包含的指针数目平均为1/(1-p)，具体取决于参数p的大小。如果像Redis里的实现一样，取p=1/4，那么平均每个节点包含1.33个指针，比平衡树更有优势。  

https://blog.csdn.net/u010900754/article/details/78022815  

## 4.flink反压
https://developer.aliyun.com/article/727389  

## 5.hbase 版本管理，时间范围查询
https://blog.csdn.net/weixin_45380326/article/details/105820410  

## 6.hbase存储结构
https://juejin.cn/post/6844903753271754759  

## 7.算法
给定一个含有 n 个正整数的数组和一个正整数 target 。  
找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。  

```java
public int minSubArrayLen(int s, int[] nums) {
    int n = nums.length;
    if (n == 0) {
        return 0;
    }
    int ans = Integer.MAX_VALUE;
    int start = 0, end = 0;
    int sum = 0;
    while (end < n) {
        sum += nums[end];
        while (sum >= s) {
            ans = Math.min(ans, end - start + 1);
            sum -= nums[start];
            start++;
        }
        end++;
    }
    return ans == Integer.MAX_VALUE ? 0 : ans;
}
```

## 8.b树、b+树，区别，mysql 为什么用b+树而不用hash、b、红黑树等
https://blog.csdn.net/weixin_36098377/article/details/108688986  

## 9. 25匹马，5个跑道，最少比多少次能比出前3名？前5名？
https://blog.csdn.net/goodfrar/article/details/51661610?utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control  

## 10.redis底层数据结构实现，hash，set，zset
https://www.jianshu.com/p/05bf8a945944  
http://redisbook.com/preview/object/sorted_set.html  
https://juejin.cn/post/6863258283483807752  
https://developer.aliyun.com/article/666398  

## 11.spring 事务 原理，transactional注意点
https://www.cnblogs.com/youzhibing/p/6414780.html  
https://www.jianshu.com/p/b33c4ff803e0  

https://my.oschina.net/zhangxufeng/blog/1935556  
https://my.oschina.net/zhangxufeng/blog/1943983  
https://my.oschina.net/zhangxufeng/blog/1973493  

https://blog.csdn.net/blacktal/article/details/79345902?utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control  

@Transactional使用注意事项：
1. 不要在 protected,private 或者包内可见方法上使用注解 @Transactional；@Transactional 注解要应用在可见性为 public 的方法上 ;
2. @Transactional注解的方法在自调用场景中无效 ;
3. 不要从@Transactional方法内向外传递任何对当前事务做结论的消息
4. 尽量不要从@Transactional方法内捕捉数据库操作异常
5. 正确的设置@Transactional 的 propagation 属性
6. 正确的设置@Transactional 的 rollbackFor 属性
https://blog.csdn.net/andy_zhang2007/article/details/83624533