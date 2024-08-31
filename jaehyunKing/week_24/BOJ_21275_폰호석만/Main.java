package BOJ_21275_폰호석만;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String num1 = sc.next();
		String num2 = sc.next();
		long answer = 0;
		int count = 0;
		int radix1 = 0;
		int radix2 = 0;
		
		for(int i = 2; i <= 36; i++) {
			for(int j = 2; j <= 36; j++) {
				if(i == j) continue;
				try {
					long tmp1 = Long.parseLong(num1, i);
					long tmp2 = Long.parseLong(num2, j);
					if(tmp1 == tmp2) {
						count++;
						answer = tmp1;
						radix1 = i;
						radix2 = j;
					}
				} 
				catch(Exception e) {
					continue;
				}
			}
		}
		
		if(count == 0) System.out.println("Impossible");
		else if(count == 1) System.out.println(answer + " " + radix1 + " " + radix2);
		else System.out.println("Multiple");
	}

}
