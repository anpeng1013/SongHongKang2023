package day03_java_oop;

import java_bean.day03.Administer;
import java_bean.day03.Authority;
import java_bean.day03.Person;
import java_bean.day03.User;

/**
 * 类java.lang.Object是类层次结构的根类，即所有其它类的父类。每个类都使用Object作为超类。
 *      * Object类型的变量与除Object以外的任意引用数据类型的对象都存在多态引用。
 *      * 所有对象（包括数组）都继承这个类的方法。
 *      * 如果一个类没有特别指定父类，那么默认则继承自Object类。
 *
 * Object类的方法
 *      根据JDK源代码及Object类的API文档， Object类当中包含的方法有11个。这里我们主要关注其中的6个。
 *      1、（重点）equals()
 *          ==:
 *              * 基本类型比较的是值，只要两个变量的值相等，即为true。
 *              * 引用类型比较的是地址，只要指向同一个对象时，==才返回true。两边的数据类型必须兼容（相同或子父类），否则编译出错。
 *          equals：所有类都继承了Object，也就获得了equals()方法，可以被重写。
 *              * 只能比较引用类型，Object类源码中的equals()的作用与“==”相同：比较是否指向同一个对象。
 *              * 格式：object1.equals(object2)
 *              * 特例：当用equals()方法进行比较时，对类File、String、Data及包装类来说，是比较类型及内容，
 *                      而不考虑引用的是否为同一个对象。因为在这些类中重写了Object类的equals()方法
 *              * 当自定义使用equals()时，可以重写。用于比较两个对象的“内容”是否都相等。
 *              * 重写equals()方法的原则：
 *                  1、对称性：如果x.equals(y)是true,那么y.equals(x)也应该是true。
 *                  2、自反性：x.equals(x)必须是true
 *                  3、传递性：x.equals(y)是true，而且y.equals(z)是true，那么x.equals(z)也应该是true。
 *                  4、一致性：如果x.equals(y)是true，只要x和y的内容一直不变，不管x.equals(y)重复多少次，返回都是true。
 *                  5、任何情况下，x.equals(null)永远是false，x.equals(和x不同类型的对象)永远是false。
 *
 *      2、（重点）hashCode()
 *          * 什么是hashCode
 *              hashCode()的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。
 *              hashcode方法是本地方法，也就是用c语言或c++实现的，该方法没有被重写时，是将对象的{内存地址}转换为{整数}之后返回。
 *          * 为什么要有hashCode
 *              以“HashSet如何检查重复”为例子来说明为什么要有hashCode？当你把对象加入HashSet时， HashSet会先计算对象的hashcode
 *              值来判断对象加入的位置，同时也会与其他已经加入的对象的hashcode值作比较，如果没有相符的hashcode， HashSet会假设对象
 *              没有重复出现。但是如果发现有相同hashcode值的对象，这时会调用 equals()方法来检查hashcode相等的对象是否真的相同。
 *              如果两者相同，HashSet就不会让其加入操作成功。如果不同的话，就会重新散列到其他位置。这样就大大减少了equals的次数，
 *              相应就大大提高了执行速度。
 *          * 为什么重写equals时必须重写hashCode
 *              如果自定义的类没有覆写hashcode方法，就会调用其父类Object中的hashcode方法，默认是根据地址进行哈希运算，
 *              也就是两个内容值相同的对象实例，其hashcode值不一样。如果自定义的类中重写了equals方法(以内容是否相同来判断对象是否相等)
 *              ，但没有重写hashcode方法时，会导致内容相同的两个对象(即相等的两个对象)，其hashcode值不一样(违反Object类的规范)，并且
 *              这种对象在hashmap、hashset中会出问题，导致相同的key可以重复放入。重写hashCode方法，其实就是将，由对象内存地址哈希成整数
 *              转换成，由对象内容转成整数。
 *          * Object类规范
 *              1、两个对象相等，则hashCode一定相同。但hashCode相同的两个对象不一定相等。
 *              2、两个对象相等，则对两个对象分别调用equals方法都返回true。
 *              3、hashCode()的默认行为是对堆上的对象内存地址产生哈希值。如果没有重写hashCode()，则该类的两个对象无论如何都不会相等
 *              （即使这两个对象指向相同的数据）
 *          * 为什么两个对象有相同的hashCode值，它们也不一定是相等
 *              因为哈希函数可能会冲突碰撞，即不同的数据经过哈希函数运算后，得到相同的哈希值。
 *
 *      3、（重点）toString
 *          * 默认情况下，toString返回的是"对象的运行类型@对象的hashCode值的十六进制形式"
 *          * 在自定义类中，可以根据需要重写toString方法，如String类重写了toString方法，返回字符串的值。
 *          * 在String与其他类型数据进行连接操作时，会自动调用toString方法。
 *          * 直接调用System.out.println(object)打印对象，默认会自动调用这个对象的toString方法。
 *
 *      4、（重点）clone
 *          * clone即克隆、拷贝对象，使用clone()拷贝出来的对象有自己新的内存地址，而不是跟被拷贝对象指向同一个内存地址。
 *          * clone()拷贝对象跟new对象的区别是，new是实例化一个新的初始化对象，而clone是复制出一个包含原对象一些信息数据的新对象。
 *          * 使用clone()，类必须要实现Clone接口。
 *              深拷贝 VS 浅拷贝
 *              * 浅拷贝：对被拷贝对象中的基本数据成员进行值传递，对引用成员进行引用传递般的拷贝(即只复制引用地址值，不复制内容)
 *              * 深拷贝：对被拷贝对象中的基本数据成员进行值传递，对引用成员，创建一个新的对象，并复制其内容。
 *          * 如果不重写clone方法，默认使用的都是浅拷贝。
 *
 *      5、finalize
 *          * 当对象被回收时，系统自动调用该对象的finalize方法。不是垃圾回收器调用的，是本类对象调用的。
 *          * 什么时候被回收：当某个对象没有任何引用时， JVM就认为这个对象是垃圾对象，就会在之后不确定的时间使用垃圾回收机制来销毁该对象，
 *              在销毁该对象前，会先调用finalize()方法。
 *          * 子类可以重写该方法，目的是在对象被清理之前执行必要的清理操作。比如，在方法内断开相关连接资源。
 *
 *      6、getClass
 *          * 获取对象的运行时类型。
 *          * 因为Java有多态现象，所以一个引用数据类型的变量的编译时类型与运行时类型可能不一致，因此如果需要查看这个变量实际指向的对象
 *              的类型，需要用 getClass()方法
 *
 * native关键字：
 *      使用native(本地)关键字说明这个方法是原生函数，即这个方法是用C/C++等非Java语言实现的，并且被编译成了DLL，由JVM去调用。
 *          * 本地方法是有方法体，用C语言编写。由于本地方法的方法体源码没有开源，故看不到方法体。
 *          * 在java中定义一个native方法时，并不提供实现体。
 *
 * @ClassName: G_Object.java
 * @Author: anpeng
 * @Date: 2023/10/30 16:15
 */
