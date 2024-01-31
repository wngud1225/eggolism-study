package Simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_1541 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = Integer.MAX_VALUE;
		String s = sc.nextLine();
		
		String[] arr = s.split("-");
		
		for(int i=0; i<arr.length; i++) {
			int temp = 0;
			
			String[] add = arr[i].split("\\+");
			
			
			for (int j=0; j<add.length; j++) {
				temp += Integer.parseInt(add[j]);
			}
			
			// 초기값 확인
			if (sum == Integer.MAX_VALUE) {
				sum = temp;
			}
			else {
				sum -= temp;
			}
		}
		
		System.out.println(sum);
		
		
	}

}
