import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//NOT FINISHED. JUST SHOWING PROGRESS
public class Save {
    private static final String SAVE_FILE = "Save.txt";
    private int tickCounter = -1;
    private List<Item> playerItems = null;
    private String stringContents = "";
    private File saveFile = null;
    public void saveGame(Player player) throws IOException {
        playerItems = player.getPlayerItems();
        tickCounter = 5; //BoardGUI.getTickCount();
        saveFile = new File("Save.txt");
        FileWriter saveWriter = new FileWriter(saveFile, false);
        saveWriter.write(itemALToString((ArrayList<Item>) playerItems) + tickCounter);
        saveWriter.close();
    }

    public String itemALToString (ArrayList<Item> arrayList) {
        String contents = "";
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof Key) {
                Key key = (Key) arrayList.get(i);
                contents = contents + "[" + key.getColour() + "]";
            } else if (arrayList.get(i) instanceof Chip) {
                Chip chip = (Chip) arrayList.get(i);
                contents = contents + "chip";
            }
        }
        return contents;
    }

    public void loadGame (Player player) throws IOException {
         Scanner saveReader = new Scanner(saveFile);
         while (saveReader.hasNextLine()) {
            String data = saveReader.nextLine();
            System.out.println(data);
         }
        saveReader.close();

    }
}