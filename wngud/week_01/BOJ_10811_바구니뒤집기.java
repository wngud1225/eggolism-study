import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = sc.nextInt();
        int total = sc.nextInt();
        int temp = 0;

        int[] numbers = new int[max+1]; //공간을 넓히자

        for (int i = 1; i <= max; i++) {
            numbers[i] = i;
        }

        //루프 시작
        for (int i = 1; i <= total; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            while (a < b) {
                temp = numbers[a];
                numbers[a] = numbers[b];
                numbers[b] = temp;

                a++;
                b--;
            }


        }

        for (int k = 1; k <= max; k++) {
            System.out.print(numbers[k] + " ");
        }


    }
}