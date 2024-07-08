import java.util.*;

public class Main {

    static int[][] matrix, dp;
    static int N, M, answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        // matrix 입력, dp 초기화 -1 -> 메모제이션 유무 파악
        matrix = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;
                dp[i][j] = -1;
            }
        }
        
        // dfs
        dfs(0, 0);

        // 정답 출력하기
        System.out.println(dp[0][0]);
    }


    public static int dfs(int r, int c) {

        // 목적지 도착
        if (r == N-1 && c == M-1) return 1;

        // 1) 이미 방문한 경우
        if (dp[r][c] != -1) return dp[r][c];


        // 2) 방문하지 않은 경우 (-1)
        dp[r][c] = 0; // 방문체크

        for (int m = 0; m < 4; m++) {
            int nr = r + dr[m];
            int nc = c + dc[m];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if (matrix[r][c] <= matrix[nr][nc]) continue;

            // 현재 값이 더 큰 경우
            dp[r][c] += dfs(nr, nc); // 재귀
        }

        return dp[r][c];
    }
}