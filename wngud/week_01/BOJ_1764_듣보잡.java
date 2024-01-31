import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 입력 받기
		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();
		
		// Set 선언
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		
		// N 넣기
		for (int i = 0; i < N; i++) {
			String input = sc.nextLine();
			set1.add(input);
		}
//		System.out.println(set1.toString());
		
		// M 넣기
		for (int i = 0; i < M; i++) {
			String input = sc.nextLine();
			set2.add(input);
		}
//		System.out.println(set2.toString());
		
		
		/*
		 * 둘 공통 부분 비교 사전순으로 출력해야 함 
		 * set은 순서가 없기 때문에 List 변환 필요
		 */
		
		// 교집합 구하기
		set1.retainAll(set2); // set1을 업데이트해주는 방식 (새로운 반환X)
		System.out.println(set1.size());
		
		// 리스트로 사전순으로 배열하기
		List<String> sorted_set1 = new ArrayList<>(set1);
		Collections.sort(sorted_set1); // Arrays.sort() 안됨
		for (String str : sorted_set1) {
			System.out.println(str);
		}
		
		
		/*
		ss.addAll(ss2);	// (1 2 3 4) ∪ (3 4 5 6) = (1 2 3 4 5 6) 합집합
		*/
		
		/*
		ss.retainAll(ss2);	// (1 2 3 4) ∩ (3 4 5 6) = (3 4) 교집합
		*/
		
		/*
		ss.removeAll(ss2);	// (1 2 3 4) - (3 4 5 6) = (1 2) 차집합
		*/

	}
}