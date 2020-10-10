package com.csquared.csgo.liveui.ui.component;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class ColorVal {
    // color values for Scoreboard
    public final static Color SCOREBOARD_SCORE_T = Color.web("#e56647");
    public final static Color SCOREBOARD_SCORE_CT = Color.web("#3f4072");
    public final static Color SCOREBOARD_FIREPOWER_T_LIGHT = Color.web("#f1aa15");
    public final static Color SCOREBOARD_FIREPOWER_T_DARK = Color.web("#e56648");
    public final static Color SCOREBOARD_FIREPOWER_CT_LIGHT = Color.web("#2d56a5");
    public final static Color SCOREBOARD_FIREPOWER_CT_DARK = Color.web("#3f4273");

    // color values for SpectatingPanel
    public final static LinearGradient SPECTATING_CT_BACKGROUND =
            new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#2d3586")), new Stop(1, Color.web("#3f96cc")));
    public final static LinearGradient SPECTATING_T_BACKGROUND =
            new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#e66e49")), new Stop(1, Color.web("#dfbb1c")));
}
