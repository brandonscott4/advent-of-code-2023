import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Part1 {

    private Scanner sc;
    private char[][] engine = new char[140][140];
    private int sum = 0;

    public Part1(){
        readGames();
    }

    private void readGames(){
        try {
            File myFile = new File("day-3/input.txt");
            sc = new Scanner(myFile);

            int i = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                char[] engineChars = line.toCharArray();

                for(int j=0; j<engineChars.length; j++){
                    engine[i][j] = engineChars[j];
                }
                i++;
            }


            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        }

    }

    private void checkPartNumbers() {
        for(int i=0; i<140; i++){
            for(int j=0; j<140; j++){
                if(Character.isDigit(engine[i][j])){
                    boolean valid = checkAdjacent(i, j);
                    if(valid){
                        //find full number and add to sum
                        int startIndex = j;
                        int endIndex = j;

                        int index = j-1;
                        while(index >= 0){
                            if(Character.isDigit(engine[i][index])){
                                startIndex = index;
                                index--;
                            } else {
                                break;
                            }
                        }

                        index = j+1;
                        while(index <= 139){
                            if(Character.isDigit(engine[i][index])){
                                endIndex = index;
                                index++;
                            } else {
                                break;
                            }
                        }

                        StringBuilder stringBuilder = new StringBuilder();
                        index = startIndex;
                        while(index <= endIndex){
                            stringBuilder.append(engine[i][index]);
                            index++;
                        }

                        int partNumber = Integer.parseInt(stringBuilder.toString());
                        System.out.println(partNumber);

                        sum += partNumber;
                        j = endIndex;
                    }
                }
            }

        }

        System.out.println(sum);
    }

    private boolean checkAdjacent(int i, int j){
        //top
        if(i > 0){
            if(checkForSymbol(engine[i-1][j])){
                return true;
            }
        }

        //top right
        if(i > 0 && j < 139){
            if(checkForSymbol(engine[i-1][j+1])){
                return true;
            }
        }

        //right
        if(j < 139){
            if(checkForSymbol(engine[i][j+1])){
                return true;
            }
        }

        //bottom right
        if(i < 139 && j < 139){
            if(checkForSymbol(engine[i+1][j+1])){
                return true;
            }
        }

        //bottom
        if(i < 139){
            if(checkForSymbol(engine[i+1][j])){
                return true;
            }
        }

        //bottom left
        if(i < 139 && j > 0){
            if(checkForSymbol(engine[i+1][j-1])){
                return true;
            }
        }

        //left
        if(j > 0){
            if(checkForSymbol(engine[i][j-1])){
                return true;
            }
        }

        //top left
        if(i > 0 && j > 0){
            if(checkForSymbol(engine[i-1][j-1])){
                return true;
            }
        }

        return false;
    }

    private boolean checkForSymbol(char c){
        if(c != '.' && !Character.isDigit(c)){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Part1 solution = new Part1();
        solution.checkPartNumbers();
    }
}