package day02_javaFoundation;

import static util.CustomUtil.getType;

/**
 * @ClassName: d_DataType.java
 * @Author: anpeng
 * @Date: 2023/3/22 17:36
 *
 * 变量类型分类：
 *      基本数据类型：包括整数类型、 浮点数类型、 字符类型、 布尔类型。(byte、short、int、long、float、double、char、boolean)
 *          整数类型：
 *              byte: 1字节、short2字节、int：4字节、long：8字节
 *              定义long类型的变量，赋值时需要以l或L作为后缀，java的整型常量默认为int型。
 *          浮点类型：
 *              float：4字节，尾数可以精确到7位有效数字。定义float类型的变量，赋值时需要以f或F作为后缀。
 *              double：8字节：精度是float的两倍。java的浮点型常量默认为double型。
 *              注意：并不是所有的小数都能可以精确的用二进制浮点数表示。例如：二进制浮点数不能精确的表示 0.1、 0.01、 0.001 这样 10 的负次幂
 *                  float、double的数据不适合在不容许舍入误差的金融计算领域。如果需要精确数字计算或保留指定位数的精度需要使用BigDecimal类
 *          字符类型：
 *              char型数据用来表示通常意义上的“字符”(占2个字符)。
 *              Java中的所有字符都使用 Unicode 编码，故一个字符可以存储一个字母，一个汉字，或其他书面语的一个字符。
 *              字符型变量的三种表现形式：
 *                  形式1：使用单引号''括起来的单个字符：char c1 = 'a'; char c2 = '中'; char c3 = '9'。
 *                  形式2：直接使用Unicode值来表示字符型常量：\u0023 表示 '#'。\u0023表示十六进制整数。一个十六进制整数占4个比特位。
 *                  形式3：Java中还允许使用转义字符‘\’来将其后的字符转变为特殊字符型常量：char c3 = '\n'; // '\n'表示换行符。
 *          布尔类型
 *              boolean类型用来判断逻辑条件，一般用于流程控制语句中。
 *              boolean类型数据只有两个值： true、 false。
 *              建议不要这样写：if(isFlag==true)，很容易写错成 if(isFlag=true)，
 *                      这样就变成赋值isFlag为true而不是判断！老鸟的写法是if(isFlag)或者if(!isFlag)。
 *          运算规则：
 *              自动类型提升：将取值范围小(或容量小)的类型自动提升为取值范围大(容量大)的类型。
 *              强制类型转换：将取值范围大（或容量大）的类型强制转换成取值范围小（或容量小）的类型。
 *                          float f = (float)12.3; 12.3默认为double类型(大），不能自动转换为float(小)，所以必须强制转换。
 *
 *      引用数据类型：包括字符串、数组、类、接口、枚举、注解、记录
 *          字符串：
 *              1、String使用一对“”来表示一个字符串，内部可以包含0个、1个或多个字符。
 *              2、任意八种基本数据类型的数据与 String 类型只能进行 “+” 连接运算，且结果一定也是 String 类型
 *              3、String 类型不能通过强制类型()转换，转为其他的类型
 */
public class D_DataType {
    /**
     * @Title: main
     * @Description: TODO
     * @Author: anpeng
     * @DateTime: 2023/3/22 18:12
     * @Param args
     * @Return void
     * @Throws
     */
    public static void main(String[] args) {
        testPrecision();
        testDataTypeTransform();
        testString();
    }

