package BOJ_2170_선긋기;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// start가 작은 것이 start가 같다면 end가 큰 것이 앞에 오게 pq로 정렬
		for(int i = 0; i < N; i++) pq.add(new Node(sc.nextInt(), sc.nextInt()));
		
		// 초기값 설정
		Node tmp = pq.poll();
		int start = tmp.start;
		int end = tmp.end;
		int length = end - start;
		
		// 끝까지 진행
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.start <= end && now.end > end) {
				length += now.end - end;
				start = now.start;
				end = now.end;
			}
			else if(now.start > end) {
				length += now.end - now.start;
				start = now.start;
				end = now.end;
			}
		}
		
		System.out.println(length);

	}
	
	public static class Node implements Comparable<Node> {
		int start;
		int end;
		
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o) {
			if(this.start < o.start) return -1;
			else if(this.start == o.start) {
				if(this.end > o.end) return -1;
			}
			return 1;
		}
		
	}

}