import java.util.*;

public class Main {

//    static ArrayList[] tree;
    static int[] tree;
    static int[] visited;
    static int answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {

            // 테스트 시작
            int N = sc.nextInt();

            // 그래프 정보
            tree = new int[N+1];

            for (int i = 0; i < N-1; i++) { // N-1개만 있음
                int from = sc.nextInt();
                int to = sc.nextInt();

                // 반대로 저장 -> 각 노드의 부모 정보 저장 (1개씩)
                tree[to] = from;
            }

            // 순회
            int targetA = sc.nextInt();
            int targetB = sc.nextInt();

            // A
            visited = new int[N + 1];
            findA(targetA);

            // B
            answer = 0;
            findB(targetB);

            // 정답 출력하기
            System.out.println(answer);

        } // 테스트 끝

    }

    public static void findA(int curNode) {

        visited[curNode] = 1;

        if (tree[curNode] == 0) return;
        int parent = tree[curNode];
        findA(parent);
    }

    public static void findB(int curNode) {

        if (visited[curNode] == 1) {
            answer = curNode;
            return;
        }

        if (tree[curNode] == 0) return; // 의미X
        int parent = tree[curNode];
        findB(parent);
    }
}