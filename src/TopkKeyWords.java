import java.util.*;

/**
 *
 * 给定一个评论列表reviews，一个关键字列表 keywords 以及一个整数k。
 * 找出在不同评论中出现次数最多的前k个关键词，这k个关键词按照出现次数的由多到少来排序。
 * 字符串不区分大小写，如果关键字在不同评论中出现的次数相等，请按字母顺序从小到大排序。
 * https://www.lintcode.com/problem/1883/description
 */
public class TopkKeyWords {
    /**
     * 先统计每个关键词在评论中出现的次数，然后实际上就是转化为找最大的K个数，这时候只要用一个最大堆每次去根结点就可以了
     * @param K
     * @param keywords
     * @param reviews
     * @return
     */
    public static List<String> TopkKeywords(int K, String[] keywords, String[] reviews) {
        // write your code here
        List<String> ansList = new ArrayList<>();
        if (K >= keywords.length) {
            K = keywords.length;
        }
        int[] count = new int[keywords.length];
        int k = K;
        Queue<Integer> maxHeap = new PriorityQueue<>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        Map<Integer, List<Integer>> seq = new HashMap<>();
        int feq = 0;
        for (int i = 0; i < keywords.length; i++) {
            for (int j = 0; j < reviews.length; j++) {
                if (reviews[j].toLowerCase().contains(keywords[i])) {
                    count[i]++;
                }
            }
        }
        for (int i = 0; i < count.length; i++) {
            if (seq.containsKey(count[i])) {
                seq.get(count[i]).add(i);
            } else {
                seq.put(count[i], new ArrayList<>());
                seq.get(count[i]).add(i);
            }
        }
        for (int i = 0; i < count.length; i++) {
            maxHeap.add(count[i]);
        }
        while (K > 0 && ansList.size() < k) {
            feq = maxHeap.peek();
            while (!maxHeap.isEmpty() && maxHeap.peek() == feq) {
                maxHeap.remove();
            }
            if (seq.get(feq).size() > 1) {
                K = addCharacter(ansList, seq.get(feq), keywords, K);
            } else {
                ansList.add(keywords[seq.get(feq).get(0)]);
                K--;
            }
        }
        return ansList;
    }

    public static int addCharacter(List<String> ansList, List<Integer> indexList, String[] keywords, int K) {
        String[] ans = new String[indexList.size()];
        for (int i = 0; i < indexList.size(); i++) {
            ans[i] = keywords[indexList.get(i)];
        }
        Arrays.sort(ans);
        for (int i = 0; i < indexList.size() && K > 0; i++) {
            ansList.add(ans[i]);
            K--;
        }
        return K;
    }

    public static void main(String[] args) {
//        String[] keyWords = {"anacell", "cetracular", "betacellular"};
//        String[] reviews = {"Anacell provides the best services in the city",
//                "betacellular has awesome services",
//                "Best services provided by anacell, everyone should use anacell"};
//        System.out.println("cnacell".compareTo("ketracular"));
        String[] keyWords = {"mmebli","zooi","jolbc","akos","dhu","hcchht","dyemw","xzadxn","vel","duop","bkm","omlm","udhxve","nnhotj","qvp","tba","cmbyu","bdpig","msxgg","tbd","erkcq","nowrp","epide","nqc","ndqsq","gnaq","olqmrm","zuwt","qng","wqq"};
        String[] reviews = {"Aenmn jhErW QBMHX cdUgIS AZbftI JfHIj Rycql MGB Okho dBhq xdgs",
                "EJuWr MXGAV KzxAt Tcjie Tcjie okho ojwxF Psowdc JcRfHz dWsdV OgefPG XZl taha THfE","rycql IjVdH uzigon aenmn ZUSuR ykDd" +
                "L Amyvbh IwXk oNa iEToBR aenmn aenmn Tcjie mhuakb mgCBsb mhuakb",
                "largh rycql ktPNg GKUbI iBbDWJ uzigon uFTtnV Cbqj boO LvjZD AdKx","Zeooj ajRBFF LBcgC okho tcjie aenmn UbcOC fWWK HLBky TXs lWDch"};
        List<String> ansList = TopkKeywords(6, keyWords, reviews);
        for (int i = 0; i < ansList.size(); i++) {
            System.out.println(ansList.get(i));
        }
//        Arrays.sort(keyWords);
//        for (int i = 0; i < keyWords.length; i++) {
//            System.out.println(keyWords[i]);
//        }
    }
}
