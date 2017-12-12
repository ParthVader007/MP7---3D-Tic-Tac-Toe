public class Player {
    /** Class variable that we used to generate a unique ID for each newly created player. */
    private static int globalID = 0;

    /**
     * Name of player.
     */
    private String name;

    /**
     * Get the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the player's name.
     *
     * @param setName the player's new name
     */
    public void setName(final String setName) {
        this.name = setName;
    }

    /** The number of games this player has won. */
    private int score;

    /**
     * Get this player's score.
     *
     * @return this player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Add one to this player's score.
     */
    public void addScore() {
        score += 1;
    }

    /** This player's ID. Used internally by {@link #equals(Object) equals()}. */
    private int id;

    /**
     * Get the current player's id.
     *
     * @return the current player's id
     */
    public int getID() {
        return id;
    }

    /**
     * Create a new player with the given name.
     * <p>
     * Each player's score begins at zero, and each receives a monotonically increasing unique ID.
     *
     * @param setName the name for the new player
     */
    public Player(final String setName) {
        this.name = setName;
        this.score = 0;
        this.id = Player.globalID++;
    }

    /**
     * Create a copy of an existing player.
     * <p>
     * This copy constructor creates a copy of an existing player, so that player information can be
     * exposed without allowing the copy to modify the state of the original player.
     * <p>
     * The id is copied, meaning that the copy will initially be equal to the original. However,
     * future modifications to the score field will not change the copy and will result in it being
     * considered non-equal to the original.
     *
     * @param other the other
     */
    public Player(final Player other) {
        this.name = other.name;
        this.score = other.score;
        this.id = other.id;
    }
    
    public String toString() {
    	return name.substring(0, 3);
    }
}