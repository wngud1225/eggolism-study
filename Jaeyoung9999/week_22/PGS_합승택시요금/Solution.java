package PGS_합승택시요금;

import java.util.*;

class Solution {
	static class Edge implements Comparable<Edge> {
		int end;
		int v;

		Edge(int end, int v) {
			this.end = end;
			this.v = v;
		}

		@Override
		public int compareTo(Edge e) {

			return this.v - e.v;
		}
	}

	public int solution(int n, int s, int a, int b, int[][] fares) {

		int answer = 0;

		List<Edge>[] edges = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < fares.length; i++) {
			edges[fares[i][0]].add(new Edge(fares[i][1], fares[i][2]));
			edges[fares[i][1]].add(new Edge(fares[i][0], fares[i][2]));
		}

		// 1. 출발점 기준 다익스트라 시작
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[n + 1];

		dist[s] = 0;
		pq.add(new Edge(s, 0));

		while (!pq.isEmpty()) {
			Edge curr = pq.poll();

			if (visited[curr.end]) {
				continue;
			}

			visited[curr.end] = true;

			for (Edge edge : edges[curr.end]) {
				if (!visited[edge.end] && dist[edge.end] > dist[curr.end] + edge.v) {
					dist[edge.end] = dist[curr.end] + edge.v;
					pq.add(new Edge(edge.end, dist[edge.end]));
				}
			}
		}

		answer = dist[a] + dist[b]; // 같이 타고 가는 것을 고려하지 않았을 때의 기본적인 최소비용의 합

		// 경유점마다 다익스트라 시작
		for (int i = 1; i <= n; i++) {
			if (dist[i] < answer) { // 경유점까지 가는 거부터가 현재 최소치보다 크면 계산할 필요없음
				PriorityQueue<Edge> pq2 = new PriorityQueue<>();
				int[] dist2 = new int[n + 1];
				Arrays.fill(dist2, Integer.MAX_VALUE);
				boolean[] visited2 = new boolean[n + 1];

				dist2[i] = 0;
				pq2.add(new Edge(i, 0));

				while (!pq2.isEmpty()) {
					Edge curr = pq2.poll();

					if (visited2[a] && visited2[b]) { // 위 다익스트라와는 다르게 a, b 지점만 도착하면 끝내면 됨
						break;
					}

					if (visited2[curr.end]) {
						continue;
					}

					visited2[curr.end] = true;

					for (Edge edge : edges[curr.end]) {
						if (!visited2[edge.end] && dist2[edge.end] > dist2[curr.end] + edge.v) {
							dist2[edge.end] = dist2[curr.end] + edge.v;
							pq2.add(new Edge(edge.end, dist2[edge.end]));
						}
					}
				}
				answer = Math.min(answer, dist[i] + dist2[a] + dist2[b]);
			}
		}
		return answer;
	}
}