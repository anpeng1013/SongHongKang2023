package day02_java_foundation;

/**
 * @ClassName: J_FunctionAndOverload.java
 * @Author: anpeng
 * @Date: 2023/4/9 15:37
 *
 * 定义函数格式：
 *     修饰符 返回值类型 函数名（参数类型 形式参数1，参数类型 形式参数2，..)
 *     {
 *         执行语句；
 *         return 返回值；//return 不仅可以返回值，还可以结束函数，return后面的语句不会执行。
 *     }
 *
 * 细节：
 *      1、参数和返回值：
 *         * 有参函数：给老师五块钱，让老师给学生做饭，做好后老师端一碗饭给学生。
 *         * 无参函数：不给老师钱，老师免费给学生做饭。
 *         * 无返回值：学生给（或不给）老师五块钱让老师做饭，并自己吃，不用给学生。void修饰，
 *                     此时函数中的return语句可以省略不写
 *      2、函数中只能调用函数，不能在函数中定义函数，主函数中也不能定义函数。
 *      3、函数的处理结果应该返回给调用者，由调用者进行处理。
 *      4、返回值类型和参数类型没有直接关系。
 *
 * 函数重载：
 *      在同一个类中允许一个以上的同名函数，只要它们的参数个数或者参数类型或参数顺序不同即可。
 *      1、同一个类中
 *      2、同名
 *      3、参数个数不同或参数类型、参数顺序不同
 *      4、函数重载和返回值类型无关,即，函数声明中只要函数名和参数列表相同，那么就是同一个函数，与返回值无关。
 *      5、java是强类型和严谨性语言，如果函数出现调用的不确定性，会编译失败。
 *
 * 可变参数：
 *      格式：
 *          方法名(参数类型...参数名)
 *      特点：
 *          1、可变形参的个数是0个，1个或多个。
 *          2、可变形参方法与同名方法之间构成重载。
 *          3、可变形参需要放在形参声明的最后。
 *          4、在一个方法的形参中，最多只能声明一个可变个数的形参。
 *
 * 参数传递机制：
 *      Java 里函数的参数传递方式只有一种：值传递。 即将实际参数值的副本（复制品）传入方法内，而参数本身不受影响。
 *          基本数据类型的形参：将实参基本数据类型变量的“数据值”传递给形参
 *          引用数据类型的形参：将实参引用数据类型变量的“地址值”传递给形参
 */
public class J_FunctionAndOverload {
    public static void main(String[] args) {
        //使用函数
        print99();

        //函数重载-- 同名，一个有参数，一个无参数。
        printCFB(7);
        printCFB();

        //可变形参
        accumulation(1, 9, 10, 23);
    }

    private static void accumulation(int... nums) {
        int sum = 0;// 局部变量没有默认初始化，所以必须进行手动初始化，否则编译报错。
        for (int num : nums) {
            sum += num;
        }
        System.out.println(sum);
    }

    private static void printCFB() {
        printCFB(9);//打印99乘法表
    }

    private static void printCFB(int num) {
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + j*i + "	");
            }
            System.out.println();
        }
    }

    /**
     * @Title: print99
     * @Description: 打印99乘法表
     * @Author: anpeng
     * @DateTime: 2023/4/9 15:47
     * @Param null
     * @Return void
     * @Throws null
     */
    private static void print99() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + j*i + "\t");
            }
            System.out.println();
        }
    }

}
