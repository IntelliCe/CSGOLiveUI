package com.csquared.csgo.liveui.ui.component.playerlist.item;

import javafx.fxml.FXMLLoader;

public class PlayerListItemLeft extends PlayerListItem {

    public PlayerListItemLeft() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerListItemLeft.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void switchSide() {

    }
}
