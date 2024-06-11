package BOJ_2493_탑;

/*
 *  스택을 이용해서 풀이(밑에 힌트 보고 풀었음)
 *  peek()을 통해 맨 위값을 보고 현재 숫자보다 작으면 스택에서 제거(큰 게 나올 때 까지)
 *  해당 숫자보다 큰 숫자가 나오거나 스택에 아무것도 안 남게 되면 해당 index를 stringbuilder에 저장
 *  메모리 초과가 났는데 스택에서 메모리 초과가 나는 줄 알았는데 그냥 버퍼쓰니까 해결됐다..
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<int[]> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		String[] nums = br.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(nums[i]);
			while(!stack.isEmpty() && stack.peek()[0] < num) {
				stack.pop();
			}
			if(stack.isEmpty()) sb.append(0).append(" ");
			else sb.append(stack.peek()[1]+1).append(" ");
			stack.push(new int[] {num, i});
		}
		
		System.out.println(sb);
	}

}