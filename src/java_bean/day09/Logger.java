package java_bean.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: Logger.java
 * @Author: anpeng
 * @Date: 2024/3/23 10:23
 */
public class Logger {
    /**
     * @Title: log
     * @Description: 记录日志的方法
     * @Author: anpeng
     * @DateTime: 2024/3/23 10:25
     * @Param String
     * @Return void
     * @Throws
     */
    public static void log(String message){
        try {
            //指向一个日志文件，尾部添加时用文件对象创建流，自动刷新
            PrintStream out = new PrintStream(new FileOutputStream(new File("file/log.txt"), true), true);
            System.setOut(out);//改变输出方向
            Date nowTime = new Date();//获取当时时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            String strTime = sdf.format(nowTime);
            System.out.println(strTime + ": " + message);//在日志文件中打印，而不是控制台屏幕
        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
    }
}
