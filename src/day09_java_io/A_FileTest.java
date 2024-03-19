package day09_java_io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 1、概述：
 *      - File 类及本包下的各种流，都定义在 java.io 包下。
 *      - 一个 File 对象代表硬盘或网络中可能存在的一个文件或者文件目录（俗称文件夹），与平台无关。（体会万事万物皆对象）
 *      - File 能新建、删除、重命名文件和目录，但File不能访问文件内容本身。如果需要访问文件内容本身，则需要使用输入/输出流。
 *          -- File对象可以作为参数传递给流的构造器。
 *      - 想要在Java程序中表示一个真实存在的文件或目录，那么必须有一个File对象，但是Java程序中的一个File对象，可能没有对应存在的文件或目录。
 *
 * 2、构造器：
 *      - public File(String pathname)：以 pathname 为路径创建 File 对象，可以是绝对路径或者相对路径。如果 pathname 是相对路径，则默认的
 *          当前路径在系统属性 user.dir（见day05_java_library/E_SystemTest.java）中存储。
 *      - public File(String parent, String child) ：以 parent 为父路径， child 为子路径创建 File 对象。
 *      - public File(File parent, String child) ：根据一个父 File 对象和子文件路径创建 File 对象。
 *          -- 绝对路径：从盘符开始的路径，这是一个完整的路径。
 *          -- 相对路径：相对于项目目录的路径，这是一个便捷的路径，开发中经常使用。
 *              > IDEA 中， main 中的文件的相对路径，是相对于"当前工程"。
 *              > IDEA 中，单元测试方法中的文件的相对路径，是相对于"当前 module"。
 *      注意：
 *          -- 无论该路径下是否存在文件或者目录，都不影响 File 对象的创建。
 *          -- 当构造路径是绝对路径时，那么 getPath 和 getAbsolutePath 结果一样。
 *          -- 当构造路径是相对路径时，那么 getAbsolutePath 的路径 = user.dir的路径 + 构造路径。
 *          -- window 的路径分隔符使用“\”，而 Java 程序中的“\”表示转义字符，所以在 Windows 中表示路径，需要用“\”。或者直接使用“/”也可以， Java 程序
 *              支持将“/”当成平台无关的路径分隔符。或者直接使用File.separator 常量值表示。比如：
 *              File file2 = new File("d:" + File.separator + "atguigu" + File.separator + "info.txt");
 *
 * 3、常用方法：
 *      - 获取文件和目录的基本信息：
 *      如果 File 对象代表的文件或目录存在，则 File 对象实例初始化时，就会用硬盘中对应文件或目录的属性信息（例如，时间、类型等）为File 对象的属性赋值，
 *      否则除了路径和名称， File 对象的其他属性将会保留默认值。
 *          -- public String getName()：获取名称。
 *          -- public boolean renameTo(File dest)：把文件重命名为指定的文件路径。
 *          -- public String getPath()：获取构造路径。
 *          -- public String getAbsolutePath()：获取绝对路径。
 *          -- public File getAbsoluteFile()：获取绝对路径表示的文件。
 *          -- public String getParent()：获取上层文件目录路径。若无，返回 null。
 *          -- public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
 *          -- public long lastModified() ：获取最后一次的修改时间，毫秒值。
 *          -- public String[] list() ：返回一个 String 数组，表示该 File 目录中的所有子文件或目录。
 *          -- public File[] listFiles() ：返回一个 File 数组，表示该 File 目录中的所有的子文件或目录。
 *      - 判断功能的方法：
 *          -- public boolean exists() ：此 File 表示的文件或目录是否实际存在。
 *          -- public boolean isDirectory() ：此 File 表示的是否为目录。
 *          -- public boolean isFile() ：此 File 表示的是否为文件。
 *          -- public boolean canRead() ：判断是否可读
 *          -- public boolean canWrite() ：判断是否可写
 *          -- public boolean isHidden() ：判断是否隐藏
 *      - 创建、删除功能的方法：
 *          -- public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false。
 *          -- public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
 *          -- public boolean mkdirs() ：创建文件目录。如果上层文件目录不存在，一并创建。
 *          -- public boolean delete() ：删除文件或者文件夹。
 *              删除注意事项： ① Java中的删除不走回收站。 ② 要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录。
 *
 *
 *
 *
 * @ClassName: A_FileTest.java
 * @Author: anpeng
 * @Date: 2024/3/19 15:27
 */
@SuppressWarnings("all")
public class A_FileTest {
    @Test
    public void testPath(){
        File file = new File("file/io.txt");
        System.out.println("user.dir=" + System.getProperty("user.dir"));
        System.out.println("文件/目录的名称：" + file.getName());
        System.out.println("文件/目录的构造路径：" + file.getPath());
        System.out.println("文件/目录的绝对路径：" + file.getAbsolutePath());
        System.out.println("文件/目录的父目录名：" + file.getParent());
    }

    @Test
    public void testInfo(){
        File file = new File("file/io.txt");
        System.out.println("文件名称：" + file.getName());
        System.out.println("文件大小：" + file.length());
        System.out.println("文件最后修改时间：" + LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.of("Asia/Shanghai")));
        file = new File("file/anpeng.txt");
        file.renameTo(new File("file/love.txt"));

        File dir = new File("file");
        File[] files = dir.listFiles();
        for (File f : files) {
            System.out.println("file = " + f);
        }
    }

    @Test
    public void testJudge(){
        File file = new File("file/io.txt");
        File dir = new File("file");
        System.out.println("file.exists() = " + file.exists());
        System.out.println("dir.exists() = " + dir.exists());
        System.out.println("file.isFile() = " + file.isFile());
        System.out.println("dir.isDirectory() = " + dir.isDirectory());
    }

    @Test
    public void testCreateDelete() throws IOException, InterruptedException {
        File file = new File("file/anpeng.txt");
        if(!file.exists()) file.createNewFile();

        File dir = new File("file/dir");
        if(!dir.exists()) dir.mkdirs();

        file = new File("file/love.txt");
        System.out.println("file.delete() = " + file.delete());

    }
    
}
