package BOJ_21275;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str1 = sc.next();
		String str2 = sc.next();

		Map<Long, Integer> map = new HashMap<>();

		start: for (int i = 36; i >= 2; i--) {

			long tmp = 0;

			for (int j = 0; j < str1.length(); j++) {

				if (toInt(str1.charAt(j)) >= i) {
					break start;
				}

				tmp += Math.pow(i, str1.length() - j - 1) * toInt(str1.charAt(j));
				
				if (tmp >= Long.MAX_VALUE) {
					continue start;
				}
			}
			map.put(tmp, i);
		}

		int cnt = 0;
		long X = 0;
		int A = 0;
		int B = 0;

		start: for (int i = 36; i >= 2; i--) {

			long tmp = 0;

			for (int j = 0; j < str2.length(); j++) {

				if (toInt(str2.charAt(j)) >= i) {
					break start;
				}

				tmp += Math.pow(i, str2.length() - j - 1) * toInt(str2.charAt(j));
				
				if (tmp >= Long.MAX_VALUE) {
					continue start;
				}
			}
			if (map.get(tmp) != null && map.get(tmp) != i) {
				cnt++;
				X = tmp;
				A = map.get(tmp);
				B = i;
			}
		}

		if (cnt == 1) {
			System.out.println(X + " " + A + " " + B);
		} else if (cnt == 0) {
			System.out.println("Impossible");
		} else {
			System.out.println("Multiple");
		}
	}

	static int toInt(char c) {

		if (c <= '9') {
			return c - 48;
		} else {
			return c - 87;
		}
	}
}