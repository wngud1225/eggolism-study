package PGS_무지의먹방라이브;

import java.util.*;

class Solution {

	static class Food {
		int num;
		int time;

		Food(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}

	public int solution(int[] food_times, long k) {
		int answer = 0;
		long sum = 0;
		int max = 0;

		for (int i = 0; i < food_times.length; i++) {
			sum += food_times[i];
			if (max < food_times[i]) {
				max = food_times[i];
			}
		}
		if (sum <= k) {
			return -1;
		}

		int left = 0;
		int right = max;
		int mid = 0;

		while (left <= right) {

			mid = (left + right) / 2;

			long tmpSum = 0;
			for (int i = 0; i < food_times.length; i++) {
				tmpSum += Math.min(food_times[i], mid);
			}

			if (tmpSum <= k) {
				left = mid + 1;
			} else if (tmpSum > k) {
				right = mid - 1;
			}
		}
		mid = (left + right) / 2;

		Queue<Food> foods = new LinkedList<>();
		long currTime = 0;

		for (int i = 0; i < food_times.length; i++) {
			currTime += Math.min(food_times[i], mid);
			if (food_times[i] > mid) {
				foods.add(new Food(i + 1, food_times[i] - mid));
			}
		}

		for (long i = currTime; i < k; i++) {
			Food curr = foods.poll();
			curr.time--;
			if (curr.time > 0) {
				foods.add(curr);
			}
		}
		answer = foods.poll().num;

		return answer;
	}
}