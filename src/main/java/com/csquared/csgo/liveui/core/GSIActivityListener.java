package com.csquared.csgo.liveui.core;

public interface GSIActivityListener {
    void onConnect();

    void onMapRoundChange(int round);
    void onPlayerListChange();
    void onObservingPlayerChange();
    void onPlayerHealthChange();
    void onPlayerArmorChange();
    void onPlayerMoneyChange();
    void onPlayerInventoryChange();
}
