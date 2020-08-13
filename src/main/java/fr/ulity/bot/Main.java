package fr.ulity.bot;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main (String[] args) throws LoginException, IOException, URISyntaxException  {
        MainDiscordApi.start(
                new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                        .toURI()).getParentFile()
        );
        /* start without path, in independant Bot mode */
    }
}
