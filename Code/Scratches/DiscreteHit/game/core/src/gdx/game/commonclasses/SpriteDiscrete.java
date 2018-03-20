/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpriteDiscrete extends Sprite {

    double dX, dY, dNewX, dNewY;
    double dAttemptMoveX, dAttemptMoveY;
    double dW, dH;
    double dInitX, dInitY;
    double dAccel;
    double dVel, dVelLimit;
    double dSpeed;
    Texture txSheet;
    float fW, fH;
    Rectangle rect;
    double dLastX, dLastY;
    int nNumJumps;

    //Constuctor
    public SpriteDiscrete(Texture txSheet_, double dX_, double dY_, float fW_, float fH_, double dAccel_, double dVel_, double dVelLimit_, double dW_, double dH_) {
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
    }

    public void move(SpriteExtended[][] arSpriteExtended_) {
        gravity();
        
        checkMove(arSpriteExtended_);
        dY = dNewY;
        dX = dNewX;
        setPosition((float) this.dX, (float) this.dY);
    }

    public void checkMove(SpriteExtended[][] arSpriteExtended_) {
        dAttemptMoveX = dSpeed;
        dAttemptMoveY = dVel;
        for (int i = 0; i < 5; i++) { //adding a loop here prevents jittering
            checkMoveX(arSpriteExtended_);
            checkMoveY(arSpriteExtended_);
        }
    }

    public void checkMoveX(SpriteExtended[][] arSpriteExtended_) {
        dNewX = dX + dAttemptMoveX;
        for (int i = arSpriteExtended_.length - 1; i >= 0; i--) { //not positive that this is how you loop through it
            for (int j = arSpriteExtended_[i].length - 1; j >= 0; j--) {
                if (arSpriteExtended_[i][j] != null) {
                    if (newRect(dNewX, dY).overlaps(arSpriteExtended_[i][j].retRect())) {
                        if (dAttemptMoveX > 0) {
                            dAttemptMoveX--;
                            if (dAttemptMoveX < 0) {
                                dAttemptMoveX = 0;
                                checkMoveX(arSpriteExtended_);
                            }
                        } else if (dAttemptMoveX < 0) {
                            dAttemptMoveX++;
                            if (dAttemptMoveX > 0) {
                                dAttemptMoveX = 0;
                                checkMoveX(arSpriteExtended_);
                            }
                        }
                        if (dAttemptMoveX != 0) {
                            checkMoveX(arSpriteExtended_);
                        }
                    }
                }
            }
        }
    }

    public void checkMoveY(SpriteExtended[][] arSpriteExtended_) {
        dNewY = dY - dAttemptMoveY;
        for (int i = arSpriteExtended_.length - 1; i >= 0; i--) { //not positive that this is how you loop through it
            for (int j = arSpriteExtended_[i].length - 1; j >= 0; j--) {
                if (arSpriteExtended_[i][j] != null) {
                    if (newRect(dX, dNewY).overlaps(arSpriteExtended_[i][j].retRect())) {
                        if (dAttemptMoveY > 0) {
                            dAttemptMoveY--;
                            if (dAttemptMoveY < 0) {
                                dAttemptMoveY = 0;
                                checkMoveY(arSpriteExtended_);
                            }
                        } else if (dAttemptMoveY < 0) {
                            dAttemptMoveY++;
                            if (dAttemptMoveY > 0) {
                                dAttemptMoveY = 0;
                                checkMoveY(arSpriteExtended_);
                            }
                        }

                        if (dAttemptMoveY != 0) {
                            checkMoveY(arSpriteExtended_);
                        }
                    }
                }
            }
        }
    }

    public void gravity() {
        dVel += dAccel;
        if (dVel >= dVelLimit) {
            dVel = dVelLimit;
        }
    }

    public void init() {
        this.setPosition((float) dX, (float) dY);
        this.setSize((float) dW, (float) dH);
    }

    public void jump() {
        //if (nNumJumps > 0) {
        dVel = -12;
        nNumJumps--;
        //}
    }

  
    public void changeDir(double dSpeed_) {
        dSpeed = dSpeed_;
    }

    private Rectangle newRect(double _dNewX, double _dNewY) {
        rect = new Rectangle(Math.round((float) _dNewX), Math.round((float) _dNewY), Math.round(dW), Math.round(dH));
        return rect;
    }

}
