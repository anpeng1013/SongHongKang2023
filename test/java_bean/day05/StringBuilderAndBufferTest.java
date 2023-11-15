package java_bean.day05;

import org.junit.Test;

/**
 * @ClassName: StringBuilderAndBufferTest.java
 * @Author: anpeng
 * @Date: 2023/11/15 14:37
 */
@SuppressWarnings("all")
public class StringBuilderAndBufferTest {
    @Test
    public void testStringBuilderAndBuffer(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello").append(" world ").append('a').append(true).append(1024).append(11.24);
        System.out.println(stringBuilder);//hello world atrue102411.24
        System.out.println(stringBuilder.length());//26

        stringBuilder.deleteCharAt(12);
        System.out.println(stringBuilder);//hello world true102411.24

        System.out.println(stringBuilder.reverse());//42.114201eurt dlrow olleh

        StringBuffer stringBuffer = new StringBuffer("anpeng");
        stringBuffer.insert(2, " ");
        System.out.println(stringBuffer);//an peng

        stringBuffer.delete(2, stringBuffer.length());
        System.out.println(stringBuffer);//an

        stringBuffer.setCharAt(1, 'p');
        System.out.println(stringBuffer);//ap

        stringBuffer.setLength(5);
        System.out.println(stringBuffer);//ap***
        System.out.println(stringBuffer.charAt(3));//*

    }

    @Test
    public void testEfficiency(){
        //初始设置
        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");

        //效率对比 StringBuilder > StringBuffer > String
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder 的执行时间： " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            buffer.append(String.valueOf(i));}
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer 的执行时间： " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String 的执行时间： " + (endTime - startTime));
    }
    
}
