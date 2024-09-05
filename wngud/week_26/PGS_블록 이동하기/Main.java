import java.util.*;

class Solution {
    
    // 왼쪽 또는 위쪽만 기준으로 저장
    class Node {
        public int r;
        public int c;
        public int dir; // 0은 가로, 1은 세로
        public int count;
        
        Node(int r, int c, int dir, int count) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.count = count;
        }
    }
    
    // 일반
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    // 가로
    int[] dr0 = {-1, 0, 0, -1};
    int[] dc0 = {1, 1, 0, 0};
    int[] dr0_check = {-1, 1, 1, -1};
    int[] dc0_check = {0, 0, 1, 1};
    // 세로
    int[] dr1 = {0, 1, 1, 0};
    int[] dc1 = {0, 0, -1, -1};
    int[] dr1_check = {1, 0, 0, 1};
    int[] dc1_check = {1, 1, -1, -1};
    
    int answer;
    
    public int solution(int[][] board) {
        
        answer = Integer.MAX_VALUE;
        bfs(board.length, board);
        
        return answer;
    }
    
    public void bfs(int N, int[][] board) {
        
        int[][][] visited = new int[2][N][N]; // 방향 정보도 추가한다.
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = 1;
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // System.out.println(cur.r + " " + cur.c + " 방향 - " + cur.dir);
            
            // 평가
            if (cur.dir == 0 && cur.r == N-1 && cur.c+1 == N-1) {
                // System.out.println("통과!! " + cur.count);
                answer = Math.min(answer, cur.count);
                return;
            } else if (cur.dir == 1 && (cur.r+1 == N-1 && cur.c == N-1)) {
                // System.out.println("통과!! " + cur.count);
                answer = Math.min(answer, cur.count);
                return;
            }
            
            // 1) 일반적인 움직임
            for (int m = 0; m < 4; m++) {
                int nr = cur.r + dr[m];
                int nc = cur.c + dc[m];
                
                if (!isAvail(nr, nc, cur.dir, N, visited, board)) continue;
                queue.add(new Node(nr, nc, cur.dir, cur.count+1));
                visited[cur.dir][nr][nc] = 1;
            }
            
            // 2) 회전하는 움직임
            for (int m = 0; m < 4; m++) {
                // 가로인 경우
                if (cur.dir == 0) {
                    int nr = cur.r + dr0[m];
                    int nc = cur.c + dc0[m];
                    int nDir = 1;
                    
                    if (!isAvail(nr, nc, nDir, N, visited, board)) continue;
                    
                    // 추가 벽 조사
                    int nnr = cur.r + dr0_check[m];
                    int nnc = cur.c + dc0_check[m];
                    if (board[nnr][nnc] == 0) {
                        visited[nDir][nr][nc] = 1;
                        queue.add(new Node(nr, nc, nDir, cur.count+1));
                    }
                }
                
                // 세로인 경우
                else {
                    int nr = cur.r + dr1[m];
                    int nc = cur.c + dc1[m];
                    int nDir = 0;
                    
                    if (!isAvail(nr, nc, nDir, N, visited, board)) continue;
                    
                    // 추가 벽 조사
                    int nnr = cur.r + dr1_check[m];
                    int nnc = cur.c + dc1_check[m];
                    if (board[nnr][nnc] == 0) {
                        visited[nDir][nr][nc] = 1;
                        queue.add(new Node(nr, nc, nDir, cur.count+1));
                    }
                }
            }
            
        }
        
        
    }
    
    public boolean isAvail(int r, int c, int dir, int N, int[][][] visited, int[][] board) {
        
        // 경계선 여부
        if (r < 0 || r >= N || c < 0 || c >= N) return false;
        
        // 방문 여부
        if (visited[dir][r][c] == 1) return false;

        // 벽 여부 -> 방향에 따라 2개를 모두 고려해야 한다. -> 디버깅: 경계조건 조심 (c+1, r+1 추가)
        if (dir == 0) {
            if (c+1 >= N || board[r][c] == 1 || board[r][c+1] == 1) return false;
        } else {
            if (r+1 >= N || board[r][c] == 1 || board[r+1][c] == 1) return false;
        }
        return true;
    }
    
}