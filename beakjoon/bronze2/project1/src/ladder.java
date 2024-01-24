import java.util.Iterator;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

public class ladder {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 10개 테스트 케이스가 주어짐 -> 1개 단위
		int test = sc.nextInt();

		int[][] ladder = new int[100][100];

		// 배열에 넣기
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				ladder[i][j] = sc.nextInt();
			}
		}

		// 임시로 출력해보기
		//		for (int i = 0; i < 100; i++) {
		//			for (int j = 0; j < 100; j++) {
		//				System.out.print(ladder[i][j]);
		//			}
		//			System.out.println();
		//		}

		int start_r = 0;
		int start_c = 67; // 원래 0


		// 내려가기 + 좌우로만 움직인다..
		// 1부터 100까지 스캔하기
		for (int i = 99; i < 100; i++) {

			// 시작
			boolean is_right = false;
			boolean is_left = false;

			int point_r = start_r;
			int point_c = start_c;

			// 1인 것만 작동되도록
			if (ladder[0][point_c] == 1) {
				//				System.out.println("시작이 1이라서 시작합니다: " + start_c);
				while (true) {
					// 예외처리 1 (false true 상태도 들어옴)
					if (point_c == 0) {
						is_left = false;
						if (ladder[point_r][point_c+1] == 1) {
							is_right = true;
							point_c++;
						} else {
							point_r++;
							is_right = false;
							is_left = false;
						}
						
						// 예외처리 2
					} else if (point_c == 99) {
						is_right = false;
						if (ladder[point_r][point_c-1] == 1) {
							is_left = true;
							point_c--;
						} else {
							point_r++;
							is_right = false;
							is_left = false;
						}
					}


					// 일반적인 상황 (둘다 false)
					if (ladder[point_r][point_c+1] == 1 && is_right == false && is_left == false) {
						is_right = true;
						point_c++;
					} else if (ladder[point_r][point_c+1] == 0 && is_right == true && is_left == false) {
						point_r++;
						is_right = false;
					} else if (ladder[point_r][point_c+1] == 1 && is_right == true && is_left == false) {
						point_c++;
					} else if (ladder[point_r][point_c-1] == 1 && is_right == false && is_left == false) {
						is_left = true;
						point_c--;
					} else if (ladder[point_r][point_c-1] == 0 && is_right == false && is_left == true) {
						point_r++;
						is_left = false;
					} else if (ladder[point_r][point_c-1] == 1 && is_right == false && is_left == true) {
						point_c--;
					} else {
						point_r++;
						is_right = false;
						is_left = false;
					}


					System.out.println("현재 위치: " + point_r + " " + point_c);

					// 옮긴 위치 평가
					if (ladder[point_r][point_c] == 2) {
						System.out.println("이거임: " + start_c);
						break;
					} else if (point_r == 99) {
						System.out.println("이거 아님: " + start_c);
						System.out.println("현재 point" +"(" + point_r + ", " + point_c + ")");
						System.out.println("현재 ladder 점수: " + ladder[point_r][point_c]);
						break;
					}
					else {
						//						System.out.println("끝이 나지 않았습니다.");
					}
				}// while 끝


			} else {

				//				System.out.println("시작이 0이 아니라서 넘어갑니다.");
			}


			// 초기화
			start_c++;

			// 한개 끝
		}









	}
}