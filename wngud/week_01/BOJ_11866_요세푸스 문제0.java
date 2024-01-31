import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        Queue<Integer> queue = new LinkedList<Integer>();
        StringBuilder result = new StringBuilder(); // 결과 출력 용도

        // 1. 큐 세팅하기
        // N만큼의 숫자를 큐에 넣기
        for(int i = 1; i <= N; i++) {
            queue.offer(i); // offer == 넣기!
        }

        // 2. 큐 실행하기
        result.append("<");
        // 원래는 N개 숫자가 나와야하니 N번 반복
        // 마지막 한 개는 >와 함께 출력하기 위해 N-1까지만 진행
        for(int i = 0; i < N-1; i++){
            // 선택되지 않는 M-1개는 빼고 다시 뒤에 넣기
            for(int j = 0; j < M-1; j++) {
                queue.offer(queue.poll());
            }
            // 선택된 M번째는 빼고 결과에 추가하기
            result.append(queue.poll()+", ");
        }

        result.append(queue.poll()+">"); // 마지막 한 개

        // 3. 결과 출력하기
        System.out.println(result);

        sc.close();
    }
}