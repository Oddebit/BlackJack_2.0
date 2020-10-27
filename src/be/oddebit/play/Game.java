package be.oddebit.play;

import be.oddebit.objects.Deck;
import be.oddebit.objects.Hand;
import be.oddebit.ui.Terminal;

public class Game {

    Deck deck;

    Hand player;
    Hand dealer;

    Hand splitHand1;
    Hand splitHand2;


    public Game() {

        boolean play = true;
        while (play) {

            this.deck  = new Deck(6);
            deal();

            if (player.getScore() == 21) {

                Terminal.win(player);
                play = Terminal.restart();
                continue;
            }

            if (splitCondition() && Terminal.askSplit()) {
                splitProcedure();
            } else {
                procedure();
            }

            play = Terminal.restart();
        }

    }

    private void deal() {

        Terminal.sayDeal();

        this.player = new Hand("Player", deck.removeCard(), deck.removeCard());
        this.dealer = new Hand("Dealer", deck.removeCard(), deck.removeCard());

        Terminal.showHand(player, false);
        Terminal.showHand(dealer, true);

    }

    private void procedure() {

        hitOrStand(player);

        if (player.getScore() > 21) {

            Terminal.lose(player);

        } else {

            dealerHitOrStand();
            Terminal.showHand(dealer, false);
            whoWins(player);

        }

    }

    private boolean splitCondition() {

        return player.getCard(0).getFace().equals(player.getCard(1).getFace());
    }

    private void splitProcedure() {

        splitDeal();

        hitOrStand(splitHand1);
        hitOrStand(splitHand2);

        if (splitHand1.getScore() > 21 && splitHand2.getScore() > 21) {

            Terminal.lose(player);

        } else {

            dealerHitOrStand();
            Terminal.showHand(dealer, false);

            whoWins(splitHand1);
            whoWins(splitHand2);

        }
    }

    private void splitDeal() {

        this.splitHand1 = new Hand("First split hand", player.getCard(0), deck.removeCard());
        this.splitHand2 = new Hand("Second split hand", player.getCard(1), deck.removeCard());

        Terminal.showHand(splitHand1, false);
        Terminal.showHand(splitHand2, false);

    }


    private void hitOrStand(Hand hand) {

        Terminal.saySplitHand(hand);
        boolean hit = true;
        while (hit && hand.getScore() < 21) {

            hit = Terminal.askCard();
            if (hit) hand.receivesCard(deck.removeCard());
            Terminal.showHand(hand, false);
        }

    }

    private void dealerHitOrStand() {

        while (dealer.getScore() <= 16) {

            dealer.receivesCard(deck.removeCard());
        }

    }

    private void whoWins(Hand hand) {

        if (hand.getScore() > dealer.getScore() || dealer.getScore() > 21) {
            Terminal.win(hand);
        } else if (hand.getScore() == dealer.getScore()) {
            Terminal.draw(hand);
        } else {
            Terminal.lose(hand);
        }

    }

}
