package com.csquared.csgo.liveui.ui.component.playerlist;

import com.csquared.csgo.liveui.ui.component.playerlist.item.PlayerListItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class PlayerList extends Pane {
    @FXML private PlayerListItem playerItem1, playerItem2, playerItem3, playerItem4, playerItem5,
            playerItem6, playerItem7, playerItem8, playerItem9, playerItem0;

    private Map<Integer, PlayerListItem> items;

    public PlayerList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerList.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // gathering list items
        items = new HashMap<>();
        items.put(1, playerItem1);
        items.put(2, playerItem2);
        items.put(3, playerItem3);
        items.put(4, playerItem4);
        items.put(5, playerItem5);
        items.put(6, playerItem6);
        items.put(7, playerItem7);
        items.put(8, playerItem8);
        items.put(9, playerItem9);
        items.put(0, playerItem0);
    }
}
