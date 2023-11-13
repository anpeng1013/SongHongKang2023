package day03_java_exception;

import java_bean.day02.Person;
import java_bean.day03.FuShuException;
import org.junit.Test;

import java.util.Scanner;

/**
 * 为什么需要自定义异常
 *      Java 中不同的异常类，分别表示着某一种具体的异常情况。那么在开发中总是有些异常情况是核心类库中没有定义好的，此时我们需要根据自己
 *      业务的异常情况来定义异常类。例如年龄负数问题，考试成绩负数问题，某员工已在团队中等。
 *
 * 如何自定义异常类
 *      1、需要继承一个异常类型
 *          * 自定义一个编译时异常类型：自定义类继承 java.lang.Exception。
 *          * 自定义一个运行时异常类型：自定义类继承 java.lang.RuntimeException。
 *      2、建议提供两个构造器，一个是无参构造器，一个是(String message)构造器。
 *      3、自定义异常类需要提供serialVersionUID。
 *
 * 注意点：
 *      1、自定义的异常的对象只能通过throw手动抛出，即JVM不会自动生成。抛出后由 try..catch 处理，也可以甩锅 throws 给调用者处理。
 *      2、自定义异常最重要的是异常类的名字和message属性。当异常出现时可以根据名字判断异常类型。比如：FuShuException("年龄为负，重新设置");
 *          、TeamException("该员工已是某团队成员");
 *
 * @ClassName: CCustomExceptionTest.java
 * @Author: anpeng
 * @Date: 2023/11/8 16:45
 */
@SuppressWarnings("all")
public class C_CustomExceptionTest {
    @Test
    public void test() throws FuShuException {
        createPerson();//对抛出编译时异常的方法，要么try-catch捕获处理，要么继续抛出
    }

    public void createPerson() throws FuShuException{
        Person person = new Person();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = scanner.next();
        person.setName(name);
        System.out.println("请输入年龄：");
        int age = scanner.nextInt();
        if (age < 0)
            throw new FuShuException("年龄为负，重新设置");
        person.setAge(age);
        person.speak();
    }
}
