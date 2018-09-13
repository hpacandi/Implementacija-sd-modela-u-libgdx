package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DLevelTutorial4 extends DLevelTutorial1 {

    public DLevelTutorial4(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getLabel("Isti iznos novca koji ste pozajmili od banke moze se prikazati i kao dug koji imate prema banci. Cilj modela ove aplikacije je otplata tog duga te dovodenje njegove vrijednosti na nulu. Dug se umanjuje za iznos otplatne kvote koja raste svaki mjesec i stoga otplata nije linearna.");
        option1.setWrap(true);
        Label option2 = getLabel("Ostatak duga prikazan je ikonom ispod i njegova vrijednost se smanjuje kroz model dok ne postigne nulu u zadnjem razdoblju odnosno kada je dug otplacen.");
        option2.setWrap(true);

        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option1);
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option2);
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getLabel(""));
        table.row().height(vp.getScreenWidth() - 500).width(vp.getScreenWidth() - 500);
        table.add(new Image(new Texture("imgs/moneybag.png")));
        return table;
    }

    @Override
    protected void previousScreen() {
        DGame.setScreen(new DLevelTutorial3(DGame));
    }

    @Override
    protected void nextScreen() {
        DGame.setScreen(new DLevelTutorial5(DGame));
    }
}
