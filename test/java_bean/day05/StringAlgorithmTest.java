package java_bean.day05;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName: StringAlgorithmTest.java
 * @Author: anpeng
 * @Date: 2023/11/14 11:50
 */
@SuppressWarnings("all")
public class StringAlgorithmTest {
    //题目1、模拟一个 trim 方法，去除字符串两端的空格。
    public String myTrim(String str) {
        if (str != null) {
            int start = 0;//用于记录从前往后首次索引位置不是空格的位置索引
            int end = str.length() - 1;//用于记录从后往前首次索引位置不是空格的位置索引
            while (start < end && str.charAt(start) == ' ') {
                start++;
            }
            while (start < end && str.charAt(end) == ' ') {
                end--;
            }
            if (str.charAt(start) == ' ') {
                return "";
            }
            return str.substring(start, end + 1);
        }
        return null;
    }

    @Test
    public void test01() {//模拟trim方法
        String string = "  huli   ";
        String newStr = myTrim(string);
        System.out.println(newStr);
    }

    //题目2、将一个字符串进行反转。将字符串中指定部分进行反转。比如："abcdefg"反转为"abfedcg"--> 其中cdef反转为fedc
    //方式1
    public String reverse1(String str, int start, int end) {//start: 2, end: 5
        if (str != null) {
            char[] charArray = str.toCharArray();
            for (int i = start, j = end; i < j; i++, j--) {
                char temp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = temp;
            }
            return new String(charArray);
        }
        return null;
    }

    //方式2
    public String reverse2(String str, int start, int end) {
        String newStr = str.substring(0, start);//ab
        for (int i = end; i >= start; i--) {
            newStr += str.charAt(i);
        }//abfedc
        newStr += str.substring(end + 1);
        return newStr;
    }

    //方式3: 推荐 （相较于方式二做的改进）
    public String reverse3(String str, int start, int end) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        stringBuffer.append(str.substring(0, start));//ab
        for (int i = end; i >= start; i--) {
            stringBuffer.append(str.charAt(i));
        }
        stringBuffer.append(str.substring(end + 1));
        return stringBuffer.toString();
    }

    @Test
    public void test02() {//字符串的反转
        String str = "abcdefg";
        System.out.println(reverse1(str, 2, 5));//abfedcg
        System.out.println(reverse2(str, 2, 5));//abfedcg
        System.out.println(reverse3(str, 2, 5));//abfedcg
    }

    //题目3、获取一个字符串在另一个字符串中出现的次数。 比如：获取"ab"在"abkkcadkabkebfkabkskab"中出现的次数
    public int getCount(String mainStr, String subStr) {
        if (mainStr.length() >= subStr.length()){
            int count = 0;
            int index = 0;
            while((index = mainStr.indexOf(subStr, index)) != -1){
                index += subStr.length();
                count++;
            }
            return count;
        }
        return 0;
    }

    @Test
    public void test03(){//获取子串出现的次数
        String str1 = "cdabkkcadkabkebfkabkskab";
        String str2 = "ab";
        int count = getCount(str1, str2);
        System.out.println(count);
    }

    // 题目4、获取两个字符串中最大相同子串。（有一个或多个最大相同子串）：短串长度逐次减一，并采用滑动窗口思想去判断。
    //情况1：只存在一个最大相同子串。
    public String getMaxSameSubString(String str1, String str2){
        if(str1 != null && str2 != null){
            String maxStr = (str1.length() > str2.length()) ? str1 : str2;
            String minStr = (str1.length() > str2.length()) ? str2 : str1;
            int len = minStr.length();
            for (int i = 0; i < len; i++) {//1、将短串的长度从尾部逐次减一
                for (int x = 0, y = len - i; y <= len; x++, y++){//2、将尾部逐次减一后的短串，在原始短串中去从前往后去滑动（类似于
                    // 滑动窗口）并判断长串中是否包含这一长度的短串。
                    if (maxStr.contains(minStr.substring(x, y))){
                        return minStr.substring(x, y);
                    }
                }
            }
        }
        return null;
    }

    //情况2：存在多个长度相同的子串
    public String[] getMaxSameSubStrings(String str1, String str2){// 此时先返回 String[]，后面可以用集合中的 ArrayList 替换，较方便
        if (str1 != null && str2 != null){
            StringBuffer stringBuffer = new StringBuffer();
            String maxStr = (str1.length() > str2.length()) ? str1 : str2;
            String minStr = (str1.length() > str2.length()) ? str2 : str1;
            int len = minStr.length();
            for (int i = 0; i < len; i++) {
                for (int x = 0, y = len -i; y <= len; x++, y++){//滑动窗口思想，和上面一样
                    String subString = minStr.substring(x, y);
                    if(maxStr.contains(subString)){
                        stringBuffer.append(subString + ",");
                    }
                }
                if (stringBuffer.length() > 0){//短串长度是逐次递减的，找到当前这个长度的公共子串后，即为最大公共子串，就没必要继续减长度了。
                    break;
                }
            }
            System.out.println(stringBuffer);
            String[] split = stringBuffer.toString().split(",");
            return split;

        }
        return null;
    }

    @Test
    public void test04(){//最大相同子串
        String str1 = "anpeng";
        String str2 = "enhulian";
        String str = getMaxSameSubString(str1, str2);
        System.out.println(str);
        String[] strs = getMaxSameSubStrings(str1, str2);
        System.out.println(Arrays.toString(strs));
    }

    //题目5、对字符串中字符进行自然排序
    @Test
    public void test05(){
        String str = "abcwerthelloyuiodef";
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        String newStr = new String(arr);
        System.out.println(newStr);
    }

}
