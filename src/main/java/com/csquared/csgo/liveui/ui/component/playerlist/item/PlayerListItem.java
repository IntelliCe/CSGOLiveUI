package com.csquared.csgo.liveui.ui.component.playerlist.item;

import com.csquared.csgo.liveui.ui.UIHelper;
import com.csquared.csgo.liveui.ui.component.Side;
import com.csquared.csgo.liveui.ui.component.utilityfield.UtilityField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import uk.oczadly.karl.csgsi.state.PlayerState;
import uk.oczadly.karl.csgsi.state.PlayerState.WeaponDetails;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class PlayerListItem extends Pane {
    @FXML private ImageView imgWeapon;
    @FXML private Rectangle rectBackground, rectHealth, rectSelected;
    @FXML private Label lbPlayerName, lbHealth, lbKill, lbDeath;
    @FXML protected UtilityField utilityField;

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

    public void setHealth(int health) {
        lbHealth.setText(Integer.toString(health));
        onHealthChange(health);
    }

    public void setArmor(boolean hasHelmet, int armor) {

    }

    public void setInventory(List<WeaponDetails> inventory) {
        utilityField.setUtilities(inventory);
    }

    abstract void switchSide(Side side);

    abstract void onHealthChange(int health);
}
