package ser215.final_project;

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
    public PlayingCardLoseTurn(String name, int turnsLost) {
        super(name, turnsLost);
        this.turnsLost = turnsLost;
    }


    @Override
    public void performAction() {

    }
}
