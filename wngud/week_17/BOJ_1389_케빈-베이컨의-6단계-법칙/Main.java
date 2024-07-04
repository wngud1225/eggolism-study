import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<ArrayList<Integer>> graph;
    static int N, M;
    static int[] answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 양방향 그래프
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // BFS
        answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        // 정답 출력하기
        System.out.println(answer[1]);
    }


    public static void bfs(int x) {

        int point = 0;

        int[] visited = new int[N+1];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{x, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < graph.get(cur[0]).size(); i++) {
                int num = graph.get(cur[0]).get(i);

                // 방문하지 않은 경우
                if (visited[num] != 1) {
                    queue.add(new int[]{num, cur[1] + 1});
                    visited[num]= 1;

                    // 점수 최신화
                    point += cur[1] + 1;
                }

            }

        }

        // 전부 돌기 끝
//        System.out.println("x: " + x + " point: " + point);
        if (answer[0] > point) {
            answer[0] = point;
            answer[1] = x;
        } else if (answer[0] == point && answer[1] > x) {
            answer[0] = point;
            answer[1] = x;
        }


    }

}
