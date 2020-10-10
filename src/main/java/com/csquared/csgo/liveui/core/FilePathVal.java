package com.csquared.csgo.liveui.core;

import java.io.File;

public class FilePathVal {

    public static File getMatchPreference() {
        return new File(appDataPath() + "\\match\\match.json");
    }

    public static File getMatchAsset(String asset) {
        return new File(appDataPath() + "\\match\\" + asset);
    }

    public static File getPlayerPreference() {
        return new File(appDataPath() + "\\player\\players.json");
    }

    public static File getPlayerAsset(String asset) {
        return new File(appDataPath() + "\\player\\" + asset);
    }

    private static String appDataPath() {
        return System.getenv("APPDATA") + "\\CSGOLiveUI";
    }
}