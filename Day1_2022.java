import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1_2022 {
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            int maxCount = 0;
            int currCount = 0;

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (currCount > maxCount) {
                        maxCount = currCount;
                    }
                    currCount = 0;
                } else {
                    currCount += Integer.parseInt(line.trim());
                }
            }

            return maxCount;

        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static int part2() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            ArrayList<Integer> maxCountList = new ArrayList<>();
            int currCount = 0;

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (maxCountList.size() == 0 || currCount > maxCountList.get(maxCountList.size() - 1)) {
                        maxCountList.add(currCount);
                    } 
                    currCount = 0;
                } else {
                    currCount += Integer.parseInt(line.trim());
                }
            }

            return (maxCountList.get(maxCountList.size() - 1) + maxCountList.get(maxCountList.size() - 2)
                    + maxCountList.get(maxCountList.size() - 3));

        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }
}