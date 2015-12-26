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
    private ArrayList<PlayingCard> hand;
    private int turnSkips;
    private boolean instantWin;
    private int rollBoost;
    private int strengthGain;

    //no-arg Constructor
    public Player() {
        this.name = "Default Player";
        this.character = new CharacterType();
        this.computerPlayer = true;
        this.boardLocation = 0;
        this.turnSkips = 0;
        this.instantWin = false;
        this.rollBoost = 0;
        this.hand = new ArrayList<PlayingCard>();
    }

    //Three-arg constructor
    public Player(String name, boolean computerPlayer, String characterName) {
        this.name = name;
        this.computerPlayer = computerPlayer;
        this.boardLocation = 0;
        this.character = new CharacterType(characterName);
        this.turnSkips = 0;
        this.instantWin = false;
        this.rollBoost = 0;
        this.hand = new ArrayList<PlayingCard>();
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
    
    public ArrayList<PlayingCard> getHand() {
    	return hand;
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

    public int getRollBoost() {
        return rollBoost;
    }

    //Mutator Methods
    public void increaseBoardLocation(int diceRoll) {
        if (this.boardLocation + diceRoll < 0) {

        }else {
            this.boardLocation += diceRoll;
        }
    }

    public void incrementTurnSkips(int amountChanged) {
        this.turnSkips += amountChanged;
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
    //Roll die method to get a random int between 1 and six. Used for rolling to move only
    public int rollDie() {
        int random = MathUtils.random(5) + 1;
        random += this.rollBoost;
        resetRollBoost();
        return random;
    }

    //Same as above method, but only used for rolling in battle, so it doesn't use the roll boost for moving
    public int rollForBattle() {
        int random = MathUtils.random(5) + 1;
        return random;
    }

    //Draws cards from the deck and adds them to the players hand
    public void drawCards(Deck deck, int num) {
    	for (int i = 0; i < num; i++) {
            if (deck.getDeckArray().size() > 0 )
                this.hand.add(deck.drawCard());
    	}
    }

    //Removes a card from the players hand
    public void removeCard(PlayingCard card) {
        this.hand.remove(card);
    }

    //Determines if the attacker or the defender wins the battle
    public boolean winBattle(Player defender) {
        int attackerRoll = rollForBattle();
        int defenderRoll = rollForBattle();

        if ((attackerRoll + this.character.getCharacterPoints() + this.strengthGain) > (defenderRoll + defender.character.getCharacterPoints() + defender.getStrengthGain()) || this.instantWin) {
            return true;
        }else {
            return false;
        }
    }
}
