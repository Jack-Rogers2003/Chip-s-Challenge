public class Frog extends Monster {
    private static final int TICK = 3;
    private static final String IMAGE_FILE = "frog.png";


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
