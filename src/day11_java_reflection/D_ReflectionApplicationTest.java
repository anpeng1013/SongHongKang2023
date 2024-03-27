package day11_java_reflection;

import java_bean.day02.Column;
import java_bean.day02.Person;
import java_bean.day02.Student1;
import java_bean.day02.Table;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * 反射的基本应用
 *
 * 1、创建运行时类的对象
 *      - 反射机制应用最多的地方。创建运行时类的对象有两种方式：
 *          -- 方式1：直接调用 Class 对象的 newInstance()方法。（已弃用）
 *              > 1）获取该类型的 Class 对象， 2）调用 Class 对象的 newInstance()方法创建对象
 *              > 要求：1）类必须有一个公共的无参数的构造器。 2）类的构造器的访问权限需要足够。
 *          -- 方式2：通过获取构造器对象来进行实例化。
 *              1）通过 Class 类的 getDeclaredConstructor(Class … parameterTypes)取得本类的指定形参类型的构造器
 *              2）向构造器的形参中传递一个对象数组进去，里面包含了构造器中所需的各个参数。
 *              3）通过 Constructor 实例化对象。如果构造器的权限修饰符修饰的范围不可见，也可以调用 setAccessible(true)
 *
 * 2、获取运行时类的完整结构
 *      - 可以获取：包、修饰符、类型名、父类（包括泛型父类）、父接口（包括泛型父接口）、成员（属性、构造器、方法）、注解（类上的、方法上的、属性上的）。
 *
 *      -相关API
 *          -- public Class<?>[] getInterfaces()   实现的全部接口
 *          -- public Class<? Super T> getSuperclass()   所继承的父类
 *          -- public Constructor<T>[] getConstructors()  此Class对象所表示的类的所有 public 构造方法
 *          -- public Constructor<T>[] getDeclaredConstructors() 此 Class 对象表示的类声明的所有构造方法（包括私有构造器）
 *          -- Constructor类中：
 *              > public int getModifiers()  取得该构造器的修饰符
 *              > public String getName()    取得该构造器的方法名称
 *              > public Class<?>[] getParameterTypes()   取得该构造器的参数类型
 *          -- public Method[] getDeclaredMethods()       返回此 Class 对象所表示的类或接口的全部方法（包括私有方法）
 *          -- public Method[] getMethods()               返回此 Class 对象所表示的类或接口的 public 的方法
 *          -- Method类中：
 *              > public Class<?> getReturnType()         取得全部的返回值
 *              > public Class<?>[] getParameterTypes()   取得全部的参数
 *              > public int getModifiers()               取得修饰符
 *              > public Class<?>[] getExceptionTypes()   取得异常信息
 *          -- public Field[] getFields()                 返回此 Class 对象所表示的类或接口的 public 的 Field。
 *          -- public Field[] getDeclaredFields()         返回此 Class 对象所表示的类或接口的全部 Field（包括私有属性）
 *          -- Field类中
 *              > public int getModifiers()               以整数形式返回此 Field 的修饰符
 *              > public Class<?> getType()               得到 Field 的属性类型
 *              > public String getName()                 返回 Field 的名称。
 *          -- Annotation 相关
 *              > getAnnotation(Class<T> annotationClass)
 *              > getDeclaredAnnotations()
 *          -- 泛型相关
 *              > Type getGenericSuperclass()             获取父类泛型类型：
 *              > getActualTypeArguments()                取实际的泛型类型参数数组
 *          -- Package getPackage()                       返回类所在的包
 *          -- public Class<?>[] getClasses()          返回所有公共内部类和内部接口。包括从超类继承的公共类和接口成员以及该类声明的公共类和接口成员。
 *          -- public Class<?>[] getDeclaredClasses()  返回 Class 对象的一个数组，这些对象为此 Class 对象所表示的类的成员的所有类和接口。
 *              包括该类所声明的公共、保护、默认（包）访问及私有类和接口，但不包括继承的类和接口
 *          -- public Class<?> getDeclaringClass()    如果此Class对象所表示的类或接口是一个内部类或内部接口，则返回它的外部类或外部接口。
 *          -- Class<?> getEnclosingClass() ：        返回某个内部类的外部类
 *
 * 3、调用运行时类的指定结构
 *      - 调用指定的属性：在反射机制中，可以直接通过Field类操作类中的属性，通过Field类提供的set()和 get()方法就可以完成设置和取得属性内容的操作。
 *          （1）获取该类型的 Class 对象           Class clazz = Class.forName("包.类名");
 *          （2）获取属性对象                     Field field = clazz.getDeclaredField("属性名");
 *          （3）如果属性的权限修饰符不是 public，那么需要设置属性可访问。    field.setAccessible(true);
 *          （4）创建实例对象：如果操作的是非静态属性，需要创建实例对象        Object obj = clazz.newInstance(); //有公共的无参构造
 *              Object obj = 构造器对象.newInstance(实参...); //通过特定构造器对象创建实例对象
 *          （5）设置指定对象 obj 上此 Field 的属性内容。   field.set(obj,"属性值");  如果操作静态变量，那么实例对象可以省略，用 null 表示
 *          （6）取得指定对象 obj 上此 Field 的属性内容。   Object value = field.get(obj);  如果操作静态变量，那么实例对象可以省略，用null表示
 *
 *       - 调用指定的方法
 *          （1）获取该类型的对象             Class clazz = Class.forName("包.类名");
 *          （2）获取方法的对象               Method method = clazz.getDeclaredMethod("方法名",方法的形参类型列表);
 *          （3）创建实例对象                 Object obj = clazz.newInstance();
 *          （4）调用方法                    Object result = method.invoke(obj, 方法的实参值列表);
 *              -- 如果方法的权限修饰符修饰的范围不可见，也可以调用setAccessible(true)。
 *              -- 如果方法是静态方法，实例对象也可以省略，用 null 代替。
 *
 * 4、读取注解信息
 *      - 声明自定义注解（在java_bean.day02中自自定义了注解Table 和Column）
 *          -- 自定义注解可以通过四个元注解@Retention，@Target，@Inherited，@Documented，分别说明它的声明周期，使用位置，是否被继承，
 *              是否被生成到 API 文档中。
 *          -- Annotation 的成员在 Annotation 定义中以无参数有返回值的抽象方法的形式来声明，我们又称为配置参数。返回值类型只能是八种基本数据类型、
 *              String 类型、 Class类型、 enum 类型、 Annotation 类型、以上所有类型的数组。
 *          -- 可以使用 default 关键字为抽象方法指定默认返回值。
 *          -- 如果定义的注解含有抽象方法，那么使用时必须指定返回值，除非它有默认值。格式是“方法名 = 返回值”，如果只有一个抽象方法需要赋值，
 *              且方法名为 value，可以省略“value=”，所以如果注解只有一个抽象方法成员，建议使用方法名 value。
 *
 *      - 使用自定义注解
 *          -- java_bean.day02.Student1.java中使用了Table和Column注解
 *
 *      - 读取和处理自定义注解
 *          -- 自定义注解必须配上注解的信息处理流程才有意义。
 *          -- 我们自己定义的注解，只能使用反射的代码读取。所以自定义注解的声明周期必须是 RetentionPolicy.RUNTIME。
 *
 * @ClassName: D_ReflectionApplicationTest.java
 * @Description:
 * @Author: anpeng
 * @Date: 2024/3/25 17:30
 */
