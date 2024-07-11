package BOJ_1202_보석도둑;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int K = sc.nextInt();
        
        // 보석 큐(무게 기준 오름차순)
        PriorityQueue<jewel> jewelry = new PriorityQueue<>();
        for(int i = 0; i < N; i++) jewelry.add(new jewel(sc.nextInt(), sc.nextInt()));
        
        // 가방 큐(오름차순)
        PriorityQueue<Integer> bags = new PriorityQueue<>();
        for(int i = 0; i < K; i++) bags.add(sc.nextInt());
        
        long sum = 0;
        // 보석 가치 순 내림차순
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        while(!bags.isEmpty()) {
            // 무게가 작은 거 부터 가방을 뺌
            int bag = bags.poll();
            while(true) {
                // 보석 큐가 비어있으면 pq에서 하나 빼서 더 함(큐에 들어가 있는 것은 다 현재 가방에 넣을 수 있는 보석)
                if(jewelry.isEmpty()) {
                    // pq가 비어있지 않으면 더 해줌
                    if(!pq.isEmpty()) sum += pq.poll();
                    break;
                }
                jewel tmp = jewelry.poll();
                // 가방에 넣을 수 없는 보석이 나오면 그 보석을 보석 큐에 넣고
                // pq에서 하나 빼서 더 함
                if(tmp.weight > bag) {
                    jewelry.add(tmp);
                    if(!pq.isEmpty()) sum += pq.poll();
                    break;
                }
                else pq.offer(tmp.price);
            }
        }
        
        System.out.println(sum);
    }

}

class jewel implements Comparable<jewel>{
    int weight;
    int price;
    
    public jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
    
    // 무게(오름차순) 정렬
    @Override
    public int compareTo(jewel o) {
        if(this.weight < o.weight) return -1;
        return 1;
    }
    
}