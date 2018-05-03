package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SpriteHero extends SpriteDiscrete {

    //<editor-fold desc="Init">
    private int nMaxHealth, nMaxMana, nHealth, nMana;
    //</editor-fold>

    //Constuctor
    public SpriteHero(Texture txSheet_, double dX_, double dY_, double dVelLimit_, double dW_, double dH_,
            int nMaxHealth_, int nMaxMana_, int nHealth_, int nMana_) {
        super(txSheet_, dX_, dY_, dVelLimit_, dW_, dH_, nMaxHealth_, nMaxMana_, nHealth_, nMana_);
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
        dVel = 0;
        dVelLimit = dVelLimit_;

        nMana = nMana_;
        nMaxMana = nMaxMana_;
        nMaxHealth = nMaxHealth_;
        nHealth = nHealth_;
    }


    private Rectangle newRect(double _dNewX, double _dNewY) {
        rect = new Rectangle(Math.round((float) _dNewX), Math.round((float) _dNewY), Math.round(dW), Math.round(dH));
        return rect;
    }

    //</editor-fold>
}
