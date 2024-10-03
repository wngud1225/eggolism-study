package PGS_오픈채팅방;

import java.util.*;

class Solution {
	public String[] solution(String[] record) {

		int len = record.length;
		Map<String, String> map = new HashMap<>();
		int cnt = 0;

		for (int i = 0; i < len; i++) {
			String[] tmp = record[i].split(" ");

			if (tmp[0].equals("Enter")) {
				cnt++;
				map.put(tmp[1], tmp[2]);
			} else if (tmp[0].equals("Leave")) {
				cnt++;
			} else {
				map.put(tmp[1], tmp[2]);
			}
		}

		String[] answer = new String[cnt];
		int idx = 0;

		for (int i = 0; i < len; i++) {
			String[] tmp = record[i].split(" ");
			if (tmp[0].equals("Enter")) {
				answer[idx++] = map.get(tmp[1]) + "님이 들어왔습니다.";
			} else if (tmp[0].equals("Leave")) {
				answer[idx++] = map.get(tmp[1]) + "님이 나갔습니다.";
			}
		}

		return answer;
	}
}