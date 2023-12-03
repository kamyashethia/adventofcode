import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class day1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        testday1();
    }

    public static int findDigit(String s) {
      boolean foundFirstDigit = false;
      boolean foundLastDigit = false;
      char digit1 = ' ';
      char digit2 = ' ';

      for (int i =0; i< s.length(); i++) {
          if (!foundFirstDigit && Character.isDigit(s.charAt(i))) {
            digit1 = s.charAt(i);
            foundFirstDigit = true;
          }
          if (!foundLastDigit && Character.isDigit(s.charAt(s.length() - i - 1))) {
              digit2 = s.charAt(s.length() - i - 1);
              foundLastDigit = true;
          }

      }
      return Integer.parseInt(digit1 + "" +  digit2);
    }


    public static void testday1() throws IOException {

        int sum = 0;
        Scanner inputScanner = new Scanner(new File("day1data.txt"));
        while (inputScanner.hasNextLine()){
           int number = findDigit(inputScanner.nextLine());
           sum += number;
        }
        System.out.println(sum);

    }
}
