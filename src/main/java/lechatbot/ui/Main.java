package lechatbot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lechatbot.LeChatBot;
import lechatbot.LeChatBotException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private LeChatBot leChatBot = new LeChatBot("data/LeChatBot.txt");

    @Override
    public void start(Stage stage) throws LeChatBotException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(leChatBot);
            stage.show();
        } catch (IOException e) {
            throw new LeChatBotException("Failed to load MainWindow.fxml.");
        }
    }
}
