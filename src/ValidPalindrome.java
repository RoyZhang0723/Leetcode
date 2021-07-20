/**
 *
 * 给一个非空字符串 s，你最多可以删除一个字符。判断是否可以把它变成回文串。
 * https://www.lintcode.com/problem/891/
 */
public class ValidPalindrome {
    /**
     * 相当于给了一次错误机会，那就按照忽视左边的点和忽视右边的点两种情况分别来一次
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public static boolean validPalindrome(String s) {
        // write your code here
        if (s.length() == 0 || s.length() == 1) return true;
        int left = 0, right = s.length() - 1;
        int falseNumber = 1;
        while (right - left > 1) {
            if (s.charAt(left) != s.charAt(right)) {
                falseNumber--;
                if (falseNumber >= 0) {
                    return isValid(s, left + 1, right) || isValid(s, left, right - 1);
                } else {
                    return false;
                }
            } else {
                left++;
                right--;
            }
        }
        if (right - left == 1) {
            if (s.charAt(left) == s.charAt(right) || falseNumber >= 1) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


    public static boolean isValid(String s, int left, int right) {
        while (right - left > 1) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        if (right - left == 1) {
            return s.charAt(left) == s.charAt(right);
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "cuppucu";
//        System.out.println(isPalindrome(s));
        System.out.println(validPalindrome(s));
    }
}
