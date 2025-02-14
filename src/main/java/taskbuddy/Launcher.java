package taskbuddy;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main entry point for launching the TaskBuddy application.
     * This method starts the JavaFX application by invoking the launch method on the Main class.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
