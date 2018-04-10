package gdx.game.commonclasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Textures implements Disposable{

    //use arrays or use a whole wack of variables? one way is cleaner code wise, the other is far more readable
    //or do i need array for item to tile comverion to make item more efficiant
    //or make it hold tex name in json

    //<editor-fold desc="Declare">
    //boxes
    public static Texture boxGrass;
    public static Texture boxDirt;
    public static Texture boxStone;
    public static Texture boxCopper;
    public static Texture boxTin;
    public static Texture boxIron;
    public static Texture boxLead;
    public static Texture boxGold;
    public static Texture boxCobalt;
    public static Texture boxDemonite;
    public static Texture boxCrimtane;
    public static Texture boxOrhicallum;
    public static Texture boxClout;
    public static Texture boxLeaves;
    public static Texture boxWood;
    public static Texture boxTungsten;
    public static Texture boxPlatinum;
    public static Texture boxSilver;
    public static Texture boxCloud;
    public static Texture boxMoss;
    public static Texture boxPalladium;
    public static Texture boxMythril;
    public static Texture boxAdamantite;
    public static Texture boxTitanium;
    public static Texture boxWater;
    public static Texture boxLava;
    public static Texture boxHoney;

    //load
    public static Texture texLoad;

    //play
    public static Texture texBack;
    public static Texture texPlay;

    //json values
    public static JsonValue jvTile;

    public static JsonValue jvGrass;
    public static JsonValue jvDirt;
    public static JsonValue jvStone;
    public static JsonValue jvTin;
    public static JsonValue jvCopper;

    public static JsonValue jvIron;
    public static JsonValue jvLead;
    public static JsonValue jvTungsten;

    public static JsonValue jvGold;
    public static JsonValue jvPlatinum;
    public static JsonValue jvSilver;

    public static JsonValue jvCrimtane;
    public static JsonValue jvDemonite;

    public static JsonValue jvCobalt;
    public static JsonValue jvPalladium;
    public static JsonValue jvMythril;
    public static JsonValue jvOrhicallum;
    public static JsonValue jvAdamantite;
    public static JsonValue jvTitanium;

    public static JsonValue jvWater;
    public static JsonValue jvLava;
    public static JsonValue jvHoney;
    public static JsonValue jvClout;
    //</editor-fold>

    public static void initTex(){

        boxGrass = new Texture("Boxes/BoxGrass.png");
        boxDirt = new Texture("Boxes/BoxDirt.png");
        boxStone = new Texture("Boxes/BoxStone.png");
        boxCopper = new Texture("Boxes/BoxCopper.png");
        boxTin = new Texture("Boxes/BoxTin.png");
        boxIron = new Texture("Boxes/BoxIron.png");
        boxLead = new Texture("Boxes/BoxLead.png");
        boxGold = new Texture("Boxes/BoxGold.png");
        boxCobalt = new Texture("Boxes/BoxCobalt.png");
        boxDemonite = new Texture("Boxes/BoxDemonite.png");
        boxCrimtane = new Texture("Boxes/BoxCrimtane.png");
        boxOrhicallum = new Texture("Boxes/BoxOrhicallum.png");
        boxClout = new Texture("Boxes/BoxClout.png");
        boxLeaves = new Texture("Boxes/BoxLeaves.png");
        boxWood = new Texture("Boxes/BoxWood.png");
        boxTungsten = new Texture("Boxes/BoxTungsten.png");
        boxPlatinum = new Texture("Boxes/BoxPlatinum.png");
        boxSilver = new Texture("Boxes/BoxSilver.png");
        boxCloud = new Texture("Boxes/BoxCloud.png");
        boxMoss = new Texture("Boxes/BoxMoss.png");
        boxPalladium = new Texture("Boxes/BoxPalladium.png");
        boxMythril = new Texture("Boxes/BoxMythril.png");
        boxAdamantite = new Texture("Boxes/BoxAdamantite.png");
        boxTitanium = new Texture("Boxes/BoxTitanium.png");
        boxWater = new Texture("Boxes/BoxWater.png");
        boxLava = new Texture("Boxes/BoxLava.png");
        boxHoney = new Texture("Boxes/BoxHoney.png");

        texLoad = new Texture("Load.jpg");

        texBack = new Texture("Back.jpg");
        texPlay = new Texture("player.jpg");

    }

    public static void initJSON(){
        JsonReader jrJson = new JsonReader();
        jvTile = jrJson.parse(Gdx.files.local("JSON/Tile.json"));

        jvGrass = jvTile.get("2");
        jvDirt = jvTile.get("3");
        jvStone = jvTile.get("5");
        jvTin = jvTile.get("6");
        jvCopper = jvTile.get("7");

        jvIron = jvTile.get("8");
        jvLead = jvTile.get("9");
        jvTungsten = jvTile.get("10");

        jvGold = jvTile.get("11");
        jvPlatinum = jvTile.get("12");
        jvSilver = jvTile.get("13");

        jvCrimtane = jvTile.get("16");
        jvDemonite = jvTile.get("17");

        jvCobalt = jvTile.get("18");
        jvPalladium = jvTile.get("19");
        jvMythril = jvTile.get("20");
        jvOrhicallum = jvTile.get("21");
        jvAdamantite = jvTile.get("22");
        jvTitanium = jvTile.get("23");

        jvWater = jvTile.get("24");
        jvLava = jvTile.get("25");
        jvHoney = jvTile.get("26");
        jvClout = jvTile.get("27");
    }

    @Override
    public void dispose() {
        boxGrass.dispose();
        boxStone.dispose();
        boxGrass.dispose();
        boxDirt.dispose();
        boxStone.dispose();
        boxCopper.dispose();
        boxTin.dispose();
        boxIron.dispose();
        boxLead.dispose();
        boxGold.dispose();
        boxCobalt.dispose();
        boxDemonite.dispose();
        boxCrimtane.dispose();
        boxOrhicallum.dispose();
        boxClout.dispose();
        boxLeaves.dispose();
        boxWood.dispose();
        boxTungsten.dispose();
        boxPlatinum.dispose();
        boxSilver.dispose();
        boxCloud.dispose();
        boxMoss.dispose();
        boxPalladium.dispose();
        boxMythril.dispose();
        boxAdamantite.dispose();
        boxTitanium.dispose();
        boxWater.dispose();
        boxLava.dispose();
        boxHoney.dispose();

        texPlay.dispose();
        texBack.dispose();
        texLoad.dispose();
    }
}
