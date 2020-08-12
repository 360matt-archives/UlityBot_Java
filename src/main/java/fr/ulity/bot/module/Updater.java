package fr.ulity.bot.module;

import fr.ulity.bot.api.Lang;
import fr.ulity.bot.utils.GetContentOfFile;
import fr.ulity.bot.utils.ReadJsonFromURL;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater {
    public static void update () {
        try {
            JSONObject json = ReadJsonFromURL.readJsonFromUrl("https://api.github.com/repos/360matt/UlityBot_Java/releases/latest");

            String currentVersion = GetContentOfFile.get(new File("version"));
            if (!currentVersion.equals(json.getString("tag_name"))) {
                URL website = new URL(json.getJSONArray("assets").getJSONObject(0).getString("browser_download_url"));
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream("bot.jar");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

                System.out.println("##\n" +
                        "##  |\n" +
                        "##  |\n" +
                        "##  | " + Lang.get("internal.updated") + "\n" +
                        "##  |\n" +
                        "##  |\n" +
                        "##  |");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(Lang.get("internal.update_failed"));
        }
    }
}
