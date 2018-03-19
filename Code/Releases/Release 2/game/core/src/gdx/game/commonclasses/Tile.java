package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;


public class Tile extends SpriteWithSidesCommon{

    public Texture tex;
    boolean bGravAffected, bSolid;
    int Durablility;

    public Tile(Texture tex,float fW, float fH, boolean bGravAffected, boolean bSolid){
        super(tex, 0,0,0,0,0,0,0);
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
