package day04_java_exception;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 异常处理：
 *      在编写程序时，经常要在可能出现错误的地方加上检测的代码，如进行 x/y 运算时，要检测分母为0，数据为空，输入的不是数字而是字符等。
 *      过多的if-else分支会导致程序的代码加长、臃肿，可读性差，程序员需要花很大的精力“堵漏洞”。因此采用异常处理机制。
 *
 *      java异常处理：
 *          Java 采用的异常处理机制，是将异常处理的程序代码集中在一起，与正常的程序代码分开，使得程序简洁、优雅，并易于维护。
 *
 *      java异常处理方法：提供了异常处理的抓抛模型
 *          方式一[捕获异常]：try-catch-finally
 *              * 如上述，Java程序的执行过程中如出现异常，会生成一个异常类对象，该对象将被提交给Java运行时系统，这个过程称为抛出(throw)异常。
 *              * 如果一个方法内抛出异常，该对象会被抛给调用者方法去处理。如果异常没有在调用者方法中处理，它继续被抛给这个调用方法的上层方法。
 *                  这个过程将一直继续下去，直到异常被处理。这个被处理的过程称为捕获(catch)异常。
 *              * 如果一个异常被抛回到main()方法，并且main()也不处理，则程序运行终止。
 *
 *              捕获异常语法格式：
 *                  try{
 *                      ...... //可能产生异常的代码
 *                  }
 *                  catch(异常类型1 e){
 *                      ...... //当产生异常类型1的异常时的处置措施
 *                  }
 *                  catch(异常类型2 e){
 *                      ...... //当产生异常类型2的异常时的处置措施
 *                  }
 *                  finally{
 *                      ...... //无论是否发生异常，都无条件执行的语句
 *                  }
 *
 *              1、整体执行过程：当某段代码可能发生异常，不管这个异常是编译时异常（受检异常）还是运行时异常（非受检异常），我们都可以
 *              使用try语句块将它括起来，并在try语句块下面编写catch分支尝试捕获对应的异常对象。
 *                  * 如果在程序运行时，try语句块中的代码没有发生异常，那么catch所有的分支都不执行。
 *                  * 如果在程序运行时，try语句块中的代码发生了异常，根据异常对象的类型，将从上到下选择第一个匹配的catch分支执行。此时try中
 *                      发生异常的语句下面的代码将不执行，而整个try...catch之后的代码可以继续运行。
 *                  * 如果在程序运行时，try语句块中的代码发生了异常，但是所有catch分支都无法匹配（捕获）这个异常，那么JVM将会终止
 *                      当前方法的执行，并把异常对象“抛”给调用者。如果调用者都不处理，程序就挂了。
 *
 *              2、try:
 *                  捕获异常的第一步是用 try{…}语句块选定捕获异常的范围，将可能出现异常的业务逻辑代码放在try语句块中
 *
 *              3、catch(Exception e)
 *                  * catch分支有两个部分，catch()中编写异常类型和异常参数名，{}中编写如果发生了这个异常，要做什么处理的代码。
 *                  * 如果明确知道产生的是何种异常，可以用该异常类作为catch的参数；也可以用其父类作为catch的参数。
 *                      比如：可以用ArithmeticException类作为参数的地方，就可以用RuntimeException类作为参数，或者用所有异常的父类
 *                      Exception类作为参数。但不能是与ArithmeticException类无关的异常，如NullPointerException(catch中的语句将不会执行)。
 *                  * 每个try语句块可以伴随一个或多个catch语句，用于处理可能产生的不同类型的异常对象。
 *                  * 如果有多个catch分支，并且多个异常类型有父子类关系，必须保证小的子异常类型在上，大的父异常类型在下。否则，报错。
 *                  * catch中常用的异常处理方式
 *                      public String getMessage()：获取异常的描述信息，返回字符串.
 *                      public void printStackTrace()：打印异常的跟踪栈信息并输出到控制台。包含了异常的类型、异常的原因、还包括异常
 *                      出现的位置，在开发和调试阶段，都得使用printStackTrace()。
 *
 *              4、finally：
 *                  * 不论在try代码块中是否发生了异常事件，catch语句是否执行，catch语句是否有异常，catch语句中是否有return，
 *                      finally块中的语句都会被执行。
 *                  * 因为异常会引发程序跳转，从而会导致有些语句执行不到。而程序中有一些特定的代码无论异常是否发生，都需要执行。
 *                      例如，数据库连接、输入流输出流、Socket连接、Lock锁的关闭等，这样的代码通常就会放到finally块中。所以，我们通常将一定
 *                      要被执行的代码声明在finally中。[资源的获取放在try后面的括号中可以实现自动资源管理，不必在finally中释放资源了]
 *                  * finally语句和catch语句是可选的，但finally不能单独使用。
 *
 *              5、try-catch原则：
 *                  * 前面使用的异常大都是RuntimeException类或是它的子类，这些类的异常的特点是：即使没有使用try和catch捕获，Java自己
 *                      也能捕获，并且编译通过(但运行时会发生异常使得程序运行终止)。所以，对于这类异常可以不作处理，因为这类异常很普遍，
 *                      若全处理可能会对程序的可读性和运行效率产生影响。
 *                  * 如果抛出的异常是IOException等类型的非运行时异常，则必须捕获，否则编译错误。也就是说，我们必须处理编译时异常，
 *                      将异常进行捕捉，转化为运行时异常。
 *
 *
 *          方式二[抛出异常]：throws + 编译时异常[注意！抛出异常主要针对的是编译时异常]
 *              * 如果在编写方法体的代码时，某句代码可能发生某个编译时异常，不处理编译不通过，但是在当前方法体中可能不适合处理或
 *                  无法给出合理的处理方式，则此方法应显示地声明抛出异常，表明该方法将不对这些异常进行处理，而由该方法的调用者负责处理。
 *              * 具体方式：在方法声明中用throws语句声明抛出异常的列表，throws后面的异常类型可以是方法中产生的异常类型，也可以是它的父类。
 *
 *              抛出异常语法格式：
 *                  修饰符 返回值类型 方法名(参数) throws 异常类名 1,异常类名 2…{ }
 *                      * 在throws后面可以写多个异常类型，用逗号隔开。
 *                      * throws后面也可以写运行时异常类型，只是运行时异常类型，写或不写对于编译器和程序执行来说都没有任何区别，都可以
 *                          编译通过并执行。如果写了，唯一的区别就是调用者调用该方法后，使用try...catch结构时，IDEA可以获得更多的信息，
 *                          需要添加哪种catch分支。
 *
 *              总结：throws主要是针对编译时异常，将当前方法中可能出现的编译时异常抛出去，该方法的调用者要么try-catch处理，调用者要么
 *                  不处理继续抛出，一直抛到main方法，main方法也可以继续抛出不作处理，但一直抛出不处理，程序在编译时发生异常则会终止。
 *
 *              方法重写中throws的要求：
 *                  方法重写中，对于方法签名是有严格要求的。复习：
 *                      1、方法名必须相同
 *                      2、形参列表必须相同
 *                      3、返回值类型
 *                          * 基本数据类型和void：必须相同
 *                          * 引用数据类型：<= 父类返回类型
 *                      4、权限修饰符：>= 父类权限修饰符，而且要求父类被重写的方法在子类中是可见的。
 *                      5、不能是static，final修饰的方法，因为静态方法和final方法不能被重写。
 *                   此外，对于throws异常列表的要求：
 *                      * 如果父类被重写方法的方法签名后面没有"throws 编译时异常类型"，那么重写方法时，方法签名后面也不能出现
 *                          "throws编译时异常类型"。
 *                      * 如果父类被重写方法的方法签名后面有"throws 编译时异常类型"，那么重写方法时，throws的编译时异常类型
 *                          必须 <= 被重写方法throws的编译时异常类型，或者不throws编译时异常。
 *                      * 方法重写，对于“throws 运行时异常类型”没有要求。
 *
 *          两种异常处理方式的选择：对于异常，使用相应的处理方式。此时的异常，主要指的是编译时异常。
 *              * 如果程序代码中，涉及到资源的调用(流、数据库连接、网络连接等)，则必须考虑使用try-catch-finally来处理，保证不出现内存泄漏。
 *              * 如果父类被重写的方法没有throws异常类型，则子类重写的方法中如果出现异常，只能考虑使用try-catch-finally进行处理，不
 *                  能throws。因为父类中没有异常，子类选择抛出异常，就大于父类的异常了。
 *              * 开发中，方法a中依次调用了方法 b,c,d 等方法，方法 b,c,d 之间是递进关系。此时，如果方法b,c,d中有异常，我们通常选择
 *                  使用throws，而方法a中通常选择使用try-catch-finally。
 *
 *      手动抛出异常对象：throw new 异常类名([参数])
 *          Java 中异常对象的生成有两种方式：
 *              * 由虚拟机自动生成：程序运行过程中虚拟机检测到程序发生了问题，针对当前代码会在后台自动创建一个对应异常类的实例对象并抛出。
 *              * 由开发人员手动创建： new 异常类型([实参列表]);，如果创建好的异常对象不抛出对程序没有任何影响，和创建一个普通对象一样，
 *                  但是一旦throw抛出，就会对程序运行产生影响了。
 *
 *          throw 语句抛出的异常对象，和 JVM 自动创建和抛出的异常对象一样。
 *              * 如果是编译时异常类型的对象，同样需要使用 throws 或者 try...catch 处理，否则编译不通过。
 *              * 如果是运行时异常类型的对象，编译器不提示，可以编译通过。
 *              * 可以抛出的异常必须是 Throwable 或其子类的实例。
 *
 * @ClassName: BTryCatchAndThrowsTest.java
 * @Author: anpeng
 * @Date: 2023/11/7 14:57
 */
