package day07_java_generic;

import java_bean.day07.MyArrays;
import java_bean.day07.Person;
import org.junit.Test;

import java.util.Arrays;

/**
 * 泛型类或接口：当在类或接口中定义某个成员时，该成员的相关类型是不确定的，而这个类型需要在使用这个类或接口时才可以确定，那么可使用泛型类、泛型接口。
 *      - 声明类或接口时，在类或接口名后面声明泛型类型，我就把这样的类或接口称为泛型类或泛型接口。
 *          [修饰符] class 类名<类型变量列表> [extends 父类] [implements 接口]{}
 *          [修饰符] class 接口名<类型变量列表> [implements 接口]{}
 *      - 说明：
 *          -- 在声明完自定义泛型类以后，可以在类的内部（比如：属性、方法、构造器中）使用类的泛型。
 *          -- 在创建自定义泛型类的对象时，可以指明泛型参数类型。一旦指明，内部凡是使用类的泛型参数的位置，都具体化为指定的类的泛型类型。
 *          -- 如果在创建自定义泛型类的对象时，没有指明泛型参数类型，那么泛型将被擦除，泛型对应的类型均按照Object处理，但不等价于Object。
 *          -- 泛型的指定中必须使用引用数据类型。不能使用基本数据类型，此时只能使用包装类替换。
 *          -- 除创建泛型类对象外，子类继承泛型类时、类实现泛型接口时，也可以确定泛型结构中的泛型参数。如果我们在给泛型类提供子类时，子类也不确定
 *             泛型的类型，则可以继续使用泛型参数。我们还可以在现有的父类的泛型参数的基础上，新增泛型参数或减少泛型参数（不是泛型擦除）。
 *      - 注意：
 *          -- 泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如：<E1,E2,E3>
 *          -- JDK7.0 开始，泛型的简化操作：ArrayList<String> list = new ArrayList<>();
 *          -- 不能使用 new E[]。但是可以：E[] elements = (E[])new Object[capacity];
 *          -- 在类/接口上声明的泛型，在本类或本接口中即代表某种类型，但不可以在静态方法中使用类的泛型。因为静态方法中泛型T还没确定。
 *          -- 异常类是不能带泛型的。
 *
 *
 * 泛型方法：定义类、接口时没有使用<泛型参数>，但是某个方法的形参类型不确定时，这个方法可以单独定义<泛型参数>。
 *      - 声明方法时，在[修饰符]与返回值类型之间声明类型变量，我们把声明了类型变量的方法，称为泛型方法。
 *          [访问权限] <泛型标识> 返回值类型 方法名([泛型标识 参数名称]) [抛出的异常]{}
 *      - 说明：
 *          -- 方法也可以被泛型化，与其所在的类是否为泛型类没有关系。
 *          -- 泛型方法中的泛型参数在方法被调用时确定。
 *          -- 泛型方法可以根据需要，声明为static的。（注意，泛型类的静态方法不可以使用类的泛型）。
 *
 * @ClassName: B_CustomGenericTest.java
 * @Author: anpeng
 * @Date: 2023/12/25 15:49
 */
@SuppressWarnings("all")
public class B_CustomGenericTest {
    @Test
    public void testGenericClass(){
        Person<String> anpeng = new Person<>("hello world");
        System.out.println("anpeng = " + anpeng.getInfo());
    }

    @Test
    public void testGenericMethod(){
        //静态泛型方法
        Person.show("anpeng love huli forever");

        //非静态方法
        String[] list = new String[3];
        list[0]=("tom");
        list[1]=("huli");
        list[2]=("anpeng");
        new MyArrays().sort(list);
        System.out.println("list = " + Arrays.toString(list));
    }

}
