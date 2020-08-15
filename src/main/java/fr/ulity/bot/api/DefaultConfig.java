package fr.ulity.bot.api;


import java.util.Arrays;

public class DefaultConfig {
    public static void make (Config config) {

        config.setHeader("  -->   UlityBot, in Java", "  by 360matt");
        config.setDefault("bot.lang", "fr");
        config.setDefault("bot.token", "super secret");
        config.setDefault("bot.prefix", "u!");
        config.setDefault("bot.owners", Arrays.asList("X-id-X", "X-id-X"));


    }
}
