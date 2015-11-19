package ser215.final_project;

/**
 * Created by Brian on 11/18/2015.
 */
public class PlayingCardGoBack extends PlayingCard {
    private int spaceMoveBack;

    //default constructor
    public PlayingCardGoBack() {
        super();
        spaceMoveBack = 0;
    }

    //2 parameter constructor
    public PlayingCardGoBack(String name, int spaceMoveBack) {
        super(name);
        this.spaceMoveBack = spaceMoveBack;
    }

    @Override
    public void performAction() {

    }
}
