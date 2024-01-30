import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int total = sc.nextInt();

        // 배열에 넣기
        List<Integer> arr = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            int num = sc.nextInt();
            arr.add(num);
        }

        // 확인
        int count = 0;
        int sight = 0;
        for (int i = total-1; i >= 0; i--) {
            if (arr.get(i) > sight) {
                sight = arr.get(i);
                count++;
            }

        }

        System.out.println(count);

    }
}