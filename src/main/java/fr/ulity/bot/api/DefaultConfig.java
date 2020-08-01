package fr.ulity.bot.api;

import de.leonhard.storage.Yaml;

import java.util.Arrays;

public class DefaultConfig extends Yaml {
    public DefaultConfig () {
        super("config", "");

        setHeader("  -->   UlityBot, in Java", "  by 360matt");
        setDefault("bot.lang", "fr");
        setDefault("bot.token", "super secret");
        setDefault("bot.prefix", "u!");
        setDefault("bot.owners", Arrays.asList("X-id-X", "X-id-X"));


    }
}
