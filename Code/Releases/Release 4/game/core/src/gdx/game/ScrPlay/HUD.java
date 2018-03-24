package gdx.game.ScrPlay;

import gdx.game.commonclasses.Item;

public class HUD {

    int nHealth;
    Item[] hotbar;

    HUD(int nHealth, Item[] hotbar){
        this.nHealth = nHealth;
        this.hotbar = hotbar;
    }
}
