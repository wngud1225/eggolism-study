import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        int total = sc.nextInt();
        String target = sc.nextLine();
        int[] arr = new int[26];

        //target 알파벳 -> 알파벳 리스트에 숫자 올리기
        target = target.toUpperCase();
        for (int i = 0; i < target.length(); i++) {
            arr[target.charAt(i) - 'A']++; //0부터 카운팅
        }

        int max = 0;
        char answer = '0';
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                answer = (char) ('A' + i);
            } else if (arr[i] == max) {
                answer = '?';
            }

        }

        System.out.println(answer);

    }
}