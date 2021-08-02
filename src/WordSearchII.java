import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单词搜索II （dfs的一个应用，难度颇大）
 *
 * https://www.lintcode.com/problem/132
 */
public class WordSearchII {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        // 特殊情况处理
        if (board == null || board.length == 0) {
            return new ArrayList<>();
        }
        if (board[0] == null || board[0].length == 0) {
            return new ArrayList<>();
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        // wordSet中含有所有需要寻找的词
        Set<String> wordSet = new HashSet<>();
        // prefixSet中含有所有词的前缀（包括整个词）
        Set<String> prefixSet = new HashSet<>();

        // 遍历每个词，然后依次添加到前缀集合里面
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                prefixSet.add(word.substring(0, i + 1));
            }
            wordSet.add(word);
        }

        Set<String> resultSet = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                visited[i][j] = true;
                dfs(board, visited, i,j,String.valueOf(board[i][j]),wordSet,prefixSet,resultSet);
                visited[i][j] = false;
            }
        }

        return new ArrayList<>(resultSet);
    }

    private void dfs(char[][] board, boolean[][] visited, int x, int y, String word, Set<String> wordSet, Set<String> prefixSet, Set<String> resultSet) {
        // 如果不是prefix, 没有不要继续走下去，回退一步
        if (!prefixSet.contains(word)) {
            return;
        }
        // 如果找到一个词，记录下来，继续走
        // 这里已经找到一个词了，为什么不可以立即返回？
        // 如果找到了dog是个词，需要继续向下找，有可能找到另一个dogecoin
        if (wordSet.contains(word)) {
            resultSet.add(word);
        }

        for (int i = 0; i < 4; i++) {
            int adjX = x + dx[i];
            int adjY = y + dy[i];
            // 如果越界，或者已经在当前路径中被访问过，跳过
            if (!inside(board, adjX, adjY) || visited[adjX][adjY]) {
                continue;
            }
            // 标记这一点在当前路径中已经被访问过
            visited[adjX][adjY] = true;
            // 递归进行dfs，继续延伸路径
            dfs(board, visited, adjX, adjY, word + board[adjX][adjY], wordSet, prefixSet, resultSet);
            // 回溯（backtracking）：标记这一点在当前路径中没有被访问过
            // dfs的常见错误：忘记清空之前dfs的数据
            // 为什么这里不需要对word进行回溯？因为word在这里没有变
            visited[adjX][adjY] = false;
        }
    }

    private boolean inside(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}
