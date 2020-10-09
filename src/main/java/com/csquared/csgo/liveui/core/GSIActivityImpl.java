package com.csquared.csgo.liveui.core;

import com.csquared.csgo.liveui.ui.component.ColorVal;
import com.csquared.csgo.liveui.ui.component.spectatingpanel.SpectatingPanel;
import com.csquared.logger.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import uk.oczadly.karl.csgsi.state.PlayerState;
import uk.oczadly.karl.csgsi.state.components.Team;

public class GSIActivityImpl implements GSIActivityListener {
    private long lastTickMillis = 0;

    private Label debugLabel;
    private SpectatingPanel spectatingPanel;

    public GSIActivityImpl debugLabel(Label debugLabel) {
        this.debugLabel = debugLabel;
        return this;
    }

    public GSIActivityImpl spectatingPanel(SpectatingPanel spectatingPanel) {
        this.spectatingPanel = spectatingPanel;
        return this;
    }

    @Override
    public void onConnect() {
        Logger.d("SERVER", "Game client connected to the GSI server.");
        lastTickMillis = System.currentTimeMillis();
    }

    @Override
    public void onTick(int handlerTime) {
        float tickRate = 1000.0f / (System.currentTimeMillis() - lastTickMillis);
        lastTickMillis = System.currentTimeMillis();
        String debugInfo =
                "Connected\n" +
                String.format("tickrate: %.2f/s\n", tickRate) +
                String.format("handler: %dms", handlerTime);
        Platform.runLater(() -> debugLabel.setText(debugInfo));
    }

    @Override
    public void onSpectatingPlayerChange(PlayerState playerState) {
        Platform.runLater(() -> {
            spectatingPanel.setPlayerName(playerState.getName());
            //spectatingPanel.setPlayerAvatar(null);
            spectatingPanel.setSide(playerState.getTeam());

            if (playerState.getState() != null) {
                onSpectatingPlayerHealthChange(playerState.getState().getHealth());
                onSpectatingPlayerArmorChange(playerState.getState().getArmor());
                onSpectatingPlayerHelmetChange(playerState.getState().hasHelmet());
            }
            if (playerState.getSelectedWeapon() != null) {
                onSpectatingPlayerAmmoChange(playerState.getSelectedWeapon().getAmmoClip(), playerState.getSelectedWeapon().getAmmoReserve());
            }
            if (playerState.getStatistics() != null) {
                onSpectatingPlayerStatsChange(playerState.getStatistics().getKillCount(), playerState.getStatistics().getAssistCount(), playerState.getStatistics().getDeathCount());
            }
        });
    }

    @Override
    public void onSpectatingPlayerHealthChange(int health) {
        Platform.runLater(() -> spectatingPanel.setHealth(health));
    }

    @Override
    public void onSpectatingPlayerArmorChange(int armor) {
        Platform.runLater(() -> spectatingPanel.setArmor(armor));
    }

    @Override
    public void onSpectatingPlayerHelmetChange(boolean hasHelmet) {
        Platform.runLater(() -> spectatingPanel.setHasHelmet(hasHelmet));
    }

    @Override
    public void onSpectatingPlayerAmmoChange(int ammoClip, int ammoReserve) {
        Platform.runLater(() -> spectatingPanel.setAmmo(ammoClip, ammoReserve));
    }

    @Override
    public void onSpectatingPlayerStatsChange(int k, int a, int d) {
        Platform.runLater(() -> spectatingPanel.setStats(k, a, d));
    }
}
