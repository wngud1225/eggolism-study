package PGS_미로탈출명령어;

import java.util.*;

//  bfs를 사용, 그냥 하면 시간초과가 나서 visited를 사용 visited는 count를 기준으로 했다.
 
class Solution {
    int[] dr = {1, 0, 0, -1};
    int[] dc = {0, -1, 1, 0};
    String[] dir = {"d", "l", "r", "u"};
    String answer = "";
    boolean[][][] visited;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
    	// 이동 가능한 거리보다 시작 지점과 종점 사이의 거리가 더 클 때 impossible
        if(Math.abs(x - r) + Math.abs(y - c) > k) answer = "impossible";
        
        if(answer.equals("")) bfs(n, m, x, y, r, c, k);
        if(answer.equals("")) answer = "impossible";
        return answer;
    }
    
    public void bfs(int n, int m, int x, int y, int r, int c, int k){
        visited = new boolean[n+1][m+1][k+1];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y, 0, ""));
        while(!queue.isEmpty()){
            Node node = queue.poll();
            // 종점에 도착하고, count == k이면 끝 -> 바로 탈출
            if(node.nowY == r && node.nowX == c && node.count == k) {
                answer = node.route;
                return;
            }
            // count가 k를 넘으면 탈출 -> impossible
            else if(node.count > k) return;
            // 중간중간에 조건을 만족할 수 없는 경우의 수는 커팅
            if (Math.abs(node.nowY - r) + Math.abs(node.nowX - c) > k - node.count) continue;
            
            for(int i = 0; i < 4; i++){
                int moveY = node.nowY + dr[i];
                int moveX = node.nowX + dc[i];
                //visited를 현재 위치, count로 표시
                if(1 <= moveY && moveY <= n && 1 <= moveX && moveX <= m && !visited[moveY][moveX][node.count]){
                    visited[moveY][moveX][node.count] = true;
                    queue.offer(new Node(moveY, moveX, node.count+1, node.route + dir[i]));
                }
            }
        }
    }
    
    public class Node{
        int nowY;
        int nowX;
        int count;
        String route;
        
        public Node(int nowY, int nowX, int count, String route){
            this.nowY = nowY;
            this.nowX = nowX;
            this.count = count;
            this.route = route;
        }
    }
}