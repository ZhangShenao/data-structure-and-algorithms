package william.basic.basicsort;

import william.common.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/8/11 9:19 AM
 * @description: 插入排序
 * <p>算法思想：假定 [0,end] 范围内的元素已经有序，对于新加入的元素 end+1，通过向前依次比较交换，最终插入到合适的位置，实现 [0,end+1] 范围内的元素有序。</p>
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printStandardSortResult(arr);

        System.out.println("插入排序后数组:");
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertionSort(int[] arr) {
        //边界条件校验
        if (arr == null || arr.length < 2) {
            return;
        }

        //假定[0,end]范围内的元素已经有序,对于新加入的元素cur=arr[end+1],通过向前依次比较交换,最终插入到合适的位置,实现[0,end+1]范围内的元素有序
        //[0,0]范围内已经有序,因此end从1开始
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            int curIdx = end;   //记录当前元素索引
            //用当前元素依次向前比较,如果前面还有元素,且当前元素比前一个元素小,则进行交换
            while (curIdx >= 1 && arr[curIdx] < arr[curIdx - 1]) {
                ArrayUtils.swap(arr, curIdx, curIdx - 1);
                --curIdx;
            }
        }
    }
}
