
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @ClassName: C_ChatServer.java
 * @Description: ����3�������ҿͻ���
 *               -- ���������Ϳͻ��˴��븴�Ƶ�file/chatĿ¼�£����������д�������������з������Ϳͻ��ˣ�ÿ�������д��ڴ���һ���ͻ��ˡ�
 * @Author: anpeng
 * @Date: 2024/3/24 16:38
 */
public class C_ChatServer {
    //������������洢�������ߵĿͻ���
    static ArrayList<Socket> online = new ArrayList<>();

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {
        //1���������������󶨶˿ں�
        ServerSocket server = new ServerSocket(1013);
        //2������ n ��Ŀͻ���ͬʱ����
        while(true){
            Socket socket = server.accept();
            online.add(socket);//���̸߳���������ӵĿͻ�����ӵ� online �б���
            MessageHandler mh = new MessageHandler(socket);
            mh.start();// ����һ���̴߳���ǰ���ӿͻ�����Ϣת����
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
                //���룺�������ͻ���ת�����������ˡ�
                sendToOther(ip+"������");

                //(1)���ոÿͻ��˵ķ��͵���Ϣ
                InputStream input = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(reader);
                String str;
                while((str = br.readLine())!=null){
                    //(2)���������߿ͻ���ת��
                    sendToOther(ip+":"+str);
                }
                sendToOther(ip+"������");
            } catch (IOException e) {
                try {
                    sendToOther(ip+"������");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }finally{
                //��������Ա���Ƴ���
                online.remove(socket);
            }
        }

        //��װһ���������������ͻ���ת�� xxx ��Ϣ
        public void sendToOther(String message) throws IOException{
            //�������е����߿ͻ��ˣ�һһת��
            for (
            Socket on : online) {
                OutputStream every = on.getOutputStream();
                //Ϊʲô�� PrintStream��Ŀ�������� println ���������д�ӡ
                PrintStream ps = new PrintStream(every);
                ps.println(message);
            }
        }
    }
}
