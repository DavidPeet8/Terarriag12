/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import static gdx.game.ScrArrayHitDetection.ScrArrayHitDetection.setXY;

public class SpriteWithSides extends Sprite {

    String sName;
    double dX, dY;
    double dW, dH;
    double dInitX, dInitY;
    double dAccel;
    double dVel, dVelLimit;
    double dSpeed;
    double dDisplacement;
    Texture txSheet;
    float fSx, fSy, fW, fH;
    Rectangle rect;
    double dLastX, dLastY;
    int nNumJumps;
    Side sideTop, sideBot, sideLeft, sideRight;

    //Constuctor
    public SpriteWithSides(Texture txSheet_, double dX_, double dY_, float fW_, float fH_, double dAccel_, double dVel_, double dVelLimit_, double dW_, double dH_) {
        super(txSheet_);
        fW = fW_;
        fH = fH_;
        //fW = txSheet.getWidth();
        //fH = txSheet.getHeight();
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

    public void hitTestUpDown(SpriteWithSides spr) {

        if (isHit(spr.sideBot, sideTop)) {
            jumpReset(1);
            goBackUpDown();

            //sprVlad.yVelReset();
            clipToTop(spr);
        } else if (isHit(spr.sideTop, sideBot)) {
            goBackUpDown();
            yVelReset();
            clipToBot(spr);
        }
    }
    public void hitTestLeftRight(SpriteWithSides spr) {

        if (isHit(spr, sideLeft) || isHit(spr, sideRight) || isHit(this, spr.sideLeft) || isHit(this, spr.sideRight)) {
            goBackLeftRight();
            xSpeedReset();
        }
    }
    public void hitTest(SpriteWithSides spr) {

        if (isHit(spr.sideBot, sideTop)) {
            jumpReset(1);
            goBackUpDown();

            //sprVlad.yVelReset();
            clipToTop(spr);
        } else if (isHit(spr.sideTop, sideBot)) {
            goBackUpDown();
            yVelReset();
            clipToBot(spr);
        } else if (isHit(spr, sideLeft) || isHit(spr, sideRight) || isHit(this, spr.sideLeft) || isHit(this, spr.sideRight)) {
            goBackLeftRight();
            xSpeedReset();
        } 
    }

    public void gravity() {
        dLastY = dY;
        dVel += dAccel;
        if (dVel >= dVelLimit) {
            dVel = dVelLimit;
        }
        dY -= dVel;
        this.setY((float) dY);
    }

    public void init() {
        this.setPosition((float) dX, (float) dY);
        this.setSize((float) dW, (float) dH);
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

    public void clipToTop(SpriteWithSides spr) {

        dDisplacement = spr.dY + spr.dH - dY;
        dY += dDisplacement;
        sideTop.dY += dDisplacement;
        sideBot.dY += dDisplacement;
        sideLeft.dY += dDisplacement;
        sideRight.dY += dDisplacement;
        this.setY((float) dY);

        /*sideTop = new Side(txSheet, dX, dY, dW, 5);
        sideBot = new Side(txSheet, dX, dY + dH - 5, dW, 5);
        sideLeft = new Side(txSheet, dX, dY + 5, 5, dH - 10);
        sideRight = new Side(txSheet, dX + dW - 5, dY + 5, 5, dH - 10);*/
    }

    public void clipToBot(SpriteWithSides spr) {
        dDisplacement = (dY + dH - spr.dY) * -1;

        dY += dDisplacement;
        sideTop.dY += dDisplacement;
        sideBot.dY += dDisplacement;
        sideLeft.dY += dDisplacement;
        sideRight.dY += dDisplacement;
        this.setY((float) dY);
        /*sideTop = new Side(txSheet, dX, dY, dW, 5);
        sideBot = new Side(txSheet, dX, dY + dH - 5, dW, 5);
        sideLeft = new Side(txSheet, dX, dY + 5, 5, dH - 10);
        sideRight = new Side(txSheet, dX + dW - 5, dY + 5, 5, dH - 10);*/
    }

    public void clipToSide(SpriteWithSides spr) {
        dDisplacement = spr.dX + spr.dH - dX + 5;
        dX += dDisplacement;
        sideTop.dX += dDisplacement;
        sideBot.dX += dDisplacement;
        sideLeft.dX += dDisplacement;
        sideRight.dX += dDisplacement;
        this.setX((float) dX);
    }

    public void moveLeftRight() {
        dLastX = dX;
        dX += dSpeed;
        this.setX((float) dX);
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
        this.setX((float) dX);
    }

    public void goBackUpDown() {
        dY = dLastY;

        sideTop.goBackUpDown();
        sideBot.goBackUpDown();
        sideRight.goBackUpDown();
        sideLeft.goBackUpDown();
        this.setY((float) dY);
    }

    public Rectangle retRect() { //replaces .getBoundingRectangle() for hit detection 
        rect = new Rectangle(Math.round((float) dX), Math.round((float) dY), Math.round(dW), Math.round(dH));
        return rect;
    }

    public void reset() {
        dY = dInitY;
        dX = dInitX;
    }

    public void jumpReset(int _nNumJumps) {
        nNumJumps = _nNumJumps;
    }

    public void yVelReset() {
        dVel = 0;
    }

    public void xSpeedReset() {
        dSpeed = 0;
    }

    private boolean isHit(SpriteWithSides spr, Side side) {
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
