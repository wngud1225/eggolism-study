package BOJ_5430_AC;

/*
 *  입력의 배열을 다 쪼개서 진짜 배열에 넣는다
 *  R이 나올 때마다 reverse의 true, false를 바꿔줘서
 *  앞 또는 뒤에서 index 범위를 줄여준다
 *  남은 index 범위만 출력 형식에 맞게 출력해준다.
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		loop: for(int i = 0; i < T; i++) {
			String S = sc.next();
			int n = sc.nextInt();
			String temp = sc.next();
			temp = temp.substring(1, temp.length()-1);
			String[] nums = temp.split(",");
			
			int start = 0;
			int end = nums.length-1;
			boolean reverse = false;
			for(int j = 0; j < S.length(); j++) {
				char order = S.charAt(j);
				if(order == 'R') reverse = !reverse;
				else {
					// 여기서 자꾸 틀려서 그냥 n == 0을 넣어줌 
					// 뭔가 말린듯 
					if(start == end+1 || n == 0) {
						System.out.println("error");
						continue loop;
					}
					else if(reverse) end--;
					else start++;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			if(reverse) {
				for(int j = end; j >= start; j--) {
					sb.append(nums[j]);
					if(j != start) sb.append(",");
				}
			}
			else {
				for(int j = start; j <= end; j++) {
					sb.append(nums[j]);
					if(j != end) sb.append(",");
				}
			}
			sb.append("]");
			
			System.out.println(sb);
		}

	}

}