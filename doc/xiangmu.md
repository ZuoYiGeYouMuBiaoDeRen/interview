项目经验

社招面试项目很重要，不光是你项目本身的技术复杂度，还有业务复杂度，你本身在项目中担任的什么角色，遇到过什么问题，瓶颈在哪，怎么解决的，这几个问题是非常重要的，很多公司到最后基本上都是围绕着你的项目在问，给面试官讲明白你的项目是必须具备的能力

总结下社招面试问项目最主要的问题套路：

# 一、推荐引擎：

## 1.推荐引擎如何设计的：
分两大块：配置中心和推荐引擎sdk；
推荐引擎sdk：微内核+插件；（微内核：完成不得不完成的功能，包括线程池管理、配置加载、插件管理、推荐流定义）


## 2. 你项目为什么这么设计，你这样设计有什么好处，解决了什么问题，会产生什么问题，还有什么可以优化的
好处：1.实现了各环节组件化、配置化，达到基础组件服用、快速组合上线、推荐流程百合花的效果
	2.提供常用的公共组件，覆盖大部分重复开发工作，缩短需求响应周期

遇到的问题：因为采用sdk的方式，业务方需要继承sdk到自己的服务，还是需要进行一定的开发；优化：采用容器化技术，实现自动部署，不过对比心来说存在过度设计问题


## 3. 这么设计有什么瓶颈吗，遇到了什么问题，有什么改善的方案
问题：目前是每个推荐请求都会到配置中心拉去配置，如果接入场景太多，会导致拉取配置的qps非常高，配置中心稍有抖动，就会影响到所有服务
改进：每个请求拉取配置改为本地缓存配置；配置的更新参考开源配置中心，进行实时和定时更新

为什么一开始不这样设计：目前的方案开发速度更快，更好拆分任务，且开始时流量不大；这些计划放到后面进行优化


## 4. 项目遇到的难点，技术挑战，你是怎么解决的，为什么用这种方式解决，还有更好的方式么


## 5. 根据你简历上提到的具体功能去扣细节



二、特征平台：

## 1.如何设计的：
实时特征、离线特征、配置管理、在线查询服务等几部分


## 2. 你项目为什么这么设计，你这样设计有什么好处，解决了什么问题，会产生什么问题，还有什么可以优化的
好处：1.在线服务与其他环节解藕，避免其他环节对在线服务的影响
	2.实时特征通过 数据预处理+flink计算，降低了flink部分的开发难度
	3.数据预处理部分，采用责任链模式，减少代码开发
	4.flink特征聚合部分，配置化，多窗口聚合处理，减少存储和计算资源的使用

遇到的问题：


## 3. 这么设计有什么瓶颈吗，遇到了什么问题，有什么改善的方案
问题：
改进：

为什么一开始不这样设计：


## 4. 项目遇到的难点，技术挑战，你是怎么解决的，为什么用这种方式解决，还有更好的方式么
问题：特征组：多表多特征，其中一张表数据修改，导致这个请求必然请求hbase，导致接口耗时变长；
解决：更新hbase后，不删除redis中缓存，而是更新redis map；如果更新redis失败，重复尝试3次；
