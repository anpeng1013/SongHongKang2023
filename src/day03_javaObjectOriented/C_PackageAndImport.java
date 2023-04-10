package day03_javaObjectOriented;

/**
 * @ClassName: B_PackageAndImport.java
 * @Author: anpeng
 * @Date: 2023/4/9 16:55
 *
 * package：称为包，用于指明该文件中定义的类、接口等结构所在的包。
 *      格式：package 顶层包名.子包名;
 *      说明：
 *          1、一个源文件只能有一个声明包的package语句，并作为Java源文件的第一条语句出现。
 *          2、包对应于文件系统的目录，package 语句中用 “.” 来指明包(目录)的层次，每.一次就表示一层文件目录。
 *          3、包可以包含类、接口和子包，划分项目层次，便于管理
 *          4、包帮助管理大型软件系统：将功能相近的类划分到同一个包中。比如：MVC的设计模式
 *          5、包解决类命名冲突的问题：即同一个包下，不能定义同名的结构（类、接口）。
 *          6、包控制访问权限。
 *      JDK中主要的包(也叫API):
 *          java.lang----包含一些Java语言的核心类，如String、Math、IntegerSystem和Thread，提供常用功能。
 *          java.net----包含执行与网络相关的操作的类和接口。
 *          java.io ----包含能提供多种输入/输出功能的类。
 *          java.util----包含一些实用工具类，如定义系统特性、接口的集合框架类、使用与日期日历相关的函数。
 *          java.sql----包含了java进行JDBC数据库编程的相关类/接口。
 *          java.awt----包含构成抽象窗口工具集(abstract window toolkits)的多个类，被用来构建和管理应用程序的图形用户界面(GUI)。
 *
 *
 *
 * import：导入，为了使用定义在其他包中的类，需要import语句来显示引入指定包下所需的类。
 *      格式：import 包名.类名;
 *      说明：
 *
 */
public class C_PackageAndImport {
    public static void main(String[] args) {

    }

}
