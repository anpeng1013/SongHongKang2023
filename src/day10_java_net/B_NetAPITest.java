package day10_java_net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 1、InetAddress类
 *      - InetAddress 类主要表示 IP 地址，两个子类： Inet4Address、 Inet6Address。
 *
 *      - InetAddress 类没有提供公共的构造器，而是提供了如下几个静态方法来获取 InetAddress 实例：
 *          -- public static InetAddress getLocalHost()
 *          -- public static InetAddress getByName(String host)
 *          -- public static InetAddress getByAddress(byte[] addr)
 *
 *      - InetAddress 类的常用方法：
 *          -- public String getHostAddress() ：返回 IP 地址字符串（以文本表现形式）
 *          -- public String getHostName() ：获取此 IP 地址的主机名
 *          -- public boolean isReachable(int timeout)：测试是否可以达到该地址
 *
 * 2、Socket类
 *      - 总结：
 *          -- 网络上具有唯一标识的 IP 地址和端口号组合在一起构成唯一能识别通信两端应用程序的标识符套接字（Socket）。
 *          -- 利用套接字(Socket)开发网络应用程序早已被广泛的采用，以至于成为事实上的标准。网络通信其实就是 Socket 间的通信。
 *          -- 通信的两端都要有 Socket，是两台机器间通信的端点。
 *          -- Socket 允许程序把网络连接当成一个流，数据在两个 Socket 间通过 IO 传输。
 *          -- 一般主动发起通信的应用程序属客户端，等待通信请求的为服务端。
 *
 *      - 分类：
 *          -- 流套接字（stream socket）：使用 TCP 提供可依赖的字节流服务。
 *              > ServerSocket：此类实现 TCP 服务器套接字。服务器套接字等待请求通过网络传入。
 *              > Socket：此类实现TCP 客户端套接字（也可以就叫“套接字”）。套接字是两台机器间通信的端点。
 *          -- 数据报套接字（datagram socket）：使用 UDP 提供“尽力而为”的数据报服务。
 *              > DatagramSocket：此类表示用来发送和接收 UDP 数据报包的套接字。
 *
 * 3、Socket相关类的API
 *      - ServerSocket类（TCP服务器）
 *          -- 构造器：
 *              > ServerSocket(int port) ：创建绑定到特定端口的服务器套接字。
 *          -- 常用方法：
 *              > Socket accept()：侦听并接受到此套接字的连接。
 *
 *      - Socket类（TCP客户端）
 *          -- 构造器：
 *              > public Socket(InetAddress address, int port)：创建一个流套接字，并将其连接到服务器的IP地址的指定端口号。
 *              > public Socket(String host,int port)：创建一个流套接字，并将其连接到指定服务器的指定端口号。
 *          -- 常用方法：
 *              > public InputStream getInputStream()：返回此套接字的输入流，可以用于接收消息
 *              > public OutputStream getOutputStream()：返回此套接字的输出流，可以用于发送消息
 *              > public InetAddress getInetAddress()：此套接字连接到的远程 IP 地址；如果套接字是未连接的，则返回 null。
 *              > public InetAddress getLocalAddress()：获取套接字绑定的本地地址。
 *              > public int getPort()：此套接字连接到的远程端口号；如果尚未连接套接字，则返回0。
 *              > public int getLocalPort()：返回此套接字绑定到的本地端口。如果尚未绑定套接字，则返回 -1。
 *              > public void close()：关闭此套接字。套接字被关闭后，便不可在以后的网络连接中使用（即无法重新连接或重新绑定）。需要创建新的套接字对象。
 *                  关闭此套接字也将会关闭该套接字的 InputStream 和 OutputStream。
 *              > public void shutdownInput()：如果在套接字上调用 shutdownInput() 后从套接字输入流读取内容，则流将返回 EOF（文件结束符）。
 *                  即不能在从此套接字的输入流中接收任何数据。
 *              > public void shutdownOutput()：禁用此套接字的输出流。对于TCP套接字，任何以前写入的数据都将被发送，并且后跟TCP的正常连接终止序列。
 *                  如果在套接字上调用 shutdownOutput() 后写入套接字输出流，则该流将抛出 IOException。 即不能通过此套接字的输出流发送任何数据。
 *              注意：先后调用 Socket 的 shutdownInput()和 shutdownOutput()方法，仅仅关闭了输入流和输出流，并不等于调用 Socket 的 close()方法。
 *              在通信结束后，仍然要调用 Scoket 的 close()方法，因为只有该方法才会释放 Socket 占用的资源，比如占用的本地端口号等。
 *
 *      - DatagramSocket类（UDP收发两端）
 *          -- 构造器：
 *              > public DatagramSocket(int port)：创建数据报套接字并将其绑定到本地主机上的指定端口。套接字将被绑定到通配符地址， IP 地址由内核来选择。
 *              > public DatagramSocket(int port, InetAddress laddr)：创建数据报套接字，将其绑定到指定的本地地址。本地端口必须在 0 到 65535 之间
 *                  （包括两者）。如果 IP 地址为0.0.0.0，套接字将被绑定到通配符地址， IP 地址由内核选择。
 *          -- 常用方法：
 *              > public void close(): 关闭此数据报套接字。
 *              > public void send(DatagramPacket p): 从此套接字发送数据报包。DatagramPacket包含的信息：将要发送的数据、其长度、远程主机的 IP 地址
 *                  和远程主机的端口号。
 *              > public void receive(DatagramPacket p)：从此套接字接收数据报包。当此方法返回时，DatagramPacket 的缓冲区填充了接收的数据。
 *                  数据报包也包含发送方的IP地址和发送方机器上的端口号。 此方法在接收到数据报前一直阻塞。数据报包对象的length字段包含所接收信息的长度。
 *                  如果信息比包的长度长，该信息将被截短。
 *              > public InetAddress getLocalAddress(): 获取套接字绑定的本地地址。
 *              > public int getLocalPort()：返回此套接字绑定的本地主机上的端口号。
 *              > public InetAddress getInetAddress()：返回此套接字对方的地址。如果套接字未连接，则返回 null。
 *              > public int getPort(): 返回此套接字对方的端口。如果套接字未连接，则返回 -1。
 *
 *      - DatagramPacket类（UDP数据报）
 *          -- 构造器：
 *              > public DatagramPacket(byte[] buf,int len): 构造DatagramPacket，用来接收长度为len的数据包。length参数必须小于等于buf.length。
 *              > public DatagramPacket(byte[] buf,int len,InetAddress addr,int port): 构造数据报，用来将长度为len的数据报发送到指定主机上
 *                  的指定端口号。len参数必须小于等于 buf.length。
 *          -- 常用方法：
 *              > public InetAddress getAddress()：返回某台机器的 IP 地址，此数据报将要发往该机器或者是从该机器接收到的。
 *              > public int getPort()：返回某台远程主机的端口号，此数据报将要发往该主机或者是从该主机接收到的。
 *              > public byte[] getData(): 返回数据缓冲区。接收到的或将要发送的数据从缓冲区中的偏移量 offset 处开始，持续 length 长度。
 *              > public int getLength()返回将要发送或接收到的数据的长度。
 *
 *
 * @ClassName: B_NetAPITest.java
 * @Author: anpeng
 * @Date: 2024/3/23 21:59
 */
@SuppressWarnings("all")
public class B_NetAPITest {
    @Test
    public void testInetAddress() throws UnknownHostException {
        InetAddress localHost1 = InetAddress.getLocalHost();
        InetAddress baidu =InetAddress.getByName("www.baidu.com");
        byte[] host = {(byte) 192, (byte) 168, 1, 105};
        InetAddress localHost2 = InetAddress.getByAddress(host);
        System.out.println("localHost1 = " + localHost1); // localHost1 = anpeng/192.168.184.1
        System.out.println("baidu = " + baidu); // baidu = www.baidu.com/183.2.172.185
        System.out.println("localHost2 = " + localHost2); // localHost2 = /192.168.1.105
    }

}
