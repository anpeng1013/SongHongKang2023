package day03_java_oop;

import java_bean.day03.*;

import java.util.Arrays;
import java.util.Scanner;

/**
 * enum关键字：
 *      和class关键字一样，枚举类型本质上也是一种类，只不过是这个类的对象是有限的、固定的几个，不能让用户随意创建。
 *
 *      * 枚举例子：
 *              星期： Monday、Tuesday、Wednesday、Thursday、Friday、Saturday、Sunday
 *              性别： Man、 Woman
 *              月份： January、February、March、April、May、June、July、August、September、October、November、December
 *              季节： Spring、Summer、Autumn、Winter
 *            三原色:  Red、green、blue
 *          支付方式:  Cash、WeChatPay、AliPay、BankCard、CreditCard
 *          就职状态:  Busy、Free、Vocation(休假)、Demission(离职)
 *          订单状态： Nonpayment(未付款)、Paid(已付款)、Fulfilled(已配货)、Delivered(已发货)、Checked(确认收货)、Return(退货)
 *                    Exchange(换货)、Cancel(取消)
 *          线程状态： Create(创建)、Ready(就绪)、Run(运行)、Block(阻塞)、dead(死亡)
 *
 *      * 若枚举只有一个对象，则可以作为一种单例模式的实现方式。
 *
 *      * 枚举类的实现：
 *          -- 在JDK1.5之前，需要程序员自定义枚举类
 *          -- 在JDK1.5之后，Java支持enum关键字来快速定义枚举类型
 *
 * 定义枚举类：
 *      JDK1.5之前:
 *          1、私有化类的构造器，保证不能在类的外部创建其对象
 *          2、在类的内部创建枚举类的实例。声明为：public static final，对外暴露这些常量对象。
 *          3、对象如果有实例变量，应该声明为private final，并在构造器中初始化。
 *
 *      JDK1.5之后：
 *          [修饰符] enum 枚举类名{
 *              常量对象列表
 *          }
 *
 *          [修饰符] enum 枚举类名{
 *              常量对象列表;
 *              对象的实例变量;
 *          }
 *          注意：
 *              * 枚举类的列表必须在枚举类的首行，因为是常量，所以建议大写。系统会自动添加public static final修饰。
 *              * 如果常量对象列表后面没有其他代码，那么分号;可以省略，否则不可以省略分号;。
 *              * 编译器给枚举类默认提供的是private的无参构造，如果枚举类需要的是无参构造，就不需要声明，写常量对象列表时也不用加参数。
 *              * 如果枚举类需要的是有参构造，需要手动定义，有参构造的private可以省略，调用有参构造的方法就是在常量对象列表中，
 *                  常量对象名后面加(实参列表)，这里省略了new。
 *              * 枚举类默认继承的是 java.lang.Enum 类，因此不能再继承其他的类型。
 *              * JDK5.0之后switch，提供支持枚举类型，case后面可以写枚举常量名，无需添加枚举类作为限定。
 *              * 开发中，当需要定义一组常量时，强烈建议使用枚举类。
 *
 * enum中常用方法：
 *      * String toString(): 默认返回的是常量名（对象名），可以继续手动重写该方法！
 *      * static 枚举类型[] values():返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值，是一个静态方法。
 *      * static 枚举类型 valueOf(String name)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。
 *               如不是，会有运行时异常： IllegalArgumentException。
 *      * String name():得到当前枚举常量的名称。建议优先使用 toString()。
 *      * int ordinal():返回当前枚举常量的次序号，默认从 0 开始。
 *
 * 实现接口的枚举类：
 *      * 和普通 Java 类一样，枚举类可以实现一个或多个接口。
 *      * 若每个枚举值(对象)在调用实现的接口方法呈现相同的行为方式，则只要统一实现该方法即可。
 *      * 若需要每个枚举值在调用实现的接口方法呈现出不同的行为方式，则可以让每个枚举值分别来实现该方法
 *      语法格式：
 *          1、枚举类可以像普通的类一样，实现接口，并且可以多个，但要求必须实现里面所有的抽象方法！
 *          enum A implements 接口1，接口2{
 *              //抽象方法的实现
 *          }
 *
 *          2、枚举类的常量可以继续重写抽象方法!
 *          enum A implements 接口 1，接口 2{
 *              常量名 1(参数){
 *                  //抽象方法的实现或重写
 *              },
 *              常量名 2(参数){
 *                  //抽象方法的实现或重写
 *              },
 *              //...
 *          }
 *
 *
 * @ClassName: N_Enumeration.java
 * @Author: anpeng
 * @Date: 2023/11/3 22:05
 */
public class N_Enumeration {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        System.out.println("jdk1.5之前-----------");
        System.out.println(Season.SPRING);

        System.out.println("jdk1.5之后-----------");
        System.out.println(Arrays.toString(Week.values()));//无参构造
        System.out.println(SeasonEnum.SPRING.getSeasonDesc());//有参构造
        //switch中支持枚举
        PrimaryColor primaryColor = PrimaryColor.RED;
        System.out.println(primaryColor);
        switch (primaryColor){
            case RED -> System.out.println("red 可以用来画红旗。");
            case GREEN -> System.out.println("green 可以用来画树叶");
            case BLUE -> System.out.println("blue 可以用来画天空");
        }
        //enum枚举中常用方法
        PrimaryColor[] primaryColors = PrimaryColor.values();
        for (int i = 0; i < primaryColors.length; i++) {
            System.out.println((primaryColors[i].ordinal()+1) + "->" + primaryColors[i].name());
        }
        System.out.println("--------valueOf()-------");
        Scanner input = new Scanner(System.in);
        System.out.println("请输入颜色值：");
        int colorValue = input.nextInt();
        PrimaryColor primaryColor1 = primaryColors[colorValue-1];
        //toString()
        System.out.println(primaryColor1);
        System.out.println("请输入颜色：");
        String colorName = input.next();//只能输入已有的常量对象名，不然会有运行时异常
        //valueOf()
        primaryColor = PrimaryColor.valueOf(colorName);
        System.out.println(primaryColor);
        input.close();
        //枚举类实现接口
       Season1.SUMMER.show();//不同实现
       Season1.SPRING.info();//统一实现
    }
}
