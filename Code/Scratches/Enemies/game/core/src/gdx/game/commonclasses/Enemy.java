package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;


/**
 *
 * @author mattm
 */
public class Enemy extends SpriteDiscrete{
    
    public Enemy(Texture txSheet_, double dX_, double dY_, double dAccel_, double dVel_, double dVelLimit_, double dW_, double dH_) {
        super(txSheet_,  dX_,  dY_,  dAccel_, dVel_,  dVelLimit_,  dW_,  dH_); //I think this is needed but I don't actually know
        dX = dX_;
        dY = dY_;
        dW = dW_;
        dH = dH_;
        dAccel = dAccel_;
        dVel = dVel_;
        dVelLimit = dVelLimit_;
        dSpeed = 4;
    }
    /*public void update(SpriteExtended[][] arSpriteExtended_) {
        pickDirection(arSpriteExtended_);
        move(arSpriteExtended_);
    }
    public void pickDirection(SpriteExtended[][] arSpriteExtended_) {
        if (dSpeed < 0) {
            //if there is nothing left keep going left
            if (arSpriteExtended_[0][0] != null) { //ask david how they get passed in
                dSpeed *= -1;
            }
        } else if (dSpeed > 0) {
            //if there is nothing right go right
            if (arSpriteExtended_[0][arSpriteExtended_[0].length - 1] != null) { //ask david how they get passed in
                dSpeed *= -1;
            }
        } else {
            //if there is nothing left go left
            //if there is nothing right go right
        }
        
    }*/
    public void update(SpriteExtended[][] arTile_) {
        pickDirection(arTile_);
        move(arTile_);
    }
    public void pickDirection(SpriteExtended[][] arTile_) {
        if (Math.random() * 1000 > 995) {
            dSpeed *= -1;
        } 
        if (Math.random() * 1000 > 995) {
            jump();
        } 
    }
}
