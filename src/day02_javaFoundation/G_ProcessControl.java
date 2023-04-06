package day02_javaFoundation;

import java.util.Scanner;
/**
 * @ClassName: ProcessControl.java
 * @Author: anpeng
 * @Date: 2023/4/5 20:00
 *
 * 三大流程控制：
 *      1、顺序结构：
 *          程序从上到下逐行地执行，并且上一行对某个变量的修改对下一行会产生影响。
 *
 *      2、分支结构：
 *          单分支：
 *              if(条件表达式){
 *                  语句块1;
 *              }
 *              注：条件表达式成立，执行语句块1；否则跳过该分支，接着执行该分支下面的代码。
 *
 *          双分支：
 *              if-else分支：
 *                  格式：if(条件表达式){
 *                      语句块1;
 *                  }else{
 *                      语句块2;
 *                  }
 *              注：如果条件表达式成立，执行语句块1；不成立时，则执行语句块2。
 *
 *           多分支：
 *              if(条件表达式1){
 *                  语句块1;
 *              }else if(条件表达式2){
 *                  语句块2;
 *              }else if(条件表达式n){
 *                  语句块n;
 *              }else{
 *                  语句块n+1;
 *              }
 *              注：一旦某个条件表达式成立，则进入执行相应的语句块，执行完对应语句之后，跳出当前分支结构。否则执行语句块n+1。
 *
 *              switch(表达式){
 *                  case 常量1：
 *                      语句块1;
 *                      [break;]
 *                  case 常量2：
 *                      语句块2;
 *                      [break;]
 *                  case 常量n:
 *                      语句块n;
 *                      [break;]
 *                  [default:
 *                      语句块n+1;
 *                      break;
 *                  ]
 *              }
 *              注：根据switch中表达式的值，依次匹配各个case。如果表达式的值等于某个case中的常量值，则执行对应case中的执行语句。
 *                  执行完此case的执行语句以后，情况1：如果遇到break,则执行break并跳出当前的switch-case结构; 情况2：如果
 *                  没有遇到break，则会继续执行当前case之后的其它case中的执行语句。 --->case穿透...直到遇到break关键字或
 *                  执行完所有的case及default的执行语句，跳出当前的switch-case结构。
 *
 *      3、循环结构：
 *          for循环：
 *              for(①初始化;②循环条件;④迭代){
 *                  ③循环体;
 *              }
 *              执行过程： ① -> ② -> ③ -> ④ -> ② -> ③ -> ④ -> ... -> ②
 *
 *          foreach循环：
 *              for(容器元素类型 临时变量：容器变量) {
 *                  循环体;
 *              }
 *
 *          while循环：
 *              ①初始化
 *              while(②循环条件)｛
 *                  ③循环体;
 *                  ④迭代;
 *              }
 *              执行
 *              ④ -> ... -> ②
 *
 *          do-while循环：
 *              ①初始化;
 *              do{
 *                  ③循环体;
 *                  ④迭代;
 *              }while(②循环条件);
 *              执行过程： ① -> ③ -> ④ -> ② -> ③ -> ④ -> ② -> ... -> ②
 *
 *
 *
 */
public class G_ProcessControl {
    public static void main(String[] args) {
        testSequenceStructure();
        testBranchStructure();
        testLoopStructure();
    }

    private static void testLoopStructure() {
        //for 循环
        for (int i = 0; i < 3; i++) {
            System.out.println("anpeng love huli");
        }

        //增强for 循环
        int[] nums = {1, 3, 5, 7, 9};
        for(int num : nums) System.out.println(num);

        //while 循环
        int i = 0;
        while (i < 2){
            System.out.println("huli is a beautiful girl!");
            ++i;
        }

        //do-while 循环
        i = 0;
        do{
            System.out.println("I love you");
            i++;
        }while (i < 2);
    }

    private static void testBranchStructure() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个0到5的整数：");
        int num = scanner.nextInt();

        //单分支
        if (num > 2){
            System.out.println("more two!");
        }

        //双分支
        if(num <= 2 ){
            System.out.println("less two!");
        }else{
            System.out.println("more two!");
        }

        //多分支
        switch (num) {
            case 0 -> System.out.println("zero");
            case 1 -> System.out.println("one");
            case 2 -> System.out.println("two");
            case 3 -> System.out.println("three");
            case 4 -> System.out.println("four");
            case 5 -> System.out.println("five");
            default -> System.out.println("too big!");
        }

        if(num == 10){
            System.out.println("ten");
        }else if(num == 9){
            System.out.println("nine");
        }else if(num == 8){
            System.out.println("eight");
        }else {
            System.out.println(num);
        }

        //关闭资源
        scanner.close();
    }

    private static void testSequenceStructure() {
        String name = "anpeng";
        short age = 26;
        System.out.println(name + " " + age);
    }
}
