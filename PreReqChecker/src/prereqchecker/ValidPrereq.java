package prereqchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    
    private static void findAllPrereqs(String course, HashMap<String, ArrayList<String>> adjList, HashSet<String> allPrereqs){
        allPrereqs.add(course);
        ArrayList<String> prereqs = adjList.get(course);
        for(String prereq: prereqs) {
            findAllPrereqs(prereq, adjList, allPrereqs);
        }
    }

    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
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
            ArrayList<String> prereqs = adjList.get(StdIn.readString());
            prereqs.add(StdIn.readString());
        }
        
        // Step 2
        String validPreReqInputFile = args[1];
        StdIn.setFile(validPreReqInputFile);

        // Step 2.1
        String advancedCourse = StdIn.readString();

        // Step 2.2
        String prereq = StdIn.readString();

        // Step 3
        String outputFile = args[2];
        StdOut.setFile(outputFile);

        HashSet<String> allPrereqs = new HashSet<>();
        findAllPrereqs(prereq, adjList, allPrereqs);

        // Step 3.1
        if(!allPrereqs.contains(advancedCourse)) {
            StdOut.print("YES");
        } else {
            StdOut.print("NO");
        }
    }
}
