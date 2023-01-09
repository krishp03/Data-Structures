package prereqchecker;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
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
        String adjListOutputFile = args[1];
        StdOut.setFile(adjListOutputFile);
        Set<String> courses = adjList.keySet();
        for(String course: courses){
            // prints course name
            StdOut.print(course+" ");

            // prints all prereqs
            ArrayList<String> prereqs =adjList.get(course);
            for(String prereq: prereqs){
                StdOut.print(prereq+" ");
            }
            StdOut.println();
        }

    }
}
