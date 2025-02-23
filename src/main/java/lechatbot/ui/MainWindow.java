package lechatbot.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lechatbot.LeChatBot;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private LeChatBot leChatBot;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/bronny.jpeg"))
    );

    private final Image leChatBotImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/lebron.jpeg"))
    );

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the LeChatBot instance */
    public void setLeChatBot(LeChatBot d) {
        leChatBot = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing LeChatBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = leChatBot.getResponse(input);
        String commandType = leChatBot.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLeChatBotDialog(response, leChatBotImage, commandType)
        );
        userInput.clear();
    }
}
