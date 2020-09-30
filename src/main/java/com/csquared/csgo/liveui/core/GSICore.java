package com.csquared.csgo.liveui.core;

import com.csquared.logger.Logger;
import uk.oczadly.karl.csgsi.GSIObserver;
import uk.oczadly.karl.csgsi.GSIServer;
import uk.oczadly.karl.csgsi.config.GSIConfig;
import uk.oczadly.karl.csgsi.config.SteamDirectoryException;
import uk.oczadly.karl.csgsi.config.SteamUtils;
import uk.oczadly.karl.csgsi.state.components.DeserializedEnum;
import uk.oczadly.karl.csgsi.state.components.Weapon;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GSICore {

    public GSICore() {
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

        GSIActivityHandler activityHandler = new GSIActivityHandler(new GSIActivityImpl());
        GSIObserver observer = (state, context) -> {
            activityHandler.handle(state);
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
