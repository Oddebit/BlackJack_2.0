package be.oddebit.play;

import be.oddebit.objects.Deck;
import be.oddebit.objects.Player;
import be.oddebit.ui.Terminal;

public class Game {

    Deck deck;

    public Player currentPlayer;
    Player dealer;

    Player splitHand1;
    Player splitHand2;

    public Game(int stack) {

        this.currentPlayer = new Player("Player", stack);
        this.dealer = new Player("Dealer", 0);

        boolean play = true;
        while (play) {

            this.deck = new Deck(6);

            Terminal.sayNewGame();
            this.currentPlayer.setBet(Terminal.askBet(this.currentPlayer));
            deal();


            if (splitCondition() && Terminal.askSplit()) {
                splitProcedure();
            } else {
                procedure();
            }

            play = Terminal.askRestart();

        }

    }

    private void deal() {

        Terminal.sayDeal();

        this.currentPlayer.receivesCard(deck.removeCard(), deck.removeCard());
        this.dealer.receivesCard(deck.removeCard(), deck.removeCard());

        Terminal.showHand(currentPlayer, false);
        Terminal.showHand(dealer, true);

    }

    private void procedure() {

        if (currentPlayer.getScore() == 21) {

            this.currentPlayer.receivesBet(2);
            Terminal.blackJack(currentPlayer);
        }

        hitOrStand(currentPlayer);

        if (currentPlayer.getScore() > 21) {

            Terminal.lose(currentPlayer);

        } else {

            dealerHitOrStand();
            Terminal.showHand(dealer, false);
            whoWins(currentPlayer);

        }

    }

    private boolean splitCondition() {

        return currentPlayer.getCard(0).getFace().equals(currentPlayer.getCard(1).getFace());
    }

    private void splitProcedure() {

        splitDeal();

        hitOrStand(splitHand1);
        hitOrStand(splitHand2);

        if (splitHand1.getScore() > 21 && splitHand2.getScore() > 21) {

            Terminal.lose(currentPlayer);

        } else {

            dealerHitOrStand();
            Terminal.showHand(dealer, false);

            whoWins(splitHand1);
            whoWins(splitHand2);

            this.currentPlayer.addStack(splitHand1.getStack(), splitHand1.getStack());

        }
    }

    private void splitDeal() {

        this.splitHand1 = new Player("First split hand", 0);
        splitHand1.receivesCard(currentPlayer.getCard(0), deck.removeCard());

        this.splitHand2 = new Player("Second split hand", 0);
        splitHand2.receivesCard(currentPlayer.getCard(1), deck.removeCard());

        Terminal.showHand(splitHand1, false);
        Terminal.showHand(splitHand2, false);

    }


    private void hitOrStand(Player player) {

        Terminal.sayName(player);
        boolean hit = true;
        while (hit && player.getScore() < 21) {

            hit = Terminal.askCard();
            if (hit) player.receivesCard(deck.removeCard());
            Terminal.showHand(player, false);
        }

    }

    private void dealerHitOrStand() {

        while (dealer.getScore() <= 16) {

            dealer.receivesCard(deck.removeCard());
        }

    }

    private void whoWins(Player player) {

        if (player.getScore() > dealer.getScore() || dealer.getScore() > 21) {
            player.receivesBet(1);
            Terminal.win(player);
        } else if (player.getScore() == dealer.getScore()) {
            player.receivesBet(0);
            Terminal.draw(player);
        } else {
            player.receivesBet((-1));
            Terminal.lose(player);
        }

    }

}
