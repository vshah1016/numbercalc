package runtime;

import java.util.Locale;
import java.util.Scanner;

public class Client {
    final Scanner scanner = new Scanner(System.in);
    private String runAgain = "y";

    public boolean isRunAgain() {
        return (runAgain.equalsIgnoreCase("y"));
    }

    public void runAgain() {
        System.out.println();
        System.out.print("Would you like to go again? (y/n): ");
        runAgain = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        System.out.println();
    }

}
