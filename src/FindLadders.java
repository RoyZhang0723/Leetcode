import java.util.*;

/**
 * 单词接龙II（bfs先进行筛选，然后dfs进行转化）
 * https://www.lintcode.com/problem/121
 */
public class FindLadders {
    /**
     *
     * @param start
     * @param end
     * @param dict
     * @return
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        if (dict == null) {
            return new ArrayList<List<String>>();
        }

        // 起点终点进入dict
        dict.add(start);
        dict.add(end);

        //记录从起点开始，到达某个词的最短路径长度
        Map<String, Integer> distance = new HashMap<String, Integer>();

        // 记录从某一个词开始，可以到达的（不绕远）的下一个词的集合
        Map<String, List<String>> fromToMap = new HashMap<String, List<String>>();

        for (String s : dict) {
            fromToMap.put(s, new ArrayList<String>());
        }

        bfs(fromToMap, distance, start, end, dict);
        // 记录dfs当前路径
        List<String> path = new ArrayList<>();
        // 记录所有最短路径
        List<List<String>> results = new ArrayList<>();
        dfs(results, path, start, end, fromToMap, distance.get(end));
        return results;
    }

    private void bfs(Map<String, List<String>> fromToMap, Map<String, Integer> distance, String start, String end, Set<String> dict) {
        distance.put(start, 0);
        Queue<String> q = new LinkedList<String>();
        q.offer(start);

        while (!q.isEmpty()) {
            String currWord = q.poll();
            List<String> nextWordList = getNextWords(currWord, dict);
            for (String nextWord : nextWordList) {
                // 如果next没出现过，或者在当前层出现过,
                // 那么currWord可以到nextWord
                if (!distance.containsKey(nextWord) || distance.get(nextWord) == (distance.get(currWord) + 1)) {
                    fromToMap.get(currWord).add(nextWord);
                }
                // 如果nextWord没有出现过，
                // 1.记录从起点开始，到达某个词对最短路径长度
                // 2.入队。在下一层中进行遍历nextWord可以到达的点
                if (!distance.containsKey(nextWord)) {
                    distance.put(nextWord, distance.get(currWord) + 1);
                    q.offer(nextWord);
                }
            }
        }
    }

    private void dfs(List<List<String>> results, List<String> path, String currWord, String end, Map<String, List<String>> fromToMap, int minLen) {
        // 如果当前路径已经等于起点到终点的最短路径长度了，没必要继续走下去，直接返回
        if (path.size() == minLen + 1) {
            return;
        }
        path.add(currWord);
        // 已经到达终点，记录path
        if (currWord.equals(end)) {
            // 深拷贝
            results.add(new ArrayList<String>(path));
        }
        // 没有到达终点，继续走下去
        else {
            for (String nextWord : fromToMap.get(currWord)) {
                dfs(results, path, nextWord, end, fromToMap, minLen);
            }
        }
        // 注意：往回走，移除最后一个词
        path.remove(path.size() - 1);
    }

    private static String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    /**
     * 找到可以和word接龙的所有单词
     * 比如 word = 'hot', dict = {'hot', 'hit', 'hog'}, return ['hit', 'hog']
     *
     * @param word
     * @param dict
     * @return
     */
    private static ArrayList<String> getNextWords(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<>();
        // 枚举当前替换字母
        for (char c = 'a'; c <= 'z'; c++) {
            // 枚举当前替换字母
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                // 如果字母替换后的单词存在与dict,加入nextWords
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
}
