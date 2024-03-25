package day10_java_net;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 1、URL类
 *      - 总结
 *          -- URL(Uniform Resource Locator)：统一资源定位符，它表示 Internet 上某一资源的地址。
 *          -- 通过URL我们可以访问Internet上的各种网络资源，比如最常见的www，ftp站点。浏览器通过解析给定的URL可以在网络上查找相应的文件或其他资源。
 *          -- URL 的基本结构由 5 部分组成：<传输协议>://<主机名>:<端口号>/<文件名>#片段名?参数列表，例如：
 *              http://192.168.1.100:8080/helloworld/index.jsp#a?username=shkstart&password=123
 *              > 片段名：即锚点，例如看小说，直接定位到章节（http://192.168.1.100:8080/helloworld/index.jsp）
 *              > 参数列表格式：参数名=参数值&参数名=参数值（username=shkstart&password=123）
 *
 *      - 构造器
 *          -- 为了表示 URL， java.net 中实现了类 URL。我们可以通过下面的构造器来初始化一个 URL 对象：
 *          -- public URL (String spec)：通过一个表示URL地址的字符串可以构造一个URL对象。例如：URL url = new URL("http://www. atguigu.com/");
 *          -- public URL(URL context, String spec)：通过基 URL 和相对 URL 构造一个URL 对象。例如：
 *              > URL downloadUrl = new URL(url, “download.html")
 *          -- public URL(String protocol, String host, String file); 例如:
 *              > URL url = new URL("http", "www.atguigu.com", “download. html");
 *          -- public URL(String protocol, String host, int port, String file); 例如:
 *              > URL gamelan = new URL("http", "www.atguigu.com", 80, “download.html");
 *          -- URL 类的构造器都声明抛出非运行时异常，必须要对这一异常进行处理，通常是用try-catch 语句进行捕获。
 *
 *      - 常用方法
 *          -- 一个 URL 对象生成后，其属性是不能被改变的，但可以通过它给定的方法来获取这些属性：
 *              > public String getProtocol( ) 获取该 URL 的协议名
 *              > public String getHost( ) 获取该 URL 的主机名
 *              > public String getPort( ) 获取该 URL 的端口号
 *              > public String getPath( ) 获取该 URL 的文件路径
 *              > public String getFile( ) 获取该 URL 的文件名
 *              > public String getQuery( ) 获取该 URL 的查询名
 *
 * 3、URLConnection 类
 *      - 总结
 *          -- URL 的方法 openStream()：能从网络上读取数据。
 *          -- 若希望输出数据，例如向服务器端的 CGI （公共网关接口-Common Gateway Interface-的简称，是用户浏览器和服务器端的应用程序进行连接的接口）程序
 *              发送一些数据，则必须先与 URL 建立连接，然后才能对其进行读写，此时需要使用URLConnection 。
 *
 *      - 构造器
 *          -- URLConnection：表示到 URL 所引用的远程对象的连接。当与一个 URL 建立连接时，首先要在一个 URL 对象上通过方法 openConnection() 生成
 *              对应的 URLConnection 对象。如果连接过程失败，将产生 IOException.
 *              > URL netchinaren = new URL ("http://www.atguigu.com/index.shtml");
 *              > URLConnectonn u = netchinaren.openConnection();
 *
 *      - 常用方法
 *          -- 通过 URLConnection 对象获取的输入流和输出流，即可以与现有的 CGI 程序进行交互。
 *          –- public Object getContent( ) throws IOException
 *          –- public int getContentLength( )
 *          –- public String getContentType( )
 *          –- public long getDate( )
 *          –- public long getLastModified( )
 *          –- public InputStream getInputStream ( ) throws IOException
 *          –- public OutputSteram getOutputStream( )throws IOException
 *
 * 4、小结
 *      - 位于网络中的计算机具有唯一的 IP 地址，这样不同的主机可以互相区分。
 *
 *      - 客户端－服务器是一种最常见的网络应用程序模型。服务器是一个为其客户端提供某种特定服务的硬件或软件。客户机是一个用户应用程序， 用于访问某台服务器
 *          提供的服务。端口号是对一个服务的访问场所，它用于区分同一物理计算机上的多个服务。套接字用于连接客户端和服务器，客户端和服务器之间的每个通信会话
 *          使用一个不同的套接字。 TCP 协议用于实现面向连接的会话。
 *
 *      - Java中有关网络方面的功能都定义在java.net程序包中。Java用InetAddress对象表示IP地址，该对象里有两个字段：主机名(String) 和 IP 地址(int)。
 *
 *      - 类 Socket 和 ServerSocket 实现了基于 TCP 协议的客户端－服务器程序。 Socket 是客户端和服务器之间的一个连接，连接创建的细节被隐藏了。这个连接
 *          提供了一个安全的数据传输通道，这是因为 TCP 协议可以解决数据在传送过程中的丢失、损坏、重复、乱序以及网络拥挤等问题，它保证数据可靠的传送。
 *
 *      - 类 URL 和 URLConnection 提供了最高级网络应用。 URL 用网络资源的位置来同一表示 Internet 上各种网络资源。通过 URL 对象可以创建当前应用程序
 *          和 URL 表示的网络资源之间的连接，这样当前程序就可以读取网络资源数据，或者把自己的数据传送到网络上去。
 *
 * @ClassName: E_URLTest.java
 * @Description:
 * @Author: anpeng
 * @Date: 2024/3/25 10:36
 */
@SuppressWarnings("all")
public class E_URLTest {
    @Test
    public void testURL() throws MalformedURLException {
        URL url = new URL("http://localhost:8080/examples/myTest.txt");
        System.out.println("getProtocol() :"+url.getProtocol());
        System.out.println("getHost() :"+url.getHost());
        System.out.println("getPort() :"+url.getPort());
        System.out.println("getPath() :"+url.getPath());
        System.out.println("getFile() :"+url.getFile());
        System.out.println("getQuery() :"+url.getQuery());
    }

}
