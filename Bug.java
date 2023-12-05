public class Bug extends Monster{

    private static final int TICK = 3;
    private static final String IMAGE_FILE = "bug.png";
    private final String side;

    public Bug(String sideToSet) {
        side = sideToSet;
    }

    public String getSide() {
        return side;
    }

    public int getTick() {
        return TICK;
    }

    public int[] getNextMovement() {
        return new int[]{0,0};
    }

    public String getImageFile() {
        return IMAGE_FILE;
    }
}
