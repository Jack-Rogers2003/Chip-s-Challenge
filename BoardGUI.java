import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BoardGUI {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 500;
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 400;
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

    private static final int GRID_WIDTH = 12;
    private Canvas canvas;
    private static final Image DIRT_IMAGE = new Image("file:dirt.png");
    private Integer tickCount = 0;
    Text textText = new Text();


    public Scene generateBoard() {
        Pane root = buildGUI();
        textText = new Text("Current Time: " + tickCount);
        textText.setStyle("-fx-font: 24 arial;");
        textText.setTranslateY(50);
        textText.setTranslateX(300);
        root.getChildren().add(textText);
        Timeline tickTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> tick()));
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        tickTimeline.play();
        drawGame();
        return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void tick(){
        tickCount++;
        textText.setText("Current Time: " + tickCount);

    }
    public void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set the background to gray.
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw row of dirt images
        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
        // We draw the row at y value 2.
        for (int x = 0; x < GRID_WIDTH; x++) {
            gc.drawImage(DIRT_IMAGE, x * GRID_CELL_WIDTH, 2 * GRID_CELL_HEIGHT);
        }
    }

    private Pane buildGUI() {
        BorderPane root = new BorderPane();
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
        return root;
    }


}
