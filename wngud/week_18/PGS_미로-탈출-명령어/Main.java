import java.util.*;

class Solution {
    
    int n, m, x, y, r, c, k;
    
    List<String> answers;
    int[][] visited;
    String record;
    
    int[] dr = {1, 0, 0, -1}; // 하좌우상
    int[] dc = {0, -1, 1, 0};
    char[] charArr = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        // 세팅
        this.n = n;
        this.m = m;
        this.x = x-1; // 조심
        this.y = y-1;
        this.r = r-1;
        this.c = c-1;
        this.k = k;
        
        // 최소값 판별 -> 최소거리보다 k가 작거나 둘의 차가 2의 배수가 아니면 불가능
        int min = Math.abs(x-r) + Math.abs(y-c);
        if (k < min || (k-min) % 2 != 0) return "impossible";
        
        // dfs
        answers = new ArrayList<>();
        record = "";
        
        dfs(x-1, y-1, 0); // 초기값 조심, 파라미터 우선순위
        
        // 정답 출력하기  
        return answers.get(0);
      
    }
    
    
    public void dfs(int mr, int mc, int mk) {
        
        // 목표 거리 k를 넘어가면 out
        if (mk > k) return;
        
        // 사전순으로 하나가 구해지면 out
        if (answers.size() >= 1) return;
        
        // 거리 너무 멀어져도 out!
        if (Math.abs(mr - r) + Math.abs(mc - c) > (k - mk)) return;
        
        // 도착
        if (mk == k && mr == r && mc == c) {
            // System.out.println(record);
            answers.add(record);
            
            return; // 빼먹지마! -> 목표 거리 k를 넘어가면 out이라 사소하긴 함
        }
        
        // 이동
        for (int i = 0; i < 4; i++) {
            int nr = mr + dr[i];
            int nc = mc + dc[i];
            
            // 경계선 넘어가면 무효
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                continue; // return하면 안됨!
            }
            
            record += charArr[i];
            dfs(nr, nc, mk+1);
            
            // 초기화
            record = record.substring(0, record.length()-1); // visited 취소 느낌 -> 이게 가능하네
        }
        
    }
    
}