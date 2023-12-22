package day06_java_container;

import java_bean.day06.User;
import org.junit.Test;

import java.util.*;

/**
 * Map接口概述：
 *      - Map与Collection并列存在。用于保存具有映射关系的数据，即用于保存键值对（key-value）。
 *          -- Collection 集合称为单列集合，元素是孤立存在的（理解为单身）。
 *          -- Map 集合称为双列集合，元素是成对存在的(理解为夫妻)。
 *      - Map中的key和value都可以是任何引用类型的数据。但常用String类作为Map的key(键)。
 *      - Map接口的常用实现类：HashMap、LinkedHashMap、TreeMap、HashTable和Properties。其中，HashMap是Map接口使用频率最高的实现类。
 *
 * Map中的key-value特点:
 *      - Map中的key用Set来存放，不允许重复，即同一个key对象所对应的类，须重写hashCode和equals方法。
 *      - Map中的value用Collection来存放，允许重复。
 *      - key和value之间存在单向一对一关系，即通过指定的key总能找到唯一确定的value，不同key对应的value可以重复。value所在的类要重写equals()方法。
 *      - key和value构成一个entry（条目，实体）。所有的entry彼此之间是无序的、不可重复的。
 *
 * Map接口的常用方法：
 *      添加、修改操作：
 *          - Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中。
 *          - void putAll(Map m):将m中的所有key-value对存放到当前map中。
 *      删除操作：
 *          - Object remove(Object key)：移除指定key的key-value对，并返回value。
 *          - void clear()：清空当前 map 中的所有数据。
 *      查询操作：
 *          - Object get(Object key)：获取指定key对应的 value。
 *          - boolean containsKey(Object key)：是否包含指定的 key。
 *          - boolean containsValue(Object value)：是否包含指定的 value。
 *          - int size()：返回map中 key-value 对的个数。
 *          - boolean isEmpty()：判断当前 map 是否为空。
 *          - boolean equals(Object obj)：判断当前map和参数对象obj是否相等。
 *      元视图操作：
 *          - Set keySet()：返回所有 key 构成的 Set 集合。
 *          - Collection values()：返回所有 value 构成的 Collection 集合。
 *          - Set entrySet()：返回所有 key-value对 构成的 Set 集合。
 *
 * Map的主要实现类：HashMap
 *      - HashMap 是 Map 接口使用频率最高的实现类。
 *      - HashMap 是线程不安全的。允许添加 null 键和 null 值。
 *      - 存储数据采用的哈希表结构，底层使用一维数组+单向链表+红黑树进行key-value数据的存储。与HashSet一样，元素的存取顺序不能保证一致。
 *      - HashMap 判断两个key相等的标准是：两个 key 的 hashCode 值相等，通过equals()方法返回 true。
 *      - HashMap 判断两个 value 相等的标准是：两个 value 通过 equals() 方法返回 true。
 *      - 通过哈希表结构可以保证键的唯一、不重复，需要键所在类重写 hashCode()方法、equals()方法。
 *
 * Map的实现类：LinkedHashMap
 *      - LinkedHashMap 是 HashMap 的子类。
 *      - 存储数据采用的哈希表结构+链表结构，在HashMap存储结构的基础上，使用双向链表来记录添加元素的先后顺序，保证遍历元素时，与添加的顺序一致。
 *      - 通过哈希表结构可以保证键的唯一、不重复，需要键所在类重写 hashCode()方法、equals()方法。
 *
 * Map的实现类：TreeMap
 *      - TreeMap存储key-value对时，需要对key-value进行排序。TreeMap可以保证所有的key-value对处于有序状态。
 *      - TreeSet 底层使用红黑树结构存储数据。（不必重写hashCode和equals方法）
 *      - TreeMap 的 Key 的排序：
 *          -- 自然排序：TreeMap的所有的Key必须实现Comparable接口，而且所有的Key应该是同一个类的对象，否则将会抛出ClasssCastException。
 *          -- 定制排序：创建TreeMap时，构造器传入一个Comparator对象，负责对TreeMap中的所有key进行排序。此时不需要Map的Key实现Comparable接口。
 *      - TreeMap判断两个key相等的标准：两个key通过compareTo()方法或者compare()方法返回 0。
 *
 * Map的实现类：Hashtable
 *      - Hashtable是Map接口的古老实现类，JDK1.0 就提供了。不同于HashMap，Hashtable是线程安全的。
 *      - Hashtable实现原理和HashMap相同，功能相同。底层都使用哈希表结构（数组+单向链表），查询速度快。
 *      - 与HashMap一样，Hashtable也不能保证其中Key-Value对的顺序。
 *      - Hashtable判断两个key相等、两个value相等的标准，与HashMap一致。
 *      - 与HashMap不同，Hashtable不允许使用null作为key或value。
 *      面试题：Hashtable 和 HashMap 的区别。
 *          -- HashMap：底层是一个哈希表（jdk7:数组+链表;jdk8:数组+链表+红黑树），是一个线程不安全的集合，但执行效率高。
 *             Hashtable：底层也是一个哈希表（数组+链表），是一个线程安全的集合，但执行效率低。
 *          -- HashMap：可以存储null的键和null的值。
 *             Hashtable：不可以存储null的键和null的值。
 *          -- Hashtable和Vector集合一样，在jdk1.2版本后被更先进的集合(HashMap,ArrayList)取代了。所以HashMap是Map的主要实现类，
 *             Hashtable是Map的古老实现类。
 *          -- Hashtable的子类Properties（配置文件）依然活跃在历史舞台上，Properties集合是一个和IO流相结合的集合。
 *
 * Map的实现类：
 *      - Properties类是 Hashtable 的子类，该对象用于处理属性文件。
 *      - 由于属性文件里的key、value都是字符串类型，所以 Properties 中要求key和value都是字符串类型。
 *      - 存取数据时，建议使用setProperty(String key,String value)方法和getProperty(String key)方法。
 *
 * @ClassName: F_MapTest.java
 * @Author: anpeng
 * @Date: 2023/12/21 17:40
 */
