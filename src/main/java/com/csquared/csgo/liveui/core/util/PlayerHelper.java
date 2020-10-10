package com.csquared.csgo.liveui.core.util;

import uk.oczadly.karl.csgsi.state.PlayerState;

import java.util.Map;

public class PlayerHelper {

    public static boolean playerMapEqual(Map<String, PlayerState> v1, Map<String, PlayerState> v2) {
        if (v1 != null ^ v2 != null) return false;
        if (v1 == null) return true;
        for (String key : v1.keySet()) {
            if (v2.get(key) == null) {
                return false;
            } else {
                v2.remove(key);
            }
        }
        return v2.isEmpty();
    }
}
