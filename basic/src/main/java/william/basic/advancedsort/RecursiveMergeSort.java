package william.basic.advancedsort;

import william.common.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/8/30 9:03 AM
 * @description: 归并排序——递归实现
 */
public class RecursiveMergeSort {
    
    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(20, -100, 100);
        ArrayUtils.printStandardSortResult(arr);
        mergeSort(arr);
        System.out.println("归并排序后数组:");
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * 对数组arr进行归并排序
     */
    private static void mergeSort(int[] arr) {
        //边界条件校验
        if (arr == null || arr.length == 0) {
            return;
        }
        
        //递归进行
        recursiveMergeSort(arr, 0, arr.length - 1);
    }
    
    /**
     * 递归实现,对数组arr[L,R]范围进行递归排序
     */
    private static void recursiveMergeSort(int[] arr, int L, int R) {
        //递归终止条件
        if (L >= R) {
            return;
        }
        
        //将数组平均划分为左、右两个部分
        int M = L + ((R - L) >> 1); //避免溢出
        
        //先保证左、右两部分数组有序
        recursiveMergeSort(arr, L, M);
        recursiveMergeSort(arr, M + 1, R);
        
        //之后将两个有序数组合并为一个整体有序数组,保证arr[L,R]范围内有序
        merge(arr, L, M, R);
    }
    
    /**
     * 将arr[L,M]和arr[M+1,R]两个有序数组,合并为一个整体有序数组
     */
    private static void merge(int[] arr, int L, int M, int R) {
        int LEN = R - L + 1;
        int[] sorted = new int[LEN];
        int l = L;
        int r = M + 1;
        int i = 0;
        
        //同时遍历两个有序数组,将较小的元素放入新数组
        while (l <= M && r <= R) {
            if (arr[l] <= arr[r]) {
                sorted[i++] = arr[l++];
            } else {
                sorted[i++] = arr[r++];
            }
        }
        
        //处理其中一个数组已经遍历完成的情况
        while (l <= M) {
            sorted[i++] = arr[l++];
        }
        while (r <= R) {
            sorted[i++] = arr[r++];
        }
        
        //将新生成的有序数组拷贝回原数组
        for (int j = 0; j < LEN; j++) {
            arr[L + j] = sorted[j];
        }
    }
}
