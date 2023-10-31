package day03_java_object_oriented;

import java_bean.day03.Person;
import java_bean.day03.Student;
import java_bean.day03.Teacher;

/**
 * 对象的多态性
 *      多态性，是面向对象中最重要的概念，在Java中的体现为对象(成员方法)的多态性：父类的引用指向子类的对象。
 *      格式：父类类型 变量名 = 子类对象;   (父类类型是指子类继承的父类或者实现的接口类型)
 *      在java中，子类的对象可以替代父类的对象使用。所以，一个引用类型的变量可能指向（引用）不同类型的对象。
 *
 * 多态的理解
 *      Java引用变量有两个类型：编译时类型和运行时类型。编译时类型由声明该变量时使用的类型决定，运行时类型由实际赋给该变量的对象决定。
 *      简称： 编译时，看左边；运行时，看右边。
 *       * 若编译时类型和运行时类型不一致，就出现了对象的多态性（polymorphism）
 *       * 多态情况下，“看左边”：看的是父类的引用, 即声明的父类引用变量指向子类对象时，该变量不能调用子类特有的属性和方法。
 *                   “看右边”：看的是子类的对象,即声明的父类引用变量指向不同子类对象时，实际执行的子类重写方法也不同
 *       * 多态的使用前提：①类的继承关系  ②方法的重写。
 *
 * 为什么需要多态性
 *      开发中，有时我们在设计一个数组、或一个成员变量、或一个方法的形参、返回值类型时，无法确定它具体的类型，只能确定它是某个系列的类型。
 *
 * 多态的好处和弊端
 *      好处：变量引用的子类对象不同，执行的重写方法就不同，实现动态绑定。代码编写更灵活，功能更强大，可维护性和扩展性更好。
 *      弊端：一个引用类型变量如果声明为父类的类型，但实际引用的是子类对象，那么该变量就不能再访问子类中添加的特有属性和方法。
 *      开发中：使用父类做方法的形参，是多态使用最多的场景。即使增加了新的子类，方法也无需改变，提高了扩展性，符合开闭原则。
 *      开闭原则(OCP)：对扩展开放，对修改关闭。即应该在不修改现有代码的基础上引入新功能。
 *
 * 虚方法调用（virtual method invocation）
 *      在java中，虚方法是指在编译阶段不能确定方法的调用入口地址，在运行阶段才能确定的方法，即可能被重写的方法。
 *          子类中定义了与父类同名同参数的方法（重写），在多态情况下，将此时父类的方法称为虚方法，父类根据赋给它的不同子类对象，
 *          动态地调用属于子类的该方法。这样的方法调用在编译期是无法确定的。（故，多态是运行时行为！！！）
 *      静态链接：当一个字节码文件被装载进JVM内部时，如果被调用的目标方法在编译期可知，且运行期保持不变时。这种情况下将调用方法的
 *          符号引用转换为直接引用的过程称之为静态链接。那么调用这样的方法，就称为非虚方法调用。比如调用静态方法、私有方法、final方法、
 *          父类构造器、本类重载构造器等。
 *      动态链接：如果被调用的方法在编译期无法被确定下来，也就是说，只能够在程序运行期将调用方法的符号引用转换为直接引用，由于这种引用的
 *          转换过程具备动态性，因此也就被称之为动态链接。调用这样的方法，就称为虚方法调用。比如调用重写的方法（针对父类）、
 *          实现的方法（针对接口）。
 *
 * 成员变量没有多态性
 *      因为父类中的成员方法可以被子类完全覆盖，而实例变量不能覆盖父类的实例变量。
 *
 * 向上转型或向下转型
 *      首先，一个对象在new的时候创建是哪个类型的对象，它从头至尾都不会变。即这个对象的运行时类型，本质的类型用于不会变。
 *      但是，把这个对象赋值给不同类型的变量时，这些变量的编译时类型却不同。
 *      为什么要类型转换：
 *          因为多态，就一定会有把子类对象赋值给父类变量的时候，这个时候，在编译期间，就会出现类型转换的现象。但是，使用父类变量接收了
 *          子类对象之后，就不能调用子类拥有，而父类没有的方法了。 这也是多态给我们带来的一点"小麻烦"。所以，想要调用子类特有的方法，
 *          必须做类型转换，使得编译通过。
 *      向上转型：当左边的变量的类型（父类） > 右边对象/变量的类型（子类），我们就称为向上转型。
 *          * 此时，编译时按照左边变量的类型处理，就只能调用父类中有的变量和方法，不能调用子类特有的变量和方法了。
 *          * 但是， 运行时，仍然是对象本身的类型，所以执行的方法是子类重写的方法体。
 *          * 此时，一定是安全的，而且也是自动完成的。
 *      向下转型：当左边的变量的类型（子类） < 右边对象/变量的编译时类型（父类），我们就称为向下转型
 *          * 此时，编译时按照左边变量的类型处理，就可以调用子类特有的变量和方法了
 *          * 但是，运行时，仍然是对象本身的类型。
 *          * 不是所有通过编译的向下转型都是正确的，可能发生ClassCastException，为了安全，可以通过isInstanceof关键字判断。
 *
 * instanceof关键字：
 *      为了避免ClassCastException的发生， Java提供了instanceof关键字，给引用变量做类型的校验。格式如下：
 *          对象object instanceof 数据类型A a
 *          解释：如果对象object的类型是数据类型A或A的父类，则将对象object转换为类型为A的对象a。
 *
 * @ClassName: F_Polymorphic.java
 * @Author: anpeng
 * @Date: 2023/10/28 17:00
 */
public class F_Polymorphic {
    public static void main(String[] args) {
        //向上转型, 父类引用指向子类对象。
        Person person = new Student("sun yat-sen university");
        //person.setSchool("s"); 属性是在编译时确定的，编译时person为Person类型，没有school成员变量，因而编译错误。只能通过
        //Student相应的构造器来对其特有的属性进行初始化，如上行代码所示。
        person.setAge(26);
        person.setName("anpeng");
        //person.study(); //编译时“看左边”： 声明的父类引用变量指向子类对象时，不能调用子类特有方法。
        person.speak(); //运行时“看右边”：声明的父类引用变量指向子类对象时，只能调用子类继承于父类或重写父类的方法。
        System.out.println(person.getClass()); //class java_bean.day03.Student

        //向下转型
        Person[] people = new Person[2];
        people[0] = new Student("Guizhou university");
        people[1] = new Teacher();
        for (int i = 0; i < people.length; i++) {
            if(people[i] instanceof Student student) {
                System.out.println(student.getSchool());//向下转型后可以调用子类特有的实例变量
                student.study();//向下转型后可以调用子类特有的成员方法。
            }else if(people[i] instanceof Teacher teacher){
                teacher.teach();//向下转型后可以调用子类特有的成员方法。
            }
        }

        //常见错误
        Teacher teacher1 = (Teacher) person;//编译能通过，因为从语法检查来说，person的编译类型是Person，Teacher是Person的子类
        //但是，运行报ClassCastException，因为person变量的运行时类型是Student，Student和Teacher之间是没有继承关系的。
        teacher1.teach();
    }
}
