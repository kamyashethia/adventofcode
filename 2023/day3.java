import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class day3 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();
        Scanner scan = new Scanner(new File("day3data.txt"));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            ArrayList<Character> lineGrid = new ArrayList<>();
            for (char c : line.toCharArray()) {
                lineGrid.add(c);
            }
            grid.add(lineGrid);
        }
        System.out.println("grid size: " + grid.size());


        int sum = day3(grid);
        System.out.println("sum is: " + sum);
    }

    public static void printGrid(ArrayList<ArrayList<Character>> grid) {
        for (int x =0; x< grid.size(); x++) {
            for (int y = 0; y< grid.get(x).size(); y++) {
                System.out.print(grid.get(x).get(y));
            }
            System.out.println();
        }
    }

    public static int day3(ArrayList<ArrayList<Character>> grid) {
        int sum = 0;
        for (int i =0; i<grid.size(); i++) {
            for (int j =0; j< grid.get(i).size(); j++) {

                if (Character.isDigit(grid.get(i).get(j))) {
                    // if this is a digit, then we need to find the index of the entire digit
                    // so there will be like a getNumber() index, which is an array of the number.
                    // the number will have to be to the right.
                    // so once I have the number and the index,
                    // I look to see if there are any characters in the index.
                    // that should be a separate function
                    // if the number if valid, I add it to the sum. Then we have to shift i by the number index.

                    ArrayList<Integer> numberIndices = getIndexOfCompleteNumber(grid.get(i), j);
                    StringBuilder numberprint = new StringBuilder();
                    for (int x = 0; x< numberIndices.size(); x++) {
                        numberprint.append(grid.get(i).get(numberIndices.get(x)));
                    }

                    boolean isValid = checkIfValidNumber(grid, numberIndices, i);
                    System.out.println("number: " + numberprint + " is valid: " + isValid);

                    if (isValid) {
                        StringBuilder number = new StringBuilder();
                        for (int x = 0; x< numberIndices.size(); x++) {
                            number.append(grid.get(i).get(numberIndices.get(x)));
                        }
                        sum += Integer.parseInt(number.toString());
                        j = j + (numberIndices.size() - 1);

                    }

                }
            }
        }
        return sum;
    }

    public static boolean checkIfValidNumber(ArrayList<ArrayList<Character>> grid, ArrayList<Integer> numberIndices, int i) {

        ArrayList<Integer> rowIndices = new ArrayList<>();

        //populate rowIndices
        if (numberIndices.get(0) >=1) {
            rowIndices.add(0, numberIndices.get(0) -1);
        }
        rowIndices.addAll(numberIndices);
        if (numberIndices.get(numberIndices.size()-1) <= grid.get(1).size() - 2) {
            rowIndices.add(numberIndices.get(numberIndices.size()-1) + 1);
        }

        //populate column indices
        ArrayList<Integer> columnIndices = new ArrayList<>();
        if (i>=1) {
            columnIndices.add(i-1);
        }
        columnIndices.add(i);
        if (i <= grid.size() -2) {
            columnIndices.add(i+1);
        }

        //check top row
        for (int x = 0; x< rowIndices.size(); x++) {
            //iterate through all the elements in numbers indices
            for (int y = 0; y< columnIndices.size(); y++) {

                Character gridElement = grid.get(columnIndices.get(y)).get(rowIndices.get(x));
                if (isValid(gridElement)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValid(Character gridElement) {
        return !Character.isDigit(gridElement) && !gridElement.equals(Character.valueOf('.'));
    }

    public static ArrayList<Integer> getIndexOfCompleteNumber(ArrayList<Character> gridLine, int j) {
        ArrayList<Integer> numberIndices = new ArrayList<>();
        for (int y =j; y<gridLine.size(); y++) {
            if (Character.isDigit(gridLine.get(y))) {
                numberIndices.add(y);
            } else {
                break;
            }
        }
        return numberIndices;
    }


}
