package william.leetcode.array;

/**
 * @author ZhangShenao
 * @date 2023/8/25 5:41 PM
 * @description: https://leetcode.cn/problems/find-peak-element/
 */
public class Solution162_寻找峰值 {
    
    public int findPeakElement(int[] nums) {
        //边界条件校验
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        
        int L = nums.length;
        
        if (L == 1) {
            return 0;
        }
        
        //考虑左右边界
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[L - 1] > nums[L - 2]) {
            return L - 1;
        }
        
        //nums[0]<arr[1]且nums[L-1]<nums[L-2],说明数组在[0,L-1]区间内呈先上升后下降的趋势,则[0,L-1]区间内一定存在峰值
        //使用二分查找思想,在arr[start,end]范围内找峰值
        int start = 0;
        int end = L - 1;
        while (start < end - 1) {    //保证[start,end]区间内至少存在三个元素,则无需额外考虑mid的边界情况
            int mid = start + ((end - start) >> 1); //避免溢出
            
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {    //找到峰值
                return mid;
            }
            
            //nums[start]<nums[start+1]且nums[mid-1]>nums[mid],说明数组在[start,mid-1]区间内呈先上升后下降的趋势,则[start,mid-1]区间内一定存在峰值
            //抛弃数组右半部分,向左侧部分继续查找
            if (nums[mid] < nums[mid - 1]) {
                end = mid - 1;
            } else { //反之,抛弃数组左半部分,向右侧继续查找
                start = mid + 1;
            }
        }
        
        //上述过程未找到峰值,最后剩余[start,end],且start>=end-1,说明该区间最多只剩两个元素,单独判断即可
        return (nums[start] > nums[end] ? start : end);
        
    }
}
