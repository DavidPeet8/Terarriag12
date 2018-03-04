/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.ScrBetterHitDetection;

import gdx.game.ScrBetterHitDetection.Side;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import static gdx.game.ScrBetterHitDetection.ScrBetterHitDetection.setXY;

public class Sprite2 extends Sprite {

    String sName;
    double dX, dY;
    double dW, dH;
    double dInitX, dInitY;
    double dAccel;
    double dVel, dVelLimit;
    double dSpeed;
    Texture txSheet;
    float fSx, fSy, fW, fH;
    Rectangle rect;
    double dLastX, dLastY;
    int nNumJumps;
    Side sideTop, sideBot, sideLeft, sideRight;

    //Constuctor
    public Sprite2(Texture txSheet_, double dX_, double dY_, float fW_, float fH_, double dAccel_, double dVel_, double dVelLimit_, double dW_, double dH_) {
        super(txSheet_);
        fW = fW_;
        fH = fH_;
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
        dLastY = dY;
        dLastX = dX;
        dInitX = dX;
        dInitY = dY;
        dAccel = dAccel_;
        dVel = dVel_;
        dVelLimit = dVelLimit_;
        txSheet = txSheet_;

        sideTop = new Side(txSheet, dX, dY, dW, 5);
        sideBot = new Side(txSheet, dX, dY + dH - 5, dW, 5);
        sideLeft = new Side(txSheet, dX, dY + 5, 5, dH - 10);
        sideRight = new Side(txSheet, dX + dW - 5, dY + 5, 5, dH - 10);
    }

    public int hitTest(Sprite2 spr) {
        /*if (isHit(spr, sideLeft) || isHit(spr, sideRight)) {
            return 2;
        } else if (isHit(spr, sideTop)) {
            return 0;
        } else if (isHit(spr, sideBot)) {
            return 1;
        }*/
        if (isHit(spr.sideBot, sideTop)) {
            return 0;
        } else if (isHit(spr.sideTop, sideBot)) {
            return 1;
        } else if (isHit(spr, sideLeft) || isHit(spr, sideRight) || isHit(this, spr.sideLeft) || isHit(this, spr.sideRight)) {
            return 2;
        }
        return -1;
    }

    public void gravity() {
        dLastY = dY;
        dVel += dAccel;
        if (dVel >= dVelLimit) {
            dVel = dVelLimit;
        }
        dY -= dVel;

    }

    public void jump() {
        //System.out.println("gonna do this jump thing");
        if (nNumJumps > 0) {
            dVel = -12;
            //dY -= dVel;
            //dLastY = dY;
            nNumJumps--;
        }
    }

    public void clipToTop(Sprite2 spr) {
        dY = spr.dY + spr.dH;
        sideTop = new Side(txSheet, dX, dY, dW, 5);
        sideBot = new Side(txSheet, dX, dY + dH - 5, dW, 5);
        sideLeft = new Side(txSheet, dX, dY + 5, 5, dH - 10);
        sideRight = new Side(txSheet, dX + dW - 5, dY + 5, 5, dH - 10);
    }

    public void clipToBot(Sprite2 spr) {
        dY = spr.dY - dH;
        sideTop = new Side(txSheet, dX, dY, dW, 5);
        sideBot = new Side(txSheet, dX, dY + dH - 5, dW, 5);
        sideLeft = new Side(txSheet, dX, dY + 5, 5, dH - 10);
        sideRight = new Side(txSheet, dX + dW - 5, dY + 5, 5, dH - 10);
    }

    public void moveLeftRight() {
        dLastX = dX;
        dX += dSpeed;
    }

    public void moveSides() {
        sideTop.move(dSpeed, dVel);
        sideBot.move(dSpeed, dVel);
        sideLeft.move(dSpeed, dVel);
        sideRight.move(dSpeed, dVel);

    }

    public void changeDir(double dSpeed_) {
        dSpeed = dSpeed_;
    }

    public void goBack() {
        dY = dLastY;
        dX = dLastX;

        sideTop.goBack();
        sideBot.goBack();
        sideRight.goBack();
        sideLeft.goBack();
    }

    public void goBackLeftRight() {
        dX = dLastX;

        sideTop.goBackLeftRight();
        sideBot.goBackLeftRight();
        sideRight.goBackLeftRight();
        sideLeft.goBackLeftRight();
    }

    public void goBackUpDown() {
        dY = dLastY;

        sideTop.goBackUpDown();
        sideBot.goBackUpDown();
        sideRight.goBackUpDown();
        sideLeft.goBackUpDown();
    }

    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float) dX), Math.round((float) dY), Math.round(dW), Math.round(dH));
        return rect;
    }

    public void reset() {
        dY = dInitY;
        dX = dInitX;
    }

    private boolean isHit(Sprite2 spr, Side side) {
        setXY(spr, Math.round(spr.getX()), Math.round(spr.getY()));
        setXY(side, Math.round(side.getX()), Math.round(side.getY()));
        //System.out.println(spr1.retRect());
        //System.out.println(side.getBoundingRectangle());
        return spr.retRect().overlaps(side.retRect()); //System.out.println("is hit");
    }

    private boolean isHit(Side side1, Side side) {
        setXY(side1, Math.round(side1.getX()), Math.round(side1.getY()));
        setXY(side, Math.round(side.getX()), Math.round(side.getY()));
        //System.out.println(spr1.retRect());
        //System.out.println(side.getBoundingRectangle());
        return side1.retRect().overlaps(side.retRect()); //System.out.println("is hit");
    }
}
