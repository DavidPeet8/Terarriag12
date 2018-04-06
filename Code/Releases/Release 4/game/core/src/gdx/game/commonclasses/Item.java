package gdx.game.commonclasses;

import com.badlogic.gdx.utils.JsonValue;

public class Item{

    //<editor-fold desc="Init">
    public String sType;
    public int requiredToolType;
    int nStack = 0;
    //</editor-fold>
    
    public Item(JsonValue jvItem){
        //errors common here make sure the json is a thing
        requiredToolType =  jvItem.getInt("RequiredToolType");
        sType = jvItem.getString("Type");
    }

    public static Item createItem(Tile t){
        Item item = new Item(t.jvJson);
        return item;
    }
    
}
