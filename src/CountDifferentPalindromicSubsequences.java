import java.util.Random;
//730 统计不同回文子序列
public class CountDifferentPalindromicSubsequences {
    public static boolean isPalindrome(String[] s) {
        if (s == null) {
            return true;
        }
        if (s.length <= 1) {
            return true;
        } else if (s[s.length - 1] == s[0]) {
            String[] newS = new String[s.length - 2];
            for (int i = 1; i < s.length - 1; i++) {
                newS[i - 1] = s[i];
            }
            return isPalindrome(newS);
        } else {
            return false;
        }
    }

    public static boolean isPalindromeDeleteOne(String[] s) {
        String[] str = new String[s.length - 1];
        int j = 0;
        for (int k = 0; k < s.length - 1; k++) {
            for (int i = 0; i < s.length; i++) {
                if (j != i) {
                    str[j] = s[i];
                    j++;
                }
            }
            if (isPalindrome(str)) {
                return true;
            }
            j = 0;
        }
        return false;
    }

    public static void solution(String S) {
        int len = S.length();
        String[] arrayStr = new String[len];

    }

    public static void main(String[] args) {
//        String[] str = new String[10];
//        Random random = new Random();
//        String[] charact = {"a", "b", "c", "d"};
//        for (int i = 0; i < 10; i++) {
//            str[i] = charact[random.nextInt(4)];
//        }
//        for (String s : str) {
//            System.out.print(s);
//        }
//
//        String[] str1 = {"a", "a", "b", "a", "b", "a"};
//        System.out.println("\n" + isPalindrome(str1));
//        String[] str2 = {"a", "b", "c"};
//        System.out.println("\n" + isPalindromeDeleteOne(str1));
    }

}


