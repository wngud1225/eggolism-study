class Solution {
    
    public String solution(String p) {
        
        // 초기 예외
        if (checkRight(p) || p.equals("")) {
            return p;
        }
        
        String answer = checkBalance(p);
        return answer;
    }
    
    public String checkBalance(String p) {
        String u = "";
        String v = "";
        int start = 0;
        int end = 0;
        
        // 반환1. 종료 조건
        if (p.equals("")) {
            return "";
        }
        
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);

            if (c == '(') start++;
            else end++;

            if (start == end) {
                u = p.substring(0, i+1);
                v = p.substring(i+1, p.length());
                break;
            }
          } 

            if (checkRight(u)) {
                // 반환2. 올바른 괄호 문자열인 경우
                return u + checkBalance(v);
            } else {
                // 반환3. 올바른 괄호 문자열이 아닌 경우
                String tmp = "(";
                tmp += checkBalance(v);
                tmp += ")";
                for (int s = 1; s < u.length()-1; s++) {
                    char k = u.charAt(s);
                    if (k == '(') tmp += ')';
                    else tmp += '(';
                }
                return tmp;
            }
    }
    
    public boolean checkRight(String input) {
        
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') count++;
            else count--;
            
            if (count < 0) return false;
        }
        return true;
    }
    
}