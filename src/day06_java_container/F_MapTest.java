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
 *      - HashMap和HashTable底层都是哈希表（也称散列表），其中维护了一个长度为2的n次方的Entry类型的数组table。数组的每一个索引位置被
 *          称为一个桶（bucket），添加的映射关系（key，value）最终都被封装到一个Map.Entry类型的对象中，放到某个table[index]桶中。
 *
 * Set 和 Map 的关系：（可见源码）
 *      - Set的内部其实就是一个Map。Set中的元素存储在Map的key中。
 *      - 即HashSet的内部实现是一个HashMap，TreeSet的内部实现是一个TreeMap，LinkedHashSet的内部实现是一个LinkedHashMap。
 *
 * Map中的key-value特点:
 *      - Map中的key不允许重复，即同一个key类，须重写hashCode和equals方法。
 *      - Map中的value允许重复。
 *      - key和value之间存在单向一对一关系，即通过指定的key总能找到唯一确定的value，不同key对应的value可以重复。value所在的类要重写equals()方法。
 *      - key和value构成一个Entry（条目，实体）。所有的Entry彼此之间是无序的、不可重复的。
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
 *      - HashMap是Map接口使用频率最高的实现类。但线程不安全的。允许添加 null 键和 null 值。
 *      - 存储数据采用的哈希表结构，底层使用一维数组+单向链表+红黑树进行key-value数据的存储。与HashSet一样，元素的存取顺序不能保证一致。
 *      - 两个key相等的标准：两个key的hashCode值相等，并且通过equals()方法返回true。即需要键所在类重写 hashCode()方法、equals()方法。
 *      - HashMap中数据的添加过程：
 *          -- JDK7过程分析：
 *              1、在底层创建了长度为 16 的Entry[] table的数组。
 *              2、将（key1，value1）添加到当前的HashMap的对象中。首先会调用key1所在类的hashCode()方法（已被重写，不再按地址哈希了），
 *                计算key1的哈希值1，此哈希值1再经过hash()得到哈希值2。此哈希值2再经过indexFor()得到在底层table数组中的索引位置i：
 *                  -- 如果数组索引为 i 上的数据为空，则(key1,value1)直接添加当前索引为i的位置上。
 *                  -- 如果数组索引为 i 上的位置上有键值对（key2,value2），则需要进一步判断：（判断key1和key2的哈希值2是否相同）
 *                      * 如果两者的哈希值2不同（indexFor冲突），则拉链法解决，（key1, value1)直接添加当前i位置的链表上。
 *                      * 如果两者的哈希值2相同，则需要继续调用key1.equals(key2)方法，进行判断。
 *                          -- equals返回false（哈希冲突，不同数据，哈希值相同），则（key1，value1）添加当前i位置的链表上。
 *                          -- equals返回true（两个key值完全相同，修改数据），默认情况下，value1会覆盖value2。
 *          -- JDK8过程分析：（判断过程与JDK7大致相同，下面是不同之处）
 *              1、JDK8使用HashMap()的构造器创建对象时，并没有在底层初始化长度为 16 的table数组。
 *              2、jdk8中添加的key,value封装到了HashMap.Node类的对象中。而非jdk7中的HashMap.Entry。
 *              3、jdk8 中新增的元素所在的索引位置如果有其他元素。在经过一系列判断后，如果能添加，则是旧的元素指向新的元素。
 *                  而非jdk7中的新的元素指向旧的元素。 “七上八下”
 *              4、jdk7底层的数据结构是：数组+单向链表。 而jdk8底层的数据结构是：数组+单向链表/红黑树。
 *              5、红黑树出现的时机：当某个索引位置 i 上的链表的长度达到 8，且数组的长度超过 64 时，此索引位置上的元素要从单向链表改为红黑树。
 *                  如果索引 i 位置是红黑树的结构，当不断删除元素的情况下，当前索引 i 位置上的元素的个数低于 6 时，要从红黑树改为单向链表。
 *
 *      - HashMap相关的面试题
 *          1、说说你理解的哈希算法。
 *              答：hash算法是一种可以从任何数据中提取出其“指纹”的数据摘要算法，它将任意大小的数据映射到一个固定大小的序列上，这个序列
 *              被称为hash code、数据摘要或者指纹。 比较出名的hash算法有MD5、SHA。hash是具有唯一性且不可逆的，唯一性是指相同的“对象”产生
 *              的hash code永远是一样的，不同的“对象”产生的hash code可能相同（哈希冲突）。
 *          2、Entry中的has属性为什么不直接使用key的hashCode()返回值呢？
 *              答：不管是JDK7还是JDK8中，都不是直接用key的hashCode值直接与table.length-1计算求下标的，而是先对key的hashCode值
 *              进行了一个运算，JDK7和DK8关于hash()的实现代码不一样，但是不管怎么样都是为了提高hash code值与(table.length-1)的按位与的结果，
 *              尽量的均匀分布。虽然算法不同，但是思路都是将hashCode值的高位二进制与低位二进制值进行了异或，让高位二进制参与到index的计算中。
 *              因为HashMap的table数组一般不会特别大，至少在不断扩容之前，那么table.length-1 的大部分高位都是0，直接用hashCode和table.length-1
 *              进行&运算的话，就会导致总是只有最低的几位是有效的，那么就算你的hashCode()实现的再好也难以避免发生碰撞，这时让高位参与进来的意义就
 *              体现出来了。它对hashcode的低位添加了随机性并且混合了高位的部分特征，显著减少了碰撞冲突的发生。
 *          3、HashMap是如何决定某个key-value存在哪个桶（table[index]）的呢？
 *              答：因为hash值是一个整数，而数组的长度也是一个整数，有两种思路：
 *              ① hash % table.length 会得到一个[0, table.length-1]范围的值，正好是下标范围，但%（取余）运算的效率没有位运算符&（按位与）高。
 *              ② hash & (table.length-1)，任何数 & (table.length-1)的结果一定也在[0, table.length-1]的范围内。
 *          4、为什么要保持table数组一直是2的n次方呢？
 *              答：因为，如果数组的长度为2的n次方，那么table.length-1的二进制就是一个高位全是0，低位全是1的数字，这样才能保证
 *              每一个下标位置都有机会被用到。
 *          5、如何解决[index]冲突问题？
 *              答：JDK8之前使用：数组 + 链表的结构。（链表中key的hashCode相同 或 hash相同 或 (hashCode & hash)相同）
 *              JDK之后使用：数组 + 链表/红黑树的结构。（链表/红黑树中key的hashCode相同 或 hash相同 或 (hashCode & hash)相同）
 *          6、为什么JDK8会出现链表和红黑树共存呢？
 *              答：因为当冲突比较严重时，table[index]下面的链表就会很长，那么导致查找效率大大降低，而如果此时选用二叉树可以大大提高查询效率。
 *              但是二叉树的结构过于复杂，占用内存也较多，如果节点个数比较少的时候，选择链表反而简单。所以会出现红黑树和链表共存。
 *          7、加载因子（表示Hsah表中元素的填满的程度）的大小有什么关系？（一般为0.75）
 *              答：如果加载因子太大，threshold就会很大，那么如果冲突比较严重的话，就会导致table[index]下面的节点太多，影响效率。
 *              如果加载因子太小，threshold就会很小，那么数组扩容的频率就会提高，数组的使用率也会降低，会造成空间的浪费。
 *              其中，threshold = capacity * 加载因子，判断是否扩容的临界值（门限）。capacity为数组长度。
 *          8、什么时候树化？什么时候反树化？
 *              答：树化阈值：8，反树化阈值：6，最小树化容量：64。
 *              ① 当table[index]下的链表的节点个数达到8，并且table.length>=64时，如果新的Entry对象还添加到该table[index]下的链表中，
 *                  那么就会将table[index]下的链表进行树化（转化成红黑树）。
 *              ② 当某个table[index]下的红黑树节点个数小于6时：
 *                  - 当继续删除table[index]下的树结点，最后这个根结点的左右结点有null，或根结点的左结点的左结点为null，会反树化。
 *                  - 当重新添加新的映射关系到map中，导致map重新扩容，这个时候如果table[index]下面还是小于等于 6 的个数，那么会反树化。
 *          9、key-value中key是否可以修改？
 *              答：key-value存储到HashMap中会存储key的hash值，这样就不用在每次查找时重新计算每一个Entry或Node（TreeNode）的hash 值了，
 *              因此如果已经put到Map中的key-value，再修改key的属性，而这个属性又参与hashcode值的计算，那么会导致匹配不上。故，不能修改。
 *              这个规则也同样适用于 LinkedHashMap、 HashSet、 LinkedHashSet、 Hashtable等所有散列存储结构的集合。
 *          10、JDK7中HashMap的循环链表是怎么回事？如何解决？
 *              答：HashMap的循环链表只会发生在JDK7版本中，主要原因是：头插法+链表+多线程并发+扩容（发生时机可看B站IT老哥的视频）。
 *              避免HashMap发生循环链表的常用解决方案：
 *                  * 多线程环境下，使用线程安全的 ConcurrentHashMap 替代 HashMap，推荐！
 *                  * 多线程环境下，使用 synchronized 或 Lock 加锁，但会影响性能，不推荐。
 *                  * 多线程环境下，使用线程安全的 Hashtable 替代，性能低，不推荐。
 *              在 JDK8版本 中， HashMap 改用尾插法，解决了链表死循环的问题。
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
