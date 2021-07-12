/**
 * 在一个 N x N 的方格里，每个格子里都放了一定数量的芝麻。一个小孩从方格的左上角开始捡格子里的芝麻，
 * 并且每次只能向右或者向下移动一格，直到到达方 格的右下角。求小孩最多能捡到多少芝麻。
 */
public class MostZhiMa {
    public static void main(String[] args) {
        int[][] zhiMaList = new int[3][3];
        zhiMaList[0][0] = 1;
        zhiMaList[0][1] = 5;
        zhiMaList[0][2] = 1;
        zhiMaList[1][0] = 1;
        zhiMaList[1][1] = 6;
        zhiMaList[1][2] = 1;
        zhiMaList[2][0] = 2;
        zhiMaList[2][1] = 3;
        zhiMaList[2][2] = 1;
        System.out.println(mostZhiMa(zhiMaList, 2, 2));
    }

    public static int mostZhiMa(int[][] zhiMaList, int line, int col) {
        if (line == 0 && col == 0) {
            return zhiMaList[0][0];
        }
        if (line == 0) {
            return mostZhiMa(zhiMaList, line, col - 1) + zhiMaList[line][col];
        } else if (col == 0) {
            return mostZhiMa(zhiMaList, line - 1, col) + zhiMaList[line][col];
        }
        return max(mostZhiMa(zhiMaList, line - 1, col), mostZhiMa(zhiMaList, line, col - 1)) + zhiMaList[line][col];
    }

    public static int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }
}
