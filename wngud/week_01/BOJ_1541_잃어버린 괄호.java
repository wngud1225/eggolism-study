import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int sum = 0;

        // - 기준으로 나누기
        List<String> numberList = Arrays.asList(input.split("[\\-]"));


        // -가 있으면 그 다음 -가 있을 때까지 더하기
        for (int i = 0; i < numberList.size(); i++) {
            List<String> calList = Arrays.asList(numberList.get(i).split("[\\+]"));

            // 처음은 무조건 +이고, +끼리 묶음은 더해줘야 함
            if (i == 0) {
                for (int j = 0; j < calList.size(); j++) {
                    sum += Integer.parseInt(calList.get(j));
                }
                // 그 외 나머지는 다 합쳐서 -
            } else {
                for (int j = 0; j < calList.size(); j++) {
                    sum -= Integer.parseInt(calList.get(j));
                }
            }

        }

        // 정답 출력하기
        System.out.println(sum);


    }
}