package fr.ulity.bot;

import fr.ulity.bot.api.CommandManager;
import fr.ulity.bot.api.Config;
import fr.ulity.bot.api.Lang;
import fr.ulity.bot.commands.PingCMD;
import fr.ulity.bot.commands.ReloadCMD;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static Config config = new Config();
    public static JDA jda;

    public static void main (String[] args) throws LoginException, IOException, URISyntaxException {
        Lang.reload();


        // register commands
        CommandManager.register(new PingCMD());
        CommandManager.register(new ReloadCMD());


        jda = JDABuilder.createDefault(config.getString("bot.token")).build();
        reload();

    }

    public static void reload () {
        jda.removeEventListener(new CommandManager());
        jda.addEventListener(new CommandManager());
    }

}
