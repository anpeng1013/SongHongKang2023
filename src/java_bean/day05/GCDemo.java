package java_bean.day05;

/**
 * @ClassName: GCDemo.java
 * @Author: anpeng
 * @Date: 2023/11/17 17:08
 */
public class GCDemo {
    private int value;
    public GCDemo(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "GCDemo{" + "value=" + value + '}';
    }
    //重写 finalize 方法，让大家看一下它的调用效果
    @Override
    protected void finalize() throws Throwable {
        // 正常重写，这里是编写清理系统内存的代码
        // 这里写输出语句是为了看到 finalize()方法被调用的效果
        System.out.println(this+ "轻轻的我走了，不带走一段代码....");
    }
}
