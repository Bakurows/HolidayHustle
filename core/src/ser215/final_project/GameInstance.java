package ser215.final_project;

import java.util.Date;

/**
 * Created by Brian on 11/16/2015.
 */
public class GameInstance {
    private int numberPlayers;
    private int[] playerLocations;
    private Date currentDate;
    private Player[] playersList;
    private int currentPlayerTurn;


    //Default Constructor
    public GameInstance() {
        this.numberPlayers = 3;
        playerLocations = new int[] {0, 0, 0};
        currentDate = new Date();
        playersList = null;
        currentPlayerTurn = 0;
    }

    //X parameter constructor
    public GameInstance(int numberPlayers, Player[] playersList, int currentPlayerTurn) {
        this.numberPlayers = numberPlayers;
        this.currentDate = new Date();
        this.playersList = playersList;
        for (int i = 0; i < numberPlayers; i++) {
            playerLocations[i] = 0;
        }
        this.currentPlayerTurn = currentPlayerTurn;
    }


    //Accessor Methods
    public int getNumberPlayers() {
        return numberPlayers;
    }

    public int[] getPlayerLocations() {
        return playerLocations;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public Player[] getPlayersList() {
        return playersList;
    }

    public int getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }


    //Mutator Methods
    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public void setPlayerLocations(int[] playerLocations) {
        this.playerLocations = playerLocations;
    }

    public void setCurrentPlayerTurn() {
        this.currentPlayerTurn ++;
    }
}
