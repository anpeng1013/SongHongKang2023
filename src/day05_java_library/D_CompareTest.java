package day05_java_library;

import java_bean.day05.Goods;
import java_bean.day05.Student;
import java_bean.day05.StudentScoreComparator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * java 比较器
 *      我们知道基本数据类型的数据（除 boolean 类型外）需要比较大小的话，之间使用比较运算符即可，但是引用数据类型是不能直接使用比较运算符
 *      来比较大小的。 那么，如何解决这个问题呢？
 *          * 在 Java 中经常会涉及到对象数组的排序问题，那么就涉及到对象之间的比较问题。
 *          * Java 实现对象排序的方式有两种：
 *              - 自然排序：java.lang.Comparable
 *              - 定制排序：java.util.Comparator
 *
 * 自然排序：java.lang.Comparable
 *      * Comparable接口强行对实现它的每个类的对象进行整体排序。这种排序被称为类的自然排序。
 *      * 实现Comparable接口的类必须重写compareTo(Object obj)方法，即两个对象通过compareTo(Object obj)方法的返回值来比较大小。如果
 *          当前对象this大于形参对象obj，则返回正整数；当前对象this小于形参对象obj，则返回负整数；当前对象this等于形参对象obj，则返回零。
 *      * package java.lang;
 *        public interface Comparable{
 *            int compareTo(Object obj);
 *        }
 *      * 实现Comparable接口的对象列表（和数组）可以通过Collections.sort或Arrays.sort进行自动排序。实现此接口的对象可以用作有序映射中
 *          的键或有序集合中的元素，无需指定比较器。
 *      * 对于类C的每一个e1和e2来说，当且仅当e1.compareTo(e2)==0与e1.equals(e2)具有相同的boolean值时，类C的自然排序才叫做与equals一致。
 *          建议（虽然不是必需的） 最好使自然排序与 equals 一致。
 *      * Comparable的典型实现：(默认都是从小到大排列的)
 *          - String：按照字符串中字符的 Unicode 值进行比较。
 *          - Character：按照字符的 Unicode 值来进行比较。
 *          - 数值类型对应的包装类以及BigInteger、BigDecimal：按照它们对应的数值大小进行比较。
 *          - Boolean：true对应的包装类实例大于false对应的包装类实例。
 *          - Date、Time等：后面的日期时间比前面的日期时间大
 *
 * 定制排序：java.util.Comparator
 *      思考：
 *          - 当元素的类型没有实现 java.lang.Comparable 接口而又不方便修改代码（例如：一些第三方的类，你只有.class 文件，没有源文件）。
 *          - 如果一个类，实现了Comparable接口，也指定了两个对象的比较大小的规则，但是此时此刻我不想按照它预定义的方法比较大小，但是
 *              我又不能随意修改，因为会影响其他地方的使用，怎么办？
 *
 *      JDK在设计类库之初，也考虑到这种情况，所以又增加了一个java.util.Comparator接口。强行对多个对象进行整体排序的比较。
 *          - 重写compare(Object o1, Object o2)方法，比较o1和o2的大小：如果方法返回正整数，则表示o1大于o2；如果返回0，表示相等；
 *              返回负整数，表示o1小于o2。
 *          - 可以将Comparator传递给sort方法（如Collections.sort或Arrays.sort），从而允许在排序顺序上实现精确控制。
 *          - package java.util;
 *            public interface Comparator{
 *              int compare(Object o1,Object o2);
 *            }
 *
 * @ClassName: D_Compare.java
 * @Author: anpeng
 * @Date: 2023/11/16 12:58
 */
@SuppressWarnings("all")
public class D_CompareTest {
    @Test
    public void testComparable(){
        //1、自然排序：java.lang.Comparable
        System.out.println("==============自然排序：java.lang.Comparable=============");
        Student[] arr = new Student[5];
        arr[0] = new Student(3,"张三",90,23);
        arr[1] = new Student(1,"熊大",100,22);
        arr[2] = new Student(5,"王五",75,25);
        arr[3] = new Student(4,"李四",85,24);
        arr[4] = new Student(2,"熊二",85,18);
        //单独比较两个对象
        System.out.println(arr[0].compareTo(arr[1]));//2, id的差值
        System.out.println("所有学生： ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("按照学号排序： ");
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length-i; j++) { //冒泡排序
                if(arr[j].compareTo(arr[j+1])>0){
                    Student temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        Goods[] all = new Goods[4];
        all[0] = new Goods("《红楼梦》 ", 100);
        all[1] = new Goods("《西游记》 ", 80);
        all[2] = new Goods("《三国演义》 ", 140);
        all[3] = new Goods("《水浒传》 ", 120);
        Arrays.sort(all);
        System.out.println(Arrays.toString(all));
    }

    @Test
    public void testComparator(){
        System.out.println("==============定制排序：java.lang.Comparator=============");
        StudentScoreComparator sc = new StudentScoreComparator();
        Student[] arr = new Student[5];
        arr[0] = new Student(3,"张三",90,23);
        arr[1] = new Student(1,"熊大",100,22);
        arr[2] = new Student(5,"王五",75,25);
        arr[3] = new Student(4,"李四",85,24);
        arr[4] = new Student(2,"熊二",85,18);
        //单独比较两个对象
        System.out.println(sc.compare(arr[0], arr[1]));//-10, score的差值
        System.out.println("所有学生： ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("按照成绩排序： ");
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length-i; j++) { //冒泡排序
                if(sc.compare(arr[j], arr[j+1])>0){
                    Student temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        Goods[] all = new Goods[4];
        all[0] = new Goods("《红楼梦》 ", 100);
        all[1] = new Goods("《西游记》 ", 80);
        all[2] = new Goods("《三国演义》 ", 140);
        all[3] = new Goods("《水浒传》 ", 120);
        Arrays.sort(all, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return o1.getName().compareTo(o2.getName());//字符串String实现了Comparable接口中的compareTo方法。
            }
        });
        System.out.println(Arrays.toString(all));
    }
}
