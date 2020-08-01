package fr.ulity.bot.api;

import de.leonhard.storage.Yaml;

import java.io.File;
import java.nio.file.Paths;

public class Config extends Yaml {
    public Config() {
        super("config", String.valueOf(Paths.get("")));
        new DefaultConfig();
    }

    public Config(String name) {
        super(name, String.valueOf(Paths.get("")));

        if (name.equals("config"))
            new DefaultConfig();
    }

    public Config(String name, String path) {
        super(name, Paths.get("") + path);

        if (name.equals("config") && path.equals(""))
            new DefaultConfig();
    }

    public Config(File file) {
        super(file);
    }




}
