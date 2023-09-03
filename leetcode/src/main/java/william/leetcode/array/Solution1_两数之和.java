package william.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/two-sum/
 */
public class Solution1_两数之和 {
    public int[] twoSum(int[] nums, int target) {
        //边界条件校验
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        //使用一个Hash表保存数组遍历的结果
        //key=元素值 value=元素在数组中的索引
        Map<Integer, Integer> cache = new HashMap<>(nums.length);

        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            //计算目标值与当前元素的差值
            int cur = nums[i];

            int minus = target - cur;

            //如果差值在Hash表中存在,说明找到了满足条件的结果,直接返回
            if (cache.containsKey(minus)) {
                int idx = cache.get(minus);
                return new int[]{idx, i};
            }

            //为找到满足条件的元素,则将当前元素及其索引放入Hash表中
            cache.put(cur, i);

        }

        //未找到目标结果
        return new int[]{-1, -1};
    }
}
