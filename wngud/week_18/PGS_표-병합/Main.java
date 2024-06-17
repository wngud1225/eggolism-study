import java.util.*;

public class Main {
    /**
     * 설계 방식
     * 1. 정방향으로 이동
     * 2. 역방향으로 이동
     * 3. 전부 방문하게 된다면 순서를 알 수 있는 것이다.
     * (뭔가 위상정렬 느낌이 나긴하지만 그냥 그렇다)
     */

    /**
     * 디버깅
     * 1. count 초기값 1 또는 처음에 ++하고 시작
     * 2. bfs 실제 숫자 탐색 (i=0부터 시작하면 안됨)
     */

    static int N, M, count;
    static int[] visited;
    static List<ArrayList<Integer>> graph, inverseGraph;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        graph = new ArrayList<>();
        inverseGraph = new ArrayList<>();


        for (int i = 0; i <= N; i++) { // 실제 숫자
            graph.add(new ArrayList<>());
            inverseGraph.add(new ArrayList<>());
        }

        // 입력받기
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            graph.get(from).add(to);
            inverseGraph.get(to).add(from);
        }

        // 순회하기
        int answer = 0;
        for (int i = 1; i <= N; i++) { // 실제 숫자
            visited = new int[N+1];
            count = 1;
            bfs(i, graph);
            bfs(i, inverseGraph);

            // 평가하기
            if (count == N) {
                answer++;
            }
//            System.out.println(Arrays.toString(visited));
//            System.out.println(count);
        }

        // 정답 출력하기
        System.out.println(answer);
    }

    public static void bfs(int start, List<ArrayList<Integer>> searchGraph) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < searchGraph.get(cur).size(); i++) {
                int target = searchGraph.get(cur).get(i);

                if (visited[target] == 1) continue;
                queue.add(target);
                visited[target] = 1;
                count++;
            }
        }
    }

}
