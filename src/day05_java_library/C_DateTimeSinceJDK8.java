package day05_java_library;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * JDK8新的日期时间API:
 *      JDK1.0中包含了一个java.util.Date类，但是它的大多数方法已经在JDK1.1引入Calendar类之后被弃用了。而Calendar并不比Date好多少。
 *      它们面临的问题是：
 *          * 可变性：像日期和时间这样的类应该是不可变的。
 *          * 偏移性： Date中的年份是从1970开始的，而月份都从0开始。
 *          * 格式化：格式化只对Date有用，Calendar则不行。
 *          * 此外，它们也不是线程安全的；不能处理闰秒等。
 *
 *      JDK8以一个新的开始为Java创建优秀的API。新的日期时间API包含：
 *          * java.time – 包含值对象的基础包。
 *          * java.time.chrono – 提供对不同的日历系统的访问。
 *          * java.time.format – 格式化和解析时间和日期。
 *          * java.time.temporal – 包括底层框架和扩展特性。
 *          * java.time.zone – 包含时区支持的类
 *          说明：
 *              说明：新的java.time中包含了所有关于时钟（Clock），本地日期（LocalDate）、本地时间（LocalTime）、
 *              本地日期时间（LocalDateTime）、时区（ZonedDateTime）和持续时间（Duration）的类。
 *              尽管有 68 个新的公开类型，但是大多数开发者只会用到基础包和 format 包，大概占总数的三分之一。
 *
 *      本地日期时间：LocalDate、 LocalTime、 LocalDateTime
 *          方法                                    描述
 *          now()/now(ZoneId zone)                  静态方法，根据当前时间创建对象/指定时区的对象
 *          of(xx,xx,xx,xx,xx,xxx)                  静态方法，根据指定日期/时间创建对象。
 *          getDayOfMonth()/getDayOfYear()          获得月份天数(1-31) /获得年份天数(1-366)。
 *          getDayOfWeek()                          获得星期几(返回一个 DayOfWeek 枚举值)。
 *          getMonth()                              获得月份, 返回一个 Month 枚举值。
 *          getMonthValue()/getYear()               获得月份(1-12) / 获得年份。
 *          getHours()/getMinute()/getSecond()      获得当前对象对应的小时、分钟、秒。
 *         [withDayOfMonth()/withDayOfYear()        将月份天数、年份天数、月份、年份修
 *          /withMonth()/withYear()                 改为指定的值并返回新的对象]
 *          with(TemporalAdjuster t)
 *         [plusDays(),plusWeeks(),                 向当前对象添加几天、几周、
 *          plusMonths(),plusYears(),plusHours()]   几个月、几年、几小时
 *         [minusMonths()/minusWeeks()              从当前对象减去几月、几周、
 *          /minusDays()/minusYears()/minusHours()] 几天、几年、几小时
 *         [plus(TemporalAmount t)/                 添加或减少一个 Duration 或 Period
 *          minus(TemporalAmount t)]
 *          isBefore()/isAfter()                    比较两个 LocalDate
 *          isLeapYear()                            判断是否是闰年（在 LocalDate 类中声明）
 *          format(DateTimeFormatter t)             格式化本地日期、时间，返回一个字符串
 *          parse(Char-sequence text)               将指定格式的字符串解析为日期、时间
 *
 *      瞬时：Instant
 *          * Instant：时间线上的一个瞬时点。 这可能被用来记录应用程序中的事件时间戳。时间戳是指格林威治时间1970年01月01日00时00分00秒
 *                    (北京时间1970年01月01日08时00分00秒)起至现在的总秒数。
 *          * java.time.Instant表示时间线上的一点，而不需要任何上下文信息，例如，时区。概念上讲，它只是简单的表示自1970年1月1日0时0分0秒
 *                  （UTC）开始的秒数。
 *          方法                              描述
 *          now()                            静态方法，返回默认 UTC 时区的 Instant 类的对象
 *          ofEpochMilli(long epochMilli)    静态方法，返回在1970-01-01 00:00:00基础上加上指定毫秒数之后的 Instant 类的对象。
 *          atOffset(ZoneOffset offset)      结合即时的偏移来创建一个 OffsetInstant
 *          toEpochMilli()                   返回 1970-01-01 00:00:00 到当前时间的毫秒数，即为时间戳
 *
 *      日期时间格式化：DateTimeFormatter
 *          该类提供了三种格式化方法：
 *          * (了解)预定义的标准格式。如：ISO_LOCAL_DATE_TIME、 ISO_LOCAL_DATE、ISO_LOCAL_TIME
 *          * (了解)本地化相关的格式。如：ofLocalizedDate(FormatStyle.LONG)
 *          * 自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
 *          方法                              描述
 *          ofPattern(String pattern)         静态方法，返回一个指定字符串格式的DateTimeFormatter
 *          format(TemporalAccessor t)        格式化一个日期、时间，返回字符串。
 *          parse(CharSequence text)          将指定格式的字符序列解析为一个日期、时间。
 *
 *      其他API：
 *          1、指定时区日期时间： ZonedId 和 ZonedDateTime
 *              * ZoneId：该类中包含了所有的时区信息，一个时区的 ID，如 Europe/Paris
 *              * ZonedDateTime：一个在 ISO-8601 日历系统时区的日期时间，如 2007-12-03T10:15:30+01:00 Europe/Paris。
 *                  -其中每个时区都对应着 ID，地区 ID 都为“{区域}/{城市}”的格式，例如：Asia/Shanghai等
 *              * 常见时区 ID:
 *                  - Asia/Shanghai
 *                  - UTC
 *                  - America/New_York
 *              * 可以通过ZonedId获取可用的时区ID
 *
 *          2、持续日期/时间：Period 和 Duration
 *              * 日期间隔：Period，用于计算两个“日期”间隔
 *              * 持续时间：Duration，用于计算两个“时间”间隔
 *
 *          3、Clock：使用时区提供对当前即时、日期和时间的访问的时钟。
 *
 *          4、TemporalAdjuster: 时间校正器。有时我们可能需要获取例如：将日期调整到“下一个工作日”等操作。TemporalAdjusters: 该类通过
 *          静态方法(firstDayOfXxx()/lastDayOfXxx()/nextXxx())提供了大量的常用TemporalAdjuster的实现。
 *
 *
 * @ClassName: C_DateTimeSinceJDK8.java
 * @Author: anpeng
 * @Date: 2023/11/15 17:40
 */
