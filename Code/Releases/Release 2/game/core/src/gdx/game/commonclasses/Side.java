
package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Side extends Sprite {
    double dX,dY,dWidth,dHeight;
    Texture tx;
    Rectangle rect;
    double dLastX;
    double dLastY;
    
    Side(Texture tx_, double dX_, double dY_, double dW_, double dH_) {
        dX = dX_;
        dY = dY_;
        dWidth = dW_;
        dHeight = dH_;
        tx = tx_;
    }
    
    public void move(double dVelX, double dVelY) {
        dLastX = dX;
        dLastY = dY;
        dX += dVelX;
        dY -= dVelY;
    }
    
    public void goBackUpDown() {
        dY = dLastY;
    }
    
    public void goBackLeftRight() {
        dX = dLastX;
    }
    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float) dX), Math.round((float) dY), Math.round(dWidth), Math.round(dHeight));
        return rect;
    }
}
