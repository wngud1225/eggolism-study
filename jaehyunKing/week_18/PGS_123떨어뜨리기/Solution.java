package PGS_123떨어뜨리기;

/*
    <초기 생각>
	target에 대해서 3으로 나누고 나머지 까지해서 
	최소 몇 번 최대 몇 번에 가능한지 체크

	count배열을 만들어서 해당 노드에 몇 번 방문하는지 체크
	각 노드의 최대값을 넘어가면 -1출력
	이 때 순서를 체크할 배열도 만들어야함

	순서, 방문 횟수에 맞게 작은 수부터 숫자 할당
	
	<실제 구현>
	그래프를 만들고 정렬 -> 정렬을 해야지 간선이 순서에 맞게 바뀐다
*/

import java.util.*;

class Solution {

     public int[] solution(int[][] edges, int[] target) {
        
        // 그래프 만들기
        List<Integer>[] graph = new ArrayList[target.length+1];
        for(int i = 1; i <= target.length; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < edges.length; i++) graph[edges[i][0]].add(edges[i][1]);
        // 그래프를 순서에 맞게 정렬(노드를 바꿔줄 때 순서대로 돌아가게 하기 위해서)
        for(int i = 1; i < graph.length; i++) Collections.sort(graph[i]);
        
        // target에 대해서 3으로 나누고 나머지까지 더해서 최소 몇 번, 최대 몇 번에 가능한지 체크
        int[] minCount = new int[target.length+1];
        int[] maxCount = new int[target.length+1];
        
        for(int i = 0; i < target.length; i++){
            int tmp = 0;
            if(target[i] % 3 == 0) tmp = 0;
            else tmp = 1;
            minCount[i+1] = target[i] / 3 + tmp;
            maxCount[i+1] = target[i];
        }
        
        // count배열에 해당 리프 노드에 몇 번 방문하는지 체크
        int[] change = new int[target.length+1];
        int[] count = new int[target.length+1];
        List<Integer> order = new ArrayList<>();
        loop: while(true){
            int now = 1;
            while(graph[now].size() != 0){
                int temp = graph[now].get(change[now]);
                // 간선 변경
                change[now]++;
                if(change[now] == graph[now].size()) change[now] = 0;
                now = temp;
            }
        
            count[now]++;
            order.add(now);
            // maxCount보다 현재 카운트가 크면 만들 수 없으므로 -1
            if(maxCount[now] < count[now]) {
                int[] answer = {-1};
                return answer;
            }
            // 모든 target을 만들 수 있게 되면 바로 빠져나옴(가장 적은 숫자)
            for(int i = 1; i <= target.length; i++){
                if(count[i] < minCount[i]) break;
                if(i == target.length) break loop;
            }
        }
        
        int[] answer = new int[order.size()];
        // 1, 2, 3 순서대로 가능하면 그 자리에 1 또는 2 또는 3을 넣음
        for(int i = 0; i < order.size(); i++){
            int now = order.get(i);
            for(int j = 1; j <= 3; j++){
                int temp = target[now-1] - j;
                int temp2 = 0;
                if(temp % 3 == 0) temp2 = 0;
                else temp2 = 1;
                // 남은 횟수가 최소 횟수(temp / 3 + temp2) 이상이면 가능
                if(count[now] - 1 >= temp / 3 + temp2) {
                	answer[i] = j;
                	target[now-1] -= j;
                	count[now]--;
                	break;
                }
            }
        }
        
        return answer;
    }
}