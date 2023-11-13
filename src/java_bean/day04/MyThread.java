package java_bean.day04;

/**
 * @ClassName: MyThread.java
 * @Author: anpeng
 * @Date: 2023/11/9 17:10
 */
public class MyThread extends Thread{
    //定义指定线程名称的构造方法，即给线程取名
    public MyThread(String name){
        //调用父类的String参数的构造方法，指定线程的名称
        super(name);
    }

    /**
     * @Title: run
     * @Description: 重写run方法，完成该线程的执行逻辑
     * @Author: anpeng
     * @DateTime: 2023/11/9 17:13
     * @Param null
     * @Return 无
     * @Throws 无
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + "：正在执行！" + i);
        }
    }
}
