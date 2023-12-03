import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day2 {

    static class GamePlay {
        int gameNumber;
        String[] gameData;

        public GamePlay(int gameNumber, String[] gameData) {
            this.gameNumber = gameNumber;
            this.gameData = gameData;
        }
    }

    //only 12 red cubes, 13 green cubes, and 14 blue cubes
    final static int RED_CUBES = 12;
    final static int GREEN_CUBES = 13;
    final static int BLUE_CUBES = 14;

    public static void main(String[] args) throws FileNotFoundException {
        int sum = 0;
        Scanner inputScanner = new Scanner(new File("day2data.txt"));
        while (inputScanner.hasNextLine()){
            sum+= checkGameplay(parseLine((inputScanner.nextLine())));

        }

        System.out.println("SUM IS: " + sum);
    }

    //returns the number of the gameplay or 0 if the gameplay is invalid
    public static int checkGameplay(GamePlay gameplay) {

        for (String game: gameplay.gameData) {
            int blueCubes =0 ,redCubes =0 ,greenCubes =0;
//            System.out.println("game: " + game);
            if (game.contains("blue")) {
                blueCubes = findCubesForColor("blue", game);
            }
            if (game.contains("green")) {
                greenCubes = findCubesForColor("green", game);
            }
            if (game.contains("red")) {
                redCubes = findCubesForColor("red", game);
            }
            if (!isValidGame(redCubes, blueCubes, greenCubes)) {
                return 0;
            }
        }
        return gameplay.gameNumber;
    }

    public static int findCubesForColor(String color, String game) {
        String regexExp = "(\\d+) " + color;
        Pattern pattern = Pattern.compile(regexExp);
        Matcher matcher = pattern.matcher(game);
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }

    public static boolean isValidGame(int redCubes, int blueCubes, int greenCubes) {
        if (redCubes > RED_CUBES || blueCubes > BLUE_CUBES || greenCubes > GREEN_CUBES) {
//            System.out.printf("Game is NOT valid for red: %d, blue: %d, green: %d\n", redCubes, blueCubes, greenCubes);
            return false;
        }
//        System.out.printf("Game is valid for red: %d, blue: %d, green: %d\n", redCubes, blueCubes, greenCubes);
        return true;
    }

    public static GamePlay parseLine(String gameplayLine) {
        String regexExp = "Game (\\d+): (.*)";
        Pattern pattern = Pattern.compile(regexExp);
        Matcher matcher = pattern.matcher(gameplayLine);
        matcher.find();
//        System.out.println(gameplayLine);
//        System.out.println(matcher.group(1));
//        System.out.println(matcher.group(2));
        matcher.group(2).split(";");

        return new GamePlay(
                Integer.parseInt(matcher.group(1)),
                matcher.group(2).split(";")
        );

    }
}
