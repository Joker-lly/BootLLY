## Redis ��װ ������������Դ��ʱ���ǵ��̵߳ģ�������ܿͻ�������ʱ��

yum -y install wget
�������������linux
wget http://download.redis.io/releases/redis-5.0.5.tar.gz 
2���� ѹ���� ��ѹ�� /opt/Ŀ¼�� 
3����װgcc  yum install gcc -y
4��make ����
5��make install

#### ����redis �������

1��redis-benchmark ���������ܲ���
2��redis-check-aof  �����޸��־û��ļ�
3��redis-check-rdb  �����޸��־û��ļ�
4��redis-cli ���ӿͻ���
5��redis-sentinel  �ڱ�-> redis-server
6��redis-server ���������

#### ����conf�ļ�����

1��bind  ����ip ����ע�͵� Ҳ��������Ϊ0.0.0.0 
2��proteed-model yes Ĭ���ǿ��� ���������������Ч 
	(1) ʹ����bind  ��2�� ����������
3��pidfile /var/run/redis_6379.pid   ��Redis���ػ����̷�ʽ����ʱ��RedisĬ�ϻ��pidд��/var/run/redis.pid�ļ�������ͨ��pidfileָ��
4��dir ./  ָ���־û��ļ�����λ�� Ĭ�������� redis �ļ�Ŀ¼
5���ر�RDB�־û�����  save "900" ->save ����  �����Ӹ��Ƶ�ʱ��  RDB���ƹرղ���
6������AOF  appendonly yes   Ҳ����ͨ�� ����� config set appendonly yes

#### �� ������

1��select 8 �л�redis��
2��DBSIZE �鿴����
3��bgsave �ֶ������־û�����

#### �塢RDB �ļ���ȱ�� 

?	ͻȻ崻���ʱ�� �ᶪʧ̫������ Ĭ����60��־û�һ��
?	AOF ��¼�����ַ���	aofҲ��fork�ӽ��̣���д��

֤�����ֶ������־û�����  Ȼ��鿴 redis ��صĽ��� ���Թ۲쵽�и� redis-bgsave ��һ������
		������Ŀ¼��Ҳ���Կ���һ�� temp-dump.rdb �ļ�

#### ������Ⱥ�

##### 1�������ڵ�������ļ������ӻ��ļ�Ⱥ����ͱ������붼���ó�һ����

?		ԭ�������Ӳ��ǹ̶���

##### 2�������������ļ�

?	1) �˿� poot  2)��̨���� daemonize yes  3) �����ļ� pidfile /var/run/redis_8001.pid 

?	4) �־û��ļ�����λ�� dir /usr/local/redis-cluster/8001 

?	5) ��Ⱥ���� masterauth 123456

?	6)  ��������requirepass 123456

##### 	3����Ⱥ���õ�

?	1)��Ⱥ���� cluster-enabled yes

?	2)��Ⱥ�ڵ������ļ� cluster-config-file nodes-8001.conf

?	3)  �������ĳһС��Ⱥȫ���ҵ���������Ⱥ�Ƿ����  cluster-require-full-coverage no

?    4) sed 's/8001/8002/g' 8001/redis.conf > 8002/redis.conf   ������ �� 8001 Ŀ¼�µ� redis.conf �е�8001 �滻Ϊ8002 		���浽8002��

?		��ͼ��ʾ ������ [cluster] �����Լ�Ⱥ�ķ�ʽ���У� ��ʱû��

![1608348265266](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608348265266.png)

##### 4�� �������� �����λ (cluster help)

/usr/local/bin/redis-cli --cluster create 192.168.31.33:8001 192.168.31.33:8002 192.168.31.33:8003 192.168.31.33:8004 192.168.31.33:8005 192.168.31.33:8006 --cluster-replicas 1

����������������£�

?	1�����⼸���ڵ��໥��ʶ �� meet����

?	2���������ӣ�ǰ����Ϊ���ڵ㣬Ȼ�����������������Ϊ���ڵ�Ĵӽڵ�

?	3�������ϣ��λ  

