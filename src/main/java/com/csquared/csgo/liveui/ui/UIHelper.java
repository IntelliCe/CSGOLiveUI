package com.csquared.csgo.liveui.ui;

import javafx.scene.Node;

public class UIHelper {
    public static void invisible(Node... args) {
        for (Node arg : args) {
            arg.setVisible(false);
        }
    }

    public static void visible(Node... args) {
        for (Node arg : args) {
            arg.setVisible(true);
        }
    }
}
