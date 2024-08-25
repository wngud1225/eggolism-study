package PGS_주차요금계산;

import java.util.*;

class Solution {
	public int[] solution(int[] fees, String[] records) {

		HashMap<Integer, Integer> hashMap = new HashMap<>();
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();

		for (int i = 0; i < records.length; i++) {
			String[] input = records[i].split(" ");
			String[] input2 = input[0].split(":");
			int hour = Integer.parseInt(input2[0]);
			int min = Integer.parseInt(input2[1]);
			int num = Integer.parseInt(input[1]);
			int time = hour * 60 + min;

			if (hashMap.get(num) == null) { // 해시맵에 없음 -> 입차
				hashMap.put(num, time);
			} else { // 해시맵에 있음 -> 출차
				if (treeMap.get(num) == null) { // 한 번도 입차/출차를 한 적이 없음
					treeMap.put(num, time - hashMap.get(num));
				} else { // 한 번이라도 입차/출차를 한 적이 있음
					treeMap.put(num, treeMap.get(num) + time - hashMap.get(num));
				}
				hashMap.remove(num);
			}
		}
		// 위 과정이 끝나면 해시맵에는 11시 59분에 출차할 차량들, 트리맵에는 현재까지 차량별 누적 주차 시간이 들어있음
		for (int key : hashMap.keySet()) { // 해시맵에 남은 차량들을 출차
			int time = 24 * 60 - 1;
			if (treeMap.get(key) == null) {
				treeMap.put(key, time - hashMap.get(key));
			} else {
				treeMap.put(key, treeMap.get(key) + time - hashMap.get(key));
			}
		}

		int size = treeMap.size();
		int[] answer = new int[size];
		for (int i = 0; i < size; i++) { // 요금 계산
			answer[i] = (int) (fees[1]
					+ Math.ceil((double) (treeMap.pollFirstEntry().getValue() - (double) fees[0]) / fees[2]) * fees[3]);
			answer[i] = answer[i] < fees[1] ? fees[1] : answer[i];
		}

		return answer;
	}
}