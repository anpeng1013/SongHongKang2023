package java_bean.day05;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @ClassName: StringAPITest.java
 * @Author: anpeng
 * @Date: 2023/11/13 21:36
 */
public class StringAPITest {
    @Test
    public void test01() {//常用方法
        //将用户输入的单词全部转为小写，如果用户没有输入单词，重新输入
        Scanner input = new Scanner(System.in);
        String word;
        while (true) {
            System.out.println("请输入单词：");
            word = input.nextLine();
            if (word.trim().length() != 0) {
                word = word.toLowerCase();
                break;
            }
        }
        System.out.println(word);
    }

    @Test
    @SuppressWarnings("all")
    public void test02() {//常用方法
        //随机生成验证码，验证码由0-9, A-Z, a-z 共62个字符组成
        char[] array = new char[26 * 2 + 10];
        for (int i = 0; i < 10; i++) {
            array[i] = (char) ('0' + i);
        }
        for (int i = 10, j = 0; i < 10 + 26; i++, j++) {
            array[i] = (char) ('A' + j);
        }
        for (int i = 10 + 26, j = 0; i < array.length; i++, j++) {
            array[i] = (char) ('a' + j);
        }
        String code = "";
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            code += array[rand.nextInt(array.length)];
        }
        System.out.println("验证码：" + code);
        //将用户输入的单词全部转为小写，如果用户没有输入单词，重新输入
        Scanner input = new Scanner(System.in);
        System.out.println("请输入验证码：");
        String inputCode = input.nextLine();
        if (!code.equalsIgnoreCase(inputCode)) {
            System.out.println("验证码输入不正确");
        }
    }

    @Test
    @SuppressWarnings("ALL")
    public void test03() {//查找
        String str = "尚硅谷是一家靠谱的培训机构，尚硅谷可以说是IT培训的小清华，JavaEE是尚硅谷的当家学科，尚硅谷的大数据培训是行业独角兽。" +
                "尚硅谷的前端和UI专业一样独领风骚。";
        System.out.println("是否包含清华： " + str.contains("清华"));
        System.out.println("培训出现的第一次下标： " + str.indexOf("培训"));
        System.out.println("培训出现的最后一次下标： " + str.lastIndexOf("培训"));
    }

    @Test
    public void test04() {//截取
        String str = "hello world java at gui gu";
        String sub1 = str.substring(6);
        String sub2 = str.substring(6, 11);
        System.out.println(sub1);
        System.out.println(sub2);

        String fileName = "快速学习 Java 的秘诀.dat";
        //截取文件名
        System.out.println("文件名： " + fileName.substring(0, fileName.lastIndexOf(".")));
        //截取后缀名
        System.out.println("后缀名： " + fileName.substring(fileName.lastIndexOf(".")));
    }

    @Test
    public void test05(){// 与字符/字符数组相关
        //将字符串中的字符按大小顺序排列
        String str = "anpeng";
        char[] array = str.toCharArray();
        Arrays.sort(array);
        str = new String(array);
        System.out.println(str);

        str = "huli";
        str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        System.out.println(str);

        char[] data = {'h','e','l','l','o','j','a','v','a'};
        String s1 = String.copyValueOf(data);
        String s2 = String.copyValueOf(data, 0, 5);
        int num = 123456;
        String s3 = String.valueOf(num);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    @Test
    public void test06(){//开头与结尾
        Scanner input = new Scanner(System.in);
        System.out.println("请输入名字：");
        String name = input.nextLine();
        System.out.println(name.startsWith("安"));

        System.out.println("请输入文件名：");
        String file = input.nextLine();
        if(file.endsWith(".java")){
            System.out.println("Java 源文件");
        }else if(file.endsWith(".class")){
            System.out.println("Java 字节码文件");
        }else{
            System.out.println("其他文件");
        }
    }

    @Test
    public void test07(){//替换
        String str1 = "hello244world.java;887";
        //把其中的非字母去掉
        str1 = str1.replaceAll("[^a-zA-Z]", "");
        System.out.println(str1);
        String str2 = "12hello34world5java7891mysql456";
        //把字符串中的数字替换成','，如果结果中开头和结尾有','的话去掉
        String string = str2.replaceAll("\\d+", ",").replaceAll("^,|,$", "");
        System.out.println(string);
    }


}
