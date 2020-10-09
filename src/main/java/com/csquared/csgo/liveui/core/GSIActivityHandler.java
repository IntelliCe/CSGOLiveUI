package com.csquared.csgo.liveui.core;

import com.csquared.logger.Logger;
import com.google.gson.Gson;
import uk.oczadly.karl.csgsi.state.GameState;
import uk.oczadly.karl.csgsi.state.MapState;
import uk.oczadly.karl.csgsi.state.PlayerState;
import uk.oczadly.karl.csgsi.state.PlayerState.PlayerStateDetails;
import uk.oczadly.karl.csgsi.state.PlayerState.WeaponDetails;
import uk.oczadly.karl.csgsi.state.PlayerState.PlayerMatchStats;

public class GSIActivityHandler {
    private GSIActivityListener listener;
    private long handlerTime;

    private boolean isFirstState = true;
    private GameState last;

    public GSIActivityHandler(GSIActivityListener listener) {
        this.listener = listener;
    }

    public void handle(GameState state) {
        handleSync(state);
    }

    private void handleSync(GameState state) {
        listener.onTick((int) handlerTime);

        long startHandlingTime = System.currentTimeMillis();

        if (isFirstState) {
            listener.onConnect();
            isFirstState = false;
            last = state;
            return;
        }
        PlayerState playerState = state.getPlayerState();
        if (playerState != null) {
            if (last.getPlayerState() == null) {
                listener.onSpectatingPlayerChange(playerState);
            } else if (!playerState.getSteamId().equals(last.getPlayerState().getSteamId())) {
                listener.onSpectatingPlayerChange(playerState);
            } else {
                // PlayerStateDetails related
                // onSpectatingPlayerXXXChange: health, armor, helmet
                PlayerStateDetails details = playerState.getState();
                PlayerStateDetails lastDetails = last.getPlayerState().getState();
                if (details != null) {
                    if (lastDetails != null) {
                        if (details.getHealth() != lastDetails.getHealth()) {
                            listener.onSpectatingPlayerHealthChange(details.getHealth());
                        }
                        if (details.getArmor() != lastDetails.getArmor()) {
                            listener.onSpectatingPlayerArmorChange(details.getArmor());
                        }
                        if (details.hasHelmet() != lastDetails.hasHelmet()) {
                            listener.onSpectatingPlayerHelmetChange(details.hasHelmet());
                        }
                    } else {

                    }
                } else {

                }
                // Selected WeaponDetails related
                WeaponDetails weaponDetails = playerState.getSelectedWeapon();
                WeaponDetails lastWeaponDetails = last.getPlayerState().getSelectedWeapon();
                if (weaponDetails != null) {
                    if (lastWeaponDetails == null) {
                        listener.onSpectatingPlayerAmmoChange(weaponDetails.getAmmoClip(), weaponDetails.getAmmoReserve());
                    } else {
                        if (weaponDetails.getAmmoClip() != lastWeaponDetails.getAmmoClip() ||
                                weaponDetails.getAmmoReserve() != lastWeaponDetails.getAmmoReserve()) {
                            listener.onSpectatingPlayerAmmoChange(weaponDetails.getAmmoClip(), weaponDetails.getAmmoReserve());
                        }
                    }
                } else {
                    if (lastDetails != null) {
                        listener.onSpectatingPlayerAmmoChange(0, 0);
                    }
                }
                // PlayerMatchStats related
                PlayerMatchStats stats = playerState.getStatistics();
                Logger.d("PMS", new Gson().toJson(stats));
                PlayerMatchStats lastStats = last.getPlayerState().getStatistics();
                if (stats != null) {
                    if (lastStats == null) {
                        listener.onSpectatingPlayerStatsChange(stats.getKillCount(), stats.getAssistCount(), stats.getDeathCount());
                    } else {
                        if (stats.getKillCount() != lastStats.getKillCount() ||
                                stats.getAssistCount() != lastStats.getAssistCount() ||
                                stats.getDeathCount() != lastStats.getDeathCount()) {
                            listener.onSpectatingPlayerStatsChange(stats.getKillCount(), stats.getAssistCount(), stats.getDeathCount());
                        }
                    }
                } else {
                    if (lastStats != null) {
                        listener.onSpectatingPlayerStatsChange(0, 0, 0);
                    }
                }
            }
        } else {
            if (last.getPlayerState() != null) {
                Logger.d("HANDLER", "PlayerState turns to NULL.");
            }
        }

        handlerTime = System.currentTimeMillis() - startHandlingTime;
    }
}
