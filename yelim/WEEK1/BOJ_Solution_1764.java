package Simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_1764 {
	// 시간초과..

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		
		String[] nArr = new String[n];
		String[] mArr = new String[m];
		
		for(int i=0; i<n; i++) {
			nArr[i] = sc.nextLine();
		}
		for(int i=0; i<m; i++) {
			mArr[i] = sc.nextLine();
		}
		
		List<String> result = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if (nArr[i].equals(mArr[j])) {
					result.add(nArr[i]);
				}
			}
		}
		
		Collections.sort(result);
		
		System.out.println(result.size());
		for(int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

}
