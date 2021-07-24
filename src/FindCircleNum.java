import java.util.LinkedList;
import java.util.Queue;

/**
 * 一个班中有N 个学生。他们中的一些是朋友，一些不是。他们的关系传递。
 * 例如，如果A是B的一个直接朋友，而B是C的一个直接朋友，那么A是C的一个间接朋友。我们定义朋友圈为一组直接和间接朋友。
 * 给出一个N*N 矩阵M表示一个班中学生的关系。如果m〔i〕〔J〕＝1，那么第i个学生和第j个学生是直接朋友，否则不是。你要输出朋友圈的数量。
 * https://www.lintcode.com/problem/1179/description
 */
public class FindCircleNum {
    public static int findCircleNum(int[][] M) {
        int ansBfs = beginBfs(M);
        return ansBfs;
    }

    public static int beginBfs(int[][] M) {
        //人数
        int n = M.length;
        //答案
        int ans = 0;
        //标记是否访问的数组
        boolean[] visisted = new boolean[n];

        for (int i = 0; i < n; i++) {
            visisted[i] = false;
        }
        //遍历每个人，如果这个人还没访问过，就从这个人开始做一遍bfs
        for (int i = 0; i < n; i++) {
            if (visisted[i] == false) {
                ans += 1;
                //将起始点压入队列并标记已经访问
                visisted[i] = true;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                while (queue.isEmpty() == false) {
                    //取出队首
                    int now = queue.poll();
                    //从队首找朋友
                    for (int j = 0; j < n; j++) {
                        //找到新朋友（之前没访问过的朋友）就标记访问并压入队列
                        if (visisted[j] == false && M[now][j] == 1) {
                            visisted[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
