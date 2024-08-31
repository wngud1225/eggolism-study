import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        
        // 기본 틀 만들기
        Map<String, List<Integer>> map = new HashMap<>();
        String[] list1 = {"cpp", "java", "python", "-"};
        String[] list2 = {"backend", "frontend", "-"};
        String[] list3 = {"junior", "senior", "-"};
        String[] list4 = {"chicken", "pizza", "-"};
        
        for (String a : list1) {
            for (String b : list2) {
                for (String c : list3) {
                    for (String d : list4) {
                        map.put(a + b + c + d, new ArrayList<Integer>());
                    }
                }
            }
        }
        
        // info 넣기
        for (String member : info) {
            String[] inputs = member.split(" ");
            String[] mList1 = {inputs[0], "-"};
            String[] mList2 = {inputs[1], "-"};
            String[] mList3 = {inputs[2], "-"};
            String[] mList4 = {inputs[3], "-"};
            
            for (String a : mList1) {
                for (String b : mList2) {
                    for (String c : mList3) {
                        for (String d : mList4) {
                            map.get(a + b + c + d).add(Integer.parseInt(inputs[4]));
                        }
                    }
                }
            }
        }
        
        // ordering by score
        for (String key : map.keySet()) {
            List<Integer> list = map.get(key);
            Collections.sort(list);
            // System.out.println(list.toString());
        }
        
        // query search
        int[] answer = new int[query.length];
        int idx = 0;
        for (String q : query) {
            String[] input = q.split(" ");
            String cond = input[0] + input[2] + input[4] + input[6];
            // System.out.println("find condition: " + cond);
            answer[idx++] = map.get(cond).size();
            
            // score -> 효율성 문제 발생
            int higher = binarySearch(map.get(cond), Integer.parseInt(input[7]));
            // System.out.println(higher);
            answer[idx - 1] -= higher;
        }
        
        return answer;
    }
    
    public int binarySearch(List<Integer> list, int target) {
        int lft = 0;
        int rgt = list.size()-1;
        int res = list.size(); // 없는 경우 대비
        
        while (lft <= rgt) {
            int mid = (lft + rgt) / 2;
            
            if (list.get(mid) >= target) {
                res = mid; // 조심
                rgt = mid - 1;
            } else {
                lft = mid + 1;
            }
        }
        
        return res;
    }
    
}