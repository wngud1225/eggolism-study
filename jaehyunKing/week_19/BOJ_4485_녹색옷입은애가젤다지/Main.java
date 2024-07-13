package BOJ_4485_녹색옷입은애가젤다지;

/*
 *  bfs를 사용해서 해결
 *  다른 건 딱히 없고, visited 조건을 이동할 곳의 visited 값이
 *  현재 visited값과 + 도둑루피의 값보다 클 때 이동할 수 있게 했다. 
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] graph;
	static int[][] visited;
	static int result, N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int idx = 0;
		while(true) {
			N = sc.nextInt();
			if(N == 0) break;
			idx++;
			
			graph = new int[N][N];
			visited = new int[N][N];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					graph[r][c] = sc.nextInt();
					visited[r][c] = Integer.MAX_VALUE;
				}
			}
			
			bfs(0, 0);
			System.out.println("Problem " + idx + ": " + visited[N-1][N-1]);
		}

	}
	
	// 평범한 bfs -> visited 조건만 현재 visited값과 + 도둑루피의 값보다 클 때 이동할 수 있게 했다
	static void bfs(int startY, int startX) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{startY, startX});
		visited[startY][startX] = graph[startY][startX];
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i = 0; i < 4; i++) {
				int moveY = now[0] + dr[i];
				int moveX = now[1] + dc[i];
				if(0 <= moveY && moveY < N && 0 <= moveX && moveX < N) {
					if(visited[moveY][moveX] > visited[now[0]][now[1]] + graph[moveY][moveX]) {
						visited[moveY][moveX] = visited[now[0]][now[1]] + graph[moveY][moveX];
						queue.offer(new int[] {moveY, moveX});
					}
				}
			}
		}
	}

}