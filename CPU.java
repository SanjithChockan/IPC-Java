import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CPU {

    static int PC = 0;
    static int SP = 0;
    static int IR = 0;
    static int AC = 0;
    static int X, Y = 0;
    public static void main(String [] args) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("java Memory");
            InputStream is = proc.getInputStream();
            OutputStream os = proc.getOutputStream();
            Scanner sc = new Scanner(is);

            while (PC <= 9) {
                
                PrintWriter pw = new PrintWriter(os);
                System.out.println("PC: " + PC);
                pw.println(PC);
                pw.flush();

                int value = sc.nextInt();
                System.out.println("Value and PC: " + value + " and " + PC);
                execInstruction(value, proc);
            }
            
            //int exitVal = proc.exitValue();
            //System.out.println("Process exited: " + exitVal);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    static void execInstruction(int value, Process proc) {
        switch(value) {
            case 50:
            // need to implement proc.waitFor to first close the other program
                System.out.println("Value: " + value);
                System.out.println("Exiting!");
                System.exit(0);

            default:
                System.out.println("Value: " + value);
                PC++;
        }

    }
}