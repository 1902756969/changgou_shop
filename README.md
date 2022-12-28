## 微服务项目-黑马畅购商城

<meta name="referrer" content="no-referrer">


## 一、技术架构

前端及WEB技术栈 Vue.js Node.js Lua HTML5 ElementUl Theamleaf

运维技术栈 Cannal Docker容器 FastDFS Eureka集群 Redis集群 Elasticsear 部署 ch集群

分布式架构及权限技术栈 Spring Security CAS单点登录 JWT SpringBoot SpringCloud

持久化技术栈 mybatis SpringDataRedis SpringDataElasticsearch

数据库&消息队列技术栈

mySQL读写分离 RabbitMQ

<meta name="referrer" content="no-referrer">


![image-20221119152711480](https://picgo-1308041230.cos.ap-nanjing.myqcloud.com/img/202212281500698.png)

####  系统架构图

![image-20221119152734144](https://pic.imgdb.cn/item/63abeffa08b6830163832ff6.png)

## 二、项目预览

## mysql截图

![image-20221119152206338](https://pic.imgdb.cn/item/63abf03508b683016383c5d0.png)



## 三、项目截图

CentOs虚拟机账号：root  		密码123456    

  mysq账号: root 	 密码: root

服务登录:heima 		密码:123456

订单服务需要开启fescar-server

![image-20221119153046126](https://pic.imgdb.cn/item/63abf03a08b683016383d181.png)

### 1）下单支付功能

添加购物车

[localhost:8001/api/wcart/add?skuId=100000006163&num=5](http://localhost:8001/api/wcart/add?skuId=100000006163&num=5)

![image-20221119175544028](https://pic.imgdb.cn/item/63abf03e08b683016383d9db.png)

购物车

http://localhost:8001/api/wcart/list#

<img src="https://pic.imgdb.cn/item/63abf04008b683016383dfa1.png" alt="image-20221119175910145" style="zoom:50%;" />

订单结算页面

http://localhost:8001/api/worder/ready/order

![image-20221119180004639](https://pic.imgdb.cn/item/63abf04308b683016383e7ba.png)



点击提交订单

![image-20221119180028583](https://pic.imgdb.cn/item/63abf04a08b683016383f811.png)

点击确定跳转

![image-20221119180043982](https://pic.imgdb.cn/item/63abf04708b683016383f008.png)

点击微信支付

![image-20221119180558806](https://pic.imgdb.cn/item/63abf05f08b683016384367a.png)











## 四、资源下载

视频教程: https://pan.baidu.com/s/1j9_y2dgO5k9rV43-pNDU-A?pwd=iqjt 提取码：iqjt


虚拟机CentOs(mysql已经安装在docker)：https://pan.baidu.com/s/1iKDSgO4df4TGv67bcOez1g?pwd=5gq9  提取码：5gq9

 项目资料代码：https://pan.baidu.com/s/1AQzBoC34bp5TZffKJY9zMw?pwd=9j31 提取码：9j31

讲义HTML版：链接：https://pan.baidu.com/s/1nPW7pc1R64h86p1JtCmJbQ?pwd=ynr5 提取码：ynr5

##### 订单死信队列

1）创建死信交换器 exchange.ordertimeout （**fanout**）

（2）创建队列queue.ordertimeout

（3）建立死信交换器 exchange.ordertimeout 与队列queue.ordertimeout 之间的绑定

（4）创建队列queue.ordercreate，Arguments添加

x-message-ttl=10000

x-dead-letter-exchange： exchange.ordertimeout

（5）测试：向queue.ordercreate队列添加消息，等待10秒后消息从queue.ordercreate队列消失，

##### 添加商品购物车

