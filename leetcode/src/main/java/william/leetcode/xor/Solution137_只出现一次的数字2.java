package william.leetcode.xor;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/single-number-ii/
 */
public class Solution137_只出现一次的数字2 {
    public int singleNumber(int[] nums) {
        //边界条件校验
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        //Step1:将数组中的每个元素,转换成int[]数组表示

        //Step2: 创建一个长度为32的int[]数组times,保存数组中每个元素在对应位出现1的次数。
        //如times[5]即为第5位出现1的次数。
        int[] times = new int[32];

        //Step3: 遍历数组,将每个元素在对应位出现1的次数相加,保存在times数组结果中
        for (int n : nums) {
            for (int i = 0; i < 32; i++) {
                if (((n >> i) & 1) == 1) {
                    times[i]++;
                }
            }
        }

        //Step4: 遍历times数组,如果某个元素mod3不等于0,说明目标数字在该位为1,记录结果
        int result = 0;
        for (int i = 0; i < times.length; i++) {
            if (times[i] % 3 != 0) {
                result |= (1 << i);
            }
        }

        return result;
    }
}
