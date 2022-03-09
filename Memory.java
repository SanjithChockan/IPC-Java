import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Memory {

    static int [] memory = new int [2000];
    public static void main(String [] args) throws FileNotFoundException {
        String fileName = "sample1.txt";
        readFromFile(fileName);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String x = sc.nextLine();
            String [] cmd = x.split(" ");
            char op = cmd[0].charAt(0);
            int address = Integer.parseInt(cmd[1]);
            int data = Integer.parseInt(cmd[2]);

            switch(op) {
                case 'r':
                    int value = read(address);
                    System.out.println(value);
                    break;
                case 'w':
                    write(address, data);
                    System.out.println(data);
                    break;
                default:
                    System.out.println("Error");
            }
        }
        
    }

    static void readFromFile (String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));
        int index = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();

            if (line.isEmpty()) {
                continue;
            }
            else if (line.charAt(0) == '.') {
                line = line.substring(1);
                index = Integer.parseInt(line.split(" ")[0]);
                continue;
            }
            else {
                int num = Integer.parseInt(line.split(" ")[0]);
                memory[index] = num;
                index++;
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
