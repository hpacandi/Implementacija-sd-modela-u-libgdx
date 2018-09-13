package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DLevelTutorial3 extends DLevelTutorial1 {

    public DLevelTutorial3(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getLabel("Kamatna stopa je vrijednost koja predstavlja dodatan iznos novca koji placate banci kao naknadu za posudeni novac. Izrazena je u postocima i unasa se u aplikaciju kako bi se izracunale daljne vrijednosti. ");
        option1.setWrap(true);
        Label option2 = getLabel("Slika predstavlja godisnju kamatnu stopu koju vam banka daje kod uzimanja kredita i ona se pretvara u relativnu kako bi se izracunao iznos koji placate na mjesecnoj bazi.");
        option2.setWrap(true);

        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option1);
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getLabel(""));
        table.row().height(vp.getScreenWidth() - 500).width(vp.getScreenWidth() - 500);
        table.add(new Image(new Texture("imgs/percent.png")));
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getLabel(""));
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option2);
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getLabel(""));
        return table;
    }

    @Override
    protected void previousScreen() {
        DGame.setScreen(new DLevelTutorial2(DGame));
    }

    @Override
    protected void nextScreen() {
        DGame.setScreen(new DLevelTutorial4(DGame));
    }
}
