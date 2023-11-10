package day03_java_oop;

/**
 * 什么是注解：
 *      注解（Annotation）是从 JDK1.5 开始引入，以“@注解名”在代码中存在。例如：
 *          @ Override（重写）
 *          @ Deprecated（标记过时）
 *          @ SuppressWarnings(value=”unchecked”)（忽略警告）
 *      Annotation可以像修饰符一样被使用，可用于修饰包、类、构造器、方法、成员变量、参数、局部变量的声明。还可以添加一些参数值，
 *      这些信息被保存在Annotation的 “name=value” 对中。注解可以在类编译、运行时进行加载，体现不同的功能。
 *
 * 注解与注释：
 *      注解也可以看做是一种注释(comment)，通过使用Annotation，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。
 *      但是，注解，不同于单行注释和多行注释。
 *          * 单行注释和多行注释是给程序员看的。
 *          * 而注解是可以被编译器或其他程序读取的。程序还可以根据注解的不同，做出相应的处理。
 *
 * 注解的重要性：
 *      * 在JavaSE中，使用注解的目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android中注解占据了更重要的角色，
 *      例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗代码和XML配置等。
 *      * 未来的开发模式都是基于注解的， JPA是基于注解的， Spring2.5以上都是基于注解的， Hibernate3.x 以后也是基于注解的，
 *      Struts2有一部分也是基于注解的了。 注解是一种趋势，一定程度上可以说： 框架 = 注解 + 反射 + 设计模式。
 *
 * 常见的Annotation作用
 *      * 生成文档相关的注解
 *          @ author: 标明开发该类模块的作者，多个作者之间使用,分割
 *          @ version: 标明该类模块的版本
 *          @ see: 参考转向，也就是相关主题
 *          @ since: 从哪个版本开始增加的
 *          @ param: 对方法中某参数的说明，如果没有参数就不能写
 *          @ return: 对方法返回值的说明，如果方法的返回值类型是 void 就不能写?
 *          @ exception: 对方法可能抛出的异常进行说明 ，如果方法没有用throws显式抛出的异常就不能写
 *
 *      * 在编译时进行格式检查(JDK 内置的三个基本注解)
 *          @ override: 限定重写父类方法，该注解只能用于方法。
 *              * 用于检测被标记的方法为有效的重写方法，如果不是，则报编译错误！
 *              * 只能标记在方法上。它会被编译器程序读取。
 *          @ Deprecated: 用于表示所修饰的元素(类，方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择。
 *              * 用于表示被标记的数据已经过时，不推荐使用。
 *              * 可以用于修饰 属性、方法、构造、类、包、局部变量、参数。它会被编译器程序读取。
 *          @ SuppressWarnings: 抑制编译器警告
 *              * 当我们不希望看到警告信息的时候，可以使用SuppressWarnings注解来抑制警告信息。
 *              * 可以用于修饰类、属性、方法、构造、局部变量、参数。它会被编译器程序读取。
 *              * 可以指定的警告类型有：
 *                  all，抑制所有警告
 *                  unchecked，抑制与未检查的作业相关的警告
 *                  unused，抑制与未用的代码及停用的代码相关的警告
 *                  deprecation，抑制与淘汰的相关警告
 *                  nls，抑制与非nls字串文字相关的警告
 *                  null，抑制与空值分析相关的警告
 *                  static-access，抑制与静态存取不正确相关的警告
 *                  static-method，抑制与可能宣告为 static 的方法相关的警告
 *
 *      * 跟踪代码依赖性，实现替代配置文件功能
 *          Servlet3.0 提供了注解(annotation)，使得不再需要在web.xml文件中进行Servlet的部署。
 *
 * 元注解：JDK1.5在java.lang.annotation包定义了4个标准的meta-annotation类型，它们被用来提供对其它annotation类型作出说明。
 *      1 @ Target：用于描述注解的使用范围
 *          * 可以通过枚举类型 ElementType 的 10 个常量对象来指定
 *          * TYPE， METHOD， CONSTRUCTOR， PACKAGE.....
 *      2 @ Retention：用于描述注解的生命周期
 *          * 可以通过枚举类型RetentionPolicy的3个常量对象来指定：SOURCE(源代码)、CLASS(字节码)、RUNTIME(运行时)
 *          * 唯有RUNTIME阶段才能被反射读取到。
 *      3、@ Documented：表明这个注解应该被Javadoc工具记录
 *      4、@ Inherited：允许子类继承父类中的注解
 *
 * 自定义注解的使用：
 *      一个完整的注解应该包含三个部分：（1）声明、（2）使用、（3）读取
 *
 *      语法格式：
 *          [元注解]
 *          [修饰符] @interface 注解名{
 *              [成员列表]
 *          }
 *          * 自定义注解可以通过四个元注解@Retention，@Target，@Inherited，@Documented，分别说明它的生命周期，使用位置，
 *              是否被继承，以及是否被生成到 API 文档中。
 *          * Annotation 的成员在 Annotation 定义中以无参数有返回值的抽象方法的形式来声明，我们又称为配置参数。返回值类型只能是
 *              八种基本数据类型、 String类型、 Class类型、 enum类型、 Annotation类型、或以上类型的数组。
 *          * 可以使用default关键字为抽象方法指定默认返回值。
 *          * 如果定义的注解含有抽象方法，那么使用时必须指定返回值，除非它有默认值。格式是“方法名 = 返回值”，如果只有一个抽象方法
 *              需要赋值，且方法名为 value，可以省略“value=”，所以如果注解只有一个抽象方法成员，建议使用value方法名。
 *
 *      读取和处理自定义注解
 *          * 自定义注解必须配上注解的信息处理流程才有意义。
 *          * 我们自己定义的注解，只能使用反射的代码读取。所以自定义注解的声明周期必须是 RetentionPolicy.RUNTIME。
 *          * 具体的使用见《尚硅谷_宋红康_第 17 章_反射机制.md》。
 *
 * JUnit单元测试
 *      测试分类：
 *          黑盒测试：不需要写代码，给输入值，看程序是否能够输出期望的值。
 *          白盒测试： 需要写代码的。关注程序具体的执行流程。
 *
 *      JUnit单元测试：JUnit是由Erich Gamma和Kent Beck编写的一个测试框架（regression testing framework），供Java开发人员
 *          编写单元测试之用。JUnit 测试是程序员测试，即所谓白盒测试，因为程序员知道被测试的软件如何(How)完成功能和完成什么样(What)的功能。
 *          要使用 JUnit，必须在项目的编译路径中引入JUnit的库，即相关的.class文件组成的jar包。 jar就是一个压缩包，压缩包都是开发好的
 *          第三方(Oracle公司第一方，我们自己第二方，其他都是第三方)工具类，都是以class文件形式存在的。引入本地 JUnit.jar的方法见PDF文件。
 *
 *      编写和运行@Test单元测试方法:
 *          JUnit4版本要求，@Test标记的方法必须满足如下要求
 *              * 所在的类必须是public的，非抽象的，包含唯一的无参构造器。
 *              * @Test标记的方法本身必须是public，非抽象的，非静态的，void无返回的，无参数的
 *              * 测试某项功能时，建议单独创建一个单元测试类。
 *
 *      设置执行JUnit用例时支持控制台输入：
 *          默认情况下，在单元测试方法中使用Scanner时，并不能实现控制台数据的输入。 需要做如下设置：
 *              在idea64.exe.vmoptions 配置文件中加入下面一行设置，重启 idea 后生效。-Deditable.java.test.console=true
 *              配置文件位置：Help -> Edit Custom VM Options...
 *
 *
 * @ClassName: N_Annotation.java
 * @Author: anpeng
 * @Date: 2023/11/4 15:32
 */
public class O_Annotation {
    /**
     * @Title: main
     * @Description: 程序主入口
     * @Author: anpeng
     * @DateTime: 2023/11/5 15:11
     * @Param args String[] 命令行参数
     * @Return void
     * @Throws 无
     */
    public static void main(String[] args) {
        System.out.println(getArea(3));
        @SuppressWarnings("unused")
        int a = 10;
    }
    /**
     * @Title: getArea
     * @Description: 求圆的面积
     * @Author: anpeng
     * @DateTime: 2023/11/5 15:14
     * @Param radius double 半径值
     * @Return double 圆的面积
     * @Throws 无
     */
    public static double getArea(double radius){
        return Math.PI * radius *radius;
    }

    //Junit单元测试，见test/java_bean.day03/JUnitTest.java
}
