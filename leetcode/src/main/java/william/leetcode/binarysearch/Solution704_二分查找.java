package william.leetcode.binarysearch;

/**
 * @author ZhangShenao
 * @date 2023/8/25 5:27 PM
 * @description: https://leetcode.cn/problems/binary-search/
 */
public class Solution704_二分查找 {
    
    public int search(int[] nums, int target) {
        //边界条件校验
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        //在[start,end]区间内进行二分查找
        int start = 0;
        int end = nums.length - 1;
        
        while (start <= end) {
            int mid = (start + ((end - start) >> 1));    //避免溢出
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        //未找到
        return -1;
    }
}
