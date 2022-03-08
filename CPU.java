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
            InputStream is;
            OutputStream os;

            while (PC <= 8) {
                is = proc.getInputStream();
                os = proc.getOutputStream();

                PrintWriter pw = new PrintWriter(os);
                System.out.println("PC: " + PC);
                pw.println(PC);
                pw.flush();

                Scanner sc = new Scanner(is);
                System.out.println("Has next: " + sc.hasNext());
                //int value = sc.nextInt();
                //execInstruction(value, proc);
                //System.out.println("Value at address: " + "inf");
                PC++;
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
                try {
                    proc.waitFor();
                    int exit = proc.exitValue();
                    System.out.println("Process exited: " + exit);
                    System.exit(0);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            default:
                System.out.println(0);
                PC++;
        }

    }
}