import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        List<Node> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();

            // 최소 start 넘는 것들만 추가
            if (c < 3) continue;

            Node node = new Node(a, b, c, d);
            list.add(node);
        }

        // 정렬하기
        Collections.sort(list);

        // 노드 하나씩 돌기
        int count = 0;
        int idx = 0;
        int targetM = 3;
        int targetD = 1;

        // 최대 사이즈만큼 돌기 -> 탐색한 것 까지만 하기
        while(idx != list.size()) {

            // 가능한 경우 계속 최신화 -> 확보 날짜 이전에 start해야 함
            boolean is_updated = false; // 끊기지 않는 경우
            int maxM = 0;
            int maxD = 0;
            if (targetM == 12) break; // 여기에 위치해야 함!

            for (int i = idx; i < list.size(); i++) {

                Node curNode = list.get(idx);
                
                if (targetM > curNode.startM 
                    || (targetM == curNode.startM && targetD >= curNode.startD)) { // 같아도 됨

                        // 확보 시간
                        if (maxM < curNode.endM 
                            || (maxM == curNode.endM && maxD < curNode.endD)) {
                            maxM = curNode.endM;
                            maxD = curNode.endD;
                        }
                        is_updated = true;
                        idx++;
                } else {
                    break; // 없으면 끝내기
                }

            } // for문 끝

            // 한번이라도 업데이트 되었다면
            if (is_updated) {
                targetM = maxM;
                targetD = maxD;
                count++;
            } else if(targetM == 12) {
                break; // 정상적인 종료
            } else {
                targetM = 0; // 중간에 끊겼다는 시그널
                break;
            }

        }

        // 정답 출력하기
        if (targetM != 12 || targetM == 0) System.out.println(0);
        else System.out.println(count);

    }

}