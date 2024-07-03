import java.util.*;

class Solution {
    public int[] answer, info, visited;
    public int N, maxCount;
    
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        maxCount = -1;
        this.info = info;
        N = n;
        
        // dfs
        visited = new int[11];
        dfs(0, 0);
        
        // 실패시 -> 최신화 안됨
        if (maxCount == -1) {
            return new int[]{-1};
        } 
        return answer;
    }
    
    public void dfs(int idx, int cnt) {
        
        // 배열 끝까지 돌고 화살 전부 사용해야 함
        if (idx == 11 && cnt == N) {
            int enemyScore = 0;
            int myScore = 0;
            
            // 점수 계산
            for (int i = 0; i < 11; i++) {
                if (info[i] == 0 && visited[i] == 0) {
                    continue;
                } else if (info[i] >= visited[i]) enemyScore += 10-i;
                else myScore += 10-i;
            }
                
            // 평가
            int diff = myScore - enemyScore;
            if (diff > 0 && maxCount < diff) {
                maxCount = diff;
                answer = visited.clone(); // 복사
            } else if (diff > 0 && maxCount == diff) { // 하드 코딩
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] < visited[i]) {
                        answer = visited.clone();
                        return;
                    }
                    else if (answer[i] > visited[i]) return;
                }
            }
            
            return;
        }
        
        if (idx == 11) return; // 위를 && 해서 넣어주기
        
        
        // 1) 둘다 0으로 점수 받는 경우
        if (info[idx] == 0) {
            dfs(idx+1, cnt);
        }
        
        // 2) 어피치한테 지는 경우 -> 지는 모든 숫자 넣어보기
        // 이기는 경우는 최소한의 숫자만 넣어야 다른 곳에 쓰일 수 있어서 +1만, 지는 경우는 여러가지 가능
        if(info[idx] != 0) {
            for (int i = 0; i <= info[idx]; i++) {
                visited[idx] = i;
                dfs(idx+1, cnt+i);
                visited[idx] = 0;
            }
        }
        
        // 3) 어피치한테 이기는 경우 -> 1로만 이기는 것이 이득
        // 총 화살을 넘기면 안됨 -> 위에서 return 해도 되긴 함
        if(cnt+(info[idx] +1) <= N) {
            visited[idx] = info[idx] + 1;
            dfs(idx+1, cnt+(info[idx] +1));
            visited[idx] = 0;
        }
        
        
    }
    
}