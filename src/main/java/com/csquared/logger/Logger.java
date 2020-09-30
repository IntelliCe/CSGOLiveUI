package com.csquared.logger;

public class Logger {
    public static final int PREFIX_LENGTH = 12;

    public static void i(String tag, String message) {
        out("[I/" + tag + "]", message);
    }

    public static void d(String tag, String message) {
        out("[D/" + tag + "]", message);
    }

    public static void dWithMillis(String tag, String message) {
        out("[D/" + tag + "]", System.currentTimeMillis() + " | " + message);
    }

    public static void e(String tag, String message) {
        out("[E/" + tag + "]", message);
    }

    public static void out(String prefix, String body) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix).append(" ");
        for (int i = 0; i < PREFIX_LENGTH - prefix.length(); i++)
            builder.append(" ");
        builder.append(body);
        System.out.println(builder);
    }
}
