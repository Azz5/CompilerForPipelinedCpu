
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
            String FileReader1 = "and 4 2 5";
            //while (FileReader.hasNextLine()){
            System.out.println(parse(FileReader));
        System.out.println(parse(FileReader1));
                Instructions += parse(FileReader); //parse(FileReader.nextLine());
            //}
        //} else throw new RuntimeException("You must Enter the file name");
    }
    static String parse(String input){
        String instruction = "";
        String[] instructionSpliced = input.split(" ");
        String output = "";
        if (instructionSpliced[0].matches(rTypeRegexZero)){
            output += "0000";

            if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-1]{3})$")){
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
            } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-f]{2})$")) {
                output += "0";
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
            } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-f]{1})$")) {
                output += "00";
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
            }

            if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-1]{3})$")){
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
            } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-f]{2})$")) {
                output += "0";
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
            } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-f]{1})$")) {
                output += "00";
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
            }

            if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[3])).matches("^([0-1]{3})$")){
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[3]));
            } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[3])).matches("^([0-f]{2})$")) {
                output += "0";
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[3]));
            } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[3])).matches("^([0-f]{1})$")) {
                output += "00";
                output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[3]));
            }

            switch (instructionSpliced[0]){
                case "or":
                    output += "000";
                    break;
                case "and":
                    output += "001";
                    break;
                case "nand":
                    output += "010";
                    break;
                case "xor":
                    output += "011";
                    break;
                case "add":
                    output += "100";
                    break;
                case "sub":
                    output += "101";
                    break;
                case "sge":
                    output += "110";
                    break;
                case "sgeu":
                    output += "111";
                    break;
            }

            instruction = Integer.toHexString(Integer.parseInt(output,2));
            if (instruction.matches("^([0-f]{3})$")){
                instruction = "0" + instruction;
            } else if (instruction.matches("^([0-f]{2})$")) {
                instruction = "0" + instruction;
            } else if (instruction.matches("^([0-f]{1})$")) {
                instruction = "0" + instruction;
            }
        } else if (instructionSpliced[0].matches(rTypeRegexOne)) {
            
        } else if (instructionSpliced[0].matches(iTypeRegex)) {
            
        } else if (instructionSpliced[0].matches(JtypeRegex)) {
            
        }


        return instruction;
    }

}
