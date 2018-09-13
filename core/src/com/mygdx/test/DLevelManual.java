package com.mygdx.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DLevelManual extends DLevel1 {

    ArrayList<Otplata> data;
    double amount;
    double rate;
    double time;


    public DLevelManual(com.mygdx.test.DGame DGame, double amount, double rate, double time) {
        super(DGame);
        this.amount = amount;
        this.rate = rate;
        this.time = time;
        this.data = calculate(amount, rate, time);
    }

    @Override
    protected Table createTopBarTable() {
        Label counter = new Label("0", skin);
        counter.setFontScale(5f);
        counter.setAlignment(Align.center);

        Table topBarTable = new Table();
        topBarTable.setDebug(Utils.isDebugging);
        topBarTable.row().expand().padLeft(50);

        Slider simSlider = new Slider(0f, data.size() - 1, 1f, false, skin);
        simSlider.setValue(0);
        simSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                displayDataForInterval(KEY_SIMULATION_TIME, data.get((int) simSlider.getValue()).getRazdoblje());
                displayDataForInterval(KEY_STOCK_OSTATAK_DUGA, data.get((int) simSlider.getValue()).getOstatakDuga());
                displayDataForInterval(KEY_VARIABLE_KAMATE, data.get((int) simSlider.getValue()).getKamate());
                displayDataForInterval(KEY_VARIABLE_KVOTA, data.get((int) simSlider.getValue()).getKvota());
                displayDataForInterval(KEY_VARIABLE_KAMATE_TOTAL, data.get((int) simSlider.getValue()).getKamateTotal());
                displayDataForInterval(KEY_VARIABLE_ANUITET, data.get((int) simSlider.getValue()).getAnuitet());

                displayDataForInterval(KEY_VARIABLE_TIME, time);
                displayDataForInterval(KEY_VARIABLE_RATE, rate);
                displayDataForInterval(KEY_VARIABLE_AMOUNT, amount);
                displayDataForInterval(KEY_VARIABLE_RELATIVNA_KAM_STOPA, (dekurzivniKamatniFaktor(rate) - 1) * 1000);
            }
        });

        topBarTable.add(simSlider).colspan(3).fill();
        topBarTable.add(new Image(new Texture("imgs/close.png")));
        topBarTable.row().height(10);
        topBarTable.add(new Image(new Texture("imgs/black32.png"))).colspan(4).fill();
        topBarTable.getCells().get(1).getActor().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                DGame.setScreen(new DLevel1(DGame));
            }
        });

        Pixmap pm = fillWithColor(Color.LIGHT_GRAY);
        topBarTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm))));
        return topBarTable;
    }

    private void displayDataForInterval(String key, double value) {
        Label label = grpHolder.get(key).findActor(key + "LBL");
        DecimalFormat df = new DecimalFormat("#0.00");
        d.updateCounters(label, df.format(value));
    }
}
