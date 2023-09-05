package william.leetcode.xor;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/swap-numbers-lcci/
 * 借助异或^操作来实现。异或的基本原理:
 * 1. 0异或任意数都等于该数本身
 * 2. 相同的两个数异或会得到0
 * 3. 异或操作满足交换律与结合律
 */
public class Interview16_01_交换数字 {
    public int[] swapNumbers(int[] numbers) {
        //边界条件校验
        if (numbers == null || numbers.length != 2) {
            return new int[0];
        }


        //使用异或操作完成两数交换
        //在原数组上完成交换
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];

        //返回原数组
        return numbers;
    }
}
