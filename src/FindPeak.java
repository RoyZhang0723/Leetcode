/**
 * 在O(logN)时间复杂度内寻找峰值
 */
public class FindPeak {
    public static int findPeak(int[] A) {
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            /**
             * 如果中间的数比后一个数大的话，peek点肯定在mid左边或者是mid.
             * 如果中间数比前一位数小的话，peek点肯定在mid右边或者是mid.
             */
            if (A[mid] > A[mid] + 1) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A[start] < A[end]) {
            return end;
        } else {
            return start;
        }
    }
}
