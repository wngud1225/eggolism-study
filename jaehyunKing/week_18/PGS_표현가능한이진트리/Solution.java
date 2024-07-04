package PGS_표현가능한이진트리;

/*
 *  하.. 리프노드 빼고는 다 1이어야되는줄.. (1000) 이런거 안되는줄...
 *  그냥 계산 직접하려다가 귀찮아서 Long.toBinaryString 찾았음 : 10진수 -> 2진수 변환
 *  완전포화트리(?) 개수에 모자른만큼 앞에 0을 달아준다.
 *  분할정복 방식으로 확인하면서 부모가 0인데 자식이 1인 애들만 커트해낸다
 */

class Solution {
    static boolean flag;
    static String number;
	
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 0){
                answer[i] = 0;
                continue;
            }
            // 2진수로 변환
            String temp = Long.toBinaryString(numbers[i]);
            int n = 0;
            while(true){
                if(temp.length() <= Math.pow(2, n) - 1) break;
                n++;
            }
            number = "";
            // 완전포화트리? 자릿수에 모자른 만큼 앞에 0을 달아준다.
            int tmp = (int)(Math.pow(2, n) - 1 - temp.length());
            for(int j = 0; j < tmp; j++) number += "0";
            number += temp;
            
            flag = true; 
            findMid(0, number.length()-1, '1');
            if(flag) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
    
    // 분할정복
    public static void findMid(int left, int right, char parent) {
    	if(left > right) return;
    	int mid = (left + right) / 2;
        char now = number.charAt(mid);
        // 부모가 0인데 자식이 1이면 커트
    	if(parent == '0' && now == '1') {
            flag = false;
            return;
        }
        
    	findMid(left, mid-1, now);
    	findMid(mid+1, right, now);
    }
}