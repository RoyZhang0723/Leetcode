/**
 *
 * 玩游戏
 * 二分法的经典题目，在答案集上进行二分
 * https://www.jiuzhang.com/problem/1671-play-game/
 */
public class PlayGames {
    /**
     *
     * @param A
     * @return
     */
    public static long playGames(int[] A) {
        // Write your code here
        long end = sum(A);
        long start = Long.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            start = Math.min(start, A[i]);
        }
        return binarySearch(A, start, end);
    }

    public static long sum(int[] A) {
        long ans = 0;
        for (int i = 0; i < A.length; i++) {
            ans += A[i];
        }
        return ans;
    }

    public static long binarySearch(int[] A, long start, long end) {
        while (start + 1 < end) {
            long mid = (start + end) / 2;
            if (isEnough(A, mid)) end = mid;
            else start = mid;
        }
        if (isEnough(A, start)) return start;
        return end;
    }

    public static boolean isEnough(int[] A, long num) {
        long[] referee = new long[A.length];
        // 总共的游戏局数减去当平民的局数，剩下的就是玩家当裁判的局数
        for (int i = 0; i < referee.length; i++) {
            referee[i] = num - A[i];
        }
        // 当裁判的局数永远大于等于0
        for (long item : referee) {
            if (item < 0) return false;
        }
        long sum = 0;
        for (long item : referee) {
            sum += item;
        }
        // 每个人当裁判的次数之和就是游戏的总次数，不能小于num
        // 假设每次都是一个人做裁判的话，num次游戏下来，平民数量总和是num * (length - 1) >= sum(A[])
        // 根据这个公式可得sum < num这个判定式子
        if (sum < num) return false;
        return true;
    }
}
