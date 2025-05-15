import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
CSES 1621: Distinct Numbers

Time limit: 1.00 s
Memory limit: 512 MB

You are given a list of n integers, and your task is to calculate
the number of distinct values in the list.

Input
The first input line has an integer n: the number of values.
The second line has n integers x_1,x_2,\dots,x_n.

Output
Print one integers: the number of distinct values.

Constraints

• 1 <= n <= 2 . 10^5
• 1 <= xi <= 10^9

Example
Input:
5
2 3 2 2 3

Output:
2
 */
public class P1621 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            List<Integer> nums = readIn(br);
            System.out.println(numDistinct(nums));
        }
    }

    static List<Integer> readIn(BufferedReader br) {
        try {
            Integer.parseInt(br.readLine());
            List<Integer> nums = new ArrayList<>();
            for (String i : br.readLine().split(" ")) {
                nums.add(Integer.parseInt(i));
            }
            return nums;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static int numDistinct(List<Integer> nums) {
        return new HashSet<>(nums).size();
    }
}
