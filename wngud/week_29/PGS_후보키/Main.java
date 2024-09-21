import java.util.*;

class Solution {
    
    int R, C;
    List<Integer> visited;
    HashSet<String> answers = new HashSet<>();
    boolean is_contains;
    String tmpForMinial;
    
    public int solution(String[][] relation) {
        
        R = relation.length;
        C = relation[0].length; // 디버깅: 열이 속성
        
        // 탐색
        visited = new ArrayList<>();
        for (int i = 1; i <= C; i++) {
            dfs(0, 0, i, relation);
        }
        
        // 정답 출력하기
        // System.out.println("answers: " + answers.toString());
        int answer = 0;
        return answers.size();
    }
    
    public boolean checkUnique(String[][] relation) {
        
        HashSet<String> set = new HashSet<>();
        String tmp;
        
        for (int i = 0; i < R; i++) {
            tmp = "";
            for (int j = 0; j < visited.size(); j++) {
                tmp += relation[i][visited.get(j)];
                tmp += "-";
            }
            set.add(tmp);
            // 검사
            // System.out.println("set 결과: " + set.toString());
        }
        if (set.size() != R) return false;
        return true;
    }
    
    public boolean checkMinial(String[][] relation) {
        // visited 부분 집합으로 모든 경우의 수 찾기
        is_contains = false;
        tmpForMinial = "";
        subset(0);
        return !is_contains;
    }
    
    public void subset(int idx) {
        if (is_contains) return;
        
        if (idx == visited.size()) {
            // 부분 집합 구한 경우
            if (answers.contains(tmpForMinial)) {
                is_contains = true;
            }
            
            return;
        }
        
        tmpForMinial += visited.get(idx);
        subset(idx + 1);
        tmpForMinial = tmpForMinial.substring(0, tmpForMinial.length()-1); // 끝 제외
        subset(idx + 1);
    }
    
    public void dfs(int idx, int sidx, int limit, String[][] relation) {

        if (sidx == limit) {
            // System.out.println(visited.toString());
            
            // 포함시킬 열에 대하여
            boolean result = checkUnique(relation);
            
            if (result) {
                // System.out.println("가능 >> " + visited.toString());
                boolean result2 = checkMinial(relation);
                if (result2) {
                    // 정답으로 추가하기
                    String tmp = "";
                    for (int i = 0; i < visited.size(); i++) {
                        tmp += visited.get(i);
                    }
                    answers.add(tmp);
                }
            } 
            return;
        }
        
        if (idx == C) return;
        
        // 재귀
        visited.add(idx);
        dfs(idx + 1, sidx + 1, limit, relation);
        visited.remove(visited.size()-1);
        dfs(idx + 1, sidx, limit, relation);
    }
}