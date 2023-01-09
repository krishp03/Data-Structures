package prereqchecker;

import java.util.*;

/**
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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {

    // Uses dfs to find taken courses
    private static void findCoursesTaken(String course, HashMap<String, ArrayList<String>> adjList, HashSet<String> coursesTaken) {
        coursesTaken.add(course);
        ArrayList<String> prereqs = adjList.get(course);
        for(String prereq: prereqs) {
            findCoursesTaken(prereq, adjList, coursesTaken);
        }
    }

    private static void findAllPrereqs(String course, HashMap<String, ArrayList<String>> adjList, HashSet<String> allPrereqs){
        allPrereqs.add(course);
        ArrayList<String> prereqs = adjList.get(course);
        for(String prereq: prereqs) {
            findAllPrereqs(prereq, adjList, allPrereqs);
        }
    }

    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	    // Step 1
        String adjListInputFile = args[0];
        StdIn.setFile(adjListInputFile);

        // Step 1.1
        int a = StdIn.readInt();

        // Step 1.2
        HashMap<String, ArrayList<String>> adjList = new HashMap<>();
        for(int i = 0; i < a; i++){
            adjList.put(StdIn.readString(), new ArrayList<>());
        }

        // Step 1.3
        int b = StdIn.readInt();

        // Step 1.4;
        for(int i = 0; i < b; i++){
            ArrayList<String> prereqs =adjList.get(StdIn.readString());
            prereqs.add(StdIn.readString());
        }

        // Step 2
        String needToTakeInput = args[1];
        StdIn.setFile(needToTakeInput);

        // Step 2.1
        String targetCourse = StdIn.readString();

        // Step 2.2
        int c = StdIn.readInt();

        // Step 2.3
        HashSet<String> coursesTaken = new HashSet<>();
        for (int i = 0; i < c; i++) {
            String course = StdIn.readString();
            findCoursesTaken(course, adjList, coursesTaken);
        }

        // Step 3
        String outputFile = args[2];
        StdOut.setFile(outputFile);

        // Step 3.1
        ArrayList<String> directPrereqs = adjList.get(targetCourse);
        HashSet<String> allPrereqs = new HashSet<>();
        for(String directPrereq: directPrereqs){
            findAllPrereqs(directPrereq, adjList, allPrereqs);
        }
        for(String prereq: allPrereqs){
            if(!coursesTaken.contains(prereq)){
                StdOut.println(prereq);
            }
        }
        
    }
}
