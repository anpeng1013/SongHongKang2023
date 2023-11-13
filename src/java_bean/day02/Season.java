package java_bean.day02;

/**
 * @ClassName: Season.java
 * @Author: anpeng
 * @Date: 2023/11/4 10:27
 */
public class Season {
    private final String SEASONNAME;//季节名称
    private final String SEASONDESCRIPTION;//季节描述
    private Season(String seasonName, String seasonDescription){
        this.SEASONNAME = seasonName;
        this.SEASONDESCRIPTION = seasonDescription;
    }
    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "白雪皑皑");

    @Override
    public String toString() {
        return "Season{" +
                "SEASONNAME='" + SEASONNAME + '\'' +
                ", SEASONDESCRIPTION='" + SEASONDESCRIPTION + '\'' +
                '}';
    }
}
