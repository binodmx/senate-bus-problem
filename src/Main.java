import java.util.Scanner;

/**
 * Main Class for the Senate Bus Problem
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        float riderInterArrivalMeanTime = 30f * 1000;
        float busInterArrivalMeanTime = 20 * 60f * 1000;

        String userInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n*******  Press any key to exit  *******\n" );

        RiderGenerator riderGenerator = new RiderGenerator(riderInterArrivalMeanTime);
        (new Thread(riderGenerator)).start();

        BusGenerator busGenerator = new BusGenerator(busInterArrivalMeanTime);
        (new Thread(busGenerator)).start();

        while (true) {
            userInput = scanner.nextLine();
            if (userInput != null) {
                System.exit(0);
            }
        }
    }
}