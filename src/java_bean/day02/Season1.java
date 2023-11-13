package java_bean.day02;

/**
 * @ClassName: Season1.java
 * @Author: anpeng
 * @Date: 2023/11/4 15:17
 */
public enum Season1 implements Info{
    //1. 创建枚举类中的常量对象，声明在 enum 枚举类的首位
    SPRING("春天","春暖花开"){//系统会自动添加public static final修饰
        public void show(){//若需要每个枚举值在调用实现的接口方法呈现出不同的行为方式，则可以让每个枚举值分别来实现该方法
            System.out.println("春天在哪里？ ");
        }
    },
    SUMMER("夏天","夏日炎炎"){
        public void show(){
            System.out.println("宁静的夏天");
        }
    },
    AUTUMN("秋天","秋高气爽"){
        public void show(){
            System.out.println("秋天是用来分手的季节");
        }
    },
    WINTER("冬天","白雪皑皑"){
        public void show(){
            System.out.println("2002 年的第一场雪");
        }
    };

    @Override
    public void info() {//每个枚举值在调用实现的接口方法呈现相同的行为方式，则只要统一实现该方法即可
        System.out.println("每个枚举值在调用实现的接口方法呈现相同的行为方式，则只要统一实现该方法即可");
    }

    //2. 声明每个对象拥有的属性：用private final 修饰
    private final String SEASON_NAME;
    private final String SEASON_DESC;
    //3. 私有化类的构造器
    private Season1(String seasonName,String seasonDesc){
        this.SEASON_NAME = seasonName;
        this.SEASON_DESC = seasonDesc;
    }
    public String getSEASON_NAME() {
        return SEASON_NAME;
    }
    public String getSEASON_DESC() {
        return SEASON_DESC;
    }
}
