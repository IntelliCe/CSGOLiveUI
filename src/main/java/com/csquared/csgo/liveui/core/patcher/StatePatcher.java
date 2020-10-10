package com.csquared.csgo.liveui.core.patcher;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import uk.oczadly.karl.csgsi.state.PlayerState;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StatePatcher {
    private Map<String, PlayerPatcherState> patcherStates;

    public StatePatcher(@NotNull JsonObject object) {
        patcherStates = new HashMap<>();
        parse(object);
    }

    private void parse(@NotNull JsonObject object) {
        JsonObject allPlayers = object.getAsJsonObject("allplayers");
        if (allPlayers == null || allPlayers.size() == 0) {
            return;
        }
        for (String key : allPlayers.keySet()) {
            JsonObject player = allPlayers.getAsJsonObject(key);
            PlayerPatcherState state = new PlayerPatcherState();
            //JsonObject pla
            if (player.getAsJsonObject().get("defusekit") != null) {
                state.setHasDefuseKit(player.getAsBoolean());
            }
            //JsonElement weapons =
        }
    }

    public static class PlayerPatcherState {
        private boolean hasDefuseKit = false;
        private List<PlayerState.WeaponDetails> weaponsInventory;

        public boolean isHasDefuseKit() {
            return hasDefuseKit;
        }

        public void setHasDefuseKit(boolean hasDefuseKit) {
            this.hasDefuseKit = hasDefuseKit;
        }

        public List<PlayerState.WeaponDetails> getWeaponsInventory() {
            return weaponsInventory;
        }

        public void setWeaponsInventory(List<PlayerState.WeaponDetails> weaponsInventory) {
            this.weaponsInventory = weaponsInventory;
        }
    }
}
