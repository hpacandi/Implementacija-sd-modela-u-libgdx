package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DLevelTutorial2 extends DLevelTutorial1 {

    public DLevelTutorial2(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getLabel("Druga vrijednost koju unasate u aplikaciju je vrijeme trajanja kredita u godinama. Odnosi se na vrijeme kroz koje cete otplacivati kredit i nakon kojeg ce vas dug prema banci nestati.");
        option1.setWrap(true);
        Label option2 = getLabel("Iznad je prikazana variabla vremena iz modela i njezina vrijednost unosi se na godisnoj razini iako se kredit otplacuje na mjesecnoj bazi. Model se takoder simulira na mjesecnoj bazi i moguce je vidjeti stanje svakog mjesecnog razdoblja kao i iznose koji se placaju.");
        option2.setWrap(true);

        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option1);
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getLabel(""));
        table.row().height(vp.getScreenWidth() - 500).width(vp.getScreenWidth() - 500);
        table.add(new Image(new Texture("imgs/time.png")));
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
        DGame.setScreen(new DLevelTutorial1(DGame));
    }

    @Override
    protected void nextScreen() {
        DGame.setScreen(new DLevelTutorial3(DGame));
    }
}
