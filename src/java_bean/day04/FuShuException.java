package java_bean.day04;

/**
 * @ClassName: FuShuException.java
 * @Author: anpeng
 * @Date: 2023/11/8 12:42
 */
@SuppressWarnings("unused")
public class FuShuException extends Exception{//继承Exception时，为编译时异常。继承RuntimeException为运行时异常。
    static final long serialVersionUID = 23423423435L;
    public FuShuException(){}
    public FuShuException(String msg){
        super(msg);//使用父类的异常信息提示反馈方法。
    }
}