@SuppressWarnings("all")
public class F_MapTest {
    @Test
    public void testHashMap(){
        HashMap map = new HashMap();
        //添加键值对到Map中
        map.put("黄晓明", "杨颖");
        map.put("李晨", "李小璐");
        map.put("李晨", "范冰冰");//会覆盖上一条entry，相当于修改。
        map.put("邓超", "孙俪");
        System.out.println(map);
        //删除指定的 key-value
        System.out.println(map.remove("黄晓明"));
        System.out.println(map);
        //查询指定 key 对应的 value
        System.out.println(map.get("邓超"));
        System.out.println(map.get("黄晓明"));

        System.out.println("所有的key:");
        Set keySet = map.keySet();
        for (Object key : keySet) {
            System.out.println(key);
        }

        System.out.println("所有的value：");
        Collection values = map.values();
        for (Object value : values) {
            System.out.println(value);
        }

        System.out.println("所有的映射关系：");
        Set entrySet = map.entrySet();
        for (Object mapping : entrySet){
            //System.out.println(mapping);
            Map.Entry entry = (Map.Entry) mapping;
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
    }

    @Test
    public void testLinkedHashMap(){
        LinkedHashMap map = new LinkedHashMap();
        map.put("anpeng", 38000.0);
        map.put("huli", 12000.0);
        //key相同时，新的value会覆盖原来的value，因为String重写了hashCode和equals方法
        map.put("huli", 11000.0);
        map.put("xiechao", 12000.0);
        //LinkedHashMap也支持key和value为null值。
        String name = null;
        Double salary = null;
        map.put(name, salary);

        Set entrySet = map.entrySet();
        for (Object obj : entrySet){ //遍历顺序与添加顺序一致。
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry);
        }
    }

    @Test
    public void testTreeMap(){
        //自然排序：--User类先按年龄排序，再按姓名排序
        TreeMap map = new TreeMap();
        map.put(new User("anpeng", 27), 38000);
        map.put(new User("huli",24), 11000);
        map.put(new User("xiechao", 26), 12000);
        System.out.println("map = " + map);

        //定制排序： --按照user的姓名排序
        map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return u1.getName().compareTo(u2.getName());//String默认实现Comparable接口，即按自然排序
                }
                throw new RuntimeException("输入类型不匹配");
            }
        });
        map.put(new User("anpeng", 27), 38000);
        map.put(new User("huli",24), 11000);
        map.put(new User("xiechao", 26), 12000);
        System.out.println("map = " + map);
    }

    @Test
    public void testHashTable(){
        Hashtable hashTable = new Hashtable();
        hashTable.put("anpeng", 27);
        hashTable.put("huli", 24);
        hashTable.put("xiechao", 26);
        String name = null;
        Integer age = null;
        //hashTable.put(name, age);//NullPointerException
        System.out.println("hashTable = " + hashTable);//添加顺序和遍历顺序不能保证一致。
    }

    @Test
    public void testProperties(){
        Properties properties = System.getProperties();
        String fileEncoding = properties.getProperty("file.encoding");//当前源文件字符编码
        System.out.println("fileEncoding = " + fileEncoding);
    }

}
