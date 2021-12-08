import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author tylercambron
 */
public class Application {
    public static int planeId = 0;
    public static int MAX_PLANES = 10;
    public static boolean running = true;
    public static void main(String[] args) {
        Airport airport = new Airport();
        Timer timer = new Timer();
        Random random = new Random();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("---");
                // 1/2 chance to bring plane into distance
                if (random.nextInt(2) == 0) {
                    // random priority 0-2
                    System.out.println("New plane " + planeId + " added to runway.");
                    Plane newPlane = new Plane(planeId, random.nextInt(3), 5000);
                    airport.planeApproaching(newPlane);
                    planeId++;
                }
                airport.run();
                if (airport.getNumPlanesLanded() >= MAX_PLANES) {
                    timer.cancel();
                    running = false;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 2000);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'STOP' to stop the runway before " + MAX_PLANES + " planes landed.");
        while (running) {
            String nextLine = scanner.nextLine();
            if (nextLine.compareToIgnoreCase("stop") == 0)  {
                timer.cancel();
                running = false;
            }
        }
        scanner.close();
    }
}
