package gdx.game.ScrBetterHitDetection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import static gdx.game.ScrBetterHitDetection.ScrBetterHitDetection.setXY;

public class SpriteWithSidesPackageSpecific extends Sprite {

    double dX, dY;
    double dWidth, dHeight;
    double dInitX, dInitY;
    double dAccel;
    double dVel, dVelLimit;
    double dSpeed;
    Texture txSheet;
    Rectangle rect;
    double dLastX, dLastY;
    int nNumJumps;
    Side sideTop, sideBot, sideLeft, sideRight;

    //Constuctor
    public SpriteWithSidesPackageSpecific(Texture txSheet_, double dX_, double dY_, double dAccel_, double dVel_, double dVelLimit_, double dWidth_, double dHeight_) {
        super(txSheet_);
        dX = dX_;
        dY = dY_;
        dWidth = dWidth_;
        dHeight = dHeight_;
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
        sideRight = new Side(txSheet, dX + dWidth - 5, dY + 5, 5, dHeight- 10);
    }

    public int hitTest(SpriteWithSidesPackageSpecific spr) {
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
        if (nNumJumps > 0) {
            dVel = -12;
            nNumJumps--;
        }
    }

    public void clipToTop(SpriteWithSidesPackageSpecific spr) {
        dY = spr.dY + spr.dHeight;
        sideTop = new Side(txSheet, dX, dY, dWidth, 5);
        sideBot = new Side(txSheet, dX, dY + dHeight - 5, dWidth, 5);
        sideLeft = new Side(txSheet, dX, dY + 5, 5, dHeight - 10);
        sideRight = new Side(txSheet, dX + dWidth - 5, dY + 5, 5, dHeight - 10);
    }

    public void clipToBot(SpriteWithSidesPackageSpecific spr) {
        dY = spr.dY - dHeight;
        sideTop = new Side(txSheet, dX, dY, dWidth, 5);
        sideBot = new Side(txSheet, dX, dY + dHeight - 5, dWidth, 5);
        sideLeft = new Side(txSheet, dX, dY + 5, 5, dHeight - 10);
        sideRight = new Side(txSheet, dX + dWidth - 5, dY + 5, 5, dHeight - 10);
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
        rect = new Rectangle(Math.round((float) dX), Math.round((float) dY), Math.round(dWidth), Math.round(dHeight));
        return rect;
    }

    public void reset() {
        dY = dInitY;
        dX = dInitX;
    }

    private boolean isHit(SpriteWithSidesPackageSpecific spr, Side side) {
        setXY(spr, Math.round(spr.getX()), Math.round(spr.getY()));
        setXY(side, Math.round(side.getX()), Math.round(side.getY()));
        return spr.retRect().overlaps(side.retRect()); 
    }

    private boolean isHit(Side side1, Side side) {
        setXY(side1, Math.round(side1.getX()), Math.round(side1.getY()));
        setXY(side, Math.round(side.getX()), Math.round(side.getY()));
        return side1.retRect().overlaps(side.retRect()); 
    }
}
