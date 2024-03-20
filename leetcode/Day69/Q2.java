package leetcode.Day69;

public class Q2 {
    public int[] swapNumbers(int[] numbers) {
            numbers[0]^=numbers[1];
            numbers[1]^=numbers[0];
            numbers[0]^=numbers[1];
            return numbers;
    }
}
