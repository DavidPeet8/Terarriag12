package gdx.game.ScrPlay;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import gdx.game.commonclasses.InventoryObj;
import gdx.game.commonclasses.Item;
import gdx.game.commonclasses.SpriteDiscrete;

public class HUD implements Disposable {

    ShapeRenderer shape = new ShapeRenderer();

    HUD(){
    }

    public void update(SpriteDiscrete sprPlayer, InventoryObj inventory){
        shape.begin(ShapeRenderer.ShapeType.Filled);
        updateHealth(sprPlayer.getHealth());
        updateMana(sprPlayer.getMana());
        updateHotbar(inventory.getHotbar(), inventory.getActiveSlot());
        shape.end();
    }

    private void updateHealth(int nHealth){
        shape.setColor(255, 0, 0, 1);
        shape.rect(50, 100, nHealth*3, 30);
    }
    private void updateMana(int nMana){
        shape.setColor(0, 0, 255, 1);
        shape.rect(50, 50, nMana*3, 30);
    }
    private void updateHotbar(Item[] aritHotbar, int nActiveSlot){
        //change onscreen hotbar so that active index is highlighted

    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
