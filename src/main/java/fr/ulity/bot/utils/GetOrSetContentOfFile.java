package fr.ulity.bot.utils;

import org.apache.commons.io.FileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetOrSetContentOfFile {
    public static String get (File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        return stringBuilder.toString();
    }

    public static void set(File file, String content) {
        try {
            FileUtils.writeStringToFile(file, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

