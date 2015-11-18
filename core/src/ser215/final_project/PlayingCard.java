package ser215.final_project;

/**
 * Created by Brian on 11/16/2015.
 */

//Should be abstract??
public class PlayingCard {
    private String name;
    private int id;


    //Default constructor
    public PlayingCard() {
    }

    //Two parameter constructor
    public PlayingCard(String name, int id) {
        this.name = name;
        this.id = id;
    }


    //Accessor Methods
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
