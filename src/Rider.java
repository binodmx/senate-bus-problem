import java.util.concurrent.Semaphore;

/**
 * Class for the rider threads
 */
class Rider implements Runnable {
    private final int index;
    private final Semaphore waitingAreaSemaphore;
    private final Semaphore riderBoardBusSemaphore;
    private final Semaphore busArrivalSemaphore;
    private final Semaphore busDepartureSemaphore;

    public Rider(int index, Semaphore waitingAreaSemaphore, Semaphore riderBoardBusSemaphore,
                 Semaphore busArrivalSemaphore, Semaphore busDepartureSemaphore) {
        this.index = index;
        this.waitingAreaSemaphore = waitingAreaSemaphore;
        this.riderBoardBusSemaphore = riderBoardBusSemaphore;
        this.busArrivalSemaphore = busArrivalSemaphore;
        this.busDepartureSemaphore = busDepartureSemaphore;
    }

    @Override
    public void run() {
        try {
            // Acquiring the semaphore in trying to enter the rider waiting area
            waitingAreaSemaphore.acquire();

            // Acquiring busArrivalSemaphore allowing rider to enter the waiting area before a bus comes
            busArrivalSemaphore.acquire();

            // Enter waiting area and increment the ridersCount
            enterWaitingArea();
            WaitingArea.incrementRidersCount();

            // Releasing busArrivalSemaphore allowing a bus to arrive
            busArrivalSemaphore.release();

            // Acquiring the semaphore to board the bus
            riderBoardBusSemaphore.acquire();

            // Board the bus
            boardBus();

            // Decrementing the ridersCount once boarded
            WaitingArea.decrementRidersCount();

            if (WaitingArea.getRidersCount() == 0) {
                // When all the riders are boarded, allowing the bus to depart by releasing the bus departure semaphore
                busDepartureSemaphore.release();
            } else {
                // If there are more riders waiting, allowing them to get into the bus
                riderBoardBusSemaphore.release();
            }

            // Releasing the semaphore for enter waiting area
            waitingAreaSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void boardBus() {
        System.out.println("Rider: " + index + " boarded bus");
    }

    public void enterWaitingArea() {
        System.out.println("Rider: " + index + " entered waiting area");
    }
}