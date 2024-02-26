package BOJ_5639_이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node[] tree = new Node[N + 1]; // 노드 배열 생성

        // 트리 구성
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()); // 띄어쓰기 기준으로 문자열 분리
            char rootValue = st.nextToken().charAt(0);
            char leftValue = st.nextToken().charAt(0);
            char rightValue = st.nextToken().charAt(0);

            // 노드 생성 및 연결
            createAndConnectNodes(tree, rootValue, leftValue, rightValue);
        }

        // 순회 출력
        tree[0].preorder();
        System.out.println();

        tree[0].inorder();
        System.out.println();

        tree[0].postorder();
        System.out.println();
    }

    static void createAndConnectNodes(Node[] tree, char rootValue, char leftValue, char rightValue) {
        if (tree[rootValue - 'A'] == null) { // 부모 노드가 아직 생성되지 않은 경우
            tree[rootValue - 'A'] = new Node(rootValue); // 부모 노드를 생성
        }
        if (leftValue != '.') { // 왼쪽 자식이 존재할 경우
            tree[leftValue - 'A'] = new Node(leftValue); // 왼쪽 자식 노드를 생성
            tree[rootValue - 'A'].left = tree[leftValue - 'A']; // 부모 노드와 연결
        }
        if (rightValue != '.') { // 오른쪽 자식이 존재할 경우
            tree[rightValue - 'A'] = new Node(rightValue); // 오른쪽 자식 노드를 생성
            tree[rootValue - 'A'].right = tree[rightValue - 'A']; // 부모 노드와 연결
        }
    }
    
    
    static class Node {
        char value; // 노드 값
        Node left; // 왼쪽 자식 노드
        Node right; // 오른쪽 자식 노드

        Node(char value) {
            this.value = value;
        }

        // 전위 순회
        void preorder() {
            System.out.print(value);
            if (left != null) left.preorder();
            if (right != null) right.preorder();
        }

        // 중위 순회
        void inorder() {
            if (left != null) left.inorder();
            System.out.print(value);
            if (right != null) right.inorder();
        }

        // 후위 순회
        void postorder() {
            if (left != null) left.postorder();
            if (right != null) right.postorder();
            System.out.print(value);
        }
    }

}

