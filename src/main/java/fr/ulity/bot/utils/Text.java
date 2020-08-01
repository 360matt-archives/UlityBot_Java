package fr.ulity.bot.utils;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Text {
    public static String full (String[] args) {
        StringBuilder returned = new StringBuilder();
        for (String x : args)
            returned.append(x).append(" ");
        return (returned.toString().trim());
    }

    public static String full (String[] args, int indice) {
        return full(Arrays.copyOfRange(args, indice, args.length));
    }

    public static String convertEncodage (String str) {
        return new String(str.getBytes(), StandardCharsets.UTF_8);
    }

    public static String[] convertEncodage (String[] arr) {
        ArrayList<String> newArr = new ArrayList<String>();

        for (String x : arr)
            newArr.add(convertEncodage(x));

        return newArr.toArray(new String[0]);
    }

    public static List<String> convertEncodage (List<String> list) {
        ArrayList<String> newArr = new ArrayList<>();

        for (String x : list)
            newArr.add(convertEncodage(x));

        return newArr;
    }


}