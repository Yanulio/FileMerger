package Services;

import Models.Directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * A class for checking the file contents for requirements.
 */
public class RequirementsChecker {
    private static final String requireWord = "require";

    /**
     * Gets all requirement strings from file contents.
     * @param file A file to get requirements from.
     * @return A list of requirement strings.
     * @throws IOException If the file cannot be read.
     */
    private List<String> getRequirementsFromFile(File file) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(file.getPath()))) {
            String line;
            List<String> fileRequirements = new ArrayList<>();

            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                if (line.startsWith(requireWord)) {
                    fileRequirements.add(line.substring(requireWord.length() + 2, line.length() - 1));
                }
            }

            Collections.sort(fileRequirements);
            return fileRequirements;
        }
    }

    /**
     * Gets all the requirement for each file and builds a map of all adjacent files. Skips a file
     * if it doesn't exist or if it cannot be read.
     * @param directory A {@code Directory} with files to read from.
     * @return A map of adjacent files.
     */
    public Map<File, List<File>> getRequirementsFromDirectory(Directory directory) {
        Map<File, List<File>> requirementsMap = new HashMap<>();
        for (File file : directory.getListOfFiles()) {
            requirementsMap.put(file, new ArrayList<>());
        }

        for (File file : requirementsMap.keySet()) {
            try {
                List<String> fileRequirementPaths = getRequirementsFromFile(file);
                List<File> fileRequirements = new ArrayList<>();

                for (String filePath : fileRequirementPaths) {
                    fileRequirements.add(new File(directory.getPath() + '/' + filePath));
                }

                for (File requirement : fileRequirements) {
                    if (requirement.exists()) {
                        requirementsMap.get(file).add(requirement);
                    }
                    else {
                        System.out.println("File " + requirement.getPath() + " does not exist. Skipping it.");
                    }
                }

            } catch (IOException e) {
                System.out.println("Wasn't able to read the file. Skipping it");
            }
        }

        return requirementsMap;
    }
}
