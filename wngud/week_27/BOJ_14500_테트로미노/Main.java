import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, M;
    public static int answer, total;
    public static int[][] matrix;
    public static int[][] visited;

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static int[][] ddr = {{-1, 0, 0}, {-1, 1, 0}, {0, 0, 1}, {1, -1, 0}};
    public static int[][] ddc = {{0, -1, 1}, {0, 0, 1}, {-1, 1, 0}, {0, 0, -1}};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs
        answer = 0;
        visited = new int[N][M]; // 시간초과 조심
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = 1; // 디버깅: 들어갈 때도 넣어줘야 함
                total = matrix[i][j]; // 디버깅: 미리 넣어주기
                dfs(i, j, 1);
                visited[i][j] = 0;
            }
        }

        // 예외 -> 십자가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // visited = new int[N][M]; // 해당 방식 제거함
                dfsCross(i, j);
            }
        }

        // 정답 출력하기
        System.out.println(answer);


    }

    public static void dfsCross(int r, int c) {

        for (int dir = 0; dir < 4; dir++) {
            // 하나의 경우 시작
            int cnt = 0;
            total = matrix[r][c];
            for (int m = 0; m < 3; m++) { // 디버깅: 굳이 4개 안하고 3개만
                int nr = r + ddr[dir][m];
                int nc = c + ddc[dir][m];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;
                cnt++;
                total += matrix[nr][nc];
            }
            if (cnt == 3) {
                answer = Math.max(total, answer);
            }
        }
    }

    public static void dfs(int r, int c, int cnt) {

        if (cnt == 4) {
            answer = Math.max(total, answer);
            return;
        }

        for (int m = 0; m < 4; m++) {
            int nr = r + dr[m];
            int nc = c + dc[m];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] == 1) continue;

            visited[nr][nc] = 1;
            total += matrix[nr][nc];
            dfs(nr, nc, cnt + 1);
            visited[nr][nc] = 0;
            total -= matrix[nr][nc];
        }

    }
}
