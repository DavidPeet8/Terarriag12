package gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import gdx.game.ScrDeath.ScrDeath;
import gdx.game.ScrInvenoryGUI.ScrInventory;
import gdx.game.ScrLoad.*;
import gdx.game.ScrMenu.*;
import gdx.game.ScrPlay.*;
import gdx.game.commonclasses.Fonts;
import gdx.game.commonclasses.Textures;

public class GamTerarria extends Game {

    //<editor-fold desc="Init">
    ScrPlay scrPlay;
    ScrMenu scrMenu;
    ScrLoad scrLoad;
    ScrInventory scrInventory;
    ScrDeath scrDeath;

    public int nScreen;
    //</editor-fold>

	@Override
	public void create () {
        Textures.initTex();
        Textures.initJSON();
        Fonts.initFonts();

        scrMenu = new ScrMenu(this);
        scrDeath = new ScrDeath(this);

        nScreen = 0;

        updateState(nScreen);
	}

	public void updateState(int nScreen){
	    if (nScreen == 0){
            setScreen(scrMenu);
            System.out.println("menu");
        }else if(nScreen == 1){
            setScreen(scrLoad);
            System.out.println("load");
        }else if(nScreen == 2){
            setScreen(scrPlay);
            System.out.println("play");
        }else if(nScreen == 3){
        setScreen(scrInventory);
        System.out.println("Inventory");
        }else if (nScreen == 4){
	        setScreen(scrDeath);
            System.out.println("respawn");
        }
    }

    //<editor-fold desc="Getters and setters">
    public ScrPlay getScrPlay(){
	    return scrPlay;
    }
    public ScrLoad getScrLoad(){
        return scrLoad;
    }
    public void setScrLoad(ScrLoad s){
        scrLoad = s;
    }
    public void setScrPlay(ScrPlay s){
        scrPlay = s;
    }
    public void setScrInventory(ScrInventory s){
        scrInventory = s;
    }
    //</editor-fold>

	@Override
	public void render () {
		super.render();
	}

	@Override
    public void dispose(){
	    super.dispose();
    }

    
}

//cannot have one master camera and viewport as it passes a reference, therefore when we change the referance, we change the actual object everywehre
//that is why we had a viewport or camera updating all other instances of it