package PGS_순위_검색;

import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        // cpp java python
        // backend frontend
        // junior senior
        // chicken pizza
        // 24개의 리스트 생성
        List<Integer>[][][][] list = new ArrayList[3][2][2][2];
        for(int a = 0; a < 3; a++) 
            for(int b = 0; b < 2; b++) 
                for(int c = 0; c < 2; c++) 
                    for(int d = 0; d < 2; d++) list[a][b][c][d] = new ArrayList<>();
        
        // 해당하는 배열 내에 존재하는 리스트에 코테 점수를 넣어준다
        for(int i = 0; i < info.length; i++){
            String[] temp = info[i].split(" ");
            
            int language = 0;
            if("java".equals(temp[0])) language = 1;
            else if("python".equals(temp[0])) language = 2;
            
            int jobTitle = 0;
            if("frontend".equals(temp[1])) jobTitle = 1;
            
            int experience = 0;
            if("senior".equals(temp[2])) experience = 1;
            
            int soulFood = 0;
            if("pizza".equals(temp[3])) soulFood = 1;
            
            list[language][jobTitle][experience][soulFood].add(Integer.parseInt(temp[4]));
        }
        
        // 24개의 리스트를 정렬
        for(int a = 0; a < 3; a++) 
            for(int b = 0; b < 2; b++) 
                for(int c = 0; c < 2; c++) 
                    for(int d = 0; d < 2; d++) Collections.sort(list[a][b][c][d]);
        
        // '-'가 들어오면 해당 하는 부분은 전부 탐색
        int[] answer = new int[query.length];
        for(int i = 0; i < query.length; i++) {
            String[] tmp = query[i].split(" and ");
            String[] tmp2 = tmp[3].split(" ");
            
            // -를 5로 설정해서 5면 해당 카테고리를 전부 다 확인하게 함
            int language = 0;
            if("java".equals(tmp[0])) language = 1;
            else if("python".equals(tmp[0])) language = 2;
            else if("-".equals(tmp[0])) language = 5;
            
            int jobTitle = 0;
            if("frontend".equals(tmp[1])) jobTitle = 1;
            else if("-".equals(tmp[1])) jobTitle = 5;
            
            int experience = 0;
            if("senior".equals(tmp[2])) experience = 1;
            else if("-".equals(tmp[2])) experience = 5;
            
            int soulFood = 0;
            if("pizza".equals(tmp2[0])) soulFood = 1;
            else if("-".equals(tmp2[0])) soulFood = 5;
            
            int count = 0;
            int point = Integer.parseInt(tmp2[1]);
            for(int a = 0; a < 3; a++){
                if(language != 5 && language != a) continue;
                for(int b = 0; b < 2; b++){
                    if(jobTitle != 5 && jobTitle != b) continue;
                    for(int c = 0; c < 2; c++){
                        if(experience != 5 && experience != c) continue;
                        for(int d = 0; d < 2; d++){
                            if(soulFood != 5 && soulFood != d) continue;
                            
                            // 리스트를 특정했다면 이분탐색으로 
                            // 해당 조건에 맞는 인원 수 카운트
                            int left = 0;
                            int right = list[a][b][c][d].size()-1;
                            
                            while(left <= right){
                                int mid = (left + right) / 2;
                                if(list[a][b][c][d].get(mid) < point) left = mid + 1;
                                else right = mid - 1;
                            }
                            count += list[a][b][c][d].size() - left;
                        }
                    }
                }
            } 
            answer[i] = count;
        }
        return answer;
    }
}