?	ִ�к� �鿴�ڵ���Ϣ���£����Կ�����hash��λ�Ѿ����䣬�ӽڵ���������ڵ����Ϣ

![1608350319656](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608350319656.png)

�����ƺõļ�Ⱥ�������

?	��������һ�ڵ�Ŀͻ��ˣ� /usr/local/bin/redis-cli  -h 192.168.31.33 -p 8001 -a 123456  -c

?	������� �� -a ���� -c :redis �Դ����ض���ڵ㹦�ܣ�����keyֵ���hash��λ�Զ��л���Ӧ�Ľڵ㣨redis�ͻ��˶��У�

?	cluster keyslot key :����key��Ӧ��hashֵ

![1608361756787](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608361756787.png)



5������

?	���/usr/local/bin/redis-cli  -h 192.168.31.33 -p 8001 -a 123456  --cluster help �ҵ� add-node

?	/usr/local/bin/redis-cli --cluster add-node  new _host:new_port  existing_node:existing_port

?	�����Ѿ����ip�˿ڣ���Ⱥ��������һ���Ϳ���

?	/usr/local/bin/redis-cli --cluster add-node 192.168.31.33:8007 192.168.31.33:8001 :Ĭ�������ڵ�

?	���ϲ��� --cluster-slave --cluster-master-id 111 ָ�����Ϊĳ���ڵ�Ĵӽ�

?	1.��Ǩ�Ƽƻ�

?		�﷨��/redis-cli --cluster reshard �Ѵ��ڽڵ�ip �� �˿�

?		/usr/local/bin/redis-cli --cluster reshard 192.168.0.104:7000

?	 2.Ǩ������

?		ִ�����̣���ʾҪ������ٲ�-�����սڵ�ID-��all/done 

�ߡ��ֲ�ʽ��

?	�ֲ�ʽ����ԣ��ֲ�ʽ�µĶ���̣�java���������˵ľ��� 

?	setnx k1 v1,��������� �򴴽����������������

?	expire �����ù���ʱ��

?	setex �� 

?	�ֲ�ʽ����Ҳ���Ե����棬Ҳ����



![1608388638899](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608388638899.png)





���� 

multi  : �������� Ȼ�������ֵ ��Ȼ��exec ��д��

discard ��֮ǰ����Ķ�ȡ��



watch key ���ĳ��key�� ����Ҫ�ٿ�������֮ǰ

![1608432592724](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608432592724.png)

![1608432612211](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608432612211.png)

ͨ����Ķ�����Ϣ�������⣬�Ժ�������˽��£� https://www.runoob.com/redis/redis-java.html ���ϵ�ַ

ɾ�����ԣ�

?	��ʱɾ������cpu�ڴ滻redis�ڴ棬ά����һ����ʱ������ʱ���������ר�ŷŵ�һ���ط���Ȼ��ʱ��ȥ�鿴�����Ƿ���ڣ�Ȼ��ɾ������

?	����ɾ������redis�ڴ� ��cpu�ڴ棬ֻ����get��ʱ��Ż��ָ��������ɾ��

?	����ɾ����

![1608434982624](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1608434982624.png)



redis ʹ���Ƕ���+����ɾ�� 

 

��̭���ԣ�

���ڴ�ʹ�ôﵽ���ֵʱ��redisʹ�õ�������ԡ������¼��ֿ���ѡ��������6�֣��ٷ������ļ���ȴ˵��5�ֿ���ѡ�񣿣���
volatile-lru   ����LRU�㷨�Ƴ����ù�����ʱ���key (LRU:���ʹ�� Least Recently Used ) 
allkeys-lru   ����LRU�㷨�Ƴ��κ�key 
volatile-random �Ƴ����ù�����ʱ������key 
allkeys-random  �Ƴ����key 
volatile-ttl   �Ƴ��������ڵ�key(minor TTL) 
noeviction  ���Ƴ��κ�key��ֻ�Ƿ���һ��д���� ��Ĭ��ѡ��





���Ӹ���

?	slaveof 