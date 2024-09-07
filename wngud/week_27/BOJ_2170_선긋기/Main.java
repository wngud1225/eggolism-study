import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
        int pos, status;

        public Node (int pos, int status) {
            this.pos = pos;
            this.status = status; // 시작 or 끝
        }

        @Override
        public int compareTo(Node o) {
            return this.pos - o.pos;
        }
    }

    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

//        int N = sc.nextInt();
        int N = Integer.parseInt(st.nextToken());
        List<Node> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
//            int num1 = sc.nextInt();
//            int num2 = sc.nextInt();
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            Node begin = new Node(num1, 0);
            Node end = new Node(num2, 1);
            arr.add(begin);
            arr.add(end);
        }

        Collections.sort(arr);

        // 계산하기
        Long answer = 0L; // 조심
        int beginCnt = 0;
        int endCnt = 0;
        long startPos = Long.MIN_VALUE;

        for (int i = 0; i < N*2; i++) {
            Node cur = arr.get(i);

            if (cur.status == 0) beginCnt++;
            else endCnt++;

            if (startPos == Long.MIN_VALUE) {
                startPos = cur.pos;
            }

            // 평가 -> 한단위 끝
            if (beginCnt == endCnt) {
                answer += cur.pos - startPos;
                startPos = Long.MIN_VALUE; // 다음 노드 길이로 변경하기 위한 플래그
            }
        }

        // 정답 출력하기
        System.out.println(answer);

    }
}
