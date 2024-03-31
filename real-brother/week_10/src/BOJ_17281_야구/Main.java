package BOJ_17281_야구;

import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int innings;
	static int[][] hitters;
	static List<Integer> thisInning;
	static int[] fieldStatus = { 0, 0, 0 }; // 1루, 2루, 3루 상태
	static int out = 0, maxTeamScore = 0;

	static int[] hitterOrder = { 1, 2, 3, 5, 6, 7, 8, 9 };
	static int[] permutation = new int[9];
	static boolean[] visited = new boolean[8];

	public static void main(String[] args) {
		innings = sc.nextInt();
		hitters = new int[innings][9];
		for (int i = 0; i < innings; i++) {
			for (int j = 0; j < 9; j++) {
				hitters[i][j] = sc.nextInt();
			}
		}

		settingHitterOrder(0, 0);

		System.out.println(maxTeamScore);
	}

	// 순열로 타자 순서 구하기
	// 맨앞에 4번타자 두고, 그 뒤 순서를 순열로 구하기
	// 8! = 약 4만
	private static void settingHitterOrder(int start, int depth) {
		if (depth == 9) {
			simulateBaseballGame();
			return;
		}
		if (depth == 0) {
			permutation[0] = 4;
			settingHitterOrder(0, depth + 1);

		}
		for (int i = 0; i < 8; i++) {
			int order = hitterOrder[i];
			if (!visited[i]) {
				visited[i] = true;
				permutation[depth] = order;
				settingHitterOrder(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}

	private static void simulateBaseballGame() {
	    int gameScore = 0;
	    int[] applyOrder = permutation.clone();
	    // Index 맞춰주기
	    for (int i = 0; i < 9; i++) {
	        applyOrder[i] -= 1;
	    }
        int startHitter = 0; // startHitter를 이닝마다 초기화
        for (int i = 0; i < innings; i++) {
        	int[] hitterStatus = new int[9];
        	for (int j = 0; j < hitterStatus.length; j++) {
        		hitterStatus[j] = hitters[i][applyOrder[j]];
        	}
	    
	        out = 0;
	        fieldStatus = new int[]{0, 0, 0};
	        
	        inningOut:
	        while (true) {
	            for (int o = startHitter; o < 9; o++) {
	                switch (hitterStatus[o]) {
	                    case 0:
	                        out++;
	                        startHitter = o + 1;
	                        if (out == 3) {
	                            if (startHitter == 9) startHitter -= 9;
	                            break inningOut;
	                        }
	                        break;
	                    case 1:
	                        for (int j = 2; j >= 0; j--) {
	                            if (j == 2 && fieldStatus[2] == 1) {
	                                gameScore++;
	                                fieldStatus[2] = 0;
	                            } else if (fieldStatus[j] == 1) {
	                                fieldStatus[j + 1] = 1;
	                                fieldStatus[j] = 0;
	                            }
	                        }
	                        fieldStatus[0] = 1;
	                        break;
	                    case 2:
	                        for (int j = 2; j >= 0; j--) {
	                            if (j == 2 && fieldStatus[j] == 1) {
	                                gameScore++;
	                                fieldStatus[2] = 0;
	                            } else if (j == 1 && fieldStatus[j] == 1) {
	                                gameScore++;
	                                fieldStatus[1] = 0;
	                            } else if (fieldStatus[j] == 1) {
	                                fieldStatus[j + 2] = 1;
	                                fieldStatus[j] = 0;
	                            }
	                        }
	                        fieldStatus[1] = 1;
	                        break;
	                    case 3:
	                        for (int j = 2; j >= 0; j--) {
	                            if (fieldStatus[j] == 1) {
	                                gameScore++;
	                                fieldStatus[j] = 0;
	                            }
	                        }
	                        fieldStatus[2] = 1;
	                        break;
	                    case 4:
	                        int count = 1;
	                        for (int j = 0; j < 3; j++) {
	                            if (fieldStatus[j] == 1)
	                                count++;
	                        }
	                        gameScore += count;
	                        fieldStatus = new int[]{0, 0, 0};
	                        break;
	                }
	            }
	            startHitter = 0; // 이닝 종료 후 다음 이닝을 위해 초기화
	        }
	        if (maxTeamScore < gameScore)
	            maxTeamScore = gameScore;
	    }
	}

}
