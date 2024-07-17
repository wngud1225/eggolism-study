class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // 1. 플로이드 워셜 초기화
        int[][] dist = new int[n+1][n+1]; // 실제 숫자
        for (int i = 1; i <= n; i++) { // 실제 숫자
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                dist[i][j] = 87654321; // 987654321 불가!
            }
        }
        
        for (int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            
            // 양방향
            dist[start][end] = cost;
            dist[end][start] = cost;
        }
        
        
        // 2. 플로이드 워셜 작성
        for (int k = 1; k <= n; k++) {
             for (int i = 1; i <= n; i++) {
                  for (int j = 1; j <= n; j++) {
                      dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                  }
             }
        }
        
        // 3. 각각 가능 경우 or 같이 가는 경우(i까지 같이)
        int answer = dist[s][a] + dist[s][b]; // 각각 가능 경우
        for (int i = 1; i <= n; i++) { // i까지 같이 가는 경우
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        
        return answer;
    }

}