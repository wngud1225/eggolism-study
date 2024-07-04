import java.util.*;

public class Main {
    /**
     * 쉬운 추측
     * 1. BFS/DFS -> 단순한 것은 시간 초과
     * 2. DP -> visited를 못써서 한계점
     * -> 이거 소프티어 2번 문제랑 비슷한건가?
     *
     * 설계 방식 - 다익스트라
     * 1, 양의 거리에서 최단 경로를 구하는 문제 (from to)
     * 2. 매 상황에서 가장 비용이 적은 노드를 선택하는 그리드 알고리즘 -> 길찾기 문제
     * 3. 최악으로 테이블을 만들어 놓고,
     * 4. 우선순위 큐를 활용하여 현재 노드 vs. 최악 테이블 비교하여 최신화
     * 5. 기존 인접 그래프와 다르게, BFS로 4방향 탐색으로 단순화되어 있음
     *
     * 6. 최신화는 모두 되고, 그 다음 탐색 노드는 거리가 짧은 것이 된다!
     */

    static int N;
    static int[][] map, dist, visited;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int Test = 1;

        while (true) {
            N = sc.nextInt();
            if (N == 0) break;

            map = new int[N][N];
            dist = new int[N][N];
            visited = new int[N][N]; // 또 가는 것은 무조건 손해, 무한 방지

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = sc.nextInt();
                    map[i][j] = num;
                    dist[i][j] = 987654321; // 1. 무한대 초기화
                }
            }

            // 다익스트라
            dijkstra();

            // 정답 출력
            sb.append("Problem ").append(Test++).append(": ").append(dist[N-1][N-1]).append("\n");
        }

        System.out.println(sb);


    }

    // 다익스크라
    public static void dijkstra() {

        // 2. 우선순위 큐 생성
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0, map[0][0])); // 초기값 넣어줘야 함

        // 초기값 설정
        dist[0][0] = map[0][0];
        visited[0][0] = 1;

        // 3. 반복
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 4개 가보기 -> 인접 그래프에서는 연결되어 있는 모든 것 (방문 기록과 최신화)
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] == 1) continue;

                // 이전에 저장된 거리 vs. 현재 노드를 통해 가는 거리
                int cost = cur.weight + map[nr][nc];
                if (cost < dist[nr][nc]) { // 현재 노드를 거쳐가는게 좋음
                    dist[nr][nc] = cost;
                    visited[nr][nc] = 1;
                    queue.add(new Node(nr, nc, cost));
                }

            }

        } // while 끝

    }

    // 노드 클래스
    public static class Node implements Comparable<Node> {
        int r;
        int c;
        int weight;

        public Node(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        // 조심!
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
