import java.util.HashMap;
import java.util.Map;

/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/17
 * leetcode-01，两数之和
 * 公众号：算法之灵魂拷问
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum2(nums, target);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }

    /**
     * 暴力解法
     * @param nums 输入的数组
     * @param target 目标值
     * @return 返回记过
     */
    public static int[] twoSum(int[] nums, int target) {
        //遍历nums数组，依次计算target与当前元素的差值
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            //遍历nums数组，判断数组中是否包含差值
            for (int j = 0; j < nums.length; j++) {
                //使用i != j来限制同一个元素不被重复利用
                if(diff == nums[j] && i != j){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    /**
     * hash法解题过程
     * @param nums 输入的数组
     * @param target 目标值
     * @return 返回记过
     */
    public static int[] twoSum2(int[] nums, int target) {
        //用于存放值->索引的映射关系，其中的key为hash结构，只存储已经遍历过的元素
        Map<Integer, Integer> map = new HashMap<>();
        //遍历所有愿随
        for (int i = 0; i < nums.length; i++) {
            //计算差值
            int diff = target - nums[i];
            //查看遍历过的元素中是否包含这个diff
            if(map.containsKey(diff)){
                return new int[]{map.get(diff), i};
            }
            //如果当前元素与已遍历过的元素没法配对达到target的要求，将当前元素放入已遍历过的hash表中
            map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }
}
