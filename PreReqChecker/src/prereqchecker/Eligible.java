package prereqchecker;

import java.util.*;

/**
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {

    // Uses dfs to find taken courses
    private static void findCoursesTaken(String course, HashMap<String, ArrayList<String>> adjList, HashSet<String> coursesTaken) {
        coursesTaken.add(course);
        ArrayList<String> prereqs = adjList.get(course);
        for(String prereq: prereqs) {
            findCoursesTaken(prereq, adjList, coursesTaken);
        }
    }

    public static void main(String[] args) {

        if (args.length < 3) {
            StdOut.println(
                    "Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

        // Step 1
        String adjListInputFile = args[0];
        StdIn.setFile(adjListInputFile);

        // Step 1.1
        int a = StdIn.readInt();

        // Step 1.2
        HashMap<String, ArrayList<String>> adjList = new HashMap<>();
        for (int i = 0; i < a; i++) {
            adjList.put(StdIn.readString(), new ArrayList<>());
        }

        // Step 1.3
        int b = StdIn.readInt();

        // Step 1.4;
        for (int i = 0; i < b; i++) {
            ArrayList<String> prereqs = adjList.get(StdIn.readString());
            prereqs.add(StdIn.readString());
        }

        // Step 2
        String eligibleInputFile = args[1];
        StdIn.setFile(eligibleInputFile);

        // Step 2.1
        int c = StdIn.readInt();

        // Step 2.2
        HashSet<String> coursesTaken = new HashSet<>();
        for (int i = 0; i < c; i++) {
            String course = StdIn.readString();
            findCoursesTaken(course, adjList, coursesTaken);
        }

        // Step 3
        String outputFile = args[2];
        StdOut.setFile(outputFile);

        Set<String> allCourses = adjList.keySet();
        for (String course : allCourses) {
            if (!coursesTaken.contains(course)) {
                boolean canBeTaken = true;
                ArrayList<String> prereqs = adjList.get(course);
                for (String prereq : prereqs) {
                    if (!coursesTaken.contains(prereq)) {
                        canBeTaken = false;
                        break;
                    }
                }
                if (canBeTaken) {
                    StdOut.println(course);
                }
            }
        }

    }
}
