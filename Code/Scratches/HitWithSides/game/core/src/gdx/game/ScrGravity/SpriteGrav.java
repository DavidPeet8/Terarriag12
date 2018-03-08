package gdx.game.ScrGravity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpriteGrav extends Sprite {

    double dX, dY;
    double dAccel;
    double dVel, dVelLimit;
    public Texture txSheet;
    float fWidth, fHeight;
    Rectangle rect;
    double dLastX;
    
    public SpriteGrav(Texture txSheet_, float fWidth_, float fHeight_, double dX_, double dY_,
            double dAccel_, double dVel_, double dVelLimit_) {
        super(txSheet_);
        fWidth = fWidth_;
        fHeight = fHeight_;
        dX = dX_;
        dY = dY_;
        dAccel = dAccel_;
        dVel = dVel_;
        dVelLimit = dVelLimit_;
        txSheet = txSheet_;
    }

    public void gravity() {
        dVel += dAccel;
        if (dVel >= dVelLimit) {
            dVel = dVelLimit;
        }
        dY -= dVel;
    }
    
    
    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float)dX), Math.round((float)dY), fWidth, fHeight);
        return rect;
    }
}
