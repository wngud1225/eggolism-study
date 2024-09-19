import java.util.*;

class Solution {
    
    List<Integer> preorderList = new ArrayList<>();
    List<Integer> postorderList = new ArrayList<>();
    
    class Node implements Comparable<Node> {
        int num;
        int x, y;
        Node lft, rgt; // 이진트리
        
        public Node (int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.y != o.y ? o.y - this.y : this.x - o.x;
        }
    }
    
    // 재귀 -> 계속 타고 내려가며 추가
    // 높이 신경쓰지 않으려면 그냥 계속 타고 내려가는 것이 좋다.
    public void addNode(Node from, Node to) {
        
        // 왼쪽
        if (from.x > to.x) {
            if (from.lft == null) {
                from.lft = to;
            } else {
                addNode(from.lft, to); // 분할정복
            }
        } else { // 오른쪽
            if (from.rgt == null) {
                from.rgt = to;
            } else {
                addNode(from.rgt, to);
            }
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        
        // 노드들 생성
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
        }
        
        Arrays.sort(nodes);
        
        // 트리 만들기
        Node root = nodes[0];
        for (int i = 1; i < nodeinfo.length; i++) { // 디버깅: 1부터
            addNode(root, nodes[i]); // root가 고정되는 것이 특징
        }
        
        
        // 전위 순회
        preorder(root);
        // 후위 순회
        postorder(root);
        
        int[][] answer = new int[2][nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = preorderList.get(i);
            answer[1][i] = postorderList.get(i);
        }
        return answer;
    }
    
    public void preorder (Node root) {
        preorderList.add(root.num);
        if (root.lft != null) preorder(root.lft);
        if (root.rgt != null) preorder(root.rgt);
    }
    
   public void postorder (Node root) {
        if (root.lft != null) postorder(root.lft);
        if (root.rgt != null) postorder(root.rgt);
        postorderList.add(root.num);
    }
}