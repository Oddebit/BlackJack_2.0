package be.oddebit.objects;

public class BetStack {

    private int stack;
    private int bet;

    public BetStack(int stack) {

        this.stack = stack;
    }

    public int getStack() {

        return stack;
    }

    public void setBet(int bet) {

        this.bet = bet;
    }

    public void receivesBet(int factor) {

        this.stack += bet * factor;
    }


}
