package BOJ_3055_탈출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R, C, targetY, targetX, count;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] graph;
	static int[][] visited;
	static Queue<int[]> queue;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		graph = new char[R][C];
		Queue<int[]> tempQueue = new LinkedList<>();
		queue = new LinkedList<>();
		visited = new int[R][C];
		
		// 고슴도치 1, 물 2
		// y좌표, x좌표, 무엇인지, 이동 횟수(고슴도치만 있음)
		for(int r = 0; r < R; r++) {
			String S = sc.next();
			for(int c = 0; c < C; c++) {
				graph[r][c] = S.charAt(c);
				if(graph[r][c] == 'S') {
					queue.add(new int[] {r, c, 1, 0});
					visited[r][c] = 1;
				}
				else if(graph[r][c] == '*') {
					tempQueue.add(new int[] {r, c, 2});
					visited[r][c] = 2;
				}
			}
		}
		
		// 고슴도치가 먼저 이동하고 그다음에 물이 이동하게 하기 위함
		while (!tempQueue.isEmpty()) queue.add(tempQueue.poll());
		
		count = -1;
		bfs();
		
		// 목적지에 도착하지 못했다면
		if(count == -1) System.out.println("KAKTUS");
		else System.out.println(count);

	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0];
			int c = now[1];
			int what = now[2];
			
			// 현재가 고슴도치인데 현재 visited가 고슴도치가 아니면 물로 덮인 것이므로 continue
			if(what == 1 && visited[r][c] != 1) continue;
			
			for(int i = 0; i < 4; i++) {
				int moveY = r + dr[i];
				int moveX = c + dc[i];
				
				// index 범위 내에 있고, 벽이 아니라면
				if(0 <= moveY && moveY < R && 0 <= moveX && moveX < C && graph[moveY][moveX] != 'X') {
					// 고슴도치
					if(what == 1) {
						// 목적지에 도착하면
						if(graph[moveY][moveX] == 'D') {
							count = now[3]+1;
							return;
						}
						// 고슴도치는 방문하지 않은 곳만 이동 가능, 원래 물이었던 곳도 다 방문 처리 해둠
						else if(visited[moveY][moveX] == 0) {
							queue.add(new int[] {moveY, moveX, what, now[3] + 1});
						}
					// 물	
					} else {
						// 물은 D에 갈 수 없고, 방문하지 않은 곳이나 고슴도치가 방문한 곳으로 이동
						if(graph[moveY][moveX] != 'D' && (visited[moveY][moveX] == 0 || visited[moveY][moveX] == 1)) {
							queue.add(new int[] {moveY, moveX, what});
						}
					}
					// 방문처리
					visited[moveY][moveX] = what;
				}
			}
			
		}
	}

}
