import java.util.Scanner;

public class Memory {

    static int [] memory = new int [] {8, 14, 8, 16, 8, 10, 11, 9, 1, 50};
    public static void main(String [] args) {

        while (true) {
            Scanner sc = new Scanner(System.in);
            String x = sc.nextLine();
            String [] cmd = x.split(" ");
            char op = cmd[0].charAt(0);
            int address = Integer.parseInt(cmd[1]);

            switch(op) {
                case 'r':
                    int value = read(address);
                    System.out.println(value);
                    break;
                case 'w':
                    int data = Integer.parseInt(cmd[2]);
                    write(address, data);
                    break;
                default:
                    System.out.println("Error");
            }
        }
        
    }

    // returns the value at the address
    static int read(int address) {
        return memory[address];
    }

    // writes the data to the address
    static void write(int address, int data) {
        memory[address] = data;
    }
}
