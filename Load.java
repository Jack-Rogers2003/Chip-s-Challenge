import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Load {
    private static final String SAVE_FILE = "Save.txt";
    private static int tickCounter = -1;
    private static List<Item> playerItems = null;
    private String stringContents = "";
    private static File saveFile = null;
    private int width = 0;
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String LEVEL_FILE_PATH = CURRENT_DIRECTORY +
            "\\Levels\\";

    public static void loadGame(Player player, Save save) throws IOException {
        playerItems = player.getPlayerItems();
        tickCounter = 5; //BoardGUI.getTickCount();
        Scanner fileReader = new Scanner(new File(LEVEL_FILE_PATH
                + save.getLevelFile() + ".txt"));
        saveFile = new File("Save.txt");
        int size[] = new int[2];
        size[0] = Integer.parseInt(fileReader.nextLine());
        size[1] = Integer.parseInt(fileReader.nextLine());
        int timer = Integer.parseInt(fileReader.nextLine());
        BoardGUI.setBoardSize(size);
        BoardGUI.setGameTime(timer);
    }
}
