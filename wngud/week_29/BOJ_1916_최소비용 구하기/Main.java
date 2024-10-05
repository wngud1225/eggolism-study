import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<Edge>[] graph;
    static int[] costs;

    public static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost; // 오름차순
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 양방향 그래프 -> 디버깅: 문제는 단방향 (치명적)
            graph[from].add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int targetFrom = Integer.parseInt(st.nextToken());
        int targetTo = Integer.parseInt(st.nextToken());

        // dijkstra
        costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        dijkstra(targetFrom);

        // 정답 출력하기
        System.out.println(costs[targetTo]);
    }

    public static void dijkstra(int targetFrom) {

        PriorityQueue<Edge> edges = new PriorityQueue<>(); // 디버깅
        int[] visited = new int[N+1];
        edges.add(new Edge(targetFrom, 0)); // 디버깅: 시작표시
        costs[targetFrom] = 0;

        while(!edges.isEmpty()) {
            Edge cur = edges.poll();
            int curNode = cur.to; // 명확하게

            if (visited[curNode] == 1) continue;
            visited[curNode] = 1;

            // 주변 관찰
            for (Edge nxt: graph[curNode]) {
                int newCost = costs[curNode] + nxt.cost;
                if (newCost < costs[nxt.to]) {
                    costs[nxt.to] = newCost;
                    edges.add(new Edge(nxt.to, newCost)); // 갱신된 경우만
                }
            }
        }
    }

}
