package Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An abstract representation of a directory. Consists of a directory path and a list of files (not directories)
 * inside this directory.
 */
public class Directory {
    private final String directoryPath;
    private final List<File> listOfFiles;

    /**
     * Creates a new {@code Directory} instance by saving the given pathname string and
     * creating a list of files inside given directory.
     * @param directoryPath A pathname string.
     * @throws FileNotFoundException If such directory does not exist.
     */
    public Directory(String directoryPath) throws FileNotFoundException {
        this.directoryPath = directoryPath;
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new FileNotFoundException("The directory does not exist.");
        }

        listOfFiles = new ArrayList<>();
        getFiles(directoryPath);
    }

    /**
     * Internal method that adds every file in the directory to the {@code listOfFiles}.
     * @param directoryPath A pathname string.
     * @throws NullPointerException If the given directory is an empty string.
     */
    private void getFiles(String directoryPath) throws NullPointerException {
        File directory = new File(directoryPath);
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                listOfFiles.add(file);
            }
            else if (file.isDirectory()) {
                getFiles(file.getAbsolutePath());
            }
        }
    }

    /**
     * Gets a copy of the {@code listOfFiles}.
     * @return A copy of the {@code listOfFiles}.
     */
    public List<File> getListOfFiles() {
        return new ArrayList<>(listOfFiles);
    }

    /**
     * Gets a pathname string.
     * @return A pathname string.
     */
    public String getPath() {
        return directoryPath;
    }
}
