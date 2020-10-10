package com.csquared.csgo.liveui.core;

import uk.oczadly.karl.csgsi.state.PlayerState;

import java.util.List;
import java.util.Map;

public interface GSIActivityListener {
    // server
    void onConnect();

    // tick
    void onTick(int handlerTime);

    // spectating player
    void onSpectatingPlayerChange(PlayerState playerState);
    void onSpectatingPlayerHealthChange(int health);
    void onSpectatingPlayerArmorChange(int armor);
    void onSpectatingPlayerHelmetChange(boolean hasHelmet);
    void onSpectatingPlayerAmmoChange(int ammoClip, int ammoReserve);
    void onSpectatingPlayerUtilitiesChange(List<PlayerState.WeaponDetails> utilities);
    void onSpectatingPlayerStatsChange(int k, int a, int d);

    // player list
    void onPlayerListChange(Map<String, PlayerState> allPlayerStates);
}
