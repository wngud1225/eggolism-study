package PGS_순위검색;

class Solution {

	public int[] solution(String[] info, String[] query) {

		int[][][][][] scores = new int[4][3][3][3][100001];

		for (int i = 0; i < info.length; i++) {
			String[] input = info[i].split(" ");
			int lang = 0;
			int end = 0;
			int age = 0;
			int food = 0;
			int score = Integer.parseInt(input[4]);

			if (input[0].equals("cpp")) {
				lang = 1;
			} else if (input[0].equals("java")) {
				lang = 2;
			} else if (input[0].equals("python")) {
				lang = 3;
			}
			if (input[1].equals("backend")) {
				end = 1;
			} else if (input[1].equals("frontend")) {
				end = 2;
			}
			if (input[2].equals("junior")) {
				age = 1;
			} else if (input[2].equals("senior")) {
				age = 2;
			}
			if (input[3].equals("chicken")) {
				food = 1;
			} else if (input[3].equals("pizza")) {
				food = 2;
			}
			scores[lang][end][age][food][score]++;
			scores[0][end][age][food][score]++;
			scores[lang][0][age][food][score]++;
			scores[lang][end][0][food][score]++;
			scores[lang][end][age][0][score]++;
			scores[0][0][age][food][score]++;
			scores[0][end][0][food][score]++;
			scores[0][end][age][0][score]++;
			scores[lang][0][0][food][score]++;
			scores[lang][0][age][0][score]++;
			scores[lang][end][0][0][score]++;
			scores[0][0][0][food][score]++;
			scores[0][0][age][0][score]++;
			scores[0][end][0][0][score]++;
			scores[lang][0][0][0][score]++;
			scores[0][0][0][0][score]++;
		}
		for (int i = 100000; i >= 1; i--) {
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 3; b++) {
					for (int c = 0; c < 3; c++) {
						for (int d = 0; d < 3; d++) {
							scores[a][b][c][d][i - 1] += scores[a][b][c][d][i];
						}
					}
				}
			}
		}
		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			String[] input = query[i].split(" ");
			int lang = 0;
			int end = 0;
			int age = 0;
			int food = 0;
			int score = Integer.parseInt(input[7]);

			if (input[0].equals("cpp")) {
				lang = 1;
			} else if (input[0].equals("java")) {
				lang = 2;
			} else if (input[0].equals("python")) {
				lang = 3;
			}
			if (input[2].equals("backend")) {
				end = 1;
			} else if (input[2].equals("frontend")) {
				end = 2;
			}
			if (input[4].equals("junior")) {
				age = 1;
			} else if (input[4].equals("senior")) {
				age = 2;
			}
			if (input[6].equals("chicken")) {
				food = 1;
			} else if (input[6].equals("pizza")) {
				food = 2;
			}
			answer[i] = scores[lang][end][age][food][score];
		}

		return answer;
	}
}