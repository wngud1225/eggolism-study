import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 입력 받기
    // 최대 높이값도 같이 구하기
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] tree = new int[N];

    int max_height = 0;
    for (int i = 0; i < N; i++) {
        int num = Integer.parseInt(st.nextToken());
        tree[i] = num;

        if (max_height < num) {
            max_height = num;
        }
    }

    int answer = binarySearch(tree, M, max_height);
    System.out.println(answer);

    }

    // 똑같으면 좋고,
    // 없다면 딱 한칸만 옆으로
    // LB 개념 이용 (크거나 같은 인덱스)

    // 정렬이 내림차순으로 되어있어서, pc가 낮아지지만, target 값이 상승
    // i와 sum이 연동되지 않음
    // sum을 -를 해버림
    // pc(height)값을 낮추면, key값이 증가하는 상황이라서, pl과 pr의 이동을 반대로 만듦
    // 둘이 정확히 바꾸기만함!
    
    // 결국 건듯 것: 최종적으로 pr이 움직이게 + 내림하지 않고 pr을 기준으로 가도록
    static int binarySearch(int[] arr, int key, int length) {
        int pl = 0;
        int pr = length-1; // length로 해도 의미 없을 듯
        int sum = 0;

        while (pl < pr) {

            int pc = ((pl + pr) / 2)+1; // 높이

            sum = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] - pc > 0) sum += (arr[i] - pc);
            }

            if (key <= sum) {
                pl = pc;

            } else {
                pr = pc-1;
            }

        }
//        System.out.println(sum);
        return pl;
    }


}


/*반례 만들기
* 4 7
20 14 10 17*/