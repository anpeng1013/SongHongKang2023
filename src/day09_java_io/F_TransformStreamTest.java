package day09_java_io;

import org.junit.Test;

import java.io.*;

/**
 * 1、转换流（处理流）
 *      - 问题引入：
 *          -- 使用 FileReader 读取项目中的文本文件。由于 IDEA 设置中针对项目设置了UTF-8 编码，当读取 Windows 系统中创建的文本文件时，
 *              如果 Windows 系统默认的是 GBK 编码，则读入内存中会出现乱码。
 *          -- 针对文本文件，现在使用一个字节流进行数据的读入，希望将数据显示在控制台上。此时针对包含中文的文本数据，可能会出现乱码。
 *
 *      - 转换流的理解
 *          -- 作用：转换流是字节与字符间的桥梁！
 *              -- InputStreamReader：读取字节，解码为字符。
 *              -- OutputStreamWriter：写出字符，编码为字节。
 *
 * 2、InputStreamReader 与 OutputStreamWriter
 *      - InputStreamReader
 *              -- 转换流 java.io.InputStreamReader，是 Reader 的子类，是从字节流到字符流的桥梁。它读取字节，并使用指定的字符集将其解码为字符。
 *                  它的字符集可以由名称指定，也可以接受平台的默认字符集。
 *              -- 构造器
 *                  > InputStreamReader(InputStream in): 创建一个使用默认字符集的字符流。
 *                  > InputStreamReader(InputStream in, String charsetName): 创建一个指定字符集的字符流。
 *
 *      - OutputStreamWriter
 *              -- 转换流 java.io.OutputStreamWriter，是 Writer 的子类，是从字符流到字节流的桥梁。使用指定的字符集将字符编码为字节。
 *                  它的字符集可以由名称指定，也可以接受平台的默认字符集。
 *              -- 构造器
 *                  > OutputStreamWriter(OutputStream in): 创建一个使用默认字符集的字符流。
 *                  > OutputStreamWriter(OutputStream in, String charsetName): 创建一个指定字符集的字符流。
 *
 * 3、字符编码和字符集
 *      - 编码与解码
 *          -- 计算机中储存的信息都是用二进制数表示的，而我们在屏幕上看到的数字、英文、标点符号、汉字等字符是二进制数转换之后的结果。按照某种规则，将字符存储到
 *              计算机中，称为编码。反之，将存储在计算机中的二进制数按照某种规则解析显示出来，称为解码。
 *          -- 字符编码：就是一套自然语言的字符与二进制数之间的对应规则。编码：字符--> 字节。解码：字节 --> 字符。
 *          -- 乱码的情况：按照 A 规则存储，同样按照 A 规则解析，那么就能显示正确的文本符号。反之， 按照 A 规则存储，再按照 B 规则解析，就会导致乱码现象。
 *
 *      - 字符集
 *          -- 字符集 Charset：也叫编码表。是一个系统支持的所有字符的集合，包括各国家文字、标点符号、图形符号、数字等。
 *          -- 计算机要准确的存储和识别各种字符集符号，需要进行字符编码，一套字符集必然至少有一套字符编码。
 *          -- 常见字符集有 ASCII 字符集、 GBK 字符集、 Unicode 字符集等。
 *              > ASCII：使用单字节表示一个字符（最前面的 1 位统一规定为 0），共 128 个字符
 *              > ISO-8859-1：使用单字节编码，拉丁码表（荷兰语、德语、意大利语、葡萄语等），兼容ASCII编码。
 *              > GBK：最常用的中文码表，采用双字节编码。兼容GB2312（简体中文码表）、ASCII编码，同时支持繁体汉字以及日韩汉字等。
 *              > GB18030：最新的中文码表，采用多字节编码，每个字可以由1、2 、4个字节组成。支持中国国内少数民族的文字，同时支持繁体汉字以及日韩汉字等。
 *              > Unicode：
 *                  >> Unicode 编码为表达任意语言的任意字符而设计，也称为统一码、标准万国码。
 *                  >> Unicode 是字符集，UTF-8、UTF-16、UTF-32 是三种将数字转换到程序数据的编码方案。顾名思义，UTF-8就是每次8个bit位传输数据，
 *                      而UTF-16就是每次16个bit位。其中，UTF-8是在互联网上使用最广的一种Unicode的实现方式。注意：这里是传输而不是编码长度。
 *                  >> 所有互联网协议都必须支持 UTF-8 编码，UTF-8 是一种变长的编码方式。它使用 1 至 4 个字节为每个字符编码，编码规则：
 *                      >>> 128 个 US-ASCII 字符，只需一个字节编码。
 *                      >>> 拉丁文等字符，需要二个字节编码。
 *                      >>> 大部分常用字（含中文），使用三个字节编码。
 *                      >>> 其他极少使用的 Unicode 辅助字符，使用四字节编码。
 *          -- 注意：在中文操作系统上，ANSI（美国国家标准学会、AMERICAN NATIONAL STANDARDS INSTITUTE）编码即为 GBK；在英文操作系统上，
 *                  ANSI 编码即为 ISO-8859-1。
 *
 * @ClassName: F_TransformStreamTest.java
 * @Author: anpeng
 * @Date: 2024/3/22 15:38
 */
@SuppressWarnings("all")
public class F_TransformStreamTest {
    @Test
    public void testInputStreamReader() throws IOException {
        String gbk = "file/gbk.txt";
        // 定义变量,保存字符
        int charData;
        //方式1 -- 创建转换流，默认UTF-8编码
        InputStreamReader isr1 = new InputStreamReader(new FileInputStream(gbk));
        // 使用默认编码字符流读取,乱码
        while ((charData = isr1.read()) != -1) {
            System.out.print((char)charData); // 读出乱码：����������
        }
        isr1.close();
        System.out.println();

        //方式2 -- 创建转换流，指定GBK编码
        InputStreamReader isr2 = new InputStreamReader(new FileInputStream(gbk), "GBK");
        // 使用指定编码字符流读取,正常解码
        while ((charData = isr2.read()) != -1) {
            System.out.print((char)charData); // 正常解码：安鹏爱胡莉
        }
        isr2.close();
    }

    @Test
    public void testOutputStreamWriter() throws IOException{
        String gbk = "file/gbk.txt";
        //方式1 -- 创建转换流，默认UTF-8编码（中文为3个字节）
        OutputStreamWriter osw1 = new OutputStreamWriter(new FileOutputStream(new File(gbk), true));
        osw1.write("\n小狐狸学java");//出现乱码：灏忕嫄鐙稿java
        osw1.close();

        //方式2 -- 创建转换流，指定GBK编码（中文为2个字节
        OutputStreamWriter osw2 = new OutputStreamWriter(new FileOutputStream(new File(gbk), true), "GBK");
        osw2.write("\n小狐狸学java");//正常解码：小狐狸学java
        osw2.close();

    }

}
