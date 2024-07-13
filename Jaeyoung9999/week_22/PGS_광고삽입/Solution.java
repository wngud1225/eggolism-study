package PGS_광고삽입;

class Solution {
	public String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";

		int playTime = timeToInt(play_time);
		int advTime = timeToInt(adv_time);

		int[] times = new int[playTime + 1];

		// 누적합을 위한 표시 (영상 종료 시각은 포함하지 않음)
		for (String log : logs) {
			int startTime = timeToInt(log.substring(0, 8));
			int endTime = timeToInt(log.substring(9, 17));
			times[startTime]++;
			times[endTime]--;
		}

		// 누적합 진행
		for (int i = 1; i <= playTime; i++) {
			times[i] += times[i - 1];
		}

		long[] timeSum = new long[playTime - advTime + 1]; // 구간합을 저장할 배열

		for (int i = 0; i < advTime; i++) {
			timeSum[0] += times[i];
		}

		long max = timeSum[0];
		int maxTime = 0;

		// 구간합 시작
		for (int i = 1; i < playTime - advTime + 1; i++) {
			timeSum[i] = timeSum[i - 1] - times[i - 1] + times[advTime + i - 1];
			if (max < timeSum[i]) {
				max = timeSum[i];
				maxTime = i;
			}
		}
		answer = intToTime(maxTime);

		return answer;
	}

	int timeToInt(String time) {
		int result = 0;
		result += 3600 * Integer.parseInt(time.substring(0, 2));
		result += 60 * Integer.parseInt(time.substring(3, 5));
		result += Integer.parseInt(time.substring(6, 8));
		return result;
	}

	String intToTime(int num) {
		int h = num / 3600;
		num %= 3600;
		int m = num / 60;
		num %= 60;
		int s = num;
		String H = h < 10 ? "0" + h : "" + h;
		String M = m < 10 ? "0" + m : "" + m;
		String S = s < 10 ? "0" + s : "" + s;

		return H + ":" + M + ":" + S;
	}
}