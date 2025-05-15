import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
CSES 1084: Apartments

Time limit: 1.00 s
Memory limit: 512 MB

There are n applicants and m free apartments. Your task is to distribute the apartments so that as
many applicants as possible will get an apartment.
Each applicant has a desired apartment size, and they will accept any apartment whose size is close
enough to the desired size.

Input
The first input line has three integers n, m, and k: the number of applicants, the number of
apartments, and the maximum allowed difference.
The next line contains n integers a_1, a_2, ..., a_n: the desired apartment size of each applicant.
If the desired size of an applicant is x, they will accept any apartment whose size is between x-k
and x+k.
The last line contains m integers b_1, b_2, ..., b_m: the size of each apartment.
Output
Print one integer: the number of applicants who will get an apartment.
Constraints

• 1 <= n,m <= 2 . 10^5
• 0 <= k <= 10^9
• 1 <= ai,bi <= 10^9

Example
Input:
4 3 5
60 45 80 60
30 60 75

Output:
2

ANSWER: Sort both, and then run 2 pointers similar to merging 2 sorted lists.

Time Complexity: O(n log n).
 */
public class P1084 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            List<List<Integer>> in = readIn(br);
            System.out.println(numApartments(in.get(0), in.get(1), in.get(2).get(0)));
        }
    }

    static List<List<Integer>> readIn(BufferedReader br) {
        try {
            String[] nmk = br.readLine().split(" ");
            List<Integer> desiredSizes = new ArrayList<>();
            for (String i : br.readLine().split(" ")) {
                desiredSizes.add(Integer.parseInt(i));
            }
            List<Integer> sizes = new ArrayList<>();
            for (String i : br.readLine().split(" ")) {
                sizes.add(Integer.parseInt(i));
            }
            return List.of(desiredSizes, sizes, List.of(Integer.parseInt(nmk[2])));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static int numApartments(List<Integer> desiredSizes, List<Integer> sizes, int k) {
        desiredSizes.sort(Comparator.naturalOrder());
        sizes.sort(Comparator.naturalOrder());
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < desiredSizes.size() && j < sizes.size()) {
            int desired = desiredSizes.get(i);
            int size = sizes.get(j);
            int lo = desired - k;
            int hi = desired + k;
            if (size >= lo && size <= hi) {
                i++;
                j++;
                count++;
            } else if (size < hi) {
                j++;
            } else {
                i++;
            }
        }
        return count;
    }
}
