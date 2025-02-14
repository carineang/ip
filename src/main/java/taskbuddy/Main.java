package taskbuddy;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A JavaFX GUI application for TaskBuddy, which is a task management tool.
 */
public class Main extends Application {

    private TaskBuddy taskbuddybot = new TaskBuddy("data/taskbuddy.txt");

    /**
     * Initializes and displays the main window of the TaskBuddy application.
     *
     * @param stage The primary stage for this application, onto which the scene will be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskBuddy(taskbuddybot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}