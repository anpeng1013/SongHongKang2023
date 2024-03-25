package day11_java_reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 1、类的生命周期
 *      - 类在内存中完整的生命周期：加载-->使用-->卸载。其中加载过程又分为：装载、链接、初始化三个阶段。
 *
 * 2、类的加载过程
 *      - 当程序主动使用某个类时，如果该类还未被加载到内存中，系统会通过加载、链接、初始化三个步骤来对该类进行初始化。如果没有意外，
 *          JVM 将会连续完成这三个步骤，所以有时也把这三个步骤统称为类加载。
 *
 *      - 类的加载分为上阶段
 *          -- 装载：将类的 class 文件读入内存，并为之创建一个 java.lang.Class 对象。此过程由类加载器完成。
 *          -- 链接：分为三个步骤
 *              > 验证 Verify：确保加载的类信息符合 JVM 规范，例如：没有安全方面的问题。
 *              > 准备 Prepare：正式为类变量（static）分配内存并设置类变量默认初始值的阶段，这些内存都将在方法区中进行分配。
 *              > 解析 Resolve：虚拟机常量池内的符号引用（常量名）替换为直接引用（地址）的过程。
 *          -- 初始化：分为三个步骤
 *              > 执行类构造器<clinit>()方法的过程。 类构造器<clinit>()方法是由编译期自动收集类中所有类变量的赋值动作和静态代码块中的语句合并产生的。
 *                  （类构造器是构造类信息的，不是构造该类对象的构造器）。
 *              > 当初始化一个类的时候，如果发现其父类还没有进行初始化，则需要先触发其父类的初始化。
 *              > 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确加锁和同步。
 *
 * 3、类加载器（Classloader）
 *      - 类加载器的作用
 *          -- 将 class 文件字节码内容加载到内存中，并将这些静态数据转换成方法区的运行时数据结构，然后在堆中生成一个代表这个类的 java.lang.Class 对象，
 *              作为方法区中类数据的访问入口。
 *          -- 类缓存：标准的 JavaSE 类加载器可以按要求查找类，但一旦某个类被加载到类加载器中，它将维持加载（缓存）一段时间。不过 JVM 垃圾回收机制
 *              可以回收这些 Class 对象。
 *
 *      - 类加载器的分类（JDK8）
 *          -- JVM 支持两种类型的类加载器，分别为引导类加载器（Bootstrap ClassLoader） 和自定义类加载器（User-Defined ClassLoader）。
 *          -- 从概念上来讲，自定义类加载器一般指的是程序中由开发人员自定义的一类类加载器，但是 Java 虚拟机规范却没有这么定义，而是将所有派生于
 *              抽象类ClassLoader 的类加载器都划分为自定义类加载器。无论类加载器的类型如何划分，在程序中我们最常见的类加载器结构主要是如下情况：
 *              > 启动类加载器（引导类加载器， Bootstrap ClassLoader）
 *                  >> 这个类加载使用 C/C++语言实现的，嵌套在 JVM 内部。获取它的对象时往往返回null
 *                  >> 它用来加载 Java 的核心库（JAVA_HOME/jre/lib/rt.jar 或 sun.boot.class.path 路径下的内容）。用于提供 JVM 自身需要的类。
 *                  >> 并不继承自 java.lang.ClassLoader，没有父加载器。
 *                  >> 出于安全考虑，Bootstrap 启动类加载器只加载包名为 java、 javax、 sun 等开头的类.
 *                  >> 加载扩展类和应用程序类加载器，并指定为他们的父类加载器。
 *              > 扩展类加载器（Extension ClassLoader）
 *                  >> Java 语言编写，由 sun.misc.Launcher$ExtClassLoader 实现。
 *                  >> 继承于 ClassLoader 类
 *                  >> 父类加载器为启动类加载器
 *                  >> 从 java.ext.dirs 系统属性所指定的目录中加载类库，或从 JDK 的安装目录的 jre/lib/ext子目录下加载类库。如果用户创建的 JAR 放
 *                      在此目录下，也会自动由扩展类加载器加载。
 *              > 应用程序类加载器（系统类加载器， AppClassLoader）
 *                  >> java 语言编写，由 sun.misc.Launcher$AppClassLoader 实现
 *                  >> 继承于 ClassLoader 类
 *                  >> 父类加载器为扩展类（平台类）加载器
 *                  >> 它负责加载环境变量 classpath 或系统属性 java.class.path 指定路径下的类库.
 *                  >> 应用程序中的类加载器默认是系统类加载器。
 *                  >> 它是用户自定义类加载器的默认父加载器.
 *                  >> 通过 ClassLoader 的 getSystemClassLoader()方法可以获取到该类加载器
 *              > 用户自定义类加载器（了解）
 *                  >> 在 Java 的日常应用程序开发中，类的加载几乎是由上述3种类加载器相互配合执行的。我们还可以自定义类加载器，来定制类的加载方式。
 *                  >> 体现 Java 语言强大生命力和巨大魅力的关键因素之一便是， Java 开发者可以自定义类加载器来实现类库的动态加载，加载源可以是
 *                      本地的 JAR 包，也可以是网络上的远程资源。
 *                  >> 自定义加载器能够实现应用隔离，例如Tomcat，Spring 等中间件和组件框架都在内部实现了自定义的加载器，并通过自定义加载器隔离不同的
 *                      组件模块。这种机制比C/C++程序要好太多，想不修改C/C++程序就能为其新增功能，几乎是不可能的，一个兼容性便能阻挡住所有美好的设想。
 *                  >> 自定义类加载器通常需要继承于 ClassLoader。
 *
 *          - 查看某个类的类加载器对象
 *              -- 获取默认的系统类加载器
 *                  > ClassLoader classloader = ClassLoader.getSystemClassLoader();
 *              -- 查看某个类是由哪个类加载器加载的
 *                  > ClassLoader classloader = Class.forName("java_bean.ClassloaderDemo").getClassLoader();
 *                  > 如果是根加载器（启动类加载器）加载的类，则会得到null
 *                      >> ClassLoader classloader1 = Class.forName("java.lang.Object").getClassLoader();
 *              -- 获取某个类加载器的父加载器
 *                  > ClassLoader parentClassloader = classloader.getParent();
 *
 * 4、使用ClassLoader获取流
 *      - 关于类加载器的一个主要方法：
 *          -- getResourceAsStream(String str): 获取类路径下的指定文件的输入流
 *
 * @ClassName: C_ClassLoaderTest.java
 * @Description:
 * @Author: anpeng
 * @Date: 2024/3/25 16:29
 */
