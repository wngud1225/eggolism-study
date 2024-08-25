package PGS_k진수에서소수개수구하기;

import java.util.*;

class Solution {

	static boolean isPrime(long num) {
		if (num == 1 || num == 0) {
			return false;
		}
		for (int i = 2; i < (long) Math.sqrt(num) + 1; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public int solution(int n, int k) {

		int answer = 0;

		Deque<Integer> deque = new ArrayDeque<>();
		while (true) {
			deque.addFirst(n % k);
			n /= k;
			if (n < k) {
				deque.addFirst(n);
				break;
			}
		}

		Queue<Integer> tmp = new LinkedList<>();
		while (!deque.isEmpty()) {
			int curr = deque.pollFirst();
			if (curr != 0) {
				tmp.add(curr);
			} else {
				long num = 0;
				while (!tmp.isEmpty()) {
					num += tmp.poll();
					num *= 10;
				}
				num /= 10;
				if (isPrime(num)) {
					answer++;
				}
			}
		}
		long num = 0;
		while (!tmp.isEmpty()) {
			num += tmp.poll();
			num *= 10;
		}
		num /= 10;
		if (isPrime(num)) {
			answer++;
		}

		return answer;
	}
}