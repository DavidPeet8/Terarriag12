
package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpriteWithSidesCommon extends Sprite {
    double dX, dY, dWidth, dHeight;
    double dInitX, dInitY, dAccel, dVel, dVelLimit, dSpeed, dDisplacement;
    Texture txSheet;
    Rectangle rect;
    double dLastX, dLastY;
    int nNumJumps;
    Side sideTop, sideBot, sideLeft, sideRight;

    public SpriteWithSidesCommon(Texture txSheet_, double dX_, double dY_, double dAccel_, double dVel_, double dVelLimit_, double dW_, double dH_) {
        super(txSheet_);
        dX = dX_;
        dY = dY_;
        dWidth = dW_;
        dHeight = dH_;
        dLastY = dY;
        dLastX = dX;
        dInitX = dX;
        dInitY = dY;
        dAccel = dAccel_;
        dVel = dVel_;
        dVelLimit = dVelLimit_;
        txSheet = txSheet_;

        sideTop = new Side(txSheet, dX, dY, dWidth, 5);
        sideBot = new Side(txSheet, dX, dY + dHeight - 5, dWidth, 5);
        sideLeft = new Side(txSheet, dX, dY + 5, 5, dHeight - 10);
        sideRight = new Side(txSheet, dX + dWidth - 5, dY + 5, 5, dHeight - 10);
    }
    public void hitTest(SpriteWithSidesCommon spr) {

        if (isHit(spr.sideBot, sideTop)) {
            jumpReset(1);
            goBackUpDown();

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
        this.setSize((float) dWidth, (float) dHeight);
    }

    public void jump() {
        if (nNumJumps > 0) {
            dVel = -12;
            nNumJumps--;
        }
    }

    public void clipToTop(SpriteWithSidesCommon spr) {

        dDisplacement = spr.dY + spr.dHeight - dY;
        dY += dDisplacement;
        sideTop.dY += dDisplacement;
        sideBot.dY += dDisplacement;
        sideLeft.dY += dDisplacement;
        sideRight.dY += dDisplacement;
        this.setY((float) dY);

    }

    public void clipToBot(SpriteWithSidesCommon spr) {
        dDisplacement = (dY + dHeight - spr.dY) * -1;

        dY += dDisplacement;
        sideTop.dY += dDisplacement;
        sideBot.dY += dDisplacement;
        sideLeft.dY += dDisplacement;
        sideRight.dY += dDisplacement;
        this.setY((float) dY);
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
        rect = new Rectangle(Math.round((float) dX), Math.round((float) dY), Math.round(dWidth), Math.round(dHeight));
        return rect;
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

    private boolean isHit(SpriteWithSidesCommon spr, Side side) {
        setXY(spr, Math.round(spr.getX()), Math.round(spr.getY()));
        setXY(side, Math.round(side.getX()), Math.round(side.getY()));
        return spr.retRect().overlaps(side.retRect());
    }

    private boolean isHit(Side side1, Side side) {
        setXY(side1, Math.round(side1.getX()), Math.round(side1.getY()));
        setXY(side, Math.round(side.getX()), Math.round(side.getY()));
        return side1.retRect().overlaps(side.retRect());
    }
    
    public static void setXY(Sprite spr, int nX, int nY) {
        spr.setX(nX);
        spr.setY(nY);
    }
}
