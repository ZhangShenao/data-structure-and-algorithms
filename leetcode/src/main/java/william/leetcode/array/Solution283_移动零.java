package william.leetcode.array;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/move-zeroes/
 */
public class Solution283_移动零 {
    public void moveZeroes(int[] nums) {
        //边界条件校验
        if (nums == null || nums.length == 0) {
            return;
        }

        //使用双指针
        //i指向数组当前元素下标
        //lastNonZero指向最后一个非零元素的下标
        int lastNonZero = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) { //如果遍历到非零元素,就将元素保存到lastNonZero的位置上,并将lastNonZero向后移动
                nums[lastNonZero++] = nums[i];
            }
        }

        //最后lastNonZero指向第一个0的位置,在数组末尾补0
        while (lastNonZero < nums.length) {
            nums[lastNonZero++] = 0;
        }
    }
}
