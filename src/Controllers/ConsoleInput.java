package Controllers;

import java.util.Scanner;

/**
 * A static class that gets user input of a directory path from console.
 */
public final class ConsoleInput {
    private static final Scanner inputStream = new Scanner(System.in);

    /**
     * Gets a directory path from console.
     * @return A directory path.
     */
    public static String getDirectoryPath() {
        String directoryPath = inputStream.nextLine();
        return directoryPath.trim();
    }
}
