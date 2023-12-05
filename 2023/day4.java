import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class day4 {

    HashMap<Integer, Integer> gameNumberToCount = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException {
        int sumPartOne = 0;
        Scanner inputScanner = new Scanner(new File("day4data.txt"));
        int gameNumber = 1;
        day4 day4 = new day4();
        while (inputScanner.hasNextLine()){
            String line = inputScanner.nextLine();

            sumPartOne+= getGamePointsPartOne(line);
            day4.getGamePointsPartTwo(line, gameNumber);

            gameNumber++;
        }

        System.out.println("SUM FOR PART ONE: " + sumPartOne);
        int sumPartTwo =0;
        for(Integer key: day4.gameNumberToCount.keySet()) {
            System.out.println("game: " + key + " count: " + day4.gameNumberToCount.get(key));
            sumPartTwo += day4.gameNumberToCount.get(key);
        }
        System.out.println("SUM FOR PART TWO: " + sumPartTwo);


    }

    public static int getGamePointsPartOne(String game) {
        int numberOfGamesWon = 0;
        String[] wonAndPlayed = game.split(":")[1].split("\\|");
        String[] winningNumbers = wonAndPlayed[0].split(" ");
        String[] playedNumbers = wonAndPlayed[1].split(" ");

        for (String playedNumber: playedNumbers) {
            if (Arrays.asList(winningNumbers).contains(playedNumber)) {
//                System.out.println("playedNumber: " + playedNumber);
                if (playedNumber.trim().isEmpty()) {
                    continue;
                }
                numberOfGamesWon++;
            }
        }
//        System.out.println("RETURNING" + (int)Math.pow(2, numberOfGamesWon-1));
        return (int)Math.pow(2, numberOfGamesWon-1);
    }

    public void getGamePointsPartTwo(String game, int gameNumber) {
        int numberOfGamesWon = 0;
        System.out.println("gameNumber" + gameNumber);

        String[] wonAndPlayed = game.split(":")[1].split("\\|");
        String[] winningNumbers = wonAndPlayed[0].split(" ");
        String[] playedNumbers = wonAndPlayed[1].split(" ");

        int currentCountOfCardForGame = gameNumberToCount.getOrDefault(gameNumber, 0);
        for (String playedNumber: playedNumbers) {
            if (Arrays.asList(winningNumbers).contains(playedNumber)) {
                if (playedNumber.trim().isEmpty()) {
                    continue;
                }
                numberOfGamesWon++;
            }
        }
        System.out.println(numberOfGamesWon);
        // right now, we know how many games this hand has one
        // we need to figure out WHICH cards we can get, and how many
        // the HOW MANY of each should be currentCountOfCardForGame + 1
        // the which cards, should be the gameNumber +1 ---> gameNumber + numberofGamesWon, inclusive

        for (int i =0; i<(currentCountOfCardForGame +1); i++) {
            for (int j = gameNumber+1; j<= gameNumber + numberOfGamesWon; j++) {
                appendGameNumberToCount(j);

            }
        }
        //have to add in the original number
        appendGameNumberToCount(gameNumber);


    }

    public void appendGameNumberToCount(int i) {
        if (gameNumberToCount.containsKey(i)) {
            int current = gameNumberToCount.get(i);
            gameNumberToCount.put(i, current + 1);
        } else {
            gameNumberToCount.put(i, 1);
        }
    }
    public static void printStringArray(String[] arr) {
        for (String s: arr) {
            System.out.print(s + "  ");
        }
        System.out.println();
    }



}
