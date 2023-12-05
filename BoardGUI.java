import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardGUI extends Application implements EventHandler<ActionEvent> {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 650;
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 400;
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;
    private static final Button EXIT_BUTTON = new Button("Exit");
    private static final int TIMER = 100;
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String ASSETS_PATH = "file:" + CURRENT_DIRECTORY + "\\src\\main\\java\\assets\\";
    private static final String TEXT_STYLE = "-fx-font: 24 arial;";
    private static int gridWidth;
    private static int gridHeight;
    private Canvas board;
    private int playerX = 0;
    private int playerY = 0;
    private int tickCount = 0;
    private Text timerText = new Text();
    private Timeline tickTimeline;
    private Board gameBoard;
    private static String nextPlayerMove = "";

    @Override
    public void start(Stage primaryStage) {

    }

    /**
     * Sets the size of the board
     * @param sizes size of the board, the 0 element is the width, and the 1
     *              element is the height
     */
    public static void setBoardSize(int[] sizes) {
        gridWidth = sizes[0];
        gridHeight = sizes[1];
    }

    /**
     * Returns the size of the board
     * @return an array of ints with the 0th element being the width of the
     * board and the 1st element being the height
     */
    public static int[] getBoardSize() {
        return new int[] {gridWidth, gridHeight};
    }

    public Scene generateBoard() {
        Pane root = buildGUI();
        gameBoard = new Board(GameGUIManager.getCurrentLevel());
        int[] playerPositions = PositionManager.getPlayerPosition();
        playerX = playerPositions[0];
        playerY = playerPositions[1];
        timerText = new Text("Current Time: " + TIMER);
        timerText.setStyle(TEXT_STYLE);
        timerText.setTranslateY(50);
        timerText.setTranslateX(200);
        root.getChildren().add(timerText);
        tickTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event ->
                tick()));
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        tickTimeline.play();
        drawGame();
        Scene window = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.addEventFilter(KeyEvent.KEY_PRESSED, this::setForKeyEvent);
        return window;
    }

    /**
     * Counts up the tick counter and adjusts the on-screen timer accordingly
     * When the tick counter and the Timer are the same, it calls for the
     * game to be reset
     */
    public void tick(){
        tickCount++;
        timerText.setText("Current Time: " + (TIMER - tickCount));
        if (TIMER == tickCount) {
            tickTimeline.stop();
            endGamePopup();
        }
    }

    /**
     * Popup when the game has ended, when clicked OK, it resets the game
     */
    public void endGamePopup() {
        Alert timeHasRunOut = new Alert(Alert.AlertType.INFORMATION);
        timeHasRunOut.setHeaderText("You've lost, try again");
        tickTimeline.stop();
        timeHasRunOut.setOnHidden(event -> restartGame());
        timeHasRunOut.show();
    }

    /**
     * Calls for the game to be restarted
     */
    public void restartGame() {
        GameGUIManager.isBoardWindowNext = true;
        GameGUIManager.windowChange();
    }

    public void drawGame() {
        GraphicsContext gc = board.getGraphicsContext2D();
        gc.clearRect(0, 0, board.getWidth(), board.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, board.getWidth(), board.getHeight());
        for (int y = 0; y < gridHeight; y++) {
            for(int x = 0; x < gridWidth; x++){
                int[] position = new int[] {x,y};
                gc.drawImage(new Image(ASSETS_PATH + PositionManager.
                                getTileAt(position).getImageFile()), x *
                                GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                drawOtherElements(position, gc);
            }
        }
        setPlayerPosition();
        gc.drawImage(new Image(ASSETS_PATH + Player.getImageFile()),
                playerX * GRID_CELL_WIDTH, playerY * GRID_CELL_HEIGHT);
    }

    public void drawOtherElements(int[] position, GraphicsContext gc) {
        ArrayList<Monster> listOfMonsters = gameBoard.getActors().
                getListOfMonsters();
        for(int i = 0; i < gameBoard.getActors().getListOfMonsters().
                size(); i++) {
            if(PositionManager.getMonsterPosition(listOfMonsters.get(i))[0] ==
                    position[0] && PositionManager.getMonsterPosition(
                            listOfMonsters.get(i))[1] == position[1]) {
                gc.drawImage(new Image(ASSETS_PATH + listOfMonsters.get(i).
                                getImageFile()), position[0] * GRID_CELL_WIDTH,
                        position[1] * GRID_CELL_HEIGHT);
            }
        }
        HashMap<Item, int[]> itemList = PositionManager.getListOfItems();
        for(Entry<Item, int[]> entry: itemList.entrySet()) {
            int[] itemPosition = entry.getValue();
            if(itemPosition[0] == position[0] &&
                    itemPosition[1] == position[1]) {
                gc.drawImage(new Image(ASSETS_PATH +
                                entry.getKey().getImageFile()), position[0] *
                        GRID_CELL_WIDTH, position[1] * GRID_CELL_HEIGHT);
            }
        }
        HashMap<Block, int[]> blockList = PositionManager.getBlockPosition();
        for(Entry<Block, int[]> entry: blockList.entrySet()) {
            int[] blockPosition = entry.getValue();
            if (blockPosition[0] == position[0] &&
                    blockPosition[1] == position[1]) {
                gc.drawImage(new Image(ASSETS_PATH + entry.getKey().
                        getImageFile()), position[0] * GRID_CELL_WIDTH,
                        position[1] * GRID_CELL_HEIGHT);
            }
        }
    }

    /**
     * Checks if the next movement will take the player out of bounds and
     * adjusts it if so
     * This may be removed once Movement is fully implemented
     */
    public void setPlayerPosition() {
        if (playerY < 0) {
            playerY = 0;
        } else if (playerY > gridHeight - 1) {
            playerY = gridHeight - 1;
        } else if (playerX < 0) {
            playerX = 0;
        } else if (playerX > gridWidth - 1) {
            playerX = gridWidth - 1;
        }
        PositionManager.setPlayerPosition(new int[] {playerX, playerY});
    }

    /**
     * Builds out the window and it's related properties
     * @return A borderpane that is the window and all it's properties
     */
    private Pane buildGUI() {
        BorderPane root = new BorderPane();
        board = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(board);
        root.setTop(EXIT_BUTTON);
        EXIT_BUTTON.setOnAction(this);
        return root;
    }

    /**
     * Performs the player movement
     * Will be adjusted once the Movement class is more made
     * @param event Key that has been pressed
     */
    public void setForKeyEvent(KeyEvent event) {

        switch (event.getCode()) {
            case UP, W -> playerY = playerY - 1;
            case LEFT, A -> playerX = playerX - 1;
            case DOWN, S -> playerY = playerY + 1;
            case RIGHT, D -> playerX = playerX + 1;
        }
        drawGame();
    }

    /**
     * Handles all the button presses and corresponding actions for the window
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        Stage window = ((Stage) EXIT_BUTTON.getScene().getWindow());
        if(event.getSource() == EXIT_BUTTON) {
            tickTimeline.stop();
            GameGUIManager.isLevelSelectorWindowNext = true;
            window.close();
            GameGUIManager.windowChange();
        }
    }
}
