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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

/**
 * This class handles all the GUI elements needed for the board, as well as
 * tracking the game's ticks and calling movement when needed
 * @author Jack Rogers
 * @version 1.0
 */
public class BoardGUI extends Application implements
        EventHandler<ActionEvent> {
    //Height of the window
    private static final int WINDOW_HEIGHT = 600;
    //Width of the window
    private static final int WINDOW_WIDTH = 650;
    //Width of the canvas that will contain the board
    private static final int CANVAS_WIDTH = 600;
    //Height of the canvas that will contain the board
    private static final int CANVAS_HEIGHT = 400;
    //Width of each cell of the Board
    private static final int GRID_CELL_WIDTH = 50;
    //Height of each cell of the board
    private static final int GRID_CELL_HEIGHT = 50;
    private static final int LEADERBOARD_MAX_SIZE = 10;
    private static final int LEADERBOARD_LAST_ELEMENT =
            LEADERBOARD_MAX_SIZE - 1;
    //The exit button of the Window
    private static final Button EXIT_BUTTON = new Button("Exit");
    //The save game button of the window
    private static final Button SAVE_GAME_BUTTON = new Button("Save");
    //x translation of the Save button
    private static final int SAVE_GAME_BUTTON_X_TRANSLATE = 20;
    //The current directory we're operating in
    private static final String CURRENT_DIRECTORY =
            System.getProperty("user.dir");
    //File path to the directory containing the game's assets
    private static final String ASSETS_PATH = "file:" + CURRENT_DIRECTORY +
            "\\assets\\";
    //File path to the directory path containing the leaderboards of the game
    private static final String LEADERBOARDS_PATH = CURRENT_DIRECTORY +
            "\\Leaderboards\\";
    private static final File SAVED_GAMES_PATH = new File(
            CURRENT_DIRECTORY + "\\saves\\");
    //The style the displayed text will be in
    private static final String TEXT_STYLE = "-fx-font: 24 arial;";
    private static final int TIME_TEXT_Y_TRANSLATE = 50;
    private static final int TIME_TEXT_X_TRANSLATE = 200;
    //How many columns the board will be made up of
    private static int gridWidth;
    //How many rows the game will be mad eup of
    private static int gridHeight;
    //Check for if the gaming is still going
    private static boolean gameHasEnded = false;
    //String that states how the game ended
    private static String endCause;
    //Starting tickCount for the game
    private static int tickCount = 0;
    //int of how long the game will last
    private static int timer;
    //Move the player will take on their next move
    private static String nextPlayerMove = "";
    //Board of the game within the window
    private static Canvas board;
    //Check for whether we load a game from the saved directory or not
    private static boolean shouldLoad = false;
    //Movement object to handle movement in the game
    private final Movement movement = new Movement();
    //Text to display the current time in the game
    private Text timerText = new Text();
    //Timeline to track game ticks and track animations
    private Timeline tickTimeline;


    /**
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {

    }

    /**
     * Handles all the button presses and corresponding actions for the window
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        Stage window = ((Stage) EXIT_BUTTON.getScene().getWindow());
        if (event.getSource() == EXIT_BUTTON) {
            tickTimeline.stop();
            tickCount = 0;
            GameGUIManager.setIsLevelSelectorWindowNext();
            window.close();
            GameGUIManager.windowChange();
        } else if (event.getSource() == SAVE_GAME_BUTTON) {
            tickTimeline.stop();
            saveGame();
            tickCount = 0;
            GameGUIManager.setIsLevelSelectorWindowNext();
            window.close();
            GameGUIManager.windowChange();
        }
    }

    /**
     * Gets the current time of the game by subtracting the timer of the game
     * by the current tickCount
     * @return int that is the currentTime of the game
     */
    private static int getCurrentTime() {
        return timer - tickCount;
    }

    /**
     * Sets shouldLoad to true if false, and false if true
     */
    public static void setShouldLoad() {
        shouldLoad = !shouldLoad;
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
     * Sets that the game has ended and also gets the end of the game cause
     * @param gameEnder String that states what caused the game to end
     */
    public static void setGameEnd(String gameEnder) {
        gameHasEnded = true;
        endCause = gameEnder;
    }

    /**
     * Resets gameHasEnded and endCause, this is as they're static and so need
     * to be reset as to not cause issues when going between levels
     */
    public static void resetGameEnd() {
        gameHasEnded = false;
        endCause = null;
    }

    /**
     * Sets what the starting time of the game will be
     * @param newGameTime int of the game's starting time
     */
    public static void setGameTime(int newGameTime) {
        timer = newGameTime;
    }

    /**
     * Returns the size of the board
     * @return an array of ints with the 0th element being the width of the
     * board and the 1st element being the height
     */
    public static int[] getBoardSize() {
        return new int[] {gridWidth, gridHeight};
    }

    /**
     * Generates the window that will be used and all properties inside it
     * @return Scene object that is the window that will display the game
     */
    public Scene generateBoard() {
        Pane root = buildGUI();
        if (!shouldLoad) {
            new Board(GameGUIManager.getCurrentLevel());
        } else {
            new Board("L");
            setShouldLoad();
        }
        timerText = new Text("Current Time: " + timer);
        timerText.setStyle(TEXT_STYLE);
        timerText.setTranslateY(TIME_TEXT_Y_TRANSLATE);
        timerText.setTranslateX(TIME_TEXT_X_TRANSLATE);
        root.getChildren().add(timerText);
        tickTimeline = new Timeline(new KeyFrame(Duration.seconds(1),
                event -> tick()));
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
    public void tick() {
        tickCount++;
        timerText.setText("Current Time: " + (timer - tickCount));
        if (timer == tickCount) {
            tickTimeline.stop();
            timeRanOut();
        }
        ArrayList<Monster> monsters = Board.getActors().getListOfMonsters();
        for (Monster monster : monsters) {
            if (tickCount % monster.getTick() == 0) {
                monster.getNextMovement();
            }
        }
        if (tickCount % Player.getTickCount() == 0) {
            nextPlayerMove();
        }
        deathCheck();
        if (gameHasEnded) {
            endGame();
        }
        drawGame();
    }

    /**
     * Checks if the player was killed by a monster, and if so calls for the
     * game to end as well as which monster kill it
     */
    public void deathCheck() {
        int[] position = PositionManager.getPlayerPosition();
        Actor boardActors = Board.getActors();
        ArrayList<Monster> listOfMonsters = boardActors.getListOfMonsters();
        for (Monster currentMonster : listOfMonsters) {
            int[] monsterPosition = PositionManager.getMonsterPosition(
                    currentMonster);
            if (monsterPosition[0] == position[0] &&
                    monsterPosition[1] == position[1]) {
                if (currentMonster instanceof Frog) {
                    BoardGUI.setGameEnd("Frog");
                } else if (currentMonster instanceof PinkBall) {
                    BoardGUI.setGameEnd("Pink Ball");
                } else if (currentMonster instanceof Bug) {
                    BoardGUI.setGameEnd("Bug");
                }
            }
        }
    }

    /**
     * Checks what ended the game and calls the respective pop up depending
     * on what caused the game to end
     */
    public void endGame() {
        switch (endCause) {
            case "Exit" -> gameWonPopup();
            case "Water" -> waterDeath();
            case "Frog", "Bug", "Pink Ball" -> monsterDeath(endCause);
            case "Block" -> blockDeath();
        }
    }

    /**
     * Pop up for when the player was killed by a block
     */
    public void blockDeath() {
        Alert timeHasRunOut = new Alert(Alert.AlertType.INFORMATION);
        timeHasRunOut.setHeaderText("You were crushed by a block! Try again");
        tickTimeline.stop();
        resetGameEnd();
        timeHasRunOut.setOnHidden(event -> restartGame());
        timeHasRunOut.show();
    }

    /**
     * Pop up for when the player was killed by a monster
     * @param monster String that is the monster that killed the player
     */
    public void monsterDeath(String monster) {
        Alert timeHasRunOut = new Alert(Alert.AlertType.INFORMATION);
        timeHasRunOut.setHeaderText("You were killed by a " + monster +
                "! Try again");
        tickTimeline.stop();
        resetGameEnd();
        timeHasRunOut.setOnHidden(event -> restartGame());
        timeHasRunOut.show();
    }

    /**
     * Pop up for if the player was killed by stepping on a water tile
     */
    public void waterDeath() {
        Alert timeHasRunOut = new Alert(Alert.AlertType.INFORMATION);
        timeHasRunOut.setHeaderText("You've drowned! Try again");
        tickTimeline.stop();
        resetGameEnd();
        timeHasRunOut.setOnHidden(event -> restartGame());
        timeHasRunOut.show();
    }

    /**
     * Pop up for if the game has run out of time
     */
    public void timeRanOut() {
        Alert timeHasRunOut = new Alert(Alert.AlertType.INFORMATION);
        timeHasRunOut.setHeaderText("You've ran out of time, try again");
        tickTimeline.stop();
        resetGameEnd();
        timeHasRunOut.setOnHidden(event -> restartGame());
        timeHasRunOut.show();
    }

    /**
     * Calls for the game to be restarted
     */
    public void restartGame() {
        tickCount = 0;
        GameGUIManager.setIsBoardWindowNext();
        GameGUIManager.windowChange();
    }

    /**
     * Draws out the game, checks over all the Tiles and then calls for
     * Actors and items to be checked and drawn on onto the board
     */
    public static void drawGame() {
        GraphicsContext gc = board.getGraphicsContext2D();
        gc.clearRect(0, 0, board.getWidth(), board.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, board.getWidth(), board.getHeight());
        //Check through tiles in PositionManager and draw them onto the board
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                int[] position = new int[]{x, y};
                gc.drawImage(new Image(ASSETS_PATH + PositionManager.
                        getTileAt(position).getImageFile()), x *
                        GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT);
                //Check if anything else is at the current position
                drawOtherElements(position, gc);
            }
        }
        //Draws player onto the game
        int[] playerPos = PositionManager.getPlayerPosition();
        gc.drawImage(new Image(ASSETS_PATH + Player.getImageFile()),
                playerPos[0] * GRID_CELL_WIDTH, playerPos[1] *
                        GRID_CELL_HEIGHT);
    }

    /**
     * Checks if there is a monsters, item, or block at the position given
     * and draws them onto the board
     * @param position current Position we're checking
     * @param gc Board that is being drawn to
     */
    public static void drawOtherElements(int[] position, GraphicsContext gc) {
        ArrayList<Monster> listOfMonsters = Board.getActors().
                getListOfMonsters();
        //Checks if a monster is at the position
        for (int i = 0; i < Board.getActors().getListOfMonsters().
                size(); i++) {
            if (PositionManager.getMonsterPosition(listOfMonsters.get(i))[0] ==
                    position[0] && PositionManager.getMonsterPosition(
                            listOfMonsters.get(i))[1] == position[1]) {
                gc.drawImage(new Image(ASSETS_PATH + listOfMonsters.get(i).
                                getImageFile()), position[0] *
                                GRID_CELL_WIDTH, position[1] *
                        GRID_CELL_HEIGHT);
            }
        }
        //Checks if an item is at the position
        HashMap<Item, int[]> itemList = PositionManager.getListOfItems();
        for (Entry<Item, int[]> entry: itemList.entrySet()) {
            int[] itemPosition = entry.getValue();
            if (itemPosition[0] == position[0] &&
                    itemPosition[1] == position[1]) {
                gc.drawImage(new Image(ASSETS_PATH +
                                entry.getKey().getImageFile()), position[0] *
                        GRID_CELL_WIDTH, position[1] * GRID_CELL_HEIGHT);
            }
        }
        //Check if a block is at the position
        HashMap<Block, int[]> blockList = PositionManager.getBlockPosition();
        for (Entry<Block, int[]> entry: blockList.entrySet()) {
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
     * Calls for the player to be moved if they have entered a key input
     */
    public void nextPlayerMove() {
        if (!Objects.equals(nextPlayerMove, "")) {
            movement.playerMovement(nextPlayerMove);
            nextPlayerMove = "";
        }
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
        FlowPane buttons = new FlowPane();
        buttons.getChildren().addAll(EXIT_BUTTON, SAVE_GAME_BUTTON);
        root.setTop(buttons);
        EXIT_BUTTON.setOnAction(this);
        SAVE_GAME_BUTTON.setOnAction(this);
        SAVE_GAME_BUTTON.setTranslateX(SAVE_GAME_BUTTON_X_TRANSLATE);
        return root;
    }

    /**
     * Performs the player movement
     * Will be adjusted once the Movement class is more made
     * @param event Key that has been pressed
     */
    public void setForKeyEvent(KeyEvent event) {
        if (Objects.equals(nextPlayerMove, "")) {
            switch (event.getCode()) {
                case UP, W -> nextPlayerMove = "U";
                case LEFT, A -> nextPlayerMove = "L";
                case DOWN, S -> nextPlayerMove = "D";
                case RIGHT, D -> nextPlayerMove = "R";
            }
        }
    }

    /**
     * Displays a pop up and calls for the level to end if the player has
     * completed the level
     */
    public void gameWonPopup() {
        Alert gameWonPopup = new Alert(Alert.AlertType.CONFIRMATION);
        gameWonPopup.setTitle("Level complete!");
        gameWonPopup.setHeaderText("You have completed the level! Well " +
                "done! Your score is: " + calculateScore());
        tickTimeline.stop();
        gameWonPopup.setOnHidden(event -> finishLevel());
        gameWonPopup.show();
    }

    /**
     * Calculates the final score of the player based off the time left and
     * the items they collected and didn't use
     * @return int that is the player's score
     */
    private int calculateScore() {
        int inventorySize;
        if (Player.getPlayerItems().size() == 0) {
            inventorySize = 1;
        } else {
            inventorySize = Player.getPlayerItems().size();
        }
        return (timer - tickCount) * inventorySize;
    }

    /**
     * Calls for the leaderboard to be updated, resets of static variable, and
     * calls for the level select window to be opened next, before closing
     * the window
     */
    public void finishLevel() {
        String currentLevel = GameGUIManager.getCurrentLevel();
        int intCurrentLevel = Integer.parseInt(currentLevel.substring(
                currentLevel.length() - 1));
        LevelSelector.updateUnlockedLevels(intCurrentLevel);
        GameGUIManager.setIsLevelSelectorWindowNext();
        resetGameEnd();
        leaderBoardUpdate(calculateScore());
        tickCount = 0;
        ((Stage) EXIT_BUTTON.getScene().getWindow()).close();
        GameGUIManager.windowChange();
    }

    /**
     * Updates the leaderboard, if the leaderboard as no ine in it,
     * It adds the player, else it calls for the leaderboard to be updated
     * @param score the score the player earned in the level
     */
    private void leaderBoardUpdate(int score) {
        String currentLevel = GameGUIManager.getCurrentLevel();
        ArrayList<ArrayList<String>> leaderboardList = getLeaderBoard(
                currentLevel);
        //Checks if the leaderboard is empty or not
        if (leaderboardList.get(0).size() == 0) {
            try {
                FileWriter fileWriter = new FileWriter(
                        LEADERBOARDS_PATH + currentLevel + ".txt");
                fileWriter.write(GameGUIManager.getCurrentProfile() + " " +
                        score);
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            updateLeaderboard(leaderboardList);
        }
    }

    /**
     * Checks if the player is in the leaderboard, and then either adds them
     * to the leaderboard or replaces the last player with them.
     * If they are already in the leaderboard, it checks if the new score
     * is higher than the old one, and if so, updates them with the new
     * score. It then calls for the list to be sorted and saved to a file
     * @param leaderboardList Names and scores in the leaderboard
     */
    private void updateLeaderboard(ArrayList<ArrayList<String>>
                                           leaderboardList) {
        ArrayList<String> playerNames = leaderboardList.get(0);
        ArrayList<String> playerScores = leaderboardList.get(1);
        String currentProfile = GameGUIManager.getCurrentProfile();
        int currentPlayerScore = calculateScore();
        //Check if the player is in the leaderbord
        if (!isInLeaderboard(playerNames)) {
            //check if the leaderboard is full or not
            if (playerScores.size() == LEADERBOARD_MAX_SIZE) {
                if (Integer.parseInt(playerScores.get(
                        LEADERBOARD_LAST_ELEMENT))
                        < currentPlayerScore) {
                    playerScores.set(LEADERBOARD_LAST_ELEMENT,
                            String.valueOf(currentPlayerScore));
                    playerNames.set(LEADERBOARD_LAST_ELEMENT,
                            currentProfile);
                }
            } else {
                playerScores.add(String.valueOf(currentPlayerScore));
                playerNames.add(currentProfile);
            }
        } else {
            for (int i = 0; i < playerNames.size(); i++) {
                if (Objects.equals(playerNames.get(i), currentProfile)) {
                    if (Integer.parseInt(playerScores.get(i)) <
                            calculateScore()) {
                        playerNames.set(i, currentProfile);
                        playerScores.set(i, String.valueOf(calculateScore()));
                    }
                }
            }
        }
        sortAndWrite(playerNames, playerScores);
    }

    /**
     * Sorts through the leaderboard with a bubble sort, updating the score and
     * name position, before writing them to a file to be saved
     * @param playerNames all names in the leaderboard
     * @param playerScores all scores in the leaderboard
     */
    public void sortAndWrite(ArrayList<String> playerNames, ArrayList<String>
            playerScores) {
        boolean swapped;
        boolean keepChecking = true;
        String tempScore;
        String tempName;
        for (int i = 0; i < playerScores.size() -  1; i++) {
            if(keepChecking) {
                swapped = false;
                for (int j = 0; j < playerScores.size() - i - 1; j++) {
                    if (Integer.parseInt(playerScores.get(j)) < Integer.parseInt(
                            playerScores.get(j + 1))) {
                        tempScore = playerScores.get(j);
                        tempName = playerNames.get(j);
                        playerScores.set(j, playerScores.get(j + 1));
                        playerNames.set(j, playerNames.get(j + 1));
                        playerScores.set(j + 1, tempScore);
                        playerNames.set(j + 1, tempName);
                        swapped = true;
                    }
                }
                if (!swapped) {
                    keepChecking = false;
                }
            }
        }
        try {
            new PrintWriter(LEADERBOARDS_PATH + GameGUIManager.
                    getCurrentLevel() + ".txt").close();
            FileWriter fileWriter = new FileWriter(LEADERBOARDS_PATH +
                    GameGUIManager.getCurrentLevel() + ".txt");
            for (int i = 0; i < playerScores.size(); i++) {
                fileWriter.write(playerNames.get(i) + " " +
                        playerScores.get(i) + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns whether a player is already in the leaderboard
     * @param playerNames names of Players in the leaderboard
     * @return whether the current player is in the leaderboard or not
     */
    private boolean isInLeaderboard(ArrayList<String> playerNames) {
        String currentProfile = GameGUIManager.getCurrentProfile();
        return playerNames.contains(currentProfile);
    }

    /**
     * Checks if a file for the leaderboard of the level exists, and if not
     * then it creates it, else it reads thought the file, creating an
     * ArrayList that contains an arrayList made up of String that are
     * each position in the leaderboard
     * @param level the level we're getting the leaderboard of
     * @return names and scores of the players in the leaderboard
     */
    public ArrayList<ArrayList<String>> getLeaderBoard(String level) {
        ArrayList<String>  playerNames = new ArrayList<>();
        ArrayList<String> playerScore = new ArrayList<>();
        try {
            File leaderboard = new File(LEADERBOARDS_PATH + level +
                    ".txt");
            if (leaderboard.exists()) {
                Scanner fileReader = new Scanner(leaderboard);
                while (fileReader.hasNextLine()) {
                    String placement = fileReader.nextLine();
                    String[] placementList = placement.split(" ");
                    playerNames.add(placementList[0]);
                    playerScore.add(placementList[1]);
                }
            } else {
                leaderboard.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> toReturn = new ArrayList<>();
        toReturn.add(playerNames);
        toReturn.add(playerScore);
        return toReturn;
    }

    /**
     * Calls for the game to be saved, it saves the level number being saved,
     * Any items he player has, the size of the board, and then calls for the
     * rows to be saved in the level file format used
     */
    private void saveGame() {
        try {
            new PrintWriter(SAVED_GAMES_PATH + "\\" +
                    GameGUIManager.getCurrentProfile() + ".txt").close();
            File toSaveTo = new File(SAVED_GAMES_PATH + "\\" +
                    GameGUIManager.getCurrentProfile() + ".txt");
            FileWriter saveWriter = new FileWriter(toSaveTo);
            String level = GameGUIManager.getCurrentLevel();
            saveWriter.write(level + "\n");
            saveWriter.write(getPlayerItemsToSave() + "\n");
            int[] dimensions = BoardGUI.getBoardSize();
            saveWriter.write(Integer.toString(dimensions[0]) + "\n");
            saveWriter.write(Integer.toString(dimensions[1]) + "\n");
            saveWriter.write(getCurrentTime() + "\n");
            for (int i = 0; i < dimensions[1]; i++) {
                saveWriter.write(saveRow(i) + "\n");
            }
            saveWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the position given and returns the cell being saved, including
     * the tile on the cell, as well as if there's an actor or item
     * on the square
     * @param row the row being checked
     * @return A String that is the current cell and it's properties
     */
    private String saveRow(int row) {
        String square = "";
        for (int i = 0; i < getBoardSize()[0]; i++) {
            int[] position = new int[] {i, row};
            square = square + tileToSave(PositionManager.getTileAt(position));
            square = square + actorOrItemToSave(position);
            square = square + "_";
        }
        return square.substring(0, square.length() - 1);
    }

    /**
     * Checks if an actor or item is on the cell being checked, and if so,
     * returns them as a String identifying them
     * @param position the position being checked
     * @return A string representing what is on the cell
     */
    private String actorOrItemToSave(int[] position) {
        //Checks if the player is at the cell
        int[] playerPosition = PositionManager.getPlayerPosition();
        if (playerPosition[0] == position[0] && playerPosition[1]
                == position[1]) {
            return ";P";
        }
        //Checks if a monster was at the cell
        ArrayList<Monster> listOfMonsters = Board.getActors().
                getListOfMonsters();
        for (Monster listOfMonster : listOfMonsters) {
            int[] monsterPosition = PositionManager.getMonsterPosition(
                    listOfMonster);
            if (monsterPosition[0] == position[0] && monsterPosition[1] ==
                    position[1]) {
                return ";M" + getMonsterToSave(listOfMonster);
            }
        }
        //Checks if a block is at the cell
        ArrayList<Block> listOfBlocks = Board.getActors().getListOfBlocks();
        for (Block listOfBlock : listOfBlocks) {
            int[] blockPosition = PositionManager.getBlockPosition(
                    listOfBlock);
            if (blockPosition[0] == position[0] && blockPosition[1] ==
                    position[1]) {
                return ";B";
            }
        }
        //Checks if an item is at the cell
        HashMap<Item, int[]> listOfItems = PositionManager.getListOfItems();
        for (Map.Entry<Item, int[]> entry : listOfItems.entrySet()) {
            int[] itemPosition = entry.getValue();
            if (itemPosition[0] == position[0] && itemPosition[1] ==
                    position[1]) {
                return ";I" + itemToSave(entry.getKey());
            }
        }
        return "";
    }

    /**
     * Checks what the item being Saved is and returns the identifier for them,
     * including any necessary information needed such as colour
     * @param toSave Item that ie being saved
     * @return The String identifier for the item
     */
    private String itemToSave(Item toSave) {
        if (toSave instanceof Chip) {
            return "C";
        } else if (toSave instanceof Key) {
            return "K" + ((Key) toSave).getColour();
        }
        return "";
    }

    /**
     * Checks which monster is being saved and returns the identifier for them,
     * including any necessary information for them
     * @param monster the monster being saved
     * @return String identifier for the Monster
     */
    private String getMonsterToSave(Monster monster) {
        if (monster instanceof Frog) {
            return "F";
        } else if (monster instanceof PinkBall) {
            return "PB" + ((PinkBall) monster).getDirection();
        } else if (monster instanceof Bug) {
            return "B" + ((Bug) monster).getSide();
        }
        return "";
    }

    /**
     * Checks what tile is being saved and returns their identifier
     * @param toCheck Tile being checked
     * @return String identitfier
     */
    private String tileToSave(Tile toCheck) {
        if (toCheck instanceof Path) {
            return "P";
        } else if (toCheck instanceof Dirt) {
            return "D";
        } else if (toCheck instanceof Wall) {
            return "W";
        } else if (toCheck instanceof Water) {
            return "A";
        } else if (toCheck instanceof Exit) {
            return "E";
        } else if (toCheck instanceof Ice) {
            return "I" + ((Ice) toCheck).getCorner();
        } else if (toCheck instanceof Door) {
            return "O" + ((Door) toCheck).getColour();
        } else if (toCheck instanceof  ChipSocket) {
            int chips = ((ChipSocket) toCheck).getNumberOfChips();
            //Integer.toString needed to prevent type mismatch when saving
            //an int to a file and trying to read it as a String
            return "C" + Integer.toString(chips);
        } else if (toCheck instanceof Trap) {
            return "T";
        } else {
            return "B";
        }
    }

    /**
     * Checks through the items the player has, and adds them to a String
     * that is returned that contains all the player's items if they have any
     * @return String that contains all the player's items
     */
    private String getPlayerItemsToSave() {
        ArrayList<Item> items = Player.getPlayerItems();
        String savedItems = "";
        for (Item item : items) {
            if (item instanceof Chip) {
                savedItems = savedItems + "C_";
            } else if (item instanceof Key) {
                savedItems = savedItems + "K" + ((Key) item).getColour() + "_";
            }
        }
        if (savedItems.length() != 0) {
            return savedItems.substring(0, savedItems.length() - 1);
        } else {
            return "";
        }
    }
}