    /**
     * @Title: testPrecision
     * @Description: TODO
     * @Author: anpeng
     * @DateTime: 2023/3/23 20:55
     * @Param
     * @Return void
     * @Throws
     */
    static void testPrecision() {
        System.out.println("testFloatPrecision---------------");
        System.out.println(getType(123)); //java的整型常量默认为int型
        System.out.println(getType(2.4)); //java的浮点型常量默认为double型
        System.out.println(0.1 + 0.2);
        float f1 = 123123123f; //查过七位有效数字，超过的部分会被截断。
        float f2 = f1 + 1;
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f1 == f2);
    }

    /**
     * @Title: testDataTypeTransform
     * @Description: TODO
     * @Author: anpeng
     * @DateTime: 2023/4/3 10:35
     * @Param null
     * @Return
     * @Throws
     */
    static void testDataTypeTransform(){
        System.out.println("testDataTypeTransform---------------");
        //自动类型提升：将取值范围小(或容量小)的类型自动提升为取值范围大(容量大)的类型。
        //1、当把存储范围小的值（常量值、变量的值、表达式计算的结果值）赋值给存储范围大的变量时
        int i1 = 'A'; //65 char自动升级为int，其实就是把字符的编码值赋值给 i 变量了
        double d1 = 10; //10.0 int自动升级为double
        long num = 1234567; //1234567 右边的整数常量值如果在int范围，编译和运行都可通过，这里涉及到数据类型转换
        //byte bigB = 130;//编译错误，右边的整数常量值超过 byte 范围
        long bigNum = 12345678912L;//右边的整数常量值如果超过int范围，必须加L,显式表示long类型。否则编译不通过
        System.out.println(i1 + " " + d1 + " " + num + " " + bigNum);

        //2、当存储范围小的数据类型与存储范围大的数据类型变量一起混合运算时，会按照其中最大的类型运算。
        int i2 = 1;
        byte b = 1;
        double d2 = 1.0;
        double sum = i2 + b + d2;//混合运算，升级为 double
        System.out.println(getType(sum) + " " + sum);

        //3、当 byte,short,char 数据类型的变量进行算术和移位运算时，按照 int 类型处理。
        byte b1 = 1;
        byte b2 = 2;
        //byte b3 = b1 + b2; //编译报错， b1 + b2 自动升级为 int
        char c1 = '0';
        char c2 = 'A';
        int i3 = c1 + c2; //至少需要使用 int 类型来接收
        System.out.println(b1+ " "+ b2 + " " + c1 +" "+ c2 + " " + i3); //113



        //强制类型转换：将取值范围大（或容量大）的类型强制转换成取值范围小（或容量小）的类型。
        //1、当把存储范围大的值（常量值、变量的值、表达式计算的结果值）强制转换为存储范围小的变量时，可能会损失精度或溢出。
        int i4 = (int)3.14; //损失精度
        double d3 = 1.2;
        int num1 = (int)d3; //损失精度
        int i5 = 200;
        byte b3 = (byte)i5; //溢出
        System.out.println(i4 + " " + num1 + " " + b3);

        //2、声明 long 类型变量时，可以出现省略后缀的情况。 float 则不同.
        long l1 = 123L;
        long l2 = 123;//如何理解呢？ 此时可以看做是 int 类型的 123 自动类型提升为 long 类型
        // long l3 = 123123123123; //报错，因为 123123123123 超出了 int 的范围。
        long l4 = 123123123123L;
        //float f1 = 12.3; //报错，因为 12.3 看做是 double，不能自动转换为 float类型
        float f2 = 12.3F;
        float f3 = (float)12.3;
        System.out.println(l1 + " " + l2 + " " + l4 + " " + f2 + " " + f3);

        //3、对于byte/short/char三种类型，如果右侧赋值的整型常量没有超过其类型范围，那么javac编译器
        // 会自动补上一个(byte)/(short)/(char)强转，但右侧常量超过范围会报错。若右侧是变量，不管有没有超过范围，都会编译报错。
        // double常量不能直接赋值给float，例如 float f = 12.3 这里12.3是double常量，即使12.3没有超过float范围也会报错。
    }

    /**
     * @Title: testString
     * @Description: TODO
     * @Author: anpeng
     * @DateTime: 2023/4/3 10:57
     * @Param null
     * @Return
     * @Throws
     */
    static void testString(){
        System.out.println("testString--------------------------");
        //1、任意八种基本数据类型的数据与 String 类型只能进行连接“+”运算，且结果一定也是 String 类型
        System.out.println("" + 1 + 2); //12
        int num = 10;
        boolean b1 = true;
        String s1 = "abc";
        String s2 = s1 + num + b1;
        System.out.println(s2);//abc10true
        //String s3 = num + b1 + s1;//编译不通过，因为 int 类型不能与 boolean 运算
        String s4 = num + (b1 + s1);//编译通过
        System.out.println(s4);

        //2、String 类型不能通过强制类型()转换，转为其他的类型
        String str1 = "123";
        //int num1 = (int)str1;//错误的
        int num2 = Integer.parseInt(str1);//正确的，后面才能讲到，借助包装类的方法才能转
        System.out.println(num2);
    }
}

