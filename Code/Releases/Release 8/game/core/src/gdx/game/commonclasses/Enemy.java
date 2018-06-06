package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author mattm
 */
public class Enemy extends SpriteDiscrete {

    //<editor-fold desc="Init">

    private int nMaxHealth, nMaxMana, nHealth, nMana;
    //</editor-fold>

    //Constuctor
    public Enemy(Texture txSheet_, double dX_, double dY_, double dVelLimit_, double dW_, double dH_,
                          int nMaxHealth_, int nMaxMana_, int nHealth_, int nMana_) {
        super(txSheet_,dX_,dY_,dVelLimit_,dW_,dH_,nMaxHealth_,nMaxMana_,nHealth_,nMana_);
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
        dVel = 0;
        dVelLimit = dVelLimit_;
        dSpeed = -4;

        nMana = nMana_;
        nMaxMana = nMaxMana_;
        nMaxHealth = nMaxHealth_;
        nHealth = nHealth_;
    }
    
    public void update(Tile[][] arTile_) {
        pickDirection(arTile_);
        move(arTile_);
    }
    public void pickDirection(Tile[][] arTile_) {
        if (Math.random() * 100 > 90) {
            dSpeed *= -1;
        } 
        if (Math.random() * 100 > 95) {
            jump();
        } 
    }
}
