package BOJ_21276;

// 계보복원가호석
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		String[] people = new String[N]; // 이름 -> 번호 매칭
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = st.nextToken();
		}
		Arrays.sort(people); // 이름순으로 정렬
		Map<String, Integer> stringToInt = new HashMap<>(); // 번호 -> 이름 매칭
		List<Integer>[] ans = new ArrayList[N]; // 직속 후손만 저장할 리스트
		List<Integer>[] graph = new ArrayList[N]; // 그래프
		int[] degree = new int[N]; // 진입차수 배열

		for (int i = 0; i < N; i++) {
			stringToInt.put(people[i], i);
		}

		for (int i = 0; i < N; i++) {
			ans[i] = new ArrayList<>();
			graph[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int child = stringToInt.get(st.nextToken());
			int parent = stringToInt.get(st.nextToken());
			graph[parent].add(child); // 그래프 연결(부모->자식)
			degree[child]++; // 자손의 진입차수 ++
		}

		List<Integer> grandParents = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (degree[i] == 0) { // 진입차수가 없다 -> 트리 최상단이다
				grandParents.add(i);
			}
		}
		sb.append(grandParents.size() + "\n");
		for (int i = 0; i < grandParents.size(); i++) {
			sb.append(people[grandParents.get(i)] + " ");
		}
		sb.append("\n");

		for (int i = 0; i < grandParents.size(); i++) { // 트리 최상단에서 시작
			Queue<Integer> queue = new LinkedList<>();
			queue.add(grandParents.get(i));
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				for (int next : graph[curr]) { // 다음에 갈 수 있는 정점들에 대해
					degree[next]--; // 진입차수 --
					if (degree[next] == 0) { // 진입차수가 0이라면
						ans[curr].add(next); // 정답 리스트에 더해주고
						queue.add(next); // 큐에 넣어줌
					}
				}
			}
		}
		// 위 과정이 끝나면 ans 리스트에 각 사람마다 직속 후손 정보가 저장됨
		for (int i = 0; i < N; i++) {
			sb.append(people[i] + " ");
			sb.append(ans[i].size() + " ");
			if (ans[i].size() == 0) {
				sb.append("\n");
			} else {
				Collections.sort(ans[i]); // 이름 순으로 출력해야 함
				for (int child : ans[i]) {
					sb.append(people[child] + " ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}