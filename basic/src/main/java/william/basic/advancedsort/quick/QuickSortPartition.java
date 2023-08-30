package william.basic.advancedsort.quick;

import william.common.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/8/30 3:31 PM
 * @description: 快速排序的Partition过程
 * <p>以数组最后一个元素为基准值,将数组分成左、右两部分,左部分元素<=基准值,右部分元素>基准值,且基准值位于左、右分界线上。</p>
 */
public class QuickSortPartition {
    
    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, 1, 100);
        System.out.println("原始数组: ");
        System.out.println(Arrays.toString(arr));
        int partition = partition(arr, 0, arr.length - 1);
        System.out.println("Partition返回结果: " + partition);
        System.out.println("Partition操作后数组结果: ");
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * 以数组最后一个元素为基准值,将数组分成左、右两部分,左部分元素<=基准值,右部分元素>基准值,且基准值位于左、右分界线上。最终返回基准值所在的索引
     */
    private static int partition(int[] arr, int L, int R) {
        //边界条件校验
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (L >= R) {
            return L;
        }
        
        //维护区间[0,lessEqualR],区间内的元素均<=基准值
        //lessEqualR为<=基准值的最后一个元素,lessEqualR为大于基准值的第一个元素
        int lessEqualR = -1;
        
        //遍历数组,依次将元素与基准值进行比较
        for (int i = 0; i <= R; i++) {
            if (arr[i] <= arr[R]) {  //当前元素小于基准值,与[0,lessEqualR]区间的下一个元素交换
                ArrayUtils.swap(arr, i, lessEqualR + 1);
                ++lessEqualR;
            }
        }
        
        return lessEqualR;
    }
}
