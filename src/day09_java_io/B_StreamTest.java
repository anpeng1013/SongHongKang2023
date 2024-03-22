package day09_java_io;

import org.junit.Test;

/**
 * 1、java中的IO原理：
 *      - Java 程序中，对于数据的输入/输出操作以“流(stream)” 的方式进行，可以看做是一种数据的流动。
 *
 *      - I/O 流中的 I/O 是 Input/Output 的缩写， I/O 技术是非常实用的技术，用于处理设备之间的数据传输。如读/写文件，网络通讯等。
 *          -- 输入 input：读取外部数据（磁盘、光盘等存储设备的数据）到程序（内存）中。
 *          -- 输出 output：将程序（内存）数据输出到磁盘、光盘等存储设备中。
 *
 * 2、流的分类：
 *      java.io 包下提供了各种“流”类和接口，用以获取不同种类的数据，并通过标准的方法输入或输出数据。
 *      - 按数据的流向不同，分为：输入流和输出流。
 *          -- 输入流：把数据从其他设备读取到内存中的流。
 *              > 以InputStream、Reader结尾。
 *          -- 输出流：把数据从内存中写入到其他设备上的流。
 *              > 以OutputStream、Writer结尾。
 *
 *     - 按操作数据单位的不同，分为：字节流（8bit）和字符流（16bit）
 *          -- 字节流：以字节为单位，读写数据的流。
 *              > 以InputStream、OutputStream结尾。
 *              > 以Reader、Writer结尾。
 *
 *     - 按IO流的角色不同，分为：节点流和处理流。
 *          -- 节点流：直接从数据源或目的地读写数据。
 *          -- 处理流：不直接连接到数据源或目的地，而是连接在已存在的流（节点流或处理流）之上，通过对数据的处理为程序提供更为强大的读写能力。
 *
 * 3、流的API：
 *      - java的IO流共涉及40多个类，实际上非常规则，都是从如下4个抽象基类中派生出来的。
 *          （抽象基类）          输入流         输出流
 *            字节流           InputStream     OutputStream
 *            字符流           Reader          Writer
 *
 *      - 由这四个类派生出来的子类名称都是以其父类名作为子类名后缀。
 *          分类          字节输入流               字节输出流               字符输入流               字符输出流
 *          抽象基类      InputStream            OutputStream             Reader                 Writer
 *          访问文件      FileInputStream        FileOutputStream         FileReader             FileWriter
 *          访问数组      ByteArrayInputStream   ByteArrayOutputStream    CharArrayReader        CharArrayWriter
 *          访问管道      PipedInputStream       PipedOutputStream        PipedReader            PipedWriter
 *          访问字符串                                                    StringReader           StringWriter
 *          缓冲流        BufferedInputStream    BufferedOutputStream     BufferedReader         BufferedWriter
 *          转换流                                                        InputStreamReader      OutputStreamWriter
 *          对象流        ObjectInputStream      ObjectOutputStream
 *          过滤流        FilterInputStream      FilterOutputStream       FilterReader           FilterWriter
 *          打印流                               PrintStream                                     PrintWriter
 *          推回输入流     PushbackInputStream                             PushbackReader
 *          数据流        DataInputStream        DataOutputStream
 *
 *      - 常用的节点流：
 *          -- 文件流：FileInputStream、 FileOutputStream、 FileReader、 FileWriter（数据源为硬盘中的文件）
 *          -- 数组流：ByteArrayInputStream、 ByteArrayOutputStream、CharArrayReader、 CharArrayWriter（数据源为内存中的数组）
 *
 *      - 常用的处理流：
 *          -- 缓冲流：BufferedInputStream、 BufferedOutputStream、 BufferedReader、BufferedWriter（增加缓冲区，避免频繁读写硬盘，提升读写效率）
 *          -- 转换流：InputStreamReader、 OutputStreamReader（实现字节流和字符流之间的转换）
 *          -- 对象流：ObjectInputStream、 ObjectOutputStream（提供直接读写 Java 对象功能）
 *
 * @ClassName: B_IOTest.java
 * @Author: anpeng
 * @Date: 2024/3/19 16:43
 */
@SuppressWarnings("all")
public class B_StreamTest {
    @Test
    public void testSummary(){
        System.out.println("this is Stream summary.");
    }

}
