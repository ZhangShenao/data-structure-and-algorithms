package william.basic.xor;

import java.util.Arrays;

/**
 * @author ZhangShenao
 * @date
 * @description 一个数组中有两种数出现了奇数次, 其他数都出现了偶数次, 怎么找到并打印这两种数
 */
public class 找到数组中出现次数为奇数次的两个数 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6};
        int[] result = findTwoOddTimesNumber(nums);
        System.out.println(Arrays.toString(result));    //期望结果[1,6]
    }

    public static int[] findTwoOddTimesNumber(int[] nums) {
        //边界条件校验
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        //Step1: 遍历数组,依次将元素进行异或,出现偶数次的数字异或后会互相抵消,最后的到的就是a^b
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }

        //Step2: 提取中异或结果右侧的第一个1,说明a和b两个数字在该位不相等,可以基于次将整个数组划分成两部分
        int rightOne = xor & ((~xor) + 1);

        //Step3: 遍历数组,将所有该位为1的元素进行异或,最终得到第一个目标数字
        int firstNum = 0;
        for (int n : nums) {
            if ((n & rightOne) != 0) {
                firstNum ^= n;
            }
        }

        //Step4: 将第一个数与第一次异或的结果再次异或,得到第二个目标数字
        int secondNum = xor ^ firstNum;

        //Step5: 返回最终结果
        return new int[]{firstNum, secondNum};
    }
}
