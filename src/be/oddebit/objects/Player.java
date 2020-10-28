package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private ArrayList<Card> hand;
    private final String name;
    private int stack;
    private int bet;


    public Player(String name) {

        this.name = name;
    }


    public void setStack(int stack) {

        this.stack = stack;
    }

    public void receivesHand(Card... cards) {

        this.hand = new ArrayList<>();
        this.hand.addAll(Arrays.asList(cards));
    }

    public void receivesCards(Card... cards) {

        this.hand.addAll(Arrays.asList(cards));
    }

    public void receivesBet(int factor) {

        this.stack += bet * factor;
    }

    public void setBet(int bet) {

        this.bet = bet;
    }


    public int getStack() {

        return stack;
    }


    public Card getCard(int index) {
        return hand.get(index);
    }

    public ArrayList<String> getHandFaces() {

        ArrayList<String> face = new ArrayList<>();

        for (Card card : hand) {
            face.add(card.getFace());
        }

        return face;
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

    public String getName() {
        return name;
    }

}
