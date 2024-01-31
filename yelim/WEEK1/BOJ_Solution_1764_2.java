package Simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_1764_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		
		HashSet<String> set = new HashSet<>();
		
		List<String> result = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			set.add(sc.nextLine());
		}
		
		for(int i=0; i<m; i++) {
			String s = sc.nextLine();
			if (set.contains(s)) {
				result.add(s);
			}
		}
		
		Collections.sort(result);
		
		System.out.println(result.size());
		for(String s : result) {
			System.out.println(s);
		}

	}

}
