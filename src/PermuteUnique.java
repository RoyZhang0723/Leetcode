import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 带重复的元素的排列
 * https://www.lintcode.com/problem/16/solution?_from=cat
 */
public class PermuteUnique {
    public static void main(String[] args) {
        int[] nums = {1, 1, 5, 7, 8};
        List<List<Integer>> results = new ArrayList<>();
        results = permuteUnique(nums);
        System.out.println("done");
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, visited, new ArrayList<>(), results);
        return results;
    }

    private static void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            /**
             * 这个判断条件就是为了排除重复元素的影响。跳过的情况是前面的元素还没排进去，
             * 如果前面的相同的元素在这个位置安排先不放，去放后面另外一个元素，但是你放一个后面位置相同的元素，就造成重复了。
             */
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}
