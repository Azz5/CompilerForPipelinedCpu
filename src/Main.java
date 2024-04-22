import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Compileer compileer = new Compileer();

        String Name = "C:\\Users\\s202159170\\Desktop\\projects\\compiler\\CompilerForPipelinedCpu\\src\\code.txt";
        if (args.length != 0){
            Scanner fileReader = null;
            try {
                fileReader = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            File file = new File(args[0]+"-Compiled.txt");
            FileWriter writer = null;
            try {
                writer = new FileWriter(file, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PrintWriter output = new PrintWriter(writer);
            output.println("v2.0 raw");
            while (fileReader.hasNext()){
                output.print(Compileer.parse(fileReader.nextLine()) + " ");
            }
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            output.close();
            fileReader.close();
        } else  throw new RuntimeException("you must include file name");
    }
}
