import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardGUI extends Application {

    private static final int WINDOW_HEIGHT = 800;
    private static final int WINDOW_WIDTH = 500;

    private static int boardHeight;
    private static int boardWidth;
    private Image playerImage;
    private Image frogImage;
    private Image pinkBallImage;
    private Image bugImage;
    private Image dirtImage;
    private Image pathImage;
    private Image iceImage;
    private Image waterImage;

    public void start(Stage currentLevel) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
