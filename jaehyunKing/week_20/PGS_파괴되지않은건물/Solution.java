package PGS_파괴되지않은건물;

class Solution {
    int[][] sum;
    int N, M;
    
    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        sum = new int[N+1][M+1];
        for(int i = 0; i < skill.length; i++){
            int[] oneSkill = skill[i];
            int degree = oneSkill[5] * (oneSkill[0] == 1 ? -1 : 1);
            
            sum[oneSkill[1]][oneSkill[2]] += degree;
            sum[oneSkill[1]][oneSkill[4]+1] += (degree * -1);
            sum[oneSkill[3]+1][oneSkill[2]] += (degree * -1);
            sum[oneSkill[3]+1][oneSkill[4]+1] += degree;
        }
        
        for (int r = 1; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sum[r][c] += sum[r-1][c];
            }
        }
        
        for (int c = 1; c < M; c++) {
            for (int r = 0; r < N; r++) {
                sum[r][c] += sum[r][c-1];
            }
        }
        
        int answer = 0;
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                if(board[r][c] + sum[r][c] > 0) answer++;
            }
        }
        return answer;
    }
}