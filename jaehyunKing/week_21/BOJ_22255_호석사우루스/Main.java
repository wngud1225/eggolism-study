package BOJ_22255_호석사우루스;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static int[][] graph;
    static int[][][] visited;
    static int N, M, endY, endX, min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        graph = new int[N][M];
        visited = new int[N][M][3];

        int startY = sc.nextInt()-1;
        int startX = sc.nextInt()-1;
        endY = sc.nextInt()-1;
        endX = sc.nextInt()-1;

        // k는 진행 횟수 % 3
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                graph[r][c] = sc.nextInt();
                for(int k = 0; k < 3; k++) {
                    visited[r][c][k] = Integer.MAX_VALUE;
                }
            }
        }
        min = Integer.MAX_VALUE;
        bfs(startY, startX);
        if(min == Integer.MAX_VALUE)System.out.println(-1);
        else System.out.println(min);
    }

    static void bfs(int nowY, int nowX) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{nowY, nowX, 1, 0});

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            // 탈출구에 도달
            if(now[0] == endY && now[1] == endX) {
                if(min > now[3]) min = now[3];
                continue;
            }
            // min값보다 현재 충격량이 더 크면 continue
            if(now[3] >= min) continue;

            for(int i = 0; i < 4; i++) {
                // 조건에 맞는 움직임
                if(now[2] % 3 == 2 && i % 2 == 1) continue; 
                else if(now[2] % 3 == 1 && i % 2 == 0) continue;
                int moveY = now[0] + dr[i];
                int moveX = now[1] + dc[i];

                if(0 > moveY || moveY >= N || 0 > moveX || moveX >= M) continue;
                
                // 벽이 아니고 이전 방문했을 때의 충격량보다 현재 이동할 때의 충격량이 더 작을 때
                if(graph[moveY][moveX] != -1 && visited[moveY][moveX][now[2] % 3] > now[3] + graph[moveY][moveX]) {
                    visited[moveY][moveX][now[2] % 3] = now[3] + graph[moveY][moveX];
                    queue.offer(new int[]{moveY, moveX, now[2] + 1, now[3] + graph[moveY][moveX]});
                }

            }
        }
    }

}