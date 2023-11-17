package java_bean.day04;

/**
 * @ClassName: Clerk.java
 * @Author: anpeng
 * @Date: 2023/11/12 10:46
 */
public class Clerk {
    //资源类--店员
    private int productNum = 0;//产品数量
    private static final int MAX_PRODUCT = 20;
    private static final int MIN_PRODUCT = 1;

    //增加产品
    public synchronized void addProduct() {//生产者调用
        if (productNum < MAX_PRODUCT) {
            productNum++;
            System.out.println(Thread.currentThread().getName() + "生产了第" + productNum + "个产品");
            //唤醒消费者
            this.notifyAll();
        } else {
            try {
                this.wait();//this是clerk锁对象，此时wait是让调用该方法的线程去等待，即生产者线程等待
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //减少产品
    public synchronized void minusProduct(){//消费者调用
        if(productNum >= MIN_PRODUCT){
            System.out.println(Thread.currentThread().getName() + "消费了第" + productNum + "个产品");
            productNum--;
            //唤醒生产者
            this.notifyAll();
        } else {
            try {
                this.wait();//this是clerk锁对象，此时wait是让调用该方法的线程去等待，即消费者线程等待
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
