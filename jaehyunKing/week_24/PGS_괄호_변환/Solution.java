package PGS_괄호_변환;

class Solution {
    public String solution(String p) {
		    // 빈 문자열이면 그대로 반환
        if(p.equals("")) return p;
        
        // 초기 세팅
        String answer = "";
        int count = 0;
        int stack = 0;
        boolean correct = true;
        
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            
            // 닫는 괄호가 더 많이 나오면 올바른 괄호 문자열 X
            if(count < 0) correct = false;
            
            // 균형잡힌 괄호 문자열
            if(count == 0) {
		            // 올바른 괄호 문자열이면 그냥 갖다붙힘
                if(correct) {
                    answer += p.substring(stack, i+1);
                } else {
                
		                //올바르지 않은 괄호 문자열 ( + v(재귀) + ) + u(뒤집은 것)
                    String tmp = p.substring(stack+1, i);
                    String tmpReverse = "";
                    for(int j = 0; j < tmp.length(); j++){
                        if(tmp.charAt(j) == '(') tmpReverse += ")";
                        else tmpReverse += "(";
                    }
                    answer += "(" + solution(p.substring(i + 1)) + ")";
                    answer += tmpReverse;
                    break;
                }
                // 해당 위치부터 자르기 위함
                stack = i+1;
                correct = true;
            }
        }
        
        return answer;
    }
}