import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Part2 {

    public class Game {
        private int gameNo;
        private ArrayList<HashMap<String, Integer>> handfuls = new ArrayList<>();

        Game(int gameNo){
            this.gameNo = gameNo;
        }

        public void addHandfuls(HashMap<String, Integer> map){
            handfuls.add(map);
        }

        public int getGameNo(){
            return this.gameNo;
        }

        public ArrayList<HashMap<String, Integer>> getHandfuls(){
            return this.handfuls;
        }
    }

    private Scanner sc;
    private int powerSum = 0;
    private Game[] games = new Game[100];


    //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green

    public Part2(){
        readGames();
    }

    private void readGames(){
        try {
            File myFile = new File("day-2/input.txt");
            sc = new Scanner(myFile);

            int index = 0;
            while(sc.hasNextLine() || index <= 99){
                String line = sc.nextLine();
                line = line.substring(4);
                String[] splitLine = line.split(":");

                //1  ------ 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game game = new Game(Integer.parseInt(splitLine[0].trim()));
                //3 blue, 4 red -------- 1 red, 2 green, 6 blue ------- 2 green
                String[] handfuls = splitLine[1].split(";");
                for(int i=0; i<handfuls.length; i++){
                    //3 blue ------- 4 red
                    HashMap<String, Integer> map = new HashMap<>();
                    String[] handful = handfuls[i].split(",");
                    for(int j=0; j<handful.length; j++){
                        String[] hand = handful[j].trim().split(" ");
                        map.put(hand[1], Integer.parseInt(hand[0]));
                    }
                    game.addHandfuls(map);
                }

                games[index] = game;
                index++;
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        }

    }

    private void checkValidGames() {
        for(int i=0; i<100; i++){
            Game game = games[i];
            ArrayList<HashMap<String, Integer>> handfuls = game.getHandfuls();
            int redMax = 0;
            int greenMax = 0;
            int blueMax = 0;
            for(int j=0; j<handfuls.size(); j++){
                HashMap<String, Integer> handful = handfuls.get(j);
                if(handful.containsKey("red") && handful.get("red") > redMax){
                    redMax = handful.get("red");
                }
                if(handful.containsKey("green") && handful.get("green") > greenMax){
                    greenMax = handful.get("green");

                }
                if(handful.containsKey("blue") && handful.get("blue") > blueMax){
                    blueMax = handful.get("blue");
                }
            }

            powerSum += (redMax * greenMax * blueMax);

        }

        System.out.println(powerSum);
    }

    public static void main(String[] args) {
        Part2 solution = new Part2();
        solution.checkValidGames();

    }
}