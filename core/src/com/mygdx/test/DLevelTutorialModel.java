package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DLevelTutorialModel extends DLevelTutorial1 {

    public DLevelTutorialModel(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getLabel("Na samom kraju prikazan je cijeli model ove aplikacije gdje su varijable povezane i vrijednosti se prikazuju ispod njih. Na dnu ekrana prikazani su vam opisi varijabli kada na njih kliknete kako bi se podsjetili i ne bi ponovo morali prolaziti kroz tutorial.");
        option1.setWrap(true);

        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option1);
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(getLabel(""));
        table.row().height(1000).width(620);
        table.add(new Image(new Texture("imgs/model.png")));

        return table;
    }

    @Override
    protected void previousScreen() {
        DGame.setScreen(new DLevelTutorial7(DGame));
    }

    @Override
    protected void nextScreen() {
        DGame.setScreen(new DLevelMainMenu(DGame));
    }

    @Override
    protected String getNextScreenIcon() {
        return "imgs/close.png";
    }
}
