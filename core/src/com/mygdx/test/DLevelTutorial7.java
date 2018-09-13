package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DLevelTutorial7 extends DLevelTutorial1 {

    public DLevelTutorial7(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getLabel("Prije nego sto se krenete koristiti sa cijelim modelom, upoznat cu vas sa opcijama kojima se koristite za rukovanje modelom. One su predstavljene na slici ispod.");
        option1.setWrap(true);
        Label option2 = getLabel("Prva opcija otvara izbornik sa opcijama unosa gdje unosite pocetne vrijednosti kredita (iznos, kamatnu stopu i vrijeme) kao i brzinu simulacije.");
        option2.setWrap(true);
        Label option3 = getLabel("Druga opcija omogucava pregled stanja modela u bilo kojem razdoblju za unesene vrijednosti.");
        option3.setWrap(true);
        Label option4 = getLabel("Treca opcija simulira i animira model i na kraju simulacije prikazuje konacno stanje modela.");
        option4.setWrap(true);
        Label option5 = getLabel("Cetvrta opcija zatvara model.");
        option5.setWrap(true);


        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option1);
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(getLabel(""));
        table.row().height(150).width(765);
        table.add(new Image(new Texture("imgs/toolbar.png")));
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option2);
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option3);
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option4);
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option5);

        return table;
    }

    @Override
    protected void previousScreen() {
        DGame.setScreen(new DLevelTutorial6(DGame));
    }

    @Override
    protected void nextScreen() {
        DGame.setScreen(new DLevelTutorialModel(DGame));
    }
}
