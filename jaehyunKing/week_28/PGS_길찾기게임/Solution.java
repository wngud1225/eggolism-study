package PGS_길찾기게임;

import java.util.*;

class Solution {
    static int[][] answer;
    static int idx;
    
    public int[][] solution(int[][] nodeinfo) {
		    // 새로운 배열을 nodes (숫자, x좌표, y좌표)를 만든다
        int[][] nodes = new int[nodeinfo.length][3];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i][0] = i + 1;
            nodes[i][1] = nodeinfo[i][0];
            nodes[i][2] = nodeinfo[i][1];
        }
        
        // y좌표가 큰 거 우선, 같다면 x좌표가 작은 거 우선으로 정렬
        // (같은 y좌표라면 왼쪽에서 오른쪽으로 순서대로 넣어준다는 느낌)
        Arrays.sort(nodes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[2] != b[2]) {
                    return Integer.compare(b[2], a[2]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            }
        });
        
        // 이진트리 만들기
        Node root = new Node(nodes[0][0], nodes[0][1], nodes[0][2]);
        for(int i = 1; i < nodeinfo.length; i++){
            Node now = root;
            Node newNode = new Node(nodes[i][0], nodes[i][1], nodes[i][2]);
            
            while(true){
                if(now.x > newNode.x){
                    if(now.left == null) {
                        now.left = newNode;
                        break;
                    }
                    now = now.left;
                } 
                else if (now.x < newNode.x) {
                    if(now.right == null) {
                        now.right = newNode;
                        break;
                    }
                    now = now.right;
                }
            }
        }
        
        // 1학기 때 배운 전위순회, 후위순회 사용
        answer = new int[2][nodes.length];
        preOrder(root);
        idx = 0;
        postOrder(root);
        
        return answer;
    }
    
    // 전위순회
    static void preOrder(Node now){
        answer[0][idx++] = now.num;
        if(now.left != null) preOrder(now.left);
        if(now.right != null) preOrder(now.right);
    }
    
    // 후위순회
    static void postOrder(Node now){
        if(now.left != null) postOrder(now.left);
        if(now.right != null) postOrder(now.right);
        answer[1][idx++] = now.num;
    }
    
    public class Node {
        int num;
        int x;
        int y;
        
        Node left;
        Node right;
        
        public Node(int num, int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
}