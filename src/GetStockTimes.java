import java.util.PriorityQueue;

/**
 *
 * 买卖股票的最佳时机V:
 * 给出一个股票n天的价格，每天最多只能进行一次交易，可以选择买入一支股票或卖出一支股票或放弃交易，输出能够达到的最大利润值
 *
 *
 * https://www.lintcode.com/problem/1691/?_from=cat
 * 要会转化，实际上还是求解第k小个数,维护一个最小堆就可以了
 *
 */
public class GetStockTimes {
    public static int getAns(int[] a) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int result = 0;
        for (int k : a) {
            // 如果k比之前遇到过堆最低价高
            if (minHeap.size() > 0 && k > minHeap.peek()) {
                // 收益就是当前k - 遇到过堆最低价
                result += k - minHeap.poll();
                minHeap.offer(k);
            }
            minHeap.offer(k);
        }
        return result;
    }
}
