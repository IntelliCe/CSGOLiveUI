package com.csquared.csgo.liveui.core;

import uk.oczadly.karl.csgsi.state.GameState;
import uk.oczadly.karl.csgsi.state.MapState;

public class GSIActivityHandler {
    private GSIActivityListener listener;

    private boolean isFirstState = true;
    private GameState last;

    public GSIActivityHandler(GSIActivityListener listener) {
        this.listener = listener;
    }

    public void handle(GameState state) {
        handleSync(state);
    }

    private void handleSync(GameState state) {
        if (isFirstState) {
            listener.onConnect();
            isFirstState = false;
            last = state;
            return;
        }
        MapState mapState = state.getMapState();
        if (mapState.getRoundNumber() != last.getMapState().getRoundNumber()) {
            listener.onMapRoundChange(mapState.getRoundNumber());
        }
    }
}
