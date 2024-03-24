package java_bean.day10;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: A_TCPSendReceiveTest.java
 * @Description: 案例1：客户端发送内容给服务器，服务器端将内容打印到控制台上。
 * @Author: anpeng
 * @Date: 2024/3/24 11:02
 */
@SuppressWarnings("all")
public class A_TCPSendReceiveTest {

    //客户端
    @Test
    public void testClient() {// 先启动服务器端再启动客户端。
        Socket socket = null;
        try {
            //创建一个Socket
            InetAddress inetAddress = InetAddress.getByName("192.168.1.105");//声明服务器端的IP地址
            int port = 1013;//声明服务器端的端口
            socket = new Socket(inetAddress, port);//用服务器端的IP地址和端口号建立TCP套接字连接。

            //发送数据
            OutputStream os= socket.getOutputStream();
            os.write("你好，我是客户端，请多多关照".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (socket != null) {
                    socket.close();//关闭外层套接字流时，会自动关闭内层的字节输出流。
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //服务器端
    @Test
    public void testServer(){ // 先启动服务器端再启动客户端。
        ServerSocket serverSocket = null;
        Socket socket = null;//阻塞式等待客户端的连接请求
        try {
            //创建一个ServerSocket
            int port = 1013;
            serverSocket = new ServerSocket(port);

            //调用accept，接收客户端的Socket
            socket = serverSocket.accept();
            System.out.println("---服务器端已开启---");
            System.out.println("收到了来自于" + socket.getInetAddress().getHostAddress() + "的连接");

            //接收数据
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[5];
            int len;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//内部维护一个byte[]
            while ((len = is.read(buffer)) != -1){
                //System.out.println(new String(buffer, 0, len));//错误的，当buffer数组较小时，可能会出现乱码
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
            System.out.println("数据传输完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
