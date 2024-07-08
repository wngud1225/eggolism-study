import java.util.*;

public class Main {

    static int[][] matrix, visited, checked;
    static int N, M, answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;
            }
        }

        // dfs
        answer = 0;
        visited = new int[N][M];
        checked = new int[N][M];

        visited[0][0] = 1;
        dfs(0, 0, matrix[0][0]);

        // 정답 출력하기
        System.out.println(answer);
    }

    public static  void dfs(int r, int c, int prev) {

        if (r == N-1 && c == M-1) {
            answer++;

            // visted 반영
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] == 0) continue;
                    checked[i][j] = visited[i][j];
                }
            }
    
            return;
        }

        for (int m = 0; m < 4; m++) {
            int nr = r + dr[m];
            int nc = c + dc[m];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if (matrix[nr][nc] >= prev) continue;

            // 이전에 길이 있던 경우
            if (checked[nr][nc] == 1) {
                answer++;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (visited[i][j] == 0) continue;
                        checked[i][j] = visited[i][j];
                    }
                }

                continue;
            }

            visited[nr][nc] = 1;
            dfs(nr, nc, matrix[nr][nc]);
            visited[nr][nc] = 0;
        }

        

    }
}