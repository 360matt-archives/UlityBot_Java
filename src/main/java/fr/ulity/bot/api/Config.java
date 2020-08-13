package fr.ulity.bot.api;

import de.leonhard.storage.Yaml;
import fr.ulity.bot.MainDiscordApi;

import java.io.File;

public class Config extends Yaml {
    public Config() {
        super(new File(MainDiscordApi.path.getPath() + "/config.yml"));
        new DefaultConfig();
    }

    public Config(String name) {
        super(new File(MainDiscordApi.path.getPath() + "/" + name + ".yml"));

        if (name.equals("config"))
            new DefaultConfig();
    }

    public Config(String name, String path) {
        super(new File(MainDiscordApi.path.getPath() + "/" + path + "/" + name + ".yml"));

        if (name.equals("config") && path.equals(""))
            new DefaultConfig();
    }

    public Config(File file) {
        super(file);
    }




}
