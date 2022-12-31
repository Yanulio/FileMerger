package Models;

import java.io.File;
import java.util.*;

/**
 * A representation of a directed graph of requirements in given files.
 */
public class FileGraph {
    private final Map<File, List<File>> graphData;
    private static final Comparator<File> fileNameComparator = Comparator.comparing(File::getName);

    /**
     * Creates a new {@code FileGraph} instance by copying the given map of all adjacent vertices (files).
     * @param adjacencyMap A map of all adjacent files.
     */
    public FileGraph(Map<File, List<File>> adjacencyMap) {
        graphData = new HashMap<>(adjacencyMap);
    }

    /**
     * Gets a list of sorted vertices according to the following principle:
     * If file A requires file B, then file B stands before file A.
     * If the files are equivalent, then the file with a lesser alphabetically
     * name will come first.
     * @return A list of sorted files.
     * @throws UnsupportedOperationException If the graph is cyclic and cannot be sorted.
     */
    public List<File> getSortedFiles() throws UnsupportedOperationException {
        List<File> sortedFiles = new ArrayList<>();
        List<File> fileVertices = new ArrayList<>(graphData.keySet());
        fileVertices.sort(fileNameComparator);
        boolean hasEmptyVertices = true;

        while (!graphData.isEmpty()) {
            File vertexToDelete = null;
            for (File keyFile : fileVertices) {
                if (graphData.get(keyFile).isEmpty()) {
                    sortedFiles.add(keyFile);
                    hasEmptyVertices = true;
                    vertexToDelete = keyFile;
                    break;
                }
                hasEmptyVertices = false;
            }

            if (hasEmptyVertices) {
                graphData.remove(vertexToDelete);
                fileVertices.remove(vertexToDelete);
                for (File keyFile : graphData.keySet()) {
                    while (graphData.get(keyFile).remove(vertexToDelete)) { }
                }
            }
            else {
                StringBuilder cyclicFiles = new StringBuilder();
                for (File file : fileVertices) {
                    cyclicFiles.append(file.getName());
                    cyclicFiles.append("; ");
                }
                throw new UnsupportedOperationException("The graph is cyclic. These files are the reason: " + cyclicFiles + '.');
            }
        }
        return sortedFiles;
    }
}
