package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpriteExtended extends Sprite {

    double dX, dY;
    double dW, dH;
    Rectangle rect;

    //Constuctor
    public SpriteExtended(Texture txSheet_, double dX_, double dY_, double dW_, double dH_) {
        super(txSheet_);
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
    }

    public void init() {
        setPosition((float) dX, (float) dY);
        setSize((float) dW, (float) dH);
    }

    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float) dX), Math.round((float) dY), Math.round(dW), Math.round(dH));
        return rect;
    }

}
