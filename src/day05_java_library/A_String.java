package day05_java_library;

import java_bean.day02.Person;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 字符串相关类之不可变字符序列： String
 *      String的基本概念：
 *          * java.lang.String类代表字符串。Java程序中所有的字符串文字（例如"hello"）都可以看作是实现此类的实例。
 *          * 字符串是常量，用双引号引起来表示。它们的值在创建之后不能更改。
 *          * 字符串String类型本身是final声明的，意味着不能继承String。
 *          * String对象的字符串内容是存储在一个字节数组value中的。-- private final byte[] value;（JDK8版本为字符数组）
 *              - private 意味着外面无法直接获取字符数组，但可以通过getBytes方法获取String对象的经过指定字符集解码的字节数组。
 *              - final 意味着字符数组的引用不可改变，即只能指向一个字节数组。而且String也没有提供方法来修改value数组某个元素值。
 *              - 因此字符串的字符数组内容也不可变的，即 String 代表着不可变的字符序列。即，一旦对字符串进行修改，就会产生新对象。
 *          * Java 语言提供对字符串进行串联的符号（"+"）以及将其他对象转换为字符串的特殊支持（toString()方法）。
 *
 *      String的内存结构：
 *          * 字符串字面常量在常量池中；new出来的String对象在堆中，该对象中的value字节数组指向的是常量池中的一个常量。
 *          * JDK6以前，字符串常量池在方法区[放类信息]。JDK7以后，字符串常量池在堆区。
 *          * 常量池中的字面量对象不会重复。
 *
 *      String的拼接：
 *          * String s1 = "hello"，其中s1是变量，"hello"是常量。
 *          * 常量+常量：结果在常量池。且常量池中不会存在相同内容的常量。
 *          * 常量+变量 或 变量+变量：结果在堆中
 *          * 拼接后调用intern方法：返回值在常量池中。
 *          * concat方法拼接，哪怕是两个常量对象拼接，结果也是在堆。
 *
 *      String的构造器
 *          方式1：直接赋值 String str1 = "anpeng" --- str1 指向常量池中字符串常量对象
 *
 *          方式2：调用构造器
 *              * public String()：初始化新创建的String对象，以使其表示空字符序列，相当于String str3=""，而不是 String str3=null。
 *              * String(String original)：初始化一个新创建的String对象，使其表示一个与参数相同的字符序列。
 *              * public String(char[] value) ：通过当前参数中的字符数组来构造新的String。
 *              * public String(char[] value,int offset, int count) ：通过字符数组的一部分来构造新的String。
 *              * public String(byte[] bytes) ：通过使用平台的默认字符集解码当前参数中的字节数组来构造新的String。
 *              * public String(byte[] bytes,String charsetName)：通过使用指定的字符集解码当前参数中的字节数组来构造新的String。
 *
 *      String与其他数据类型的转换：
 *          1、基本数据类型 --> 字符串
 *             方式1：调用字符串重载的valueOf方法
 *                 int a = 10;
 *                 String str = String.valueOf(a)
 *             方法2：使用加号连接一个空字符串。
 *                 String str = a + "";
 *          2、字符串 --> 基本数据类型
 *             方式1：除Character类之外，其他所有包装类都具有parseXxx静态方法可以将字符串参数转换为对应的基本类型。
 *             方式2：使用包装类的valueOf方法将字符串转为包装类，然后可以自动拆箱为基本数据类型。已过时
 *             方式3：通过包装类的构造器实现。已过时
 *
 *          3、字符数组 --> 字符串
 *              方式1：String的构造器String(char[] arr)用字符数组的全部字符创建字符串。
 *              方式2：String的构造器String(char[] arr, int offset, int length)用字符数组的部分字符创建字符串。
 *          4、字符串 --> 字符数组
 *              方式1：public char[] toCharArray()：将字符串中的全部字符存放在一个字符数组中的方法
 *              方式2：public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)：提供了将指定索引范围内的字符串
 *                                                                                              存放到目标字符数组中的方法。
 *
 *          5、字符串 --> 字节数组（编码）
 *              方式1：public byte[] getBytes()：使用平台的默认字符集将此String编码为byte序列，并将结果存储到一个新的byte数组中。
 *              方式2：public byte[] getBytes(String charsetName)：使用指定的字符集将此String编码到byte序列，并将结果存储到新的byte数组。
 *          6、字节数组 --> 字符串（解码）
 *              方式1：String(byte[])：通过构造器使用平台的默认字符集解码指定的byte数组，构造一个新的String。
 *              方式2：String(byte[], int offset, int length)：用指定的字节数组的一部分，即从数组起始位置offset开始取
 *                                                           length个字节构造一个字符串对象。
 *              方式3：String(byte[], String charsetName)或String(byte[], int, int, String charsetName)：按照指定的编码方式进行解码。
 *
 *      String的API
 *          常用方法：
 *              String toLowerCase()：将字符串中大写字母转为小写       String toUpperCase()：将字符串中小写字母转为大写
 *              boolean isEmpty()：字符串是否为空                    int length()：返回字符串的长度
 *              String concat(xx)：拼接                             boolean equals(Object obj)：比较字符串是否相等，区分大小写
 *              String trim()：去掉字符串前后空白符                   String intern()：结果在常量池中共享
 *              boolean equalsIgnoreCase(Object obj)：比较字符串是否相等，不区分大小写
 *              int compareTo(String other)：比较字符串大小，区分大小写，按照Unicode编码值比较大小
 *              int compareToIgnoreCase(String other)：比较字符串大小，不区分大小写
 *
 *          查找：
 *              boolean contains(xx)：是否包含xx。
 *              int indexOf(xx)：从前往后找当前字符串中xx，即如果有返回第一次出现的下标，要是没有返回-1。
 *              int indexOf(String str, int fromIndex)：返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始。
 *              int lastIndexOf(xx)：从后往前找当前字符串中xx，即如果有返回最后一次出现的下标，要是没有返回-1。
 *              int lastIndexOf(String str, int fromIndex)：返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索。
 *
 *          截取：
 *              String substring(int beginIndex)：返回一个新的字符串，它是此字符串的从 beginIndex 开始截取到最后的一个子字符串。
 *              String substring(int beginIndex, int endIndex)：返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)
 *                                                              的一个子字符串。
 *
 *          和字符/字符数组相关：
 *              char charAt(index)：返回[index]位置的字符。
 *              char[] toCharArray()：将此字符串转换为一个新的字符数组返回。
 *              static String valueOf(char[] data)：返回指定数组中表示该字符序列的 String。
 *              static String copyValueOf(char[] data)：返回指定数组中表示该字符序列的 String。
 *              static String valueOf(char[] data, int offset, int count)：返回指定数组中表示该字符序列的String，从指定位置开始。
 *              static String copyValueOf(char[] data, int offset, int count)：返回指定数组中表示该字符序列的String，从指定位置开始。
 *
 *          开头与结尾：
 *              boolean startsWith(xx)：测试此字符串是否以指定的前缀开始。
 *              boolean startsWith(String prefix, int offset)：测试此字符串从指定索引开始的子字符串是否以指定前缀开始。
 *              boolean endsWith(xx)：测试此字符串是否以指定的后缀结束。
 *
 *          替换：
 *              String replace(char oldChar, char newChar)：返回一个通过用newChar替换此字符串中出现的所有oldChar得到的新字符串。不支持正则。
 *              String replace(CharSequence target, CharSequence replacement)：使用指定的字面值替换序列替换此字符串所有匹配
 *                                                                              字面值目标序列的子字符串。
 *              String replaceAll(String regex, String replacement)：使用给定的replacement替换此字符串中所有匹配给定的正则表达式的子串。
 *              String replaceFirst(String regex, String replacement)：使用给定的replacement替换此字符串中匹配给定的正则表达式的
 *                                                                      第一个子字符串。
 *
 *      String的算法题：
 *          题目1、模拟一个 trim 方法，去除字符串两端的空格。
 *          题目2、将一个字符串进行反转。将字符串中指定部分进行反转。比如："abcdefg"反转为"abfedcg"--> 其中cdef反转为fedc
 *          题目3、获取一个字符串在另一个字符串中出现的次数。 比如：获取"ab"在"abkkcadkabkebfkabkskab"中出现的次数。
 *          题目4、获取两个字符串中最大相同子串。（有一个或多个最大相同子串）滑动窗口原理
 *          题目5、对字符串中字符进行自然排序。
 *              提示：
 *                  1、字符串变字符数组（toCharArray）；
 *                  2、对字符数组进行排序（Arrays.sort）；
 *                  3、字符数组变回字符串（构造器new String(arr)）
 *
 * 字符串相关类之可变字符序列： StringBuffer  和 StringBuilder
 *      String对象是不可变对象，虽然可以共享常量对象，但是对于频繁字符串的修改和拼接操作，都会创建新的常量对象，效率极低，空间消耗也比较高。
 *      因此，JDK又在java.lang包提供了可变字符序列 StringBuffer 和 StringBuilder 类型。
 *
 *      StringBuffer 与 StringBuilder 的理解
 *          * java.lang.StringBuffer 代表可变的字符序列，JDK1.0 中声明，可以对字符串内容进行增删，此时不会产生新的对象。
 *          * StringBuffer和StringBuilder都继承AbstractStringBuilder类，AbstractStringBuilder有byte[] value字节数组。JDK8中为字符数组，
 *              , value没有声明为final的，说明value可以指向新的数组。
 *          * StringBuilder 和 StringBuffer 非常类似，均代表可变的字符序列，而且提供相关功能的方法也一样。
 *          * 区分 String、 StringBuffer、 StringBuilder
 *              - String:不可变的字符序列；底层使用 char[] value 数组存储(JDK8.0 中);
 *              - StringBuffer:可变的字符序列；线程安全（方法有 synchronized 修饰），效率低；底层使用 char[] value数组存储 (JDK8.0 中)
 *              - StringBuilder:可变的字符序列； jdk1.5 引入，线程不安全的，效率高；底层使用 char[] value数组存储(JDK8.0 中)
 *              - 以上三种的字符数组 char[] value 在 JDK17版本中为字节数组byte[] value
 *              - 效率对比：StringBuilder > StringBuffer > String
 *
 *      StringBuffer 与 StringBuilder的API
 *          StringBuilder、 StringBuffer 的 API 是完全一致的，并且很多方法与 String 相同。
 *          常用API：
 *              （1） StringBuffer append(xx)：提供了很多的 append()方法，用于进行字符串追加的方式拼接。
 *              （2） StringBuffer delete(int start, int end)：删除[start,end)之间字符。
 *              （3） StringBuffer deleteCharAt(int index)：删除[index]位置字符。
 *              （4） StringBuffer replace(int start, int end, String str)：替换[start,end)范围的字符序列为 str
 *              （5） void setCharAt(int index, char c)：替换[index]位置字符。
 *              （6） char charAt(int index)：查找指定 index 位置上的字符。
 *              （7） StringBuffer insert(int index, xx)：在[index]位置插入 xx 。
 *              （9） StringBuffer reverse()：反转。
 *              （10）void setLength(int newLength) ：设置当前字符序列长度为 newLength
 *
 *          其他API：
 *              和String中的API一致。
 *
 * @ClassName: A_String.java
 * @Author: anpeng
 * @Date: 2023/11/13 10:49
 */
