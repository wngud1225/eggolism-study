package BOJ_1520_내리막길;

import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, M;
	static int[][] graph, D;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		graph = new int[M][N];
		D = new int[M][N];
		visited = new boolean[M][N];
		for(int r = 0; r < M ; r++) for(int c = 0; c < N; c++) graph[r][c] = sc.nextInt();
			
		System.out.println(dfs(0, 0));
	}
	
	static int dfs(int nowY, int nowX) {
		if(nowY == M-1 && nowX == N-1) return 1;
		
		if(visited[nowY][nowX]) return D[nowY][nowX];
		
		visited[nowY][nowX] = true;
		
		for(int i = 0; i < 4; i++) {
			int moveY = nowY + dr[i];
			int moveX = nowX + dc[i];
			
			if(moveY < 0 || moveY >= M || moveX < 0 || moveX >= N) continue;
			
			if(graph[moveY][moveX] < graph[nowY][nowX]) D[nowY][nowX] += dfs(moveY, moveX);
		}
		return D[nowY][nowX];
		
	}

}