@SuppressWarnings("all")
public class C_ClassLoaderTest {
    @Test
    public void testClassLoader() throws ClassNotFoundException {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);//jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b

        //获取当前类的加载器（应用程序类的加载器，默认为系统类加载器
        ClassLoader currentClassLoader = Class.forName("day11_java_reflection.C_ClassLoaderTest").getClassLoader();
        System.out.println("currentClassLoader = " + currentClassLoader);//jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b

        //获取当前类的加载器的父类加载器
        //系统类加载器的父类加载器为jdk.internal.loader.ClassLoaders$PlatformClassLoader@47089e5f
        System.out.println("当前类的加载器的父类加载器 = " + C_ClassLoaderTest.class.getClassLoader().getParent());
        
        //获取String类的加载器--为启动类加载器（根类加载器）。所以，返回为null。
        ClassLoader stringClassLoager = String.class.getClassLoader();
        System.out.println("stringClassLoager = " + stringClassLoager);//null
    }

    @Test
    public void testGetResourceAsStream() throws IOException {
        Properties properties = new Properties();
        //方式1：使用字节流，此时默认的相对路径是当前的module
        FileInputStream fis = new FileInputStream("src/info.properties");
        System.out.println("fis = " + fis);

        //方式2：使用类的加载器，此时默认的相对路径是当前module下的src目录
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("info.properties");
        properties.load(is);
        //获取配置文件信息
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        System.out.println("name = " + name);
        System.out.println("password = " + password);
    }

}
