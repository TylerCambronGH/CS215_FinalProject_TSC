public class Plane implements Comparable<Plane> {
    private int uid; // Unique ID
    private int priorityLevel; // Higher the better.
    
    public int distanceFromAirport;

    /**
     * Constructor
     * @param uid unique id.
     * @param priorityLevel priority level, higher the better.
     */
    public Plane(int uid, int priorityLevel, int distanceFromAirport) {
        setUid(uid);
        this.priorityLevel = priorityLevel;
        this.distanceFromAirport = distanceFromAirport;
    }

    /**
     * Set the plane's unique id.
     * @param uid unique id.
     */
    private void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * @return unique id.
     */
    public int getUid() {
        return uid;
    }

    /**
     * @return priority level.
     */
    public int getPriortyLevel() {
        return priorityLevel;
    }

    /**
     * Return negative if this plane is greater than the comparison. Positive if smaller. 0 if same.
     */
    @Override
    public int compareTo(Plane plane) {
        return plane.priorityLevel - this.priorityLevel;
    }
}
