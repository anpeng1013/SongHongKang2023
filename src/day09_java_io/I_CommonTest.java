package day09_java_io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

/**
 * 1、Apache-common包的介绍：
 *      - IO 技术开发中，代码量很大，而且代码的重复率较高，为此 Apache 软件基金会，开发了 IO 技术的工具类 commonsIO，大大简化了 IO 开发。
 *      - Apahce 软件基金会属于第三方，（Oracle 公司第一方，我们自己第二方，其他都是第三方）我们要使用第三方开发好的工具，需要添加 jar 包。
 *
 * 2、导包
 *      - 在导入 commons-io-2.5.jar 包之后，内部的 API 都可以使用。
 *
 *      - IOUtils 类的使用
 *          -- 静态方法：IOUtils.copy(InputStream in, OutputStream out)传递字节流，实现文件复制。
 *          -- 静态方法： IOUtils.closeQuietly(任意流对象)悄悄的释放资源，自动处理 close()方法抛出的异常。
 *
 *      - FileUtils 类的使用
 *          -- 静态方法：void copyDirectoryToDirectory(File src, File dest)：整个目录的复制，自动进行递归遍历。
 *          -- 静态方法：void writeStringToFile(File file,String content)：将内容 content 写入到 file 中。
 *          -- 静态方法： String readFileToString(File file)：读取文件内容，并返回一个String。
 *          -- 静态方法： void copyFile(File srcFile,File destFile)：文件复制
 *
 * @ClassName: I_CommonTest.java
 * @Author: anpeng
 * @Date: 2024/3/23 11:03
 */
@SuppressWarnings("all")
public class I_CommonTest {
    @Test
    public void testIOUtils() throws IOException {
        //- 静态方法：IOUtils.copy(InputStream in,OutputStream out)传递字节流，实现文件复制。
        IOUtils.copy(new FileInputStream("file/huli.jpg"), new FileOutputStream("file/beauty.jpg"));

        //- 静态方法： IOUtils.closeQuietly(任意流对象)悄悄的释放资源，自动处理 close()方法抛出的异常。
        FileWriter fw = null;
        try {
            fw = new FileWriter("file/io.txt", true);
            fw.write("huli is beautiful");
        }catch (IOException exception){
            exception.printStackTrace();
        }finally {
            IOUtils.closeQuietly(fw);//自动处理close的异常，不用判断fw字符流是否为空了
        }
    }

    @Test
    public void testFileUtils(){
        try {
            //整个目录的复制，自动进行递归遍历。
            FileUtils.copyDirectory(new File("file/dir"), new File("file/copyDir"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
