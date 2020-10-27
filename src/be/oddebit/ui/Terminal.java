package be.oddebit.ui;

import be.oddebit.objects.Hand;

import java.util.Scanner;

public class Terminal {

    public static boolean restart() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Press '1' to restart.");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void sayDeal() {

        System.out.println("\n--- Deal ---");
    }

    public static void showHand(Hand hand, boolean firstOnly) {

        if (firstOnly) {
            System.out.println(hand.getOwner() + " : [" + hand.getCard(0).getFace() + ", X]");
        } else {
            System.out.println(hand.getOwner() + " : " + hand.getHand() + " = " + hand.getScore());
        }
    }

    public static boolean askSplit() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Split? Yes (1) or No (2)?");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void saySplitHand(Hand hand) {

        System.out.println(hand.getOwner());
    }

    public static boolean askCard() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hit (1) or Stand(2) ?");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void lose(Hand hand) {
        System.out.println(hand.getOwner() + " loses.");
    }

    public static void draw(Hand hand) {
        System.out.println("It is a stand-off for " + hand + ".");
    }

    public static void win(Hand hand) {
        System.out.println(hand.getOwner() + " wins.");
    }

}
