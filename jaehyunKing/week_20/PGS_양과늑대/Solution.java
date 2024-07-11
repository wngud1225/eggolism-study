package PGS_양과늑대;

import java.util.*;

class Solution {
    int[] sel;
    int answer;
    int size;
    int[] infos;
    List<Integer>[] graph;
    
    public int solution(int[] info, int[][] edges) {
        size = info.length;
        infos = info;
        sel = new int[size];
        graph = new ArrayList[size];
        for(int i = 0; i < size; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < edges.length; i++) graph[edges[i][0]].add(edges[i][1]);
        answer = 0;
        sel[0] = 1;
        perm(1);
        return answer;
    }
    
    public void perm(int idx){
        if(idx == size){
            int count = 0;
            for(int i = 0; i < size; i++) if(sel[i] == 1 && infos[i] == 0) count++;
            if(count > answer) {
                if(bfs(0)) answer = count;
            }
            return;
        }
        
        sel[idx] = 1;
        perm(idx+1);
        
        sel[idx] = 0;
        perm(idx+1);
    }
    
    public boolean bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[size];
        visited[start] = true;
        queue.offer(start);
        while(queue.isEmpty()){
            int num = queue.poll();
            for(int i = 0; i < graph[num].size(); i++){
                int next = graph[num].get(i);
                if(sel[next] == 1 && !visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        
        for(int i = 0; i < size; i++) if(!(sel[i] == 1 && visited[i])) return false;
        return true;
        
    }
}