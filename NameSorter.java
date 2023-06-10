import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NameSorter {
    public static void main(String[] args) {
        String originalFilePath = "unsorted-names-list.txt";  // Replace with the actual file path if different
        String sortedFilePath = "sorted-names-list.txt";  // New file name for the sorted names
        List<String> names = readNamesFromFile(originalFilePath);
        sortNames(names);
        writeNamesToFile(sortedFilePath, names);
        System.out.println("Names sorted and written to file successfully!");
    }

    public static List<String> readNamesFromFile(String filePath) {
        List<String> names = new ArrayList<>();

        // Read names from the text file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                names.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return names;
    }

    public static void sortNames(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String name1, String name2) {
                String[] parts1 = name1.split(" ");
                String[] parts2 = name2.split(" ");
                String lastName1 = parts1[parts1.length - 1];
                String lastName2 = parts2[parts2.length - 1];
                return lastName1.compareToIgnoreCase(lastName2);
            }
        });
    }

    public static void writeNamesToFile(String filePath, List<String> names) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String name : names) {
                writer.write(name);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}