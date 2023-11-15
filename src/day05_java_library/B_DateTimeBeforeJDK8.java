package day05_java_library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JDk8.0之前的DateTime日期时间的API
 *      java.lang.System类
 *         * System类提供的public static long currentTimeMillis()：用来返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。
 *             - 此方法适合用来计算时间差
 *             - 此方法返回的毫秒值采用的是世界标准-通用协调时间(UTC, Universal Time Coordinated)。
 *             - 计算世界时间的主要标准有：
 *                 * UTC（Universal Time Coordinated）：通用协调时
 *                 * GMT（Greenwich Mean Time）：格林尼治平均时
 *                 * CST（Central Standard Time）：中央标准时，在GMT的基础上的偏移。每个国家的CST都不一样。中国为：北京时间（GMT+8）。
 *                 * 在国际无线电通信场合，为了统一起见，使用一个统一的时间，称为通用协调时(UTC, Universal Time Coordinated)。
 *                      UTC与格林尼治平均时(GMT, Greenwich Mean Time)一样，都与英国伦敦的本地时相同。这里UTC与GMT含义完全相同。
 *
 *      java.util.Date类--表示特定的瞬间，精确到毫秒值。
 *         * 构造器：
 *             - Date()：使用无参构造器创建的对象可以获取本地当前时间。
 *             - Date(long 毫秒数)：把该毫秒值换算成日期时间对象。
 *         * 常用方法：
 *             - getTime(): 返回自1970年1月1日00:00:00GMT以来此Date对象表示的毫秒数。
 *             - toString(): 把此Date对象转换为以下形式的String：dow mon dd hh:mm:ss zzz yyyy（可以直接打印该对象，不用toString方法）
 *                          其中：dow是(day of week)一周中的某一天(Sun, Mon, Tue, Wed, Thu, Fri, Sat)， zzz 是时间标准。
 *             - 其他很多方法都过时了。
 *
 *      java.text.SimpleDateFormat--用不与语言环境有关的方式来格式化和解析日期的具体类。
 *          * 可以进行格式化：日期-->文本，也可以进行解析：文本-->日期
 *          * 构造器：
 *              - SimpleDateFormat()：默认的模式和语言环境创建对象。
 *              - public SimpleDateFormat(String pattern)：该构造方法可以用参数 pattern 指定的格式创建一个对象。
 *              字母          日期或时间元素                 表示          实例
 *               G              纪元标志符                   Text          AD（公元）
 *               y              年份                        Year          1996; 96
 *               M            年中的月份                     Month         July; 07
 *               W            年中的周数                     Number        第27周
 *               D            年中的天数                     Number        第360天
 *               w            月中的周数                     Number        第3周
 *               d            月中的天数                     Number        第28天
 *               F            月中的星期                     Number        第2个星期
 *               E            星期中的天数                   Text          Tuesday；Tue
 *               a            AM/pm标记                     Text          PM下午
 *               H            一天中的小时数（0-23）          Number        23时
 *               k            一天中的小数数（1-24）          Number        24时
 *               K            am/pm中的小数数（0-11）         Number        0时
 *               h            am/pm中的小数数（1-12）         Number        12时
 *               m            小时中的分钟数                  Number        40分钟
 *               s            分钟中的秒数                    Number        55秒
 *               S            毫秒数                         Number        978毫秒
 *               z            时区                           GTZ           GMT-08:00
 *               Z            时区                           RCF           -0800
 *          * 格式化：
 *              - public String format(Date date)：用pattern指定的模式，去格式化时间对象date
 *          * 解析：
 *              - public Date parse(String source)：从给定字符串的开始解析文本，以生成一个日期。
 *
 *      java.util.Calendar(日历)
 *          * Date类的 API 大部分被废弃了，替换为 Calendar。
 *          * Calendar 类是一个抽象类，主用用于完成日期字段之间相互操作的功能。
 *          * 获取 Calendar 实例的方法:
 *              - 使用Calendar.getInstance()方法
 *              - 调用它的子类 GregorianCalendar（公历）的构造器。
 *          * 一个Calendar的实例是系统时间的抽象表示，可以修改或获取日历字段对应的时间值。
 *              - public int get(int field)：返回给定日历字段的值.
 *              - public void set(int field, int value) ：将给定的日历字段设置为指定的值。
 *              - public void add(int field, int amount)：根据日历的规则，为给定的日历字段添加或者减去指定的时间量。
 *              - public final Date getTime()：将Calendar转成Date对象。
 *              - public final void setTime(Date date)：使用指定的 Date对象重置Calendar的时间。
 *          * 常用字段：
 *              字段                含义                          字段           含义
 *              YEAR                年                           Month          月（从0开始，可以+1使用）
 *              DAY_OF_MONTH       月中的天(几号)                 HOUR            时（12小时制）
 *              HOUR_OF_DAY        时（24小时制）                 MINUTE          分
 *              SECOND             秒                            DAY_OF_WEEK     周中的天（周几，周日为1，可以-1使用）
 *
 *
 *
 * @ClassName: B_DateTimeBeforeJDK8.java
 * @Author: anpeng
 * @Date: 2023/11/15 15:34
 */
public class B_DateTimeBeforeJDK8 {
    public static void main(String[] args) throws ParseException {
        //JDK8之前
        //1、java.lang.System类
        System.out.println("===========java.lang.System类=========");
        long time = System.currentTimeMillis();
        System.out.println(time);//1700034948000，当前系统时间距离 1970-1-1 0:0:0 0 毫秒的时间差，毫秒为单位

        //2、java.util.Date类
        System.out.println("===========java.util.Date类=========");
        Date date = new Date();
        System.out.println(date);//Wed Nov 15 16:02:47 CST 2023，直接打印date对象，不用调用toString方法。
        System.out.println(date.getTime());//1700035894477，System.currentTimeMillis()方法获取的一致。
        time = 1559806982971L;
        date = new Date(time);
        System.out.println(date);//Thu Jun 06 15:43:02 CST 2019 --pdf笔记的时间

        //3、java.text.SimpleDateFormat
        System.out.println("===========java.text.SimpleDateFormat类=========");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒 sss毫秒 E Z");
        //把 Date 日期转成字符串，按照指定的格式转
        System.out.println(sdf.format(date));//2019 年 06 月 06 日 15 时 43 分 02 秒 002毫秒 周四 +0800 --pdf笔记的时间
        //将日期字符串解析为Date对象
        String str = "2023 年 11 月 15 日 16 时 59 分 02 秒 002毫秒 周三 +0800";
        date = sdf.parse(str);
        System.out.println(date);//Wed Nov 15 16:59:02 CST 2023

        //4、java.util.Calendar(日历)
        System.out.println("===========java.util.Calendar类=========");
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);//类名+[所有字段的值]
        System.out.println(calendar.get(Calendar.MONTH));//10，月份从0开始，所以11月份返回10，使用的时候应该+1
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));//4，周日为1，周一是2,...周六是7，使用应该-1（那周日就为0了）
        // 从一个 Calendar对象中获取 Date 对象
        date = calendar.getTime();
        System.out.println(date);//Wed Nov 15 17:31:55 CST 2023
        //使用特定的Date重置calendar的时间
        date = new Date(time);//time = time = 1559806982971L; pdf笔记时间--Thu Jun 06 15:43:02 CST 2019
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -2);
        System.out.println("当前日期减 2 个月后,时间是:" + calendar.getTime());//Sat Apr 06 15:43:02 CST 2019


    }
}
