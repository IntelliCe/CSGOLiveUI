package com.csquared.csgo.liveui.core;

import com.csquared.csgo.liveui.ui.component.spectatingpanel.SpectatingPanel;
import com.csquared.logger.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import uk.oczadly.karl.csgsi.state.PlayerState;

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
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("tickrate:    %.2f/s", tickRate)).append("\n");
        builder.append(String.format("handler:     %dms", handlerTime));
        Platform.runLater(() -> debugLabel.setText(builder.toString()));
    }

    @Override
    public void onSpectatingPlayerChange(PlayerState playerState) {
        spectatingPanel.setPlayer(playerState);
    }

    @Override
    public void onSpectatingPlayerHealthChange(int health) {
        spectatingPanel.setHealth(health);
    }

    @Override
    public void onSpectatingPlayerArmorChange(int armor) {
        spectatingPanel.setArmor(armor);
    }

    @Override
    public void onSpectatingPlayerHelmetChange(boolean hasHelmet) {
        spectatingPanel.setHasHelmet(hasHelmet);
    }

    @Override
    public void onSpectatingPlayerAmmoChange(int ammoClip, int ammoReserve) {
        spectatingPanel.setAmmo(ammoClip, ammoReserve);
    }

    @Override
    public void onSpectatingPlayerStatsChange(int k, int a, int d) {
        spectatingPanel.setStats(k, a, d);
    }
}
