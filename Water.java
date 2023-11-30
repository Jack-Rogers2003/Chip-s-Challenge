public class Water extends Tile {
  public boolean movability = True;
  public String imageFile = "file:water.png";

  public boolean getMoveability() {
    return moveability;
  }

  public String getFilePath() {
    return imageFile;
  }
}
