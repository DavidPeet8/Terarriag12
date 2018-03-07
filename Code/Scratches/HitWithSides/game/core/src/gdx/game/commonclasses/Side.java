/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author mattm
 */
public class Side extends Sprite {
    double dX,dY,dW,dH;
    Texture tx;
    Rectangle rect;
    double dLastX;
    double dLastY;
    
    Side(Texture tx_, double dX_, double dY_, double dW_, double dH_) {
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
        tx = tx_;
    }
    
    public void move(double dVelX, double dVelY) {
        dLastX = dX;
        dLastY = dY;
        dX += dVelX;
        dY -= dVelY;
    }
    
    public void goBack() {
        dX = dLastX;
        dY = dLastY;
    }
    
    public void goBackUpDown() {
        dY = dLastY;
    }
    
    public void goBackLeftRight() {
        dX = dLastX;
    }
    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float) dX), Math.round((float) dY), Math.round(dW), Math.round(dH));
        return rect;
    }
}
