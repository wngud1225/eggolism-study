package PGS_합승택시요금;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        // 시간을 다 초로 변경
        String[] temp = play_time.split(":");
        int totalTime = Integer.parseInt(temp[0]) * 60 * 60 + Integer.parseInt(temp[1]) * 60 + Integer.parseInt(temp[2]);
        temp = adv_time.split(":");
        int advTime = Integer.parseInt(temp[0]) * 60 * 60 + Integer.parseInt(temp[1]) * 60 + Integer.parseInt(temp[2]);
        int[] time = new int[totalTime+1];
        
        // 누적합을 위한 값을 입력
        for(int i = 0; i < logs.length; i++){
            temp = logs[i].split("-");
            String[] tmp = temp[0].split(":");
            time[Integer.parseInt(tmp[0]) * 60 * 60 + Integer.parseInt(tmp[1]) * 60 + Integer.parseInt(tmp[2])]++;
            tmp = temp[1].split(":");
            time[Integer.parseInt(tmp[0]) * 60 * 60 + Integer.parseInt(tmp[1]) * 60 + Integer.parseInt(tmp[2])]--;
        }
        
        // 누적합 계산
        for(int i = 1; i < totalTime; i++) time[i] += time[i-1];
        
        // 최대값 찾기
        long sum = 0;
        int startTime = 0;
        for(int i = 0; i < advTime; i++) sum += time[i];
        long max = sum;
        // 앞에꺼 하나 빼고 뒤에꺼 하나 추가
        for(int i = 1; i <= totalTime - advTime; i++){
            sum -= time[i-1];
            sum += time[i+advTime-1];
            
            if(sum > max) {
                max = sum;
                startTime = i;
            }
        }
        
        // 최대값일 때의 startTime을 다시 String형태로 변환
        String answer = "";
        if(startTime / 3600 <= 9) answer += "0";
        answer += startTime / 3600;
        answer += ":";
        if(startTime % 3600 / 60 <= 9) answer += "0";
        answer += startTime % 3600 / 60;
        answer += ":";
        if(startTime % 60 <= 9) answer += "0";
        answer += startTime % 60;       
        
        return answer;
    }
}