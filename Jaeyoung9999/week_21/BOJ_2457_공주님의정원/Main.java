import java.util.*;

public class Main {

	static class Flower {
		int start;
		int end;

		Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int[] months = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 }; // 날짜 계산용

		int N = sc.nextInt();
		PriorityQueue<Flower> pq1 = new PriorityQueue<>(new Comparator<Flower>() { // 모든 꽃을 저장할 pq

			@Override
			public int compare(Flower f1, Flower f2) {

				return f1.start - f2.start; // 시작일 기준 오름차순
			}
		});

		PriorityQueue<Flower> pq2 = new PriorityQueue<>(new Comparator<Flower>() { // 피울 수 있는 꽃을 추가할 pq

			@Override
			public int compare(Flower f1, Flower f2) {

				return f2.end - f1.end; // 종료일 기준 내림차순 --> 종료일이 늦을수록 이득
			}
		});

		for (int i = 0; i < N; i++) {

			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			int D = sc.nextInt();

			int start = months[A - 1] + B;
			int end = months[C - 1] + D;

			pq1.add(new Flower(start, end));
		}

		int day = 60; // 현재 날짜를 나타낼 변수, 3월 1일에 시작
		int ans = 0;

		start: while (day <= 334) { // 11월 30일까지
			add: while (true) { // pq2에 꽃을 추가하는 과정
				Flower curr = pq1.poll(); // pq1에서 하나를 꺼내서

				if (curr == null) { // 추가할 꽃이 없다면 add 작업 중지
					break add;
				}

				if (curr.start <= day) { // 현재 날짜보다 꽃이 피는 날짜가 빠르면
					pq2.add(curr); // pq2에 추가하고
				} else { // 아니면
					pq1.add(curr); // 다시 pq1에 넣음(이후 day 값 변경에 따라 또 쓸 수도 있음)
					break add; // 마찬가지로 add 작업 중지
				}
			}

			// add가 끝난 이후 pq2에는 현재 날짜를 기준으로 피울 수 있는 꽃들이 존재 --> 종료일이 늦은 순으로 정렬된 상태
			Flower curr = pq2.poll(); // 꽃 하나를 꺼내서

			if (curr == null) { // 피울 수 있는 꽃이 없다면 공주님의정원 유지 불가
				break start;
			}
			if (curr.end > day) { // 꽃의 종료일이 현재 날짜보다 뒤라면
				ans++; // 꽃 카운트 ++
				day = curr.end; // 현재 날짜 갱신
			} else { // 아니면 갱신 불가
				break start;
			}
		}
		if (day < 335) { // 현재 날짜가 12월 1일 이전이라면 실패
			ans = 0;
		}
		System.out.println(ans);
	}
}