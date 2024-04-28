import java.util.*;

/**
 *
 * 2XXX 年，人类通过对火星的大气进行宜居改造分析，使得火星已在理论上具备人类宜居的条件；
 * 由于技术原因，无法一次性将火星大气全部改造，只能通过局部处理形式；
 * 假设将火星待改造的区域为 row∗column 的网格，每个网格有 3 个值，宜居区、可改造区、死亡区，使用 YES、NO、NA 代替：
 * ● YES 表示该网格已经完成大气改造；
 * ● NO 表示该网格未进行改造，后期可进行改造；
 * ● NA 表示死亡区，不作为判断是否改造完成的宜居，无法穿过；
 * 初始化下，该区域可能存在多个宜居区，并且每个宜居区能同时在每个太阳日单位向上下左右四个方向的相邻格子进行扩散，自动将 4 个方向相邻的真空区改造成宜居区；
 * 请计算这个待改造区域的网格中，可改造区是否能全部变成宜居区，如果可以，则返回改造的太阳日天数，不可以则返回-1。
 */
public class PlantGraph {
    private static int rows;
    private static int cols;
    private static String[][] gridCopy;
    //coordinates是每次没有用于扩散的yes节点的坐标
    private static List<int[]> coordinates = new ArrayList<>();

    public static void main(String[] args) {
        List<String> inputList = readInput();

        initGrids(inputList);
        //记录no出现次数
        int noNums = countNoOccurrences();

        boolean flag = true;
        int day = 0;

        while (noNums != 0 && flag) {
            for (int[] coordinate : findYesCoords()) {
                //更新附近的NO节点为yes
                updateAdjElems(coordinate[0], coordinate[1]);
            }

            if (!coordinates.isEmpty()) {
                updateGridCopy();
                noNums -= coordinates.size();
                coordinates.clear();
                day++;
            } else {
                flag = false;
            }
        }

        printDayOrMinusOne(noNums, day);
    }

    private static List<String> readInput() {
        List<String> inputList = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                inputList.add(scanner.nextLine());
            }
        }
        return inputList;
    }

    private static void initGrids(List<String> inputList) {
        rows = inputList.size();
        cols = inputList.get(0).split(" ").length;
        String[][] grid = new String[rows][cols];
        gridCopy = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] strings = inputList.get(i).split(" ");
            System.arraycopy(strings, 0, grid[i], 0, cols);
            System.arraycopy(strings, 0, gridCopy[i], 0, cols);
        }
    }

    /**
     * 记录no出现次数
     * @return
     */
    private static int countNoOccurrences() {
        int count = 0;
        for (String[] row : gridCopy) {
            count += (int) Arrays.stream(row).filter(s -> s.equals("NO")).count();
        }
        return count;
    }

    private static void updateAdjElems(int i, int j) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    gridCopy[newRow][newCol].equals("NO")) {
                gridCopy[newRow][newCol] = "YES";
                coordinates.add(new int[] {newRow, newCol});
            }
        }
    }


    private static List<int[]> findYesCoords() {
        List<int[]> yesCoords = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (gridCopy[i][j].equals("YES")) {
                    yesCoords.add(new int[] {i, j});
                }
            }
        }
        return yesCoords;
    }

    private static void updateGridCopy() {
        for (int[] coordinate : coordinates) {
            gridCopy[coordinate[0]][coordinate[1]] = "YES";
        }
    }

    private static void printDayOrMinusOne(int noNums, int day) {
        System.out.println(noNums== 0 ? day : -1);
    }
}