import java.util.*;

class Solution {
    ArrayList<Integer>[] graph;
    int[][][] visited;
    int answer;
    
    public int solution(int[] info, int[][] edges) {
        
        // 그래프 채우기
        graph = new ArrayList[17]; // 최대값
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            // 양방향 그래프 -> visited만 걸리지 않으면 계속 탐색
            graph[from].add(to);
            graph[to].add(from);
        }
        
        
        // dfs 호출
        visited = new int[17][18][18]; // 실제 숫자 17까지 가능
        answer = 0;
        dfs(0, 0, 0, info);
        
        // 점수 출력하기
        return answer;
    }
    
    public void dfs(int idx, int sheep, int wolf, int[] info) {
        
        if (visited[idx][sheep][wolf] == 1) return; // visited + 똑같은 상태면X (두번X)
        visited[idx][sheep][wolf] = 1;
        
        // 백업 -> visited 초기화, 노드 상태 초기화
        int backupSheep = sheep;
        int backupWolf = wolf;
        int backupNode = info[idx];
        
        // 노드 방문
        if (info[idx] == 0) sheep++;
        else if (info[idx] == 1) wolf++; // else 쓰면 안됨!
        info[idx] = -1;
        
        // 평가
        if (wolf < sheep) {
            answer = Math.max(answer, sheep);
            
            // 더 이동 가능
            for (int next : graph[idx]) {
                dfs(next, sheep, wolf, info); // 재귀
            }
        }
        
        // 초기화
        visited[idx][backupSheep][backupWolf] = 0;
        info[idx] = backupNode;
        
    }

}