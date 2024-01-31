import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 입력 받기
		int total = sc.nextInt();
		sc.nextLine();
		
		/*
		 * 1개의 줄 갯수 세기가 핵심
		 * `(`와 `)`의 갯수를 찾아서 둘의 값 비교 
		 * 단순 개수는 `)(`와 같은 예외 케이스를 판별하지 못함 -> 개수 + 순서가 중요
		 * 
		 * `)`가 `(`보다 갯수가 많아지는 경우가 발생하면 안됨
		 * 만약 그렇다면 `is_false` 실행
		 */
		
		// 전체 케이스
		for (int T = 0; T < total; T++) {
			
			String input = sc.nextLine();
			
			boolean is_false = false;
			int lft = 0;
			int rgt = 0;
			

			// 1개 줄 시작
			for (int i = 0; i < input.length(); i++) {

				if (input.charAt(i) == '(') {
					lft++;
				} else {
					rgt++;
					
					// 예외 판별
					if (rgt > lft) {
						is_false = true;
						break;
					}
				}
			
			}
			
			// 개수가 동일한지 비교
			if (lft == rgt && is_false == false) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			// 1개 줄 끝
			
		}
		
		

	}
}