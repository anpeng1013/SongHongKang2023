package day11_java_reflection;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 1、剖析Class类并获取Class实例
 *      - 要想解剖一个类，必须先要获取到该类的 Class 对象。而剖析一个类或用反射解决具体的问题就是使用相关 API:
 *          -- java.lang.Class
 *          -- java.lang.reflection.*
 *
 * 2、理解Class
 *      - 理论上
 *          -- 在 Object 类中定义了 public final Class getClass() 的方法，此方法将被所有子类继承。返回值的类型是一个 Class 类，此类
 *              是 Java 反射的源头，实际上所谓反射从程序的运行结果来看也很好理解，即：可以通过对象反射求出类的名称。
 *          -- 对象反射后可以得到的信息：某个类的属性、方法和构造器、某个类到底实现了哪些接口。对于每个类而言，JRE都为其保留一个不变的 Class 类型的对象。
 *              一个 Class 对象包含了特定某个结构(class/interface/enum/annotation/primitive type/void/[])的有关信息。
 *              > Class 本身也是一个类
 *              > Class 对象只能由系统建立对象
 *              > 一个加载的类在 JVM 中只会有一个 Class 实例
 *              > 一个 Class 对象对应的是一个加载到 JVM 中的一个.class 文件
 *              > 每个类的实例都会记得自己是由哪个 Class 实例所生成
 *              > 通过 Class 可以完整地得到一个类中的所有被加载的结构
 *              > Class 类是 Reflection 的根源，针对任何你想动态加载、运行的类，唯有先获得相应的 Class 对象。
 *
 *      - 内存结构上
 *          -- 描述一个类的信息的Claas对象保存在JVM的方法区中。
 *
 * 3、获取Class类的实例（四种方法）
 *      - 方式1：要求编译期间已知具体的类型
 *          -- 前提：若已知具体的类，通过类的 class 属性获取，该方法最为安全可靠，程序性能最高
 *          -- 案例：Class clazz1 = Person.class;
 *
 *      - 方式2：获取对象的运行时类型
 *          -- 前提：已知某个类的实例，调用该实例的 getClass()方法获取 Class 对象。
 *          -- 案例：Class clazz2 = "anpeng love huli".getClass();
 *
 *      - 方式3：可以获取编译期间未知的类型
 *          -- 前提：已知一个类的全类名（含包名），且该类在类路径下，可通过 Class 类的静态方法forName()获取，可能抛出 ClassNotFoundException
 *          -- 案例：Class clazz3 = Class.forName("java.lang.String");
 *
 *      - 方式4：其他方式（不做要求）
 *          -- 前提：可以用系统类加载对象或自定义加载器对象加载指定路径下的类型
 *          -- 案例：
 *              > ClassLoader cl = this.getClass().getClassLoader();
 *              > Class clazz4 = cl.loadClass("类的全类名");
 *
 * 4、哪些类型可以有Class对象
 *      - 简而言之：所有java类型！
 *          -- 1）class：外部类，成员(成员内部类，静态内部类)，局部内部类，匿名内部类
 *          --（2） interface：接口
 *          --（3） []：数组，只要类型和维度相同，就是同一个Class
 *          --（4） enum：枚举
 *          --（5） annotation：注解@interface
 *          --（6） primitive type：基本数据类型
 *          -- （7） void
 *
 * 5、Class常用方法
 *      - 注意！（获取私有的构造器、属性、方法时，用getDeclaredXxx方法，并使用setAccessible(true)将访问权限打开）
 *      - static Class forName(String name)    返回指定类名 name 的 Class 对象
 *      - Object newInstance()                 调用缺省构造函数，返回该 Class 对象的一个实例
 *      - getName()                            返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称
 *      - Class getSuperClass()                返回当前 Class 对象的父类的 Class 对象
 *      - Class [] getInterfaces()             获取当前 Class 对象的接口
 *      - ClassLoader getClassLoader()         返回该类的类加载器
 *      - Constructor[] getConstructors()      返回一个包含某些 Constructor 对象的数组
 *      - Field[] getDeclaredFields()          返回 Field 对象的一个数组
 *      - Field getDeclaredField()             返回一个声明的属性（可以是私有）
 *      - Method getMethod(Stringname, Class … paramTypes)  返回一个 Method 对象，此对象的形参类型为 paramType
 *
 * @ClassName: B_ClassTest.java
 * @Description:
 * @Author: anpeng
 * @Date: 2024/3/25 11:32
 */
@SuppressWarnings("all")
public class B_ClassTest {
    @Test
    public void testGetClassInstance() throws ClassNotFoundException {
        //方式1
        Class c1 = B_ClassTest.class;
        //方式2
        B_ClassTest obj = new B_ClassTest();
        Class c2 = obj.getClass();
        //方式3
        Class c3 = Class.forName("day11_java_reflection.B_ClassTest");
        //方式4
        Class c4 = ClassLoader.getSystemClassLoader().loadClass("day11_java_reflection.B_ClassTest");

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);
        System.out.println("c4 = " + c4);
        System.out.println(c1 == c2);
        System.out.println(c1 == c3);
        System.out.println(c1 == c4);
    }

    @Test
    public void testMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        String str = "java_bean.day02.Person";
        Class clazz = Class.forName(str);
        Object obj = clazz.newInstance();
        //获取私有属性/方法要使用getDeclaredField/Method
        Field field = clazz.getDeclaredField("name");
        //利用反射访问 私有的 属性/方法 之前，先把获取的 私有的 属性/方法 的权限“打开” ().setAccessible(true)，要不然无法访问。
        field.setAccessible(true);
        field.set(obj, "Peter");
        Object name = field.get(obj);
        System.out.println(name);
    }

}
