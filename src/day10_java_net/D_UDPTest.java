package day10_java_net;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * UDP网络编程
 *     UDP(User Datagram Protocol，用户数据报协议)：是一个无连接的传输层协议、提供面向事务的简单不可靠的信息传送服务，类似于短信。
 *
 * 1、通信模型
 *      - UDP 协议是一种面向非连接的协议，面向非连接指的是在正式通信前不必与对方先建立连接，不管对方状态就直接发送，至于对方是否可以接收到这些数据内容，
 *          UDP协议无法控制，因此说，UDP协议是一种不可靠的协议。无连接的好处就是快，省内存空间和流量，因为维护连接需要创建大量的数据结构。
 *
 *      - UDP 会尽最大努力交付数据，但不保证可靠交付，没有 TCP 的确认机制、重传机制，如果因为网络原因没有传送到对方，UDP 也不会给应用层返回错误信息。
 *
 *     - UDP 协议是面向数据报文的信息传送服务。 UDP 在发送端没有缓冲区，对于应用层交付下来的报文在添加了首部之后就直接交付于IP层，不会进行合并，
 *          也不会进行拆分，而是一次交付一个完整的报文。比如我们要发送 100 个字节的报文，我们调用一次 send()方法就会发送 100 字节，接收方也需要
 *          用 receive()方法一次性接收 100 字节，不能使用循环每次获取 10 个字节，获取十次这样的做法。
 *
 *      - UDP 协议没有拥塞控制，所以当网络出现的拥塞不会导致主机发送数据的速率降低。虽然 UDP 的接收端有缓冲区，但是这个缓冲区只负责接收，并不会保证
 *          UDP 报文的到达顺序是否和发送的顺序一致。因为网络传输的时候，由于网络拥塞的存在是很大的可能导致先发的报文比后发的报文晚到达。如果此时缓冲区满了，
 *          后面到达的报文将直接被丢弃。这个对实时应用来说很重要，比如：视频通话、直播等应用。
 *
 *      - 因此 UDP 适用于一次只传送少量数据、对可靠性要求不高的应用环境，数据报大小限制在 64K 以下。
 *
 *      -类 DatagramSocket 和 DatagramPacket 实现了基于 UDP 协议网络程序。
 *          -- UDP 数据报通过数据报套接字 DatagramSocket 发送和接收，系统不保证UDP数据报一定能够安全送到目的地，也不能确定什么时候可以抵达。
 *          -- DatagramPacket 对象封装了 UDP 数据报，在数据报中包含了发送端的 IP 地址和端口号以及接收端的 IP 地址和端口号。
 *          -- UDP 协议中每个数据报都给出了完整的地址信息，因此无须建立发送方和接收方的连接。如同发快递包裹一样。
 *
 * 2、开发步骤
 *      - 发送端程序包含以下四个基本的步骤
 *          -- 创建 DatagramSocket ：默认使用系统随机分配端口号。
 *          -- 创建 DatagramPacket：将要发送的数据用字节数组表示，并指定要发送的数据长度，接收方的 IP 地址和端口号。
 *          -- 调用 该 DatagramSocket 类对象的 send 方法 ：发送数据报 DatagramPacket 对象。
 *          -- 关闭 DatagramSocket 对象：发送端程序结束，关闭通信套接字。
 *
 *      - 接收端程序包含以下四个基本步骤
 *          -- 创建 DatagramSocket ：指定监听的端口号。
 *          -- 创建 DatagramPacket：指定接收数据用的字节数组，起到临时数据缓冲区的效果，并指定最大可以接收的数据长度。
 *          -- 调用 该 DatagramSocket 类对象的 receive 方法 ：接收数据报 DatagramPacket 对象。
 *          -- 关闭 DatagramSocket ：接收端程序结束，关闭通信套接字。
 *
 * 3、演示发送和接收消息
 *      基于 UDP 协议的网络编程仍然需要在通信实例的两端各建立一个 Socket，但这两个 Socket 之间并没有虚拟链路，这两个 Socket 只是发送、接收数据报的对象，
 *      Java 提供了 DatagramSocket 对象作为基于 UDP 协议的 Socket，使用 DatagramPacket 代表 DatagramSocket 发送、接收的数据报。
 *
 * @ClassName: D_UDPTest.java
 * @Description: UDP网络编程
 * @Author: anpeng
 * @Date: 2024/3/24 17:35
 */
@SuppressWarnings("all")
public class D_UDPTest {
    @Test
    public void testSend() throws IOException {
        //创建DatagramSocket
        DatagramSocket ds = new DatagramSocket();

        //创建DatagramPacket
        ArrayList<String> send = new ArrayList<>();
        send.add("anpeng");
        send.add("love");
        send.add("huli");

        //接收放的IP地址和监听端口号
        InetAddress receiveIP = InetAddress.getByName("127.0.0.1");
        int receivePort = 1013;

        //发送数据
        for (int i = 0; i < send.size(); i++) {
            //创建DatagramPacket
            byte[] data = send.get(i).getBytes(StandardCharsets.UTF_8);
            DatagramPacket dp = new DatagramPacket(data, 0, data.length, receiveIP, receivePort);
            //调用socket的发送方法
            ds.send(dp);
        }
        System.out.println("数据发送完毕");

        //关闭socket
        ds.close();
    }

    @Test
    public void testReceive() throws IOException {
        //创建DatagramSocket
        DatagramSocket ds = new DatagramSocket(1013);
        //一直监听数据
        while (true){
            //2、建立数据包 DatagramPacket
            byte[] buffer = new byte[1024*64];//最大UDP数据报为64K
            DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
            //3、调用 Socket 的接收方法
            ds.receive(dp);
            //4、拆封数据
            String str = new String(dp.getData(),0,dp.getLength());
            System.out.println(str);
        }
    }

}
