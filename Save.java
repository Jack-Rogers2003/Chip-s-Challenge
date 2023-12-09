package com.example.goingtoblowmybrainsout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//NOT FINISHED. JUST SHOWING PROGRESS. LOTS OF JUNK CLASSES. WHATEVER
public class Save {
    private static final String SAVE_FILE = "Save.txt";
    private int tickCounter = -1;
    private List<Item> playerItems = null;
    private String stringContents = "";
    private File saveFile = null;
    private int width = 0;
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String LEVEL_FILE_PATH = CURRENT_DIRECTORY +
            "\\Levels\\";
    private String levelFile = "";
    public void saveGame(Player player, String levelFile) throws IOException {
        playerItems = player.getPlayerItems();
        tickCounter = 5; //BoardGUI.getTickCount();
        this.levelFile = levelFile;
        Scanner fileReader = new Scanner(new File(LEVEL_FILE_PATH
                + levelFile + ".txt"));
        saveFile = new File("Save.txt");
        FileWriter saveWriter = new FileWriter(saveFile, false);
        saveWriter.write( writeLevel(levelFile) + tickCounter + "\n" + itemALToString((ArrayList<Item>) playerItems));
        saveWriter.close();
    }

    public String itemALToString (ArrayList<Item> arrayList) {
        String contents = "";
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof Key) {
                Key key = (Key) arrayList.get(i);
                contents = contents + "[K" + key.getColour() + "]";
            } else if (arrayList.get(i) instanceof Chip) {
                Chip chip = (Chip) arrayList.get(i);
                contents = contents + "[C]";
            }
        }
        return contents;
    }

    public String writeLevel (String levelFile) throws FileNotFoundException {
        String contents = "";
        Scanner fileReader = new Scanner(new File(LEVEL_FILE_PATH
                + levelFile + ".txt"));
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            contents = contents + data + "\n";
        }
        return contents;
    }

    public String createRow (String fileRow, int nextRow) {
        String contents = "";
        String[] rowElements = fileRow.split("_");
        for(int i = 0; i <= 5; i++) { //BoardGUI.getBoardSize()[0] - 1
            int[] currentSquarePosition = new int[]{i,nextRow};
            String[] propertiesOfSquare = rowElements[i].split(";");
            contents = contents + propertiesOfSquare;
            if(rowElements[i].length() > 1) {
                for(int j = 1; j < propertiesOfSquare.length; j++) {
                    contents = contents + propertiesOfSquare;
                }
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

    public String getLevelFile() {
        return levelFile;
    }
}