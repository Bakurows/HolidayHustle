package ser215.final_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Brian on 11/16/2015.
 */

//Should be abstract??
public abstract class PlayingCard {
    private String name;
    private Skin cardSkin = new Skin();
    
    protected int tier;

    //Default constructor
    public PlayingCard() {
        name = "None";
    }

    //Two parameter constructor
    public PlayingCard(String name, int tier) {
        this.name = name;
        this.tier = tier;
    }


    //Accessor Methods
    public String getName() {
        return name;
    }
    
    public int getTier() {
    	return tier;
    }



    //Other Methods
    public abstract void performAction();
}
