package gdx.game.ScrPlay;

import gdx.game.commonclasses.Item;

public class HUD {

    int nHealth;
    Item[] aritHotbar;

    HUD(int nHealth, Item[] aritHotbar){
        this.nHealth = nHealth;
        this.aritHotbar = aritHotbar;
    }
}
