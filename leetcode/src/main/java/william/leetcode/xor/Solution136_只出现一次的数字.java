package william.leetcode.xor;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/single-number/
 */
public class Solution136_只出现一次的数字 {
    public int singleNumber(int[] nums) {
        //边界条件校验
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        //使用异或操作,遍历数组,依次对每一个元素进行异或
        //出现了两次的数字异或后会互相抵消为0,最后剩余的结果就是只出现了一次的数字
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }

        //返回异或结果
        return xor;
    }
}
