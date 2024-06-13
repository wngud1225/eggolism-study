import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int Test = sc.nextInt();
        sc.nextLine();

        for (int t = 0; t < Test; t++) {

            // 입력 받기
            String func = sc.nextLine();

            int N = sc.nextInt();
            sc.nextLine();

            // 배열 입력 처리
            String InputArr = sc.nextLine();
            InputArr = InputArr.substring(1, InputArr.length() - 1); // 문자열 자르기
            String[] InputArrSplit = InputArr.split(",");

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(InputArrSplit[i]));
            }

//            System.out.println(list.toString());

            // 작업 진행
            int cur = 0;
            int errorFlag = 0;

            for (int i = 0; i < func.length(); i++) {
                char play = func.charAt(i);

                // R
                if (play == 'R') {
                    if (cur == 0) cur = list.size() - 1;
                    else if (cur == list.size() - 1) cur = 0;
                }

                // D
                else if (play == 'D') {
                    // 실패의 경우
                    if (list.size() == 0) {
                        errorFlag = 1;
                        break;
                    }

                    list.remove(cur);
                    if (cur != 0) cur = list.size() - 1; // 사이즈 최신화
                }
            }

            // 정답 출력하기
            StringBuilder sb = new StringBuilder();
            if (errorFlag == 1) {
                sb.append("error");
            } else {
                // 배열 만들어서 출력하기
                sb.append("[");
                if (cur == 0) {
                    for (int i = 0; i < list.size(); i++) {
                        sb.append(list.get(i));
                        if (i != list.size() - 1) sb.append(",");
                    }
                } else {
                    for (int i = list.size() - 1; i >= 0; i--) {
                        sb.append(list.get(i));
                        if (i != 0) sb.append(",");
                    }
                }
                sb.append("]");
            }

            System.out.println(sb);
        }
    }
}
