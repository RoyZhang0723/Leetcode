public class test {
    public static void main(String[] args) {
       String s = "hello";
       StringBuilder sb = new StringBuilder(s);
       sb.setCharAt(2, 'n');
       s = sb.toString();
       System.out.println(s);
    }
}