public class A_String {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //1、String的特性 -- getBytes可以获取String对象的经过指定字符集解码的字节数组
        System.out.println("=======String的特性=============");
        String anpeng = "安鹏";
        System.out.println(Arrays.toString(anpeng.getBytes(StandardCharsets.UTF_8)));
        byte[] bytes ={-27, -82, -119, -23, -71, -113};
        String ap = new String(bytes);
        System.out.println(ap);

        //2、String的内存结构 -- 字符串字面常量在常量池中，new出来的String引用对象在堆中，对象中的value字节数组指向的也常量池中的一个常量。
        System.out.println("=======String的内存结构=============");
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setName("Tom");
        p2.setName("Tom");
        System.out.println(p1.getName() == p2.getName());//true, ==比较的是地址，常量池中只有一个"Tom"常量被创建，同时被p1和p2共享。

        //3、String的拼接
        System.out.println("=======String的拼接=============");
        String s1 = "hello"; //s1是变量, "hello"是常量，下面同理。
        String s2 = " world";
        String s3 = "hello" + " world";//常量+常量 结果指向常量池，即s3指向常量池中的字面量"hello world"对象
        String s4 = s1 + " world";//变量+常量，结果指向堆中，即s4指向堆中value为字面量"hello world"的String类对象
        String s5 = s1 + s2;//变量+变量，结果指向堆中，即s5指向堆中value为字面量"hello world"的新String类对象
        String s6 = (s1 + s2).intern(); //拼接后调用intern方法：返回值在常量池中,即s6指向常量池中的字面量"hello world"对象
        String s7 = "hello".concat(" world");//哪怕两个常量对象用concat方法进行拼接，结果也是在堆中
        System.out.println(s3 == s4); //false，s3指向常量池对象，s4指向堆区对象
        System.out.println(s3 == s5); //false，s3指向常量池对象，s5指向堆区对象
        System.out.println(s4 == s5); //false，两个都指向堆中，但，是不同的对象，只是它们的value都指向了常量池中的"hello world"
        System.out.println(s3 == s6); //true，两个都指向常量池
        System.out.println(s3 == s7);//false，哪怕两个常量对象用concat方法拼接，结果也是在堆中

