import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day1part2 {
    public static void main(String[] args) throws FileNotFoundException {

        int sum = 0;
        //day2 data is same as day 1 data
        Scanner inputScanner = new Scanner(new File("day1data.txt"));
        while (inputScanner.hasNextLine()){
            int number = findDigit(inputScanner.nextLine());
            sum += number;
        }
        System.out.println(sum);

    }

    public static void day2() {}


    static String[] DIGITS = {
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    };


    //TODO: this isn't quite right -- the digits could be the string OR the int
    public static int findDigit(String s) {

        System.out.println("for string s: " + s);
        System.out.println(findFirstNumberStringOrInt(s));
        System.out.println(findLastNumberStringOrInt(s));
        return Integer.parseInt(findFirstNumberStringOrInt(s) + "" + findLastNumberStringOrInt(s));
    }

    public static int findLastNumberStringOrInt(String s) {
        int lastDigitStringAsInt = -1;
        int lastDigitStringIndex = -1;

        for (int i =0; i<DIGITS.length; i++) {

            if (s.contains(DIGITS[i])) {
                if (s.lastIndexOf(DIGITS[i]) > lastDigitStringIndex) {
                    lastDigitStringAsInt = i;
                    lastDigitStringIndex = s.lastIndexOf(DIGITS[i]);
                }
            }
        }


        for (int i = s.length() -1; i >=0; i--) {
            if (i < lastDigitStringIndex) {
                break;
            }
            if (Character.isDigit(s.charAt(i))) {
                return Character.getNumericValue(s.charAt(i));
            }
        }
        return lastDigitStringAsInt;

    }

    public static int findFirstNumberStringOrInt(String s) {
        int firstDigitStringAsint = -1;
        int firstDigitStringIndex = Integer.MAX_VALUE;

        for (int i =0; i<DIGITS.length; i++) {

            if (s.contains(DIGITS[i]) && (s.indexOf(DIGITS[i]) < firstDigitStringIndex)) {
                firstDigitStringAsint = i;
                firstDigitStringIndex = s.indexOf(DIGITS[i]);
            }
        }

        for (int i = 0; i<s.length(); i++) {
            if (i >= firstDigitStringIndex) {
                break;
            }
            if (Character.isDigit(s.charAt(i))) {
                return Character.getNumericValue(s.charAt(i));
            }
        }
        return firstDigitStringAsint;

    }


}
