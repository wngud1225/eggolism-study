package BOJ_2156_포도주시식;

/*
 *  입력 받으면서 진행
 *  D 배열 -> 이번 꺼를 안 골랐을 때, 이번 꺼를 골라서 연속 1개, 2개로 구분
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] D = new int[3];
		
		for(int i = 0; i < n; i++) {
			int num = sc.nextInt();
			int temp = D[0];
			// D[0]는 이전 D[0], D[1], D[2] 중 가장 큰 갑을 선택
			D[0] = Math.max(D[0], Math.max(D[1], D[2]));
			// D[2]는 이전 D[1]값에 현재 입력받은 숫자를 더 함 
			D[2] = D[1] + num;
			// D[1]는 이전 D[0]값에 현재 입력받은 숫자를 더 함
			D[1] = temp + num;
		}
		
		int max = 0;
		for(int i = 0; i < 3; i++) if(max < D[i]) max = D[i];
		System.out.println(max);
	}

}