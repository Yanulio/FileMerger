package View;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * A static class that prints to console all given files in the order.
 */
public final class ConsoleFileOutput {
    /**
     * Internal method that prints a given file to console.
     * @param file A file to print.
     */
    private static void printFileToConsole(File file) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(file.getPath()))) {
            while (bufferedReader.ready()) {
                System.out.println(bufferedReader.readLine());
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Wasn't able to read the file. Skipping it");
        }
    }

    /**
     * Prints all given files to console.
     * @param files A list of files to print.
     */
    public static void printFilesToConsole(List<File> files) {
        for (File file : files) {
            printFileToConsole(file);
        }
    }
}