public class G_Object {
    public static void main(String[] args) throws CloneNotSupportedException{
        //重写toString
        User user = new User();
        user.setHost("127.0.0.1");
        user.setUsername("anpeng");
        user.setPassword("aphl");
        System.out.println(user);
        //不重写toString
        Person person = new Person();
        System.out.println("anpeng" + person);

        //重写equals和hashCode
        User user1 = new User();
        User user2 = new User();
        System.out.println(user1.equals(user2));//两个null肯定相等，返回true。
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        Authority authority = new Authority();
        authority.setId(1);
        authority.setGrade(1);
        authority.setDescription("1级用户权限");
        user1.setHost("127.0.0.1");
        user1.setUsername("huli");
        user1.setPassword("aphl");
        user1.setAuthority(authority);
        user2.setHost("127.0.0.1");
        user2.setUsername("anpeng");
        user2.setPassword("aphl");
        user2.setAuthority(authority);
        System.out.println(user1.equals(user2));

        //浅拷贝--不重写clone
        Administer administer = new Administer();
        administer.setName("anpeng");
        administer.setPassword("123456");
        administer.setAuthority(authority);
        Administer administer1 = administer.clone();
        administer1.setName("huli");
        administer1.getAuthority().setId(2);//修改其中一个对象的引用成员变量，两个对象的成员变量同时改变，浅拷贝。
        System.out.println(administer);
        System.out.println(administer1);

        //深拷贝--重写clone
        User originUser = new User();
        Authority authority1 = new Authority();
        authority1.setId(3);
        authority1.setGrade(2);
        authority1.setDescription("2级用户权限");
        originUser.setUsername("anpeng");
        originUser.setHost("127.0.0.1");
        originUser.setPassword("aphl1124");
        originUser.setAuthority(authority1);
        User clonedUser = originUser.clone();
        clonedUser.getAuthority().setId(4);//修改拷贝对象的引用成员变量，被拷贝对象的成员变量不变，深拷贝。
        System.out.println(originUser);
        System.out.println(clonedUser);
    }
}
