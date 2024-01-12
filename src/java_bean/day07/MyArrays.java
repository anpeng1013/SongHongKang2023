package java_bean.day07;

/**
 * @ClassName: MyArrays.java
 * @Author: anpeng
 * @Date: 2023/12/25 17:01
 */
@SuppressWarnings("all")
public class MyArrays {
    public <T> void sort(T[] arr){
        for (int i = 1; i < arr.length; i++){//冒泡排序
            for (int j = 0; j < arr.length - i; j++){
                if(((Comparable<T>)arr[j]).compareTo(arr[j+1])>0){
                    T temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
