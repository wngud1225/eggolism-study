package SWEA_1210;

import java.util.Scanner;
  
public class Solution {
  
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
      
        //사다리 타기
        for(int test_case = 1; test_case <= 10; test_case++) {
              
            int T = sc.nextInt();
            int[][] data = new int[100][100];
              
            int a = 99;
            int b = 0;
              
            //배열에 입력값 넣기
            for(int i=0; i<100; i++) {
                for(int j=0; j< 100; j++) {
                    data[i][j] = sc.nextInt();  
                    if(data[i][j] == 2) {
                        b = j;
                    }
                }
            }
  
            while(a > 0) {
                    if (b - 1 >= 0 && data[a][b-1] == 1) {
                        do  b--;  while(b - 1 >= 0 && data[a][b-1] == 1);
                        a--;
                    }
                    else if (b + 1 < 100 && data[a][b+1] == 1) {
                        do  b++;  while(b + 1 < 100 && data[a][b+1] == 1);
                        a--;
                    } 
                    else a--;
            }
            System.out.println("#"+T+" "+b);
        }
    }
}