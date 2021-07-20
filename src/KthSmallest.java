import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 找到一个无序数组中第K小的数，目标是在O(n)的时间复杂度下解决
 */
public class KthSmallest {
    /**
     * 解法一，利用双指针来进行隔离
     *
     * @param k
     * @param nums
     * @return
     */
    public static int kthSmallest(int k, int[] nums) {
        int n = nums.length;
        // 数组从0开始标号，要传k - 1
        return partition(nums, 0, n - 1, k - 1);
    }

    private static int partition(int[] nums, int start, int end, int k) {
        int left = start, right = end;
        int pivot = nums[left];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        // 如果第 k 小在右侧，搜索右边的范围，否则搜索左侧。
        if (k <= right) {
            return partition(nums, start, right, k);
        }
        if (k >= left) {
            return partition(nums, left, end, k);
        }
        return nums[k];
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    /**
     * 解法二 ： 用堆来解决
     *
     * @param k
     * @param nums
     * @return
     */
    public static int kthSmallestHeap(int k, int[] nums) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
        }
        for (int i = k - 1; i > 0; i--) {
            minHeap.remove(minHeap.peek());
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {2, 6, 10, 5, 8};
        System.out.println(kthSmallest(3, nums));
        System.out.println(kthSmallestHeap(3, nums));
    }

}
