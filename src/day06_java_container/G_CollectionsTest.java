package day06_java_container;

import java_bean.day06.User;
import org.junit.Test;

import java.util.*;

/**
 * Collections工具类：
 *      参考操作数组的工具类：Arrays。Collections是一个操作Set、List 和 Map等集合的工具类。
 *
 * 常用方法：
 *      Collections中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现
 *      同步控制等方法（均为static 方法）
 *
 *      - 排序操作：
 *          -- reverse(List)：反转List中元素的顺序。
 *          -- shuffle(List)：对List集合元素进行随机排序。
 *          -- sort(List)：根据元素的自然顺序对指定List集合元素按升序排序。
 *          -- sort(List, Comparator)：根据指定的Comparator产生的顺序对List集合元素进行排序。
 *          -- swap(List, int i, int j)：将指定 list 集合中的 i 处元素和 j 处元素进行交换。
 *
 *      - 查找操作：
 *          -- Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素。
 *          -- Object max(Collection， Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最大元素。
 *          -- Object min(Collection)：根据元素的自然顺序，返回给定集合中的最小元素。
 *          -- Object min(Collection，Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最小元素。
 *          -- int binarySearch(List list, T key)：在List集合中查找某个元素的下标，但是List的元素必须是T或T的子类对象，而且必须是
 *                                                  可比较大小的，即支持自然排序的。而且集合也必须是有序的，否则结果不确定。
 *          -- int binarySearch(List list, T key, Comparator c)：在List集合中查找某个元素的下标，但是List的元素必须是T或T的子类对象，
 *                                                  而且集合也必须是按照c比较器规则进行排序过的，否则结果不确定。
 *          -- int frequency(Collection c， Object o)：返回指定集合中指定元素的出现次数。
 *
 *      - 复制、替换：
 *          -- void copy(List dest, List src)：将src中的内容复制到dest中。
 *          -- boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替换 List 对象的所有旧值。
 *          -- 提供了多个unmodifiableXxx()方法，该方法返回指定 Xxx 的不可修改的视图。
 *
 *      - 添加操作：
 *          -- boolean addAll(Collection c, T... elements)将所有指定元素添加到指定collection中。
 *
 *      - 同步操作：
 *          -- Collections类中提供了多个synchronizedXxx()方法，该方法可使将指定集合包装成线程同步的集合，从而可以解决多线程
 *              并发访问集合时的线程安全问题。
 *
 * @ClassName: G_CollectionsTest.java
 * @Author: anpeng
 * @Date: 2023/12/22 16:32
 */
@SuppressWarnings("all")
public class G_CollectionsTest {
    @Test
    public void testAddAll() {
        /*
        public static <T> boolean addAll(Collection<? super T> c, T... elements)
        将所有指定元素添加到指定的collection中。Collection集合中的元素类型必须 >= T类型。
         */
        Collection<Object> coll = new ArrayList<>();
        Collections.addAll(coll, "java", "python", "C++");
        Collections.addAll(coll, 1, 2, 3, 4);
        System.out.println("coll = " + coll);

        Collection<String> collection = new ArrayList<>();
        Collections.addAll(collection, "anpeng", "love", "huli");
        //Collections.addAll(collection, 1124);//报错，int不是String的父类。
        System.out.println("collection = " + collection);
    }

    @Test
    public void testMax(){
        //返回集合元素的最大值--自然排序：
        List<User> users = new ArrayList<>();
        users.add(new User("anpeng", 27));
        users.add(new User("huli", 24));
        users.add(new User("xiechao", 26));
        User max = Collections.max(users);
        System.out.println("max = " + max);

        //返回集合元素的最大值--定制排序：
        max = Collections.max(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());//String类默认Comparable的compareTo方法。
            }
        });
        System.out.println("max = " + max);
    }

    @Test
    public void testReverse(){
        //反转指定列表中的元素顺序
        List list = new ArrayList();
        Collections.addAll(list, "anpeng", "love", "huli");
        System.out.println("list = " + list);
        Collections.reverse(list);
        System.out.println("list = " + list);
    }

    @Test
    public void testShuffle(){
        //对List集合元素进行随机排序，类似洗牌，打乱顺序。
        List list = new ArrayList();
        Collections.addAll(list, "anpeng", "love", "huli", 1124);
        System.out.println("list = " + list);
        Collections.shuffle(list);
        System.out.println("list = " + list);
    }

    @Test
    public void testSort(){
        //自然排序：--按user的年龄进行排序
        List<User> users = new ArrayList<>();
        users.add(new User("anpeng", 27));
        users.add(new User("xiechao", 26));
        users.add(new User("huli", 24));
        Collections.sort(users);
        System.out.println("users = " + users);

        //定制排序：-- 按user的姓名进行排序
        Collections.shuffle(users);//重新打乱顺序。
        System.out.println("users = " + users);
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("users = " + users);


    }

    @Test
    public void testSwap(){
        //将指定List集合中i出元素和j处元素进行交换。
        List list = new ArrayList();
        Collections.addAll(list, "anpeng", "love", "huli");
        Collections.swap(list, 0, 2);
        System.out.println("list = " + list);
    }

    @Test
    public void testFrequency(){
        //返回指定集合中某个元素的出现次数。
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "anpeng", "love", "love", "love", "huli");
        int count = Collections.frequency(list, "love");
        System.out.println("count = " + count);
    }

    @Test
    public void testCopy(){
        //将src的内容复制到dest中, src.size()必须小于等于dest.size()，否则会抛出Source does not fit in dest异常
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        List<Integer> list1 = new ArrayList<>();
        for (int i = 10; i <=14 ; i++) {
            list1.add(i);
        }
        Collections.copy(list, list1);
        System.out.println("list = " + list);
    }

    @Test
    public void testReplaceAll(){
        //使用新值替换list对象中的所有旧值。
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"hello","java","world","hello");
        Collections.replaceAll(list,"hello","anpeng");
        System.out.println("list = " + list);
    }

}
