package ser215.final_project;

import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;

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
    private boolean instantWin;
    private int rollBoost;
    private int strengthGain;

    //Default Constructor
    public Player() {
        this.name = "Default Player";
        this.character = new CharacterType();  //Incomplete
        this.computerPlayer = true;
        this.boardLocation = 0;
        //this.activeCard = null; //Incomplete
        this.turnSkips = 0;
        this.instantWin = false;
        this.rollBoost = 0;
    }

    //Three parameter constructor
    public Player(String name, boolean computerPlayer, String characterName) {
        this.name = name;
        this.computerPlayer = computerPlayer;
        this.boardLocation = 0;
        this.character = new CharacterType(characterName);
        //this.activeCard = null; //Incomplete
        this.turnSkips = 0;
        this.instantWin = false;
        this.rollBoost = 0;
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

    public boolean isInstantWin() {
        return instantWin;
    }

    public int getStrengthGain() {
        return strengthGain;
    }

    public int getTurnSkips() {
        return turnSkips;
    }



    //Mutator Methods
    public void increaseBoardLocation(int diceRoll) {
        this.boardLocation += diceRoll;
    }

    public void setActiveCard(PlayerPlayingCard activeCard) {
        this.activeCard = activeCard;
    }

    public void incrementTurnSkips(int amountChanged) {
        this.turnSkips += amountChanged;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void gainInstantWin() {
        this.instantWin = true;
    }

    public void resetRollBoost() {
        this.rollBoost = 0;
    }

    public void increaseRollBoost(int incAmount) {
        this.rollBoost += incAmount;
    }

    public void increaseStrengthGain(int incAmount) {
        this.strengthGain += incAmount;
    }

    //Other Methods
    public int rollDie() {
        int random = MathUtils.random(5) + 1;
        random += this.rollBoost;
        resetRollBoost();
        return random;
        //return 1;
    }

    public int rollForBattle() {
        int random = MathUtils.random(5) + 1;
        return random;
    }
    
    public void drawCards(Deck deck, int num) {
    	for (int i = 0; i < num; i++) {
    		hand.add(deck.drawCard());
    	}
    }
    
    public int handSize() {
    	return hand.size();
    }
    
    

    public boolean winBattle(Player defender) {
        int attackerRoll = rollForBattle();
        int defenderRoll = rollForBattle();


        //INSERT FORMULA FOR DETERMINING WINNER AFTER THIS
        if ((attackerRoll + this.character.getCharacterPoints() + this.strengthGain /*+ this.activeCard.getStatBoost()*/) > (defenderRoll + defender.character.getCharacterPoints() + defender.getStrengthGain() /*+ defender.activeCard.getStatBoost()*/) ||
                this.instantWin) {
            return true;
        }else {
            return false;
        }
    }
}
