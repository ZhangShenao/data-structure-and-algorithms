package william.leetcode.sort;

/**
 * @author ZhangShenao
 * @date 2023/8/30 5:03 PM
 * @description: https://leetcode.cn/problems/merge-sorted-array/
 */
public class Solution88_合并两个有序数组 {
    
    //nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //边界条件校验
        if (nums2 == null || nums2.length == 0 || n <= 0 || nums2.length != n){
            return;
        }
        
        
        int N1 = m - 1;
        int N2 = n - 1;
        int i = nums1.length - 1;
        
        //从大到小(从右往左)同时遍历两个数组,将较大的数保存到nums1的末尾
        while (N1 >= 0 && N2 >= 0) {
            if (nums1[N1] >= nums2[N2]) {
                nums1[i--] = nums1[N1--];
            } else {
                nums1[i--] = nums2[N2--];
            }
        }
        
        //处理其中一个数组已经遍历完成的情况
        while (N1 >= 0) {
            nums1[i--] = nums1[N1--];
        }
        while (N2 >= 0) {
            nums1[i--] = nums2[N2--];
        }
    }
}
