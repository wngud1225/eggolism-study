import java.util.*;

public class Main {

    /**
     * 디버깅
     * 1. is_okay 플래그 잘 못 사용
     * - 한번이라도 변화가 있으면 다음 while문 돌게하는 용도
     * - 이후에 4번 이상 BFS 돌면 없애는 용도로 플래그를 사용
     * - 한 연쇄에 계속 변수를 공유하는 문제 발생
     * - count 변수를 새로 만들어 해결
     *
     * 2. 점들은 제외하고 탐색
     */

    static char[][] matrix;
    static int[][] visited;
    static boolean is_ok;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        matrix = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String input = sc.nextLine();
            for (int j = 0; j < 6; j++) {
                char c = input.charAt(j);
                matrix[i][j] = c;
            }
        }

        // 탐색 시작
        int continuity = 0;

        do {
            // 한번 확인
            visited = new int[12][6];
            is_ok = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (visited[i][j] == 1 || matrix[i][j] == '.') continue; // 점 제외
                    bfs(i, j);
                }
            }

            // 변화 확인
            if (!is_ok) break;

            // 아래서부터!
            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (matrix[i][j] != '.') move(i, j);
                }
            }
            continuity++;

        } while (is_ok);

        // 정답 출력하기
        System.out.println(continuity);

    }

    public static void move(int i, int j) {

        int nr = i+1;
        int nc = j;

        while (true) {
            if (nr >= 12 || matrix[nr][nc] != '.') break;
            nr++;
        }

        // 평가 -> 최소 자기 자기 + 막힌 부분 위
        // 이동이 있다면 변경
        if (nr-1 != i) {
            matrix[nr-1][nc] = matrix[i][j]; // 먼저
            matrix[i][j] = '.';
        }

    }

    public static void bfs(int i, int j) {

        Queue<int[]> queue = new LinkedList<>();
        int[][] check = new int[12][6]; // 한 단위만 검사하는 용도
        int count = 1;

        queue.add(new int[] {i, j, 1});
        visited[i][j] = 1;
        check[i][j] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (count >= 4) is_ok = true; // 하나라도 존재

            for (int k = 0; k < 4; k++) {
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];

                if (nr < 0 || nr >= 12 || nc < 0 || nc >= 6 ||
                        visited[nr][nc] == 1 || matrix[nr][nc] == '.') continue; // 점도 제외!

                if (matrix[cur[0]][cur[1]] == matrix[nr][nc]) {
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = 1;
                    check[nr][nc] = 1;
                    count++;
                }
            }
        }

        // 같은 것들 다 모음
        if (count < 4) return;

        for (int k = 0; k < 12; k++) {
            for (int l = 0; l < 6; l++) {
                if (check[k][l] == 1) {
                    matrix[k][l] = '.'; // 변수명 실수!
                }
            }
        }

    }


}
