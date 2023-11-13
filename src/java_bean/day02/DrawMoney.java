package java_bean.day02;

/**
 * @ClassName: DrawMoney.java
 * @Author: anpeng
 * @Date: 2023/11/1 17:08
 */
public class DrawMoney extends BankTemplateMethod{

    @Override
    public void transact(){
        System.out.println("我要取款！");
    }
}
