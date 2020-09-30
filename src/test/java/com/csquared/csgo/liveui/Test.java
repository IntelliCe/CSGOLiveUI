package com.csquared.csgo.liveui;

import com.csquared.logger.Logger;
import javafx.scene.layout.Pane;
import uk.oczadly.karl.csgsi.GSIObserver;
import uk.oczadly.karl.csgsi.GSIServer;
import uk.oczadly.karl.csgsi.config.GSIConfig;
import uk.oczadly.karl.csgsi.config.SteamDirectoryException;
import uk.oczadly.karl.csgsi.config.SteamUtils;
import uk.oczadly.karl.csgsi.state.PlayerState;
import uk.oczadly.karl.csgsi.state.components.DeserializedEnum;
import uk.oczadly.karl.csgsi.state.components.Weapon;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        GSIConfig config = new GSIConfig(1337)
                .setDescription("Test service for CSGO GSI")
                .setTimeoutPeriod(5.0)
                .setBufferPeriod(0.1)
                .setHeartbeatPeriod(60.0)
                .setThrottlePeriod(0.1)
                .setAuthToken("password", "Q79v5tcxVQ8u")
                .setAllDataComponents();

        Logger.i("CFG", "Initializing CSGO GSI configuration file...");
        try {
            Path configPath = SteamUtils.locateCsgoConfigFolder();

            if (configPath != null) {
                Logger.i("CFG", "configPath = " + configPath);
                GSIConfig.createConfig(configPath, config, "test_csquared_gsi.cfg");
                Logger.i("CFG", "GSI configuration file is deployed successfully.");
            }
        } catch (SteamDirectoryException e) {
            Logger.e("CFG", "Could not locate Steam installation directory.");
        } catch (IOException e) {
            Logger.e("CFG", "I/O error: could not write config file.");
        }

        GSIObserver observer = (state, context) -> {
            Logger.d("OBS", "New state received from game client.");
            if (state.getPlayerState() != null) {
                String name = state.getPlayerState().getName();
                int health = state.getPlayerState().getState().getHealth();
                int slot = state.getPlayerState().getObserverSlot();
                DeserializedEnum<Weapon> weapon = state.getPlayerState().getSelectedWeapon().getWeapon();
                Logger.dWithMillis("STATE", "[" + slot + "] " + name + " | HP=" + health + " | Weapon=" + weapon.getRawString());
            }
            try {
                //Logger.d("OBS", "NullPtrTest: mapName=" + state.getMapState().getName());
                //Logger.d("OBS", "NullPtrTest: bombStatePlayerId=" + state.getBombState().getPlayerId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Map<String, String> authToken = new HashMap<>();
        authToken.put("password", "Q79v5tcxVQ8u");
        GSIServer server = new GSIServer(1337, authToken);
        server.registerObserver(observer);
        try {
            server.startServer();
        } catch (IOException e) {
            Logger.e("SERVER", "I/O error.");
        }
        Logger.i("SERVER", "Server started. Listening for state data stream.");
    }
}
