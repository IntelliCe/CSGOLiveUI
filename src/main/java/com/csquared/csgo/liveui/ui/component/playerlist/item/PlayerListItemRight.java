package com.csquared.csgo.liveui.ui.component.playerlist.item;

import javafx.fxml.FXMLLoader;

public class PlayerListItemRight extends PlayerListItem {

    public PlayerListItemRight() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerListItemRight.fxml"));
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
