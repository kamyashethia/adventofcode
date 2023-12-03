import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day3Test {

    @Test
    void test_getIndexOfCompleteNumber() {
        String row = ".....664...998..";
        ArrayList<Character> input = new ArrayList<>();
        char[] rowArray = row.toCharArray();
        for (char c : rowArray) {
            input.add(c);
        }
        ArrayList<Integer> actual = day3.getIndexOfCompleteNumber(input, 5);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(5); expected.add(6); expected.add(7);
        assertEquals(expected, actual );
    }


}
