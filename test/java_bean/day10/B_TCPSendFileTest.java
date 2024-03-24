package java_bean.day10;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: B_TCPSendFileTest.java
 * @Description: 案例2：客户端发送文件给服务器端，服务器端将文件保存在本地。并返回”发送成功“给客户端。
 * @Author: anpeng
 * @Date: 2024/3/24 15:06
 */
@SuppressWarnings("all")
public class B_TCPSendFileTest {
    //客户端
    @Test
    public void testClient() {// 先启动服务器端再启动客户端。
        Socket socket = null;
        FileInputStream fis = null;
        OutputStream os = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //创建一个Socket
            InetAddress inetAddress = InetAddress.getByName("192.168.1.105");//声明服务器端的IP地址
            int port = 1013;//声明服务器端的端口
            socket = new Socket(inetAddress, port);//用服务器端的IP地址和端口号建立TCP套接字连接。

            //准备文件
            fis = new FileInputStream("file/client/huli.jpg");

            //发送文件
            os = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }
            System.out.println("文件发送完毕");

            //客户端表明不再继续发送数据
            socket.shutdownOutput();

            //接收来自服务器端的回执
            is = socket.getInputStream();
            baos = new ByteArrayOutputStream();//内部维护了一个byte[]
            byte[] received = new byte[5];
            while ((len = is.read(received)) != -1){
                baos.write(received, 0, len);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源，所有流对象都要关闭
            try {
                if (socket != null) {
                    socket.close();//关闭外层套接字流时，会自动关闭内层的字节输出流。
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (baos != null) {
                    baos.close();
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
        Socket socket = null;
        FileOutputStream fos = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            //创建一个ServerSocket
            int port = 1013;
            serverSocket = new ServerSocket(port);

            //调用accept，接收客户端的Socket
            socket = serverSocket.accept();//阻塞式等待客户端的连接请求。
            System.out.println("---服务器端已开启---");
            System.out.println("收到了来自于" + socket.getInetAddress().getHostAddress() + "的连接");

            //通过客户端的Socket获取一个输入流
             is = socket.getInputStream();

            //准备保存文件
            fos = new FileOutputStream("file/server/huli.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
               fos.write(buffer, 0, len);
            }
            //当客户端没有关闭Socket或没有表明发送结束的时候，会一直死循环等待客户端的数据。
            System.out.println("数据接收完毕");

            //服务器端发送回执给客户端
            os = socket.getOutputStream();
            os.write("收到了你的图片，非常漂亮！".getBytes(StandardCharsets.UTF_8));
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
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
