import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered
 * linked list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	/**
	 * Makes a layered linked list representing the given arrays of train stations,
	 * bus stops, and walking locations. Each layer begins with a location of 0,
	 * even though the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops      Int array listing all the bus stops
	 * @param locations     Int array listing all the walking locations (always
	 *                      increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
		TNode trainZero = new TNode(0);
		TNode busZero = new TNode(0);
		TNode walkZero = new TNode(0);

		// Current node while building list
		TNode curr = trainZero;

		// Creates train station list
		for (int trainStation : trainStations) {
			curr.next = new TNode(trainStation);
			curr = curr.next;
		}

		// Creates bus stop list and connects adjacent train stations
		trainZero.down = busZero;
		curr = busZero;
		TNode currStation = trainZero.next;
		for (int busStop : busStops) {
			curr.next = new TNode(busStop);
			curr = curr.next;

			if (currStation != null && currStation.location == busStop) {
				currStation.down = curr;
				currStation = currStation.next;
			}
		}

		// Creates walking list and connects adjacent bus stops
		busZero.down = walkZero;
		curr = walkZero;
		TNode currStop = busZero.next;
		for (int location : locations) {
			curr.next = new TNode(location);
			curr = curr.next;

			if (currStop != null && currStop.location == location) {
				currStop.down = curr;
				currStop = currStop.next;
			}
		}

		return trainZero;
	}

	/**
	 * Modifies the given layered list to remove the given train station but NOT its
	 * associated bus stop or walking location. Do nothing if the train station
	 * doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station   The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) {
		TNode curr = trainZero;
		TNode last = trainZero;
		while (curr != null) {
			if (curr.location == station) {
				last.next = curr.next;
				break;
			}
			last = curr;
			curr = curr.next;
		}
	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified
	 * location. Do nothing if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop   The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) {
		// Sets up walking stop variable to see if theres a corresponding stop to
		// busStop
		TNode walkCurr = trainZero.down.down;
		walkCurr = (walkCurr.down != null) ? walkCurr.down : walkCurr;
		walkCurr = destination(walkCurr, busStop);
		if (walkCurr == null) {
			return;
		}

		// Adds stop
		TNode busCurr = trainZero.down;
		TNode busLast = null;
		while (busCurr != null && busCurr.location < busStop) {
			busLast = busCurr;
			busCurr = busCurr.next;
		}
		// checks if stop already exists; does not add stop if it does
		if (busCurr != null && busCurr.location == busStop) {
			return;
		}
		TNode newStop = new TNode(busStop, busCurr, walkCurr);
		busLast.next = newStop;

		// checks for cooresponding train station and connects it
		TNode trainCurr = trainZero;
		while (trainCurr != null && trainCurr.location < busStop) {
			trainCurr = trainCurr.next;
		}
		if (trainCurr != null && trainCurr.location == busStop) {
			trainCurr.down = newStop;
		}
	}

	/**
	 * Determines the optimal path to get to a given destination in the walking
	 * layer, and collects all the nodes which are visited in this path into an
	 * arraylist.
	 * 
	 * @param trainZero   The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */
	public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {

		TNode walkCurr = trainZero.down.down;
		walkCurr = (walkCurr.down != null) ? walkCurr.down : walkCurr;
		if (destination(walkCurr, destination) == null) {
			throw new IllegalArgumentException("destination doesnt exist");
		}

	

		ArrayList<TNode> bestPath = new ArrayList<>();
		TNode currNode = trainZero;
		TNode lastNode = null;
		while (currNode != null) {
			while (currNode != null && currNode.location < destination) {
				bestPath.add(currNode);
				lastNode = currNode;
				currNode = currNode.next;
			}
			if (currNode != null && currNode.location == destination) {
				bestPath.add(currNode);
				break;
			}
			currNode = lastNode.down;
		}
		while(currNode.down != null){
			bestPath.add(currNode.down);
			currNode = currNode.down;
		}
		return bestPath;
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the
	 * same locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {
		TNode curr = trainZero.next;
		TNode newTrainZero = new TNode();
		TNode newTrainCurr = newTrainZero;
		while(curr != null){
			newTrainCurr.next = new TNode(curr.location);
			newTrainCurr = newTrainCurr.next;
			curr = curr.next;
		}

		TNode currLevel = trainZero.down;
		TNode newLastLevelZero = newTrainZero;
		TNode newLastLevelCurr = newLastLevelZero.next;
		while (currLevel != null){
			TNode newLevel = new TNode();
			newLastLevelZero.down = newLevel;
			curr = currLevel.next;
			while(curr != null){
				newLevel.next = new TNode(curr.location);
				newLevel = newLevel.next;
				curr = curr.next;
				if(newLastLevelCurr != null && newLastLevelCurr.location == newLevel.location){
					newLastLevelCurr.down = newLevel;
					newLastLevelCurr = newLastLevelCurr.next;
				}
			}
			currLevel = currLevel.down;
			newLastLevelZero = newLastLevelZero.down;
			newLastLevelCurr = newLastLevelZero.next;
		}
		return newTrainZero;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero    The zero node in the train layer of the given layered
	 *                     list
	 * @param scooterStops An int array representing where the scooter stops are
	 *                     located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {
		TNode scooterZero = new TNode(0, null, trainZero.down.down);
		trainZero.down.down = scooterZero;

		TNode busCurr = trainZero.down.next;
		TNode scooterCurr = scooterZero;
		TNode walkCurr = scooterZero.down.next;
		int i = 0;
		while (i < scooterStops.length) {
			if (scooterStops[i] == walkCurr.location) {
				scooterCurr.next = new TNode(scooterStops[i], null, walkCurr);
				scooterCurr = scooterCurr.next;
				if(busCurr != null && busCurr.location == scooterStops[i]){
					busCurr.down = scooterCurr;
					busCurr = busCurr.next;
				}
				i++;
			}
			walkCurr = walkCurr.next;
		}

	}

	private static TNode destination(TNode walkCurr, int destination) {
		// Checks if busStop exists; if it doesnt, do not create stop
		while (walkCurr != null) {
			if (walkCurr.location == destination) {
				break;
			}
			walkCurr = walkCurr.next;
		}
		return walkCurr;
	}
}