@SuppressWarnings("all")
public class D_ReflectionApplicationTest {
    @Test
    public void testCreateObject() throws Exception {
        //方式1：直接调用 Class 对象的 newInstance()方法。（已弃用）
        Class<?> clazz = Class.forName("java_bean.day02.Animal");
        Object animal = clazz.newInstance();
        System.out.println("animal = " + animal);//java_bean.day02.Animal@64b8f8f4

        //方式2：通过获取构造器对象来进行实例化。
        Class<?> clazz2 = Class.forName("java_bean.day02.Student");
        Constructor<?> constructor = clazz2.getDeclaredConstructor(String.class);
        Object obj = constructor.newInstance("中山大学");
        System.out.println("obj = " + obj);
    }

    @Test
    public void testField() throws ClassNotFoundException {
        Class clazz = Class.forName("java_bean.day02.Person");
        Field[] declaredField = clazz.getDeclaredFields();
        for (Field field : declaredField) {
            System.out.println(field);
        }
    }

    @Test
    public void testModifier() throws ClassNotFoundException {
        Class clazz = Class.forName("java_bean.day02.Person");
        Field[] declaredField = clazz.getDeclaredFields();
        for (Field field : declaredField) {
            /* 用十六进制整数表示的权限修饰符
            public        = 0x00000001;         1       1
            private       = 0x00000002;         2      10
            protected     = 0x00000004;         4     100
            static        = 0x00000008;         8    1000
            final         = 0x00000010;        16   10000
            ...
            设计理论：就是用二进制的某一位是 1，来代表一种修饰符，整个二进制中只有一位是 1，其余都是 0。
             */
            //1、属性的修饰符
            int modifier = field.getModifiers();
            System.out.print(Modifier.toString(modifier) + "\t");
            //2、属性的类型
            Class type = field.getType();
            System.out.print(type.getName() + "\t");
            //3.属性的变量名
            String fName = field.getName();
            System.out.print(fName);

            System.out.println();
        }
    }

