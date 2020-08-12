package fr.ulity.bot.commands;

import fr.ulity.bot.MainDiscordApi;
import fr.ulity.bot.api.CommandBuilder;
import fr.ulity.bot.api.Lang;
import fr.ulity.bot.utils.Time;

public class PingCMD extends CommandBuilder {
    public PingCMD() {
        name = "ping";
        level = Level.USER;
        dm = false;
        cooldown = new Time(3);

        aliases = new String[]{"latence"};

    }

    @Override
    public void run() {
        long ping = MainDiscordApi.jda.getGatewayPing();
        Lang.Prepared prepared;

        if (ping > 250)      prepared = Lang.prepare("commands.ping.bad");
        else if (ping > 150) prepared = Lang.prepare("commands.ping.fair");
        else                 prepared = Lang.prepare("commands.ping.well");

        prepared
                .variable("ping", String.valueOf(MainDiscordApi.jda.getGatewayPing()))
                .sendChannel(channel);

        cooldownApplique();
    }
}
