package com.csquared.csgo.liveui.ui.component.spectatingpanel;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class SpectatingPanel extends Pane {

    public SpectatingPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SpectatingPanel.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
