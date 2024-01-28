import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = sc.nextInt();
        int total = sc.nextInt();

        int[] numbers = new int[max];

        for (int i = 0; i < total; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int num = sc.nextInt();

            for (int j = a-1; j <= b-1; j++) {
                numbers[j] = num;
            }
        }

        for (int k = 0; k < max; k++) {
            System.out.print(numbers[k] + " ");
        }


    }
}