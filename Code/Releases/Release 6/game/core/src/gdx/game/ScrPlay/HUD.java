package gdx.game.ScrPlay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import gdx.game.commonclasses.InventoryObj;
import gdx.game.commonclasses.Item;
import gdx.game.commonclasses.SpriteDiscrete;
import gdx.game.commonclasses.Fonts;

import static gdx.game.commonclasses.Textures.texActive;

public class HUD implements Disposable {

    ShapeRenderer shape = new ShapeRenderer();
    SpriteBatch batch = new SpriteBatch();

    HUD(){
    }

    public void update(SpriteDiscrete sprPlayer, InventoryObj inventory){
        shape.begin(ShapeRenderer.ShapeType.Filled);
        updateHealth(sprPlayer.getHealth());
        updateMana(sprPlayer.getMana());
        shape.end();

        batch.begin();
        Fonts.F1.draw(batch,"HEALTH: " , 100, 120);
        Fonts.F1.draw(batch, Integer.toString(sprPlayer.getHealth()), 180, 120);
        Fonts.F1.draw(batch,"MANA: " , 100, 70);
        Fonts.F1.draw(batch, Integer.toString(sprPlayer.getMana()), 180, 70);
        updateHotbar(inventory.getHotbar(), inventory.getActiveSlot());
        batch.end();
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
        for (int r = 0; r < aritHotbar.length; r++) {
            if(aritHotbar[r] != null) {
                if (aritHotbar[r].getTex() != null) {
                    batch.draw(aritHotbar[r].getTex(), r * 40 + 400, 600, 30, 30);
                }
            }
            if(r == nActiveSlot){
                batch.draw(texActive, r*40+400, 600, 30, 30);
            }
        }
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
