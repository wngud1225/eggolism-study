public class Main {

    static int[][] matrix;
    static int[][] visited;

    static int R, C;
    static int answer;
    static boolean complete;

    static int[] dr = {1, 0, -1}; // 하중상
    static int[] dc = {1, 1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new int[R][C];
        for (int i = 0; i < R; i++) {
            String inputs = br.readLine();
            for (int j = 0; j < C; j++) {
                char input = inputs.charAt(j);
                if (input == '.') {
                    matrix[i][j] = 0;
                } else if (input == 'x') {
                    matrix[i][j] = 1;
                }
            }
        }

        // 아래서부터 파이프라인 돌리기
        answer = 1;

        visited = new int[R][C];
        for (int i = R-1; i >= 0; i--) {
            complete = false;
            dfs(i, 0);
        }

        // 정답 출력하기
        System.out.println(answer-1);

    }


    public static void dfs(int r, int c) {

        // 도달한 이후 나머지는 실행하지 않기
        if (complete) {
            visited[r][c] = 0;
            return;
        }

        // 끝까지 도달한 경우
        if (c == C - 1) {
            answer++;
            complete = true;
            return;
        }

        for (int i = 0; i < 3; i++) {

            int nr = r + dr[i];
            int nc = c + dc[i];

            // 벽과 이전에 방문했으면 통과 불과
            if (nr < 0 || nr >= R || nc < 0 || nc >= C ||
                    matrix[nr][nc] == 1 || visited[nr][nc] != 0) continue;

            visited[nr][nc] = answer;
            dfs(nr, nc);
            // 가장 먼저 성공하면 visited 초기화 못하도록 설정
//            if (!complete) {
//                visited[nr][nc] = 0;
//            }

        }
    }


}