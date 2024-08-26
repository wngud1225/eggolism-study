import java.util.*;

public class Main {
    static int[][] matrix;
    static int R, C;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        matrix = new int[R][C];

        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int num = sc.nextInt();
                matrix[i][j] = num;
                if (num == 1) total++;
            }
        }

        // BFS 돌리기
        // 0만 queue에 추가 가능
        // 1을 만나면 +1 표시를 해준다. 카운트를 세준다.
        Stack<Integer> stack = new Stack<>();
        int tmp = 0;
        int sum = 0;
        int time = 0;
        while (true) {
            tmp = bfs();
            sum += tmp;
            time++;

            stack.add(tmp);
            if (sum == total) {
                break;
            }
        }

        // 정답 출력
        System.out.println(time);
        System.out.println(stack.peek());


    }

    public static int bfs() {

        int count = 0;

        int[][] visited = new int[R][C];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int m = 0; m < 4; m++) {
                int nr = cur[0] + dr[m];
                int nc = cur[1] + dc[m];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] == 1) continue;

                if (matrix[nr][nc] == 1) {
                    visited[nr][nc] = 1;
                    matrix[nr][nc] = 0; // continue로 이후 넘어가지 않음
                    count++;
                    continue; // 디버깅
                }

                // 1이 아닌 경우 전부 이동
                if (matrix[nr][nc] != 1) {
                    visited[nr][nc] = 1;
                    queue.add(new int[]{nr, nc});
                }

            }
        }

        return count;

    }
}
