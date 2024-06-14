import java.util.*;

// int[]로 주어진 것을 List<Integer>로 변환하기

class Solution {
    static int N, M;
    static int[] sel, visited, emoticonsArr, answer;
    static int[][] usersMatrix;
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        // 클래스 레벨의 static 변수 초기화 -> GPT 추천 (메서드 파라미터를 static으로)
        // Solution.users = users;
        // Solution.emoticons = emoticons;
        
        usersMatrix = users;
        emoticonsArr = emoticons;
        
        N = users.length;
        M = emoticons.length;
        
        // 경우의 수 구하기
        sel = new int[emoticons.length];
        visited = new int[emoticons.length];
        answer = new int[2];
        perm(0);
  
        // 정답 출력하기      
        return answer;
    }
    
    public static void perm(int sidx) {
        if (sidx == M) {
            // System.out.println(Arrays.toString(sel));
            
            // 이모티콘 한개
            int totalPurchase = 0;
            int totalRegister = 0;

            // 사용자 순회
            for (int i = 0; i < N; i++) {

                // 사용자 한명
                int[] user = usersMatrix[i];
                int purchase = 0;

                // 이모티콘 가격 구하기
                for (int k = 0; k < M; k++) {
                    if (sel[k] >= user[0]) purchase += (emoticonsArr[k] * (100 - sel[k])) / 100; // 계산 조심
                }

                // 사용자 판별하기
                if (purchase >= user[1]) {
                    totalRegister++;
                } else {
                    totalPurchase += purchase;
                }

            } // 사용자 for문 끝

            // 이모티콘 판별
            if (answer[0] < totalRegister) {
                answer[0] = totalRegister;
                answer[1] = totalPurchase;
            } else if (answer[0] == totalRegister) {
                if (answer[1] < totalPurchase) {
                    answer[1] = totalPurchase;
                }
            }
            return;
        }
        
        // 4개까지 들어가야 함
        for (int i = 0; i < 4; i++) {
            if (visited[sidx] == 1) continue; 
            sel[sidx] = (i+1)*10;
            visited[sidx] = 1;
            perm(sidx+1);
            visited[sidx] = 0;
        }     
    }
    
}