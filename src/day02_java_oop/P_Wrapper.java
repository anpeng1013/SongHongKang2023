package day02_java_oop;

/**
 * 为什么需要包装类:
 *      Java提供了两个类型系统， 基本数据类型与引用数据类型。使用基本数据类型在于效率，然而，当使用只针对对象设计的API或新特性（例如泛型）时，
 *      必须将基本数据类包装成引用类型。
 *
 * 具体的包装类：
 *      Java 针对八种基本数据类型定义了相应的引用类型：包装类（封装类）。有了类的特点，就可以调用类中的方法， Java才是真正的面向对象。
 *      基本数据类型              包装类
 *         byte                  Byte
 *         short                 Short
 *         int                   Integer
 *         long                  Long
 *         float                 Float
 *         double                Double
 *         boolean               Boolean
 *         char                  Character
 *      基本数据类型的局部变量存储在栈中，而包装类型的局部变量作为对象存储堆中
 *
 * 包装类与基本数据类型间的转换
 *      装箱：把基本数据类型转为包装类对象。转为包装类的对象，是为了使用专门为对象设计的API和特性。
 *          * 使用构造函数函数：Integer obj1 = new Integer(4); 已过时
 *          * 使用包装类中的valueOf方法：Integer obj2 = Integer.valueOf(4); 已过时
 *      拆箱：把包装对象拆为基本数据类型。转为基本数据类型，一般是因为需要运算，Java中的大多数运算符是为基本数据类型设计的。比较、算术等。
 *          * 使用包装类中的intValue方法：int num1 = obj.intValue();已过时
 *
 *      自动装箱与拆箱：
 *          由于我们经常要做基本类型与包装类之间的转换，从JDK1.5开始，基本类型与包装类的装箱、拆箱动作可以自动完成。
 *              Integer i = 4;//自动装箱。相当于 Integer i = Integer.valueOf(4);
 *              i = i + 5;//等号右边：将i对象转成基本数值(自动拆箱) i.intValue() + 5; 加法运算完成后，再次装箱，把基本数值转成对象。
 *              System.out.println(i.getClass());//class java.lang.Integer
 *          注意：只能与自己对应的类型之间才能实现自动装箱与拆箱。
 *              Integer i = 12;
 *              Double d = 12;//错误的， 12是int类型
 *
 * 基本数据类型、包装类与字符串间的转换
 *      1、基本数据类型转为字符串
 *          方式1：调用字符串重载的valueOf方法
 *              int a = 10;
 *              String str = String.valueOf(a)
 *          方法2：使用加号连接一个空字符串。
 *              String str = a + "";
 *      2、字符串转为基本数据类型
 *          方式1：除Character类之外，其他所有包装类都具有parseXxx静态方法可以将字符串参数转换为对应的基本类型。
 *          方式2：使用包装类的valueOf方法将字符串转为包装类，然后可以自动拆箱为基本数据类型。已过时
 *          方式3：通过包装类的构造器实现。已过时
 *
 * 包装类的其他API
 *      1、数据类型的最大最小值：Integer.MAX_VALUE 和 Integer.MIN_VALUE
 *      2、字符转大小写：Character.toUpperCase('x') 和 Character.toLowerCase('X');
 *      3、整数转进制：Integer.toBinaryString(int i)、Integer.toHexString(int i)和Integer.toOctalString(int i)
 *      4、比较的方法：Double.compare(double d1, double d2)
 *
 * 包装类缓冲对象：（类似于字符串的常量池）
 *     包装类        缓存对象
 *     Byte         -128~127
 *     Short        -128~127
 *     Integer      -128~127
 *     Long         -128~127
 *     Float        没有
 *     Double       没有
 *     Character    0~127
 *     Boolean      true 和 false
 *
 *     包装类对象和字符串一样，属于不可变对象，即一旦修改，就是新对象，和实参无关了。
 *
 * @ClassName: P_Wrapper.java
 * @Author: anpeng
 * @Date: 2023/11/6 11:29
 */
public class P_Wrapper {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        //装箱：把基本数据类型转为包装类对象。转为包装类的对象，是为了使用专门为对象设计的API和特性。JDK1.5以前的用法，已过时。
        //Integer obj1 = new Integer(4);//使用构造函数函数
        //Float f = new Float("4.56");
        //Long l = new Long("abc"); //NumberFormatException
        //Integer obj2 = Integer.valueOf(4);//使用包装类中的valueOf方法

        //装箱：把包装对象拆为转为基本数据类型，一般是因为需要运算，Java中的大多数运算符是为基本数据类型设计的。比较、算术等。
        //int num1 = obj1.intValue();//使用包装类的intValue方法，已过时。

        //自动装箱与拆箱，从JDK1.5开始，基本类型与包装类的装箱、拆箱动作可以自动完成。
        Integer i = 4;//自动装箱。相当于 Integer i = Integer.valueOf(4);
        i = i + 5;//等号右边：将 i 对象转成基本数值(自动拆箱) i.intValue() + 5; 加法运算完成后，再次装箱，把基本数值转成对象。
        System.out.println(i.getClass());//class java.lang.Integer

        //基本数据类型、包装类与字符串间的转换
        //1、基本数据类型转为字符串
        int a = 10;
        String str1 = String.valueOf(a);//方式1：调用字符串重载的valueOf方法
        String str2 = a + "";//方法2：使用加号连接一个空字符串。
        System.out.println(str1 + str2);
        //2、字符串转为基本数据类型
        int b = Integer.parseInt("13");//方式1：除Character之外，其他所有包装类都具有parseXxx静态方法将字符串参数转换为对应的基本类型。
        float f = Float.parseFloat("14");
        System.out.println(b + f);
        //int c = Integer.valueOf("12");//方式2：使用包装类的valueOf方法将字符串转为包装类，然后可以自动拆箱为基本数据类型。已过时
        //int d = new Integer("10");//方式3：通过包装类的构造器实现，已过时。

        //包装类的其他API
        System.out.println(Integer.MAX_VALUE + " " + Integer.MIN_VALUE);
        System.out.println(Character.toUpperCase('x') + "" + Character.toLowerCase('S'));
        System.out.println(Integer.toBinaryString(15) + " " + Integer.toHexString(256));

        //包装类的缓存对象
        Integer a1 = 127;
        Integer a2 = 127;
        System.out.println(a1 == a2);//true
        Integer b1 = 128;
        Integer b2 = 128;
        System.out.println(b1 == b2);//false,缓冲常量对象的范围时是-128~127

        Integer m = new Integer(127);//新new的在堆中
        Integer n = 127;// 这个用的是缓冲的常量对象，在方法区
        System.out.println(m == n);//false

        //包装类对象不可变
        test(n);
        System.out.println(n);//即，包装类对象虽然是引用类型，但通过参数传递不能修改对象的值，类似于值传递。
    }

    public static void test(Integer integer){
        System.out.println(integer);
        integer = 10;
        System.out.println(integer);
    }
}
