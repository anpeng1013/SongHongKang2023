package day09_java_io;

import java_bean.day09.Employee;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

/**
 * 1、数据流与对象流（处理流）
 *      - 问题引入：如果需要将内存中定义的变量（包括基本数据类型或引用数据类型）保存在文件中，那怎么办呢？
 *
 *      - java提供了数据流和对象流来处理这些类型的数据。
 *          -- 数据流：DataInputStream、DataOutputStream
 *              > DataInputStream：允许应用程序以与机器无关的方式从底层输入流中读取基本数据类型、 String 类型的变量。
 *              > DataOutputStream：允许应用程序将基本数据类型、 String 类型的变量写入输出流中。
 *              > 弊端：只支持Java基本数据类型和字符串的读写，而不支持其它Java对象的类型。而对象流ObjectOutputStream和ObjectInputStream
 *                  既支持Java基本数据类型的数据读写，又支持Java对象的读写，所以重点介绍对象流 ObjectOutputStream 和 ObjectInputStream。
 *
 *          -- 对象流：ObjectOutputStream、ObjectInputStream
 *              > ObjectOutputStream：将Java基本数据类型和对象写入字节输出流中。通过在流中使用文件可以实现Java各种基本数据类型的数据以及对象的持久存储。
 *              > ObjectInputStream：对以前使用 ObjectOutputStream 写出的基本数据类型的数据和对象进行读入操作，保存在内存中。
 *
 * 2、对象流API
 *      - 对象输出流：ObjectOutputStream（处理流）
 *          -- 构造器：public ObjectOutputStream(OutputStream out)： 创建一个指定的ObjectOutputStream。
 *          -- 常用方法：
 *              > public void writeBoolean(boolean val)：写出一个 boolean 值
 *              > public void writeByte(int val)：写出一个 8 位字节
 *              > public void writeShort(int val)：写出一个 16 位的 short 值
 *              > public void writeChar(int val)：写出一个 16 位的 char 值
 *              > public void writeInt(int val)：写出一个 32 位的 int 值
 *              > public void writeLong(long val)：写出一个 64 位的 long 值
 *              > public void writeFloat(float val)：写出一个 32 位的 float 值
 *              > public void writeDouble(double val)：写出一个 64 位的 double 值（以上为写入八大基本数据类型）
 *              > public void writeUTF(String str)：根据字符的值，将字符串str中每个字符转换成一个字节、两个字节或三个字节的字节组。
 *              > public void writeObject(Object obj)：写出一个 obj 对象
 *              > public void close() ：关闭此输出流并释放与此流相关联的任何系统资源
 *
 *      - 对象输入流：ObjectInputStream（处理流）
 *          -- 构造器：public ObjectInputStream(InputStream in)： 创建一个指定的ObjectInputStream。
 *          -- 常用方法：将ObjectOutputStream上述的方法中的 write 改为相应的 read 即可。
 *
 * 3、认识对象序列化机制
 *      - 序列化机制的原理：对象序列化机制允许把内存中的 Java 对象转换成平台无关的二进制流，从而允许把这种二进制流持久地保存在磁盘上，或通过网络将这种二进制流
 *          传输到另一个网络节点。当其它程序获取了这种二进制流，就可以恢复成原来的 Java 对象。
 *          -- 序列化过程：用一个字节序列表示一个对象，该字节序列包含该对象的类型和存储的属性等信息。字节序列写出到文件之后，持久保存了一个对象的信息。
 *          -- 反序列化过程：该字节序列还可以从文件中读取回来，重构对象，对它进行反序列化。对象的数据、类型和存储的数据信息，都可以用来在内存中创建对象。
 *
 *      - 序列化的重要性：序列化是RMI（Remote Method Invoke）过程的参数和返回值都必须实现的机制，而RMI是JavaEE的基础。即序列化机制是JavaEE平台的基础。
 *
 *      - 实现原理：
 *          -- 序列化：用 ObjectOutputStream 类保存基本类型数据或对象的机制。方法为：
 *              > public final void writeObject (Object obj): 将指定的对象写出。
 *          -- 反序列化：用 ObjectInputStream 类读取基本类型数据或对象的机制。方法为：
 *              > public final Object readObject () : 读取一个对象。
 *
 * 4、如何实现对象序列化机制
 *      - 如果需要让某个对象支持序列化机制，则必须让对象所属的类及其属性是可序列化的，即该类必须实现java.io.Serializable接口。
 *          Serializable 是一个标记接口，不实现此接口的类将不会使任何状态序列化或反序列化，会抛出 NotSerializableException。
 *          -- 如果对象的某个属性也是引用数据类型，那么如果该属性也要序列化的话，也要实现Serializable 接口。
 *          -- 该类的所有属性必须是可序列化的。如果有一个属性不需要可序列化的，则该属性必须注明是瞬态的，使用 transient 关键字修饰。
 *          -- 静态（static）变量的值不会序列化。因为静态变量的值不属于某个对象。
 *
 *      - 如果有多个对象需要序列化，则可以将对象放到集合中，再序列化集合对象即可。
 *
 * 5、反序列化失败的问题
 *      - 存在的问题
 *          -- JVM可以反序列化对象，但它必须找到该对象所属类的class文件。如果找不到该类的class文件，则抛出一个 ClassNotFoundException 异常。
 *          -- 当JVM反序列化对象时，能找到class文件，但是class文件在序列化对象之后发生了修改，那么反序列化操作也会失败，抛出一个InvalidClassException异常。
 *              发生这个异常的原因如下：
 *              > 该类的序列版本号与从流中读取的类描述符的版本号不匹配。
 *              > 该类包含未知数据类型。
 *
 *      - 解决方法：
 *          -- Serializable 接口给需要序列化的类，提供了一个序列版本号：serialVersionUID 。凡是实现 Serializable 接口的类都应该有一个表示序列化版本
 *              标识符的静态变量：static final long serialVersionUID = 234242343243L; //它的值由程序员随意指定即可
 *              > serialVersionUID 用来表明类的不同版本间的兼容性。简单来说，Java的序列化机制是通过在运行时判断类的 serialVersionUID 来验证版本一致性的。
 *              在进行反序列化时， JVM 会把传来的字节流中的 serialVersionUID 与本地相应实体类的serialVersionUID 进行比较，如果相同就认为是一致的，可以进行
 *              反序列化，否则就会出现序列化版本不一致的异常(InvalidCastException)。
 *              > 如果类没有显示定义这个静态常量，它的值是 Java 运行时环境根据类的内部细节自动生成的。若类的实例变量做了修改， serialVersionUID 可能发生变化。
 *              因此，建议显式声明。
 *              > 如果声明了 serialVersionUID，即使在序列化完成之后修改了类导致类重新编译，则原来的数据也能正常反序列化，只是新增的字段值是默认值而已。
 *
 * 6、面试题：谈谈你对 java.io.Serializable 接口的理解，我们知道它用于序列化，是空方法接口，还有其它认识吗？
 *      答：
 *          -- 实现了 Serializable 接口的对象，可将它们转换成一系列字节，并可在以后完全恢复回原来的样子。这一过程亦可通过网络进行。这意味着序列化机制
 *      能自动补偿操作系统间的差异。换句话说，可以先在 Windows 机器上创建一个对象，对其序列化，然后通过网络发给一台 Unix 机器，然后在那里准确无误地重新“装配”。
 *      不必关心数据在不同机器上如何表示，也不必关心字节的顺序或者其他任何细节。
 *          -- 由于大部分作为参数的类如 String、 Integer 等都实现了 java.io.Serializable的接口，也可以利用多态的性质，作为参数使接口更灵活。
 *          -- 可序列化的子类也可以序列化。
 *
 * @ClassName: G_ObjectStreamTest.java
 * @Author: anpeng
 * @Date: 2024/3/22 16:56
 */
