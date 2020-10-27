package be.oddebit.ui;

import be.oddebit.objects.Player;

import java.util.Scanner;

public class Terminal {

    static Scanner scanner = new Scanner(System.in);

    public static void sayNewGame() {
        System.out.println("\n\n--- NEW GAME ---");
    }

    public static int askBet(Player player) {

        System.out.println("Your stack : " + player.getStack());
        System.out.println("What is your bet ? (1 - 500)");
        int input = scanner.nextInt();

        while (input > player.getStack()) {

            System.out.println("Current stack : " + player.getStack() + ". Do not bet more.");
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

    public static void showHand(Player player, boolean firstOnly) {

        if (firstOnly) {
            System.out.println(player.getName() + " : [" + player.getCard(0).getFace() + ", X]");
        } else {
            System.out.println(player.getName() + " : " + player.getHandFaces() + " = " + player.getScore());
        }
    }

    public static boolean askSplit() {

        System.out.println("Split? Yes (1) or No (2)?");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void sayName(Player player) {

        System.out.println(player.getName());
    }

    public static boolean askCard() {

        System.out.println("Hit (1) or Stand(2) ?");
        int input = scanner.nextInt();

        return input == 1;
    }

    public static void lose(Player player) {
        System.out.println(player.getName() + " loses.");
    }

    public static void draw(Player player) {
        System.out.println("It is a stand-off for " + player.getName() + ".");
    }

    public static void win(Player player) {
        System.out.println(player.getName() + " wins.");
    }

    public static void blackJack(Player player) {
        System.out.println("Black Jack for " + player.getName() + "!");
    }

}
