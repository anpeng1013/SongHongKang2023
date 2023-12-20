package day06_java_container;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Iterator接口：
 *      * 在程序开发中，经常需要遍历集合中的所有元素。针对这种需求，JDK专门提供了一个接口java.util.Iterator。 Iterator接口也是Java集合中
 *        的一员，但它与Collection、 Map 接口有所不同。
 *          - Collection 接口与 Map 接口主要用于存储元素。
 *          - Iterator，被称为迭代器接口，本身并不提供存储对象的能力，主要用于遍历 Collection 中的元素。可以删除元素，但不能修改。
 *
 *      * Collection接口继承了Iterable接口，该接口有一个 iterator()方法，那么所有实现了Collection接口的集合类都有一个 iterator()方法，
 *        用以返回一个实现了Iterator接口的对象。
 *          - public Iterator iterator(): 获取集合对应的迭代器，用来遍历集合中的元素的。
 *          - 集合对象每次调用 iterator()方法都得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前。
 *
 *      * Iterator 接口的常用方法如下：
 *          - public E next():返回迭代的下一个元素。
 *          - public boolean hasNext():如果仍有元素可以迭代，则返回 true。
 *          注意：在调用next()方法之前必须要调用hasNext()进行检测。若不调用且下一条记录无效，直接调用next()会抛出NoSuchElementException异常。
 *
 * 迭代器的执行原理：
 *      Iterator 迭代器对象在遍历集合时，内部采用指针的方式来跟踪集合中的元素。
 *      可以使用Iterator迭代器删除元素：使用remove()方法删除当前next指针指向的元素;
 *      注意：
 *          * Iterator可以删除集合的元素，但是不能修改元素，且遍历过程中调用的是迭代器对象的remove方法，不是集合对象的remove方法。
 *          * 如果还未调用next()或在上一次调用next()方法之后已经调用了remove()方法，再调用remove()都会报 IllegalStateException。
 *          * Collection已经有remove(xx)方法了，为什么Iterator迭代器还要提供删除方法呢？因为迭代器的remove()可以按指定的条件进行删除。
 *          * 在JDK8.0 时，Collection 接口有了 removeIf 方法，即可以根据条件删除。
 *
 * foreach循环：
 *      * foreach循环（也称增强 for 循环）是JDK5.0中定义的一个高级for循环，专门用来遍历数组和集合的。
 *      * foreach 循环的语法格式：
 *          for(元素的数据类型 局部变量 : Collection集合或数组){
 *              //操作局部变量的输出操作
 *          }
 *      * 对于集合的遍历，增强for循环的内部原理其实是个 Iterator 迭代器。
 *      * 它只能用于遍历Collection和数组中的元素，不能在遍历的过程中对集合元素进行增删改操作。
 *
 * @ClassName: C_IteratorTest.java
 * @Author: anpeng
 * @Date: 2023/12/20 11:37
 */
@SuppressWarnings("all")
public class C_IteratorTest {
    @Test
    public void testIteratorAndNext(){
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        Iterator iterator = coll.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        //System.out.println(iterator.next()); //报 NoSuchElementException 异常
    }

    @Test
    public void testHasNext() {
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        Iterator iterator = coll.iterator();//获取迭代器对象
        while(iterator.hasNext()) {//判断是否还有元素可迭代
            System.out.println(iterator.next());//取出下一个元素
        }
    }

    @Test
    public void testRemove(){
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        System.out.println(coll);
        Iterator iterator = coll.iterator();//获取迭代器对象
        while(iterator.hasNext()) {//判断是否还有元素可迭代
            Object obj = iterator.next();
            if(obj.equals("扫地僧")){
                iterator.remove();//remove删除next指针指向的元素
            }
            if(obj.equals("小李广")){
                obj = "anpeng"; //注意：迭代器只能对遍历的元素进行删除，不能对元素进行修改。
            }
        }
        System.out.println(coll);
    }

    @Test
    public void testRemoveIf(){
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll = " + coll);
        coll.removeIf(new Predicate() {
            @Override
            public boolean test(Object o) {
                String str = (String) o;
                return str.contains("地");
            }
        });
        System.out.println("删除包含\"地\"字的元素之后 coll = " + coll);
    }

    @Test
    public void testForeach(){
        int[] nums = new int[5];
        for(int num : nums){
            num = 3;//修改无效，foreach只能遍历集合或数组中的元素，不能进行增删改操作。
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);//全是0
        }
    }

}
