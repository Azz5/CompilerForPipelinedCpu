public class Compiler {
    private final static String R_TYPE_REGEX_ZERO = "or|and|nand|xor|add|sub|sge|sgeu";
    private final static String R_TYPE_REGEX_ONE = "sll|srl|sra|ror|movz|movn|jr|jalr";
    private final static String I_TYPE_REGEX = "andi|ori|addi|sw|lw|bne|beq|bgt|bge|blt|ble";
    private final static String J_TYPE_REGEX = "lui|j|jal";

    public static String parse(String input){
        String instruction = "";
        String[] instructionSpliced = input.split(" ");
        String output = "";
        if (instructionSpliced[0].matches(R_TYPE_REGEX_ZERO)){
            output += "0000";

            output = threeReg(instructionSpliced,output);

            switch (instructionSpliced[0]) {
                case "or" -> output += "000";
                case "and" -> output += "001";
                case "nand" -> output += "010";
                case "xor" -> output += "011";
                case "add" -> output += "100";
                case "sub" -> output += "101";
                case "sge" -> output += "110";
                case "sgeu" -> output += "111";
            }

            instruction = Integer.toHexString(Integer.parseInt(output,2));
            if (instruction.matches("^([0-f]{3})$")){
                instruction = "0" + instruction;
            } else if (instruction.matches("^([0-f]{2})$")) {
                instruction = "00" + instruction;
            } else if (instruction.matches("^([0-f]{1})$")) {
                instruction = "000" + instruction;
            }
        } else if (instructionSpliced[0].matches(R_TYPE_REGEX_ONE)) {
            output += "0001";

            output = threeReg(instructionSpliced,output);

            switch (instructionSpliced[0]) {
                case "sll" -> output += "000";
                case "srl" -> output += "001";
                case "sra" -> output += "010";
                case "ror" -> output += "011";
                case "movz" -> output += "100";
                case "movn" -> output += "101";
                case "jr" -> output += "110";
                case "jalr" -> output += "111";
            }

            instruction = Integer.toHexString(Integer.parseInt(output,2));
            if (instruction.matches("^([0-f]{3})$")){
                instruction = "0" + instruction;
            } else if (instruction.matches("^([0-f]{2})$")) {
                instruction = "00" + instruction;
            } else if (instruction.matches("^([0-f]{1})$")) {
                instruction = "000" + instruction;
            }

        } else if (instructionSpliced[0].matches(I_TYPE_REGEX)) {
            switch (instructionSpliced[0]){
                case "andi" -> output += "0010";
                case "ori" -> output += "0011";
                case "addi" -> output += "0100";
                case "sw" -> output += "0101";
                case "lw" -> output += "0110";
                case "bne" -> output += "0111";
                case "beq" -> output += "1000";
                case "bgt" -> output += "1001";
                case "bge" -> output += "1010";
                case "blt" -> output += "1011";
                case "ble" -> output += "1100";
            }
            output = twoReg(instructionSpliced,output);
            instruction = Integer.toHexString(Integer.parseInt(output,2));
            if (instruction.matches("^([0-f]{3})$")){
                instruction = "0" + instruction;
            } else if (instruction.matches("^([0-f]{2})$")) {
                instruction = "00" + instruction;
            } else if (instruction.matches("^([0-f]{1})$")) {
                instruction = "000" + instruction;
            }

        } else if (instructionSpliced[0].matches(J_TYPE_REGEX)) {
            switch (instructionSpliced[0]){
                case "lui" -> output += "1101";
                case "j" -> output += "1110";
                case "jal" -> output += "1111";
            }
            output = parse12Bits(instructionSpliced, output);
            instruction = Integer.toHexString(Integer.parseInt(output,2));
            if (instruction.matches("^([0-f]{3})$")){
                instruction = "0" + instruction;
            } else if (instruction.matches("^([0-f]{2})$")) {
                instruction = "00" + instruction;
            } else if (instruction.matches("^([0-f]{1})$")) {
                instruction = "000" + instruction;
            }
        }
        return instruction;
    }

    private static String threeReg(String[] instructionSpliced,String output) {
        if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-1]{3})$")){
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-1]{2})$")) {
            output += "0";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-1]{1})$")) {
            output += "00";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
        }

        if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-1]{3})$")){
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-1]{2})$")) {
            output += "0";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-1]{1})$")) {
            output += "00";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
        }

        if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[3])).matches("^([0-1]{3})$")){
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[3]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[3])).matches("^([0-1]{2})$")) {
            output += "0";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[3]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[3])).matches("^([0-1]{1})$")) {
            output += "00";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[3]));
        }
        return output;
    }

    private static String twoReg(String[] instructionSpliced,String output) {
        if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-1]{3})$")){
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-1]{2})$")) {
            output += "0";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[1])).matches("^([0-1]{1})$")) {
            output += "00";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
        }

        if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-1]{3})$")){
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-1]{2})$")) {
            output += "0";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
        } else if (Integer.toBinaryString(Integer.parseInt(instructionSpliced[2])).matches("^([0-1]{1})$")) {
            output += "00";
            output += Integer.toBinaryString(Integer.parseInt(instructionSpliced[2]));
        }

        String parsed = Integer.toBinaryString(Integer.parseInt(instructionSpliced[3]));
        if (parsed.matches("^([0-1]{6})$")){
            output += parsed;
        } else if (parsed.matches("^([0-1]{5})$")) {
            output += "0" +parsed;
        } else if (parsed.matches("^([0-1]{4})$")) {
            output += "00" +parsed;
        } else if (parsed.matches("^([0-1]{3})$")) {
            output += "000" +parsed;
        } else if (parsed.matches("^([0-1]{2})$")) {
            output += "0000" +parsed;
        } else if (parsed.matches("^([0-1]{1})$")) {
            output += "00000"+parsed;
        }
        return output;
    }

    private static String parse12Bits(String[] instructionSpliced,String output){
        String parsed = Integer.toBinaryString(Integer.parseInt(instructionSpliced[1]));
        if (parsed.matches("^([0-1]{12})$")){
            output += parsed;
        } else if (parsed.matches("^([0-1]{11})$")) {
            output += "0" +parsed;
        } else if (parsed.matches("^([0-1]{10})$")) {
            output += "00" +parsed;
        } else if (parsed.matches("^([0-1]{9})$")) {
            output += "000" +parsed;
        } else if (parsed.matches("^([0-1]{8})$")) {
            output += "0000" +parsed;
        } else if (parsed.matches("^([0-1]{7})$")) {
            output += "00000"+parsed;
        } else if (parsed.matches("^([0-1]{6})$")) {
            output += "000000"+parsed;
        } else if (parsed.matches("^([0-1]{5})$")) {
            output += "0000000" +parsed;
        } else if (parsed.matches("^([0-1]{4})$")) {
            output += "00000000" +parsed;
        } else if (parsed.matches("^([0-1]{3})$")) {
            output += "000000000" +parsed;
        } else if (parsed.matches("^([0-1]{2})$")) {
            output += "0000000000" +parsed;
        } else if (parsed.matches("^([0-1]{1})$")) {
            output += "00000000000"+parsed;
        }
        return output;
    }

}
