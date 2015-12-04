package ser215.final_project;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brian on 11/18/2015.
 */
public class PlayingCardMoveSpaces extends PlayingCard {
    private int spaceMove;

    //default constructor
    public PlayingCardMoveSpaces() {
        super();
        this.spaceMove = 0;
    }

    //2 parameter constructor
    public PlayingCardMoveSpaces(int spaceMove, Texture cardTexture) {
        super(spaceMove, cardTexture);
        this.spaceMove = spaceMove;
    }

    @Override
    public void performAction(Player playerActingOn) {

    }
}
