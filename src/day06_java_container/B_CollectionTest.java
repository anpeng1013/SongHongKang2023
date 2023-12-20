package day06_java_container;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Collection接口及其方法
 *      * JDK 不提供此接口的任何直接实现，而是提供更具体的子接口（如：Set 和 List）去实现。
 *      * Collection接口是List和Set接口的父接口，该接口里定义的方法既可用于操作Set集合，也可用于操作List集合。方法如下：
 *          - 添加：
 *              -- add(E obj)：添加元素到当前集合中
 *              -- addAll(Collection other)：添加other集合中的所有元素到当前集合中，即this = this U other
 *
 *          - 判断：
 *              -- int size()：获取当前集合中实际存储的元素个数
 *              -- boolean isEmpty()：判断当前集合是否为空集合
 *              -- boolean contains(Object obj)：判断集合中是否包含一个与obj对象equals返回true的元素
 *              -- boolean containsAll(Collection coll)：判断coll集合是否为当前集合的子集
 *              -- boolean equals(Object obj)：判断当前集合与obj是否相等。
 *
 *          - 删除：
 *              -- void clear()：清空集合元素
 *              -- boolean remove(Object obj)：从当前集合中删除第一个与obj对象equals返回true的元素
 *              -- boolean removeAll(Collection coll)：从当前集合中删除所有与coll集合中相同的元素，即this = this - this ∩ coll
 *              -- boolean retainAll(Collection coll)：使当前集合仅保留与coll集合相同的元素，即this = this ∩ coll
 *
 *
 *
 * @ClassName: B_CollectionTest.java
 * @Author: anpeng
 * @Date: 2023/12/20 11:17
 */
@SuppressWarnings("all")
public class B_CollectionTest {
    @Test
    public void testAdd(){
        //ArrayList 是 Collection 的子接口 List 的实现类之一。
        Collection coll = new ArrayList(); // 多态引用：接口引用指向实现类对象。
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        System.out.println(coll);
    }

    @Test
    public void testAddAll(){
        Collection c1 = new ArrayList();
        c1.add(1);
        c1.add(2);
        System.out.println("c1 = " + c1);

        Collection c2 = new ArrayList();
        c2.add(1);
        c2.add(2);
        System.out.println("c2 = " + c2);

        Collection other = new ArrayList();
        other.add(1);
        other.add(2);
        other.add(3);
        System.out.println("other = " + other);

        //注意： coll.addAll(other);与 coll.add(other);
        c1.addAll(other);
        System.out.println("c1.addAll(other) = " + c1);
        c2.add(other);
        System.out.println("c2.add(other) = " + c2);
    }

    @Test
    public void testSizeAndEmpty(){
        Collection coll = new ArrayList();
        System.out.println("coll 在添加元素之前， isEmpty = " + coll.isEmpty());
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll 的元素个数" + coll.size());
        System.out.println("coll 在添加元素之后， isEmpty = " + coll.isEmpty());
    }

    @Test
    public void testContainsAndContainsAll() {
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll = " + coll);
        System.out.println("coll 是否包含“小李广” = " + coll.contains("小李广"));
        System.out.println("coll 是否包含“宋红康” = " + coll.contains("宋红康"));
        Collection other = new ArrayList();
        other.add("小李广");
        other.add("扫地僧");
        other.add("尚硅谷");
        System.out.println("other = " + other);
        System.out.println("coll.containsAll(other) = " + coll.containsAll(other));
    }

    @Test
    public void testClearAndRemove(){
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll = " + coll);
        coll.remove("小李广");
        System.out.println("删除元素\"小李广\"之后 coll = " + coll);
        coll.clear();
        System.out.println("coll 清空之后， coll = " + coll);
    }

    @Test
    public void testRemoveAll(){
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll = " + coll);
        Collection other = new ArrayList();
        other.add("小李广");
        other.add("扫地僧");
        other.add("尚硅谷");
        System.out.println("other = " + other);
        coll.removeAll(other);
        System.out.println("coll.removeAll(other)之后， coll = " + coll);
        System.out.println("coll.removeAll(other)之后， other = " + other);
    }

    @Test
    public void testRetainAll(){
        Collection coll = new ArrayList();
        coll.add("小李广");
        coll.add("扫地僧");
        coll.add("石破天");
        coll.add("佛地魔");
        System.out.println("coll = " + coll);
        Collection other = new ArrayList();
        other.add("小李广");
        other.add("扫地僧");
        other.add("尚硅谷");
        System.out.println("other = " + other);
        coll.retainAll(other);
        System.out.println("coll.retainAll(other)之后， coll = " + coll);
        System.out.println("coll.retainAll(other)之后， other = " + other);
    }
}
