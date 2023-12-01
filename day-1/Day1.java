import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Character;

public class Day1 {
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() {
        int total = 0;
        ArrayList<Integer> indexes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                for (int i = 0; i < (line.length()); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        indexes.add(i);
                    }
                }
                if (indexes.size() == 1) {
                    total = total + Character.getNumericValue(line.charAt(indexes.get(0))) * 10
                            + Character.getNumericValue(line.charAt(indexes.get(0)));
                } else if (indexes.size() > 1) {
                    total = total + (Character.getNumericValue(line.charAt(indexes.get(0))) * 10
                            + Character.getNumericValue(line.charAt(indexes.get(indexes.size() - 1))));
                }
                indexes.clear();
            }
            return total;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static int part2() {
        int total = 0;
        ArrayList<Integer> indexes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = lineFix(line);
                for (int i = 0; i < (line.length()); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        indexes.add(i);
                    }
                }
                if (indexes.size() == 1) {
                    total = total + Character.getNumericValue(line.charAt(indexes.get(0))) * 10
                            + Character.getNumericValue(line.charAt(indexes.get(0)));
                } else if (indexes.size() > 1) {
                    total = total + (Character.getNumericValue(line.charAt(indexes.get(0))) * 10
                            + Character.getNumericValue(line.charAt(indexes.get(indexes.size() - 1))));
                }
                indexes.clear();
            }
            return total;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static String lineFix(String line) {
        String modify = line;
        if(modify.contains("one")){
            modify = modify.replace("one", "o1e");
        }
        if(modify.contains("two")){
            modify = modify.replace("w", "t2o");
        }
        if(modify.contains("three")){
            modify = modify.replace("three", "th3ee");
        }
        if(modify.contains("four")){
            modify = modify.replace("four", "f4ur");
        }
        if(modify.contains("five")){
            modify = modify.replace("five", "f5ve");
        }
        if(modify.contains("six")){
            modify = modify.replace("six", "s6x");
        }
        if(modify.contains("seven")){
            modify = modify.replace("eve", "se7en");
        }
        if(modify.contains("eight")){
            modify = modify.replace("igh", "ei8ht");
        }
        if(modify.contains("nine")){
            modify = modify.replace("ine", "n9ne");
        }
        return modify;
    }
}