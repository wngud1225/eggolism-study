package Simulation;

import java.util.Scanner;

public class BOJ_Solution_1157_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String s = sc.nextLine();
		s = s.toLowerCase();
		
		int[] arr = new int[26];
		
		for(int i=0; i<s.length(); i++) {
			arr[s.charAt(i)-97]++;
		}
		
		int max = -1;
		char c = 0;
		
		for(int i=0; i<arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
				c = (char)(i+65); // 대문자로 출력
			}
			else if (arr[i] == max) {
				c = '?';
			}
		}
		
		System.out.println(c);

	}

}
