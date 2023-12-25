package day07_java_generic;

import java_bean.day07.Circle;
import java_bean.day07.CircleComparatorAfterGeneric;
import java_bean.day07.CircleComparatorBeforeGeneric;
import org.junit.Test;

import java.util.*;

/**
 * 泛型：
 *      引入：在Java中，我们在声明方法时，在定义方法功能时如果有未知数据需要参与，这些未知数据需要在调用方法时才能确定，那么我们把
 *           这样的数据称为参数，通过形参表示。在方法体中，用这个形参名来代表那个某种类型的数据，而调用者在调用时，对应地传入实参就可以了。
 *           受以上启发，JDK1.5设计了泛型的概念，当某个数据的类型不确定时，将这个参数的类型也设置为参数，即类型参数。
 *
 *      举例：
 *          - 集合中使用泛型
 *              集合类在设计阶段/声明阶段不能确定这个容器到底实际存的是什么类型的对象，所以在JDK5.0之前只能把元素类型设计为Object，JDK5.0时
 *              Java引入了“参数化类型（Parameterized type） ”的概念，允许我们在创建集合时指定集合元素的类型。比如：List<String>，这表明
 *              List只能保存字符串类型的对象。Java 泛型可以保证如果程序在编译时没有发出警告，运行时就不会产生ClassCastException异常。即，把不安
 *              全的因素在编译期间就排除了，而不是运行期；既然通过了编译，那么类型一定是符合要求的，就避免了类型转换。同时，代码更加简洁、健壮。
 *              把一个集合中的内容限制为一个特定的数据类型，这就是generic背后的核心思想。
 *
 *          - 接口中使用泛型
 *              java.lang.Comparable 接口和 java.util.Comparator 接口，是用于比较对象大小的接口。这两个接口只是限定了当一个对象大于另一个
 *              对象时返回正整数，小于返回负整数，等于返回 0，但是并不确定是什么类型的对象比较大小。JDK5.0之前只能用Object类型表示，使用时既麻烦
 *              又不安全，因此DK5.0给它们增加了泛型，限定特定类型之间的比较。如下：
 *              public interface Comparable<T>{
 *                  public int compareTo(T o);
 *              }
 *              public interface Comparator<T>{
 *                  public int compare(T o1, T o2);
 *              }
 *              其中<T>就是类型参数，即泛型。
 *
 *      所谓泛型，就是允许在定义类、接口时通过一个标识表示类中某个属性的类型或者是某个方法的返回值或参数的类型。这个类型参数将在使用时（例如，继承
 *      或实现这个接口、创建对象或调用方法时）确定（即传入实际的类型参数，也称为类型实参）。
 *
 * 使用说明：
 *      - 在创建集合对象的时候，可以指明泛型的类型。具体格式为：List<String> list = new ArrayList<String>();
 *      - JDK1.7时具有新特性，上面格式可以简写为：List<String> list = new ArrayList<>();//类型推断。
 *      - 泛型，也称为泛型参数，即参数的类型，只能使用引用数据类型进行赋值。（不能使用基本数据类型，可以使用包装类型替换）
 *
 * @ClassName: A_Summary.java
 * @Author: anpeng
 * @Date: 2023/12/25 10:19
 */
@SuppressWarnings("all")
public class A_SummaryTest {
    @Test
    public void testGenericInList() {
        //泛型在List中的使用--将学生成绩保存在ArrayList中
        //标准写法
        ArrayList<Integer> list = new ArrayList<>();
        list.add(56);//自动装箱
        list.add(76);
        list.add(88);
        list.add(96);
        //list.add("anpeng");//当添加非Integer的数据时，编译报错，编译不通过。
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            //不需要强转，可以直接获取添加时元素的数据类型
            Integer score = iterator.next();
            System.out.println("score = " + score);
        }
    }

    @Test
    public void testGenericInMap(){
        //泛型在Map中的使用
        HashMap<String,Integer> map = new HashMap<>();
        map.put("Tom",67);
        map.put("Jim",56);
        map.put("Rose",88);
        //map.put(67,"Jack");//编译不通过。

        //遍历 key 集
        Set<String> keySet = map.keySet();
        for(String str:keySet){
            System.out.println(str);
        }
        //遍历 value 集
        Collection<Integer> values = map.values();
        Iterator<Integer> iterator = values.iterator();
        while(iterator.hasNext()){
            Integer value = iterator.next();
            System.out.println(value);
        }
        //遍历 entry 集
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator1 = entrySet.iterator();
        while(iterator1.hasNext()){
            Map.Entry<String, Integer> entry = iterator1.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ":" + value);
        }
    }

    @Test
    public void testGenericInComparator(){
        //使用泛型之前
        CircleComparatorBeforeGeneric com = new CircleComparatorBeforeGeneric();
        System.out.println(com.compare(new Circle(5), new Circle(2)));//返回整数 1
        //System.out.println(com.compare("圆1", "圆2"));//运行时异常：ClassCastException。强转失败

        //使用泛型之后
        CircleComparatorAfterGeneric com1 = new CircleComparatorAfterGeneric();
        System.out.println(com1.compare(new Circle(3), new Circle(6)));//返回负数 -1
        //System.out.println(com1.compare("圆1", "圆2"));//编译错误，因为"圆 1"等不是Circle类型，编译器提前报错，而不是冒风险在运行时报错。

    }

}
