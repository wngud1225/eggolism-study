import java.util.*;

public class Main {

    static int[] parents;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        // 1. 자기 자신을 부모 노드로 초기화
        parents = new int[N+1]; // 실제 숫자
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int cmd = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            // 1) 합집합 -> union
            if (cmd == 0) {
                int pa = find(a);
                int pb = find(b);

                // 큰 것을 기준으로 묶기 (union)
                if (pa > pb) parents[pa] = pb;
                else parents[pb] = pa;
            }

            // 2) 같은 집합 찾기 연산 -> find
            else {
                int pa = find(a);
                int pb = find(b);

                if (pa == pb) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }

        }


    }

    public static int find(int x) {
        if (x == parents[x]) return x; // 부모 노드이면 리턴
//        return find(parents[x]); // 부모 노드가 아니면 다시 찾기
        return parents[x] = find(parents[x]); // 경로 압축까지 추가
    }
}
