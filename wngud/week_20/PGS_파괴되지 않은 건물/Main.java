class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        // 변화 매트릭스 만들기
        int[][] diff = new int[board.length+1][board[0].length+1]; // +1
        
        for (int i = 0; i < skill.length; i++) {
            int status = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            if (status == 1) degree *= -1; // 반대
            
            diff[r1][c1] += degree;
            diff[r2+1][c2+1] += degree; // 경계값 조심 -> +1
            diff[r1][c2+1] -= degree;
            diff[r2+1][c1] -= degree;
        }
        
        // 변화 반영하기 -> 굳이 반영 안하고 누적합 테이블로만 만들기
        // 아래쪽
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                diff[i][j] += diff[i-1][j];
            }
        }
        
        // 오른쪽
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                diff[i][j] += diff[i][j-1];
            }
        }
        
        // 평가
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + diff[i][j] > 0) answer++;
            }
        }
        
        
        return answer;
    }
}