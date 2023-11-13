package java_bean.day04;

/**
 * @ClassName: HungrySingle.java
 * @Author: anpeng
 * @Date: 2023/11/11 15:55
 */
@SuppressWarnings("all")
public class HungrySingle {
    private static HungrySingle INSTANCE = new HungrySingle();//对象是否声明为final都可以

    private HungrySingle(){}

    public static HungrySingle getInstance(){
        return INSTANCE;
    }

    //可以通过只有一个常量对象的枚举类实现懒汉式单例模式
    /*
    public enum HungryOne{
       INSTANCE
    }
     */

}
