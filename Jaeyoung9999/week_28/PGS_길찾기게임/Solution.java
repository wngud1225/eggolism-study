package PGS_길찾기게임;

import java.util.*;

class Solution {

	static class Node implements Comparable<Node> {

		int num;
		int x;
		int y;
		
		Node left;
		Node right;

		Node(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node n) {

			return n.y - this.y;
		}
	}

	static int[][] answer;
	static int preOrderIdx;
	static int postOrderIdx;

	public int[][] solution(int[][] nodeinfo) {
		
		answer = new int[2][nodeinfo.length];
		preOrderIdx = 0;
		postOrderIdx = 0;
		
		// pq에는 노드를 넣고, 정렬 기준은 y값이 큰 순서
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int num = 1;

		for (int r = 0; r < nodeinfo.length; r++) {
			pq.add(new Node(num++, nodeinfo[r][0], nodeinfo[r][1]));
		}

		Node head = pq.poll();

		// 새로운 노드를 넣을 때는 pq에서 하나를 빼서 헤드부터 타고 내려감
		while (!pq.isEmpty()) {

			Node newNode = pq.poll();
			Node curr = head;

			while (true) {
				if (newNode.x < curr.x) {
					if (curr.left == null) {
						curr.left = newNode;
						break;
					}
					curr = curr.left;
				} else {
					if (curr.right == null) {
						curr.right = newNode;
						break;
					}
					curr = curr.right;
				}
			}
		}
		preOrder(head);
		postOrder(head);

		return answer;
	}

	void preOrder(Node node) {
		answer[0][preOrderIdx++] = node.num;
		if (node.left != null) {
			preOrder(node.left);
		}
		if (node.right != null) {
			preOrder(node.right);
		}
	}

	void postOrder(Node node) {
		if (node.left != null) {
			postOrder(node.left);
		}
		if (node.right != null) {
			postOrder(node.right);
		}
		answer[1][postOrderIdx++] = node.num;
	}
}
