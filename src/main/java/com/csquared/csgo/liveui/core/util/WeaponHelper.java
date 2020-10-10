package com.csquared.csgo.liveui.core.util;

import uk.oczadly.karl.csgsi.state.PlayerState.WeaponDetails;
import uk.oczadly.karl.csgsi.state.components.Weapon;

import java.util.ArrayList;
import java.util.List;

public class WeaponHelper {

    public static WeaponDetails getPrimaryWeapon(List<WeaponDetails> list) {
        if (list == null) {
            return null;
        }
        for (WeaponDetails weapon : list) {
            Weapon.Type type = weapon.getWeaponType().getEnum();
            if (type == Weapon.Type.RIFLE || type == Weapon.Type.MACHINE_GUN || type == Weapon.Type.SNIPER_RIFLE ||
                    type == Weapon.Type.SUBMACHINE_GUN || type == Weapon.Type.SHOTGUN) {
                return weapon;
            }
        }
        for (WeaponDetails weapon : list) {
            Weapon.Type type = weapon.getWeaponType().getEnum();
            if (type == Weapon.Type.PISTOL) {
                return weapon;
            }
        }
        return null;
    }

    public static List<WeaponDetails> getUtilityList(List<WeaponDetails> list) {
        if (list == null) {
            return null;
        }
        List<WeaponDetails> utilities = new ArrayList<>();
        for (WeaponDetails weapon : list) {
            if (weapon.getWeaponType().getEnum() == Weapon.Type.GRENADE) {
                utilities.add(weapon);
            }
        }
        return utilities;
    }

    public static boolean weaponListEqual(List<WeaponDetails> v1, List<WeaponDetails> v2) {
        if (v1 != null ^ v2 != null) {
            return false;
        }
        if (v1 == null) {
            return true;
        }
        for (WeaponDetails weapon : v1) {
            WeaponDetails w = getWeaponFromList(v2, weapon);
            if (w == null) {
                return false;
            }
            v2.remove(w);
        }
        return v2.size() == 0;
    }

    private static WeaponDetails getWeaponFromList(List<WeaponDetails> list, WeaponDetails weapon) {
        if (list == null) {
            return null;
        }
        for (WeaponDetails w : list) {
            if (w.getWeapon().getEnum() == weapon.getWeapon().getEnum()) {
                return w;
            }
        }
        return null;
    }
}
