package fr.ulity.bot.api;

public class UserData extends Data {
    public <T> UserData (T id) {
        super(String.valueOf(id), "data/users");
    }
}
