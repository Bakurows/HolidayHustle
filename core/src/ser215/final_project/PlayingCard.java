package ser215.final_project;

/**
 * Created by Brian on 11/16/2015.
 */

//Should be abstract??
public abstract class PlayingCard {
    private String name;


    //Default constructor
    public PlayingCard() {
        name = "None";
    }

    //Two parameter constructor
    public PlayingCard(String name) {
        this.name = name;
    }


    //Accessor Methods
    public String getName() {
        return name;
    }



    //Other Methods
    public abstract void performAction();
}
