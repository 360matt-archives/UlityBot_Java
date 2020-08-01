package fr.ulity.bot.commands;

import fr.ulity.bot.Main;
import fr.ulity.bot.api.CommandBuilder;

public class ReloadCMD extends CommandBuilder {
    public ReloadCMD () {
        name = "reload";
        level = Level.OWNER;
        dm = true;
        cooldown = 0;

    }

    @Override
    public void run() {
        Main.reload();
        reply("Reloaded !");

    }
}
