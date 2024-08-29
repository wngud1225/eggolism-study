package PGS_주차_요금_계산;

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 숫자가 적은 차량부터 출력하기 위한 PriorityQueue
        PriorityQueue<String> pq = new PriorityQueue<>();
        // 차량 숫자 -> IN 시간, 시간 합 계산(분 단위)
        // out이면 시간을 -1로 넣기
        Map<String, int[]> map = new HashMap<>();
        
        for(int i = 0; i < records.length; i++){
            String[] tmp = records[i].split(" ");
            String[] tmpTime = (tmp[0].split(":"));
            int nowTime = Integer.parseInt(tmpTime[0]) * 60 + Integer.parseInt(tmpTime[1]);
            
            if (!map.containsKey(tmp[1])) {
                map.put(tmp[1], new int[]{nowTime, 0});
                pq.offer(tmp[1]);
            } else {
                int[] tmpMap = map.get(tmp[1]);
                //OUT -> IN
                if (tmpMap[0] == -1) {
                    map.replace(tmp[1], new int[]{nowTime, tmpMap[1]});
                //IN -> OUT 
                } else {
                    map.replace(tmp[1], new int[]{-1, tmpMap[1] + nowTime - tmpMap[0]});
                }
            }
        }
        
        int maxOutTime = 23 * 60 + 59;
        int[] answer = new int[pq.size()];
        int idx = 0;
        
        while(!pq.isEmpty()) {
            String now = pq.poll();
            int[] nowTemp = map.get(now);
            int time;
           
            // IN한 시간이 -1일 경우 현재 차량은 나간 것이므로 로직대로 계산하고,
            // -1이 아닌 경우에 23:59에 나갔다고 가정하고 계산해준다.
            if(nowTemp[0] != -1){
                time = nowTemp[1] + maxOutTime - nowTemp[0];
            } else {
                time = nowTemp[1];
            }
            
            int price = 0;
            // 이용 시간이 기본 시간 내일 때
            if (time <= fees[0]) {
                price = fees[1];
            // 초과일 때는 나눴을 때 나머지가 있으면 올림 처리(+1)
            } else {
                if ((time - fees[0]) % fees[2] == 0) {
                    price = fees[1] + (time - fees[0]) / fees[2] * fees[3];
                } else {
                    price = fees[1] + ((time - fees[0]) / fees[2] + 1) * fees[3];
                }
            }
            answer[idx] = price;
            idx++;
        }
        
        return answer;
    }
}