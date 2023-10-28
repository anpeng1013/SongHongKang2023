package java_bean.day03;

/**
 * @ClassName: Cat.java
 * @Author: anpeng
 * @Date: 2023/10/26 17:09
 */
public class Cat extends Animal{
     private int id = 3; //显示初始化

    //静态代码块
    static {
        System.out.println("Cat static code block is running...");
    }

    //构造代码块
    {
        System.out.println("Cat construct code block is running..." + id);
    }

    public Cat(){
        super();//默认就有，可以不写。super初始化父类属性时，子类的成员变量并未初始化。等super()将父类初始化后，再进行子类的显式初始化。
        //super()执行完后-->显示初始化-->构造代码块初始化。
        id = 4;
        System.out.println("Cat constructor code is running..." + id);
        eat(); //调用的是子类Cat中的eat方法，父类的eat方法被重写了。
    }

    @Override
    public void eat(){
        System.out.println("Cat eat fish...");
    }

    public void getInfo(){
        System.out.println("this is a " + getKind() + " and " + getAge() + "years old");
    }
}
