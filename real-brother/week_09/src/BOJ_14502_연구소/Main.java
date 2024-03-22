package BOJ_14502_연구소;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int m;
	static int maxSize = Integer.MIN_VALUE;
	static int[][] originLaboratory;
	static int[][] testLaboratory;
	static boolean[] visited;
	static boolean[][] labVisited;
	static boolean[][] safeVisited;
	static int[] combination;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<Integer> empty; // 빈칸 좌표를 1차원배열로 변환하여 저장
	
	public static void main(String[] args) {
		n = sc.nextInt();
		m = sc.nextInt();
		
		combination = new int[3];
		originLaboratory = new int[n][m];
		testLaboratory = new int[n][m];
		empty = new ArrayList<Integer>();
		labVisited = new boolean[n][m];
		safeVisited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				originLaboratory[i][j] = sc.nextInt();
				if (originLaboratory[i][j] == 0) empty.add(i * m + j);
			}
		}
		visited = new boolean[empty.size()];
		
		for (int i = 0; i < originLaboratory.length; i++) {
		    testLaboratory[i] = new int[originLaboratory[i].length];
		    System.arraycopy(originLaboratory[i], 0, testLaboratory[i], 0, originLaboratory[i].length);
		}
		// 빈칸중 3개를 조합으로 골라서
		generateCombinations(0, 0);
		
		System.out.println(maxSize);
		
	}
	
	// 백트래킹을 활용한 조합 구하기
	private static void generateCombinations(int start, int depth) {
	    if (depth == 3) {
	        spreadVirus();
	        for (int i = 0; i < originLaboratory.length; i++) {
			    testLaboratory[i] = new int[originLaboratory[i].length];
			    System.arraycopy(originLaboratory[i], 0, testLaboratory[i], 0, originLaboratory[i].length);
			}
	        return;
	    }

	    for (int i = start; i < empty.size(); i++) {
	        if (!visited[i]) {
	            visited[i] = true;
	            combination[depth] = empty.get(i);
	            generateCombinations(i + 1, depth + 1);
	            visited[i] = false;
	        }
	    }
	}

	// 바이러스 퍼뜨리기
	private static void spreadVirus() {

		labVisited = new boolean[n][m];
		safeVisited = new boolean[n][m];
		// 벽 세워주기
		for (int comb : combination) {
			int r = comb / m;
			int c = comb % m;
			testLaboratory[r][c] = 1;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (testLaboratory[i][j] == 2 && !labVisited[i][j]) {
					bfs(i * m + j);
				}
			}
		}
		
		
		// 안전영역 크기들 구해서 -> 최대값 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (testLaboratory[i][j] == 0 && !safeVisited[i][j]) {
					int safeSize = getSafeSize(i * n + j);
					if (safeSize > maxSize) {
						maxSize = safeSize;
						System.out.println(safeSize);
						for (int k = 0; k < n; k++) {
							for (int l = 0; l < m; l++) {
								System.out.print(testLaboratory[k][l] + " ");
							}
							System.out.println();
						}
					}
				}
			}
		}
	}

	private static void bfs(int index) {
		int startR = index / m;
		int startC = index % m;
		int[] start = {startR, startC};
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(start);
		labVisited[startR][startC] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nr = now[0];
			int nc = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextR = nr + dr[i];
				int nextC = nc + dc[i];
				
				if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) continue;
				if (testLaboratory[nextR][nextC] == 0 && !labVisited[nextR][nextC]) {
					testLaboratory[nextR][nextC] = 2;
					labVisited[nextR][nextC] = true;
					queue.add(new int[] {nextR, nextC});
				}
				
			}
		}
	}
	
	private static int getSafeSize(int index) {
		int size = 1;
		int startR = index / m;
		int startC = index % m;
		int[] start = {startR, startC};
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(start);
		safeVisited[startR][startC] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nr = now[0];
			int nc = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextR = nr + dr[i];
				int nextC = nc + dc[i];
				
				if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) continue;
				if (testLaboratory[nextR][nextC] == 0 && !safeVisited[nextR][nextC]) {
					safeVisited[nextR][nextC] = true;
					size++;
					queue.add(new int[] {nextR, nextC});
					
				}
			}
		}
		
		return size;
	}
}