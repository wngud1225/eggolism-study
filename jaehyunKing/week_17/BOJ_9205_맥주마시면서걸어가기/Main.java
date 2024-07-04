package BOJ_9205_맥주마시면서걸어가기;

/*
 *  bfs를 2차원 배열에 돌리는 것이 아닌 편의점(shop)배열을 만들어서
 *  해당 위치에서 도착지와 편의점에 갈 수 있는지 확인
 *  도착지에 갈 수 있으면 happy를 출력
 *  편의점에 갈 수 있으면 편의점으로 이동
 *  이렇게 했는데 가지 못한다면 sad를 출력
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int startY, startX, endY, endX;
	static List<int[]> shop;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for(int i = 0; i < t; i++) {
			int n = sc.nextInt();
			
			startX = sc.nextInt();
			startY = sc.nextInt();
			shop = new ArrayList<>();
			
			for(int j = 0; j < n; j++) {				
				int tempX = sc.nextInt();
				int tempY = sc.nextInt();
				shop.add(new int[] {tempY, tempX});
			}
			
			endX = sc.nextInt();
			endY = sc.nextInt();
			bfs(startY, startX);
		}
		
	}
	
	static void bfs(int startY, int startX) {
		Queue<int[]> queue = new LinkedList<>();
		// y, x, 미터, 맥주 개수
		queue.offer(new int[] {startY, startX});
		boolean[] visited = new boolean[shop.size()];
		
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			
			// 목적지에 도착할 수 있는 지 확인
			// 맥주 개수 20, 50미터에 하나 먹음 -> 20 * 50 = 1000
			if(Math.abs(endY - num[0]) + Math.abs(endX - num[1]) <= 1000) {
				System.out.println("happy");
				return;
			}
			
			// 도착지에 갈 수 없다면 편의점에 갈 수 있는 지 확인
			for(int i = 0; i < shop.size(); i++) {
				if(!visited[i] && Math.abs(shop.get(i)[0] - num[0]) + Math.abs(shop.get(i)[1] - num[1]) <= 1000) {
					visited[i] = true;
					queue.offer(new int[] {shop.get(i)[0], shop.get(i)[1]});
				}
			}
			
		}
		
		// 도착지에 도착할 수 없다면 sad를 출력
		System.out.println("sad");
		
	}

}