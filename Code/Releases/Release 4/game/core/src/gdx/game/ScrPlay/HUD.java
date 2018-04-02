package gdx.game.ScrPlay;

import gdx.game.commonclasses.Item;

public class HUD {

    //<editor-fold desc="Init">
    int nHealth;
    Item[] aritHotbar;
    //</editor-fold>

    HUD(int nHealth, Item[] aritHotbar){
        this.nHealth = nHealth;
        this.aritHotbar = aritHotbar;
    }
}
