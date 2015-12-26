package ser215.final_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * Deck class to generate the playing cards so players can draw
 * @author Andrew
 * 
 */
public class Deck {
	private final int maxSize = 50;
	private ArrayList<PlayingCard> deckArray;
	
	public Deck() {
		this.deckArray = new ArrayList<>();
		for (int i = 0; i < maxSize; i++) {
			int card = MathUtils.random(15) + 1;
			switch(card) {
				case 1:	deckArray.add(new PlayingCardMoveSpaces("move_up", 1, new Texture(Gdx.files.internal("cards/move_up_1.png"))));
						break;
				case 2:	deckArray.add(new PlayingCardMoveSpaces("move_up", 2, new Texture(Gdx.files.internal("cards/move_up_2.png"))));
						break;
				case 3:	deckArray.add(new PlayingCardMoveSpaces("move_up", 3, new Texture(Gdx.files.internal("cards/move_up_3.png"))));
						break;
                case 4:	deckArray.add(new PlayingCardMoveSpaces("move_down", -1, new Texture(Gdx.files.internal("cards/move_down_1.png"))));
                    break;
                case 5:	deckArray.add(new PlayingCardMoveSpaces("move_down", -2, new Texture(Gdx.files.internal("cards/move_down_2.png"))));
                    break;
                case 6:	deckArray.add(new PlayingCardMoveSpaces("move_down", -3, new Texture(Gdx.files.internal("cards/move_down_3.png"))));
                    break;
				case 7:	deckArray.add(new PlayingCardLoseTurn("lose_turn", 1, new Texture(Gdx.files.internal("cards/lose_turn_1.png"))));
						break;
				case 8:	deckArray.add(new PlayingCardLoseTurn("lose_turn", 2, new Texture(Gdx.files.internal("cards/lose_turn_2.png"))));
						break;
				case 9:	deckArray.add(new PlayingCardLoseTurn("lose_turn", 3, new Texture(Gdx.files.internal("cards/lose_turn_3.png"))));
						break;
				case 10:	deckArray.add(new PlayerPlayingCard("add_strength", 1, new Texture(Gdx.files.internal("cards/add_strength_1.png"))));
						break;
				case 11:	deckArray.add(new PlayerPlayingCard("add_strength", 2, new Texture(Gdx.files.internal("cards/add_strength_2.png"))));
						break;
				case 12:	deckArray.add(new PlayerPlayingCard("add_strength", 3, new Texture(Gdx.files.internal("cards/add_strength_3.png"))));
						break;
                case 13:	deckArray.add(new PlayerPlayingCard("lose_strength", -1, new Texture(Gdx.files.internal("cards/lose_strength_1.png"))));
                    break;
                case 14:	deckArray.add(new PlayerPlayingCard("lose_strength", -2, new Texture(Gdx.files.internal("cards/lose_strength_2.png"))));
                    break;
                case 15:	deckArray.add(new PlayerPlayingCard("lose_strength", -3, new Texture(Gdx.files.internal("cards/lose_strength_3.png"))));
                    break;
				case 16:deckArray.add(new PlayerPlayingCard("battle_win", 0, new Texture(Gdx.files.internal("cards/battle_win.png"))));
						break;
			}
		}
	}

    public ArrayList<PlayingCard> getDeckArray() {
        return deckArray;
    }

    /**
	 * Takes a card out of the deck
	 * @return the card removed from deck
	 */
	public PlayingCard drawCard() {
		int card = MathUtils.random(deckArray.size() - 1);

		return deckArray.remove(card);
	}
}
