package BOJ_1937_욕심쟁이판다;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int[][][] graph;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static PriorityQueue<Node> pq;
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		pq = new PriorityQueue<>();
		graph = new int[n][n][2];
		// graph0에 대나무 양을 저장, graph1을 1로 초기화
		// 우선순위 큐에 모든 좌표 다 넣어줌
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				graph[r][c][0] = sc.nextInt();
				graph[r][c][1] = 1;
				pq.offer(new Node(r, c ,graph[r][c][0]));
			}
		}
		
		bfs();
		
		int max = 0;
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(max < graph[r][c][1]) max = graph[r][c][1];
			}
		}
		System.out.println(max);
	}
	
	static void bfs() {
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int max = 0;
			for(int i = 0; i < 4; i++) {
				int moveY = node.y + dr[i];
				int moveX = node.x + dc[i];
				// 대나무 양이 현재 칸 보다 많아야함
				if(0 <= moveY && moveY < n && 0 <= moveX && moveX < n && graph[moveY][moveX][0] > node.size) {
					if(max < graph[moveY][moveX][1]) max = graph[moveY][moveX][1];
				}
			}
			// 4방향 중 graph1이 가장 큰 값을 더해줌
			graph[node.y][node.x][1] += max;
		}
	}
	
	static class Node implements Comparable<Node>{
		int y;
		int x;
		int size;

		public Node(int y, int x, int size) {
			this.y = y;
			this.x = x;
			this.size = size;
		}

		@Override
		public int compareTo(Node o) {
			return o.size - this.size;
		}
		
	}
	
}