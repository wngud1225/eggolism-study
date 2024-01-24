package package01;

import java.util.Scanner;

public class Switch {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];		// 스위치 상태 배열
		
		// 스위치 상태 입력받기
		for (int i=0; i<arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		int students = sc.nextInt();
		
		
		// 학생 성별, 수 입력받기
		for (int i=0; i<students; i++) {
			int gender = sc.nextInt();
			int number = sc.nextInt();
			
			// 남학생
			if (gender == 1) {
				for (int j=0; j<n; j++) {
					if ((j+1)%number == 0) {		
						arr[j] = arr[j]==0 ? 1 : 0; 
					}
				}
				
			}
			
			// 여학생
			if (gender == 2) {
				arr[number-1] = arr[number-1]==0 ? 1 : 0;
				for (int j=1; j<=n/2; j++) {
					if (number-j-1 < 0 || number+j-1 >= n) {
						break;
					}
					if (arr[number-j-1] == arr[number+j-1]) {
						arr[number-j-1] = arr[number-j-1]==0 ? 1 : 0;
						arr[number+j-1] = arr[number+j-1]==0 ? 1 : 0;
					}
					else break;
				}
			}
			
		}
		
		for (int i=0; i<n; i++) {
			System.out.print(arr[i] + " ");
			if ((i+1)%20 == 0) {
				System.out.println();
			}
		}
	}
}