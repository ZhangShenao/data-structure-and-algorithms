package william.basic.binarysearch;

import william.common.utils.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShenao
 * @date 2023/8/16 9:31 AM
 * @description: 经典二分查找算法
 */
public class BinarySearch {
    
    public static void main(String[] args) {
        int[] array = ArrayUtils.generateRandomSortedArray(10, -100, 100);
        System.out.println("有序数组为:");
        System.out.println(Arrays.toString(array));
        int idx = ThreadLocalRandom.current().nextInt(0, array.length);
        int target = array[idx];
        System.out.println("待查找元素为: " + target + ", 所在索引为: " + idx);
        int search = binarySearch(array, target);
        System.out.println("使用二分查找得到的索引为: " + search);
        int notExisted = 9999;
        int notExistedIndex = binarySearch(array, notExisted);
        System.out.println("使用二分查找不存在的元素, 索引为: " + notExistedIndex);
    }
    
    /**
     * 二分查找:在数组arr中查找指定元素target所在的索引。如果未找到则返回-1
     */
    private static int binarySearch(int[] arr, int target) {
        //边界条件校验
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = arr.length - 1;
        
        while (start <= end) {
            int mid = start + ((end - start) >> 1); //避免直接相加导致结果溢出
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
        
    }
}
