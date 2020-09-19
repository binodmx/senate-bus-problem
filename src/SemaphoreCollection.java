import java.util.concurrent.Semaphore;

/**
 * Class for storing semaphores
 */
public class SemaphoreCollection {
    // Semaphore used for riders to come the waiting area, allowing 50 riders to the waiting area
    private static final Semaphore waitingAreaSemaphore = new Semaphore(50);

    // Semaphore used for riders to enter the waiting area
    private static final Semaphore riderEnterWaitingAreaSemaphore = new Semaphore(1);

    // Semaphore used for riders to board the bus
    private static final Semaphore riderBoardBusSemaphore = new Semaphore(0);

    // Semaphore used for bus to arrive
    private static final Semaphore busArrivalSemaphore = new Semaphore(1);

    // Semaphore used for bus to depart after the riders are boarded
    private static final Semaphore busDepartureSemaphore = new Semaphore(0);

    // Method to get the waitingAreaSemaphore
    public static Semaphore getWaitingAreaSemaphore() {
        return waitingAreaSemaphore;
    }

    // Method to get the riderEnterWaitingAreaSemaphore
    public static Semaphore getRiderEnterWaitingAreaSemaphore() {
        return riderEnterWaitingAreaSemaphore;
    }

    // Method to get the riderBoardBusSemaphore
    public static Semaphore getRiderBoardBusSemaphore() {
        return riderBoardBusSemaphore;
    }

    // Method to get the busArrivalSemaphore
    public static Semaphore getBusArrivalSemaphore() {
        return busArrivalSemaphore;
    }

    // Method to get the busDepartureSemaphore
    public static Semaphore getBusDepartureSemaphore() {
        return busDepartureSemaphore;
    }
}
