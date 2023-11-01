package java_bean.day03;

/**
 * @ClassName: Child.java
 * @Author: anpeng
 * @Date: 2023/11/1 16:04
 */
public class Child extends Father {//继承final修饰的Eunuch类时，会报错。
    //public void notOverride(){}; //重写父类中的notOverride方法会报错，因为是final修饰的。
    private final String GENDER;

    public Child(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getGENDER() {
        return GENDER;
    }
}
