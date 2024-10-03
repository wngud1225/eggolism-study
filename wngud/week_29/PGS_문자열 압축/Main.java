import java.util.*;

class Solution {
    public int solution(String s) {
        
        // 가능한 단위 찾기 -> 그냥 뒤에 넣는 것이었음
        
        // 자르기
        int answer = s.length(); // 똑같은 길이인 경우 포함
        int len = s.length();
        
        for (int unit = 1; unit < s.length(); unit++) {
            String result = "";
            String tmp = "";
            int duration = 1;
            
            // 한 단위 작업
            for (int j = 0; j + unit <= len; j += unit) { // 디버깅: 간격 조심
                String now = s.substring(j, j + unit);
                
                // 디버깅: 처음처리
                if (j == 0) {
                    tmp = now;
                    continue;
                }
                
                if (now.equals(tmp)) {
                    duration++;
                } else{
                    if (duration == 1) {
                        result += tmp;
                        tmp = now;
                    } else {
                        result += duration;
                        result += tmp;
                        tmp = now;
                        // 초기화
                        duration = 1;
                    }
                }
            }
            
            // 마지막 처리
            if (duration == 1) {
                result += tmp;
            } else {
                result += duration;
                result += tmp;
            }
            
            // 남은 것 처리
            if (s.length() % unit != 0) {
                result += s.substring(unit * (s.length() / unit));
            }
            
            // 평가
            // System.out.println("=== 해당 단위: " + unit);
            // System.out.println(result);
            // System.out.println(result.length());
            answer = Math.min(answer, result.length());
        }
        
        return answer;
    }
}