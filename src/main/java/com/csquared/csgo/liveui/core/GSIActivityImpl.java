package com.csquared.csgo.liveui.core;

import com.csquared.logger.Logger;

public class GSIActivityImpl implements GSIActivityListener {
    @Override
    public void onConnect() {
        Logger.d("SERVER", "Game client connected to the GSI server.");
    }

    @Override
    public void onMapRoundChange(int round) {

    }

    @Override
    public void onPlayerListChange() {

    }

    @Override
    public void onObservingPlayerChange() {

    }

    @Override
    public void onPlayerHealthChange() {

    }

    @Override
    public void onPlayerArmorChange() {

    }

    @Override
    public void onPlayerMoneyChange() {

    }

    @Override
    public void onPlayerInventoryChange() {

    }
}
