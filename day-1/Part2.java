import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Part2 {
    private Scanner sc;
    private String[] obscuredCalibrations;

    public Part2(){
        obscuredCalibrations = new String[1000];
        readObscuredCalibrations();
    }

    private void readObscuredCalibrations(){
        try {
            File myFile = new File("day-1/part-1-input.txt");
            sc = new Scanner(myFile);

            int index = 0;
            while(sc.hasNextLine() || index <= 999){
                String line = sc.nextLine();
                obscuredCalibrations[index] = line;
                index++;
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        }

    }

    private void solveCalibrations() {
        int solution = 0;
        for(int i=0; i<1000; i++){
            String obscuredCalibration = obscuredCalibrations[i];
            boolean foundFirst = false;
            char firstNum = 'Z';
            char lastNum = 'Z';

            while(obscuredCalibration.length() > 0){
                char ch = obscuredCalibration.charAt(0);
                if(Character.isDigit(ch)){
                    if(!foundFirst){
                        firstNum = ch;
                        foundFirst = true;
                    } else{
                        lastNum = ch;
                    }
                } else {
                    char num = checkWordedNumber(obscuredCalibration);
                    if(num != ' '){
                        if(!foundFirst){
                            firstNum = num;
                            foundFirst = true;
                        } else{
                            lastNum = num;
                        }
                    }
                }

                obscuredCalibration = obscuredCalibration.substring(1);
                
            }

            if(lastNum == 'Z'){
                lastNum = firstNum;
            }

            String strCalibration = "" + firstNum + lastNum;
            int calibration = Integer.parseInt(strCalibration);
            solution += calibration;
        }

        System.out.println(solution);
    }

    private char checkWordedNumber(String obscuredCombination){
        char firstChar = obscuredCombination.charAt(0);

        switch(firstChar){
            case 'o':
                if(obscuredCombination.startsWith("one")){
                    return '1';
                }

            case 't':
                if(obscuredCombination.startsWith("two")){
                    return '2';
                } else if(obscuredCombination.startsWith("three")){
                    return '3';
                }

            case 'f':
                if(obscuredCombination.startsWith("four")){
                    return '4';
                } else if(obscuredCombination.startsWith("five")){
                    return '5';
                }

            case 's':
                if(obscuredCombination.startsWith("six")){
                    return '6';
                } else if(obscuredCombination.startsWith("seven")){
                    return '7';
                }

            case 'e':
                if(obscuredCombination.startsWith("eight")){
                    return '8';
                }
            
            case 'n':
                if(obscuredCombination.startsWith("nine")){
                    return '9';
                }

            default:
                return ' ';
        }
    }

    public static void main(String[] args) {
        Part2 solution = new Part2();
        solution.solveCalibrations();
    }
}