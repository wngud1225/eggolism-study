package BOJ_7579_앱;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 최대 비용 N * 100
		int[][] D = new int[N+1][N * 100 + 1];
		
		// 0 -> 메모리, 1 -> 비용
		int[][] app = new int[N][2];

		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < N; j++) {
				app[j][i] = sc.nextInt();
			}
		}
		
		for(int r = 1; r < N+1; r++) {
			for(int c = 0; c < N * 100 + 1; c++) {
				// 현재 비용 c + 현재 앱의 비용 (app[r-1][1])이 최대 비용 내에 있다면
				// 해당 비용 칸의 메모리와 (현재 칸의 메모리 + 현재 앱의 메모리) 중에 큰 값을 넣어준다. 
				if(c + app[r-1][1] <= N * 100) D[r][c + app[r-1][1]] = Math.max(D[r-1][c] + app[r-1][0], D[r-1][c + app[r-1][1]]);
				
				// 이전 칸과 현재 칸 중에 더 큰 메모리를 사용
				D[r][c] = Math.max(D[r][c], D[r-1][c]);
			}
		}
		
		// 행 우선순회(비용이 작은 것부터 확인)를 하면서 요구하는 메모리보다 큰 메모리가 나오면 반환
		// 해당 메모리 이상을 확보하는 최소 비용이 나옴
		loop : for(int c = 0; c < N * 100 + 1; c++) {
			for(int r = 1; r < N + 1; r++) {
				if(D[r][c] >= M) {
					System.out.println(c);
					break loop;
				}
			}
		}

	}

}
