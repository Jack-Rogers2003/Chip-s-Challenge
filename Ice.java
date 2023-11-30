public class Ice extends Tile {
  private boolean moveability = True;
  private String imageFile;
  private String corner;

  public Ice(String newCorner) {
    corner = newCorner;
    switch (corner) {
      case "BR":
        imageFile = "file:BottomRightIce";
        break;
      case "BL":
        imageFile = "file:BottomLeftIce";
        break;
      case "TR":
        imageFile = "file:TopRightIce";
        break;
      case "TL":
        imageFile = "file:TopLeftIce";
        break;
      default:
        imageFile = "file:ice";
    }
  }

  public String getCorner() {
    return corner;
  }

  public boolean getMoveability() {
    return moveability;
  }
}
