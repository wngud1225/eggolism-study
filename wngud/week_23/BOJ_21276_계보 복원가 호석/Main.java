import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 이름 정보 저장
        st = new StringTokenizer(br.readLine());
        List<String> nameList = new ArrayList<>();
        Map<String, Integer> nameMap = new HashMap<>();
        Map<Integer, String> nameMapReverse = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();
            nameList.add(name);
            nameMap.put(name, i);
            nameMapReverse.put(i, name);
        }
        Collections.sort(nameList);

        // 그래프 저장
        List<ArrayList<Integer>> graph = new ArrayList<>(); // 조상 정보
        int[] ancestor = new int[N]; // 손자 정보
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = nameMap.get(st.nextToken());
            int from = nameMap.get(st.nextToken());

            // 손자 정보 저장
            graph.get(from).add(to);
            // 조상 숫자 저장
            ancestor[to] += 1;
        }

//        System.out.println(Arrays.toString(ancestor));

        // 시조 출력하기 (사전순)
        List<String> sijo = new ArrayList<>();
        nameMap.forEach((key, value) -> {
           if (ancestor[value] == 0) sijo.add(key);
        });
        Collections.sort(sijo);

        System.out.println(sijo.size());
        for (int i = 0; i < sijo.size(); i++) {
            sb.append(sijo.get(i)).append(" ");
        }
        sb.append("\n");

        // 자식 이름 출력 (사전순)
        for (String name : nameList) {
            sb.append(name).append(" ");

            // 사람 수와 리스트 구하기
            List<String> gasick = new ArrayList<>();
            int targetNum = nameMap.get(name);
            int targetCount = ancestor[targetNum] + 1;
            for (int j = 0; j < graph.get(targetNum).size(); j++) {
                int checkNum = graph.get(targetNum).get(j);
                if (ancestor[checkNum] == targetCount) {
                    gasick.add(nameMapReverse.get(checkNum));
                }
            }
            Collections.sort(gasick);
            sb.append(gasick.size()).append(" ");
            for (int k = 0; k < gasick.size(); k++) {
                sb.append(gasick.get(k)).append(" ");
            }
            sb.append("\n");
        }

        // 정답 출력하기
        System.out.println(sb);

    }
}