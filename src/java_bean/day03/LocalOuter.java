package java_bean.day03;

/**
 * @ClassName: LocalOuter.java
 * @Author: anpeng
 * @Date: 2023/11/3 21:26
 */
public class LocalOuter {
    public static void outMethod(){
        final String str = "局部常量str";
        class Inner{
            public void inMethod(){
                System.out.println("Inner.inMethod");
                System.out.println(str);
            }
        }
        Inner inner = new Inner();
        inner.inMethod();
    }

    public void outTest(){
        class Inner{
            public void inMethod1(){
                System.out.println("Inner.inMethod1");
            }
        }
        Inner inner = new Inner();
        inner.inMethod1();
    }

    public static Runner getRunner(){
        class LocalRunner implements Runner{
            @Override
            public void run() {
                System.out.println("LocalRunner.run");
            }
        }
        return new LocalRunner();
    }
}
