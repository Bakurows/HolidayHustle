package ser215.final_project;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brian on 11/16/2015.
 */
public class Player {
    private String name;
    private boolean computerPlayer;
    private CharacterType character;
    private int boardLocation;
    private PlayerPlayingCard activeCard;
    private ArrayList<PlayingCard> hand;
    private int turnSkips;
    private int playerNumber;

    //Default Constructor
    public Player() {
        this.name = "Default Player";
        this.character = new CharacterType();  //Incomplete
        this.computerPlayer = true;
        this.boardLocation = 0;
        //this.activeCard = null; //Incomplete
        this.turnSkips = 0;
    }

    //Three parameter constructor
    public Player(String name, boolean computerPlayer, CharacterType character) {
        this.name = name;
        this.computerPlayer = computerPlayer;
        this.boardLocation = 0;
        this.character = character;
        //this.activeCard = null; //Incomplete
        this.turnSkips = 0;
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
    
    public ArrayList<PlayingCard> getHand() {
    	return hand;
    }

    public int LosingTurnLeft() {
        return turnSkips;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    //Mutator Methods
    public void increaseBoardLocation(int diceRoll) {
        this.boardLocation += diceRoll;
    }

    public void setActiveCard(PlayerPlayingCard activeCard) {
        this.activeCard = activeCard;
    }

    public void incrementLosingTurn(int amountChanged) {
        this.turnSkips += amountChanged;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    //Other Methods
    public int rollDie() {
        Random rng = new Random();
        return (rng.nextInt(6) + 1);
    }
    
    public void drawCard(Deck deck) {
    	hand.add(deck.drawCard());
    }

    public boolean winBattle(Player defender) {
        int attackerRoll = rollDie();
        int defenderRoll = rollDie();

        //INSERT FORMULA FOR DETERMINING WINNER AFTER THIS
        if ((attackerRoll + this.character.getCharacterPoints() /*+ this.activeCard.getStatBoost()*/) > (defenderRoll + defender.character.getCharacterPoints() /*+ defender.activeCard.getStatBoost()*/) /*||
                this.activeCard.winAttackBattle()*/) {
            return true;
        }else {
            return false;
        }
    }
}
