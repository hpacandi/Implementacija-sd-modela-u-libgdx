package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DLevelTutorial6 extends DLevelTutorial1 {

    public DLevelTutorial6(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getLabel("Akumuliranjem iznosa mjesecnih kamata, na kraju simulacije dobiva se iznos ukupnih kamata koje cete platiti na odredenu svotu novca kroz dano vrijeme uz danu kamatnu stopu. Ta vrijednost predstavlja indikator koliko vas zapravo kosta kredit i koliko cete platiti banci za posudbu novca - kredita.");
        option1.setWrap(true);
        Label option2 = getLabel("");
        option2.setWrap(true);

        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option1);
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option2);
        table.row().height(vp.getScreenWidth() - 500).width(vp.getScreenWidth() - 500);
        table.add(new Image(new Texture("imgs/ukupnekamate.png")));
        return table;
    }

    @Override
    protected void previousScreen() {
        DGame.setScreen(new DLevelTutorial5(DGame));
    }

    @Override
    protected void nextScreen() {
        DGame.setScreen(new DLevelTutorial7(DGame));
    }
}
