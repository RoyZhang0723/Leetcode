import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  子集（DFS在解决组合类问题时的一个应用）
 *  给定一个含不同整数的集合，返回其所有的子集。
 *  https://www.jiuzhang.com/problem/subsets/
 */
public class Subsets {
    public static List<List<Integer>> subSets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), results);
        return results;
    }

    /**
     * 1.递归的定义
     * 以subset开头的，配上nums以index开始的数所有组合放到results里
     * @param nums
     * @param index
     * @param subset
     * @param results
     */
    public static void dfs(int[] nums, int index, List<Integer> subset, List<List<Integer>> results) {
        // 3. 递归的出口
        if (index == nums.length) {
            results.add(new ArrayList<Integer>(subset));
            return;
        }

        // 2.递归的拆解
        // （如何进入下一层）

        // 选择了nums[index]
        subset.add(nums[index]);
        dfs(nums, index + 1, subset, results);

        // 不选nums[index]
        subset.remove(subset.size() - 1);
        dfs(nums, index + 1, subset, results);
    }
}
