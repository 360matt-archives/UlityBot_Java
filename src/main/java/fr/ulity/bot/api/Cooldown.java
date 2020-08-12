package fr.ulity.bot.api;

import fr.ulity.bot.utils.Time;
import net.dv8tion.jda.api.entities.User;

import java.util.Date;

public class Cooldown {
    private UserData userdata;

    public Cooldown (User user) {
        try {
            userdata = new UserData(user.getIdLong());
        } catch (Exception ignored) { }
    }

    public void applique (String subject, Time time) {
        userdata.set("cooldown." + subject + ".date", new Date().getTime());
        userdata.set("cooldown." + subject + ".seconds", time.seconds);
    }

    public Time getLeft (String subject) {
        try {
            long expire = userdata.getLong("cooldown." + subject + ".date")
            + (userdata.getLong("cooldown." + subject + ".seconds")*1000);

            long diff = expire - new Date().getTime();
            return new Time((diff < 0) ? 0 : (int) (diff/1000));

        } catch (Exception e) {
            return new Time(0);
        }
    }

}
