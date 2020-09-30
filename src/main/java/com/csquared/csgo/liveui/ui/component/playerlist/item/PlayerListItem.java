package com.csquared.csgo.liveui.ui.component.playerlist.item;

import com.csquared.csgo.liveui.ui.UIHelper;
import com.csquared.csgo.liveui.ui.component.playerlist.PlayerList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("unused")
public abstract class PlayerListItem extends Pane {
    @FXML private ImageView imgWeapon;
    @FXML private Rectangle rectBackground, rectHealth, rectSelected;
    @FXML private Label lbPlayerName, lbHealth, lbKill, lbDeath;

    @FXML
    private void initialize() {
        rectSelected.setVisible(false);
    }

    public void setPlayer() {

    }

    public void select() {
        rectSelected.setVisible(true);
    }

    public void unselect() {
        rectSelected.setVisible(false);
    }

    abstract void switchSide();
}
