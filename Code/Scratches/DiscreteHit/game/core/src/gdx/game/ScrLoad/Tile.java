package gdx.game.ScrLoad;

import gdx.game.ScrBetterHitDetection.Sprite2;
import com.badlogic.gdx.graphics.Texture;


public class Tile extends Sprite2{

    Texture tex;
    boolean bGravAffected, bSolid;
    //String sBiome;
    int Durablility;

    public Tile(Texture tex,float fW, float fH, boolean bGravAffected, boolean bSolid){
        super(tex, 0,0,0,0,0,0,0,0,0);
        this.tex = tex;
        this.bGravAffected = bGravAffected;
        //this.sBiome = sBiome;
        this.bSolid = bSolid;
    }

    public void update(){
        if(bGravAffected == true){
            ApplyGrav();
            CheckFallingHit();
        }
    }

    public void ChangeTile(Texture t){
        tex = t;
    }
    public void ApplyGrav(){

    }
    public void CheckFallingHit(){

    }
}
