import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String book = sc.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
//        Pattern pattern = Pattern.compile("[a-zA-Z]+"); 匹配字母
//        Pattern pattern = Pattern.compile("\\D+"); 匹配非数字
        Matcher matcher = pattern.matcher(book);
        String[] size = book.split(",");
        int[][] value = new int[size.length / 2][2];
        int i = 0;
        int j = 0;
        while (matcher.find()) {
            String match = matcher.group();
            Integer integer = Integer.valueOf(match);
            value[i][j] = integer;
            if (matcher.find()) {
                String match2 = matcher.group();
                Integer integer2 = Integer.valueOf(match2);
                j++;
                value[i][j] = integer2;
                j = 0;
            }
            i++;
        }
    }
}
