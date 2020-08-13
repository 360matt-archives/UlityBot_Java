package fr.ulity.bot.api;

import de.leonhard.storage.Json;
import fr.ulity.bot.MainDiscordApi;

import java.io.File;

public class Data extends Json {
    public Data () {
        super(new File(MainDiscordApi.path.getPath() + "/data/data.json"));
        new DefaultConfig();
    }

    public Data (String name) {
        super(new File(MainDiscordApi.path.getPath() + "/" + name + ".json"));

        if (name.equals("data"))
            new DefaultConfig();
    }

    public Data (String name, String path) {
        super(new File(MainDiscordApi.path.getPath() + "/" + path + "/" + name + ".json"));

        if (name.equals("data") && path.equals(""))
            new DefaultConfig();
    }

    public Data (File file) {
        super(file);
    }




}
