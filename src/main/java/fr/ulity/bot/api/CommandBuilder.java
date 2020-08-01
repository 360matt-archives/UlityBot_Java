package fr.ulity.bot.api;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public abstract class CommandBuilder {
    public String name;
    public Level level = Level.USER;
    public boolean dm = false;
    public int cooldown = 0;
    public String[] aliases = new String[0];

    protected MessageChannel channel;
    protected User user;
    protected String[] args;

    public CommandBuilder () {

    }


    public enum Level {
        USER,
        MOD,
        ADMIN,
        OWNER
    }

    public abstract void run ();


    public void reply (String message) {
        channel.sendMessage(message).queue();
    }

}
