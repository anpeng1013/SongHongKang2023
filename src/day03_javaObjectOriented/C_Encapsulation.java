package day03_javaObjectOriented;

import javaBean.day03.Person;

/**
 * @ClassName: C_Encapsulation.java
 * @Author: anpeng
 * @Date: 2023/4/9 20:39
 *
 * 封装：类将数据和方法只向可信的类或者对象开放，向没必要开放的类或者对象隐藏信息。
 *      实现封装就是控制类或成员的可见性范围。这就需要依赖访问控制修饰符，也叫权限修饰符来控制。
 *
 * 权限修饰符：public(公共)、protected(保护)、default(默认)、private(私有)
 *      修饰符      本类      本包      其他包子类      其他包非子类
 *      private     √         ×          ×               ×
 *      default     √         √          ×               ×
 *      protected   √         √          √               ×
 *      public      √         √          √               √
 *
 * 具体修饰结构：
 *      外部类：public、default。
 *      成员变量、成员方法、构造器、内部类：public、protected、default、private。
 *
 * 成员属性私有化：
 *      开发中，一般成员变量都习惯使用private修饰，再提供相应的public权限的getXxx/setXxx方法访问。
 *      对于final的成员变量，不提供setXxx()方法。对于static final的成员，习惯上使用public修饰。
 *
 */
public class C_Encapsulation {
    public static void main(String[] args) {
        //在其他包的非子类中，只能访问public修饰的类、成员或方法
        Person person;
        //引用数据必须初始化后才可以使用
        person = new Person();
        //直接打印对象  对象类型@对象地址
        System.out.println(person); //javaBean.day03.Person@3b07d329
        //使用set方法赋值私有化的成员变量
        person.setName("anpeng");
        person.setAge(27);
        //使用get方法获取私有化的成员变量
        System.out.println(person.getName() + " " + person.getAge());
    }
}
