package gdx.game.commonclasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;



public class Item {
    
    boolean bIsActive = false;
    JsonReader json = new JsonReader();
    JsonValue tile = json.parse(Gdx.files.local("JSON/Tile.json"));
    
    public Item(int bToolType, int image){
        JsonValue type = tile.get(image);
        type.getBoolean("isTool");
        type.getString("Type");
        
    }
    
}
