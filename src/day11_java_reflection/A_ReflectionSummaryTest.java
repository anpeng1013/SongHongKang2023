package day11_java_reflection;

import org.junit.Test;

/**
 * 1、反射背景
 *      - Java程序中，所有的对象都有两种类型： 编译时类型和运行时类型，而很多时候对象的编译时类型和运行时类型不一致。
 *          -- 例如：Object obj = new String("hello"); 多态，编译时为Object，运行时为String
 *
 *      - 某些变量或形参的声明类型是 Object 类型，但是程序却需要调用该对象运行时类型的方法，该方法不是 Object 中的方法，那么如何解决呢？
 *          -- 方案1：在编译和运行时都完全知道类型的具体信息，在这种情况下，我们可以直接先使用 instanceof 运算符进行判断，再利用强制类型转换符
 *              将其转换成运行时类型的变量即可。
 *          -- 方案2：编译时根本无法预知该对象和类的真实信息，程序只能依靠运行时信息来发现该对象和类的真实信息，这就必须使用反射。
 *
 * 2、反射概述
 *      - Reflection（反射）是被视为动态语言的关键，反射机制允许程序在运行期间借助于 Reflection API 取得任何类的内部信息，并能直接操作任意对象的
 *          内部属性及方法。
 *
 *      - 加载完类之后，在堆内存的方法区中就产生了一个 Class 类型的对象（一个类只有一个 Class 对象），这个对象就包含了完整的类的结构信息。我们可以
 *          通过这个对象看到类的结构。 这个对象就像一面镜子，透过这个镜子看到类的结构，所以，我们形象的称之为：反射。
 *
 *      - 实例化对象的两种方式
 *          -- 以前：引入需要的“包类”名称-->通过new实例化-->取得实例化对象
 *          -- 现在：实例化对象-->getClass方法-->得到完整的“包类”名称
 *
 * 3、反射机制
 *      - java反射机制提供的功能：
 *          -- 在运行时判断任意一个对象所属的类
 *          -- 在运行时构造任意一个类的对象
 *          -- 在运行时判断任意一个类所具有的成员变量和方法
 *          -- 在运行时获取泛型信息
 *          -- 在运行时调用任意一个对象的成员变量和方法
 *          -- 在运行时处理注解
 *          -- 生成动态代理
 *
 * 4、反射相关API
 *      - java.lang.Class：代表一个类
 *      - java.lang.reflect.Method：代表类的方法
 *      - java.lang.reflect.Field：代表类的成员变量
 *      - java.lang.reflect.Constructor：代表类的构造器
 *      - ....
 *
 * 5、反射的优缺点
 *      - 优点
 *          -- 提高了 Java 程序的灵活性和扩展性， 降低了耦合性，提高自适应能力。
 *          -- 允许程序创建和控制任何类的对象，无需提前硬编码目标类。
 *
 *      -缺点
 *          -- 反射的性能较低。反射机制主要应用在对灵活性和扩展性要求很高的系统框架上。
 *          -- 反射会模糊程序内部逻辑， 可读性较差。
 *
 * @ClassName: A_ReflectionSummaryTest.java
 * @Description:
 * @Author: anpeng
 * @Date: 2024/3/25 11:08
 */
@SuppressWarnings("all")
public class A_ReflectionSummaryTest {
    @Test
    public void testSummary(){
        System.out.println("this is reflection summary");
    }

}
