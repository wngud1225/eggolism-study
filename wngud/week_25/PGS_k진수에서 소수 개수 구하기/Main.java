import java.util.*;
import java.io.*;

class Solution {
    
    public static Deque<Integer> queue;
    
    public int solution(int n, int k) {
        
        queue = new ArrayDeque<>();
        int answer = 0;
        
        // transform
        transform(n, k);
        System.out.println(queue.toString());
        
        // iterate
        int size = queue.size();
        String target = "";
        
        for (int t = 0; t < size; t++) {
            int num = queue.pollLast();
            if (num != 0) {
                target += num;
            } else {
                // is_primary
                if (target.length() > 0) {
                    System.out.println("check: " + target);
                    Long targetNum = Long.parseLong(target);
                    boolean result = check(targetNum);
                    if (result) answer ++;
                    System.out.println(result);
                }
                // reset
                target = "";
            }
            
        }
        
        // left target
        if (target.length() > 0) {
          System.out.println("check: " + target);
            Long targetNum = Long.parseLong(target);
            boolean result = check(targetNum);
            if (result) answer ++;
            System.out.println(result);
        }

        return answer;
    }
    
    public void transform(int n, int k) {
        
        while (n > 0) {
            queue.addLast(n % k);
            n /= k;
        }
    }
    
    public boolean check(Long num) {
        
        if (num == 1) return false;
        else if(num == 2) return true;
        else if (num == 0) return false;
        
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
}