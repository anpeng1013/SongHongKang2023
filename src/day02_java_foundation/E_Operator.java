package day02_java_foundation;

/**
 * @ClassName: E_Operator.java
 * @Author: anpeng
 * @Date: 2023/4/3 11:38
 *
 * 运算符分类：
 *      按照功能分为：算术运算符、赋值运算符、比较(或关系)运算符、逻辑运算符、位运算符、条件运算符、 Lambda 运算符
 *          算术运算符（7个）： +、 -、 *、 /、 %、 ++、 --
 *              运算符         运 算                   范 例          结 果
 *                +           正号                    +3              3
 *                -           负号                    b=4;-b         -4
 *                +            加                     5+5            10
 *                -            减                     6-4             2
 *                *            乘                     3*4            12
 *                /            除                     5/2             2
 *                %           取余                    7%5             2
 *                ++     自增（前）：先运算后取值        a=2;b=++a      a=3;b=3
 *                ++     自增（后）：先取值后运算        a=2;b=a++      a=3;b=2
 *                --     自减（前）：先运算后取值        a=2;b=--a      a=1;b=1
 *                --     自减（后）：先取值后运算        a=2;b=a--      a=1;b=2
 *                +         字符串拼接                "an"+10        "an10"
 *
 *          赋值运算符（12个）： =、 +=、 -=、 *=、 /=、 %=、 >>=、 <<=、 >>>=、&=、 |=、 ^=等
 *                =: 支持连续赋值,当“=”两侧数据类型不一致时，可以使用自动类型转换或使用强制类型转换原则进行处理
 *                (运算符)=:都是将左右两边的操作数进行(运算符)运算，再将运算结果赋值给左边操作数。
 *
 *          关系运算符（6个）：>、 >=、 <、 <=、 ==、 !=、instanceof
 *                关系运算符的结果都是 boolean 型，也就是要么是 true，要么是 false。
 *                > < >= <= ：只适用于基本数据类型（除 boolean 类型之外）   == != ：适用于基本数据类型和引用数据类型
 *                instanceof：检查是否为类的对象。
 *
 *          逻辑运算符（6个）：&、 |、 ^、 !、 &&、 ||
 *                逻辑运算符，操作的都是 boolean 类型的变量或常量，而且运算得结果也是 boolean 类型的值。
 *                说明： & 和 &&：表示"且"关系，当符号左右两边布尔值都是 true 时，结果才能为 true。否则，为 false。
 *                      | 和 || ：表示"或"关系，当符号两边布尔值有一边为 true 时，结果为true。当两边都为 false 时，结果为 false
 *                      ! ：表示"非"关系，当变量布尔值为 true 时，结果为 false。当变量布尔值为 false 时，结果为 true。
 *                      ^ ：当符号左右两边布尔值不同时，结果为 true。当两边布尔值相同时，结果为 false。理解： 异或，追求的是“异”！
 *                      逻辑运算符用于连接布尔型表达式，在 Java 中不可以写成 3 < x < 6，应该写成 x > 3 & x < 6 。
 *                区分“&”和“&&”：
 *                      相同点：如果符号左边是 true，则二者都执行符号右边的操作
 *                      不同点： & ： 如果符号左边是 false,则继续执行符号右边的操作
 *                              && ：如果符号左边是 false,则不再继续执行符号右边的操作
 *                      建议：开发中，推荐使用 &&
 *                区分“|”和“||”：
 *                      相同点：如果符号左边是 false，则二者都执行符号右边的操作
 *                      不同点： | ： 如果符号左边是 true，则继续执行符号右边的操作
 *                              || ：如果符号左边是 true，则不再继续执行符号右边的操作
 *                      建议：开发中，推荐使用 ||
 *
 *          位运算符（7个）：&、 |、 ^、 ~、 <<、 >>、 >>>
 *                <<：左移，右边空位补0，被移除的高位直接丢弃，移位结果可正可负。（相当于逻辑左移）
 *                >>：右移，最高位，移1补1，移0补0。右移一位相当于除2，如果不能整除则向下取整。（相当于算术右移）
 *                >>>：右移，最高位无论是0还是1，空缺位都用0补。（相当于逻辑右移）
 *                  &：各二进制位进行逻辑与
 *                  |：各二进制位进行逻辑或
 *                  ~：二进制位按补码各位取反
 *                  ^：各二进制位进行逻辑异或。
 *
 *          条件运算符（1个）：(条件表达式)?结果 1:结果 2
 *          Lambda 运算符（1个）：->
 *
 *      按照操作个数分为：一元运算符（单目运算符）、二元运算符（双目运算符）、三元运算符 （三目运算符）
 *          一元运算符（单目运算符）： 正号（+）、负号（-）、 ++、 --、 !、 ~
 *          二元运算符（双目运算符）： 除了一元和三元运算符剩下的都是二元运算符
 *          三元运算符（三目运算符）： (条件表达式)?结果 1:结果 2
 *
 */
public class E_Operator {
    public static void main(String[] args) {
        testArithmeticOperator();
        testSetValueOperator();
        testMoveBitOperator();
        testConditionOperator();
    }

    private static void testConditionOperator() {
        String[] array = {"anpeng", "huli" , "love"};
        for (int i = 0; i < 2; i++) {
            System.out.println(array[i].equals("huli") ? "find it" : array[i]);
        }
    }

    static void testArithmeticOperator(){
        System.out.println("testArithmetic-------------------");
        int a = 3;
        int b = 4;
        System.out.println(a + b); // 7
        System.out.println(a - b); // -1
        System.out.println(a * b); // 12
        System.out.println(a / (double)b); // 因为 / 是整除，如果不强制转换成double，那么 3/4 永远都是0
        System.out.println(a % b); // 3

        //余数符号与被模数符号相同
        System.out.println(5%2); //1
        System.out.println(5%-2); //1
        System.out.println(-5%2); //-1
        System.out.println(-5%-2); //-1
        //商*除数 + 余数 = 被除数
        //5%-2 ==>商是-2，余数时 1 (-2)*(-2)+1 = 5
        //-5%2 ==>商是-2，余数是-1 (-2)*2+(-1) = -4-1=-5

    }

    static void testSetValueOperator(){
        System.out.println("testSetValue----------------");
        long l1 = 10; //自动类型提升
        byte bb1 = (byte)l1; //强制类型缩减
        System.out.println(l1 + " " + bb1);

        //连续赋值的测试
        //以前的写法
        int a1 = 10;
        int b1 = 10;
        System.out.println(a1 + " " + b1);
        //连续赋值的写法
        int a2, b2;
        a2 = b2 = 10;
        System.out.println(a2 + " " + b2);

        //练习：开发中，如何实现一个变量+2 的操作呢？
        // += 的操作不会改变变量本身的数据类型。其他拓展运算符也如此。
        //写法 1：推荐
        short s1 = 10;
        s1 += 2; //编译通过，因为在得到 int 类型的结果后， JVM 自动完成一步强制类型转换，将 int 类型强转成 short
        System.out.println(s1);//12
        //写法 2：
        short s2 = 18;
        //s2 = s2 + 2;//编译报错，因为将 int 类型的结果赋值给 short 类型的变量 s 时，可能损失精度
        s2 = (short)(s2 + 2);
        System.out.println(s2);
    }

    static void testMoveBitOperator(){
        byte b = -9;
        System.out.println(b << 4);  //-144 char,byte,short在运算时，会自动按照int类型处理

    }
}
