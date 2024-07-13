package BOJ_11559_PuyoPuyo;

/*
 *  반복문을 돌면서 방문하지 않고 .인 애가 나왔을 때 bfs를 시작
 *  같은 색깔인 애들만 방문해주면서 리스트에 추가해준다.
 *  4칸이상 붙어있다면 리스트를 순회하면서 다 .으로 바꿔준다.
 *  처음 반복문을 다 돌았다면 graph를 갱신해준다
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] graph;
	static boolean[][] visited;
	static boolean change;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		graph = new char[12][6];
		
		for(int r = 0; r < 12; r++) {
			String S = sc.next();
			for(int c = 0; c < 6; c++) {
				graph[r][c] = S.charAt(c);
			}
		}
		
		// count == 연쇄
		int count = 0;
		while(true) {
			change = false;
			visited = new boolean[12][6];
			for(int r = 0; r < 12; r++) {
				for(int c = 0; c < 6; c++) {
					if(graph[r][c] != '.' && !visited[r][c]) {
						visited[r][c] = true;
						bfs(r, c, graph[r][c]);
					}
				}
			}
			// 변화가 없다면 나감(변화 == 뿌요가 터지는 거)
			if(!change) break;
			else count++;
			
			refactor();

		}
		
		System.out.println(count);
		
	}
	
	// 다음 주석까지는 평범한 bfs
	static void bfs(int nowY, int nowX, char color) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {nowY, nowX});
		List<int[]> list = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			list.add(new int[] {now[0], now[1]});
			
			for(int i = 0; i < 4; i++) {
				int moveY = now[0] + dr[i];
				int moveX = now[1] + dc[i];
				if(0 <= moveY && moveY < 12 && 0 <= moveX && moveX < 6) {
					if(!visited[moveY][moveX] && graph[moveY][moveX] == color) {
						visited[moveY][moveX] = true;
						queue.offer(new int[] {moveY, moveX});
					}
				}
			}
		}
		
		// 뿌요가 4개 이상 붙어있다면 다 없애준다
		if(list.size() >= 4) {
			change = true;
			for(int i = 0; i < list.size(); i++) {
				graph[list.get(i)[0]][list.get(i)[1]] = '.';
			}
		}
		
	}
	
	// graph 재구성을 위한 메서드
	static void refactor() {
		for(int c = 0; c < 6; c++) {
			for(int r = 11; r >= 0; r--) {
				if(graph[r][c] == '.') {
					for(int r2 = r-1; r2 >= 0; r2--) {
						if(graph[r2][c] != '.') {
							graph[r][c] = graph[r2][c];
							graph[r2][c] = '.';
							break;
						}
					}
				}
			}
		}
	}
}
