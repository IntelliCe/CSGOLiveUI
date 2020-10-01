package com.csquared.csgo.liveui.ui.component;

import javafx.scene.image.Image;

public class ImageVal {
    // image values for utilities
    public static final Image IC_FLASH_BANG = resource("ic_flashbang.png");
    public static final Image IC_SMOKE = resource("ic_smoke.png");
    public static final Image IC_DECOY = resource("ic_decoy.png");
    public static final Image IC_HE_GRENADE = resource("ic_hegrenade.png");
    public static final Image IC_MOLOTOV = resource("ic_molotov.png");
    public static final Image IC_INCENDIARY = resource("ic_incendiary");

    private static Image resource(String name) {
        return new Image(String.valueOf(ImageVal.class.getClassLoader().getResource(name)));
    }
}
