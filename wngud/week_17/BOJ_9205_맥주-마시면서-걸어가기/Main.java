import java.util.*;

public class Main {

    /**
     * 설계 방식
     * 1] DFS -> 시간 초과
     * 1. DFS로 1000m 이하인 것으로 계속 이동
     * 2. 모든 경우의 수를 가면서 결국 festival과 1000m 안에만 있으면 됨
     *
     * 2] BFS -> 정답
     * 1. BFS로 1000m 이하인 것으로 계속 이동
     * 2. 모든 경우의 수를 가면서 결국 festival과 1000m 안에만 있으면 됨
     */

    static int N, answer;
    static int[] home, festival, visited;
    static List<int[]> conv;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int Test = sc.nextInt();

        for (int t = 0; t < Test; t++) {

            N = sc.nextInt(); // 편의점의 수

            // 입력 받기
            home = new int[2];
            festival = new int[2];
            conv = new ArrayList<>();

            home[0] = sc.nextInt();
            home[1] = sc.nextInt();

            for (int i = 0; i < N; i++) {
                int inputX = sc.nextInt();
                int inputY = sc.nextInt();

                conv.add(new int[]{inputX, inputY});
            }

            festival[0] = sc.nextInt();
            festival[1] = sc.nextInt();

            // DFS
            answer = 0;
            visited = new int[N];
            bfs(home);

            // 정답 출력하기
            if (answer == 0) {
                System.out.println("sad");
            } else {
                System.out.println("happy");
            }
        }


    }

    public static void bfs(int[] point) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(point);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 판별
            if (distance(cur, festival) <= 1000) {
                answer = 1;
                break;
            }

            for (int i = 0; i < N; i++) {
                if (visited[i] == 1) continue;

                int[] canGo = conv.get(i);
                int dist = distance(canGo, cur);

                if (dist <= 1000 & visited[i] == 0) {
                    queue.add(canGo);
                    visited[i] = 1;
                }
            }
        }

    }

    public static int distance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }
}
