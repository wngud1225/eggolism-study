import java.util.*;

class Solution {
    
    boolean status;
    
    public int[] solution(long[] numbers) {
        int[] answers = new int[numbers.length];
        
        for (int t = 0; t < numbers.length; t++) {
            long num = numbers[t];
            
            // 1. 10진수를 2진수로 변환
            String binary = Long.toBinaryString(num);
            // System.out.println(binary);
            
            // 2. 포화이진트리 형식으로 만들기
            binary = perfectTree(binary);
            // System.out.println("완성된 바이너리: " + binary);
            
            // 3. 포화이진트리 순회하기 -> 0으로 끊기는지 확인
            status = true;
            check(binary, 0, binary.length()-1, '1'); // 루트노드는 고정
            
            if (status) {
                answers[t] = 1;
            } else {
                answers[t] = 0;
            }
            
        }
        
        
        return answers;
        // return answer.stream().mapToInt(Integer::intValue).toArray();; // 리스트 -> 배열
    }
    
    public void check(String binary, int a, int b, char is_dummy) {
        
        int mid = (a + b) / 2;
        // System.out.println("a: " + a + " b: " + b + " mid: " + mid + " is_dummy: " + is_dummy);
        
        // 앞 노드가 is_dummy인데 자식 노드가 1인 경우는 불가
        if (is_dummy == '0' && binary.charAt(mid) == '1') {
            status = false;
            return;
        }
        
        // 종료조건 둘이 만날 때
        if (a != b) {
            check(binary, a, mid-1, binary.charAt(mid)); // 왼쪽
            check(binary, mid+1, b, binary.charAt(mid)); // 오른쪽
        }
        
    }
    
    
    public String perfectTree(String binary) {
        
        int len = binary.length();
        int nodeCount = 1;
        int lev = 1; // 레벨마다 추가할 숫자
        
        while (len > nodeCount) {
            lev *= 2;
            nodeCount += lev;
        }
        
        int add = nodeCount - len;
        String addString = "";
        for (int i = 0; i < add; i ++) {
            addString += "0";
        }
        return addString + binary;
    }
    
}