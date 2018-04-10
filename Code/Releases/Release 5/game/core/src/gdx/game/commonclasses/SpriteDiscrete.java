package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpriteDiscrete extends Sprite {

    //<editor-fold desc="Init">
    double dX, dY, dNewX, dNewY;
    double dAttemptMoveX, dAttemptMoveY;
    double dW, dH;
    double dAccel;
    double dVel, dVelLimit;
    double dSpeed;
    Rectangle rect;
    int nNumJumps;
    //</editor-fold>

    //Constuctor
    public SpriteDiscrete(Texture txSheet_, double dX_, double dY_, double dAccel_, double dVel_, double dVelLimit_, double dW_, double dH_) {
        super(txSheet_);
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
        dAccel = dAccel_;
        dVel = dVel_;
        dVelLimit = dVelLimit_;
    }

    public void move(Tile[][] artTiles_) {
        gravity();
        
        checkMove(artTiles_);
        dY = dNewY;
        dX = dNewX;
        setPosition((float) this.dX, (float) this.dY);
    }

    //<editor-fold desc="Check Move">
    public void checkMove(Tile[][] artTiles_) {
        dAttemptMoveX = dSpeed;
        dAttemptMoveY = dVel;
        for (int i = 0; i < 5; i++) { //adding a loop here prevents jittering
            checkMoveX(artTiles_);
            checkMoveY(artTiles_);
        }
    }

    public void checkMoveX(Tile[][] artTiles_) {
        dNewX = dX + dAttemptMoveX;
        for (int i = artTiles_.length - 1; i >= 0; i--) {
            for (int j = artTiles_[i].length - 1; j >= 0; j--) {
                if (artTiles_[i][j] != null) {
                    if (newRect(dNewX, dY).overlaps(artTiles_[i][j].getBoundingRectangle())) {
                        if (dAttemptMoveX > 0) {
                            dAttemptMoveX--;
                            if (dAttemptMoveX < 0) {
                                dAttemptMoveX = 0;
                                checkMoveX(artTiles_);
                            }
                        } else if (dAttemptMoveX < 0) {
                            dAttemptMoveX++;
                            if (dAttemptMoveX > 0) {
                                dAttemptMoveX = 0;
                                checkMoveX(artTiles_);
                            }
                        }
                        if (dAttemptMoveX != 0) {
                            checkMoveX(artTiles_);
                        }
                    }
                }
            }
        }
    }

    public void checkMoveY(Tile[][] artTiles_) {
        dNewY = dY - dAttemptMoveY;
        for (int i = artTiles_.length - 1; i >= 0; i--) {
            for (int j = artTiles_[i].length - 1; j >= 0; j--) {
                if (artTiles_[i][j] != null) {
                    if (newRect(dX, dNewY).overlaps(artTiles_[i][j].getBoundingRectangle())) {
                        dVel = 0;
                        if (dAttemptMoveY > 0) {
                            dAttemptMoveY--;
                            if (dAttemptMoveY < 0) {
                                dAttemptMoveY = 0;
                                checkMoveY(artTiles_);
                            }
                        } else if (dAttemptMoveY < 0) {
                            dAttemptMoveY++;
                            if (dAttemptMoveY > 0) {
                                dAttemptMoveY = 0;
                                checkMoveY(artTiles_);
                            }
                        }

                        if (dAttemptMoveY != 0) {
                            checkMoveY(artTiles_);
                        }
                    }
                }
            }
        }
    }
    //</editor-fold>

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
