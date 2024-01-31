package Simulation;

import java.util.Scanner;

public class BOJ_Solution_10810 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];	// 0으로 초기값 가지고 있어서 안넣어줘도 되나?
				
		for(int a=0; a<m; a++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			int k = sc.nextInt();
			
			for(int b=(i-1); b<j; b++) {
				arr[b] = k;
			}
		}
		
		for(int x : arr) {
			System.out.print(x+" ");
		}

	}

}
