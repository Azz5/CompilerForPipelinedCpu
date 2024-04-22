import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class main {

    private static final Compiler compiler = new Compiler();
    public static void main(String[] args) throws IOException {
        if (args.length != 0){
            Scanner fileReader = new Scanner(args[0]);
            File file = new File(args[0]+"-Compiled.txt");
            FileWriter writer = new FileWriter(file, true);
            PrintWriter output = new PrintWriter(writer);
            output.println("v2.0 raw");
            while (fileReader.hasNext()){
                output.print(Compiler.parse(fileReader.nextLine()) + " ");
            }
        } throw new RuntimeException("you must include file name");
    }
}
