package fr.ulity.bot.api;

import fr.ulity.bot.utils.Time;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public abstract class CommandBuilder {
    public String name;
    public Level level = Level.USER;
    public boolean dm = false;
    public Time cooldown = new Time(0);
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

    public void cooldownApplique () {
        Cooldown cooldownObj = new Cooldown(user);
        cooldownObj.applique(name, cooldown);
    }

    public Time cooldownLeft () {
        Cooldown cooldownObj = new Cooldown(user);
        return cooldownObj.getLeft(name);
    }



}
