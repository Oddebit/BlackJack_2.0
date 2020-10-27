package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

    private ArrayList<Card> hand = new ArrayList<Card>();
    private final String owner;


    public Hand(String owner, Card... cards) {

        this.owner = owner;
        this.hand.addAll(Arrays.asList(cards));
    }


    public void receivesCard(Card... cards) {

        this.hand.addAll(Arrays.asList(cards));
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    public ArrayList<String> getHand() {

        ArrayList<String> visual = new ArrayList<>();

        for (Card card : hand) {
            visual.add(card.getFace());
        }

        return visual;
    }

    private int getAces() {

        int aces = 0;
        for (Card card : hand) {
            if (card.getValue() == 11) aces++;
        }

        return aces;
    }

    public int getScore() {

        int score = 0;
        int aces = getAces();

        for (Card card : hand) {

            score += card.getValue();
        }

        while (score > 21 && aces > 0) {

            score -= 10;
            aces -= 1;
        }

        return score;
    }

    public String getOwner() {
        return owner;
    }


}
