import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        // 보석 정보
        int[][] info = new int[N][2];
        for (int i = 0; i < N; i++) {
            int m = sc.nextInt();
            int v = sc.nextInt();
            info[i][0] = m;
            info[i][1] = v;
        }

        // 가방 정보
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            int num = sc.nextInt();
            bags[i] = num;
        }

        // 보석은 무게를 기준으로 오름차순
        Arrays.sort(info, ((o1, o2) -> {
            return o1[0] - o2[0];
        }));

        // 가방 오름차순으로 정렬
        Arrays.sort(bags);

        // 모든 가방 순회하기
        long answer = 0;
        int idx = 0; // 현재까지 검색한 보석 무게 -> 오름차순이라서 이전 것은 안해도 가능
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < K; i++) {
            int curBag = bags[i];

            // 이전 무게 측정 이후만 하면 됨
            while (idx < N) {
                if (info[idx][0] <= curBag) {
                    queue.add(info[idx][1]); // 가치만 비교하면 됨
                    idx++;
                } else {
                    break;
                }
            }

            // 지금까지 저장한 큐 원소와 높아진 가방 무게로 추가된 큐 원소를 넣고 가장 높은 것
            if (!queue.isEmpty()) {
                answer += queue.poll();
            }

        }

        // 정답 출력하기
        System.out.println(answer);


    }
}
