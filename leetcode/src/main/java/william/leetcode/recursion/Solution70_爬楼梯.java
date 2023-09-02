package william.leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/climbing-stairs/
 */
public class Solution70_爬楼梯 {
    /**
     * 使用Map保持中间结果,减少重复计算,提升运算效率
     */
    private Map<Integer, Integer> cache = new HashMap<>();

    public int climbStairs(int n) {
        //边界条件校验
        if (n <= 0) {
            return 0;
        }
        return climbStairsRecursion(n);
    }

    /**
     * 使用递归思想：爬n阶楼梯的方式 = (第一次爬1阶+爬剩余n-1阶楼梯的方式) + (第一次爬2阶+爬剩余n-2阶楼梯的方式)
     * 递推公式: f(n) = f(n-1) + f(n-2)
     */
    private int climbStairsRecursion(int n) {
        //递归终止条件
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        //从结果缓存中查找
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int result = climbStairsRecursion(n - 1) + climbStairsRecursion(n - 2);

        //将中间计算结果保存在缓存中
        cache.put(n, result);
        return result;
    }
}
