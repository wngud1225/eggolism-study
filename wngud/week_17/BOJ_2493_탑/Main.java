import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /**
     * 설계 방식
     * 1] arr로 무지성 비교 -> 시간 초과
     * 2] dp를 사용하여 최대감소 -> 시간 초과 (거의 의미없음)
     *
     * 3] 높은 숫자들만 낮은순으로 모으기 -> 애매한 숫자들 불필요 -> Stack 사용
     * 1. 비교대상 (내림차순)
     * - 이전까지 가장 높은 것
     * - 그 다음으로 높은 것..
     * 2. 매커니즘
     * - 내림차순의 끝보다 높은 숫자가 나오면, 중간 애매한 것들은 다 없앤다.
     * - 내림차순 끝보다 낮은 숫자가 나오면, 끝의 idx가 정답이다.
     */

    static class Top {
        int height;
        int idx;

        Top(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 입력 받기
        Stack<Top> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            Top top = new Top(num, i);

            // 아무것도 없는 경우
            if (stack.isEmpty()) {
                sb.append(0).append(" ");
                stack.push(top);
            }

            // 높은(같은) 숫자가 생긴 경우 -> 이전 숫자들은 의미 없어짐
            else if (stack.peek().height <= top.height){

                while (!stack.isEmpty()) {

                    // 자기 자신이 높은 경우 이전 것 없애버림
                    if (stack.peek().height <= top.height) {
                        stack.pop();
                    } else {
                        // 1) 높은 숫자 발견
                        sb.append(stack.peek().idx).append(" ");
                        break;
                    }
                }

                // 2) 높은 숫자를 하나도 발견하지 못한 경우
                if (stack.isEmpty()) {
                    sb.append(0).append(" ");
                }

                stack.push(top);
            }

            // 낮은 숫자인 경우 -> 애매한 숫자 포함해줘야 함
            else if (stack.peek().height > top.height) {
                sb.append(stack.peek().idx).append(" ");
                stack.push(top);
            }

        }

        // 정답 출력하기
        System.out.println(sb);
    }
}
