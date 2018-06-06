package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SpriteDiscrete extends Sprite {

    //<editor-fold desc="Init">
    double dX, dY, dNewX, dNewY;
    double dAttemptMoveX, dAttemptMoveY;
    double dW, dH;
    double dVel, dVelLimit;
    double dSpeed;
    Rectangle rect;
    int nNumJumps;

    private int nMaxHealth, nMaxMana, nHealth, nMana;
    //</editor-fold>

    //Constuctor
    public SpriteDiscrete(Texture txSheet_, double dX_, double dY_, double dVelLimit_, double dW_, double dH_,
                          int nMaxHealth_, int nMaxMana_, int nHealth_, int nMana_) {
        super(txSheet_);
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
        dVel = 0;
        dVelLimit = dVelLimit_;

        nMana = nMana_;
        nMaxMana = nMaxMana_;
        nMaxHealth = nMaxHealth_;
        nHealth = nHealth_;
    }

     public void move(Tile[][] artTiles_) {
        gravity();

        checkMove(artTiles_);
        dY = dNewY;
        dX = dNewX;
        setPosition((float) dX, (float) dY);
    }

    //<editor-fold desc="Check Move">
    public void checkMove(Tile[][] artTiles_) {
        dAttemptMoveX = dSpeed;
        dAttemptMoveY = dVel;
        checkMoveX(artTiles_);
        checkMoveY(artTiles_);
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
                            }
                            checkMoveX(artTiles_);
                        } else if (dAttemptMoveX < 0) {
                            dAttemptMoveX++;
                            if (dAttemptMoveX > 0) {
                                dAttemptMoveX = 0;
                            }
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
                        if (dVel > 0) {
                            nNumJumps = 1;
                        }
                        dVel = 0;
                        if (dAttemptMoveY > 0) {
                            dAttemptMoveY--;
                            if (dAttemptMoveY < 0) {
                                dAttemptMoveY = 0;
                            }
                            checkMoveY(artTiles_);
                        } else if (dAttemptMoveY < 0) {
                            dAttemptMoveY++;
                            if (dAttemptMoveY > 0) {
                                dAttemptMoveY = 0;
                            }
                            checkMoveY(artTiles_);
                        }
                    }
                }
            }
        }
    }
    //</editor-fold>

    public void gravity() {
        dVel += Constants.GRAVCONSTANT;
        if (dVel >= dVelLimit) {
            dVel = dVelLimit;
        }
    }

    public void init() {
        this.setPosition((float) dX, (float) dY);
        this.setSize((float) dW, (float) dH);
    }

    public void jump() {
        if (nNumJumps > 0) {
            dVel = -12;
            nNumJumps--;
        }
    }

  
    public void changeDir(double dSpeed_) {
        dSpeed = dSpeed_;
    }

    private Rectangle newRect(double _dNewX, double _dNewY) {
        rect = new Rectangle(Math.round((float) _dNewX), Math.round((float) _dNewY), Math.round(dW), Math.round(dH));
        return rect;
    }

    //<editor-fold desc="Getters">
    //---------Getters-------------

    public int getHealth(){
        return nHealth;
    }
    public int getMana(){
        return nMana;
    }
    public int getMaxHealth(){
        return nMaxHealth;
    }
    public int getMaxMana(){
        return nMaxMana;
    }
    public double getdX(){return dX;}
    public double getdY(){return dY;}
    public double getdNewX(){return dNewX;}
    public double getdNewY(){return dNewY;}
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setdX(double value){
        dX = value;
    }
    public void setdY(double value){
        dY = value;
    }
    public void setdPos(double Xvalue, double Yvalue){
        dX = Xvalue;
        dY = Yvalue;
    }
    public void decrementHealth(int value){
        if(nHealth + value > 0) {
            nHealth += value;
        }else{
            nHealth = 0;
        }
    }
    public void decrementMana(int value){
        if(nMana + value > 0) {
            nMana += value;
        } else{
            nMana = 0;
        }
    }

    public void setHealth(int newHealth){
        nHealth = newHealth;
        System.out.println(nHealth);
    }
    //</editor-fold>
}
