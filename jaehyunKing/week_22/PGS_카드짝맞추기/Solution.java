package PGS_카드짝맞추기;

import java.util.*;
class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int[][] graph, temp;
    List<Integer> list;
    int[] sel;
    boolean[] visited;
    int min;
    
    public int solution(int[][] board, int r, int c) {
        graph = board;
        list = new ArrayList<>();
        visited = new boolean[7];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(graph[i][j] != 0 && !visited[graph[i][j]]){
                    visited[graph[i][j]] = true;
                    list.add(graph[i][j]);
                }
            }
        }
        visited = new boolean[list.size()];
        sel = new int[list.size()];
        temp =  new int[4][4];
        min = Integer.MAX_VALUE;
        perm(0, r, c);
        return min;
    }
    
    // 순열 -> 어떤 카드를 먼저 맞출 지 결정
    public void perm(int idx, int r, int c){
        if(idx == list.size()){
            for(int i = 0; i < 4; i++) for(int j = 0; j < 4; j++) temp[i][j] = graph[i][j];
            int startY = r;
            int startX = c;
            int sum = 0;
            for(int i = 0; i < sel.length; i++){
	            // 선택한 숫자를 2개 지워야함
                for(int j = 0; j < 2; j++){
                    Node node = bfs(startY, startX, list.get(sel[i]));
                    startY = node.y;
                    startX = node.x;
                    sum += node.count;
                }
            }
            if(min > sum) min = sum;
            return;
        }
        
        for(int i = 0; i < list.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                sel[idx] = i;
                perm(idx+1, r, c);
                visited[i] = false;
            }
        }
    }
    
    public Node bfs(int startY, int startX, int num){
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        queue.offer(new Node(startY, startX, 0));
        visited[startY][startX] = true;
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            // 내가 원하는 곳에 도달하면 return
            if(temp[node.y][node.x] == num){
                temp[node.y][node.x] = 0;
                node.count++;
                return node;
            }
            
            for(int i = 0; i < 4; i++){
                int moveY = node.y + dr[i];
                int moveX = node.x + dc[i];
                if(0 <= moveY && moveY < 4 && 0 <= moveX && moveX < 4){
                    if(!visited[moveY][moveX]){
                        visited[moveY][moveX] = true;
                        queue.offer(new Node(moveY, moveX, node.count+1));
                    }
                }
                
                while(true){
                    
                    if(!(0 <= moveY && moveY < 4 && 0 <= moveX && moveX < 4)) {
                        moveY -= dr[i];
                        moveX -= dc[i];
                        break;    
                    }
                    if(temp[moveY][moveX] != 0) break;
                    moveY += dr[i];
                    moveX += dc[i];
                }
                
                if(!visited[moveY][moveX]){
                    visited[moveY][moveX] = true;
                    queue.offer(new Node(moveY, moveX, node.count+1));
                }
            }
            
        }  
        return null;
        
    }
    
    public class Node{
        int y;
        int x;
        int count;
        
        Node(int y, int x, int count){
            this.y = y;
            this.x = x;
            this.count = count;
        }
        
    }
}