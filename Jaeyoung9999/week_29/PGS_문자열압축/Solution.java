package PGS_문자열압축;

import java.util.*;

class Solution {

	public int solution(String s) {

		int len = s.length();
		int answer = len;

		for (int lv = 1; lv <= len * 0.5; lv++) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < len; i += lv) {
				String tmp = "";
				for (int j = 0; j < lv; j++) {
					if (i + j >= len) {
						break;
					}
					tmp += s.charAt(i + j);
				}
				list.add(tmp);
			}

			String tmp = "";
			int i = 0;
			boolean[] visited = new boolean[list.size()];
			start: while (i < list.size()) {
				if (visited[i]) {
					i++;
					continue start;
				}
				String curr = list.get(i);
				int cnt = 1;
				if (i < list.size() - 1 && curr.equals(list.get(i + 1))) {
					cnt++;
					i++;
					visited[i] = true;
					while (i < list.size() - 1 && curr.equals(list.get(i + 1))) {
						cnt++;
						i++;
						visited[i] = true;
					}
				} else {
					i++;
				}
				if (cnt >= 2) {
					tmp += cnt;
				}
				tmp += curr;
			}

			if (answer > tmp.length()) {
				answer = tmp.length();
			}
		}
		return answer;
	}
}