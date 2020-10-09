package com.csquared.csgo.liveui.ui.component.sponsorslot;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class SponsorSlot extends Pane {
    private static final int SWAP_INTERVAL = 5000;

    private List<Image> images;
    private FadeTransition transitionIn, transitionOut;

    @FXML private ImageView imageView;

    public SponsorSlot() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SponsorSlot.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            transitionOut.play();
        }
    };

    @FXML
    private void initialize() {
        transitionIn = new FadeTransition(Duration.millis(500), imageView);
        transitionIn.setFromValue(0.0);
        transitionIn.setToValue(1.0);
        transitionOut = new FadeTransition(Duration.millis(500), imageView);
        transitionOut.setFromValue(1.0);
        transitionOut.setToValue(0.0);
        transitionOut.setOnFinished(actionEvent -> {
            Platform.runLater(() -> imageView.setImage(nextImage()));
            transitionIn.play();
        });

        File path = new File("D:\\Program Files (x86)\\CSGOLiveUI\\sponsor");
        File[] files = path.listFiles();
        images = new ArrayList<>();
        for (File file : files) {
            if (file.getName().endsWith(".png")) {
                images.add(new Image(file.toURI().toString()));
            }
        }
        imageView.setImage(nextImage());
        new Timer().schedule(timerTask, SWAP_INTERVAL, SWAP_INTERVAL);
    }

    private int cursor = -1;
    private Image nextImage() {
        if (images.size() > 0) {
            cursor++;
            if (cursor < images.size()) {
                return images.get(cursor);
            }
            cursor = 0;
            return images.get(cursor);
        }
        return null;
    }
}
