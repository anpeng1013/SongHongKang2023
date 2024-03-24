
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @ClassName: C_ChatServer.java
 * @Description: 案例3：聊天室客户端
 *               -- 将服务器和客户端代码复制到file/chat目录下，并用命令行窗口来编译和运行服务器和客户端，每个命令行窗口代表一个客户端。
 * @Author: anpeng
 * @Date: 2024/3/24 16:38
 */
public class C_ChatServer {
    //这个集合用来存储所有在线的客户端
    static ArrayList<Socket> online = new ArrayList<>();

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {
        //1、启动服务器，绑定端口号
        ServerSocket server = new ServerSocket(1013);
        //2、接收 n 多的客户端同时连接
        while(true){
            Socket socket = server.accept();
            online.add(socket);//主线程负责把新连接的客户端添加到 online 列表中
            MessageHandler mh = new MessageHandler(socket);
            mh.start();// 另起一个线程处理当前连接客户的消息转发。
        }
    }

    static class MessageHandler extends Thread{
        private final Socket socket;
        private String ip;
        public MessageHandler(Socket socket) {
            super();
            this.socket = socket;
        }
        public void run(){
            try {
                ip = socket.getInetAddress().getHostAddress();
                //插入：给其他客户端转发“我上线了”
                sendToOther(ip+"上线了");

                //(1)接收该客户端的发送的消息
                InputStream input = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(reader);
                String str;
                while((str = br.readLine())!=null){
                    //(2)给其他在线客户端转发
                    sendToOther(ip+":"+str);
                }
                sendToOther(ip+"下线了");
            } catch (IOException e) {
                try {
                    sendToOther(ip+"掉线了");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }finally{
                //从在线人员中移除我
                online.remove(socket);
            }
        }

        //封装一个方法：给其他客户端转发 xxx 消息
        public void sendToOther(String message) throws IOException{
            //遍历所有的在线客户端，一一转发
            for (
            Socket on : online) {
                OutputStream every = on.getOutputStream();
                //为什么用 PrintStream？目的用它的 println 方法，按行打印
                PrintStream ps = new PrintStream(every);
                ps.println(message);
            }
        }
    }
}
