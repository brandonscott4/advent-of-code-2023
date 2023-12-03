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

            for(int j=0; j<obscuredCalibration.length(); j++){
                char ch = obscuredCalibration.charAt(j);
                    if(Character.isDigit(ch)){
                        if(!foundFirst){
                            firstNum = ch;
                            foundFirst = true;
                        } else{
                            lastNum = ch;
                        }
                    } else {
                        char num = checkWordedNumber(obscuredCalibration, j);
                        if(num != ' '){
                            if(!foundFirst){
                                firstNum = num;
                                foundFirst = true;
                            } else{
                                lastNum = num;
                            }
                        }

                    }
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
 
    private char checkWordedNumber(String obscuredCombination, int index){
        int lineLength = obscuredCombination.length();
        char ch = obscuredCombination.charAt(index);
        char result = ' ';
        switch(ch) {
            case 'o':
              if((index+3) > lineLength){break;}

              String checkOne = obscuredCombination.substring(index, index+3);
              if(checkOne.equals("one")){
                result = '1';
              }
                break;
            case 't':
              if((index+3) > lineLength){break;}

              String checkTwo = obscuredCombination.substring(index, index+3);
              if(checkTwo.equals("two")){
                result = '2';
              } else {
                    if((index+5) > lineLength){break;}

                    String checkThree = obscuredCombination.substring(index, index+5);
                    if(checkThree.equals("three")){
                        result = '3';
                    }
              }
                break;
            case 'f':
              if((index+4) > lineLength){break;}

              String checkFour = obscuredCombination.substring(index, index+4);
              if(checkFour.equals("four")){
                result = '4';
              } else{
                    if((index+4) > lineLength){break;}

                    String checkFive = obscuredCombination.substring(index, index+4);
                    if(checkFive.equals("five")){
                        result = '5';
                    }
              }
                break;
            case 's':
              if((index+3) > lineLength){break;}

              String checkSix = obscuredCombination.substring(index, index+3);
              if(checkSix.equals("six")){
                result = '6';
              } else{
                    if((index+5) > lineLength){break;}

                    String checkSeven = obscuredCombination.substring(index, index+5);
                    if(checkSeven.equals("seven")){
                        result = '7';
                    }
              }
                break;
            case 'e':
              if((index+5) > lineLength){break;}
              String checkEight = obscuredCombination.substring(index, index+5);
              if(checkEight.equals("eight")){
                result = '8';
              }
                break;
            case 'n':
              if((index+4) > lineLength){break;}
              String checkNine = obscuredCombination.substring(index, index+4);
              if(checkNine.equals("nine")){
                result = '9';
              }
            default:
                break;
          }
          
          return result;
    }

    public static void main(String[] args) {
        Part2 solution = new Part2();
        solution.solveCalibrations();
    }
}