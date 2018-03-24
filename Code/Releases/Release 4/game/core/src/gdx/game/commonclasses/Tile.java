package gdx.game.commonclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.JsonValue;


public class Tile extends Sprite{

    public Texture tex;
    boolean bGravAffected, bSolid;
    int Durablility;
    JsonValue json;

    public Tile(Texture tex, float fW, float fH, boolean bGravAffected, boolean bSolid, JsonValue json){
        //set size?
        super(tex);
        this.tex = tex;
        this.bGravAffected = bGravAffected;
        this.bSolid = bSolid;
        this.json = json;

        if(json != null){
            Durablility = json.getInt("Durability");
        }
    }

    public int getDurability(){
        return Durablility;
    }
    public void setDurability(int d){
        Durablility = d;
    }

}
