import java.util.Arrays;
import java.util.Scanner;

public class huawei1 {
    public static void huawei1(int[][] data, int n, int m) {
        int[] answer = new int[n];
        int ans_index = 0, ans_num = 0;
        int j = 0;
        for (int i = 0; i < m; i++) {
            if (ans_num < n) {
                for (j = answer.length - ans_index - 1; j >= 0 && data[i][0] > 0; j--) {
                    answer[j] = data[i][1];
                    data[i][0]--;
                    ans_num++;
                    ans_index++;
                }
                Arrays.sort(answer);
            }
            while (data[i][0] > 0 && data[i][1] > findMin(answer)) {
                answer[0] = data[i][1];
                data[i][0]--;
                Arrays.sort(answer);
            }
            System.out.println(getString(answer, ans_index));

        }
    }

    public static int findMin(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static String getString(int[] array, int index) {
        String answer = "";
        int i;
        for (i = array.length - 1; i >= array.length - index; i--) {
            answer = answer + String.valueOf(array[i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int[][] data = new int[m][2];
        for (int i = 0; i < m; i++) {
            data[i][0] = s.nextInt();
            data[i][1] = s.nextInt();
        }
        huawei1(data, n, m);
    }
}
