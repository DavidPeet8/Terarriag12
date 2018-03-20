/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpriteExtended extends Sprite {

    String sName;
    double dX, dY;
    double dW, dH;
    double dInitX, dInitY;
    Texture txSheet;
    Rectangle rect;
    double dLastX, dLastY;
    int nNumJumps;

    //Constuctor
    public SpriteExtended(Texture txSheet_, double dX_, double dY_, double dW_, double dH_) {
        super(txSheet_);
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
        dLastY = dY;
        dLastX = dX;
        dInitX = dX;
        dInitY = dY;
        txSheet = txSheet_;
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
