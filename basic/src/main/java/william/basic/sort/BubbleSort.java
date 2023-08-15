package william.basic.sort;

import william.common.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/8/11 9:10 AM
 * @description: 冒泡排序
 * <p>算法思想:每趟在[0,end]范围内,对相邻元素两两比较,将较大的元素交换到后面,最后使得end位置的元素为[0,end]范围内的最大值</p>
 */
public class BubbleSort {
    
    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printStandardSortResult(arr);
        
        bubbleSort(arr);
        System.out.println("自行排序后数组:");
        System.out.println(Arrays.toString(arr));
    }
    
    private static void bubbleSort(int[] arr) {
        //边界条件校验
        if (arr == null || arr.length < 2) {
            return;
        }
        
        //每趟在[0,end]范围内,对相邻元素两两比较,将较大的交换到后面,最后使得end位置的元素为[0,end]范围内的最大值
        int N = arr.length;
        for (int end = N - 1; end >= 1; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    ArrayUtils.swap(arr, i, i + 1);
                }
            }
        }
    }
}
