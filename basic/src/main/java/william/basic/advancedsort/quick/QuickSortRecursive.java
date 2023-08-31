package william.basic.advancedsort.quick;

import william.common.utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date 2023/8/31 9:38 AM
 * @description: 快速排序——递归实现
 */
public class QuickSortRecursive {
    
    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(20, -100, 100);
        ArrayUtils.printStandardSortResult(arr);
        quickSort(arr);
        System.out.println("快速排序后数组:");
        System.out.println(Arrays.toString(arr));
    }
    
    public static void quickSort(int[] arr) {
        //边界条件校验
        if (arr == null || arr.length < 2) {
            return;
        }
        
        //对arr[0,len-1]范围进行快速排序
        quickSortRecursive(arr, 0, arr.length - 1);
    }
    
    /**
     * 递归实现：对数组arr[L,R]范围进行快速排序
     */
    private static void quickSortRecursive(int[] arr, int L, int R) {
        //递归终止条件
        if (L >= R) {
            return;
        }
        
        //进行partition操作,找到等于区间的范围
        int[] equalsRange = partition(arr, L, R);
        
        //对小于区和大于区分别再次进行快速排序
        if (L < equalsRange[0] - 1) {
            quickSortRecursive(arr, L, equalsRange[0] - 1);
        }
        if (equalsRange[1] + 1 < R) {
            quickSortRecursive(arr, equalsRange[1] + 1, R);
        }
    }
    
    /**
     * 以数组最后一个元素为基准值, 将数组分成三部分:左边部分元素均小于基准值;中间部分元素均等于基准值;右边部分元素均大于基准值。
     *
     * @return 返回一个包含两个元素的数组, 第一个元素是等于基准值区间的左边界, 第二个元素是等于基准值区间的右边界
     */
    public static int[] partition(int[] arr, int L, int R) {
        //边界条件校验
        if (arr == null || arr.length == 0 || L >= R) {
            return new int[] {0, 0};
        }
        
        //维护两个区间
        //小于区间:[L,lessR],lessR指向小于基准值的最后一个位置
        //大于区间:[greaterL,R],greaterL指向大于基准值的第一个位置
        int lessR = L - 1;
        int greaterL = R;
        
        //遍历数组
        int i = L;
        while (i < greaterL) {   //终止条件:当前元素到达了大于区的第一个元素
            if (arr[i] < arr[R]) {   //当前元素小于基准值,将当前元素与lessR的下一个元素交换,并且[L,lessR]区间向右移动一位,并继续遍历下一个元素
                ArrayUtils.swap(arr, i++, ++lessR);
            } else if (arr[i] > arr[R]) { //当前元素大于基准值,将当前元素与greaterL的前一个元素交换,并且[greaterL,R]区间向左移动一位
                ArrayUtils.swap(arr, i, --greaterL);
                
                //这里i不要向前移动,因为新交换过来的i位置的元素还没有处理
            } else {    //当前元素等于基准值,无需任何处理,继续遍历
                i++;
            }
        }
        
        //将基准值与greaterL处的元素交换
        ArrayUtils.swap(arr, R, greaterL);
        
        //等于基准值的区间为[lessR+1,greaterL]
        return new int[] {lessR + 1, greaterL};
    }
}
