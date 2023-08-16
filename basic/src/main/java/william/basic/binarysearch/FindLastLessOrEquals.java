package william.basic.binarysearch;

import william.common.utils.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShenao
 * @date 2023/8/16 10:15 AM
 * @description: 在有序数组中找到最后一个<= target的索引
 */
public class FindLastLessOrEquals {
    
    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomSortedArray(10, -100, 100);
        System.out.println("有序数组为:");
        System.out.println(Arrays.toString(arr));
        int target = arr[ThreadLocalRandom.current().nextInt(0, arr.length)] + 1;
        System.out.println("待查找元素为: " + target);
        int findIndex = findLastLessOrEquals(arr, target);
        System.out.println("使用二分查找的索引为: " + findIndex);
        int compareIndex = compareMethod(arr, target);
        System.out.println("对照方法的索引为: " + compareIndex);
        int notExists = -999;
        int notExistsIndex = findLastLessOrEquals(arr, notExists);
        System.out.println("查找不存在的元素,索引为: " + notExistsIndex);
    }
    
    /**
     * 在有序数组arr中找到最后一个<=target的索引。如果未找到则返回-1
     */
    private static int findLastLessOrEquals(int[] arr, int target) {
        //边界条件校验
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = arr.length - 1;
        int index = -1; //记录当前<=target的索引
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (arr[mid] <= target) {    //找到了<=target的区间,更新索引值,并继续向右侧区间查找
                index = mid;
                start = mid + 1;
            } else {  //当前区间均>target,继续向左侧区间查找
                end = mid - 1;
            }
        }
        
        return index;
    }
    
    /**
     * 对照方法
     */
    private static int compareMethod(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= target) {
                return i;
            }
        }
        
        return -1;
    }
}
