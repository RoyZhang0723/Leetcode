import java.util.*;

public class XuanXiuKe {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            //得到输入数据
            String[] one = scanner.nextLine().split(";");
            String[] two = scanner.nextLine().split(";");
            Map<String, Integer> tIds = new HashMap<>();
            for (String t : two) {
                String[] tStu = t.split(",");
                String tId = tStu[0];
                int tScore = Integer.parseInt(tStu[1]);
                tIds.put(tId, tScore);
            }
            Comparator<Student> studentComparator = Comparator.comparingInt(Student::getScore).thenComparing(Student::getId);
            TreeMap<String, TreeSet<Student>> map = new TreeMap<>();
            for (String s : one) {
                String[] sStu = s.split(",");
                String sId = sStu[0];
                if (tIds.containsKey(sId)) {
                    int sScore = Integer.parseInt(sStu[1]);
                    int tScore = tIds.get(sId);
                    int totalScore = sScore + tScore;
                    String cls = sId.substring(0, 5);
                    Student student = new Student(sId, totalScore);
                    map.computeIfAbsent(cls, k -> new TreeSet<>(studentComparator)).add(student);
                }
            }
            if (map.isEmpty()) {
                System.out.println("NULL");
            } else {
                map.forEach((key, value) -> {
                    System.out.println(key);
                    String res = String.join(";", value.stream().map(Student::getId).toArray(String[]::new));
                    System.out.println(res);
                });
            }
        }
    }
    static class Student {
        String id;
        int score;
        public Student(String id, int score) {
            this.id = id;
            this.score = score;
        }

        public String getId() {
            return id;
        }

        public int getScore() {
            return score;
        }
    }
}
