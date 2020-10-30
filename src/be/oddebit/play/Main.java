package be.oddebit.play;

import be.oddebit.objects.Player;
import be.oddebit.ui.Terminal;

public class Main {

    public static void main(String[] args) {

        Terminal.sayWelcome();
        Player player = new Player(Terminal.askName());
        player.setStack(Terminal.askStack(player));

        new Game(player);
    }
}
 