package gdx.game.ScrGravity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Sprite2 extends Sprite {

    String sName;
    double dX, dY;
    double dAccel;
    double dVel, dVelLimit;
    public Texture txSheet;
    float fW, fH;
    Rectangle rect;
    double dLastX, dLastY;
    int nNumJumps;

    //Constuctor
    public Sprite2(Texture txSheet_, float fW_, float fH_, double dX_, double dY_,
            double dAccel_, double dVel_, double dVelLimit_) {
        super(txSheet_);
        fW = fW_;
        fH = fH_;
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
    
    public void jump() {
        dVel = -12;
        nNumJumps --;
    }
    
    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float)dX), Math.round((float)dY), fW, fH);
        return rect;
    }
    
    
    public void reset() {
        dY = 600;
        dX = 300;
    }
    
    //sprite checks hit, want to check in all 8 adjacent tiles no more 
    //--for efficiancy
    //look at grondins 2D array note, look at 
}
