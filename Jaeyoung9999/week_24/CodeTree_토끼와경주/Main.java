package codetree_토끼와경주;

import java.util.*;

public class Main {

	static class Rabbit {
		int r;
		int c;
		int jump;
		int num;

		Rabbit(int r, int c, int jump, int num) {
			this.r = r;
			this.c = c;
			this.jump = jump;
			this.num = num;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int Q = sc.nextInt();

		PriorityQueue<Rabbit> pq1 = new PriorityQueue<>(new Comparator<Rabbit>() {

			@Override
			public int compare(Rabbit r1, Rabbit r2) {

				if (r1.jump == r2.jump) {

					if (r1.r + r1.c == r2.r + r2.c) {

						if (r1.r == r2.r) {

							if (r1.c == r2.c) {
								return r1.num - r2.num;
							}

							return r1.c - r2.c;
						}

						return r1.r - r2.r;
					}

					return r1.r + r1.c - r2.r - r2.c;
				}

				return r1.jump - r2.jump;
			}
		});

		PriorityQueue<Rabbit> pq2 = new PriorityQueue<>(new Comparator<Rabbit>() {

			@Override
			public int compare(Rabbit r1, Rabbit r2) {

				if (r2.r + r2.c == r1.r + r1.c) {

					if (r2.r == r1.r) {

						return r2.c - r1.c;
					}

					return r2.r - r1.r;
				}

				return r2.r + r2.c - r1.r - r1.c;
			}

		});

		PriorityQueue<Rabbit> pq3 = new PriorityQueue<>(new Comparator<Rabbit>() {

			@Override
			public int compare(Rabbit r1, Rabbit r2) {

				if (r2.r + r2.c == r1.r + r1.c) {

					if (r2.r == r1.r) {

						if (r2.c == r1.c) {
							return r2.num - r1.num;
						}

						return r2.c - r1.c;
					}

					return r2.r - r1.r;
				}

				return r2.r + r2.c - r1.r - r1.c;
			}

		});

		int[] dist = null;
		int[] score = null;
		int[] jump = null;
		long maxScore = 0;
		Map<Integer, Integer> map = new HashMap<>();
		int N = 0, M = 0, P = 0;

		for (int tc = 1; tc <= Q; tc++) {
			int order = sc.nextInt();

			if (order == 100) {
				N = sc.nextInt();
				M = sc.nextInt();
				P = sc.nextInt();
				dist = new int[P + 1];
				score = new int[P + 1];
				jump = new int[P + 1];
				for (int i = 0; i < P; i++) {
					int id = sc.nextInt();
					map.put(id, i);
					dist[i] = sc.nextInt();
					pq1.add(new Rabbit(1, 1, 0, id));
				}

			} else if (order == 200) {
				int K = sc.nextInt();
				for (int i = 0; i < K; i++) {
					Rabbit curr = pq1.poll();
					int d = dist[map.get(curr.num)];
					int nr = (curr.r + d) % (2 * N - 2);
					if (nr == 0) {
						nr = 2;
					} else if (nr > N) {
						nr = 2 * N - nr;
					}
					pq2.add(new Rabbit(nr, curr.c, curr.jump + 1, curr.num));
					nr = (2 * N - curr.r + d) % (2 * N - 2);
					if (nr == 0) {
						nr = 2;
					} else if (nr > N) {
						nr = 2 * N - nr;
					}
					pq2.add(new Rabbit(nr, curr.c, curr.jump + 1, curr.num));
					int nc = (curr.c + d) % (2 * M - 2);
					if (nc == 0) {
						nc = 2;
					} else if (nc > M) {
						nc = 2 * M - nc;
					}
					pq2.add(new Rabbit(curr.r, nc, curr.jump + 1, curr.num));
					nc = (2 * M - curr.c + d) % (2 * M - 2);
					if (nc == 0) {
						nc = 2;
					} else if (nc > M) {
						nc = 2 * M - nc;
					}
					pq2.add(new Rabbit(curr.r, nc, curr.jump + 1, curr.num));

					Rabbit curr2 = pq2.poll();
					pq1.add(curr2);
					pq3.add(curr2);
					jump[map.get(curr2.num)]++;
					maxScore += curr2.r + curr2.c;
					score[map.get(curr2.num)] -= curr2.r + curr2.c;
					pq2.clear();
				}
				while (true) {
					Rabbit curr = pq3.poll();
					if (curr.jump == jump[map.get(curr.num)]) {
						int S = sc.nextInt();
						score[map.get(curr.num)] += S;
						pq3.clear();
						break;
					}
				}
			} else if (order == 300) {
				int id = sc.nextInt();
				int L = sc.nextInt();
				dist[map.get(id)] *= L;
			} else if (order == 400) {
				int max = 0;
				for (int i = 0; i < P; i++) {
					max = Math.max(max, score[i]);
				}
				System.out.println(max + maxScore);
			}
		}
	}
}