package fr.ulity.bot;

import fr.ulity.bot.api.CommandBuilder;
import fr.ulity.bot.api.CommandManager;
import fr.ulity.bot.api.Config;
import fr.ulity.bot.api.Lang;
import fr.ulity.bot.commands.PingCMD;
import fr.ulity.bot.module.Updater;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainDiscordApi {
    public static Config config = new Config();
    public static JDA jda;

    public static void start (String path) throws LoginException, IOException, URISyntaxException {
        Lang.reload();

        if (!path.equals(""))
            Updater.update();

        Config langConfig = Lang.langConfigs.get(Lang.defaultLang);

        for (String x : langConfig.singleLayerKeySet("internal_vars"))
            Lang.Prepared.internalVariable.put(x, langConfig.getString("internal_vars." + x));


        // register commands
        CommandManager.register(new PingCMD());


        jda = JDABuilder.createDefault(config.getString("bot.token")).build();
        jda.addEventListener(new CommandManager());
    }

    public static void registerChecker (Class checker) { CommandManager.checkers.add(checker); }
    public static void unregisterChecker (Class checker) { CommandManager.checkers.remove(checker); }
    public static void registerCommand (CommandBuilder cmd) { CommandManager.register(cmd); }
    public static void unregisterCommand (CommandBuilder cmd) { CommandManager.unregister(cmd); }

}
