import java.util.*;

/**
 * 单词接龙：实际上也是一个经典的BFS问题
 * https://www.lintcode.com/problem/120
 */
public class LadderLength {
    public static int ladderLength(String start, String end, Set<String> dict) {
        // 假设dict不为null
        // 假设 beginWord 和 endWord 是非空的，且二者不相同

        // 必须加入end,可以加入start
        dict.add(end);
        HashSet<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        visited.add(start);

        // 记录最短路线长度，起始长度为1
        int length = 1;
        while (!queue.isEmpty()) {
            // 到下一层（不是当前层）的长度
            length++;
            // 当前层有size个元素
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                // 得到下一步的单词
                for (String nextWord: getNextWords(word, dict)) {
                    if (visited.contains(nextWord)) {
                        continue;
                    }
                    // 如果下一层的词为尾词，直接返回当前到下一层（不是当前层）的长度
                    if (nextWord.equals(end)) {
                        return length;
                    }
                    // 加入下一层，为后面BFS做准备
                    visited.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }
        return 0;
    }

    private static String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    /**
     * 找到可以和word接龙的所有单词
     * 比如 word = 'hot', dict = {'hot', 'hit', 'hog'}, return ['hit', 'hog']
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
