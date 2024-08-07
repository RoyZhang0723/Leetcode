import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        int[] array = {12, 34, 54, 2, 3};
        System.out.println("Original Array: " + Arrays.toString(array));

        shellSort(array);

        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    public static void shellSort(int[] array) {
        int n = array.length;

        // 使用希尔排序的增量序列，通常为 n/2, n/4, ..., 1
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 从 gap 开始进行插入排序
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;

                // 插入排序
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }
    }
}