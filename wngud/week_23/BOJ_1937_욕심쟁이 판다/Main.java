import java.util.*;
import java.io.*;

public class Main {
    static int[][] matrix, dp;
    static int N, answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs
        answer = 0;
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] != -1) continue;
                dfs(i, j);
                answer = Math.max(answer, dp[i][j]);
            }
        }

        // 정답 출력하기
        System.out.println(answer);

    }

    public static int dfs(int r, int c) {

        // 1. 이미 값이 있는 경우
        if (dp[r][c] != -1) return dp[r][c]; // 메모이제이션
        dp[r][c] = 1; // 방문 겸 최소값

        for (int m = 0; m < 4; m++) {
            int nr = r + dr[m];
            int nc = c + dc[m];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if (matrix[r][c] < matrix[nr][nc]) {
                // 여기 부분 처리가 어려웠음
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }

        // 2. 값을 채운 경우
        return dp[r][c];

    }

}