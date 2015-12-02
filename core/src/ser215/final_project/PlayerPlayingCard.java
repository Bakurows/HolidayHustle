package ser215.final_project;

/**
 * Created by Brian on 11/18/2015.
 */
public class PlayerPlayingCard extends PlayingCard {
    private boolean winAttackBattle;
    private int statBoost;


    //default constructor
    public PlayerPlayingCard() {
        super();
        this.winAttackBattle = false;
        this.statBoost = 0;
    }

    //2 parameter constructor
    public PlayerPlayingCard(String name, boolean winAttackBattle, int statBoost) {
        super(name, statBoost);
        this.winAttackBattle = winAttackBattle;
        this.statBoost = statBoost;
    }

    @Override
    public void performAction() {

    }

    public int getStatBoost() {
        return statBoost;
    }

    public boolean winAttackBattle() {
        return winAttackBattle;
    }
}
