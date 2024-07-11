package BOJ_2457_공주님의정원;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		PriorityQueue<flower> pq = new PriorityQueue<>();
		// 12월 1일 -> 1201, 1월 1일 -> 101 이런 식으로 바꾸고 우선순위 큐에 넣어줌
		for(int i = 0; i < N; i++) pq.add(new flower(sc.nextInt()*100 + sc.nextInt(), sc.nextInt()*100 + sc.nextInt()));
		// 종료일을 3월 1일로 갱신, 3월 1일 전에만 시작하면 됨
		int endDate = 301;
		int count = 0;
		int maxEndDate = endDate;
		
		// endDate >= 1201이면 조건 만족
		while(endDate < 1201) {
			if(pq.isEmpty()) {
				// 우선순위큐가 비었고, 가장 늦게 지는 날이 갱신되지 않았다면 꽃 선택 불가
				if(maxEndDate == endDate) {
					count = 0;
					break;
				}
				// 가장 늦게 지는 날이 갱신되었다면 continue 
				else {
					endDate = maxEndDate;
					count++;
					continue;
				}
			}
			flower now = pq.poll();
			// 피는 날이 지는 날보다 작거나 같으면
			if(now.start <= endDate) {
				// 가장 늦게 지는 날이 더 작다면 갱신
				if(maxEndDate < now.end) maxEndDate = now.end; 
			}
			// 피는 날이 더 크면
			else {
				pq.offer(now);
				if(maxEndDate == endDate) {
					count = 0;
					break;
				}
				count++;
				// 지는 날을 갱신
				endDate = maxEndDate;
			}
		}
		
		System.out.println(count);
		
	}
}

class flower implements Comparable<flower>{
	int start;
	int end;
	
	flower(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(flower o) {
		// 1. 먼저 피거나, 2. 피는 날이 같지만 지는 날이 늦는 것을 기준으로 정렬
		if(this.start < o.start || this.start == o.start && this.end > o.end) return -1;
		return 1;
	}
}