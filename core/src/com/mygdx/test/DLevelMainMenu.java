package com.mygdx.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class DLevelMainMenu extends DLevelMaster {

    public DLevelMainMenu(com.mygdx.test.DGame DGame) {
        super(DGame);
    }

    @Override
    Table createMainTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());

        Label option1 = getBigLabel("TUTORIAL");
        option1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                DGame.setScreen(new DLevelTutorial1(DGame));
            }
        });
        Label option2 = getBigLabel("MODEL");
        option2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                DGame.setScreen(new DLevel1(DGame));
            }
        });

        table.row().height(vp.getScreenWidth() - 500).width(vp.getScreenWidth() - 500);
        table.add(new Image(new Texture("imgs/mainmenu.png")));
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getBigLabel(""));
        table.row().height(160).width(vp.getScreenWidth());
        table.add(option1);
        table.row().height(10).width(vp.getScreenWidth() - 400);
        table.add(new Image(new Texture("imgs/black32.png"))).fill();
        table.row().height(160).width(vp.getScreenWidth());
        table.add(option2);
        table.row().height(10).width(vp.getScreenWidth() - 600);
        table.add(new Image(new Texture("imgs/black32.png"))).fill();
        return table;
    }

    @Override
    protected Table createTopBarTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());
        table.row().height(160).width(vp.getScreenWidth() - 200);
        Label title = getBigLabel("Aplikacija kamatnog racuna na temelju sistem-dinamickog modela");
        title.setWrap(true);
        table.add(title);
        return table;
    }

    @Override
    protected Table createBottomBarTable() {
        Table table = new Table();
        table.setPosition(vp.getScreenWidth(), vp.getScreenHeight());
        table.row().height(160).width(vp.getScreenWidth());
        table.add(getSmallLabel("Hugo Pacandi"));
        return table;
    }

    @Override
    void playSimulation(double amount, double time, double rate) {
        //no sim
    }

    private Label getBigLabel(String text) {
        Label lbl = new Label(text, skin);
        lbl.setSize(CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH);
        lbl.setAlignment(Align.bottom);
        lbl.setFontScale(3.5f);
        return lbl;
    }

    private Label getSmallLabel(String text) {
        Label lbl = new Label(text, skin);
        lbl.setSize(CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH);
        lbl.setAlignment(Align.bottom);
        lbl.setFontScale(2.5f);
        return lbl;
    }
}