@SuppressWarnings("all")
public class G_SerializableStreamTest {
    @Test
    public void testSerializeSaveData() throws IOException {
        String name = "anpeng";
        char gender = '男';
        int age = 28;
        boolean relive = true;
        int salary = 50000;

        //序列化数据--保存基本类型数据到文件中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file/dir/anpeng.dat"));
        oos.writeUTF(name);
        oos.writeChar(gender);
        oos.writeInt(age);
        oos.writeBoolean(relive);
        oos.writeInt(salary);
        oos.close();
    }

    @Test
    public void testSerializeReloadData() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file/dir/anpeng.dat"));
        String name = ois.readUTF();
        char gender = ois.readChar();
        int age = ois.readInt();
        boolean relive = ois.readBoolean();
        int salary = ois.readInt();
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("age = " + age);
        System.out.println("relive = " + relive);
        System.out.println("salary = " + salary);
        ois.close();
    }

    @Test
    public void testSerializeSaveObject() throws IOException{
        Employee.setCompany("华为");
        Employee employee = new Employee("安鹏", "贵州省思南县", 28);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file/dir/employee.dat"));
        oos.writeObject(employee);
        oos.close();
        System.out.println("Serialized data is saved");//姓名，地址被序列化，静态变量-“公司” 和 暂态变量-“年龄”不会被序列化。
    }

    @Test
    public void testSerializeReloadObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file/dir/employee.dat"));//新文件替换旧文件。
        Employee employee = (Employee) ois.readObject();
        ois.close();
        System.out.println("employee = " + employee);
    }

    @Test
    public void testSerializeSaveObjectList() throws IOException {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("anpeng","贵州省思南县",28));
        employees.add(new Employee("huli","云南省镇雄县", 25));
        employees.add(new Employee("xiechao","贵州省思南县",26));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file/dir/employees.dat"));
        oos.writeObject(employees);
        oos.close();
    }

    @Test
    public void testSerializeReloadObjectList() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file/dir/employees.dat"));
        ArrayList<Employee> employees = (ArrayList<Employee>) ois.readObject();
        ois.close();
        System.out.println("employees = " + employees);
    }

}
