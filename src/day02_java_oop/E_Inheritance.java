package day02_java_oop;

import java_bean.day02.Cat;
import java_bean.day02.Student;

/**
 * 继承：描述事物之间的所属关系，这种关系是 is-a 的关系。可见，父类更通用，子类更具体。
 *      也有延续（下一代延续上一代的基因、财富）和扩展（下一代又有所不同）的意思。
 *      但不能仅为了获得其他类中的某一个功能而去继承！
 *
 * 好处：
 *     继承可以减少代码冗余，提高代码的复用性。
 *     继承有利于功能的扩展。
 *     继承可以让类之间产生is-a的关系，为多态的使用提供前提。
 *
 * 语法：
 *      [权限修饰符] class 类B extends 类A{
 *          属性声明;
 *          方法声明;
 *      }
 *      * 其中，类B，称为子类(SubClass)、派生类(derived class)
 *      * 类A，称为父类、超类(SuperClass)、基类(base class)
 *      * java中默认所有类都继承于Object。
 *
 * 细节：
 *      1、子类会继承父类所有的实例变量和实例方法。
 *          * 当子类对象被创建时，在堆中给对象申请内存，要看子类和父类都声明了什么实例变量，这些实例变量都要分配内存。
 *          * 当子类对象调用方法时，编译器会先在子类模板中找，看该类是否有这个方法，如果没有找到，会看它的父类甚至父类的父类是否
 *            声明了这个方法，遵循从下往上找的顺序，找到了就停止，一直到根父类都没有找到，就会报编译错误。
 *      2、子类不能直接访问父类中私有的（private）的成员变量和方法，可通过继承的get/set方法进行访问。
 *      3、在java中，继承的关键字是extends，即子类不是父类的子集，而是对父类的扩展。
 *      4、java支持多层继承（多重继承体系。即，C继承B，B继承A，从而C也继承了A）
 *      5、java中的类，支持单继承多实现（即，一个子类只能有一个直接父类，但能同时实现多个接口）
 *      6、java中的接口，支持多继承（即，一个子接口可以同时有多个直接父接口）
 *
 * 在子父类中，成员的特点体现：
 *  1、成员变量。
 *     当本类的成员变量和局部变量同名时用this区分。
 *     当子父类中的成员变量同名时 用super区分父类。
 *     this和super的用法很相似:
 *     this:代表一个本类对象的引用
 *     super：代表一个本类中的父类空间的引用，该父类空间中
 *            存储了继承于父类的成员变量。
 *
 *  2、成员函数。
 *     函数重写：
 *         当子父类中出现成员函数相同(函数名和参数列表都相同)的情况，会运行子类的函数，如上述细节1中描述，称为重写操作。
 *         这是函数在子父类中的特性。函数的两个特性如下：
 *              1、重载：在同一个类中。函数名相同，参数列表不同。overload
 *              2、重写：子类中对父类同名函数的覆盖，复写。函数名相同，参数列表也相同。override。
 *
 *     重写注意事项：
 *         1、子类方法覆盖父类方法时，子类方法的权限必须大于等于父类的权限。
 *         2、子类方法覆盖父类方法时，子类方法抛出的编译时异常必须小于等于父类抛出的编译时异常，或不抛。
 *         2、父类的私有方法和静态方法不能被重写。
 *         3、子类与父类中同名同参数的方法必须同时声明为非static的(即为重写)，或者同时声明为static的（不是重写）。
 *         因为static方法是属于类的，子类无法覆盖父类的静态方法。
 *
 *
 *     使用重写操作的时机：
 *         当对一个进行子类的扩展时，子类需要保留父类的功能声明，但是要定义子类中该功能的特有内容时，就使用覆盖操作。
 *
 *  3、构造函数。
 *     执行当前子类的构造函数时，首先一定会调用父类构造函数，因为在子类的构造函数中的第一行有一个默认的隐式语句：super();
 *
 *     为什么子类实例化的时候要访问父类中的构造函数呢？
 *         因为子类继承了父类，获取到了父类中的非私有内容（属性），
 *         所以在使用父类内容之前，要先看父类如何对自己内容进行初始化的。
 *         所以子类在构造对象时，必须访问父类中的构造函数。
 *
 *      注意：
 *          * 若父类中没有空参数构造函数，那么子类构造函数必须要用super语句明确要调用的父类中的哪个构造函数，否则会报错。
 *              super语句必须要定义在子类构造函数的第一行。因为父类的初始化工作要先完成。
 *          * 若子类构造函数中如果使用this调用了本类构造函数时，此时super就没有了。
 *              因为super和this都只能定义在第一行，所以只能有一个。但是必须保证子类中会有其他的构造函数会访问父类的构造函数。
 *
 *          开发中常见错误：如果子类构造器中既未显式调用父类或本类的构造器，且父类中又没有空参的构造器，则编译出错。
 *          * 总结：一个子类对象创建时，如果子类构造器第一行是this(形参列表),即嵌套调用子类构造器，那么最后一个嵌套调用的
 *                  this(形参列表)的第一行一定是super(形参列表)，最终一定会先执行父类的构造函数super
 *
 * 代码块：
 *      局部代码块：在函数体内部，用大括号{}包围的代码。
 *          作用：限定局部变量的生命周期。
 *
 *      构造代码块：在函数体外，类的内部，用大括号包围的代码。
 *          作用：创建对象时调用，给对象进行通用的初始化。
 *          * 可以有输出语句。
 *          * 可以对类属性和实例属性进行初始化，即可以调用静态和非静态的属性和方法。
 *          * 若有多个构造代码块，按照从上到下的顺序依次执行。
 *          * 每次创建对象的时候，都会执行一次。且先于构造器执行。
 *
 *      静态代码块：在函数体外，类的内部，在构造代码块前面加static，就是静态代码块。
 *          作用：类加载时调用，给静态成员变量（类属性）进行初始化。
 *          * 可以有输出语句。
 *          * 可以对类（静态）属性进行初始化，不可以对实例属性初始化。即：不可以调用非静态的属性和方法。
 *          * 若有多个静态代码块，按照从上到下的顺序依次执行。
 *          * 静态代码块的执行要优于构造代码块。
 *          * 静态代码块随着类的加载而执行，且只执行一次。
 *
 *  4、类的实例化过程--Cat cat = new Cat();
 *      1、JVM会读取指定路径下的Cat.class文件，欲将此类加载进内存。如果有直接父类的情况下，会先加载Cat的父类Animal进内存，
 *          并执行父类的静态代码块。父类加载完后，再加载子类进内存，并执行子类的静态代码块。
 *      2、在堆内存中开辟空间，给对象的属性[包括继承于父类的属性]分配地址并进行默认初始化。
 *      3、首先会调用当前对象所属类的构造函数。因为进入当前对象的构造函数，第一行会先调用父类中的构造函数，对继承于父类的属性进行初始化。
 *      4、父类的属性按：显示初始化-->构造代码块初始化-->父类构造函数其余代码
 *      5、父类初始化完毕后，再对子类的属性按：显示初始化-->构造代码块-->子类构造函数其余代码
 *      6、子类构造函数执行完毕，将地址值赋给引用变量。
 *
 *      父类静态代码块-->子类静态代码块-->子类构造函数-->子类构造函数第一行首先执行父类构造函数--> 父类显示初始化--> 父类构造代码块
 *      -->父类构造函数其余代码-->子类显示初始化-->子类构造代码块-->子类构造函数其余代码。
 *
 * super关键字：
 *      * super可用于访问父类中定义的属性、方法和构造器
 *      * 尤其当子父类出现同名成员(属性或方法)时,可以用super表明调用的是父类的成员。
 *      * super的追溯不仅限于直接父类，还包括间接父类。
 *      * super和this的用法，this代表本类对象的引用，super代表父类的内存空间的引用。
 *
 *      使用场景：
 *          1、在子类中调用父类被重写的方法。(就近原则)
 *          2、在子类中调用父类中同名的成员属性。(就近原则)
 *          3、子类构造器中调用父类构造器。
 *
 * @ClassName: E_Inheritance.java
 * @Author: anpeng
 * @Date: 2023/4/10 21:51
 */
public class E_Inheritance {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("anpeng"); //Student类的age和name继承自Person
        student.setAge(26);
        student.setSchool("sun yat sen university");

        student.speak(); //测试继承(继承了父类Person的age和name属性)和重写(重写了父类Person的speak方法)
        student.study();

        Cat cat = new Cat(); //测试实例化过程
        cat.setAge(2);
        cat.setKind("cat");
        cat.getInfo();
    }
}
