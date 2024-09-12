package BOJ_2170;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		List<Integer> starts = new ArrayList<>();
		List<Integer> ends = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			starts.add(Integer.parseInt(st.nextToken()));
			ends.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(starts);
		Collections.sort(ends);

		int startIdx = 0;
		int endIdx = 0;
		int last = starts.get(0);
		int ans = 0;

		while (startIdx < N) {

			int start = starts.get(startIdx);
			int end = ends.get(endIdx);

			if (start > end) {
				endIdx++;
			} else if (start < end) {
				startIdx++;
			} else {
				startIdx++;
				endIdx++;
			}

			if (startIdx == endIdx) {
				ans += ends.get(endIdx - 1) - last;
				last = starts.get(startIdx);
			}
		}
		ans += ends.get(N - 1) - last;
		System.out.println(ans);
	}
}