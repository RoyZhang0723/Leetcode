import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        System.out.println("Original Array: " + Arrays.toString(array));

        heapSort(array);

        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // 提取元素一个一个地从堆中移除
        for (int i = n - 1; i >= 0; i--) {
            // 将当前根移动到数组末尾
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // 调整堆
            heapify(array, i, 0);
        }
    }

    // 堆化函数
    public static void heapify(int[] array, int n, int i) {
        int largest = i; // 初始化最大元素为根
        int left = 2 * i + 1; // 左子节点
        int right = 2 * i + 2; // 右子节点

        // 如果左子节点大于根
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // 如果右子节点大于当前最大元素
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // 如果最大元素不是根
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // 递归堆化子树
            heapify(array, n, largest);
        }
    }
}