@SuppressWarnings("all")
public class B_TryCatchAndThrowsTest {
    int x;

    //测试try catch finally 捕获运行时异常
    @Test
    public void test01(){
        String[] friends = { "lisa", "tom", "huli" };
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(friends[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index err");
        }
        System.out.println("this is the end");
    }

    @Test
    @SuppressWarnings("all")
    public void test02(){
        int y;
        B_TryCatchAndThrowsTest tct = new B_TryCatchAndThrowsTest();
        try {
            y = 3 / tct.x;
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
        System.out.println("program ends ok!");
    }

    @Test
    @SuppressWarnings("all")
    public void test03(){
        try{
            String str1 = "huawei.com";
            str1 = null;
            System.out.println(str1.charAt(0));
        }catch(NullPointerException e){
            //异常的处理方式 1
            System.out.println("不好意思，亲~出现了小问题，正在加紧解决...");
        }catch(ClassCastException e){
            //异常的处理方式 2
            System.out.println("出现了类型转换的异常");
        }catch(RuntimeException e){
            //异常的处理方式 3
            System.out.println("出现了运行时异常");
        }
        //此处的代码，在异常被处理了以后，是可以正常执行的
        System.out.println("hello, welcome to ...");
    }

    @Test
    public void test04(){
        //资源的获取放在try后面的括号中可以实现自动资源管理，不必在finally中释放资源了
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("请输入第一个整数： ");
            int a = input.nextInt();
            System.out.print("请输入第二个整数： ");
            int b = input.nextInt();
            int result = a / b;
            System.out.println(a + "/" + b + "=" + result);
        } catch (InputMismatchException e) {
            System.out.println("数字格式不正确，请输入两个整数");
        } catch (ArithmeticException e) {
            System.out.println("第二个整数不能为 0");
        } finally {
            System.out.println("程序结束，释放资源");
        }
    }

    @Test
    public void test05(){
        System.out.println(test("2"));
    }

    @SuppressWarnings("all")
    public int test(String str){//只要有finally，不管是否异常，函数一定返回的finally语句块中return值，因为函数返回值只有一个。
        try {
            Integer.parseInt(str);
            return 1;
        }catch (NumberFormatException e) {
            return -1;
        }finally {
            System.out.println("test结束");
            return 0;
        }
    }

    //测试try catch finally 捕获编译时异常
    @Test
    public void test06(){
        FileInputStream fis = null;
        try{
            File file = new File("anpeng.txt");
            fis = new FileInputStream(file);//FileNotFoundException
            int b = fis.read();//IOException
            while(b != -1){
                System.out.print((char)b);
                b = fis.read();//IOException
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                if(fis != null)
                    fis.close();//IOException
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //测试throws 抛出异常
    //针对编译时异常
    @Test
    public void test07(){
        System.out.println("上课...");
        try {
            afterClass();//换到这里来处理抛出的中断异常
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("准备提前上课");
        }
        System.out.println("上课...");
    }

    public void afterClass() throws InterruptedException{
        for (int i = 10; i >= 1 ; i--) {
            Thread.sleep(1000);//本来应该在这里处理中断异常
            System.out.println("距离上课还有：" + i + "分钟");
        }
    }

    //针对运行时异常 抛不抛出都无所谓
    @Test
    public void test08(){
        try (Scanner input = new Scanner(System.in)){
            System.out.println("请输入第一个整数：");
            int a = input.nextInt();
            System.out.println("请输入第二个整数：");
            int b = input.nextInt();
            int result = divide(a, b);
            System.out.println(a + "/" + b + "=" + result);
        }catch (InputMismatchException | ArithmeticException e){
            e.printStackTrace();
        }
    }

    public int divide(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
