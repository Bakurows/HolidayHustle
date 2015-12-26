package ser215.final_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Brian on 11/16/2015.
 */

//Should be abstract??
public abstract class PlayingCard {
    private Texture cardTexture;
    private Image cardImage;
    protected String type;
    protected int tier;

    //Three parameter constructor
    public PlayingCard(String type, int tier, Texture cardTexture) {
    	this.type = type;
        this.tier = tier;
        this.cardTexture = cardTexture;
        this.cardImage = new Image(cardTexture);
    }


    //Accessor Methods
    public String getType() {
    	return type;
    }
    
    public Image getImage() {
    	return cardImage;
    }

    public Texture getCardTexture() {
        return cardTexture;
    }

    //Other Methods
    public abstract void performAction(Player playerActingOn);
}
