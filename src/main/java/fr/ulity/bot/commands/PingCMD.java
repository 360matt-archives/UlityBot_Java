package fr.ulity.bot.commands;

import fr.ulity.bot.api.CommandBuilder;

public class PingCMD extends CommandBuilder {
    public PingCMD() {
        name = "ping";
        level = Level.OWNER;
        dm = true;
        cooldown = 5;

    }

    @Override
    public void run() {
        reply("Pong !");

    }
}
