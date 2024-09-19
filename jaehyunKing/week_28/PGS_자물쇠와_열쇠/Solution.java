package PGS_자물쇠와_열쇠;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;
        boolean answer = false;

        total: for(int i = 0; i < 4; i++){
		        // key를 회전
            if(i != 0) key = rotateKey(key);
            
            // r, c는 key[m-1][m-1]을 lock[r][c]에 매칭시키는 용도
            // r2, c2는 완탐 용도 -> 조건을 만족하는 지 확인
		        for(int r = 0; r < n+m-1; r++){
            loop: for(int c = 0; c < n+m-1; c++){
                    for(int r2 = 0; r2 < n; r2++){
                        for(int c2 = 0; c2 < n; c2++){
                            int keyY = m - 1 + r2 - r; // 현재 r2, c2칸에 맞는 keyY, keyX로 설정                            
                            int keyX = m - 1 + c2 - c;
                            // 설정한 keyY, keyX가 index안에 있다면 매칭되는 key가 있는 것
                            if(0 <= keyY && keyY < m && 0 <= keyX && keyX < m){
                                if(lock[r2][c2] + key[keyY][keyX] != 1) continue loop;
                            }
                            // 매칭되는 key가 없다면
                            else {
                                if(lock[r2][c2] != 1) continue loop;
                            }
                            // 끝까지오는 데 성공했다면 
                            if(r2 == n-1 && c2 == n-1) {
                                answer = true;
                                break total;
                            }
                        }
                    }
                }
            }
        }
        
        return answer;
    }
    
    // 90도 회전 메서드
    public int[][] rotateKey(int[][] key) {
		    int m = key.length;
        int[][] rotatedKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotatedKey[j][m - 1 - i] = key[i][j];
            }
        }
        return rotatedKey;
    }
}