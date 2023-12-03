import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Part1 {
    private Scanner sc;
    private String[] obscuredCalibrations;

    public Part1(){
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

    public static void main(String[] args) {
        Part1 solution = new Part1();
        solution.solveCalibrations();
    }
}