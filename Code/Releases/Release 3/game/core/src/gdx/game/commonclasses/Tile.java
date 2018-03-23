package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Tile extends Sprite{

    public Texture tex;
    boolean bGravAffected, bSolid;
    int Durablility;

    public Tile(Texture tex,float fW, float fH, boolean bGravAffected, boolean bSolid){
        super(tex);
        this.tex = tex;
        this.bGravAffected = bGravAffected;
        this.bSolid = bSolid;
    }

//    public void update(){
//        if(bGravAffected == true){
//            ApplyGrav();
//            CheckFallingHit();
//        }
//    }
//
//    public void ApplyGrav(){
//
//    }
//    public void CheckFallingHit(){
//
//    }
}
