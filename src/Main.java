import Controllers.ConsoleInput;
import Models.Directory;
import Models.FileGraph;
import Services.RequirementsChecker;
import View.ConsoleFileOutput;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Please, enter your directory path: ");
            Directory directory;
            while (true) {
                try {
                    directory = new Directory(ConsoleInput.getDirectoryPath());
                    break;
                }
                catch (FileNotFoundException e) {
                    System.out.println(e.getMessage() + " Please, try entering a different path.");
                }
                catch (NullPointerException e) {
                    System.out.println("Your path is an empty string. Please, try entering a different path.");
                }
            }
            RequirementsChecker checker = new RequirementsChecker();
            FileGraph graph = new FileGraph(checker.getRequirementsFromDirectory(directory));
            ConsoleFileOutput.printFilesToConsole(graph.getSortedFiles());
        }
        catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage() + " Please, change these files and restart the program.");
        }
    }
}