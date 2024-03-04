package day06_java_container;

import java_bean.day06.MyDate;
import java_bean.day06.User;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Collection的子接口：Set
 *      特性：
 *          - Set接口是Collection的子接口，元素的添加位置具有无序性，Set接口相较于Collection接口没有提供额外的方法。
 *          - Set集合不允许包含相同的元素，如果试把两个相同的元素加入同一个Set集合中，会导致添加操作失败。
 *          - Set集合支持的遍历方式和Collection集合一样：foreach和Iterator。
 *          - Set的常用实现类有： HashSet、 TreeSet、 LinkedHashSet。
 *
 * Set 和 Map 的关系
 *      - Set的内部其实就是一个Map。Set中的元素存储在Map的key中
 *      - 即HashSet的内部实现是一个HashMap，TreeSet的内部实现是一个TreeMap，LinkedHashSet的内部实现是一个LinkedHashMap。
 *
 * Set的主要实现类：HashSet
 *      概述：
 *          - HashSet是Set接口的主要实现类，大多数时候使用Set集合时都使用这个实现类。
 *          - HashSet按Hash算法来存储集合中的元素，因此具有很好的存储、查找、删除性能。
 *          - HashSet具有以下特点：
 *              -- 不能保证元素的排列顺序。
 *              -- HashSet不是线程安全的。
 *              -- 集合元素可以是null。
 *              -- 存储的元素必须重写hashCode和equals方法。
 *          - HashSet集合判断两个元素相等的标准：两个对象通过hashCode()方法得到的哈希值相等，且两个对象的equals()方法返回值为true。
 *          - 对于存在Set容器中的对象，对应的类一定要重写hashCode()和equals(Object obj)方法，以实现对象相等规则。即：相等的对象必须有相同的散列码。
 *
 *      HashSet添加元素的过程：
 *          1、当向HashSet集合中存入一个元素时，HashSet会调用该对象的hashCode()方法对该对象求哈希，得到该对象的hashCode值，然后根据hashCode值，
 *             通过某个哈希函数对hashCode值求哈希，以决定该对象在HashSet底层数组中的存储位置（即对hashCode值再求一次哈希）。
 *          2、如果底层数组的存储的位置上没有元素，则直接可以添加成功。
 *          3、如果底层数组的存储的位置上有元素，则进行比较：
 *              - 两个元素的hashCode值不相等（对hashCode值求哈希时，不同hashCode值哈希到同一个存储位置），则添加成功（拉链法解决哈希冲突）。
 *              - 两个元素的hashCode值相等，则会继续调用equals()方法：
 *                  -- equals()方法结果为false（对元素求哈希时，不同元素哈希到同一个hashCode值），则添加成功（拉链法解决哈希冲突）。
 *                  -- equals()方法结果为true（集合中已存在相同元素），则添加失败。
 *
 *      重写hashCode()方法的基本原则：
 *          1、在程序运行时，同一个对象多次调用hashCode()方法应该返回相同的值。
 *          2、当两个对象的equals()方法比较返回true时，这两个对象的hashCode()方法的返回值也应相等，反之不然。
 *          3、对象中用作equals()方法比较的Field，都应该用来计算hashCode值。
 *
 *      重写equals()方法的基本原则：
 *          1、重写equals方法的时候一般都需要重写hashCode方法。通常参与计算hashCode的对象的属性也应该参与到equals()中进行计算。
 *          2、推荐：开发中直接调用IDEA里的快捷键（Alt+insert）自动重写equals()和hashCode()方法即可。
 *
 * Set的主要实现类：LinkedHashSet
 *      - LinkedHashSet是HashSet的子类，不允许集合元素重复。
 *      - LinkedHashSet根据元素的hashCode值来决定元素的存储位置，但它同时使用双向链表维护元素的次序，即遍历集合时以添加顺序进行访问的。
 *      - LinkedHashSet 插入性能略低于 HashSet，但在迭代访问 Set 里的全部元素时有很好的性能。
 *
 * Set的主要实现类：TreeSet
 *      - TreeSet是SortedSet接口的实现类，TreeSet可以按照添加元素的指定属性的大小顺序进行遍历。
 *      - TreeSet底层使用红黑树结构存储数据，没有哈希表，故不需要重写equals和hashCode。
 *      - 因为只有相同类的两个实例才会比较大小，所以向 TreeSet 中添加的应该是同一个类的对象。
 *      - TreeSet 特点：不允许重复、实现排序（自然排序或定制排序）。
 *      - TreeSet 两种排序方法：自然排序和定制排序（参见day05_java_library/D_CompareTest.java）。默认情况下，TreeSet采用自然排序（升序）。
 *
 * @ClassName: E_SetTest.java
 * @Author: anpeng
 * @Date: 2023/12/20 21:41
 */
@SuppressWarnings("all")
public class E_SetTest {
    @Test
    public void testHashSet(){
        HashSet hashSet = new HashSet();
        hashSet.add(new MyDate(2021, 1, 1));
        hashSet.add(new MyDate(2021, 1, 1));
        hashSet.add(new MyDate(2021, 2, 4));
        hashSet.add(new MyDate(2018, 11, 24));
        System.out.println("hashSet = " + hashSet);//无序，不重复。
    }

    @Test
    public void testLinkedHashSet(){
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("张三");
        linkedHashSet.add("张三");
        linkedHashSet.add("李四");
        linkedHashSet.add("王五");
        linkedHashSet.add("王五");
        linkedHashSet.add("赵六");
        System.out.println("linkedHashSet = " + linkedHashSet);//不允许重复，但遍历时按指针访问，体现添加顺序。
    }

    @Test
    public void testTreeSet(){
        //自然排序--按照User的年龄进行升序排序
        TreeSet set = new TreeSet();
        //User类没有重写equals和hashCode方法，TreeSet也能去重，
        set.add(new User("Tom",12));//因为User类中重写compareTo方法时，同时比较了name和age，相当于同时对name和age进行哈希。
        set.add(new User("Rose",23));
        set.add(new User("Jerry",22));
        set.add(new User("anpeng",12));
        //set.add("Tom");//报 ClassCastException 的异常
        System.out.println("set = " + set);

        //定制排序--按照User的姓名进行升序排序
        Comparator comparator = new Comparator(){
            @Override
            public int compare(Object o1, Object o2){
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    //相当于只对name进行哈希，只要name相同就会被去掉，不管年龄是否一致。
                    return u1.getName().compareTo(u2.getName());//String是自然排序-compareTo
                }
                throw new RuntimeException("出入的类型不匹配");
            }
        };
        TreeSet treeSet = new TreeSet(comparator);
        treeSet.add(new User("anpeng",27));
        treeSet.add(new User("Rose",23));
        treeSet.add(new User("Jerry",22));
        treeSet.add(new User("anpeng",28));
        System.out.println("treeSet = " + treeSet);// 只要name相同就会被去掉，不管年龄是否一致。


    }

}
