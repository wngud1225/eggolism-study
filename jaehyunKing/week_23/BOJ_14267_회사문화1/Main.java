package BOJ_14267_회사문화1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static List<Integer>[] graph;
	static int n;
	static int[] count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		int m = sc.nextInt();
		
		graph = new ArrayList[n+1];
		count = new int[n+1];
		for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		// 사장의 상사 -1 버려줌
		sc.nextInt();
		// 단방향 그래프
		for(int i = 2; i <= n; i++) {
			graph[sc.nextInt()].add(i);
		}
		
		// 칭찬 받은 사람에게 칭찬의 수치만큼 기록해둔다
		for(int i = 0; i < m; i++) {
			count[sc.nextInt()] += sc.nextInt();
		}
		
		bfs(1);
		
		for(int i = 1; i <= n; i++) {
			System.out.print(count[i] + " ");
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int i = 0; i < graph[now].size(); i++) {
				int next = graph[now].get(i);
				// bfs를 타고 내려가면서 이전 사람(상사)의 칭찬 수치만큼 더 해준다
				count[next] += count[now];
				queue.offer(next);
			}
		}
	}
}