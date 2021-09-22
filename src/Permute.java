import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定一个数字列表，返回其所有可能的排列。（假设没有重复元素）
 * https://www.lintcode.com/problem/15/
 */
public class Permute {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public static List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        boolean visited[] = new boolean[nums.length];
        dfs(nums, visited,new ArrayList<Integer>(), results);
        return results;
    }

    private static void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<Integer>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            permutation.add(nums[i]);
            // 在这个地方分成两个分叉，一个分叉是在说排这个数，一个分叉不排这个数
            // 全排列实际上挺简单的，就是在每个位置上把每个可能的元素都放上去一下
            visited[i] = true;
            dfs(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,7,8};
        List<List<Integer>> result = new ArrayList<>();
        result = permute(nums);
        System.out.println("done");
    }
}