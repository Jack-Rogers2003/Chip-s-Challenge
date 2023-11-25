import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.FileNotFoundException;


/**
 * Class that handles creating the gui for the initial menu when the
 * game is started
 * @author Jack Rogers
 * @version 1.0 - Menu and buttons created, no functionality as waiting for
 * Game class to be completed
 */
public class GameStart extends Application implements EventHandler<ActionEvent> {
    //The text for the New game button
    private static final String NEW_GAME_BUTTON_TEXT = "New Game";
    //Text for the load game button
    private static final String LOAD_GAME_BUTTON_TEXT = "Load Game";
    //Text for the header above the buttons
    private static final String MENU_HEADER_TEXT = "Choose an option";
    //Two variables below are test text to ensure even handling it working
    private static final String NEW_GAME_BUTTON_PRESSED
            = "You have pressed new game";
    private static final String LOAD_GAME_BUTTON_PRESSED =
            "You have pressed load game";
    //These are the buttons that will be used
    private static final Button NEW_GAME_BUTTON =
            new Button(NEW_GAME_BUTTON_TEXT);
    private static final Button LOAD_GAME_BUTTON =
            new Button(LOAD_GAME_BUTTON_TEXT);

    //These are the texts displayed
    private static final Text MENU_HEADER = new Text(MENU_HEADER_TEXT);
    private static final Text BUTTON_PRESSED = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Class to generate the window the menu will be displayed within
     * @param menuWindow the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage menuWindow) {
        menuWindow.setTitle("Menu");
        MENU_HEADER.setStyle("-fx-font: 24 arial;");
        MENU_HEADER.setTranslateY(-50);
        BUTTON_PRESSED.setStyle("-fx-font: 20 arial;");
        BUTTON_PRESSED.setTranslateY(50);

        NEW_GAME_BUTTON.setTranslateX(50);
        LOAD_GAME_BUTTON.setTranslateX(-40);
        NEW_GAME_BUTTON.setOnAction(this);
        LOAD_GAME_BUTTON.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(NEW_GAME_BUTTON);
        layout.getChildren().add(LOAD_GAME_BUTTON);
        layout.getChildren().add(MENU_HEADER);
        layout.getChildren().add(BUTTON_PRESSED);

        Scene menuScene = new Scene(layout, 300, 250);
        menuWindow.setScene(menuScene);
        menuWindow.show();
    }

    /**
     * Handles what happens when buttons are pressed
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == NEW_GAME_BUTTON) {
            BUTTON_PRESSED.setText(NEW_GAME_BUTTON_PRESSED);
        } else {
            BUTTON_PRESSED.setText(LOAD_GAME_BUTTON_PRESSED);
        }
    }
}
