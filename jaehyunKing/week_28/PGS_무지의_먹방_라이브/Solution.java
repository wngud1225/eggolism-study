package PGS_무지의_먹방_라이브;

import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        
        // pq에 시간 오름차순으로 정렬해서 넣어줌
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < food_times.length; i++){
            pq.add(new Node(i+1, food_times[i]));
        }
        
        int stack = 0; // 전체 순회한 횟수
        // k가 nowTime * pq.size()보다 작을 때까지 반복
        while(!pq.isEmpty()){
            Node tmp = pq.peek();
            // 순회한 것에 대한 계산을 stack에 저장해두고 미뤄놨다가 지금 계산
            // nowTime -> 지금 pq에 들어있는 애들은 최소 nowTime보다 큰 시간을 가지고 있음
            long nowTime = tmp.time - stack; 
            
            if(k < nowTime * pq.size()) break;
            
            k -= nowTime * pq.size();
            stack += nowTime; // 순회한 횟수 더하기
            
            while (!pq.isEmpty() && pq.peek().time - stack == 0) {
                pq.poll(); // time이 0인 것이 있다면 제거
            }
        }
        
        // k가 pq.size()보다 작을 때까지 반복
        // 위랑 동일 로직
        while(!pq.isEmpty()){
            if(k < pq.size()) break;
            
            k -= pq.size();
            stack++;
            
            while(!pq.isEmpty() && pq.peek().time - stack == 0){
                pq.poll();
            }
            
        }
        
        // 남은 k에 대한 처리, 남은 음식 종류보다 k가 작음
        // 배열에 순서에 맞게 넣어줌
        int[] lastFood = new int[food_times.length+1];
        while(!pq.isEmpty()){
            Node temp = pq.poll();
            lastFood[temp.order] = temp.time - stack;
        }
        
        // 순회하면서 k == -1일 때 i를 출력
        // 왜 -1이냐면 0일 때, 다음 꺼를 찾아야하기 때문
        for(int i = 0; i < food_times.length+1; i++){
            if(lastFood[i] > 0) k--;
            if(k == -1) return i;
        }
        
        return -1;
    }
    
    public class Node implements Comparable<Node> {
        int order;
        int time;
        
        Node(int order, int time){
            this.order = order;
            this.time = time;
        }
        
        @Override
        public int compareTo(Node o) { return this.time - o.time; }
    }
}