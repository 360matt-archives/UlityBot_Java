package fr.ulity.bot.commandCheckers;

import fr.ulity.bot.api.CommandBuilder;
import fr.ulity.bot.api.Lang;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CheckerDM {
    public static boolean check (MessageReceivedEvent event, CommandBuilder cmd) {
        if (!cmd.dm && event.getChannel().getType().equals(ChannelType.PRIVATE)) {
            Lang.prepare("error.dm_not_allowed").sendChannel(event.getChannel());
            return false;
        }
        return true;
    }
}
