package java_bean.day02;

/**
 * @ClassName: Animal.java
 * @Author: anpeng
 * @Date: 2023/10/26 15:44
 */
public class Animal {
    private int num = 1; //显式初始化
    private int age;
    private String kind;

    //静态代码块
    static {
        System.out.println("Animal static code block is running...");
    }

    //构造代码块
    {
        System.out.println("Animal construct code block is running..." + num);
    }

    //构造函数
    public Animal(){
        super();//不写也行，默认就有。Animal继承于Object，但是Object随着JVM的启动就加载了。
        //super()执行完后-->显式初始化-->构造代码块初始化
        num = 2;
        System.out.println("Animal constructor code is running..." + num);
        eat(); //调用的是子类Cat中的eat方法，父类的eat方法被重写了。
        System.out.println(this);//java_bean.day02.Cat@4e50df2e 传进来的this是子类对象。
    }
    public void eat(){
        System.out.println("Animal eat food...");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
