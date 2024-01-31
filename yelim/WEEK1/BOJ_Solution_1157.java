package Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_1157 {
	// 메모리 초과...

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String s = sc.nextLine();
		s = s.toLowerCase();
		char[] arr = s.toCharArray();
		List<Integer> cntList = new ArrayList<>();
		int cnt = 0;
				
		for(int i=0; i<arr.length; i++) {
			cntList.add(0);
			cnt = 0;
			for(int j=0; j<arr.length; j++) {
				if (arr[i] == arr[j]) {
					cntList.set(i, ++cnt);
				}
			}
		}
		
		int max = 0;
		for(int i=0; i<cntList.size(); i++) {
			if (cntList.get(i) > max) {
				max = cntList.get(i);
			}
		}
		
		char c = 0;
		boolean b = false;
		
		for(int i=0; i<cntList.size(); i++) {
			if (cntList.get(i) == max) {
				if (c == 0) {
					c = arr[i];
				}
				else if (c == arr[i]) {
					c = arr[i];
					b = true;
				}
				else {
					b = false;
					break;
				}
			}
		}
		
		if (b) System.out.println(Character.toUpperCase(c));
		else System.out.println("?");
		
	}

}
