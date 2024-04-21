import java.util.*;

public class main {
    static String Instructions = "";
    final static String rTypeRegexZero = "or|and|nand|xor|add|sub|sge|sgeu";
    final static String rTypeRegexOne = "sll|srl|sra|ror|movz|movn|jr|jalr";
    final static String iTypeRegex = "andi|ori|addi|sw|lw|bne|beq|bgt|bge|blt|ble";
    final static String JtypeRegex = "lui|j|jal";
    public static void main(String[] args) {
        //Scanner FileReader;
        //if (args.length != 0){
            String FileReader = "or 3 1 2";//FileReader = new Scanner(args[0]);
            //while (FileReader.hasNextLine()){
            System.out.println(parse(FileReader));
                Instructions += parse(FileReader); //parse(FileReader.nextLine());
            //}
        //} else throw new RuntimeException("You must Enter the file name");
    }
    static String parse(String input){
        String[] instructionSpliced = input.split(" ");
        String output = "";
        if (instructionSpliced[0].matches(rTypeRegexZero)){
            output += "0";
            int data = Integer.parseInt(instructionSpliced[1]) + Integer.parseInt(instructionSpliced[2]) + Integer.parseInt(instructionSpliced[3]);
            switch (instructionSpliced[0]){
                case "or":
                    break;
                case "and":
                    data += 1;
                    break;
                case "nand":
                    data += 2;
                    break;
                case "xor":
                    data += 3;
                    break;
                case "add":
                    data += 4;
                    break;
                case "sub":
                    data += 5;
                    break;
                case "sge":
                    data += 6;
                    break;
                case "sgeu":
                    data += 7;
                    break;
            }
            String datatostring = Integer.toHexString(data);
            if (datatostring.matches("^([0-f]{3})$")){
                output+=datatostring;
            } else if (datatostring.matches("^([0-f]{2})$")) {
                output += "0";
                output+=datatostring;
            } else if (datatostring.matches("^([0-f]{1})$")) {
                output += "00";
                output+=datatostring;
            }
        } else if (instructionSpliced[0].matches(rTypeRegexOne)) {
            
        } else if (instructionSpliced[0].matches(iTypeRegex)) {
            
        } else if (instructionSpliced[0].matches(JtypeRegex)) {
            
        }


        return output;
    }

}
