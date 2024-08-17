package PGS_괄호변환;

class Solution {

	public String solution(String p) {
		String answer = function(p);
		return answer;
	}

	static String function(String str) {

		if (str.equals("")) {
			return "";
		}

		String u = str.substring(0, 1);
		int left = 0;
		int right = 0;

		if (u.equals(")")) {
			right++;
		} else {
			left++;
		}

		int idx = 1;

		while (left != right) {
			String tmp = str.substring(idx, ++idx);
			if (tmp.equals(")")) {
				right++;
			} else {
				left++;
			}
			u += tmp;
		}

		String v = "";
		for (int i = idx; i < str.length(); i++) {
			v += str.substring(i, i + 1);
		}

		if (check(u)) {
			return u + function(v);
		} else {
			String rtn = "(";
			rtn += function(v);
			rtn += ")";
			for (int i = 1; i < u.length() - 1; i++) {
				String tmp = str.substring(i, i + 1);
				if (tmp.equals(")")) {
					rtn += "(";
				} else {
					rtn += ")";
				}
			}
			return rtn;
		}
	}

	static boolean check(String str) {
		int left = 0;
		int right = 0;

		for (int i = 0; i < str.length(); i++) {
			String tmp = str.substring(i, i + 1);
			if (tmp.equals(")")) {
				right++;
			} else {
				left++;
			}
			if (right > left) {
				return false;
			}
		}
		return true;
	}
}