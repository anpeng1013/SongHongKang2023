package java_bean.day02;

/**
 * @ClassName: MemberOuter.java
 * @Author: anpeng
 * @Date: 2023/11/3 18:11
 */
public class MemberOuter {
    private static final String a = "成员外部类的静态变量 a";
    private static final String b = "成员外部类的静态变量 b";
    private final String c = "成员外部类对象的非静态变量 c";
    private final String d = "成员外部类对象的非静态变量 d";

    public static class StaticMemberInner{
        private static final String a ="静态内部类的静态变量 a";
        private final String c = "静态内部类对象的非静态变量 c";
        public static void inMethod(){
            System.out.println("static inMethod-------");
            System.out.println("Inner.a = " + a);//内外同名时，采取就近原则
            System.out.println("Outer.a = " + MemberOuter.a);//内外同名时，外部静态成员直接类名调用。
            System.out.println("Outer.b = " + b);//静态内部类可以直接调用外部静态成员
        }
        public void inFunc(){
            System.out.println("NoStatic inFunc---------");
            System.out.println("Outer.a = " + MemberOuter.a);//内外同名时，外部静态成员直接类名调用。
            System.out.println("Inner.a = " + a);//内外同名时，采取就近原则
            System.out.println("Outer.b = " + b);//静态内部类的非静态方法可以访问外部的静态变量。
            System.out.println("Inner.c = " + c);//静态内部类的非静态方法可以访问自己内部的非静态变量。
            //System.out.println("d = " + d);整个内部类是静态的，因此内部类里面不能访问外部类的非静态成员。
        }
    }

    public class NoStaticMemberInner{
        private final String a = "非静态内部类对象的非静态 a";
        private final String c = "非静态内部类对象的非静态 c";
        public void inFunc(){
            System.out.println("NoStaticInner.inFun-----------");
            System.out.println("Outer.a = " + MemberOuter.a);//外部静态成员直接类名调用。
            System.out.println("Inner.a = " + a);//内外同名时，采取就近原则
            System.out.println("Outer.b = " + b);//非静态内部类的非静态方法可以访问外部的静态变量。
            System.out.println("Outer.c = " + MemberOuter.this.c);//内外同名时，外部非静态成员直接类名.this.成员名。
            System.out.println("Inner.c = " + c);//内外同名时，采取就近原则
            System.out.println("Outer.d = " + d);//非静态成员内部类可以调用外部的非静态成员
        }
    }

    public NoStaticMemberInner getNoStaticMemberInner(){
        return new NoStaticMemberInner();
    }
}
