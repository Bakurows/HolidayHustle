package ser215.final_project;

import java.util.Date;

/**
 * Created by Brian on 11/16/2015.
 */
public class Game {
    private int numberPlayers;
    //private int[] playerLocations;
    private Date currentDate;
    private Player[] playersList;
    private int currentPlayerTurn;
    private PlayingCard[] playingCards;


    //Default Constructor
    public Game() {
        this.numberPlayers = 3;
        //playerLocations = new int[] {0, 0, 0};
        currentDate = new Date();
        playersList = null;
        currentPlayerTurn = 0;
    }

    //X parameter constructor
    public Game(int numberPlayers) {
        this.numberPlayers = numberPlayers;
        this.currentDate = new Date();
        this.playersList = new Player[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            //playerLocations[i] = 0;
        }
        this.currentPlayerTurn = 0;
    }


    //Accessor Methods
    public int getNumberPlayers() {
        return numberPlayers;
    }

    /*public int[] getPlayerLocations() {
        return playerLocations;
    }*/

    /*public int getCurrentPlayerLocation() {
        return this.playerLocations[this.currentPlayerTurn];
    }*/

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
    public void changePlayerLocation(int currentPlayer, int spaceMoved) {
        this.playersList[currentPlayer].increaseBoardLocation(spaceMoved);
    }

    public void incrementPlayerTurn() {
        this.currentPlayerTurn ++;
    }


    //Other Methods
    public void nextTurn() {
        int dieRoll = this.playersList[this.currentPlayerTurn].rollDie();
        movePlayer(this.currentPlayerTurn, dieRoll);
    }

    public void movePlayer(int playerToMove, int dieRoll) {
        if (otherPlayerPresent(this.playersList[playerToMove].getBoardLocation() + dieRoll)) {
            if (this.playersList[this.currentPlayerTurn].winBattle(this.playersList[getPlayerNumberAtLocation(this.playersList[playerToMove].getBoardLocation() + dieRoll)])) {
                moveLosingDefender(getPlayerNumberAtLocation(this.playersList[playerToMove].getBoardLocation() + dieRoll));
                changePlayerLocation(playerToMove, dieRoll);
            }else {
                movePlayer(playerToMove, dieRoll - 1);
            }
        }else {
            changePlayerLocation(playerToMove, dieRoll);
        }
    }

    public boolean otherPlayerPresent(int boardLocation) {
        for (int i = 0; i < this.numberPlayers; i++) {
            if (this.playersList[i].getBoardLocation() == boardLocation)
                return true;
        }
        return false;
    }

    public void moveLosingDefender (int playerNumber) {
        movePlayer(playerNumber, -1);       //-1 is back one space for losing battle
    }

    public int getPlayerNumberAtLocation(int boardLocation) {
        for (int i = 0; i < this.numberPlayers; i++) {
            if (this.playersList[i].getBoardLocation() == boardLocation)
                return i;
        }
        return 0;
    }

    //NEED TO ADD METHOD(s) FOR VARIOUS GAME BOARD EVENTS SUCH AS LOSE TURN,
}
