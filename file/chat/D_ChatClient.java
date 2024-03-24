
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName: D_ChatClient.java
 * @Description: ����3�������ҿͻ���
 *               -- ���������Ϳͻ��˴��븴�Ƶ�file/chatĿ¼�£����������д�������������з������Ϳͻ��ˣ�ÿ�������д��ڴ���һ���ͻ��ˡ�
 * @Author: anpeng
 * @Date: 2024/3/24 17:00
 */
public class D_ChatClient {

    public static void main(String[] args)throws Exception {
        //1�����ӷ�����
        Socket socket = new Socket("127.0.0.1",1013);

        //2�����������߳�
        //(1)һ���̸߳��𿴱����ģ������շ�����ת������Ϣ
        Receive receive = new Receive(socket);
        receive.start();

        //(2)һ���̸߳������Լ��Ļ�
        Send send = new Send(socket);
        send.start();
        send.join();//���ҷ����߳̽����ˣ��Ž�����������
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
            //���д�ӡ
            PrintStream ps = new PrintStream(outputStream);
            Scanner input = new Scanner(System.in);
            //�Ӽ��̲��ϵ������Լ��Ļ��������������ͣ��ɷ�������������ת��
            while(true){
                System.out.print("�Լ��Ļ��� ");
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
