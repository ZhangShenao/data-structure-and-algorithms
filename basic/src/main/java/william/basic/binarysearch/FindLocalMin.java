package william.basic.binarysearch;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShenao
 * @date 2023/8/17 10:11 AM
 * @description: 找到局部最小值
 * <p>给定一个长度为N的无序数组,已知数组中的相邻元素均不相等。要求找到数组中任意一个局部最小值所在的索引。</p>
 */
public class FindLocalMin {
    
    public static void main(String[] args) {
        int[] arr = genTargetArray(20, 40, 1, 1000);
        System.out.println("目标数组为:");
        System.out.println(Arrays.toString(arr));
        int localMinIndex = findLocalMinIndex(arr);
        System.out.println("找到的局部最小值索引为: " + localMinIndex + ", 元素为: " + arr[localMinIndex]);
        boolean succ = isLocalMinIndex(arr, localMinIndex);
        System.out.println("该索引是否为局部最小值索引: " + succ);
    }
    
    /**
     * 找到数组arr中局部最小值所在的索引。如果未找到则返回-1
     */
    private static int findLocalMinIndex(int[] arr) {
        //处理边界条件
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int N = arr.length;
        //如果数组只有一个元素,则arr[0]就是局部最小值
        if (N == 1) {
            return 0;
        }
        //处理首、尾处的局部最小值
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[N - 1] < arr[N - 2]) {
            return N - 1;
        }
        
        //数组呈先下降、后上升的趋势，局部最小值一定在[0,N-1]范围内。
        //采用二分查找思想,在[start,mid,end]范围内判断局部最小值
        int start = 0;
        int end = N - 1;
        while (start < end - 1) {    //要求[start,mid,end]区间内至少存在三个元素
            int mid = start + ((end - start) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {    //找到局部最小值,直接返回
                return mid;
            }
            if (arr[mid] > arr[mid] - 1) {   //数组在[start,mid]范围内先下降后上升,该范围内一定存在局部最小值,在[start,mid-1]范围内继续查找
                end = mid - 1;
            } else {   //否则arr[mid]>arr[mid + 1],则数组在[mid,end]范围内先下降后上升,该范围内一定存在局部最小值,在[mid+1,end]范围内继续查找。
                start = mid + 1;
            }
        }
        
        //上述过程为找到局部最小值,说明start>=end-1,则[start,end]区间内最多只有2个元素,单独判断这两个元素即可
        return arr[start] <= arr[end] ? start : end;
    }
    
    /**
     * 生成目标数组
     */
    private static int[] genTargetArray(int minLen, int maxLen, int minValue, int maxValue) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int len = rand.nextInt(minLen, maxLen);
        int[] arr = new int[len];
        arr[0] = rand.nextInt(minValue, maxValue);
        for (int i = 1; i < len; i++) {
            int v = rand.nextInt(minLen, maxLen);
            while (v == arr[i - 1]) {
                v = rand.nextInt(minLen, maxLen);
            }
            arr[i] = v;
        }
        return arr;
    }
    
    /**
     * 判断所以index所在的元素是否为数组arr的局部最小值
     */
    private static boolean isLocalMinIndex(int[] arr, int index) {
        if (arr == null || arr.length == 0) {
            return (index == -1);
        }
        if (arr.length == 1) {
            return (index == 0);
        }
        int N = arr.length;
        if (index == 0) {
            return (arr[0] < arr[1]);
        }
        if (index == N - 1) {
            return (arr[N - 1] < arr[N - 2]);
        }
        return (arr[index] < arr[index - 1] && arr[index] < arr[index + 1]);
    }
}
