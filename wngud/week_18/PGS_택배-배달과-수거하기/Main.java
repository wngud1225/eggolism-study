class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int devSave = 0;
        int picSave = 0;
        
        for (int i = deliveries.length-1; i >= 0; i--) {
            // 어쨌든 한번 움직이면 둘 다 더하거나 빼줘야 함
            devSave -= deliveries[i]; 
            picSave -= pickups[i];
            
            // 음수가 있다면 save가 없어서 왔어야 함
            // 양수가 있다면 이후에 save의 것으로 이동 가능
            while (devSave < 0 || picSave < 0) { // 여러번 오는 경우
                devSave += cap;
                picSave += cap;
                
                answer += (i+1) * 2; // 무조건 왕복
            }
        }
        
        return answer;
    }
}