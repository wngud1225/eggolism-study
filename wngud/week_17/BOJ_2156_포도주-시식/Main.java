import java.util.*;

public class Main {
    /**
     * 설계 방식
     * 1] DFS로 백트래킹
     * -> 매우 많은 경우의 수
     * -> 완탐의 경우에는 2의 만승이 된다.
     * <p>
     * 2] DP 이용
     * 1. dp에는 현재 상황에서 가장 높은 숫자를 구한다.
     * 2. 일반적인 arr와 dp를 고려함으로써 모든 경우의 수가 고려된다.
     * 3. 일반적인 arr도 포함시켜서 이후에 계산하니 (XX0)와 같은 경우는 고려하지 않는다.
     * 4. dp는 누적의 최대 경우의 수 저장 + 일반적인 arr로 나머지 경우의 수 고려
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 1부터 가능 조심

        // 입력 받기
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            arr[i] = num;
        }

        // dp
        if (N >= 1) {
            dp[0] = arr[0];
        }

        if (N >= 2) {
            dp[1] = arr[0] + arr[1];
        }

        if (N >= 3) {
            dp[2] = Math.max(arr[0] + arr[1], Math.max(arr[0] + arr[2], arr[1] + arr[2]));
        }

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i]));
        }

//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(dp));

        System.out.println(dp[N - 1]);


    }
}
