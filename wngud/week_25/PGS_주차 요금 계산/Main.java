import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        int[] cars = new int[10000];
        int[] ins = new int[10000];
        Arrays.fill(ins, -1); // 맨 처음 0인 경우 예외 처리
        TreeSet<Integer> set = new TreeSet<>();
        
        for (String record : records) {
            // input
            String[] input = record.split(" ");
            int time = transformToTime(input[0]);
            int carNum = Integer.parseInt(input[1]);
            String dir = input[2];
            
            // in case
            if (dir.equals("IN")) {
                set.add(carNum); // 중복 가능
                ins[carNum] = time;
            }
            
            // out case
            else {
                int result = time - ins[carNum];
                cars[carNum] += result;
                ins[carNum] = -1;
            }
        }
        
        Iterator iter = set.iterator();
        int[] answer = new int[set.size()];
        int idx = 0;
        while (iter.hasNext()) {
            // 남은 자동차 예외 처리
            int target = (int) iter.next();
            if (ins[target] != -1) {
                cars[target] += (23*60 + 59) - ins[target];
            }  
            
            // 기본 요금
            if (cars[target] <= fees[0]) {
                answer[idx++] = fees[1];
            }
            
            // 초과요금
            else {
                answer[idx++] = fees[1];
                // 올림
                if ((cars[target] - fees[0]) % fees[2] > 0) {
                    answer[idx - 1] += (((cars[target] - fees[0]) / fees[2]) + 1) * fees[3];
                } else {
                    answer[idx - 1] += ((cars[target] - fees[0]) / fees[2]) * fees[3];
                }
                
            }
            
            System.out.println(target + " " + cars[target] + " " + answer[idx-1]);  
            
        }   
        
        return answer;
    }
    
    public int transformToTime(String input) {
        String[] inputString = input.split(":");
        int result = 60 * Integer.parseInt(inputString[0]);
        result += Integer.parseInt(inputString[1]);
        return result;
    }
}