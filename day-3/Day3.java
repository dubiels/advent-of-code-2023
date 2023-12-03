import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.text.html.HTMLDocument.HTMLReader.CharacterAction;

public class Day3 {
  public static void main(String[] args) {
    System.out.println(part1());
    System.out.println(part2());
  }

  public static int part1() {
    int total = 0;
    int current = 0;
    boolean adjacent = false;
    ArrayList<Integer> indexes = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
      String line = br.readLine(); // Read the first line of input before entering the loop
      String previousLine = null;
      String nextLine = br.readLine();
      int i = 0; // Counts index within line
      int x = 1;
      while (line != null) {
        // Identify the digit within line:
        adjacent = false;
        if (i <= 139 && Character.isDigit(line.charAt(i))) {
          indexes.add(i);

          if (!(i == 139)) {
            while (((i + x) < 140) && Character.isDigit(line.charAt(i + x))) {
              indexes.add(i + x);
              x++;
            }
            x = 1;
          }
          // Indexes of all the digits in this number are now added to indexes ArrayList

          // Check if the digits are adjacent to a symbol in ITS OWN LINE:
          // Checks spot after the end of the number:
          if ((indexes.get(indexes.size() - 1) + 1) <= 139) {
            if (!Character.isLetterOrDigit(line.charAt(indexes.get(indexes.size() - 1) + 1)) && ((line.charAt(indexes.get(indexes.size() - 1) + 1)) != '.')) {
              adjacent = true;
            }
          }
          // Checks spot before the beginning of the number:
          if ((indexes.get(0) != 0)) {
            if (!Character.isLetterOrDigit(line.charAt(indexes.get(0) - 1)) && (line.charAt(indexes.get(0) - 1) != '.')) {
              adjacent = true;
            }
          }

          // Check if the digits are adjacent to a symbol in the line ABOVE:
          if (previousLine != null) {
            for (int z = 0; z < indexes.size(); z++) {
              if ((!Character.isLetterOrDigit(previousLine.charAt(indexes.get(z)))) && (previousLine.charAt(indexes.get(z)) != '.')) {
                adjacent = true;
              }
              // Diagonal cases:
              if (indexes.get(0) - 1 > 0) {
                if (((!Character.isLetterOrDigit(previousLine.charAt(indexes.get(0) - 1))) && (previousLine.charAt(indexes.get(0) - 1) != '.'))) {
                  adjacent = true;
                }
              }
              if (indexes.get(indexes.size() - 1) + 1 < 139) {
                if (!Character.isLetterOrDigit(previousLine.charAt(indexes.get(indexes.size() - 1) + 1)) && (previousLine.charAt(indexes.get(indexes.size() - 1) + 1) != '.')) {
                  adjacent = true;
                }
              }
            }
          }

          // Check if the digits are adjacent to a symbol in the line BELOW:
          if (nextLine != null) {
            for (int z = 0; z < indexes.size(); z++) {
              if ((!Character.isLetterOrDigit(nextLine.charAt(indexes.get(z)))) && (nextLine.charAt(indexes.get(z)) != '.')) {
                adjacent = true;
              }
              // Diagonal cases:
              if (indexes.get(0) - 1 > 0) {
                if (((!Character.isLetterOrDigit(nextLine.charAt(indexes.get(0) - 1))) && (nextLine.charAt(indexes.get(0) - 1) != '.'))) {
                  adjacent = true;
                }
              }
              if (indexes.get(indexes.size() - 1) + 1 < 139) {
                if (!(Character.isLetterOrDigit(nextLine.charAt(indexes.get(indexes.size() - 1) + 1))) && (nextLine.charAt(indexes.get(indexes.size() - 1) + 1) != '.')) {
                  adjacent = true;
                }
              }
            }
          }

          if (adjacent) {
            for (int w = 0; w < indexes.size(); w++) {
              current = current + Character.getNumericValue(line.charAt(indexes.get(w))) * (int) (Math.pow(10, (indexes.size() - w - 1)));
            }
            total += current;
          }

          i = indexes.get(indexes.size() - 1) + 1;
          if (i >= 139) {
            i = 0;
            previousLine = line;
            line = nextLine;
            nextLine = br.readLine();
            adjacent = false;
          }
          current = 0;
          indexes.clear();
        } else {
          if (i >= 139) {
            i = 0;
            previousLine = line;
            line = nextLine;
            nextLine = br.readLine();
            adjacent = false;
          } else {
            i++;
          }
        }
      }
      return total;
    } catch (IOException e) {
      e.printStackTrace();
      return 1;
    }
  }

  public static int part2() {
    int total = 0;
    int current = 0;
    int index;
    ArrayList<Integer> numbers = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
      String line = br.readLine(); // Read the first line of input before entering the loop
      String previousLine = null;
      String nextLine = br.readLine();
      int i = 0; // Counts index within line

      while (line != null) {
        // Identify the * within line:
        if (i <= 139 && (line.charAt(i)) == '*') {
          index = i;
          // Check if the * are adjacent to a digit in ITS OWN LINE:
          // Checks spot after the end of the number:
          if ((index + 1) <= 139) {
            if (Character.isDigit(line.charAt(index + 1))) {
              if (!numbers.contains(numberDetector(index + 1, line))) {
                numbers.add(numberDetector(index + 1, line));
              }
            }
          }
          // Checks spot before the beginning of the number:
          if ((index != 0)) {
            if (Character.isDigit(line.charAt(index - 1))) {
              if (!numbers.contains(numberDetector(index - 1, line))) {
                numbers.add(numberDetector(index - 1, line));
              }
            }
          }

          // Check if the digits are adjacent to a symbol in the line ABOVE:
          if (previousLine != null) {
            if (Character.isDigit(previousLine.charAt(index))) {
              if (!numbers.contains(numberDetector(index, previousLine))) {
                numbers.add(numberDetector(index, previousLine));
              }
            }

            if ((index + 1) <= 139) {
              if (Character.isDigit(previousLine.charAt(index + 1))) {
                if (!numbers.contains(numberDetector(index + 1, previousLine))) {
                  numbers.add(numberDetector(index + 1, previousLine));
                }
              }
            }
            // Checks spot before the beginning of the number:
            if ((index != 0)) {
              if (Character.isDigit(previousLine.charAt(index - 1))) {
                if (!numbers.contains(numberDetector(index - 1, previousLine))) {
                  numbers.add(numberDetector(index - 1, previousLine));
                }
              }
            }
          }

          // Check if the digits are adjacent to a symbol in the line BELOW:
          if (nextLine != null) {
            if (Character.isDigit(nextLine.charAt(index))) {
              numbers.add(numberDetector(index, nextLine));
            }

            if ((index + 1) <= 139) {
              if (Character.isDigit(nextLine.charAt(index + 1))) {
                if (!numbers.contains(numberDetector(index + 1, nextLine))) {
                  numbers.add(numberDetector(index + 1, nextLine));
                }
              }
            }
            // Checks spot before the beginning of the number:
            if ((index != 0)) {
              if (Character.isDigit(nextLine.charAt(index - 1))) {
                if (!numbers.contains(numberDetector(index - 1, nextLine))) {
                  numbers.add(numberDetector(index - 1, nextLine));
                }
              }
            }
          }

          if (numbers.contains(0)) {
            numbers.remove(numbers.indexOf(0));
          }
          if (numbers.size() == 2) {
            current = numbers.get(0) * numbers.get(1);
            total += current;
          }

          i++;
          if (i >= 139) {
            i = 0;
            previousLine = line;
            line = nextLine;
            nextLine = br.readLine();
          }
          current = 0;
          numbers.clear();

        } else {
          // Go to new line
          if (i >= 139) {
            i = 0;
            previousLine = line;
            line = nextLine;
            nextLine = br.readLine();

          } else {
            i++;
          }
        }
      }
      return total;
    } catch (IOException e) {
      e.printStackTrace();
      return 1;
    }
  }

  public static int numberDetector(int index, String line) {
    int number = 0;
    boolean eval = true;
    ArrayList<Integer> numberConstruction = new ArrayList<>();

    // Finding the starting index of the number
    while (Character.isDigit(line.charAt(index))) {
      if (index > 0) {
        if (Character.isDigit(line.charAt(index - 1))) {
          index = index - 1;
        } else {
          break;
        }
      } else {
        break;
      }
    }

    // Steps forward to collect full number
    while (Character.isDigit(line.charAt(index))) {
      numberConstruction.add((int) (line.charAt(index)));
      if (index < 139) {
        index++;
      } else {
        break;
      }
    }

    // Assembling the number
    for (int w = 0; w < numberConstruction.size(); w++) {
      number = number + Character.getNumericValue(numberConstruction.get(w)) * (int) (Math.pow(10, (numberConstruction.size() - w - 1)));
    }
    numberConstruction.clear();
    return number;
  }
}
