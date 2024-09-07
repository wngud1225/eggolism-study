import java.util.*;
class Solution {
    public int[] solution(String[] words, String[] queries) {
        
        // 문자열 길이 별 배열을 하나 만든다 (정순, 역순 2개)
        Node[] node = new Node[10001];  //kak??
        Node[] reverseNode = new Node[10001]; //??kao -> oak??
        
        // Node 객체 생성
        for (int i = 0; i < 10001; i++) {
            node[i] = new Node();
            reverseNode[i] = new Node();
        }
        
        // 입력 부분
        // Node에 count, map을 만들어서 한 단어를 타고타고 만들 수 있게 한다 
        for(int i = 0; i < words.length; i++){
            String tmp = words[i];
            Node now = node[tmp.length()]; // 노드 배열에서 가져옴 -> depth별
            Node reverseNow = reverseNode[tmp.length()];
            
            for(int j = 0; j < tmp.length(); j++){
                char c = tmp.charAt(j);
                if (now.child.get(c) == null) {
                    now.child.put(c, new Node());
                }
                now.child.get(c).count++;
                now = now.child.get(c);
                
                char reverse = tmp.charAt(tmp.length()-1-j);
                if(reverseNow.child.get(reverse) == null){
                    reverseNow.child.put(reverse, new Node());
                }
                reverseNow.child.get(reverse).count++;
                reverseNow = reverseNow.child.get(reverse);
            }
        }
        
        // 출력 부분
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            String tmp = queries[i];
            
            // 맨 앞이 ?
            if(tmp.charAt(0) == '?'){
		            // 맨 뒤도 ? -> 전부 다 ?
                if(tmp.charAt(tmp.length()-1) == '?') {
		                // 배열에 해당 문자열 길이에 맞는 곳으로 이동 
		                // -> 모든 child map의 count를 합산
                    for (Character key : node[tmp.length()].child.keySet()) {
                        Node child = node[tmp.length()].child.get(key);
                        answer[i] += child.count;
                    }
                } else {
		                // reverseNode 배열로 진행
                    Node now = reverseNode[tmp.length()];
                    for(int j = tmp.length()-1; j >= 0; j--){
		                    // 현재 문자
                        char c = tmp.charAt(j);
                        // 현재 문자가 ?면 현재 count를 answer에 넣어줌
                        if(c == '?') {
                            answer[i] = now.count;
                            break;
                        // ?가 아닌데 null이라면 0을 출력(쿼리는 ?로 시작하거나 끝남)
                        } else if (now.child.get(c) == null) {
                            answer[i] = 0;
                            break;
                        }
                        // 현재 꺼를 다음 꺼로 바꿔 줌
                        now = now.child.get(c);
                    }
                }
            } else {
		            // 그냥 node 배열로 진행
		            // 위랑 같은 로직
                Node now = node[tmp.length()];
                for(int j = 0; j < tmp.length(); j++){
                    char c = tmp.charAt(j);
                    if(c == '?') {
                        answer[i] = now.count;
                        break;
                    }
                    else if (now.child.get(c) == null) {
                        answer[i] = 0;
                        break;
                    }
                    now = now.child.get(c);
                }
            }
        }
        return answer;
    }
}

class Node {
    int count;
    Map<Character, Node> child = new HashMap<>();
}