package featurea.collections;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    @Test
    public void reverseStringWithPop() {
        String input = "Hello, World!";
        char[] expected = "!dlroW ,olleH".toCharArray();
        Stack<Character> stack = new Stack(input.toCharArray());
        char[] actual = new char[stack.getCapacity()];
        int index = 0;
        while (!stack.isEmpty()) {
            actual[index++] = stack.pop();
        }
        Assert.assertArrayEquals("", actual, expected);
    }

}
