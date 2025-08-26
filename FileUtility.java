import java.io.*;
import java.util.Scanner;

public class FileUtility {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- File Handling Utility ---");
            System.out.println("1. Write to a file (overwrite)");
            System.out.println("2. Read a file");
            System.out.println("3. Modify a file");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> writeToFile();
                case 2 -> readFromFile();
                case 3 -> modifyFile();
                case 4 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Write new content to a file (overwrite if exists)
    private static void writeToFile() {
        System.out.print("Enter file name to write: ");
        String fileName = scanner.nextLine();

        System.out.println("Enter content to write to the file:");
        String content = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Content written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read content from a file
    private static void readFromFile() {
        System.out.print("Enter file name to read: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        System.out.println("\n--- File Content ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Modify content in the file by replacing a word/line
    private static void modifyFile() {
        System.out.print("Enter file name to modify: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        // Read original content
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null)
                content.append(line).append(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Original file content:");
        System.out.println(content);

        System.out.print("Enter text to find: ");
        String toFind = scanner.nextLine();

        System.out.print("Enter replacement text: ");
        String toReplace = scanner.nextLine();

        // Modify content
        String modifiedContent = content.toString().replace(toFind, toReplace);

        // Write modified content back
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(modifiedContent);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error writing modified content: " + e.getMessage());
        }
    }
}