public class C_DateTimeSinceJDK8 {
    public static void main(String[] args) {
        //1、本地日期时间：LocalDate、 LocalTime、 LocalDateTime
        System.out.println("==========本地日期时间：LocalDate、 LocalTime、 LocalDateTime==========");
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);//2023-11-16   程序运行时刻的本地日期

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);//09:29:37.763408200 程序运行时刻的本地时间，精确到纳秒级别

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);//2023-11-16T09:33:08.147609400 程序运行时刻的本地日期加时间，精确到纳秒级别

        localDate = LocalDate.of(2019, 5, 13);
        System.out.println(localDate);//2019-05-13
        System.out.println(localDate.getDayOfYear());//133
        System.out.println(localDate.plusDays(160));//2019-10-20, 返回的是新的日期对象，原来的日期对象不会改变。
        System.out.println(localDate.withDayOfMonth(28));//2019-05-28
        System.out.println(localDate.isLeapYear());//false 不是闰年
        System.out.println(localDate.minusYears(4));//2015-05-13

        localTime = LocalTime.of(9, 45, 23);
        System.out.println(localTime);//09:45:23
        System.out.println(localTime.plusSeconds(58));//09:46:21

        localDateTime = LocalDateTime.of(2025, 7, 1, 10, 8, 8);
        System.out.println(localDateTime);//2025-07-01T10:08:08
        System.out.println(localDateTime.plusDays(188));//2026-01-05T10:08:08

        //2、瞬时：Instant
        System.out.println("==========瞬时：Instant==========");
        Instant instant = Instant.now();
        System.out.println(instant);//2023-11-16T02:09:34.834355300Z 默认以UTC时区创建瞬时对象，中国为东八区，所以少了8个小时
        System.out.println(instant.getNano());//834355300 获取纳秒值
        //通过偏移来创建系统本地时区的瞬时对象
        System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)));//2023-11-16T10:36:57.117424700+08:00

        //3、日期时间格式化：DateTimeFormatter
        System.out.println("==========日期时间格式化：DateTimeFormatter==========");
        // 方式一：预定义的标准格式。如： ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 格式化:日期-->字符串
        localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);//2023-11-16T10:50:29.377620600
        System.out.println(str1);//2023-11-16T10:50:29.3776206 省略尾部的零
        // 解析：字符串 -->日期
        TemporalAccessor parse = formatter.parse("2022-12-04T21:02:14.808");
        LocalDateTime dateTime = LocalDateTime.from(parse);
        System.out.println(dateTime);

        // 方式二：
        //本地化相关的格式。如：ofLocalizedDateTime()--FormatStyle.LONG/.MEDIUM/.SHORT:适用于 LocalDateTime
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault());
        //格式化
        String str2 = formatter1.format(LocalDateTime.now());
        System.out.println(str2);//2023年11月16日 CST 上午11:11:57
        //本地化相关的格式。如：ofLocalizedDate()--FormatStyle.FULL/.LONG/.MEDIUM/.SHORT:适用于LocalDate
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withZone(ZoneId.systemDefault());
        //格式化
        String str3 = formatter2.format(LocalDate.now());
        System.out.println(str3);//2023年11月16日星期四

        //方式三：自定义的方式（关注、重点）
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //格式化
        String strDateTime = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(strDateTime); //2022/12/04 21:05:42
        //解析
        TemporalAccessor accessor = dateTimeFormatter.parse("2022/12/04 21:05:42");
        System.out.println(LocalDateTime.from(accessor)); //2022-12-04T21:05:42

        //4、其他API
        System.out.println("==========其他API==========");
        //需要知道一些时区ID, Set<String>是一个集合，容器
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        //快捷模板 iter
        System.out.println(availableZoneIds.contains("Asia/Shanghai"));
        System.out.println(ZoneId.systemDefault());//Asia/Shanghai
        //带时区的日期时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);//2023-11-16T11:33:58.055392500+08:00[Asia/Shanghai]

        //计算日期间隔--period
        LocalDate t1 = LocalDate.now();
        LocalDate t2 = LocalDate.of(2018, 12, 31);
        Period between = Period.between(t2, t1);//起始时间放前面。
        System.out.println(between);//P4Y10M16D -P:period -Y:year -M:month -D:day
        System.out.println("相差的年数： " + between.getYears());
        System.out.println("相差的月数： " + between.getMonths());
        System.out.println("相差的天数： " + between.getDays());
        System.out.println("相差的总月数： " + between.toTotalMonths());
        //计算时间间隔--duration
        LocalDateTime t3 = LocalDateTime.of(2017, 8, 29, 0, 0, 0, 0);
        LocalDateTime t4 = LocalDateTime.now();
        Duration duration = Duration.between(t3, t4);//起始时间放前面。
        System.out.println(duration);
        System.out.println("相差的总天数： " + duration.toDays());
        System.out.println("相差的总小时数： " + duration.toHours());
        System.out.println("相差的总分钟数： " + duration.toMinutes());
        System.out.println("相差的总秒数： " + duration.getSeconds());
        System.out.println("相差的总毫秒数： " + duration.toMillis());
        System.out.println("相差的总纳秒数： " + duration.toNanos());
        System.out.println("不够一秒的纳秒数： " + duration.getNano());

        //时间校正器--Temporal:时间的，暂时的；时态，时域
        // 获取当前日期的下一个周日是哪天？
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.SUNDAY);
        System.out.println(LocalDateTime.now().with(temporalAdjuster));//2023-11-19T11:50:57.046222200
        // 获取下一个工作日是哪天？
        localDate = LocalDate.now().with(new TemporalAdjuster()
        {
            @Override
            public Temporal adjustInto(Temporal temporal) {
                LocalDate date = (LocalDate) temporal;
                if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    return date.plusDays(3);
                } else if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                {
                    return date.plusDays(2);
                } else {
                    return date.plusDays(1);
                }
            }
        });
        System.out.println("下一个工作日是： " + localDate);//下一个工作日是：2023-11-17

    }
}
