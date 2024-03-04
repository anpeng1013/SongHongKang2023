package day06_java_container;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection的子接口：List
 *      特点：
 *          * 鉴于Java中用数组存储数据的局限性，我们通常使用 java.util.List 替代数组。
 *          * List 集合类中元素有序、且可重复，集合中的每个元素都有其对应的顺序索引index。
 *          * JDK API 中 List 接口的实现类常用的有： ArrayList、LinkedList、Vector 和 Stack。
 *          * 在JavaSE中List名称的类型有两个，一个是java.util.List集合接口，一个是java.awt.List图形界面的组件，别导错包了。
 *      方法：
 *          增：
 *              - void add(int index, Object ele)：在index位置添加ele元素。没有指定index时，默认在尾部添加，下同。
 *              - boolean addAll(int index, Collection eles)：从index位置开始将eles中的所有元素添加进来，index后面的元素后移。
 *          删：
 *              - Object remove(int index)：删除指定 index 位置的元素，并返回此元素。
 *          改：
 *              - Object set(int index, Object ele)：将指定 index 位置的元素修改为ele。
 *          查：
 *              - Object get(int index)：获取指定 index 位置的元素。
 *              - List subList(int fromIndex, int toIndex)：返回从 fromIndex 到 toIndex 位置的子集合。
 *          索引：
 *              - int indexOf(Object obj)：返回 obj 在集合中首次出现的位置。
 *              - int lastIndexOf(Object obj)：返回 obj 在当前集合中最后一次出现的位置。
 *
 * List接口主要实现类：ArrayList：
 *      * ArrayList 是 List 接口的主要实现类。
 *      * 本质上，ArrayList 是对象引用的一个”变长”数组。
 *      * Arrays.asList(...)方法返回的 List 集合，既不是ArrayList实例，也不是Vector实例。返回值是一个固定长度的List集合
 *
 * List接口主要实现类：LinkedList
 *      * 对于频繁的插入或删除元素的操作，建议使用LinkedList类，效率较高。这是由底层采用链表（双向链表）结构存储数据决定的。
 *      * 特有方法：
 *          - void addFirst(Object obj)
 *          - void addLast(Object obj)
 *          - Object getFirst()
 *          - Object getLast()
 *          - Object removeFirst()
 *          - Object removeLast()
 *
 * List的主要实现类：Vector
 *      * Vector是一个古老的集合， JDK1.0就有了。大多数操作与ArrayList相同，区别之处在于Vector是线程安全的。
 *      * 在各种 List 中，最好把ArrayList作为默认选择。当插入、删除频繁时，使用LinkedList； Vector总是比ArrayList慢，所以尽量避免使用。
 *
 * ArrayList vs Vector（动态数组，底层物理结构都是数组）
 *      * ArrayList是新版的动态数组，线程不安全，但效率很高；Vector是旧版的动态数组，线程安全，但效率低。
 *      * 两者的扩容机制不同，ArrayList默认扩容为原来的 1.5 倍；Vector默认扩容增加为原来的 2 倍。
 *      * 数组的初始化容量，如果在构建ArrayList与Vector的集合对象时，没有显式指定初始化容量，那么Vector的内部数组的初始容量默认为10，而ArrayList
 *          在JDK6.0及之前的版本也是10，JDK8.0之后的版本ArrayList初始化为长度为0的空数组，之后在添加第一个元素时，再创建长度为10的数组。
 *
 * 链表 vs 动态数组
 *      * 动态数组底层的物理结构是数组，因此根据索引访问的效率非常高。但是非末尾位置的插入和删除效率不高，因为涉及到移动元素。
 *          另外添加操作时涉及到扩容问题，就会增加时空消耗。
 *      * 链表底层的物理结构是链表，因此根据索引访问的效率不高，即查找元素慢。但是插入和删除不需要移动元素，只需要修改前后元素的指向关系即可，
 *          所以插入、删除元素快。而且链表的添加不会涉及到扩容问题。
 *
 * @ClassName: D_ListTest.java
 * @Author: anpeng
 * @Date: 2023/12/20 17:11
 */
@SuppressWarnings("all")
public class D_ListTest {
    @Test
    public void testList() {
        // 创建 List 集合对象
        List<String> list = new ArrayList<String>();
        // 往尾部添加 指定元素
        list.add("图图");
        list.add("小美");
        list.add("不高兴");
        System.out.println(list);
        // add(int index,String s) 往指定index位置添加元素，index后面的元素后移。
        list.add(1,"没头脑");
        System.out.println(list);
        // String remove(int index) 删除指定位置元素 返回被删除元素，删除索引位置为 2 的元素
        System.out.println("删除索引位置为 2 的元素");
        System.out.println(list.remove(2));
        System.out.println(list);
        // String set(int index,String s)，在指定位置 进行 元素替代（改）
        list.set(0, "三毛");
        System.out.println(list);
        // String get(int index) 获取指定位置元素，跟 size() 方法一起用 来 遍历的
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        //还可以使用增强 for
        for (String string : list) {
            System.out.println(string);
        }
        System.out.println(list.contains("不高兴"));//使用父接口Collection中的contains方法。
    }

    @Test
    public void testLinkedList(){
        LinkedList<String> ll = new LinkedList<String>();
        ll.add("anpeng");
        ll.add("huli");
        ll.add("love");
        ll.addFirst("Tom");
        System.out.println(ll.get(0));
    }

}
