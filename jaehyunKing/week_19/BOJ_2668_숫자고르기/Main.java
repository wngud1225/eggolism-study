package BOJ_2668_숫자고르기;

// 처음 숫자가 나올 때까지 리스트에 추가하면서 계속 타고 감, graph[j][1]이 이미 방문했던 숫자가 나온다면 빠져나오고
// 처음 숫자가 나온다면 리스트에 있는 모든 수에 대해서 result라고 체크
// for문으로 result = true인 애들을 출력

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[][] graph = new int[n+1][2];
		boolean[] result = new boolean[n+1];
		for(int i = 1; i <= n; i++) {
			graph[i][0] = i;
			graph[i][1] = sc.nextInt();
		}
		
		int count = 0;
		for(int i = 1; i <= n; i++) {
			if(!result[i]) { // i가 이미 사용되지 않았다면
				List<Integer> list = new ArrayList<>();
				int j = i;
				boolean[] visited = new boolean[n+1];
				while(true) {
					// j가 방문하자 않았고, i와 일치하지 않는다면
					if(!visited[j] && graph[j][1] != i) {
						list.add(j);
						visited[j] = true;
						j = graph[j][1];
					}
					// i와 일치한다면
					else if(graph[j][1] == i){
						list.add(j);
						for(int k = 0; k < list.size(); k++) {
							result[list.get(k)] = true;
							count++;
						}
						break;
					}
					// 방문한 곳이라면
					else break;
				}
			}
		}
		
		System.out.println(count);
		for(int i = 1; i <= n; i++) {
			if(result[i]) System.out.println(i);
		}
	}

}