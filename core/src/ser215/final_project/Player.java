package ser215.final_project;

import java.util.Date;

/**
 * Created by Brian on 11/16/2015.
 */
public class Player {
    private String name;
    private boolean computerPlayer;
    private CharacterType character;
    private int boardLocation;
    private PlayingCard activeCard;
    private boolean losingTurn;

    //Default Constructor
    public Player() {
        this.name = "";
        this.character = null;  //Incomplete
        this.computerPlayer = true;
        this.boardLocation = 0;
        this.activeCard = null; //Incomplete
        this.losingTurn = false;
    }

    //Three parameter constructor
    public Player(String name, boolean computerPlayer, CharacterType character) {
        this.name = name;
        this.computerPlayer = computerPlayer;
        this.boardLocation = 0;
        this.character = character;
        this.activeCard = null; //Incomplete
        this.losingTurn = false;
    }


    //Accessor Methods
    public String getName() {
        return name;
    }

    public boolean isComputerPlayer() {
        return computerPlayer;
    }

    public CharacterType getCharacter() {
        return character;
    }

    public int getBoardLocation() {
        return boardLocation;
    }

    public PlayingCard getActiveCard() {
        return activeCard;
    }

    public boolean isLosingTurn() {
        return losingTurn;
    }


    //Mutator Methods
    public void increaseBoardLocation(int diceRoll) {
        this.boardLocation += diceRoll;
    }

    public void setActiveCard(PlayingCard activeCard) {
        this.activeCard = activeCard;
    }

    public void alternateLosingTurn() {
        this.losingTurn = !this.losingTurn;
    }
}
