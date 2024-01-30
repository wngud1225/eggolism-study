import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 1. 가지고 있는 리스트 만들기
        // 리스트로 굳이 할 필요없이 set 이용
        int mine_total = sc.nextInt();

        HashSet<Integer> mine = new HashSet<>();
        for (int i = 0; i < mine_total; i++) {
            int num = sc.nextInt();
            mine.add(num);
        }

//        System.out.println(mine.toString());


        // 2. 체크 리스트
        // set은 get 메서드가 없어서 리스트를 만듦
        int check_total = sc.nextInt();

        List<Integer> check = new ArrayList<>();
        for (int i = 0; i < check_total; i++) {
            int num = sc.nextInt();
            check.add(num);
        }

//        System.out.println(mine.toString());

        // 3. 순회하면서 내가 가지고 있는 것과 비교
        for (int i = 0; i < check.size(); i++) {
            if (mine.contains(check.get(i))) {
                System.out.print(1 + " ");
            } else {
                System.out.print(0 + " ");
            }

        }


    }
}