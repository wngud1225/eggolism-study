import java.util.*;
import java.io.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        // 1. 문자열 처리
        int play_time_num = parse(play_time);
        int adv_time_num = parse(adv_time);
        
        int[][] logs_num = new int[logs.length][2];
        for (int i = 0; i < logs.length; i++) {
            String[] input = logs[i].split("-");
            logs_num[i][0] = parse(input[0]);
            logs_num[i][1] = parse(input[1]); 
        }
        
        // 2. logs를 가지고 prefix_sum 점 직기
        int[] prefix_point = new int[play_time_num+50]; // 넉넉(1~)
        for (int i = 0; i < logs_num.length; i++) {
            prefix_point[logs_num[i][0]] += 1;
            prefix_point[logs_num[i][1]] -= 1; // 1 추가 유무 -> 없이로 가도 재생 시간표현 가능
        }
        
        // 3. prefix_sum 만들기
        int[] prefix = new int[play_time_num+50]; // 넉넉(1~)
        int num = 0;
        for (int i = 0; i < prefix_point.length; i++) {
            num += prefix_point[i];
            prefix[i] = num;
        }
        
        // 4. 가장 높은 시작점 구하기
        // 초기값 설정하기
        int answer_num = 0;
        long answer_sum = 0;
        long sum = 0;

        for (int i = 0; i < adv_time_num; i++) { // 등호표시 유뮤!! -> 자연스럽게 없이
            sum += prefix[i];
        }        
        
        // 다음부터 시작 -> 0부터 빼기 시작!!
        answer_sum = sum;
        for (int i = 0; i < play_time_num - adv_time_num; i++) { // 등호 유무 -> 자연스럽게 없이
            sum -= prefix[i];
            sum += prefix[i+adv_time_num]; // +1 유무!! -> 자연스럽게 없이
        
            // 최신화 -> 같으면 최신화X
            if (sum > answer_sum) {
                answer_sum = sum;
                answer_num = i+1; // 시작은 플러스 1해줘야 함!! -> 가장 큰 문제
            }
        }
        
        
        // 정답 출력하기
        // 1) 시간 형식으로 변경
        int h = answer_num / 3600;
        answer_num -= h * 3600;
        int m = answer_num / 60;
        answer_num -= m * 60;
        int s = answer_num;
        
        // 2) 문자로 변경
        String answer = "";
        if (h == 0) {
            answer += "00";
        } else if (h < 10) {
            answer += "0";
            answer += String.valueOf(h);
        } else {
            answer += String.valueOf(h);
        }
        answer += ":";
        if (m == 0) {
            answer += "00";
        } else if (m < 10) {
            answer += "0";
            answer += String.valueOf(m);
        } else {
            answer += String.valueOf(m);
        }
        answer += ":";
        if (s == 0) {
            answer += "00";
        } else if (s < 10) {
            answer += "0";
            answer += String.valueOf(s);
        } else {
            answer += String.valueOf(s);
        }

        return answer;
        
    }
    
    
    public int parse(String input) {
        
        String[] arr = input.split(":");
        int seconds = 0;
        seconds += Integer.parseInt(arr[0]) * 3600;
        seconds += Integer.parseInt(arr[1]) * 60;
        seconds += Integer.parseInt(arr[2]);
        
        // System.out.println("변환: " + input + " -> " + seconds);    
        return seconds;
    }
}