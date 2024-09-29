package PGS_후보키;

import java.util.*;

public class Solution {

	static int[] sel;
	static int N;
	static int R;
	static int row;
	static int col;
	static List<int[]> answers;
	static String[][] relation_copy;

	public static int solution(String[][] relation) {

		row = relation.length; // 테이블의 행 수
		col = relation[0].length; // 테이블의 열 수
		answers = new ArrayList<>(); // 정답이 되는 sel을 담을 리스트
		relation_copy = new String[row][col]; // 참조용 카피

		for (int r = 0; r < row; r++) { // 테이블 복사중
			for (int c = 0; c < col; c++) {
				relation_copy[r][c] = relation[r][c];
			}
		}
		// 조합 nCr
		N = col; // N은 열의 길이로 고정
		R = 1; // R은 1부터 늘려갈거임

		while (R <= col) { // R은 1부터 col까지(nC1부터 nCn까지)
			sel = new int[R];
			comb(0, 0);
			R++;
		}
		return answers.size();
	}

	static boolean isContain(List<String[]> list, String[] tmp) { // tmp가 list에 존재하는지 체크
		for (String[] item : list) {
			if (Arrays.equals(item, tmp)) {
				return true;
			}
		}
		return false;
	}

	static boolean isContain() { // 새로운 답이 최소성을 만족하는지
		for (int[] item : answers) { // 기존 정답들을 하나씩 꺼내서
			int cnt = 0;
			for (int i = 0; i < item.length; i++) { // 새로운 정답과 몇 개 겹치는지 확인
				for (int j = 0; j < sel.length; j++) {
					if (item[i] == sel[j]) {
						cnt++;
					}
				}
			}
			if (cnt == item.length) { // 겹친 수가 기존 정답의 길이와 같다면 -> 최소성 만족 x
				return true;
			}
		}
		return false;
	}

	static void comb(int idx, int sidx) {
		if (sidx == R) { // 조합이 되었다면
			List<String[]> list = new ArrayList<>();
			for (int r = 0; r < row; r++) {
				String[] tmp = new String[R];
				for (int c = 0; c < R; c++) { // 조합된 열을 한 행씩 담아서
					tmp[c] = relation_copy[r][sel[c]];
				}
				if (isContain(list, tmp)) { // 이미 리스트에 있는지 확인하고
					return;
				} else { // 없으면 추가
					list.add(tmp);
				}
			}
			// 여기까지 왔으면 유일성을 만족
			if (!isContain()) { // 최소성 체크
				answers.add(Arrays.copyOf(sel, sel.length));
			}
			return;
		}
		if (idx == N) {
			return;
		}
		sel[sidx] = idx;
		comb(idx + 1, sidx + 1);
		comb(idx + 1, sidx);
	}
}