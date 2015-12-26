package ser215.final_project;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brian on 11/18/2015.
 */
public class PlayingCardMoveSpaces extends PlayingCard {
    private int spaceMove;

    //2 parameter constructor
    public PlayingCardMoveSpaces(String type, int spaceMove, Texture cardTexture) {
        super(type, spaceMove, cardTexture);
        this.spaceMove = spaceMove;
    }

    //Performs the action associated with this type of playing card - Moving a player a designated number of spaces
    @Override
    public void performAction(Player playerActingOn) {
        playerActingOn.increaseBoardLocation(this.spaceMove);
    }
}
