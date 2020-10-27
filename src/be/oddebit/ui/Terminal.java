package be.oddebit.ui;

import be.oddebit.objects.Hand;
import be.oddebit.objects.BetStack;

import java.util.Scanner;

public class Terminal {

    static Scanner scanner = new Scanner(System.in);

    public static void sayNewGame() {
        System.out.println("\n\n--- NEW GAME ---");
    }

    public static int askBet(BetStack stack) {

        System.out.println("Your stack : " + stack.getStack());
        System.out.println("What is your bet ? (1 - 500)");
        int input = scanner.nextInt();

        while (input > stack.getStack()) {

            System.out.println("Current stack : " + stack.getStack() + ". Do not bet more.");
            input = scanner.nextInt();
        }

        while (0 > input || input > 500) {

            System.out.println("Please bet between 1 and 500.");
            input = scanner.nextInt();
        }

        return input;

    }

    public static boolean askRestart() {

        System.out.println("Press '1' to restart.");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void sayDeal() {

        System.out.println("\n- Deal -");
    }

    public static void showHand(Hand hand, boolean firstOnly) {

        if (firstOnly) {
            System.out.println(hand.getOwner() + " : [" + hand.getCard(0).getFace() + ", X]");
        } else {
            System.out.println(hand.getOwner() + " : " + hand.getHand() + " = " + hand.getScore());
        }
    }

    public static boolean askSplit() {

        System.out.println("Split? Yes (1) or No (2)?");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void sayName(Hand hand) {

        System.out.println(hand.getOwner());
    }

    public static boolean askCard() {

        System.out.println("Hit (1) or Stand(2) ?");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void lose(Hand hand) {
        System.out.println(hand.getOwner() + " loses.");
    }

    public static void draw(Hand hand) {
        System.out.println("It is a stand-off for " + hand.getOwner() + ".");
    }

    public static void win(Hand hand) {
        System.out.println(hand.getOwner() + " wins.");
    }

    public static void blackJack(Hand hand) {
        System.out.println("Black Jack for " + hand.getOwner() + "!");
    }

}
