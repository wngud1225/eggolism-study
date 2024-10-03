import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        Map<String, String> nameMap = new HashMap<>();
        List<String[]> logs = new ArrayList<>();
        
        for (int i = 0; i < record.length; i++) {
            String[] input = record[i].split(" ");
            
            if (input[0].equals("Enter")) {
                // 이름 최신화
                nameMap.put(input[1], input[2]); 
                // 로그 남기기
                logs.add(new String[]{input[1], "님이 들어왔습니다."});
            } else if (input[0].equals("Leave")) {
                // 로그 남기기
                logs.add(new String[]{input[1], "님이 나갔습니다."});
            } else if (input[0].equals("Change")) {
                // 이름 최신화
                nameMap.put(input[1], input[2]);
            }
            // System.out.println(nameMap.toString());
        }
        
        // 임시 결과 사용
        String[] answer = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            String output = nameMap.get(logs.get(i)[0]);
            output += logs.get(i)[1];
            answer[i] = output;
        }
        
        return answer;
    }
}