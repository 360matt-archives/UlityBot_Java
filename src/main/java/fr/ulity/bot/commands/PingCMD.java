package fr.ulity.bot.commands;

import fr.ulity.bot.api.CommandBuilder;

public class PingCMD extends CommandBuilder {
    public PingCMD() {
        name = "ping";
        level = Level.USER;
        dm = false;
        cooldown = 5;

        aliases = new String[]{"latence"};

    }

    @Override
    public void run() {
        reply("Pong !");

    }
}
