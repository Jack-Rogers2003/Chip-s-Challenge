/**
 * Class to track game statistics over time
 * 
 * @author Uzair
 * 
 * @version 1.0
 */
public class Stats {
    private static final String STATS_FILE = "stats.txt";
    //file to store game stats

    private Map<String, Integer> stats;
    //map to store stats

    private static final Stats instance = new Stats();

    private Stats() {
        stats = new HashMap<>();
        // Load stats from a file
        loadStatsFromFiLe();
    }

    /**
     * Returns an instance of the Stats
     */
    public static Stats getInstance() {
        return instance
    }

    /**
     * Update player stats based on a valid movement
     * @param playerName Name of the player
     * @param steps Number of steps taken
     */
    public void updateStats(String playerName, int steps) {
        Stats.put(playerName, stats.getOrDefault(playerName, 0) + steps);
        saveStatsToFile();
    }

    /**
     * Retrieve the number of steps taken by a player
     * @param playerName Name of the player
     * @return Number of steps taken by the player
     */
    public int getSteps(String playerName) {
        return stats.getOrDefault(playerName, 0);
    }

    /**
     * Save stats to a file
     */
    private void saveStatsToFile() {
        try (FileWriter writer = new FileWriter(STATS_FILE)) {
            for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                writer.write(entry.getKey() + "," + entery.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Load stats to a file
     */
    private void loadStatsFromFiLe() {
   //To be implemented
    }
}
