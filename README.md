## 本项目clone自CIM，gitee地址：https://gitee.com/farsunset/cim.git，感谢作者：远方夕阳 

***

### TODO  
- 1.添加IM  

- 2.集群化  


### 相关架构图  

### 设计文档


### TODO now
1.全局异常在单元测试中如何开启
2.单元测试如何关闭jdbc等不必要组件启动
3.日志打印方式



#### Q&A
1.Q:客户端发送，等待服务端回复OK，超时重试。
  A:客户端使用Future，超时重发，服务端校验重复。
2.Q:服务端推送客户端超时，顺序性。
  A:1.服务端重发添加重试标志，客户端接收到有重试标志的消息=》1.本地校验2.或者调用服务端校验
    2.服务端未成功的不推送下一条？
  