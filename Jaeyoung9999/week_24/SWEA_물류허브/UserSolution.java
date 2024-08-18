package no32_물류허브;

import java.util.*;

class UserSolution {

	static class Edge implements Comparable<Edge> {
		int next;
		int v;

		Edge(int next, int v) {
			this.next = next;
			this.v = v;
		}

		@Override
		public int compareTo(Edge e) {

			return this.v - e.v;
		}
	}

	static Map<Integer, Integer> map;
	static int cityNum;
	static List<Edge>[] edges;
	static List<Edge>[] reverse_edges;

	public int init(int N, int sCity[], int eCity[], int mCost[]) {

		map = new HashMap<>();
		cityNum = 1;
		edges = new ArrayList[601];
		reverse_edges = new ArrayList[601];

		for (int i = 1; i <= 600; i++) {
			edges[i] = new ArrayList<>();
			reverse_edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			if (map.get(sCity[i]) == null) {
				map.put(sCity[i], cityNum++);
			}
			if (map.get(eCity[i]) == null) {
				map.put(eCity[i], cityNum++);
			}
			edges[map.get(sCity[i])].add(new Edge(map.get(eCity[i]), mCost[i]));
			reverse_edges[map.get(eCity[i])].add(new Edge(map.get(sCity[i]), mCost[i]));
		}

		return map.size();
	}

	public void add(int sCity, int eCity, int mCost) {
		if (map.get(sCity) == null) {
			map.put(sCity, cityNum++);
		}
		if (map.get(eCity) == null) {
			map.put(eCity, cityNum++);
		}
		edges[map.get(sCity)].add(new Edge(map.get(eCity), mCost));
		reverse_edges[map.get(eCity)].add(new Edge(map.get(sCity), mCost));
		return;
	}

	public int cost(int mHub) {

		return diikstra(map.get(mHub), edges) + diikstra(map.get(mHub), reverse_edges);
	}

	int diikstra(int mHub, List<Edge>[] edges) {
		int[] dist = new int[601];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[601];
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		pq.add(new Edge(mHub, 0));
		dist[mHub] = 0;

		while (!pq.isEmpty()) {
			Edge curr = pq.poll();

			if (visited[curr.next]) {
				continue;
			}

			visited[curr.next] = true;

			for (Edge edge : edges[curr.next]) {
				if (!visited[edge.next] && dist[edge.next] > dist[curr.next] + edge.v) {
					dist[edge.next] = dist[curr.next] + edge.v;
					pq.add(new Edge(edge.next, dist[edge.next]));
				}
			}
		}

		int result = 0;
		for (int i = 1; i <= 600; i++) {
			if (dist[i] != Integer.MAX_VALUE) {
				result += dist[i];
			}
		}
		return result;
	}
}