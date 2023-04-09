package day02_javaFoundation;

/**
 * @ClassName: I_Array.java
 * @Author: anpeng
 * @Date: 2023/4/8 20:55
 *
 * 数组(Array)：是多个相同数据类型的元素按一定顺序排序的集合，通过下标对数据元素进行操作。
 *
 *  特点：
 *      1、数组本身是引用类型，而数组元素可以是任何类型，包括基本数据类型和引用数据类型；
 *      2、创建数组对象会在内存中开辟一整块连续的空间。开辟的内存空间大小，取决于数组的长度和数组中元素的类型；
 *      3、可以自动给数组中的元素从0开始编号，方便操作这些元素.
 *      4、有序、可重复、可随机访问、默认初始化值为0(int)、null(引用)、0.0(double)、false(boolean)。
 *      5、数组变量存储在栈中，栈中存储的是数组地址，数组实体存储在堆内存中。
 *
 *  JVM内存：
 *      1、寄存器
 *      2、本地方法区
 *      3、方法区
 *          用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据
 *      4、栈内存
 *          指虚拟机栈，用于存储局部变量（定义在方法或局部代码块中的变量）。
 *          而且变量所属的作用域一旦结束，该变量就自动释放，局部变量没有默认初始化值。
 *      5、堆内存
 *          存储的是数组和对象（其实数组也是对象）凡是new建立的都在堆中
 *          特点：
 *              每一个实体都有首地址值。
 *              堆内存的每一个变量都有默认初始化值，根据类型的不同而不同。
 *              默认初始化值为0(int)、null(引用)、0.0(double)、false(boolean)、char ' '
 *              垃圾回收机制
 *
 *  分类：
 *      1、按元素分类：
 *          基本数据类型元素的数组：每个元素位置存储基本数据类型的值；
 *          引用数据类型元素的数组：每个元素位置存储对象的首地址；
 *      2、按维度分类：
 *          一维数组：存储一组数据
 *              声明:
 *                  推荐 --> 元素类型[] 数组名;
 *                  不推荐 --> 元素类型 数组名[];
 *              初始化：
 *                  静态(数组长度由给定的数据个数确定) --> 元素类型[] 数组名 = new 元素类型[]{元素1,元素2，...}
 *                                                     或  元素类型[] 数组名 = {元素1,元素2，...}
 *                  动态(首先确定数组长度，元素默认值) --> 元素类型[] 数组名 = new 元素类型[长度];
 *                                                     或  元素类型[] 数组名;数组名 = new 元素类型[长度];
 *
 *          多维数组：存储多组数据
 *              声明:
 *                  推荐 --> 元素类型[][] 数组名;
 *                  不推荐 --> 元素类型 数组名[][];
 *                            元素类型[] 数组名[];
 *              初始化：
 *                  静态(数组长度由给定的数据个数确定) --> 元素类型[][] 数组名 = new 元素类型[]{元素1,元素2，...}
 *                                                     或  元素类型[][] 数组名 = {元素1,元素2，...}
 *                  动态(首先确定数组长度，元素默认值) --> 元素类型[][] 数组名 = new 元素类型[长度][长度];
 *                                                     或(每行长度不一致)  数组名 = new 元素类型[总行数];
 *                                                                        数组名[行下标] = new 元素类型[该行的总列数]
 *
 *
 *
 *
 */
public class I_Array {
    public static void main(String[] args) {
        testStaticInitialization();
        testDynamicInitialization();
    }

    private static void testDynamicInitialization() {
        //声明数组
        int[] nums;
        //动态初始化数组
        nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        //使用数组
        for (int i = 0; i < nums.length; i++) {
            System.out.println("nums[i] = " + nums[i]);
        }
    }

    private static void testStaticInitialization() {
        //声明并静态初始化数数组
        int[] nums = {1, 3, 5, 7, 9};
        //使用数组-遍历
        for (int i = 0; i < nums.length; i++) {
            System.out.println("nums[i] = " + nums[i]);
        }
    }
}
