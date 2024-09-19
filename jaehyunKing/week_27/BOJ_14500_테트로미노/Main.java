package BOJ_14500_테트로미노;

import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] graph;
	static boolean[][] visited;
	static int N, M, max;
	static int[] comb = {0, 1, 2, 3};
	static int[] sel = new int[3];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for(int r = 0; r < N; r++) for(int c = 0; c < M; c++) graph[r][c] = sc.nextInt();
		
		max = Integer.MIN_VALUE;
		
		// 시작 지점을 하나하나 완탐
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				dfs(r, c, 1, graph[r][c]);
				combination(r, c, 0, 0);
				visited[r][c] = false;
			}
		}
		
		System.out.println(max);
	}
	
	// 3칸을 이동(시작 count가 1)
	static void dfs(int now_y, int now_x, int count, int sum) {
		if(count == 4) {
			if(max < sum) max = sum;
			return;
		}
		
		visited[now_y][now_x] = true;
		
		for(int i = 0; i < 4; i++) {
			int move_y = now_y + dr[i];
			int move_x = now_x + dc[i];
			
			if(0 <= move_y && move_y < N && 0 <= move_x && move_x < M) {
			// 방문하지 않았다면 이동하고, count에 1 더하고, 현재 칸 숫자를 sum에 더 해줌
				if(!visited[move_y][move_x]) {
					dfs(move_y, move_x, count+1, sum+graph[move_y][move_x]);
					visited[move_y][move_x] = false;
				}
			}
		}
	}
	
	// 조합으로 현재 칸 사방 중 3칸 선택 
	static void combination(int now_y, int now_x, int idx, int idx2) {
		if(idx == 3) {
			int sum = graph[now_y][now_x];
			// 0, 1, 2, 3 중에 3개를 골라서 해당 방향으로 사방 탐색
			for(int i = 0; i < 3; i++) {
				int move_y = now_y + dr[sel[i]];
				int move_x = now_x + dc[sel[i]];
				if(0 <= move_y && move_y < N && 0 <= move_x && move_x < M) sum += graph[move_y][move_x];
			}
			if(max < sum) max = sum;
			return;
		}
		if(idx2 == 4) return;
			
		sel[idx] = comb[idx2]; 
		combination(now_y, now_x, idx+1, idx2+1); // 선택
		
		combination(now_y, now_x, idx, idx2+1); // 미선택
	}
}