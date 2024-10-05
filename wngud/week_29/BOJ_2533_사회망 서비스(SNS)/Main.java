import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제
 * 1. 얼리어답터가 아닌 사람은 자신의 친구 모두가 얼리어답터일 때만 정보를 받아드림.
 * 2. 최소의 얼리어답터를 확보해서 아이디어 퍼지게 해야 함.
 * 3. 한 노드는 얼리어답터 or not.
 *
 * 4. 최소를 구해야 하는 문제.
 * 5. 선택 기준 -> 연결된 친구가 많으면 좋음.
 *
 * 6. 그래프가 아니라 트리다!
 * 7. 리프노드에 하는 경우가 있으려나?
 *  -> 그렇게 되면 짝수 level의 노드에만 표시하는 전략을 사용할 수 있다.
 *  -> 풀이가 별로긴 해네.
 *
 * 설계 방식
 * 1. 우선순위 큐에다가 연결된 숫자가 많은 순대로 저장을 해 놓음
 * 2. visited를 활용하여 이미 체크된 경우를 제외하면서 없앰
 *  -> 앞에서 체크된 경우는 visited로 알 수 있지만, 우선순위 큐에서는 반영하지 못함
 * 3. 체크된 경우 visited와 함께 해당 visted에 연결되어 있는 노드들의 정보를 -1 해야 한다.
 *  -> 속도 걱정되긴 한다.
 *
 * 설계 방식2
 * 1. 얼리어답터 or not을 이용하여 dp로 상태 저장하는 방식으로 가자.
 * 2. (트리 특성을 이용하여) 위 부분을 따라서 dp를 활성화 or not 할 수 있다.
 *  -> 위 부분이 활성화되어 있으면 아래 부분은 dp를 활성화하거나 안 할 수 있다.
 *  -> 위 부분이 활성화되어 있지 않으면 아래 부분은 dp를 활성화해야 한다.
 *  -> 꼭 위아래가 아니여도 연결되어 있으면 되네.
 * 3. root나 업다운을 모르는 상횡이다.
 *  -> 이러한 조건이 필요없도록 문제를 푸는게 맞다.
 * 4. 아무것부터 시작하면서, dfs로 탐색을 한다.
 * 5. 이전 것부터 구해야 하니까 dfs로 계속 재귀호출을 한다.
 * 6. vsiited와 dp 기본 세팅을 하고, 이전 식을 가지고 dp를 추가한다. (updown)
 */

public class Main {

    static List<Integer>[] graph;
    static int N;
    static int[][] dp;
    static int[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향 그래프
            graph[a].add(b);
            graph[b].add(a);
        }

        // 탐색 시작
        dp = new int[N+1][2];
        visited = new int[N+1];
        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs (int idx) {
        visited[idx] = 1;
        dp[idx][0] = 0;
        dp[idx][1] = 1; // 세팅

        for (int i = 0; i < graph[idx].size(); i++) {
            int next = graph[idx].get(i);
            if (visited[next] == 1) continue;

            // 자식 먼저 구하기
            // 리프노드가 되면, 위의 visited, dp 2개만 채우고 반환
            dfs(next);

            // dp
            dp[idx][0] += dp[next][1]; // 무조건 얼리어답터
            dp[idx][1] += Math.min(dp[next][0], dp[next][1]); // 얼리어답터 안해도 됨
        }
    }

}
