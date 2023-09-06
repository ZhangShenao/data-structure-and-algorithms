package william.leetcode.xor;

/**
 * @author ZhangShenao
 * @date
 * @description 在一个数组中, 只有一个数字出现奇数次, 其余数字均出现偶数次。实现算法找到出现奇数次的数字。
 */
public class FindOddTimesNumber_找到一个数组中出现奇数次的数字 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 4, 3, 2, 1, 2, 2, 3, 3, 4, 1, 1};
        int res = findOddTimesNumber(arr);  //目标:4
        System.out.println(res);
    }

    private static int findOddTimesNumber(int[] arr) {
        //边界条件校验
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }

        //使用异或操作
        int xor = 0;

        //遍历数组,依次进行异或操作
        for (int i : arr) {
            xor ^= i;
        }

        //返回最终的异或结果
        return xor;
    }
}
