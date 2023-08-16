package william.common.utils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShenao
 * @date 2023/8/10 7:59 PM
 * @description: 数组工具类
 */
public class ArrayUtils {
    
    /**
     * 交换数组arr中i和j位置的元素
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    /**
     * 生成长度为len的数组,元素大小在[min,max]范围内
     */
    public static int[] generateRandomArray(int len, int min, int max) {
        int[] arr = new int[len];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(min, max + 1);
        }
        return arr;
    }
    
    /**
     * 生成长度为len的有序数组,元素大小在[min,max]范围内
     */
    public static int[] generateRandomSortedArray(int len, int min, int max) {
        int[] arr = new int[len];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(min, max + 1);
        }
        Arrays.sort(arr);
        return arr;
    }
    
    /**
     * 打印标准排序结果
     */
    public static void printStandardSortResult(int[] arr) {
        System.out.println("原始数组:");
        System.out.println(Arrays.toString(arr));
        
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        System.out.println("正确排序数组:");
        System.out.println(Arrays.toString(copy));
    }
    
}
