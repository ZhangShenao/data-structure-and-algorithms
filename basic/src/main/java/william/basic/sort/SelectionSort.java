package william.basic.sort;

import william.common.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/8/10 8:03 PM
 * @description: 选择排序
 * <p>算法思想：每趟在[start,N-1]范围内找到最小元素,并与start位置的元素进行交换</p>
 */
public class SelectionSort {
    
    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printStandardSortResult(arr);
        
        selectionSort(arr);
        System.out.println("自行排序后数组:");
        System.out.println(Arrays.toString(arr));
    }
    
    private static void selectionSort(int[] arr) {
        //边界条件校验
        if (arr == null || arr.length < 2) {
            return;
        }
        
        int N = arr.length;
        //每趟在[start,N-1]范围内找到最小元素,并与start位置的元素进行交换
        for (int start = 0; start < N - 1; start++) {
            int minIdx = start;
            for (int next = start + 1; next < N; next++) {
                if (arr[next] < arr[minIdx]) {
                    minIdx = next;
                }
            }
            
            ArrayUtils.swap(arr, start, minIdx);
        }
    }
}
