package william.basic.random;

import william.common.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/8/14 9:36 AM
 * @description: 计算数组[L, R]范围内的元素和
 * <p>算法思想：构造前缀和数组 preSum，使得 preSum[i] = 数组 [0,i] 范围内的元素和。</p>
 * <p>那么，[L,R] 范围内的元素和 = preSum[R] - preSum[L - 1]</p>
 */
public class RangeSum {
    
    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        System.out.println(Arrays.toString(arr));
        int rangeSum = rangeSum(arr, 2, 4);
        System.out.println(rangeSum);
    }
    
    /**
     * 计算数字arr[L,R]范围内的元素和
     */
    private static int rangeSum(int[] arr, int L, int R) {
        //边界条件校验
        if (L < 0 || R >= arr.length || L > R) {
            throw new IllegalArgumentException();
        }
        
        int[] preSum = buildPreSumArray(arr);
        if (L == 0) {
            return preSum[R];
        }
        
        return (preSum[R] - preSum[L - 1]);
    }
    
    /**
     * 构造前缀和数组
     */
    private static int[] buildPreSumArray(int[] arr) {
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        return preSum;
    }
}
