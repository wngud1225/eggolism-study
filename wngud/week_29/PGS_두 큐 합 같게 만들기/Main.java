import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        Long sum1 = 0L;
        Long sum2 = 0L;
        int len1 = queue1.length;
        int len2 = queue2.length;
        
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            que1.add(queue1[i]);
            sum2 += queue2[i];
            que2.add(queue2[i]);
        }
        
        int idx1 = 0;
        int idx2 = 0;
        int answer = 0;
        
        while (sum1 != sum2) {
            if(answer > (len1 + len2) * 2) {
                return -1;
            }
            
            if (sum1 > sum2) { // 둘 다 등호 빼야 함
                int cur = que1.poll();
                que2.offer(cur);
                sum1 -= cur;
                sum2 += cur;
            } else if (sum1 < sum2) {
                int cur = que2.poll();
                que1.offer(cur);
                sum2 -= cur;
                sum1 += cur;
            } else { // 초기 예외
                return answer;
            }
            answer++;
        }
                
        return answer;
    }
}