package com.csquared.csgo.liveui.ui.component.utilityfield;

import com.csquared.csgo.liveui.ui.component.ImageVal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uk.oczadly.karl.csgsi.state.PlayerState;
import uk.oczadly.karl.csgsi.state.PlayerState.WeaponDetails;
import uk.oczadly.karl.csgsi.state.components.Weapon;

import java.util.List;

@SuppressWarnings("unused")
public class UtilityField extends Pane {
    public enum Alignment {
        LEFT, RIGHT
    }

    private List<WeaponDetails> utilities;
    private Alignment alignment;

    @FXML private ImageView slot1, slot2, slot3, slot4;
    private final ImageView[] slots = new ImageView[4];

    public UtilityField() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UtilityField.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        setStyle("-fx-background: transparent;");
        slots[0] = slot1;
        slots[1] = slot2;
        slots[2] = slot3;
        slots[3] = slot4;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
        validate();
    }

    public void setUtilities(List<WeaponDetails> utilities) {
        this.utilities = utilities;
        validate();
    }

    private void validate() {
        // display order: flash, decoy, smoke, incendiary, molotov, grenade
        if (utilities == null || utilities.size() == 0) {
            for (ImageView slot : slots) {
                slot.setImage(null);
            }
            return;
        }
        if (alignment == Alignment.LEFT) {
            int slot = 0;
            for (int i = 0; i < has(Weapon.FLASH_BANG); i++) {
                slots[slot].setImage(ImageVal.IC_FLASH_BANG);
                slot++;
            }
            if (has(Weapon.DECOY) == 1) {
                slots[slot].setImage(ImageVal.IC_DECOY);
                slot++;
            }
            if (has(Weapon.SMOKE_GRENADE) == 1) {
                slots[slot].setImage(ImageVal.IC_SMOKE);
                slot++;
            }
            if (has(Weapon.INC_GRENADE) == 1) {
                slots[slot].setImage(ImageVal.IC_INCENDIARY);
                slot++;
            }
            if (has(Weapon.MOLOTOV) == 1) {
                slots[slot].setImage(ImageVal.IC_MOLOTOV);
                slot++;
            }
            if (has(Weapon.HE_GRENADE) == 1) {
                slots[slot].setImage(ImageVal.IC_HE_GRENADE);
            }
        } else if (alignment == Alignment.RIGHT) {
            int slot = 3;
            for (int i = 0; i < has(Weapon.FLASH_BANG); i++) {
                slots[slot].setImage(ImageVal.IC_FLASH_BANG);
                slot--;
            }
            if (has(Weapon.HE_GRENADE) == 1) {
                slots[slot].setImage(ImageVal.IC_HE_GRENADE);
                slot--;
            }
            if (has(Weapon.MOLOTOV) == 1) {
                slots[slot].setImage(ImageVal.IC_MOLOTOV);
                slot--;
            }
            if (has(Weapon.INC_GRENADE) == 1) {
                slots[slot].setImage(ImageVal.IC_INCENDIARY);
                slot--;
            }
            if (has(Weapon.SMOKE_GRENADE) == 1) {
                slots[slot].setImage(ImageVal.IC_SMOKE);
                slot--;
            }
            if (has(Weapon.DECOY) == 1) {
                slots[slot].setImage(ImageVal.IC_DECOY);
            }
        }
    }

    private int has(Weapon w) {
        for (WeaponDetails details : utilities) {
            if (details.getWeapon().getEnum() == w) {
                return details.getAmmoReserve();
            }
        }
        return 0;
    }
}
