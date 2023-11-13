package java_bean.day04;

/**
 * @ClassName: Consumer.java
 * @Author: anpeng
 * @Date: 2023/11/12 11:15
 */
@SuppressWarnings("all")
public class Consumer extends Thread{
    private final Clerk clerk;

    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("===========消费者开始消费产品============");
        while(true){
            //消费一个便让店员减少一个
            clerk.minusProduct();
            try {
                Thread.sleep(1000);//停顿。为了便于观察，不会释放锁。
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
