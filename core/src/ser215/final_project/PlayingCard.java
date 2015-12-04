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
    private Skin cardSkin = new Skin();
    private Texture cardTexture;
    private Image cardImage;
    
    protected int tier;

    //Default constructor
    public PlayingCard() {
    }

    //Two parameter constructor
    public PlayingCard(int tier, Texture cardTexture) {
        this.tier = tier;
        this.cardTexture = cardTexture;
        this.cardImage = new Image(cardTexture);
    }


    //Accessor Methods
    public int getTier() {
    	return tier;
    }
    
    public Image getImage() {
    	return cardImage;
    }



    //Other Methods
    public abstract void performAction(Player playerActingOn);
}
