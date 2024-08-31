package PGS_k진수에서_소수_개수_구하기;

class Solution {
    public int solution(int n, int k) {
        // 10진수 -> k진수
        String num = Integer.toString(n, k);
        
        // split('0') -> k진수를 자르기
        String[] nums = num.split("0");

        // n진수 -> 10진수
        int answer = 0;
        loop: for(int i = 0; i < nums.length; i++){
            if(nums[i].equals("")) continue;
            long temp = Long.parseLong(nums[i]);
            
            // 숫자가 있을 때 -> 루트 숫자만큼만 확인하면됨(Math.sqrt())
            long maxCount = (long) Math.sqrt(temp);  
            
            for(int j = 2; j <= maxCount; j++) {
                if(temp % j == 0) continue loop;
            }
            if(temp != 1) answer++;
        }
        return answer;
    }
}