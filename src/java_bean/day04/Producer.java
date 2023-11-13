package java_bean.day04;

/**
 * @ClassName: Producer.java
 * @Author: anpeng
 * @Date: 2023/11/12 10:43
 */
@SuppressWarnings("all")
public class Producer extends Thread{
    //生产者
    private final Clerk clerk;

    public Producer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("===========生产者开始生产产品===========");
        while (true){
            try {
                Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //生产一个便让店员增加一个
            clerk.addProduct();
        }
    }
}
