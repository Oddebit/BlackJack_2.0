package be.oddebit.play;

import be.oddebit.objects.Deck;
import be.oddebit.objects.Player;
import be.oddebit.ui.Terminal;

public class Game {

    Deck deck;

    Player currentPlayer;
    Player dealer;

    Player splitHand1;
    Player splitHand2;

    public Game(Player player) {

        this.currentPlayer = player;
        this.dealer = new Player("Dealer");

        boolean play = true;
        while (play && currentPlayer.getStack() > 0) {

            shuffle();

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

        if (!play) Terminal.sayBye(currentPlayer);

        if (currentPlayer.getStack() <= 0) Terminal.sayBroke(currentPlayer);

    }

    private void shuffle() {

        this.currentPlayer.clearHand();
        this.dealer.clearHand();
        this.deck = new Deck(6);
    }

    private void deal() {

        Terminal.sayDeal();

        this.currentPlayer.receivesCards(deck.removeCard(), deck.removeCard());
        this.dealer.receivesCards(deck.removeCard(), deck.removeCard());

        Terminal.showHand(currentPlayer, false);
        Terminal.showHand(dealer, true);

    }

    private void procedure() {

        if (currentPlayer.getScore() == 21) {

            Terminal.blackJack(currentPlayer);
        }

        hitOrStand(currentPlayer);

        if (currentPlayer.getScore() <= 21) {

            dealerHitOrStand();
            Terminal.showHand(dealer, false);
        }

        whoWins(currentPlayer);

    }

    private boolean splitCondition() {

        return currentPlayer.getCard(0).getFace().equals(currentPlayer.getCard(1).getFace());
    }

    private void splitProcedure() {

        splitDeal();

        Terminal.sayName(splitHand1);
        hitOrStand(splitHand1);

        Terminal.sayName(splitHand2);
        hitOrStand(splitHand2);

        if (splitHand1.getScore() <= 21 || splitHand2.getScore() <= 21) {

            dealerHitOrStand();
            Terminal.showHand(dealer, false);
        }

        whoWins(splitHand1);
        whoWins(splitHand2);

    }

    private void splitDeal() {

        this.splitHand1 = new Player("First split hand");
        splitHand1.receivesCards(currentPlayer.getCard(0), deck.removeCard());

        this.splitHand2 = new Player("Second split hand");
        splitHand2.receivesCards(currentPlayer.getCard(1), deck.removeCard());

        Terminal.showHand(splitHand1, false);
        Terminal.showHand(splitHand2, false);

    }


    private void hitOrStand(Player player) {

        boolean hit = true;
        while (hit && player.getScore() < 21) {

            hit = Terminal.askCard();
            if (hit) player.receivesCards(deck.removeCard());
            Terminal.showHand(player, false);
        }

    }

    private void dealerHitOrStand() {

        while (dealer.getScore() <= 16) {

            dealer.receivesCards(deck.removeCard());
        }

    }

    private void whoWins(Player player) {

        if (player.getScore() > 21) {

            currentPlayer.receivesBet(-1);
            Terminal.lose(player);

        } else if (player.getScore() > dealer.getScore() || dealer.getScore() > 21) {

            if (player.getScore() == 21) {
                currentPlayer.receivesBet(2);
            } else {
                currentPlayer.receivesBet(1);
            }

            Terminal.win(player);

        } else if (player.getScore() == dealer.getScore()) {

            currentPlayer.receivesBet(0);
            Terminal.draw(player);

        } else {

            currentPlayer.receivesBet(-1);
            Terminal.lose(player);

        }

    }
}
