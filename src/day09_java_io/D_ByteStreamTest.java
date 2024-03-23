package day09_java_io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 1、InputStream 和 OutputStream
 *      - 如果我们读取或写出的数据是非文本文件，则 Reader、 Writer 就无能为力了，必须使用字节流。
 *
 *      - 字节输入流：InputStream
 *          -- java.io.InputStream 抽象类是表示字节输入流的所有类的超类，可以读取字节信息到内存中。它定义了字节输入流的基本共性功能方法。
 *          -- public int read()： 从输入流读取一个字节。返回读取的字节值。虽然读取了一个字节，但是会自动提升为 int 类型。如果已经到达流末尾，
 *                  没有数据可读，则返回-1。
 *          -- public int read(byte[] b)： 从输入流中读取一些字节数，并将它们存储到字节数组 b 中 。每次最多读取 b.length 个字节。返回实际读取的字节个数。
 *                  如果已经到达流末尾，没有数据可读，则返回-1。
 *          -- public int read(byte[] b, int off, int len)：从输入流中读取一些字节数，并将它们存储到字节数组 b 中，从 b[off]开始存储，
 *                  每次最多读取 len 个字节 。返回实际读取的字节个数。如果已经到达流末尾，没有数据可读，则返回-1。
 *          -- public void close() ：关闭此输入流并释放与此流相关联的任何系统资源。
 *
 *      - 字节输入流：OutputStream
 *          -- java.io.OutputStream 抽象类是表示字节输出流的所有类的超类，将指定的字节信息写出到目的地。它定义了字节输出流的基本共性功能方法。
 *          -- public void write(int b) ：将指定的字节输出流。虽然参数为 int 类型四个字节，但是只会保留一个字节的信息写出。
 *          -- public void write(byte[] b)：将 b.length 个字节从指定的字节数组写入此输出流。
 *          -- public void write(byte[] b, int off, int len) ：从指定的字节数组写入 len 个字节，从偏移量 off 开始输出到此输出流。
 *          -- public void flush() ：刷新此输出流并强制任何缓冲的输出字节被写出。
 *          -- public void close() ：关闭此输出流并释放与此流相关联的任何系统资源。
 *
 * 2、FileInputStream 与 FileOutputStream（节点流，直接与数据源连接）
 *      - FileInputStream
 *          -- java.io.FileInputStream 类是文件输入流，从文件中读取字节。
 *          -- FileInputStream(File file)： 通过打开与实际文件的连接来创建一个FileInputStream。
 *          -- FileInputStream(String name)： 通过文件的路径名连接来创建一个FileInputStream。
 *
 *      - FileOutputStream
 *          -- java.io.FileOutputStream 类是文件输出流，用于将数据写出到文件。
 *          -- public FileOutputStream(File file)：创建文件输出流，写出由指定的 File 对象表示的文件。
 *          -- public FileOutputStream(String name)： 创建文件输出流，指定的名称为写出文件。
 *          -- public FileOutputStream(File file, boolean append)： 创建文件输出流，指明是否在现有文件末尾追加内容。
 *          -- public FileOutputStream(String name, boolean append)： 创建文件输出流，指明是否在现有文件末尾追加内容。
 *
 *
 * @ClassName: D_ByteStreamTest.java
 * @Author: anpeng
 * @Date: 2024/3/21 10:52
 */
@SuppressWarnings("all")
public class D_ByteStreamTest {
    @Test
    public void testFileInputStream01(){
        //实现方式1 -- 调用read()方法，一次读取一个字节。
        try (FileInputStream input = new FileInputStream("file/io.txt")){
            int read;
            while ((read = input.read()) != -1){
                System.out.print((char) read);
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }

    @Test
    public void testFileInputStream02(){
        // 实现方式2 -- 调用read(char[] cbuf)，每次从文件中读取多个字节。
        try (FileInputStream input = new FileInputStream("file/io.txt")){
            byte[] cbuf = new byte[5];
            int len;
            while ((len = input.read(cbuf)) != -1){
                // 最后错误数据foreverrev，是由于最后一次读取时，只读取两个字节`er`，数组中，上次读取的数据没有被完全替换，所以要通过`len`，获取有效的字节
                // 注意：window中的换行符为两个字节 \n\t
                System.out.print(new String(cbuf));
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void testFileInputStream03(){
        // 实现方式3 -- 调用read(char[] cbuf)，每次从文件中读取多个字节。
        try (FileInputStream input = new FileInputStream("file/io.txt")){
            byte[] cbuf = new byte[5];
            int len;
            while ((len = input.read(cbuf)) != -1){
                // 最后错误数据foreverrev，是由于最后一次读取时，只读取两个字节`er`，数组中，上次读取的数据没有被完全替换，所以要通过`len`，获取有效的字节
                // 注意：window中的换行符为两个字节 \n\t
                System.out.print(new String(cbuf, 0, len));//每次读取后,把数组的有效字节部分，变成字符串打印
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void testFileOutputStream01(){
        //实现方式1 -- 从头开始写，新建文件替换旧的文件。
        try (FileOutputStream output = new FileOutputStream("file/byte.txt")){
            for (int i = 97; i < 100 ; i++) {
                output.write(i);//abc
            }
            byte[] cbuf = "\nthis is FileOutputStream".getBytes(StandardCharsets.UTF_8);
            output.write(cbuf);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void testFileOutputStream02(){
        //实现方式2 -- 从尾部添加，原有内容不变。
        try (FileOutputStream output = new FileOutputStream("file/byte.txt",true)){
            output.write("\nthis is appended安鹏爱胡莉".getBytes(StandardCharsets.UTF_8));//这种方式可以代替转换流。
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

}
