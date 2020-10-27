package be.oddebit.objects;

public class StackManagement {

    private int stack;
    private int bet;

    public StackManagement(int stack) {

        this.stack = stack;
    }

    public int getStack() {

        return stack;
    }

    public void setStack(int stack) {

        this.stack = stack;
    }

    public void setBet(int bet) {

        this.bet = bet;
    }

    public void receivesBet(int factor) {

        this.stack += bet * factor;
    }


}
