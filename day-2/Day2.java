import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Day2 {
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line; // The line being read
            int gameID; // The ID of the game currently being read
            int total = 0; // The total of all the IDs of impossible games

            // The specified number of allowed cubes of each color:
            int red = 12;
            int green = 13;
            int blue = 14;

            int numberOfCubes; // Stores current number of cubes of a certain color to compare against maximums
            String currentColor; // Current color of cubes being analyzed
            List<String> cubes = new ArrayList<>();
            List<String> pickUps = new ArrayList<>();
            boolean valid;

            while ((line = br.readLine()) != null) { // Read each line of the input text
                gameID = Integer.parseInt(line.substring(line.indexOf("e") + 1, line.indexOf(":")).trim());
                line = line.substring(line.indexOf(":") + 1).trim();
                cubes = new ArrayList<>(Arrays.asList(line.split(";")));
                valid = true;
                for (String cube : cubes) {
                    pickUps = new ArrayList<>(Arrays.asList(cube.split(",")));
                    for (String pickUp : pickUps) {
                        if (pickUp != null && pickUp != "") {
                            pickUp = pickUp.trim();
                            numberOfCubes = Integer.parseInt(pickUp.substring(0, pickUp.indexOf(" ")));
                            currentColor = pickUp.substring(pickUp.indexOf(" ")).trim();
                            if (("blue".equals(currentColor) && numberOfCubes > blue)
                                    || ("red".equals(currentColor) && numberOfCubes > red)
                                    || ("green".equals(currentColor) && numberOfCubes > green)) {
                                valid = false;
                            }
                        }
                    }
                    pickUps.clear();
                }
                if (valid) {
                    total = total + gameID;
                }
                cubes.clear();
            }
            return total;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    // Independent of Part 1, so there is some code repetition
    public static int part2() {
        int powerSum = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int numberOfCubes; // Stores current number of cubes of a certain color to compare against maximums
        String currentColor; // Current color of cubes being analyzed
        List<String> cubes = new ArrayList<>();
        List<String> pickUps = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) { // Read each line of the input text
                line = line.substring(line.indexOf(":") + 1).trim();
                cubes = new ArrayList<>(Arrays.asList(line.split(";")));

                for (String cube : cubes) {
                    pickUps = new ArrayList<>(Arrays.asList(cube.split(",")));
                    for (String pickUp : pickUps) {
                        if (pickUp != null && pickUp != "") {
                            pickUp = pickUp.trim();
                            numberOfCubes = Integer.parseInt(pickUp.substring(0, pickUp.indexOf(" ")));
                            currentColor = pickUp.substring(pickUp.indexOf(" ")).trim();
                            if("blue".equals(currentColor) && numberOfCubes > blue) {
                                blue = numberOfCubes;
                            } else if("red".equals(currentColor) && numberOfCubes > red) {
                                red = numberOfCubes;
                            } else if("green".equals(currentColor) && numberOfCubes > green) {
                                green = numberOfCubes;
                            }
                        }
                    }
                    pickUps.clear();
                }
                powerSum = powerSum + (blue*red*green);
                blue = 0;
                green = 0;
                red = 0;
                cubes.clear();
            }
            return powerSum;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
