import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 岛屿的个数（字节跳动面试真题）
 * https://www.lintcode.com/problem/433
 * 基本思想还是采用bfs来做
 */
public class NumIslands {
    class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[] deltaX = {0, 1, -1, 0};
    int[] deltaY = {1, 0, 0, -1};

    public int numIslands(boolean[][] grid) {
        // 特殊情况处理
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int islands = 0;
        int row = grid.length, col = grid[0].length;
        //记录某点是否被BFS过，如果之前已经被BFS过，不应再次被BFS
        boolean[][] visited = new boolean[row][col];

        //遍历矩阵中的每一个点
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //如果为海洋，无需BFS
                //如果该点已经被visited,无需做冗余遍历，重复计算
                if (grid[i][j] && !visited[i][j]) {
                    bfs(grid, i, j, visited);
                    islands++;
                }
            }
        }

        return islands;
    }

    public void bfs(boolean[][] grid, int x, int y, boolean[][] visited) {
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.offer(new Coordinate(x,y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Coordinate coor = queue.poll();
            // 遍历上下左右四个方向
            for (int direction = 0; direction < 4; direction++) {
                int newX = coor.x + deltaX[direction];
                int newY = coor.y + deltaY[direction];
                if (!isValid(grid, newX, newY, visited)) {
                    continue;
                }
                queue.offer(new Coordinate(newX, newY));
                //一旦入队，标记此点已经参与BFS
                visited[newX][newY] = true;
            }
        }
    }

    public boolean isValid(boolean[][] grid, int x, int y, boolean[][] visited) {
        int n = grid.length, m = grid[0].length;
        //如果出界，返回false
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        //如果已经BFS过，不要再次BFS，避免：1，死循环 2，冗余的BFS变量
        if (visited[x][y]) {
            return false;
        }
        // 如果是1，则为true；如果是0，则为false
        return grid[x][y];
    }

}
