package java_bean.day04;

/**
 * @ClassName: InnerSingle.java
 * @Author: anpeng
 * @Date: 2023/11/11 17:18
 */
public class InnerSingle {
    private InnerSingle(){}

    public static InnerSingle getInstance() {
        return Inner.INSTANCE;
    }

    @SuppressWarnings("all")
    private static class Inner{
        static final InnerSingle INSTANCE = new InnerSingle();
    }
}
