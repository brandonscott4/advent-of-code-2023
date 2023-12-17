import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Part2 {

    private Scanner sc;
    private char[][] engine = new char[140][140];
    private int sum = 0;

    public Part2(){
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
                if(engine[i][j] == '*'){
                    int[][] partNumberIndexes = checkAdjacent(i, j);
                    if(partNumberIndexes[0][0] != 0 || partNumberIndexes[0][1] != 0 || partNumberIndexes[1][0] != 0 || partNumberIndexes[1][1] != 0){
                        int partNumber1 = makePartNumber(partNumberIndexes[0][0], partNumberIndexes[0][1]);
                        int partNumber2 = makePartNumber(partNumberIndexes[1][0], partNumberIndexes[1][1]);
                        sum += partNumber1 * partNumber2;
                    }
                }
            }

        }

        System.out.println(sum);
    }

    private int[][] checkAdjacent(int i, int j){
        int countAdjacent = 0;
        int[][] indexes = new int[2][2];

        //used to handle when same part number is in two different positions (where there is not a . seperating)
        boolean numTopLeft = false;
        boolean numTop= false;
        boolean numBottomLeft = false;
        boolean numBottom = false;

        //top left
        if(i > 0 && j > 0){
            if(Character.isDigit(engine[i-1][j-1])){
                if(countAdjacent <= 1){
                    if(countAdjacent == 0){
                        indexes[0][0] = i-1;
                        indexes[0][1] = j-1;
                    } else {
                        indexes[1][0] = i-1;
                        indexes[1][1] = j-1;
                    }
                    numTopLeft = true;
                    countAdjacent++;
                } else {
                    return indexes;
                }
            }
        }

        //top
        if(i > 0){
            if(Character.isDigit(engine[i-1][j])){
                numTop= true;
                if(!numTopLeft){
                    if(countAdjacent <= 1){
                        if(countAdjacent == 0){
                            indexes[0][0] = i-1;
                            indexes[0][1] = j;
                        } else {
                            indexes[1][0] = i-1;
                            indexes[1][1] = j;
                        }
                        countAdjacent++;
                    } else {
                        return indexes;
                    }
                }
            }
        }

        //top right
        if(i > 0 && j < 139){
            if(Character.isDigit(engine[i-1][j+1]) && !numTop){
                if(countAdjacent <= 1){
                    if(countAdjacent == 0){
                        indexes[0][0] = i-1;
                        indexes[0][1] = j+1;
                    } else {
                        indexes[1][0] = i-1;
                        indexes[1][1] = j+1;
                    }
                    countAdjacent++;
                } else {
                    return indexes;
                }
            }
        }

         //left
        if(j > 0){
            if(Character.isDigit(engine[i][j-1])){
                if(countAdjacent <= 1){
                    if(countAdjacent == 0){
                        indexes[0][0] = i;
                        indexes[0][1] = j-1;
                    } else {
                        indexes[1][0] = i;
                        indexes[1][1] = j-1;
                    }
                    countAdjacent++;
                } else {
                    return indexes;
                }
            }
        }

        //right
        if(j < 139){
            if(Character.isDigit(engine[i][j+1])){
                if(countAdjacent <= 1){
                    if(countAdjacent == 0){
                        indexes[0][0] = i;
                        indexes[0][1] = j+1;
                    } else {
                        indexes[1][0] = i;
                        indexes[1][1] = j+1;
                    }
                    countAdjacent++;
                } else {
                    return indexes;
                }
            }
        }

        //bottom left
        if(i < 139 && j > 0){
            if(Character.isDigit(engine[i+1][j-1])){
                if(countAdjacent <= 1){
                    if(countAdjacent == 0){
                        indexes[0][0] = i+1;
                        indexes[0][1] = j-1;
                    } else {
                        indexes[1][0] = i+1;
                        indexes[1][1] = j-1;
                    }
                    countAdjacent++;
                    numBottomLeft = true;
                } else {
                    return indexes;
                }
            }
        }

        //bottom
        if(i < 139){
            if(Character.isDigit(engine[i+1][j])){
                numBottom = true;
                if(!numBottomLeft){
                    if(countAdjacent <= 1){
                        if(countAdjacent == 0){
                            indexes[0][0] = i+1;
                            indexes[0][1] = j;
                        } else {
                            indexes[1][0] = i+1;
                            indexes[1][1] = j;
                        }
                        countAdjacent++;
                    } else {
                        return indexes;
                    }
                }
            }
        }

        //bottom right
        if(i < 139 && j < 139){
            if(Character.isDigit(engine[i+1][j+1]) && !numBottom){
                if(countAdjacent <= 1){
                    if(countAdjacent == 0){
                        indexes[0][0] = i+1;
                        indexes[0][1] = j+1;
                    } else {
                        indexes[1][0] = i+1;
                        indexes[1][1] = j+1;
                    }
                    countAdjacent++;
                } else {
                    return indexes;
                }
            }
        }

        if(countAdjacent == 2){
            return indexes;
        }

        return new int[2][2];
    }

    private int makePartNumber(int i, int j){
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

        return partNumber;
    }

    public static void main(String[] args) {
        Part2 solution = new Part2();
        solution.checkPartNumbers();
    }
}