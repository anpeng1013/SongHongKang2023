package java_bean.day03;

import org.junit.Test;

import java.util.Scanner;

/**
 * 编写和运行@Test单元测试方法:
 *      JUnit4版本要求，@Test标记的方法必须满足如下要求
 *          * 所在的类必须是public的，非抽象的，包含唯一的无参构造器。
 *          * @Test标记的方法本身必须是public，非抽象的，非静态的，void无返回的，无参数的
 *          * 测试某项功能时，建议单独创建一个单元测试类。
 *
 * 设置执行JUnit用例时支持控制台输入：
 *      默认情况下，在单元测试方法中使用Scanner时，并不能实现控制台数据的输入。 需要做如下设置：
 *          在idea64.exe.vmoptions 配置文件中加入下面一行设置：-Deditable.java.test.console=true
 *          配置文件位置：Help -> Edit Custom VM Options...
 *          注意一定要重启idea后才能生效！！！
 *
 * @ClassName: JUnitTest.java
 * @Author: anpeng
 * @Date: 2023/11/6 10:38
 */
public class JUnit4Test {
    @Test
    public void junitTest(){
        System.out.println("测试某项功能时，建议单独创建一个单元测试类。");
    }

    @Test
    public void testScanner(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = scanner.next();
        System.out.println("您的姓名是：" + name);
    }

    @Test
    public void testTemplates(){
        System.out.println("自定义测试模板");
    }

}