    @Test
    public void testMethod(){
        Class clazz = Person.class;
        Method[] declarations = clazz.getDeclaredMethods();//返回该类的所有方法，包括私有方法。
        for (Method method : declarations) {
            System.out.println(method);
        }
    }

    @Test
    public void testReturn(){
        Class clazz = Person.class;
        Method[] declarations = clazz.getDeclaredMethods();
        for (Method method : declarations){
            // 1、权限修饰符
            System.out.print(Modifier.toString(method.getModifiers()) + "\t");
            // 2.返回值类型
            System.out.print(method.getReturnType().getName() + "\t");
            // 3.方法名
            System.out.print(method.getName());
            System.out.print("(");
            // 4.形参列表
            Class[] parameterTypes = method.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (i == parameterTypes.length - 1) {
                        System.out.print(parameterTypes[i].getName() + " args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + " args_" + i + ",");
                }
            }
            System.out.print(")\n");
        }
    }

    @Test
    public void testInnerClass(){
        Class<?> clazz = Map.class;
        Class<?>[] inners = clazz.getDeclaredClasses();//获取内部类
        for (Class<?> inner : inners) {
            System.out.println(inner);
        }
        Class<?> ec = Map.Entry.class;
        Class<?> outer = ec.getDeclaringClass();//获取外部类
        System.out.println(outer);
    }

    @Test
    public void testInvokeField() throws Exception {
        //1、获取 Student 的 Class 对象
        Class clazz = Class.forName("java_bean.day11.Student");
        //2、获取属性对象，例如： id 属性
        Field idField = clazz.getDeclaredField("id");
        //3、如果 id 是私有的, 则在当前类中不可访问 access 的，我们需要做如下操作
        idField.setAccessible(true);
        //4、创建实例对象，即，创建 Student 对象
        Object stu = clazz.newInstance();
        //5、获取属性值
        /*
         * 以前： int 变量= 学生对象.getId()
         * 现在： Object id 属性对象.get(学生对象)
         */
        Object value = idField.get(stu);
        System.out.println("id = "+ value);
        //6、设置属性值
        /*
        以前：学生对象.setId(值)
        现在：id 属性对象.set(学生对象,值)
         */
        idField.set(stu, 2);
        value = idField.get(stu);
        System.out.println("id = "+ value);
    }

    @Test
    public void testInvokeMethod() throws Exception {
        // 1、 获取 Student 的 Class 对象
        Class<?> clazz = Class.forName("java_bean.day11.Student");
        //2、获取方法对象 -- 在一个类中，唯一定位到一个方法，需要：（1）方法名（2）形参列表，因为方法可能重载
        Method setNameMethod = clazz.getDeclaredMethod("setName", String.class);
        //3、创建实例对象
        Object stu = clazz.newInstance();
        //4、调用方法
        /*
         * 以前：学生对象.setName(值)
         * 现在：方法对象.invoke(学生对象，值)
         */
        Object setNameMethodReturnValue = setNameMethod.invoke(stu, "张三");
        System.out.println("stu = " + stu);
        //setName 方法返回值类型 void，没有返回值，所以 setNameMethodReturnValue 为 null
        System.out.println("setNameMethodReturnValue = " + setNameMethodReturnValue);
        Method getNameMethod = clazz.getDeclaredMethod("getName");
        Object getNameMethodReturnValue = getNameMethod.invoke(stu);
        //getName 方法返回值类型 String，有返回值， getNameMethod.invoke的返回值就是 getName 方法的返回值
        System.out.println("getNameMethodReturnValue = " + getNameMethodReturnValue);//张三
    }

    @Test
    public void testGetAnnotation(){
        Class<Student1> student1Class = Student1.class;
        Table tableAnnotation = (Table) student1Class.getAnnotation(Table.class);
        String tableName = "";
        if(tableAnnotation != null){
            tableName = tableAnnotation.value();
        }
        Field[] declaredFields = student1Class.getDeclaredFields();
        String[] columns = new String[declaredFields.length];
        int index = 0;
        for (Field declaredField : declaredFields) {
            Column column = declaredField.getAnnotation(Column.class);
            if(column!= null) {
                columns[index++] = column.columnName();
            }
        }
        String sql = "select ";
        for (int i = 0; i < index; i++) {
            sql += columns[i];
            if(i < index-1){
                sql += ",";
            }
        }
        sql += " from " + tableName;
        System.out.println("sql = " + sql);
    }

}
