package day09_java_io;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 1、Reader与Writer（字符流）
 *      - Java 提供一些字符流类，以字符为单位读写数据，专门用于处理文本文件。不能操作图片，视频等非文本文件。
 *          -- 常见的文本文件有如下的格式： .txt、 .java、 .c、 .cpp、 .py 等。
 *          -- 注意： .doc、 .xls、 .ppt 这些都不是文本文件。
 *
 *      - 字符输入流：Reader
 *          -- java.io.Reader 抽象类是表示用于读取字符流的所有类的父类，可以读取字符信息到内存中。它定义了字符输入流的基本共性功能方法。
 *          -- public int read()：从输入流读取一个字符。虽然读取了一个字符，但是会自动提升为 int 类型。返回该字符的 Unicode 编码值。
 *                                如果已经到达流末尾了，则返回-1。
 *          -- public int read(char[] cbuf)： 从输入流中读取一些字符，并将它们存储到字符数组 cbuf 中 。每次最多读取 cbuf.length 个字符。
 *                                返回实际读取的字符个数。如果已经到达流末尾，没有数据可读，则返回-1。（cbuf, 即 char buffer）
 *          -- public int read(char[] cbuf,int off,int len)：从输入流中读取一些字符，并将它们存储到字符数组 cbuf 中，从 cbuf[off]开始的位置存储。
 *                                每次最多读取len 个字符。返回实际读取的字符个数。如果已经到达流末尾，没有数据可读，则返回-1。
 *          -- public void close() ：关闭此流并释放与此流相关联的任何系统资源。当完成流的操作时，必须调用 close()方法，释放系统资源，否则会造成内存泄漏。
 *
 *      - 字符输出流：Writer
 *          -- java.io.Writer 抽象类是表示用于写出字符流的所有类的超类，将指定的字符信息写出到目的地。它定义了字节输出流的基本共性功能方法。
 *          -- public void write(int c) ：写出单个字符。
 *          -- public void write(char[] cbuf)：写出字符数组。
 *          -- public void write(char[] cbuf, int off, int len)：写出字符数组的某一部分。 off：数组的开始索引； len：写出的字符个数。
 *          -- public void write(String str)：写出字符串。
 *          -- public void write(String str, int off, int len) ：写出字符串的某一部分。 off：字符串的开始索引； len：写出的字符个数。
 *          -- public void flush()：刷新该流的缓冲。
 *          -- public void close() ：关闭此流。当完成流的操作时，必须调用 close()方法，释放系统资源，否则会造成内存泄漏。
 *
 * 2、FileReader与FileWriter（节点流，直接与数据源连接）
 *      - FileReader
 *          -- java.io.FileReader 类用于读取字符文件，构造时使用系统默认的字符编码和默认字节缓冲区。
 *          -- FileReader(File file)： 创建一个新的 FileReader ，给定要读取的 File 对象。
 *          -- FileReader(String fileName)： 创建一个新的 FileReader ，给定要读取的文件的名称。
 *
 *      - FileWriter
 *          -- java.io.FileWriter 类用于写出字符到文件，构造时使用系统默认的字符编码和默认字节缓冲区。
 *          -- FileWriter(File file)： 创建一个新的 FileWriter，给定要读取的 File 对象。
 *          -- FileWriter(String fileName)： 创建一个新的 FileWriter，给定要读取的文件的名称。
 *          -- FileWriter(File file, boolean append)： 创建一个新的 FileWriter，指明是否在现有文件末尾追加内容。
 *
 *      - 总结
 *          -- 因为出现流资源的调用，为了避免内存泄漏，需要使用 try-catch-finally 处理异常。
 *          -- 对于输入流来说，File类的对象必须在物理磁盘上存在，否则执行就会报FileNotFoundException。如果传入的是一个目录，则会报IOException异常。
 *          -- 对于输出流来说， File 类的对象是可以不存在的。
 *              > 如果 File 类的对象不存在，则可以在输出的过程中，自动创建 File 类的对象。
 *              > 如果 File 类的对象存在：
 *                  >> 如果调用 FileWriter(File file)或 FileWriter(File file,false)，输出时会新建 File 文件覆盖已有的文件。
 *                  >> 如果调用 FileWriter(File file,true)构造器，则在现有的文件末尾追加写出内容。
 *
 * 3、关于flush（刷新）
 *      - 因为内置缓冲区的原因，如果 FileWriter 不关闭输出流，无法写出字符到文件中。但是关闭的流对象，是无法继续写出数据的。如果我们既想写出数据，
 *        又想继续使用流，就需要 flush() 方法了。
 *          -- flush() ：刷新缓冲区，流对象可以继续使用。
 *          -- close()：先刷新缓冲区，然后通知系统释放资源。流对象不可以再被使用了。
 *          -- 注意：即便是 flush()方法写出了数据，操作的最后还是要调用 close 方法，释放系统资源。
 *
 * @ClassName: C_FileReaderAndFileWriterTest.java
 * @Author: anpeng
 * @Date: 2024/3/20 11:27
 */
@SuppressWarnings("all")
public class C_CharStreamTest {
    @Test
    public void testFileReader01() throws IOException {
        //实现方式1 -- 调用read()方法，一次读取一个字符。
        File file = new File("file/io.txt");
        FileReader reader = new FileReader(file);
        int data;
        while ((data = reader.read())!=-1){
            System.out.print((char) data);
        }
        reader.close();
    }

     @Test
     public void testFileReader02(){
        //实现方式2 -- 在1的基础上，使用try-catch-finally处理异常，保证流是可以关闭的。
         FileReader reader = null;
         try{
             File file = new File("file/io.txt");
             reader = new FileReader(file);
             int data;
             while ((data = reader.read())!= -1){
                 System.out.print((char) data);
             }
         }catch (IOException exception){
             exception.printStackTrace();
         }finally {
             try{
                 if (reader != null)
                     reader.close();
             }catch (IOException exception){
                 exception.printStackTrace();
             }
         }
     }

     @Test
     public void testFileReader03(){
         // 实现方式3 -- 调用read(char[] cbuf)，每次从文件中读取多个字符。
         // 资源的获取放在try后面的括号中可以实现自动资源管理，不必在finally中释放资源了
         try(FileReader reader = new FileReader("file/io.txt")){
             char[] cbuf = new char[10];
             int len;
             while ((len = reader.read(cbuf)) != -1){
                 System.out.print(new String(cbuf, 0, len));
             }
         }catch(IOException exception){
             exception.printStackTrace();
         }
     }

     @Test
     public void testFileWriter01() {
         //实现方式1 -- 从头开始写，新建文件替换旧的文件。
         try(FileWriter writer = new FileWriter("file/anpeng.txt")){
             char[] chars = "安鹏爱胡莉".toCharArray();
             writer.write(chars);
         }catch (IOException exception){
             exception.printStackTrace();
         }
     }

     @Test
     public void testFileWriter02() {
         //实现方式2 -- 从尾部添加，原有内容不变。
         try (FileWriter writer = new FileWriter(new File("file/anpeng.txt"), true)){
             writer.write("\nanpeng love huli");
         }catch (IOException exception){
             exception.printStackTrace();
         }
     }

     @Test
     public void testFileWriter03() throws IOException {
         FileWriter writer = new FileWriter(new File("file/anpeng.txt"), true);
         //写出数据，通过flush
         writer.write('\n');
         writer.write("刷新\n");
         writer.flush();

         //写出数据，通过close
         writer.write("关闭");
         writer.close();
     }

}
