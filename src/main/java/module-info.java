module CSGOLiveUI {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires uk.oczadly.karl.csgogsi;
    requires kotlin.stdlib;

    exports com.csquared.csgo.liveui.ui.window;
    opens com.csquared.csgo.liveui.ui.window to javafx.fxml, javafx.graphics;

    exports com.csquared.csgo.liveui.ui.component;

    exports com.csquared.csgo.liveui.ui.component.scoreboard;
    opens com.csquared.csgo.liveui.ui.component.scoreboard to javafx.fxml;

    exports com.csquared.csgo.liveui.ui.component.playerlist;
    opens com.csquared.csgo.liveui.ui.component.playerlist to javafx.fxml;

    exports com.csquared.csgo.liveui.ui.component.playerlist.item;
    opens com.csquared.csgo.liveui.ui.component.playerlist.item to javafx.fxml;

    exports com.csquared.csgo.liveui.ui.component.utilityfield;
    opens com.csquared.csgo.liveui.ui.component.utilityfield to javafx.fxml;

    exports com.csquared.csgo.liveui.ui.component.sponsorslot;
    opens com.csquared.csgo.liveui.ui.component.sponsorslot to javafx.fxml;
}