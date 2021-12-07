## Redis 安装 （处理网络资源的时候是单线程的，比如接受客户端命令时候）

yum -y install wget
在虚拟机上下载linux
wget http://download.redis.io/releases/redis-5.0.5.tar.gz 
2、将 压缩包 解压到 /opt/目录下 
3、安装gcc  yum install gcc -y
4、make 编译
5、make install

#### 二、redis 命令解析

1、redis-benchmark 用来做性能测试
2、redis-check-aof  用来修复持久化文件
3、redis-check-rdb  用来修复持久化文件
4、redis-cli 连接客户端
5、redis-sentinel  哨兵-> redis-server
6、redis-server 启动服务端

#### 三、conf文件解析

1、bind  本机ip 或者注释掉 也可以配置为0.0.0.0 
2、proteed-model yes 默认是开启 遇到两种情况不生效 
	(1) 使用了bind  （2） 设置了密码
3、pidfile /var/run/redis_6379.pid   当Redis以守护进程方式运行时，Redis默认会把pid写入/var/run/redis.pid文件，可以通过pidfile指定
4、dir ./  指定持久化文件生成位置 默认是启动 redis 文件目录
5、关闭RDB持久化机制  save "900" ->save “”  有主从复制的时候  RDB机制关闭不掉
6、开启AOF  appendonly yes   也可以通过 命令开启 config set appendonly yes

#### 四 、命令

1、select 8 切换redis库
2、DBSIZE 查看数量
3、bgsave 手动触发持久化操作

#### 五、RDB 文件的缺点 

?	突然宕机的时候 会丢失太多数据 默认是60秒持久化一次
?	AOF 记录命令字符串	aof也有fork子进程（重写）

证明：手动触发持久化操作  然后查看 redis 相关的进程 可以观察到有个 redis-bgsave 的一个进程
		在启动目录下也可以看到一个 temp-dump.rdb 文件

#### 六、集群搭建

##### 1、各个节点的配置文件的主从机的集群密码和本机密码都设置成一样的

?		原因是主从不是固定的

##### 2、基础的配置文件

?	1) 端口 poot  2)后台运行 daemonize yes  3) 进程文件 pidfile /var/run/redis_8001.pid 

?	4) 持久化文件保存位置 dir /usr/local/redis-cluster/8001 

?	5) 集群密码 masterauth 123456

?	6)  本机密码requirepass 123456

##### 	3、集群配置的

?	1)集群开关 cluster-enabled yes

?	2)集群节点配置文件 cluster-config-file nodes-8001.conf

?	3)  设置如果某一小集群全部挂掉，整个集群是否可用  cluster-require-full-coverage no

?    4) sed 's/8001/8002/g' 8001/redis.conf > 8002/redis.conf   含义是 将 8001 目录下的 redis.conf 中的8001 替换为8002 		并存到8002下

?		下图所示 ：带有 [cluster] 的是以集群的方式运行， 此时没有

![1608348265266](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608348265266.png)

##### 4、 分配主从 分配槽位 (cluster help)

/usr/local/bin/redis-cli --cluster create 192.168.31.33:8001 192.168.31.33:8002 192.168.31.33:8003 192.168.31.33:8004 192.168.31.33:8005 192.168.31.33:8006 --cluster-replicas 1

这条命令干了三件事：

?	1、让这几个节点相互认识 即 meet操作

?	2、分配主从，前三个为主节点，然后后面的三个随机分配为主节点的从节点

?	3、分配哈希槽位  

?	执行后 查看节点信息如下，可以看到，hash槽位已经分配，从节点后面有主节点的信息

![1608350319656](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608350319656.png)

向配制好的集群添加数据

?	连接任意一节点的客户端： /usr/local/bin/redis-cli  -h 192.168.31.33 -p 8001 -a 123456  -c

?	命令解释 ： -a 密码 -c :redis 自带的重定向节点功能，根据key值算出hash槽位自动切换对应的节点（redis客户端独有）

?	cluster keyslot key :返回key对应的hash值

![1608361756787](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608361756787.png)



5、扩容

?	命令：/usr/local/bin/redis-cli  -h 192.168.31.33 -p 8001 -a 123456  --cluster help 找到 add-node

?	/usr/local/bin/redis-cli --cluster add-node  new _host:new_port  existing_node:existing_port

?	对于已经存的ip端口，集群里随意拿一个就可以

?	/usr/local/bin/redis-cli --cluster add-node 192.168.31.33:8007 192.168.31.33:8001 :默认是主节点

?	加上参数 --cluster-slave --cluster-master-id 111 指定添加为某主节点的从节

?	1.槽迁移计划

?		语法：/redis-cli --cluster reshard 已存在节点ip ： 端口

?		/usr/local/bin/redis-cli --cluster reshard 192.168.0.104:7000

?	 2.迁移数据

?		执行流程：提示要分配多少槽-》接收节点ID-》all/done 

七、分布式锁

?	分布式锁针对，分布式下的多进程，java级别解决不了的竞争 

?	setnx k1 v1,如果不存在 则创建，如果存在则作废

?	expire ：设置过期时间

?	setex ： 

?	分布式锁：也可以单机版，也可以



![1608388638899](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608388638899.png)





事物 

multi  : 开启事物 然后插入多个值 ，然后exec 都写入

discard ，之前插入的都取消



watch key 监控某个key： 必须要再开启事物之前

![1608432592724](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608432592724.png)

![1608432612211](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608432612211.png)

通配符的订阅消息的有问题，稍后查资料了解下： https://www.runoob.com/redis/redis-java.html 资料地址

删除策略：

?	定时删除：以cpu内存换redis内存，维护了一个定时器，有时间戳的数据专门放到一个地方，然后定时器去查看数据是否过期，然后删除数据

?	惰性删除：以redis内存 换cpu内存，只有再get的时候才会把指定的数据删除

?	定期删除：

![1608434982624](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608434982624.png)



redis 使用是惰性+定期删除 

 

淘汰策略：

当内存使用达到最大值时，redis使用的清除策略。有以下几种可以选择（明明有6种，官方配置文件里却说有5种可以选择？）：
volatile-lru   利用LRU算法移除设置过过期时间的key (LRU:最近使用 Least Recently Used ) 
allkeys-lru   利用LRU算法移除任何key 
volatile-random 移除设置过过期时间的随机key 
allkeys-random  移除随机key 
volatile-ttl   移除即将过期的key(minor TTL) 
noeviction  不移除任何key，只是返回一个写错误 。默认选项





主从复制

?	slaveof 