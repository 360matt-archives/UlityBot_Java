package fr.ulity.bot.commandCheckers;

import fr.ulity.bot.Main;
import fr.ulity.bot.api.CommandBuilder;
import fr.ulity.bot.api.Lang;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CheckerLevel {

    public static boolean check (MessageReceivedEvent event, CommandBuilder cmd) {
        if (cmd.level.equals(CommandBuilder.Level.USER))
            return true;

        if (cmd.level.equals(CommandBuilder.Level.OWNER)) {
            if (Main.config.getList("bot.owners").contains(event.getAuthor().getId()))
                return true;
            else {
                Lang.prepare("error.owner_only")
                        .variable("command", cmd.name)
                        .sendChannel(event.getChannel());
                return false;
            }
        }

        if (event.getChannel().getType().equals(ChannelType.TEXT)) {
            if (cmd.level.equals(CommandBuilder.Level.MOD)) {
                if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE, Permission.BAN_MEMBERS))
                    return true;
            } else if (cmd.level.equals(CommandBuilder.Level.ADMIN)) {
                if (event.getMember().hasPermission(Permission.ADMINISTRATOR))
                    return true;
            }
        }

        Lang.prepare("error.noperm")
                .variable("command", cmd.name)
                .sendChannel(event.getChannel());
        return false;
    }

}
