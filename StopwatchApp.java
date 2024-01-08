import java.util.Scanner;

public class StopwatchApp {
    private static long startTime = 0;
    private static long pausedTime = 0;
    private static boolean isRunning = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    startTimer();
                    break;
                case 2:
                    pauseTimer();
                    break;
                case 3:
                    resetTimer();
                    break;
                case 0:
                    System.out.println("Exiting the stopwatch. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== Stopwatch Menu =====");
        System.out.println("1. Start");
        System.out.println("2. Pause");
        System.out.println("3. Reset");
        System.out.println("0. Exit");
    }

    private static void startTimer() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
            System.out.println("Stopwatch started.");
            while (isRunning) {
                displayElapsedTime();
                try {
                    Thread.sleep(100); // Sleep for 100 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Stopwatch is already running.");
        }
    }

    private static void pauseTimer() {
        if (isRunning) {
            pausedTime += (System.currentTimeMillis() - startTime);
            isRunning = false;
            System.out.println("Stopwatch paused.");
        } else {
            System.out.println("Stopwatch is not running.");
        }
    }

    private static void resetTimer() {
        isRunning = false;
        startTime = 0;
        pausedTime = 0;
        System.out.println("Stopwatch reset.");
    }

    private static void displayElapsedTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime + pausedTime;

        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long seconds = (elapsedTime / 1000) % 60;
        long milliseconds = elapsedTime % 1000;

        System.out.printf("Time: %02d:%02d:%03d%n", minutes, seconds, milliseconds);
    }
}
