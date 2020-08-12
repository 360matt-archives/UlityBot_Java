package fr.ulity.bot.commandCheckers;

import fr.ulity.bot.api.CommandBuilder;
import fr.ulity.bot.api.Lang;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CheckerCooldow {
    public static boolean check (MessageReceivedEvent event, CommandBuilder cmd) {
        if (!cmd.cooldownLeft().isEmpty()) {
            Lang.prepare("error.cooldown_not_ended")
                    .variable("left", cmd.cooldownLeft().text)
                    .variable("command", cmd.name)
                    .sendChannel(event.getChannel());
            return false;
        } else return true;
    }
}
