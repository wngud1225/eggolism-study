import java.util.*;

public class Main {

    static int[][] matrix;
    static int[][][] visited;
    static int N, M, answer;
    static boolean is_avail;
    
    static int[] dr = {-1, 1, 0, 0}; // 하상좌우
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        int startR = sc.nextInt()-1;
        int startC = sc.nextInt()-1;
        int endR = sc.nextInt()-1;
        int endC = sc.nextInt()-1;

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;
            }
        }

        // 다익스트라
        visited = new int[N][M][3]; // 3가지 이동패턴에 맞게 설정!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) {
                    visited[i][j][k] = 987654321;
                }
            }
        }

        is_avail = false;
        dijkstra(startR, startC, endR, endC);

        // 정답 출력하기 -> 셋 중 제일 작은 값
        if (is_avail) System.out.println(Math.min(visited[endR][endC][0], Math.min(visited[endR][endC][1], visited[endR][endC][2])));
        else System.out.println(-1);
        
    }


    public static void dijkstra(int startR, int startC, int endR, int endC) {

        // 어차피 다 돌긴 해야 함 -> 큐로 변경
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2]; // 2번째 인덱스(cost)를 기준
        });

        queue.add(new int[]{startR, startC, 0, 1}); // cost, dist
        visited[startR][startC][1] = 0; // 1번째로 가는 경우

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == endR && cur[1] == endC) is_avail = true; // 가능은 함

            for (int m = 0; m < 4; m++) {
                // 이동 패턴
                if (cur[3] % 3 == 1 && m >= 2) continue;
                if (cur[3] % 3 == 2 && m <= 1) continue; 

                // 새로운 길 시작
                int nr = cur[0] + dr[m];
                int nc = cur[1] + dc[m];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (matrix[nr][nc] == -1) continue; // 벽은 불가능

                // 탐색 -> 짧은 길이로 최신화
                int newPath = cur[2] + matrix[nr][nc];
                int writtenPath = visited[nr][nc][(cur[3]+1) % 3]; // 출발때를 기준으로

                if (newPath < writtenPath) { // 등호 유무? -> 없애야 됨
                    visited[nr][nc][(cur[3]+1) % 3] = newPath;
                    queue.add(new int[] {nr, nc, newPath, (cur[3]+1) % 3}); // 이동 패턴을 위해 BFS처럼 거리 추가해주기
                }

            }

        }

    }
}