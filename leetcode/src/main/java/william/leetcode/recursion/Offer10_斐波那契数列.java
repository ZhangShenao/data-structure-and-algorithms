package william.leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class Offer10_斐波那契数列 {
    /**
     * 使用一个HashMap保存中间计算结果,减少重复计算,提升性能
     */
    private Map<Integer, Integer> cache = new HashMap<>();

    public int fib(int n) {
        //边界条件校验
        if (n < 0) {
            return 0;
        }
        return fibRecursion(n);
    }

    /**
     * 采用递归思想
     * f(0)=0 f(1)=1
     * 递推公式: f(n) = f(n-1) + f(n-2) , 其中n>=2
     */
    private int fibRecursion(int n) {
        //递归终止条件
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        //首先从缓存中查询
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int result = (fib(n - 1) + fib(n - 2)) % 1000000007;

        //缓存计算结果
        cache.put(n, result);

        return result;
    }
}
