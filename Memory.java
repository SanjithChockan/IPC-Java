import java.util.Scanner;

public class Memory {
    public static void main(String [] args) {
        int [] memory = new int [] {8, 14, 8, 16, 8, 10, 11, 9, 1, 50};

        while (true) {
            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();
            System.out.println(memory[x]);
        }
        
    }
}
