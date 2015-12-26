package ser215.final_project;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brian on 11/18/2015.
 */
public class PlayerPlayingCard extends PlayingCard {
    private int strengthBoost;

    //2-arg constructor
    public PlayerPlayingCard(String type, int strengthBoost, Texture cardTexture) {
        super(type, strengthBoost, cardTexture);
        this.strengthBoost = strengthBoost;
    }

    //Performs the action associated with this type of playing card - Giving a player a strength boost/loss and giving a player the Instant Win ability
    @Override
    public void performAction(Player playerActingOn) {
        System.out.println("Perform action PlayerPlayingCard");
        if (this.strengthBoost == 0)
            playerActingOn.gainInstantWin();
        playerActingOn.increaseStrengthGain(this.strengthBoost);
    }
}
