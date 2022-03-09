import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class CPU {

    static int PC = -1;
    static int SP = 1000;
    static int IR = 0;
    static int AC = 0;
    static int X, Y = 0;
    public static void main(String [] args) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("java Memory");
            
            while (PC < 10) {
                incrementPC();
                
                IR = fetch(proc, 'r');
                execInstruction(IR, proc);
            }
            

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    static void incrementPC() {
        PC++;
    }

    static int fetch(Process proc, char op) {
        InputStream is = proc.getInputStream();
        OutputStream os = proc.getOutputStream();
        Scanner sc = new Scanner(is);

        PrintWriter pw = new PrintWriter(os);
        pw.println(op + " " + PC + " " + AC);
        pw.flush();
        return sc.nextInt();
    }

    static void push(Process proc, int data) {
        SP--;
        int tempPC = PC;
        int tempAC = AC;

        PC = SP;

        int writeData = fetch(proc, 'w');

        AC = tempAC;
        PC = tempPC;

    }

    static int pop(Process proc) {
        int tempPC = PC;
        PC = SP;

        int ret = fetch(proc, 'r');
        SP++;
        PC = tempPC;

        return ret;
    }

    static void execInstruction(int instruction, Process proc) {

        int tempAddress;
        int tempPC;
        int tempAC;
        int writeData;

        switch(instruction) {
            // load value into AC
            case 1:
                incrementPC();
                AC = fetch(proc, 'r');
                break;

            // load the value at the address into AC
            case 2:
                incrementPC();
                tempAddress = fetch(proc, 'r');
                tempPC = PC;
                PC = tempAddress;
                AC = fetch(proc, 'r');
                PC = tempPC;
                break;

            // load the value from the address found in the given address into the AC
            case 3:
                incrementPC();
                // get first address
                tempAddress = fetch(proc, 'r');
                tempPC = PC;
                PC = tempAddress;
                // get second address
                tempAddress = fetch(proc, 'r');
                PC = tempAddress;
                // get value from second address
                AC = fetch(proc, 'r');
                PC = tempPC;
                break;

            // load the value at (address+X) into the AC
            case 4:
                incrementPC();
                tempAddress = fetch(proc, 'r');
                tempAddress += X;
                tempPC = PC;
                PC = tempAddress;
                AC = fetch(proc, 'r');
                PC = tempPC;
                break;
            
            // load the value at (address+Y) into the AC
            case 5:
                incrementPC();
                tempAddress = fetch(proc, 'r');
                tempAddress += Y;
                tempPC = PC;
                PC = tempAddress;
                AC = fetch(proc, 'r');
                PC = tempPC;
                break;
            
            // load from (Sp+X) into the AC 
            case 6:
                tempPC = PC;
                PC = SP + X;
                AC = fetch(proc, 'r');
                PC = tempPC;
                break;
            
            // store the value in the AC into the address
            case 7:
                incrementPC();
                tempAddress = fetch(proc, 'r');
                tempPC = PC;
                PC = tempAddress;
                writeData = fetch(proc, 'w');
                PC = tempPC;
                break;
                
            // Generate random number (1 - 100) and store it in AC
            case 8:
                Random rand = new Random();
                int randomNum = rand.nextInt((99) + 1) + 1;
                AC = randomNum;
                break;
            
            // put port: If port == 1, print AC as an int; if port == 2, print AC as a char
            case 9:
                incrementPC();
                int port = fetch(proc, 'r');
                if (port == 1) {
                    System.out.print(AC);
                }
                else if (port == 2) {
                    System.out.print((char)AC);
                }
                break;

            // Add the value in X to the AC
            case 10:
                AC += X;
                break;
            
            // Add the value in Y to the AC
            case 11:
                AC += Y;
                break;
            
            // subtract the value in X from the AC
            case 12:
                AC -= X;
                break;
            
            // subtract the value in Y from the AC
            case 13:
                AC -= Y;
                break;
            
            // Move value from AC to X
            case 14:
                X = AC;
                break;

            // Copy the value in X to the AC
            case 15:
                AC = X;
                break;

            // Move value from AC to Y
            case 16:
                Y = AC;
                break;

            // Copy the value in Y to the AC
            case 17:
                AC = Y;
                break;
            
            // Copy the value in AC to the SP
            case 18:
                SP = AC;
                break;
            
            // Copy the value in SP to the AC 
            case 19:
                AC = SP;
                break;
            
            // Jump to the address
            case 20:
                incrementPC();
                tempAddress = fetch(proc, 'r');
                PC = tempAddress - 1;
                break;
            
            // Jump to the address only if the value in the AC is zero
            case 21:
                incrementPC();
                if (AC == 0) {
                    tempAddress = fetch(proc, 'r');
                    PC = tempAddress - 1;
                }
                break;
            
            // Jump to the address only if the value in the AC is not zero
            case 22:
                incrementPC();
                if (AC != 0) {
                    tempAddress = fetch(proc, 'r');
                    PC = tempAddress - 1;
                }
                break;


            // Push return address onto stack, jump to the address
            case 23:
                incrementPC();
                tempAddress = fetch(proc, 'r');
                push(proc, PC);
                PC = tempAddress - 1;
                break;
                
            // Pop return address from the stack, jump to the address
            case 24:
                PC = pop(proc);
                break;
            // Increment the value in X
            case 25:
                X++;
                break;
            
            // Decrement the value in X
            case 26:
                X--;
                break;
            
            // Push AC onto stack
            case 27:
                push(proc, AC);
                break;
            
            // Pop from stack into AC
            case 28:
                AC = pop(proc);
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