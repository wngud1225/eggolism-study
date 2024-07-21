package BOJ_14267;

// 회사문화1
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		List<Integer>[] graph = new ArrayList[n + 1];
		int[] val = new int[n + 1]; // 칭찬을 저장할 배열
		int[] ans = new int[n + 1]; // 답을 저장할 배열
		boolean[] visited = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 1; i <= n; i++) {
			int parent = sc.nextInt();
			if (parent != -1) { // 사장의 인덱스(-1) 에러 방지
				graph[parent].add(i); // 부모에게 자식 정보를 넣어줌
			}
		}
		for (int i = 1; i <= m; i++) {
			val[sc.nextInt()] += sc.nextInt(); // 한 명에 대해 칭찬이 여러 번 있을 수도 있음
		}
		for (int i = 1; i <= n; i++) { // 직속 상사의 번호가 자신의 번호보다 작음이 보장됨
			if (!visited[i]) {
				Queue<Integer> bfs = new LinkedList<>();
				bfs.add(i);
				visited[i] = true;
				while (!bfs.isEmpty()) {
					int curr = bfs.poll();
					for (int next : graph[curr]) {
						if (!visited[next]) {
							ans[next] = ans[curr] + val[next];
							visited[next] = true;
							bfs.add(next);
						}
					}
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			System.out.printf(ans[i] + " ");
		}
	}
}