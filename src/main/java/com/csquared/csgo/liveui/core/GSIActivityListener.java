package com.csquared.csgo.liveui.core;

import uk.oczadly.karl.csgsi.state.PlayerState;

import java.util.List;

public interface GSIActivityListener {
    // server
    void onConnect();

    void onTick(int handlerTime);

    // spectating player
    void onSpectatingPlayerChange(PlayerState playerState);
    void onSpectatingPlayerHealthChange(int health);
    void onSpectatingPlayerArmorChange(int armor);
    void onSpectatingPlayerHelmetChange(boolean hasHelmet);
    void onSpectatingPlayerAmmoChange(int ammoClip, int ammoReserve);
    void onSpectatingPlayerUtilitiesChange(List<PlayerState.WeaponDetails> utilities);
    void onSpectatingPlayerStatsChange(int k, int a, int d);
}
