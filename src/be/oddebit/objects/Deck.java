package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<Card>();

    public Deck(int numberOfDecks) {

        if (numberOfDecks < 1) numberOfDecks = 1;

        for (int i = 0; i < 13; i++) {

            for (int j = 0; j < 4 * numberOfDecks; j++) {

                deck.add(new Card(i + 1));
            }
        }

    }

    public Card removeCard() {
 //       Random random = new Random();
 //       int index = random.nextInt(deck.size());

        int index = 10;
        return this.deck.remove(index);
    }

    public int getSize() {
        return deck.size();
    }
}
