package ser215.final_project;

import java.time.LocalDate;

/**
 * Created by Brian on 11/16/2015.
 */
public class GameInstance {
    private int numberPlayers;
    //private int[] playerLocations;
    private LocalDate currentDate;
    private Player[] playersList;
    private int currentPlayerTurn;
    private PlayingCard[] playingCards;
    final private int LASTBOARDLOCATION = 38;//Need to change for correct value


    //Default Constructor
    public GameInstance() {
        this.numberPlayers = 3;
        //playerLocations = new int[] {0, 0, 0};
        this.currentDate.now();
        this.playersList = null;
        this.currentPlayerTurn = 0;
    }

    //X parameter constructor
    public GameInstance(int numberPlayers) {
        this.numberPlayers = numberPlayers;
        this.currentDate.now();
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

    public LocalDate getCurrentDate() {
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
    //Begins the next players turn
    public void nextTurn() {
        int dieRoll = this.playersList[this.currentPlayerTurn].rollDie();
        movePlayer(this.currentPlayerTurn, dieRoll);
    }

    //Moves a player
    public void movePlayer(int playerToMove, int dieRoll) {
        if (!(this.playersList[playerToMove].getBoardLocation() + dieRoll > LASTBOARDLOCATION) && (this.playersList[playerToMove].getBoardLocation() + dieRoll < (LASTBOARDLOCATION - 2) ||
                this.playersList[playerToMove].getBoardLocation() + dieRoll == LASTBOARDLOCATION)){
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
        }else {
            //Add code to let player know that their roll doesn't allow any moves
        }
    }

    //Determines if another player is present on the spot a player is going to
    public boolean otherPlayerPresent(int boardLocation) {
        for (int i = 0; i < this.numberPlayers; i++) {
            if (this.playersList[i].getBoardLocation() == boardLocation)
                return true;
        }
        return false;
    }

    //Moves the defender if he/she loses
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

    public void calculatePlayerPositions() {
        for (int i = 0; i < numberPlayers; i++) {
            //Add code to get the correct location to draw the players on the board
        }
    }
}
