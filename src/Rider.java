import java.util.concurrent.Semaphore;

/**
 * Class for the rider threads
 */
class Rider implements Runnable {
    private final int index;
    private final Semaphore waitingAreaSemaphore;
    private final Semaphore riderEnterWaitingAreaSemaphore;
    private final Semaphore riderBoardBusSemaphore;
    private final Semaphore busDepartureSemaphore;

    public Rider(int index, Semaphore waitingAreaSemaphore, Semaphore riderEnterWaitingAreaSemaphore,
                 Semaphore riderBoardBusSemaphore, Semaphore busDepartureSemaphore) {
        this.index = index;
        this.waitingAreaSemaphore = waitingAreaSemaphore;
        this.riderEnterWaitingAreaSemaphore = riderEnterWaitingAreaSemaphore;
        this.riderBoardBusSemaphore = riderBoardBusSemaphore;
        this.busDepartureSemaphore = busDepartureSemaphore;
    }

    @Override
    public void run() {
        try {
            // Acquiring the semaphore in trying to enter the rider waiting area
            waitingAreaSemaphore.acquire();

            // Locking waiting area until the rider enters the waiting area
            riderEnterWaitingAreaSemaphore.acquire();

            // Enter waiting area and increment the ridersCount
            enterWaitingArea();
            WaitingArea.incrementRidersCount();

            // Unlocking waiting area allowing other riders to enter the waiting area
            riderEnterWaitingAreaSemaphore.release();

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