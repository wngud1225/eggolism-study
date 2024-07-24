package BOJ_21276_계보복원가호석;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		List<String>[] list = new ArrayList[N];
		List<String>[] graph = new ArrayList[N];
		Map<String, Integer> findByName = new HashMap<>();
		Map<Integer, String> findById = new HashMap<>();
		PriorityQueue<String> pq = new PriorityQueue<>();
		
		// 조상이 자식을 가리키게 인접리스트를 만들고, parentCount를 만든다
		// index로 String, String으로 index를 찾을 수 있게 Map을 2개 써준다
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
			graph[i] = new ArrayList<>();
			String S = sc.next();
			findByName.put(S, i);
			findById.put(i, S);
		}
		
		int M = sc.nextInt();
		int[] parentCount = new int[N];
		for(int i = 0; i < M; i++) {
			String child = sc.next();
			String parent = sc.next();
			parentCount[findByName.get(child)]++;
			list[findByName.get(parent)].add(child);
		}
		
		// 시조들을 우선순위 큐에 넣음
		for(int i = 0; i < N; i++) {
			if(parentCount[i] == 0) {
				pq.offer(findById.get(i));
			}
		}
		
		boolean[] visited = new boolean[N];
		PriorityQueue<String> answer = new PriorityQueue<>();
		System.out.println(pq.size());
		
		// 시조들을 출력
		while(!pq.isEmpty()) {
			String S = pq.poll();
			answer.offer(S);
			visited[findByName.get(S)] = true;
			for(int i = 0; i < list[findByName.get(S)].size(); i++) {
				String now = list[findByName.get(S)].get(i);
				parentCount[findByName.get(now)]--;
				if(!visited[findByName.get(now)] && parentCount[findByName.get(now)] == 0) {
					// 친자식 인접리스트 생성
					graph[findByName.get(S)].add(now);
				}
			}
			System.out.print(S + " ");
		}
		System.out.println();
		
		// 시조들을 제외하고 parentCount가 0인 애들을 우선순위 큐에 넣음
		for(int i = 0; i < N; i++) {
			if(!visited[i] && parentCount[i] == 0) {
				visited[i] = true;
				pq.offer(findById.get(i));
				answer.offer(findById.get(i));
			}
		}
		
		// 모든 계보를 확인, 위상정렬
		while(!pq.isEmpty()) {
			String S = pq.poll();
			for(int i = 0; i < list[findByName.get(S)].size(); i++) {
				String now = list[findByName.get(S)].get(i);
				parentCount[findByName.get(now)]--;
				if(!visited[findByName.get(now)] && parentCount[findByName.get(now)] == 0) {
					visited[findByName.get(now)] = true;
					// 친자식 인접리스트 생성
					graph[findByName.get(S)].add(now);
					pq.offer(now);
					answer.offer(now);
				}
			}
		}
		
		// 형식에 맞게 출력
		while(!answer.isEmpty()) {
			String S = answer.poll();
			System.out.print(S + " " + graph[findByName.get(S)].size() + " ");
			Collections.sort(graph[findByName.get(S)]);
			for(int i = 0; i < graph[findByName.get(S)].size(); i++) {
				System.out.print(graph[findByName.get(S)].get(i) + " ");
			}
			System.out.println();
		}
		
	}

}