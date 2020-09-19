/**
 * Class for the waiting area to keep track of the riders
 */
public class WaitingArea {
    private static int ridersCount = 0;

    // Method to get the ridersCount
    public static int getRidersCount() {
        return ridersCount;
    }

    // Method to increment the ridersCount
    public static void incrementRidersCount() {
        ridersCount++;
    }

    // Method to decrement the ridersCount
    public static void decrementRidersCount() {
        ridersCount--;
    }
}