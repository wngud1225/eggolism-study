class Solution {
    public int solution(int m, int n, String[] board) {
        
        int answer = 0;
        int R = m;
        int C = n;
        int[] dr = {0, -1, -1};
        int[] dc = {-1, -1, 0};
        
        char[][] matrix = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                matrix[i][j] = board[i].charAt(j); 
            }
        }
        
        int[][] visited = new int[R][C];
        int count = -1;
        
        while (count != 0) {
            count = 0;
            
            // 1. 한번 찾기
            for (int r = 1; r < R; r++) { // 인덱스 조심
                for (int c = 1; c < C; c++) {

                    // 4블록
                    char target = matrix[r][c];
                    if (target == '.') continue;
                    boolean is_ok = true;
                    for (int s = 0; s < 3; s++) {
                        int nr = r + dr[s];
                        int nc = c + dc[s];
                        if (target != matrix[nr][nc] || matrix[nr][nc] == '.') {
                            is_ok = false;
                            break;
                        }
                    }

                    // 없애기 -> 바로 없애면 공통 부분 평가가 안됨
                    if (is_ok) {
                        visited[r][c] = 1;
                        for (int s = 0; s < 3; s++) {
                            int nr = r + dr[s];
                            int nc = c + dc[s];
                            visited[nr][nc] = 1;
                        }
                    }

                    // 한번 끝
                }
            }

            // 2. 한번 없애기
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (visited[r][c] == 0) continue;
                    matrix[r][c] = '.';
                    count++;
                    visited[r][c] = 0; // 초기화
                }
            }

            // 3. 한번 내리기
            for (int r = R-2; r >= 0; r--) { // 아래서부터 + 1칸 위
                for (int c = 0; c < C; c++) {
                    if (matrix[r+1][c] != '.' || matrix[r][c] == '.') continue; // 디버깅
                    char target = matrix[r][c];
                    matrix[r][c] = '.';
                    
                    int now = r;
                    while (now < R) {
                        if (matrix[now][c] != '.') break;
                        now++;
                    }
                    if (now > 0) {
                        matrix[now - 1][c] = target;
                    }
                }
            }
            
            // 전체 끝
            //  for (int i = 0; i < R; i++) {
            //     for (int j = 0; j < C; j++) {
            //         System.out.print(matrix[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            
            // System.out.println(">>> " + count);
            if (count == 0) break;
            answer += count;
        }
        
        
        return answer;
    }
}