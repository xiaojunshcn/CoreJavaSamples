-----------------------thread-------------------------------------
1	Producer/Consumer model（jdk 1.4方式）
	多个生产者线程来生产
	多个消费者线程负责消费
	生产出一个就消费掉一个
	
2	Producer/Consumer model（jdk 5.0方式,唤醒所有线程）

3	Producer/Consumer model（jdk 5.0方式,唤醒对方线程）

4	com.joe.concurrent.Singleton 	同步升级

-----------------------collection--------------------------------
5	com.joe.sort.BubbleSort	冒泡排序
6	com.joe.sort.QuickSort	快速排序

7	com.joe.collection.TreeSet	 二叉树方式存数据
	TreeSet本身有排序功能。
	第一种比较方式：实现 Comparable接口
		如果要按自己的需求，比较年龄，需要实现 Comparable接口。
		接口里写具体逻辑
	第二种比较方式：让TreeSet集合自身具备比较性
	
8	CountCharacterTimes
	运用TreeMap 获取字符串中每个字母出现的次数

-------------------------io------------------------------------
	
9	FileWriterDemo
	在硬盘上创建一个文件并写入一些文字数据
	
10	FileReaderDemo		读文件的内容
	FileReaderDemo2		buffer方式读文件的内容
	FileCopyDemo		读文件内容并写到另一文件里。相当于拷贝
	
11	BufferedReaderDemo
	BufferedWriterDemo
	CopyTextByBuf		Buffer方式复制文件
	
12	CopyMP3				运用自定义的缓冲流来读MP3文件
	MyBufferedInputStream	自己写的缓冲类	

13	ReadIn		捕捉键盘录入
	字节流字符流互相转换
	缓冲功能

14	FileDemo		列出指定目录下的文件或文件夹，包括子目录.保存文件目录信息到指定文件中
	
15	PropertiesDemo	
	用于记录应用程序次数。
	如果次数已到，给出注册提示。
	
16	SequenceInputStreamDemo	把多个文件里的内容合并到一个新文件里

17	SpliteFileDemo		按等分切割文件（一个读取流对应多个写文件流）
	并再合并它们	
	
18	ObjectStreamDemo	对象序列化到文件。 	序列化后的文件再被读取到对象里。

19	RandomAccessFileDemo
	DataStreamDemo
	
20	ByteArrayStreamDemo	关闭流后还能操作数据

21	EncodeDemo	字符串和byte数组 编码

流操作规律：
	源    设备：键盘（System.in），硬盘（FileStream），内存（ArrayStream）
	目的设备：控制台(System.out)，硬盘（FileStream），内存（ArrayStream）
	
-----------------------------GUI--------------------------------
22	AwtMyWindowDemo		awt图形化方式列出指定文件夹下的所有文件名

23	AwtMyMenuSample			带菜单的文本编辑器：可以载入文件，也可以保存数据到文件

	$javac -d c:\myclasses Xxxx.java
	$cd myclasses
	$jar -cvf my.jar *.class	不可执行的jar
	
	目录里放一文件：内容： Main-Class: package.Xxxx 必须要有回车
	jar -cvfm my.jar folderName
	
-----------------------------net--------------------------------
24	UdpSender/UdpReceiver	先运行Receiver，后运行Sender才会看到效果。 UDP无所谓谁先启动

25	UdpChatRoomDemo		UDP方式多线程技术聊天程序

26	TcpServer/TcpClient	TCP是面向连接的，所以必须先启动Server端

27	TcpServerClient2	server端接收到数据后向客户端发一个信息

28	TcpServerClient3	客户端发送文本信息给server，server转换成大写返回给client

29	TcpServerClient4	Tcp复制文本文件。客户端发文件给server,发送结束后server发一条信息给client

30	TcpUploadFile		Tcp上传图片

31	TcpConcurrentUploadFile		Tcp并发上传图片

32	TcpConcurrentLogin		Tcp并发登录，client里键盘输入名字，查找user.txt. 存在就允许登录，最多允许三次尝试

33	SelfDefinedServerDemo	服务器端自定义，接收来自浏览器的访问

34	SelfDefinedIE			自定义浏览器去访问已知的tomcat服务

35	URLConnectionDemo		访问本机tomcat上的自定义应用

-----------------------------web--------------------------------
socket program: ServerSocket, Client, Admin Client, Multi-thread, Thread Pool.