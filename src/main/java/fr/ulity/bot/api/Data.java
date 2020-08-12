package fr.ulity.bot.api;

import de.leonhard.storage.Json;

import java.io.File;
import java.nio.file.Paths;

public class Data extends Json {
    public Data () {
        super("data", String.valueOf(Paths.get("")));
        new DefaultConfig();
    }

    public Data (String name) {
        super(name, String.valueOf(Paths.get("")));

        if (name.equals("data"))
            new DefaultConfig();
    }

    public Data (String name, String path) {
        super(name, Paths.get("") + path);

        if (name.equals("data") && path.equals(""))
            new DefaultConfig();
    }

    public Data (File file) {
        super(file);
    }




}
