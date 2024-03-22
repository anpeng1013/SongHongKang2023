package day09_java_io;

import org.junit.Test;

import java.io.*;

/**
 * 1、缓冲流（处理流）
 *      - 为了提高数据读写的速度， Java API 提供了带缓冲功能的流类：缓冲流。
 *      - 缓冲流要“套接”在相应的节点流之上，根据数据操作单位可以把缓冲流分为：
 *          -- 字节缓冲流： BufferedInputStream， BufferedOutputStream
 *          -- 字符缓冲流： BufferedReader， BufferedWriter
 *      - 缓冲流的基本原理：在创建流对象时，内部会创建一个缓冲区数组（缺省使用 8192 个字节(8Kb)的缓冲区），通过缓冲区读写，减少系统IO次数，从而提高读写的效率。
 *
 * 2、构造器
 *      - public BufferedInputStream(InputStream in) ：创建一个新的字节型的缓冲输入流。
 *      - public BufferedOutputStream(OutputStream out)： 创建一个新的字节型的缓冲输出流。
 *      - public BufferedReader(Reader in) ：创建一个新的字符型的缓冲输入流。
 *      - public BufferedWriter(Writer out)： 创建一个新的字符型的缓冲输出流。
 *
 * 3、效率测试
 *      - 查询 API，缓冲流读写方法与基本的流是一致的，我们通过复制大文件（375MB），测试它的效率。缓冲流的效率高很多。
 *
 * 4、特有方法
 *      - 字符缓冲流的基本方法与普通字符流调用方式一致，不再阐述，下面字符缓冲流特有方法
 *          -- BufferedReader： public String readLine(): 读一行文字。
 *          -- BufferedWriter： public void newLine(): 写一行行分隔符,由系统属性定义符号。
 *
 *      - 涉及到嵌套的多个流时，如果都显式关闭的话，需要先关闭外层流，再关闭内层流。但在开发中，只需关闭最外层流即可，因为在关闭外层流时，内层流也会被关闭。
 *
 * @ClassName: E_BufferedStreamTest.java
 * @Author: anpeng
 * @Date: 2024/3/22 10:59
 */
@SuppressWarnings("all")
public class E_BufferedStreamTest {

    //方法1：使用FileInputStream\FileOutputStream实现非文本文件的复制
    public void copyFileWithFileStream(String srcPath, String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            //1、造文件流
            fis = new FileInputStream(srcPath);
            fos = new FileOutputStream(destPath);
            //2、复制操作（读写）
            byte[] buffer = new byte[1024];
            int len;//每次读入到buffer中字节的个数
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
            System.out.println("复制成功");
        } catch (IOException exception){
            throw new RuntimeException(exception);
        }finally {
            //3、关闭资源
            try{
                if(fis != null)
                    fis.close();
            } catch (IOException exception){
                throw new RuntimeException(exception);
            }
            try{
                if(fos != null)
                    fos.close();
            } catch (IOException exception){
                throw new RuntimeException(exception);
            }
        }
    }

    @Test
    public void testNoBuffered(){
        String srcPath = "E:\\src.mp4";
        String destPath = "E:\\dest.mp4";
        long start = System.currentTimeMillis();
        copyFileWithFileStream(srcPath, destPath);
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start) + "毫秒");//2890毫秒
    }

    //方法2：使用BufferedInputStream\BufferedOutputStream实现非文本文件的复制
    public void copyFileWithBufferedStream(String srcPath, String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1、造文件流
            fis = new FileInputStream(srcPath);
            fos = new FileOutputStream(destPath);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //2、读写操作（复制）
            int len;
            byte[] buffer = new byte[1024];
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer, 0, len);
            }
            System.out.println("复制成功");
        } catch(IOException exception){
            exception.printStackTrace();
        } finally {
            //3.关闭资源流--先关闭外面的流，再关闭内部的流
            try {
                if(bis != null)
                    bis.close();
            }catch(IOException exception){
                throw new RuntimeException(exception);
            }
            try {
                if(bos != null)
                    bos.close();
            }catch(IOException exception){
                throw new RuntimeException(exception);
            }
            try {
                if(fis != null)
                    fis.close();
            }catch(IOException exception){
                throw new RuntimeException(exception);
            }
            try {
                if(fos != null)
                    fos.close();
            }catch(IOException exception){
                throw new RuntimeException(exception);
            }
        }
    }

    @Test
    public void testBuffered(){
        String srcPath = "E:\\src.mp4";
        String destPath = "E:\\dest.mp4";
        long start = System.currentTimeMillis();
        copyFileWithBufferedStream(srcPath, destPath);
        long end = System.currentTimeMillis();
        System.out.println("花费的时间：" + (end - start) + "毫秒");//267毫秒
    }

    @Test
    public void testReadline() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file/io.txt"));
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
        br.close();
    }

    @Test
    public void testNewline() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("file/io.txt"), true));
        bw.newLine();
        bw.write("java");
        bw.newLine();
        bw.write("python");
        bw.close();
    }

}
