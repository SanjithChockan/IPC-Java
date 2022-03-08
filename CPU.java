import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class CPU {

    static int PC = -1;
    static int SP = 0;
    static int IR = 0;
    static int AC = 0;
    static int X, Y = 0;
    public static void main(String [] args) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("java Memory");
            
            while (PC <= 9) {
                incrementPC();
                
                IR = fetch(proc);
                execInstruction(IR, proc);
            }
            
            //int exitVal = proc.exitValue();
            //System.out.println("Process exited: " + exitVal);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    static void incrementPC() {
        PC++;
    }

    static int fetch(Process proc) {
        InputStream is = proc.getInputStream();
        OutputStream os = proc.getOutputStream();
        Scanner sc = new Scanner(is);

        PrintWriter pw = new PrintWriter(os);
        pw.println("r " + PC);
        pw.flush();
        return sc.nextInt();
    }

    static void execInstruction(int instruction, Process proc) {


        switch(instruction) {
            // load value into AC
            case 1:
                incrementPC();
                AC = fetch(proc);
                break;

            // load the value at the address into AC
            case 2:
                incrementPC();
                int tempAddress = fetch(proc);
                int temp = PC;
                PC = tempAddress;
                AC = fetch(proc);
                PC = temp;

            // Generate random number (1 - 100) and store it in AC
            case 8:
                Random rand = new Random();
                int randomNum = rand.nextInt((99) + 1) + 1;
                AC = randomNum;
                break;
            
            // put port: If port == 1, print AC as an int; if port == 2, print AC as a char
            case 9:
                incrementPC();
                int port = fetch(proc);
                if (port == 1) {
                    System.out.println(AC);
                }
                else if (port == 2) {
                    System.out.println((char)AC);
                }

            // Add the value in X to the AC
            case 10:
                AC += X;
                break;
            // Add the value in Y to the AC
            case 11:
                AC += Y;
                break;
            // Move value from AC to X
            case 14:
                X = AC;
                break;
            // Move value from AC to Y
            case 16:
                Y = AC;
                break;
            case 50:
            // need to implement proc.waitFor to first close the other program
                System.exit(0);
                break;

            default:
                ;
        }

    }
}