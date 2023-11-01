package java_bean.day03;

/**
 * @ClassName: BankTemplateMethod.java
 * @Author: anpeng
 * @Date: 2023/11/1 17:03
 */
public abstract class BankTemplateMethod {
    //具体方法
    public void takeNumber(){
        System.out.println("取号排队");
    }

    //抽象方法
    public abstract void transact();//不同子类，不同业务--钩子方法。

    //具体方法
    public void evaluate(){
        System.out.println("反馈评分");
    }

    //模板方法，把基本操作组合到一起，final修饰后子类不能重写
    public final void process(){
        this.takeNumber();
        this.transact();//像个钩子，具体执行时，挂哪个子类，就执行哪个子类的实现代码。
        this.evaluate();
    }
}
