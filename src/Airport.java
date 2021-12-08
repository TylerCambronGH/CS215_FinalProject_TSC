import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author Tyler Cambron
 */
public class Airport {

    private Plane planeLanding;
    private PriorityQueue<Plane> readyToLand;
    private LinkedList<Plane> approaching;
    private int planesLanded;
    private int timeRan; // hours

    /**
     * Constructor
     */
    public Airport() {
        readyToLand = new PriorityQueue<Plane>();
        approaching = new LinkedList<Plane>();
        planesLanded = 0;
        timeRan = 0;
    }

    /**
     * Add a plane to the approaching queue at the back based on distance.
     * @param plane
     */
    public void planeApproaching(Plane plane) {
        approaching.add(plane);
    }

    /**
     * Moves plane from approaching to readyToLand.
     * @param index number of plane in linked list.
     */
    public void promoteApproaching(int index) {
        Plane ready = approaching.get(index);
        if (ready == null) { return; }
        approaching.remove(index);
        readyToLand.offer(ready);
    }

    /**
     * @return amount of planes landed.
     */
    public int getNumPlanesLanded() {
        return this.planesLanded;
    }

    /**
     * Airport runway (in loop)
     */
    public void run() {
        Random r = new Random();
        for (int i=0; i < approaching.size(); i++) {
            Plane plane = approaching.get(i);
            plane.distanceFromAirport -= (600 - r.nextInt(200)); // Move the plane 400-600 miles in the hour
            if (plane.distanceFromAirport < 1) { plane.distanceFromAirport = 0; }
            System.out.println("Moving airplane " + plane.getUid() + " closer. (" + plane.distanceFromAirport + " m)");
            if (plane.distanceFromAirport < 1) {
                this.promoteApproaching(i);
            }
        }

        for (Plane plane: readyToLand) {
            System.out.println("Ready to land plane " + plane.getUid() + " (" + plane.getPriortyLevel() + ")");
        }

        Plane readyPlane = readyToLand.peek();
        if (planeLanding != null) {
            System.out.println("Landed plane " + planeLanding.getUid() + " with queue priority " + planeLanding.getPriortyLevel());
            planeLanding = null;
            planesLanded++;
        } else if (readyPlane != null) {
            planeLanding = readyToLand.poll();
            System.out.println("Started to land plane " + planeLanding.getUid() + " with queue priority " + planeLanding.getPriortyLevel() + " (1h)");
        }
        timeRan++;
        System.out.println("("+ timeRan +"h)");
    }
}