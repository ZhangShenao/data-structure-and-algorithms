package william.basic.advancedsort.merge;

import william.common.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/8/30 9:21 AM
 * @description: 归并排序——迭代(非递归)实现
 */
public class MergeSortIteration {
    
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
        int LEN = arr.length;
        
        //借助一个步长step,初始值为1,每次*2
        int step = 1;
        
        while (step < LEN && step > 0) { //终止条件:step=数组长度,或step值过大溢出
            //在每个步长周期,从左至右依次找到左、右两个数组,长度均为step
            int L = 0;
            while (L < LEN) {
                if (LEN - L <= step) { //只有左边数组,无序merge,直接返回
                    break;
                }
                int M = L + step - 1;
                
                int R;
                if (LEN - M - 1 >= step) {
                    R = M + step;
                } else {
                    R = LEN - 1;
                }
                
                //左边数组范围[L,M],右边数组范围[M+1,R]。将两个有序数组合并成一个有序数组
                merge(arr, L, M, R);
                
                //继续处理下一个区间
                if (R >= LEN - 1) {
                    break;
                }
                L = R + 1;
                
            }
            
            step = step << 1;
        }
        
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
