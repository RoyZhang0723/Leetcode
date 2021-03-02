public class quickSort {
    public static void main(String[] args) {
        int a[];
        a = new int[]{5, 9, 6, 55, 87, 23, 99, 62, 74, 52};
        qs(a, 0, 9);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void qs(int a[], int lo, int hi) {
        if (hi <= lo) {
            return;
        } else if (hi - lo == 1) {

        } else {
            int middle;
            middle = partition(a, lo, hi);
            qs(a, lo, middle-1);
            qs(a, middle+1, hi);
        }
    }

    public static int partition(int a[], int lo, int hi) {
        int length = hi - lo + 1;
        int pivot = a[length - 1];
        int temp = 0;
        int i = lo - 1;
        for (int j = lo; j < length; j++) {
            if (a[j] < pivot) {
                i++;
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        temp = a[hi];
        a[hi] = a[i + 1];
        a[i + 1] = temp;
        return i + 1;
    }
}
