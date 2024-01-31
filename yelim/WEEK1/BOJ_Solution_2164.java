package Stack_Queue;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_2164 {
	
	// 시간초과...!!

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		List<Integer> arr = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			arr.add(i, i+1);
		}
		System.out.println(arr);
		
		while (arr.size() > 1) {
			arr.remove(0);
			int temp = arr.get(0);
			arr.remove(0);
			arr.add(arr.size(), temp);
		}
		
		System.out.println(arr.get(0));
		
	}

}
