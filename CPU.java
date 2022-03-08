import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CPU {
    public static void main(String [] args) {
        try {
            Runtime rt = Runtime.getRuntime();

            Process proc = rt.exec("java Memory");
            
            InputStream is = proc.getInputStream();
            OutputStream os = proc.getOutputStream();

            PrintWriter pw = new PrintWriter(os);
            pw.println(50);
            System.out.println("After sending 50");
            pw.flush();
            System.out.println("After flushing");

            Scanner sc = new Scanner(is);
            System.out.println("Before loading address");
            int address = sc.nextInt();
            System.out.println("After loading address");
            System.out.print("Address: ");
            System.out.println(address);
            
            //int exitVal = proc.exitValue();

            //System.out.println("Process exited: " + exitVal);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}