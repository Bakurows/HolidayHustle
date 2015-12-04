package ser215.final_project;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brian on 11/18/2015.
 */
public class PlayerPlayingCard extends PlayingCard {
    private boolean winAttackBattle;
    private int strengthBoost;


    //default constructor
    public PlayerPlayingCard() {
        super();
        this.winAttackBattle = false;
        this.strengthBoost = 0;
    }

    //2 parameter constructor
    public PlayerPlayingCard(boolean winAttackBattle, int strengthBoost, Texture cardTexture) {
        super(strengthBoost, cardTexture);
        this.winAttackBattle = winAttackBattle;
        this.strengthBoost = strengthBoost;
    }

    public int getStatBoost() {
        return strengthBoost;
    }

    public boolean winAttackBattle() {
        return winAttackBattle;
    }

    @Override
    public void performAction(Player playerActingOn) {

    }
}
