package ser215.final_project;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brian on 11/18/2015.
 */
public class PlayingCardLoseTurn extends PlayingCard {
    private int turnsLost;

    //Default Constructor
    public PlayingCardLoseTurn() {
        super();
        turnsLost = 0;
    }

    //Name parameter constructor
    public PlayingCardLoseTurn(int turnsLost, Texture cardTexture) {
        super(turnsLost, cardTexture);
        this.turnsLost = turnsLost;
    }


    @Override
    public void performAction(Player playerActingOn) {
        //Does this card have multiple tiers?
        playerActingOn.incrementTurnSkips(super.getTier());
    }
}
