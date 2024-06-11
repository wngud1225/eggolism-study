package BOJ_1389_케빈베이컨의6단계법칙;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int N, min, result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		
		list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) list[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list[a].add(b);
			list[b].add(a);
		}
		
		min = Integer.MAX_VALUE;
		result = Integer.MAX_VALUE;
		
		for(int i = 1; i <= N; i++) bfs(i);
		
		System.out.println(result);

	}
	
	static void bfs(int start) {
		visited = new boolean[N+1];
		int count = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {start, 0});
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			// 케빈 베이컨의 수를 계산해준다
			count += num[1];
			for(int i = 0; i < list[num[0]].size(); i++) {
				int next = list[num[0]].get(i);
				if(!visited[next]) {
					visited[next] = true;
					queue.offer(new int[] {next, num[1] + 1});
				}
			}
		}
		
		// 가장 작은 케빈 베이컨의 수를 가지고 있는 사람을 result에 저장
		for(int i = 1; i <= N; i++) 
		if(min > count) {
			min = count;
			result = start;
			
		} else if(min == count && result > start) result = start;
	
	}

}