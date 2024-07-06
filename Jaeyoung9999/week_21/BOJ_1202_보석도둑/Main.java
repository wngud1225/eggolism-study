import java.util.*;

public class Main {

	static class Jewel implements Comparable<Jewel> {
		int M; // 무게
		int V; // 가치

		Jewel(int M, int V) {
			this.M = M;
			this.V = V;
		}

		@Override
		public int compareTo(Jewel j) {

			return j.V - this.V; // 가치 내림차순
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 보석의 수
		int K = sc.nextInt(); // 가방의 수

		List<Jewel> jewels = new ArrayList<>(); // 보석을 무게 오름차순으로 정렬
		PriorityQueue<Integer> bags = new PriorityQueue<>(); // 가방을 오름차순으로 정렬
		PriorityQueue<Jewel> pq = new PriorityQueue<>(); // 보석을 가치 내림차순으로 정렬

		for (int i = 0; i < N; i++) {
			jewels.add(new Jewel(sc.nextInt(), sc.nextInt()));
		}
		for (int i = 0; i < K; i++) {
			bags.add(sc.nextInt());
		}
		
		Collections.sort(jewels, new Comparator<Jewel>() {

			@Override
			public int compare(Jewel j1, Jewel j2) {

				return j1.M - j2.M; // 무게 오름차순
			}
		});

		int idx = 0;
		long result = 0; // 큰 값에 대비해 long으로 선언

		while (!bags.isEmpty()) {
			int limit = bags.poll(); // 가장 낮은 무게의 가방을 꺼내서
			while (idx < N && jewels.get(idx).M <= limit) { // 그 가방에 넣을 수 있는 보석을 pq에 넣고
				pq.add(jewels.get(idx));
				idx++;
			}
			if (!pq.isEmpty()) { // pq가 비었다면 가능한 보석이 없는 것
				result += pq.poll().V; // 가능한 보석 중에 가장 높은 가치의 보석을 꺼냄
			}
		}
		System.out.println(result);
	}
}