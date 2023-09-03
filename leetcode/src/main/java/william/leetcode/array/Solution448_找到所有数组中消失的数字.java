package william.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ZhangShenao
 * @date
 * @description https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
 */
public class Solution448_找到所有数组中消失的数字 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //边界条件校验
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        //第一次遍历数组,将以(当前元素-1)为下标处的元素变为负数
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;    //该位置的元素可能已经变为负数了,需要先恢复原值
            nums[idx] = -Math.abs(nums[idx]);
        }

        //第二次遍历数组,如果当前元素仍未正数,说明当前下标并未被修改,则值为(下标+1)的元素不存在
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add((i + 1));
            }
        }

        //返回结果
        return result;
    }
}
