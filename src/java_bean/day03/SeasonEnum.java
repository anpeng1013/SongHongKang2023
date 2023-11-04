package java_bean.day03;

/**
 * @ClassName: SeasonEnum.java
 * @Author: anpeng
 * @Date: 2023/11/4 10:53
 */
public enum SeasonEnum {
    SPRING("春天","春风又绿江南岸"),//因为是常量对象列表，故除了最后一项，其余用逗号隔开。
    SUMMER("夏天","映日荷花别样红"),//系统会自动添加public static final修饰
    AUTUMN("秋天","秋水共长天一色"),
    //类似于--public static final Season WINTER = new Season("冬天", "窗含西岭千秋雪");
    WINTER("冬天","窗含西岭千秋雪");

    private final String seasonName;
    private final String seasonDesc;

    private SeasonEnum(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }
}
