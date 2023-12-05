import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day4 {
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            int total = 0;
            String line;
            String winningStr;
            String mineStr;
            ArrayList<String> winningStrArr;
            ArrayList<String> mineStrArr;
            ArrayList<Integer> winning;
            ArrayList<Integer> mine;
            int winningNum = 0; // How many winning numbers I have
            int winningAmount = 0;

            while ((line = br.readLine()) != null) {
                winningNum = 0;
                winningAmount = 0;

                line = line.substring(line.indexOf(": ") + 2);
                winningStr = line.substring(0, line.indexOf(" | "));
                mineStr = line.substring(line.indexOf(" | ") + 3);

                winningStrArr = new ArrayList<>(Arrays.asList(winningStr.split(" ")));
                mineStrArr = new ArrayList<>(Arrays.asList(mineStr.split(" ")));
                winning = convertToIntArray(winningStrArr);
                mine = convertToIntArray(mineStrArr);

                // Identify how many winning numbers I have:
                for (int i = 0; i < mine.size(); i++) {
                    for (int z = 0; z < winning.size(); z++) {
                        if (mine.get(i) == winning.get(z)) {
                            winningNum++;
                        }
                    }
                }

                // Find the points per card
                if (winningNum > 0) {
                    winningAmount = 1;
                    for (int i = 1; i < winningNum; i++) {
                        winningAmount = winningAmount * 2;
                    }
                }

                total += winningAmount; // Combine into total points
                winningStrArr.clear();
                mineStrArr.clear();
                winning.clear();
                mine.clear();
            }

            return total;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static ArrayList<Integer> convertToIntArray(ArrayList<String> array) {
        for (int w = 0; w < array.size(); w++) {
            if (array.get(w).compareTo("") == 0) {
                array.remove(w);
                w--;
            }
        }
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            intArray.add(Integer.parseInt(array.get(i)));
        }
        return intArray;
    }

    public static int part2() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            int total = 0;
            String line;
            String winningStr;
            String mineStr;
            ArrayList<String> winningStrArr;
            ArrayList<String> mineStrArr;
            ArrayList<Integer> winning;
            ArrayList<Integer> mine;
            int winningNum = 0; // How many winning numbers I have
            ArrayList<Integer> copies = new ArrayList<>(); // Represents how many copies of each card are present
            int gameNum;

            // Populate the copies ArrayList
            for (int i = 0; i < 186; i++) {
                copies.add(1);
            }

            while ((line = br.readLine()) != null) {
                gameNum = Integer.parseInt(line.substring(4, line.indexOf(":")).trim());
                System.out.println(copies.toString());
                line = line.substring(line.indexOf(": ") + 2);
                winningStr = line.substring(0, line.indexOf(" | "));
                mineStr = line.substring(line.indexOf(" | ") + 3);

                winningStrArr = new ArrayList<>(Arrays.asList(winningStr.split(" ")));
                mineStrArr = new ArrayList<>(Arrays.asList(mineStr.split(" ")));
                winning = convertToIntArray(winningStrArr);
                mine = convertToIntArray(mineStrArr);

                // Identify how many winning numbers I have:
                for (int i = 0; i < mine.size(); i++) {
                    for (int z = 0; z < winning.size(); z++) {
                        if (mine.get(i) == winning.get(z)) {
                            winningNum++;
                        }
                    }
                }

                System.out.println("Game " + gameNum + " had " + winningNum + " winning numbers");

                for (int y = 0; y < winningNum; y++) {
                    copies.set((gameNum + y), copies.get(gameNum + y) + copies.get(gameNum - 1));
                }

                winningNum = 0;
                winningStrArr.clear();
                mineStrArr.clear();
                winning.clear();
                mine.clear();
            }

            for (int k = 0; k < copies.size(); k++) {
                total += copies.get(k);
            }

            return total;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
