package com.csquared.csgo.liveui.ui.component.spectatingpanel;

import com.csquared.csgo.liveui.ui.component.ColorVal;
import com.csquared.csgo.liveui.ui.component.ImageVal;
import com.csquared.csgo.liveui.ui.component.utilityfield.UtilityField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import uk.oczadly.karl.csgsi.state.PlayerState;
import uk.oczadly.karl.csgsi.state.components.DeserializedEnum;
import uk.oczadly.karl.csgsi.state.components.Team;

import java.util.List;

@SuppressWarnings("unused")
public class SpectatingPanel extends Pane {
    @FXML private Label lbPlayerName, lbStats, lbADR, lbAmmo, lbHealth, lbArmor;
    @FXML private ImageView imgAvatar, imgArmor;
    @FXML private Rectangle rectBackground;
    @FXML private UtilityField utilityField;

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

    @FXML
    private void initialize() {
        utilityField.setAlignment(UtilityField.Alignment.RIGHT);
    }

    public void setPlayer(PlayerState playerState) {
        Platform.runLater(() -> {
            lbPlayerName.setText(playerState.getName());
            if (playerState.getTeam().getEnum() == Team.COUNTER_TERRORIST) {
                rectBackground.setFill(ColorVal.SPECTATING_CT_BACKGROUND);
            } else {
                rectBackground.setFill(ColorVal.SPECTATING_T_BACKGROUND);
            }
        });
        setHealth(playerState.getState().getHealth());
        setArmor(playerState.getState().getArmor());
        setHasHelmet(playerState.getState().hasHelmet());
        setAmmo(playerState.getSelectedWeapon().getAmmoClip(), playerState.getSelectedWeapon().getAmmoReserve());
        setStats(playerState.getStatistics().getKillCount(), playerState.getStatistics().getAssistCount(), playerState.getStatistics().getDeathCount());
    }

    public void setPlayerName(String name) {
        lbPlayerName.setText(name);
    }

    public void setPlayerAvatar(Image image) {
        imgAvatar.setImage(image);
    }

    public void setSide(DeserializedEnum<Team> team) {
        try {
            if (team.getEnum() == Team.COUNTER_TERRORIST) {
                rectBackground.setFill(ColorVal.SPECTATING_CT_BACKGROUND);
            } else {
                rectBackground.setFill(ColorVal.SPECTATING_T_BACKGROUND);
            }
        } catch (Exception e) {

        }
    }

    public void setHealth(int health) {
        lbHealth.setText(Integer.toString(health));
    }

    public void setArmor(int armor) {
        lbArmor.setText(Integer.toString(armor));
    }

    public void setHasHelmet(boolean hasHelmet) {
        if (hasHelmet) {
            imgArmor.setImage(ImageVal.IC_HELMET);
        } else {
            imgArmor.setImage(ImageVal.IC_KEVLAR);
        }
    }

    public void setAmmo(int ammoClip, int ammoReserve) {
        lbAmmo.setText(String.format("%s/%s", ammoClip, ammoReserve));
    }

    public void setStats(int k, int a, int d) {
        lbStats.setText(String.format("K  %d  /  A  %d  /  D  %d", k, a, d));
    }

    public void setUtilityField(List<PlayerState.WeaponDetails> utils) {
        utilityField.setUtilities(utils);
    }
}
