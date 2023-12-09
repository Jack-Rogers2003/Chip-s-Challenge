
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Load {
    private static final String SAVE_FILE = "Save.txt";
    private static int tickCounter = -1;
    private static List<Item> playerItems = null;
    private String stringContents = "";
    private static File saveFile = null;
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String LEVEL_FILE_PATH = CURRENT_DIRECTORY +
            "\\Levels\\";

    public static void loadGame(Player player, Save save) throws IOException {
        playerItems = player.getPlayerItems();
        tickCounter = 5; //BoardGUI.getTickCount();
        saveFile = save.getSaveFile();
        Scanner fileReader = new Scanner(saveFile);
        int size[] = new int[2];
        size[0] = Integer.parseInt(fileReader.nextLine());
        size[1] = Integer.parseInt(fileReader.nextLine());
        int timer = Integer.parseInt(fileReader.nextLine());
        String level = ""; //new positions
        int height = size[1];
        for (int i = 0; i < height; i++) {
            level = level + fileReader.nextLine() + "\n";
        }
        int tickCount = Integer.parseInt(fileReader.nextLine());
        inventoryPutter(fileReader.nextLine(), save);
        BoardGUI.setBoardSize(size);
        BoardGUI.setGameTime(timer);
        //BoardGUI.setTicks(tickCount);
    }

    public static void  inventoryPutter(String inventoryLine, Save save) {
        String[] output = inventoryLine.split("-", 10);
        playerItems.clear();
        for (int i = 1; i < output.length; i++) {
            if (output[i].equals("Kred")) {
                playerItems.add(new Key("R"));
            } else if (output[i].equals("Kblue")) {
                playerItems.add(new Key("B"));
            } else if (output[i].equals("Kyellow")) {
                playerItems.add(new Key("Y"));
            } else if (output[i].equals("Kgreen")) {
                playerItems.add(new Key("G"));
            } else if (output[i].equals("C")) {
                playerItems.add(new Chip());
            }
        }
    }
}
