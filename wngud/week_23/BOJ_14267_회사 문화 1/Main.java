import java.util.*;
import java.io.*;

public class Main {
    public static List<ArrayList<Integer>> graph;
    public static int[] prefixSum;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 그래프 입력
        graph = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (i == 1) continue;
            graph.get(num).add(i);
        }

        // 칭찬 주기
        prefixSum = new int[N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int employee = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());

            // 칭찬 누적 점찍기
            prefixSum[employee] += point;
        }

        // 누적합 하기 -> 아래로 쭉 한 번만 하면 된다.
        dfs(1, prefixSum[1]);


        // 정답 출력하기
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(prefixSum[i]).append(" ");
        }
        System.out.println(sb);

    }

    public static void dfs(int em, int p) {

        // 리프 노드면 끝내기
        if (graph.get(em).size() == 0) return;

        // 계속 돌기
        for (int i = 0; i < graph.get(em).size(); i++) {
            int nxt = graph.get(em).get(i);
            prefixSum[nxt] += p; // 상위 노드의 점수 추가하기
            dfs(nxt, prefixSum[nxt]); // 나의 점수를 가지고 아래로 내리기
        }

    }

}