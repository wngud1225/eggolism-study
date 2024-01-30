import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int total = sc.nextInt();
        sc.nextLine();

        // 리스트에 넣기
        List<String> arr = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            String line = sc.nextLine();
            arr.add(line);
        }

        // 리스트 출력
        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
            String[] words =  arr.get(i).split("\\s");

            // 단어 배열 출력
            System.out.print("Case #" + (i+1) + ":");
            for (int j = words.length-1; j >= 0 ; j--) {
                System.out.print(" " + words[j]);
            }
            if (i != arr.size()-1) {
                System.out.println();
            }
        }

    }
}