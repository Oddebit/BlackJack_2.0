package be.oddebit.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private ArrayList<Card> hand = new ArrayList<>();
    private final String name;
    private int stack;
    private int bet;


    public Player(String name, int stack) {

        this.name = name;
        this.stack = stack;
    }


    public void receivesCard(Card... cards) {

        this.hand.addAll(Arrays.asList(cards));
    }

    public void receivesBet(int factor) {

        this.stack += bet * factor;
    }

    public void addStack(int... stacks) {

        for (int stack : stacks) {
            this.stack += stack;
        }
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

    public String getName() {
        return name;
    }

}
