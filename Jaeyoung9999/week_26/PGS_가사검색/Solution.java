package PGS_가사검색;

import java.util.*;

class Solution {

	static class Node {
		Node[] children;
		int cnt;

		Node() {
			this.children = new Node[26];
			cnt = 0;
		}
	}

	public static int[] solution(String[] words, String[] queries) {

		Node[] nodes = new Node[10001];
		Node[] reverses = new Node[10001];
		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < 10001; i++) {
			nodes[i] = new Node();
			reverses[i] = new Node();
		}

		for (int i = 0; i < words.length; i++) {
			Node curr = nodes[words[i].length()];
			for (int j = 0; j < words[i].length(); j++) {
				curr.cnt++;
				int num = words[i].charAt(j) - 'a';
				if (curr.children[num] == null) {
					curr.children[num] = new Node();
				}
				curr = curr.children[num];
			}

			curr = reverses[words[i].length()];
			for (int j = words[i].length() - 1; j >= 0; j--) {
				curr.cnt++;
				int num = words[i].charAt(j) - 'a';
				if (curr.children[num] == null) {
					curr.children[num] = new Node();
				}
				curr = curr.children[num];
			}
		}

		for (int i = 0; i < queries.length; i++) {
			if (queries[i].charAt(0) == '?') { // ?가 앞에 있으면 reverse 사용
				Node curr = reverses[queries[i].length()];
				for (int j = queries[i].length() - 1; j >= 0; j--) {
					if (queries[i].charAt(j) == '?') {
						ans.add(curr.cnt);
						break;
					}
					int num = queries[i].charAt(j) - 'a';
					if (curr.children[num] == null) {
						ans.add(0);
						break;
					}
					curr = curr.children[num];
				}
			} else { // ?가 뒤에 있으면 nodes 사용
				Node curr = nodes[queries[i].length()];
				for (int j = 0; j < queries[i].length(); j++) {
					if (queries[i].charAt(j) == '?') {
						ans.add(curr.cnt);
						break;
					}
					int num = queries[i].charAt(j) - 'a';
					if (curr.children[num] == null) {
						ans.add(0);
						break;
					}
					curr = curr.children[num];
				}
			}
		}
		int[] answer = new int[ans.size()];

		for (int i = 0; i < ans.size(); i++) {
			answer[i] = ans.get(i);
		}
		return answer;
	}
}