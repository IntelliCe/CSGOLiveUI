package com.csquared.csgo.liveui.ui.component.playerlist.item;

import com.csquared.csgo.liveui.ui.component.Side;
import com.csquared.csgo.liveui.ui.component.utilityfield.UtilityField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

@SuppressWarnings("unused")
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

    @FXML
    private void initialize() {
        utilityField.setAlignment(UtilityField.Alignment.LEFT);
    }

    @Override
    public void switchSide(Side side) {

    }

    @Override
    void onHealthChange(int health) {

    }
}
