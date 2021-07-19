import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 在一条数轴上，有n个城市，编号从0 ~ n – 1 , 约翰打算在这n个城市做点生意，
 * 他对Armani的一批货物感兴趣，每个城市对于这批货物都有一个价格prices[i]。
 * 对于城市x,约翰可从城市编号为[x - k, x + k]购买货物，然后卖到城市x,问约翰在每个城市最多能赚到多少钱？
 */
public class JohnBusiness {

    /**
     * 因为这个一个涉及到固定区间内的问题，所以用窗口滑动的思路是比较正确的，然后需要找更新区间的最小值，实际上维护一个最小队就可以了。
     * @param A
     * @param k
     * @return
     */
    public static int[] business(int[] A, int k) {
        // handle corner cases
        if (A == null || A.length == 0) {
            return new int[0];
        }

        int n = A.length;
        int[] profit = new int[n];
        Queue<Integer> minHeap = new PriorityQueue<>();

        // init the first 0...k-1 prices into the minHeap
        for (int i = 0; i < k; i++) {
            minHeap.add(A[i]);
        }

        // core logic - keep sliding the window,
        // update the minHeap by removing the left most outdated, and adding the right most new addition.
        // then always take the current price, the A[i], and subtract that to the minHeap.peek(),
        // otherwise if it's not profitable, just use zero. indicating that you don't take that deal for a loss.
        for (int i = 0; i < n; i++) {
            if (i - k - 1 >= 0) {
                minHeap.remove(A[i - k - 1]);
            }
            if (i + k < n) {
                minHeap.add(A[i + k]);
            }
            profit[i] = Math.max(0, A[i] - minHeap.peek());
        }

        return profit;
    }

}
