import java.util.Random;

/**
 * Class to spawn the threads for the bus arrival
 */
public class BusGenerator implements Runnable {
    private float interArrivalMeanTime;
    private static Random random;

    public BusGenerator(float interArrivalMeanTime) {
        this.interArrivalMeanTime = interArrivalMeanTime;
        random = new Random();
    }

    @Override
    public void run() {
        int busIndex = 1;

        // Spawning bus threads for the user specified value
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Initializing and starting the bus threads
                Bus bus = new Bus(
                        busIndex,
                        SemaphoreCollection.getBusArrivalSemaphore(),
                        SemaphoreCollection.getBusDepartureSemaphore(),
                        SemaphoreCollection.getRiderBoardBusSemaphore(),
                        SemaphoreCollection.getRiderEnterWaitingAreaSemaphore()
                );
                (new Thread(bus)).start();

                // Incrementing busIndex
                busIndex++;

                // Sleeping the thread to obtain the inter arrival time between the bus threads
                Thread.sleep(getExponentiallyDistributedInterArrivalTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get the exponentially distributed bus inter arrival time
    public long getExponentiallyDistributedInterArrivalTime() {
        float lambda = 1 / interArrivalMeanTime;
        return Math.round(Math.log(1 - random.nextFloat()) / (-lambda));
    }
}