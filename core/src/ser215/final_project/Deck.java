package ser215.final_project;

import java.util.ArrayList;
import java.util.Random;

/**
 * Deck class to generate the playing cards so players can draw
 * @author Andrew
 * 
 */
public class Deck {
	private int maxSize = 50;
	private ArrayList<PlayingCard> deckArray;
	
	public Deck() {
		Random rng = new Random();
		for (int i = 0; i < maxSize; i++) {
			int card = rng.nextInt(10 + 1);
			switch(card) {
				case 1:	deckArray.add(new PlayingCardGoBack("???", 1));
						break;
				case 2:	deckArray.add(new PlayingCardGoBack("???", 2));
						break;
				case 3:	deckArray.add(new PlayingCardGoBack("???", 3));
						break;
				case 4:	deckArray.add(new PlayingCardLoseTurn("???", 1));
						break;
				case 5:	deckArray.add(new PlayingCardLoseTurn("???", 2));
						break;
				case 6:	deckArray.add(new PlayingCardLoseTurn("???", 3));
						break;
				case 7:	deckArray.add(new PlayerPlayingCard("???", false, 1));
						break;
				case 8:	deckArray.add(new PlayerPlayingCard("???", false, 2));
						break;
				case 9:	deckArray.add(new PlayerPlayingCard("???", false, 3));
						break;
				case 10:deckArray.add(new PlayerPlayingCard("???", true, 0));
						break;
			}
		}
	}
	
	/**
	 * Takes a card out of the deck
	 * @return the card removed from deck
	 */
	public PlayingCard drawCard() {
		Random rng = new Random();
		int card = rng.nextInt(deckArray.size());
		return deckArray.remove(card);
	}
}
