package application;
import controller.HealthRecordController;
import controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class MyHealthTracker extends Application {
    private UserController userController;
    private HealthRecordController healthRecordController;
    private Database database;
    private User currentUser;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO: implement the GUI and connect it to the controllers and database
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
