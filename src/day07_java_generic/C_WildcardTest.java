package day07_java_generic;

import java_bean.day07.Person;
import java_bean.day07.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通配符的使用：
 *      当我们声明一个变量/形参时，这个变量/形参的类型是一个泛型类或泛型接口。例如：Comparator类型，但是我们仍然无法确定这个泛型类或
 *      泛型接口的类型变量的具体类型，此时我们可以考虑使用类型通配符 ？。例如：List<?>。
 *
 * 通配符的读和写：
 *      写操作：
 *          将任意元素加入到其中不是类型安全的：
 *              Collection <?> c = new ArrayList<>();
 *              c.add("anpeng"); // 编译时错误
 *          因为我们不知道c的元素类型，我们不能向其中添加对象。 add方法有类型参数E作为集合的元素类型。我们传给add的任何参数都必须
 *          是一个未知类型的子类。因为我们不知道那是什么类型，所以我们无法传任何东西进去。唯一可以插入的元素是null，因为它是所有引用类型的默认值。
 *      读操作：
 *          另一方面，读取List<?>的对象list中的元素时，永远是安全的，因为不管list的真实类型是什么，它都包含Object。
 *
 * 使用注意点：
 *      1、不能用在泛型方法声明上，返回值类型前面<>不能使用？, 否则编译报错。
 *          如：public static <?> void test(ArrayList<?> list){}
 *      2、不能用在泛型类的声明上，否则编译报错。
 *          如：class GenericTypeClass<?>{}
 *      3、不能用在右边的创建对象上，右边属于创建集合对象，否则编译报错。
 *          如：ArrayList<?> list2 = new ArrayList<?>();
 *
 * 有限制的通配符：
 *      * <?> -- 允许所有泛型的引用调用
 *      * 通配符指定上限：<? extends 类/接口>
 *          -- 使用时指定的类型必须是继承某个类，或者实现某个接口，即<=。
 *      * 通配符指定下限：<? super 类/接口>
 *          -- 使用时指定的类型必须是某个类或接口，或者是某个类的父类或父接口，即>=。
 *
 * @ClassName: C_WildcardTest.java
 * @Author: anpeng
 * @Date: 2023/12/25 18:43
 */
@SuppressWarnings("all")
public class C_WildcardTest {
    @Test
    public void testWildcard(){
        Collection<?> c = new ArrayList<>();//因为？不知道具体是什么类型，故所有类型的数据都不能加入，只能加入null。
        //c.add("anpeng");//编译错误。
        c.add(null);
        System.out.println(c);


        //通配符指定上限--只读不写
        List<? extends Person> list = new ArrayList<>();
        //list.add(new Person("hello"));//编译报错，不可以写入。

        //通配符指定下限--可读可写
        List<? super Person> list1 = new ArrayList<>();
        //list.add("anpeng");//编译报错
        list1.add(new Person("anpeng love huli"));
        list1.add(new Student("student", "anpeng", 27));
        System.out.println("list5 = " + list1);
    }

}
