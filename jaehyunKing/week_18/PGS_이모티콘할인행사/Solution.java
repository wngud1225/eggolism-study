package PGS_이모티콘할인행사;

// 중복 순열 할인율을 골라줌 -> 조건 만족하는지 확인

class Solution {
    int[] discountRate;
    int joinCount = 0;
    int totalPrice = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        discountRate = new int[emoticons.length];
        perm(0, users, emoticons);
        int[] answer = {joinCount, totalPrice};
        return answer;
    }
    
    // 중복 순열
    public void perm(int idx, int[][] users, int[] emoticons){
        if(idx == emoticons.length){
            int tempTotalPrice = 0;
            int tempJoinCount = 0;
            for(int i = 0; i < users.length; i++){
                int tempUserPrice = 0;
                for(int j = 0; j < emoticons.length; j++){
                	// 소수점이 나오지 않게 하기위함(가격은 100의 배수)
                    int price = emoticons[j] / 100 * (100 - discountRate[j]);
                    // 할인율이 크거나 같으면 구매
                    if(users[i][0] <= discountRate[j]) tempUserPrice += price;
                }
                // 가격이 플러스 가입 기준이상이면 가입
                if(tempUserPrice >= users[i][1]) tempJoinCount++;
                // 아니면 가격 추가
                else tempTotalPrice += tempUserPrice;
            }
            // 서비스 가입자 수 비교, 같다면 이모티콘 판매액이 더 높은 것을 선택
            if(joinCount < tempJoinCount || (joinCount == tempJoinCount && totalPrice < tempTotalPrice)){
                joinCount = tempJoinCount;
                totalPrice = tempTotalPrice;
            }           
            return;
        }
        
        discountRate[idx] = 40;
        perm(idx+1, users, emoticons);
        
        discountRate[idx] = 30;
        perm(idx+1, users, emoticons);
        
        discountRate[idx] = 20;
        perm(idx+1, users, emoticons);
        
        discountRate[idx] = 10;
        perm(idx+1, users, emoticons);
        
    }
}