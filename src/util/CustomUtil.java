package util;

/**
 * @ClassName: Util.java
 * @Author: anpeng
 * @Date: 2023/3/23 21:28
 */
public class CustomUtil {
    /**
     * @Title: getType
     * @Description: 获取基本数据的类型
     * @Author: anpeng
     * @DateTime: 2023/3/29 16:40
     * @Param obj
     * @Return java.lang.String
     * @Throws
     */
    public static String getType(Object obj){
        return obj.getClass().toString();
    }
}
