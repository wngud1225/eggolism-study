package BOJ_2458_키순서;

/*
 * 단방향 그래프를 정방향 역방향으로 2개 만든다
 * 하나의 학생에 대해서 정방향 그래프, 역방향 그래프를 모두 bfs로 탐색
 * 정방향, 역방향 그래프 둘 중 하나라도 방문 처리가 되어있다면 크기 비교가 가능
 * 다르 모든 학생들에 대해서 방문 처리가 되어있다면 count++
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, count;
	static List<Integer>[] graph;
	static List<Integer>[] reverseGraph;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		
		// 단방향 그래프를 정방향과 역방향으로 하나씩 만듬
		graph = new ArrayList[N+1];
		reverseGraph = new ArrayList[N+1];
		for(int i = 0; i < N+1; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int temp1 = sc.nextInt();
			int temp2 = sc.nextInt();
			graph[temp1].add(temp2);
			reverseGraph[temp2].add(temp1);
		}
		
		
		for(int i = 1; i < N+1; i++) bfs(i);
		System.out.println(count);

	}
	
	// 정방향으로 한 번 탐색
	// 역방향으로 한 번 탐색
	// 2개의 visited를 모두 만족하지 않는다면 비교 불가
	static void bfs(int now) {
		boolean[] visited = new boolean[N+1];
		boolean[] reverseVisited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(now);
		visited[now] = true;
		while(!queue.isEmpty()) {
			int num = queue.poll();
			for(int i = 0; i < graph[num].size(); i++) {
				int next = graph[num].get(i);
				if(!visited[next]) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
		
		queue.offer(now);
		reverseVisited[now] = true;
		while(!queue.isEmpty()) {
			int num = queue.poll();
			for(int i = 0; i < reverseGraph[num].size(); i++) {
				int next = reverseGraph[num].get(i);
				if(!reverseVisited[next]) {
					reverseVisited[next] = true;
					queue.offer(next);
				}
			}
		}

		for(int i = 1; i < N+1; i++) {
			if(!visited[i] && !reverseVisited[i]) {
				return;
			}
		}
		count++;
		
	}

}