package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DLevelTutorial5 extends DLevelTutorial1 {

    public DLevelTutorial5(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getLabel("Iz unesenih vrijednosti iznosa kredita, kamatne stope i vremena otplate, racuna se anuitet koji predstavlja iznos koji mjesecno placate banci. On je zbroj mjesecnog iznosa otplate kamate i mjesecnog iznosta otplate duga.");
        option1.setWrap(true);
        Label option2 = getLabel("Mjesecne kamate su dio anuiteta koji placate banci kao naknadu za kredit i u pocetku su velik dio anuiteta koji kroz vrijeme pada.");
        option2.setWrap(true);
        Label option3 = getLabel("Otplatna kvota je iznos kojim se otplacuje kredit (ostatak duga) na mjesecnoj razini. Iznos je isprva mali i raste kroz vrijeme, obrnuto od mjesecnih kamata.");
        option3.setWrap(true);

        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option1);
        table.row().height(vp.getScreenWidth() - 800).width(vp.getScreenWidth() - 800);
        table.add(new Image(new Texture("imgs/anuitet.png")));
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option2);
        table.row().height(vp.getScreenWidth() - 800).width(vp.getScreenWidth() - 800);
        table.add(new Image(new Texture("imgs/kamate.png")));
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option3);
        table.row().height(vp.getScreenWidth() - 800).width(vp.getScreenWidth() - 800);
        table.add(new Image(new Texture("imgs/money.png")));
        return table;
    }

    @Override
    protected void previousScreen() {
        DGame.setScreen(new DLevelTutorial4(DGame));
    }

    @Override
    protected void nextScreen() {
        DGame.setScreen(new DLevelTutorial6(DGame));
    }
}
