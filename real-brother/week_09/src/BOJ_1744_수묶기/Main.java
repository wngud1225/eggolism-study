package BOJ_1744_수묶기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static List<Integer> numList = new ArrayList<Integer>();

	public static void main(String[] args) {
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			numList.add(sc.nextInt());
		}
		
		Collections.sort(numList, Collections.reverseOrder());
		
		System.out.println(numList);
	}
}