        //4、String的构造
        System.out.println("=======String的构造=============");
        char[] abc = {'a', 'b', 'c'}; //在堆区创建了一个字节数组对象{'a', 'b', 'c'}
        String str = new String();//在堆区创建了一个字符串对象str。并在常量池中创建了一个空字符串""，str中的value指向常量中的这个空字符串。
        System.out.println(str);
        String str1 = "abc"; //在常量池中创建了一个字符串常量"abc", str1指向该字符串常量"abc"。
        //只要有new，都是在堆内存中创建一个新对象。
        String str2 = new String("abc");//在堆区创建了一个字符串对象str2，该对象的value指向了常量池中的字符串常量"abc"。
        String str3 = new String(abc); //在堆区创建了一个字符串对象str3，该对象的value指向了常量池中已有的字符串常量"abc"。
        System.out.println(str1 == str3); //false
        System.out.println(str1 == str2); //false
        System.out.println(str2 == str3); //false
        String str4 = new String(abc, 0, 2); //在堆区创建了字符串对象str4，并在常量池创建了"ab"对象，str4对象的value指向"ab"对象。
        System.out.println(str4);
        String str5 = new String(bytes);//"安鹏": 系统默认字符集，同上，两个对象
        System.out.println(str5);
        String str6 = new String(bytes,StandardCharsets.UTF_16);//"觩릏", 指定字符集。同上，两个对象
        System.out.println(str6);

        //5.String的转换
        System.out.println("=======String的构造=============");
        String str7 = "中国";
        //ISO8859-1 把所有的字符都当做一个byte处理，处理不了多个字节
        System.out.println(str7.getBytes(StandardCharsets.ISO_8859_1).length);//2
        System.out.println(str7.getBytes(StandardCharsets.UTF_8).length);//6 UTF-8编码中，常规的中文都是3个字节
        //不乱码：（1）保证编码与解码的字符集名称一样（2）不缺字节
        System.out.println(new String(str7.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1));//缺字节，乱码
        System.out.println(new String(str7.getBytes(StandardCharsets.UTF_8),StandardCharsets.UTF_8));//中国
        System.out.println(new String(str7.getBytes("gbK"), "GBK"));//中国

        //6、String的API
        System.out.println("============见/test/java_bean.day05/StringAPITest================");

        //7、String的算法题
        System.out.println("============见/test/java_bean.day05/StringAlgorithmTest================");

        //8、StringBuilder和StringBuffer
        System.out.println("============见/test/java_bean.day05/StringBuilderAndBufferTest================");
    }
}
