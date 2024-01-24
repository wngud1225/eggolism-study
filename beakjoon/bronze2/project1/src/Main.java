import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();

		int diff_N = (N - 8) + 1;
		int diff_M = (M - 8) + 1;

		int answer = Integer.MAX_VALUE;

		// 원본1과 2
		char[] arr1 = { 'W', 'B' };
		char[] arr2 = { 'B', 'W' };

		// 여러번 시작
		for (int a = 0; a < diff_N; a++) {
			for (int b = 0; b < diff_M; b++) {

				int count1 = 0;
				int count2 = 0;

				// 8개 줄로 한정 -> 2*4
				for (int row = 0 + a; row < 4 + a; row++) {

					// 한 줄 시작
					String one_line = sc.nextLine();
					String two_line = sc.nextLine();
					System.out.println();
					System.out.println(one_line);
					System.out.println(two_line);
					for (int i = 0 + b; i < 8 + b; i++) {
						char one_char = one_line.charAt(i);
						char tow_char = two_line.charAt(i);
//						System.out.println(one_char + " vs " + arr1[i % 2]);
//						System.out.println(tow_char + " vs " + arr2[i % 2]);
						
						// i%2 변경해야 함?
						if (arr1[i % 2] != one_char) {
							count1++;
						} else {
							count2++;
						}
						if (arr2[i % 2] != tow_char) {
							count1++;
						} else {
							count2++;
						}

					}
					
				} // 8줄 끝 (한세트)

				System.out.println("count1: " + count1);
				System.out.println("count2: " + count2);

				int eightbyeight = Math.min(count1, count2);
				System.out.println(">>> " + eightbyeight);
				
				// 판별
				if (eightbyeight < answer) {
					answer = eightbyeight;


				}

			}
		} // 전부 끝

		System.out.println("정답:" + answer);

	}

}
