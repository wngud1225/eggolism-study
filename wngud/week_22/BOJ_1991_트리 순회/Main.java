import java.util.*;

public class Main {

    static Node[] arr;
    static int N;
    static String answer;

    
    static class Node {
        char root;
        char lft;
        char rgt;

        public Node(char root, char lft, char rgt) {
            this.root = root;
            this.lft = lft; // '.'는 패스
            this.rgt = rgt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "root=" + root +
                    ", lft=" + lft +
                    ", rgt=" + rgt +
                    '}';
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        sc.nextLine();
        arr = new Node[N+1]; // 알파벳 차례대로 사용

        // 트리 입력
        for (int i = 0; i < N; i++) {
            String input = sc.nextLine();

            Node node = new Node(input.charAt(0), input.charAt(2), input.charAt(4));
            arr[input.charAt(0)-'A'] = node;
        }

        // 순회
        answer = "";
        preOrder(arr[0]);
        System.out.println(answer);

        answer = "";
        inOrder(arr[0]);
        System.out.println(answer);

        answer = "";
        postOrder(arr[0]);
        System.out.println(answer);

    }

    public static void preOrder(Node node) {
        answer += node.root; // 자기 자신
        if (node.lft != '.') preOrder(arr[node.lft-'A']); // 왼쪽
        if (node.rgt != '.') preOrder(arr[node.rgt-'A']); // 오른쪽
    }
    
    public static void inOrder(Node node) {
        if (node.lft != '.') inOrder(arr[node.lft-'A']); // 왼쪽
        answer += node.root; // 자기 자신
        if (node.rgt != '.') inOrder(arr[node.rgt-'A']); // 오른쪽
    }
    
    public static void postOrder(Node node) {
        if (node.lft != '.') postOrder(arr[node.lft-'A']); // 왼쪽
        if (node.rgt != '.') postOrder(arr[node.rgt-'A']); // 오른쪽
        answer += node.root; // 자기 자신
    }
}