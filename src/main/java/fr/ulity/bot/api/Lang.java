package fr.ulity.bot.api;



import fr.ulity.bot.MainDiscordApi;
import fr.ulity.bot.utils.ListingResources;
import fr.ulity.bot.utils.Text;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lang {
    public static HashMap<String, Config> langConfigs = new HashMap<>();
    public static String defaultLang = MainDiscordApi.config.getString("global.lang");

    private enum ReloadExpressions {
        FR("Les fichiers de langue ont été rechargés"),
        EN("Language files have been reloaded");

        private final String exp;
        ReloadExpressions(String exp) {
            this.exp = exp;
        }

        public String getString() {
            return exp;
        }
    }

    public static void reload () throws IOException, URISyntaxException {
        defaultLang = MainDiscordApi.config.getString("bot.lang");

        String path = ("fr/ulity/bot/languages/");
        for (String x : ListingResources.getResourceListing(Lang.class, path)) {
            Matcher m = Pattern.compile("(.*).yml").matcher(x);
            if (m.find()) {
                Config configLooped = new Config(m.group(1), "languages/");

                URL urlLooped = Lang.class.getClassLoader().getResource(path + x);

                if (urlLooped != null) {
                    InputStream input = new URL(urlLooped.toString()).openStream();
                    configLooped.addDefaultsFromInputStream(input);

                    langConfigs.put(m.group(1), configLooped);
                }
            }
        }

        try {
            System.out.println(ReloadExpressions.valueOf(defaultLang.toUpperCase()).getString());
        } catch (Exception e) {
            System.out.println(ReloadExpressions.EN.getString());
        }

    }



    private static Config getOptimalLang (String arg, String exp) {
        Config optimalConfig = null;
        boolean noChange = false;

        for (Map.Entry<String, Config> x : langConfigs.entrySet()) {
            if (x.getKey().equals(arg)) {
                if (x.getValue().contains(exp))
                    return x.getValue();
            } else if (x.getKey().equals(defaultLang)) {
                if (x.getValue().contains(exp)) {
                    optimalConfig = x.getValue();
                    noChange = true;
                }
            } else if (!noChange && x.getKey().equals("en")) {
                if (x.getValue().contains(exp)) {
                    optimalConfig = x.getValue();
                    noChange = true;
                }
            } else if (!noChange) {
                if (x.getValue().contains(exp))
                    optimalConfig = x.getValue();
            }
        }

        return optimalConfig;
    }

    public static class Prepared {
        public static HashMap<String, String> internalVariable = new HashMap<>();

        private final String exp;
        private final HashMap<String, String> vars = new HashMap<>();
        private String prefix = "";
        private String suffix = "";

        public Prepared (String exp) { this.exp = exp; }

        public Prepared prefix (String prefix) { this.prefix = prefix; return this; }
        public Prepared suffix (String suffix) { this.suffix = suffix; return this; }

        public Prepared variable (String name, String replacement) {
            vars.put(name, replacement);
            return this;
        }

        public void sendUser (User user) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage(getOutput()).queue());
        }
        public void sendChannel (MessageChannel channel) {
            channel.sendMessage(getOutput()).queue();
        }

        public String getOutput (String lang) {
            String output = Lang.get(lang, exp);
            for (Map.Entry<String,String> x : vars.entrySet())
                output = output.replaceAll("%" + x.getKey() + "%", x.getValue());
            for (Map.Entry<String,String> x : internalVariable.entrySet())
                output = output.replaceAll("\\{" + x.getKey() + "}", x.getValue());
            return prefix + output + suffix;
        }

        public String getOutput () { return getOutput(defaultLang); }
    }



    /* API use */

    public static Prepared prepare (String exp) { return new Prepared(exp); }

    public static String get (String lang, String exp){
        Config langFile = getOptimalLang(lang, exp);
        return (langFile != null) ? Text.convertEncodage(langFile.getString(exp)) : "";
    }
    public static String get (String exp){
        return get(defaultLang, exp);
    }

    public static int getInt (String lang, String exp) {
        Config langFile = getOptimalLang(lang, exp);
        return (langFile != null) ? langFile.getInt(exp) : 0;
    }
    public static int getInt (String exp) {
        return getInt(defaultLang, exp);
    }

    public static String[] getStringArray (String lang, String exp) {
        Config langFile = getOptimalLang(lang, exp);
        return (langFile != null) ? Text.convertEncodage(langFile.getList(exp).toArray(new String[0])) : new String[0];
    }
    public static String[] getStringArray (String exp) {
        return getStringArray(defaultLang, exp);
    }

    public static boolean getBoolean (String lang, String exp) {
        Config langFile = getOptimalLang(lang, exp);
        return (langFile != null) && langFile.getBoolean(exp);
    }
    public static boolean getBoolean (String exp) {
        return getBoolean(defaultLang, exp);
    }

}