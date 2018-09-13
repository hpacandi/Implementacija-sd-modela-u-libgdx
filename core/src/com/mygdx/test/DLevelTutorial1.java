package com.mygdx.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DLevelTutorial1 extends DLevelMaster {

    public DLevelTutorial1(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getLabel("Prije nego sto odlucite donjeti veliku odluku u zivotu i uzeti kredit za veliku investiciju koju planirate uciniti, bitno je informirati se i pripremiti na neplanirane i skrivene troskove koji se mogu pojaviti. Ova aplikacija omogucava vam jednostavan izracun otplate kredita i varijabli povezanih za otplatu kao sto su anuitet, iznos kamata, mjesecni omjer kamate i otplatne kvote i drugih. Simulacija je prikazana grafickim prikazom baziranom na sistem dinamickom modelu. ");
        option1.setWrap(true);
        Label option2 = getLabel("Pretpostavimo da ste odlucili velicinu iznosa koji cete financirant kreditom. To je iznos koji vam banka posuduje odmah i koji otplacujete kroz odredeno vrijeme. Njega cemo u modelu prikazivat slikom ispod i vi ga unosite u model.");
        option2.setWrap(true);

        table.row().height(160).width(vp.getScreenWidth());
        table.add(getLabel(""));
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option1);
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getLabel(""));
        table.row().height(160).width(vp.getScreenWidth() - 200);
        table.add(option2);
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getLabel(""));
        table.row().height(vp.getScreenWidth() - 500).width(vp.getScreenWidth() - 500);
        table.add(new Image(new Texture("imgs/amount.png")));
        return table;
    }

    @Override
    void playSimulation(double amount, double time, double rate) {
    }

    @Override
    protected Table createTopBarTable() {
        Table topBarTable = new Table();
        topBarTable.setDebug(Utils.isDebugging);
        topBarTable.row().expand();
        topBarTable.add(new Image(new Texture("imgs/back.png")));
        topBarTable.add(new Image(new Texture("imgs/blank.png")));
        topBarTable.add(new Image(new Texture("imgs/blank.png")));
        topBarTable.add(new Image(new Texture(getNextScreenIcon())));
        topBarTable.row().height(10);
        topBarTable.add(new Image(new Texture("imgs/black32.png"))).colspan(4).fill();

        Pixmap pm = fillWithColor(Color.LIGHT_GRAY);
        topBarTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm))));

        topBarTable.getCells().get(0).getActor().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                previousScreen();
            }
        });

        topBarTable.getCells().get(3).getActor().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                nextScreen();
            }
        });

        return topBarTable;
    }

    @Override
    protected Table createBottomBarTable() {
        return null;
    }

    protected void previousScreen() {
        DGame.setScreen(new DLevelMainMenu(DGame));
    }

    protected void nextScreen() {
        DGame.setScreen(new DLevelTutorial2(DGame));
    }

    protected String getNextScreenIcon() {
        return "imgs/next.png";
    }

}
