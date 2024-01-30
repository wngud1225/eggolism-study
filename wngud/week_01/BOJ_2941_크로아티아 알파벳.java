import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String target = sc.nextLine();

        String[] refs = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        for (int i = 0; i < refs.length; i++) {
            if (target.contains(refs[i])) {
                target = target.replace(refs[i], "@");
            }
        }

        System.out.println(target.length());
    }
}