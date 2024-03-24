package java_bean.day10;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName: D_ChatClient.java
 * @Description: 案例3：聊天室客户端
 *               -- 将服务器和客户端代码复制到file/chat目录下，并用命令行窗口来编译和运行服务器和客户端，每个命令行窗口代表一个客户端。
 *               -- 编译前要将Java代码改为GBK编码，否则会乱码，编译失败。
 * @Author: anpeng
 * @Date: 2024/3/24 17:00
 */
public class D_ChatClient {

    public static void main(String[] args)throws Exception {
        //1、连接服务器
        Socket socket = new Socket("127.0.0.1",1013);

        //2、开启两个线程
        //(1)一个线程负责看别人聊，即接收服务器转发的消息
        Receive receive = new Receive(socket);
        receive.start();

        //(2)一个线程负责发送自己的话
        Send send = new Send(socket);
        send.start();
        send.join();//等我发送线程结束了，才结束整个程序
        socket.close();
    }
}

class Send extends Thread{
    private final Socket socket;
    public Send(Socket socket) {
        super();
        this.socket = socket;
    }
    public void run(){
        try {
            OutputStream outputStream = socket.getOutputStream();
            //按行打印
            PrintStream ps = new PrintStream(outputStream);
            Scanner input = new Scanner(System.in);
            //从键盘不断的输入自己的话，给服务器发送，由服务器给其他人转发
            while(true){
                System.out.print("自己的话： ");
                String str = input.nextLine();
                if("bye".equals(str)){
                    break;
                }
                ps.println(str);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Receive extends Thread{
    private final Socket socket;
    public Receive(Socket socket) {
        super();
        this.socket = socket;
    }
    public void run(){
        try {
            InputStream inputStream = socket.getInputStream();
            Scanner input = new Scanner(inputStream);
            while(input.hasNextLine()){
                String line = input.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
