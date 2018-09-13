package com.mygdx.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class DLevelMaster implements Screen {

    protected static Integer CELL_SIZE_HEIGHT = 180;
    protected static Integer CELL_SIZE_WIDTH = 144;

    DGame DGame;
    Skin skin;
    protected Stage stage;
    ScreenViewport vp;

    Label itemTitle;
    Label itemFormula;
    Label itemDescription;

    Slider timeSlider;
    Label timeLabel;
    Slider rateSlider;
    Label rateLabel;
    TextField amountTextField;
    Actor playButton;

    public DLevelMaster(DGame DGame) {
        this.DGame = DGame;
        vp = new ScreenViewport();
        stage = new Stage(vp);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal(Utils.DSkinPath));
    }

    @Override
    public void show() {

        Table containerTable = createContainerTable();
        containerTable.setFillParent(true);

        Table topBarTable = createTopBarTable();
        Table bottomBarTable = createBottomBarTable();
        Table mainTable = createMainTable();

        containerTable.row();
        containerTable.add(topBarTable).width(vp.getScreenWidth()).height(vp.getScreenHeight() * 0.10f);
        containerTable.row();
        containerTable.add(mainTable).width(vp.getScreenWidth()).height(vp.getScreenHeight() * 0.75f);
        containerTable.row();
        containerTable.add(bottomBarTable).width(vp.getScreenWidth()).height(vp.getScreenHeight() * 0.15f);

        NavigationDrawer.NavigationDrawerListener listener = new NavigationDrawer.NavigationDrawerListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onRun() {
            }

            @Override
            public void onFinish(float camX) {
            }
        };
        NavigationDrawer.initialize(stage, listener);

        setupDrawerBackground();

        stage.addActor(containerTable);

        setupDrawerLayout();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(204 / 255f, 230 / 255f, 255 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    private Table createContainerTable() {

        Table containerTable = new Table();
        containerTable.setFillParent(true);
        containerTable.align(Align.top);
        containerTable.setDebug(Utils.isDebugging);

        return containerTable;
    }

    protected Table createTopBarTable() {
        Table topBarTable = new Table();
        topBarTable.setDebug(Utils.isDebugging);
        topBarTable.row().expand();
        topBarTable.add(new Image(new Texture("imgs/sliders.png")));
        topBarTable.add(new Image(new Texture("imgs/range.png")));
        topBarTable.add(new Image(new Texture("imgs/play.png")));
        topBarTable.add(new Image(new Texture("imgs/close.png")));
        topBarTable.row().height(10);
        topBarTable.add(new Image(new Texture("imgs/black32.png"))).colspan(4).fill();

        Pixmap pm = fillWithColor(Color.LIGHT_GRAY);
        topBarTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm))));

        topBarTable.getCells().get(0).getActor().addListener(new ClickListener() {
            private int clicked = 0;

            public void clicked(InputEvent event, float x, float y) {
                if (clicked % 2 == 0) {
                    clicked++;
                    NavigationDrawer.show(true);
                } else {
                    clicked++;
                    NavigationDrawer.show(false);
                }
            }
        });

        topBarTable.getCells().get(1).getActor().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                double amount = Double.valueOf(amountTextField.getText());
                double time = round(Double.parseDouble(timeLabel.getText().toString()), 1);
                double rate = round(Double.parseDouble(rateLabel.getText().toString()), 1);
                DGame.setScreen(new DLevelManual(DGame, amount, rate, time));
            }
        });

        playButton = topBarTable.getCells().get(2).getActor();
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String amountString = amountTextField.getText();
                if (amountString != null && !amountString.equals("")) {
                    double amount = Double.valueOf(amountString);
                    double time = round(Double.parseDouble(timeLabel.getText().toString()), 1);
                    double rate = round(Double.parseDouble(rateLabel.getText().toString()), 1);
                    if (amount > 999 && amount <= 5000000) playSimulation(amount, time, rate);
                    else displayDialog();
                }
            }
        });

        topBarTable.getCells().get(3).getActor().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                DGame.setScreen(new DLevelMainMenu(DGame));
            }
        });

        return topBarTable;
    }

    protected Table createBottomBarTable() {

        itemTitle = new Label("APLIKACIJA KAMATNOG RACUNA", skin);
        itemTitle.setFontScale(3f);

        itemDescription = new Label("KLIKOM NA SLIKU OTVARA SE OPIS", skin);
        itemDescription.setFontScale(2.3f);
        itemDescription.setWrap(true);

        float titleHeight = (vp.getScreenHeight() * 0.15f) - 10 - 200;

        Table bottomBarTable = new Table();
        bottomBarTable.row().height(10).width(vp.getScreenWidth());
        bottomBarTable.add(new Image(new Texture("imgs/black32.png"))).fill();
        bottomBarTable.row().height(titleHeight).width(vp.getScreenWidth() - 100);
        bottomBarTable.add(itemTitle);
        bottomBarTable.row().height(200).width(vp.getScreenWidth() - 100);
        bottomBarTable.add(itemDescription);

        Pixmap pm = fillWithColor(Color.LIGHT_GRAY);
        bottomBarTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm))));

        return bottomBarTable;
    }

    protected Pixmap fillWithColor(Color color) {
        Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGB565);
        pm.setColor(color);
        pm.fill();
        return pm;
    }

    abstract Table createMainTable();

    private void setupDrawerBackground() {
        Image line = new Image(new Texture("imgs/black32.png"));
        line.setX(-vp.getScreenX());
        line.setPosition(-10, 0);
        line.setSize(10, vp.getScreenHeight());
        stage.addActor(line);
    }

    private void setupDrawerLayout() {
        timeLabel = getLabel("5");
        timeLabel.setAlignment(Align.top);
        timeLabel.setFontScale(3f);
        timeSlider = new Slider(1, 50, 1, false, skin);
        timeSlider.setValue(5f);
        timeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                timeLabel.setText(String.valueOf(timeSlider.getValue()));
            }
        });

        rateLabel = getLabel("9.2");
        rateLabel.setAlignment(Align.top);
        rateLabel.setFontScale(3f);
        rateSlider = new Slider(0.1f, 20f, 0.1f, false, skin);
        rateSlider.setValue(9.2f);
        rateSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rateLabel.setText(String.valueOf(rateSlider.getValue()));
            }
        });

        Label speedLabel = getLabel(String.valueOf(Utils.totalSleep / 1000));
        speedLabel.setAlignment(Align.top);
        speedLabel.setFontScale(3f);
        Slider speedSlider = new Slider(10f, 100f, 1f, false, skin);
        speedSlider.setValue(Utils.totalSleep / 1000);
        speedSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                speedLabel.setText(String.valueOf(speedSlider.getValue()));
                Utils.totalSleep = Math.round(speedSlider.getValue() * 1000);
            }
        });

        amountTextField = new TextField("100000", skin);
        amountTextField.setSize(CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH);
        skin.getFont("font").getData().setScale(3f);
        amountTextField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        int drawerSize = vp.getScreenWidth() - vp.getScreenWidth() / 3;
        int padding = 50;

        Table table = new Table();
        table.setPosition(-vp.getScreenWidth() / 3, vp.getScreenHeight() / 2);
        table.row().height(160).width(drawerSize - padding);
        table.add(getLabel("IZNOS KREDITA (KN)"));
        table.row().height(160).width(drawerSize - padding);
        table.add(amountTextField);
        table.row().height(160).width(drawerSize - padding);
        table.add(getLabel("VRIJEME (GODINA)"));
        table.row().height(160).width(drawerSize - padding);
        table.add(timeSlider);
        table.row().height(160).width(drawerSize - padding);
        table.add(timeLabel);
        table.row().height(160).width(drawerSize - padding);
        table.add(getLabel("KAMATNA STOPA (%)"));
        table.row().height(160).width(drawerSize - padding);
        table.add(rateSlider);
        table.row().height(160).width(drawerSize - padding);
        table.add(rateLabel);
        table.row().height(160).width(drawerSize - padding);
        table.add(getLabel("BRZINA SIMULACIJE (SEKUNDA)"));
        table.row().height(160).width(drawerSize - padding);
        table.add(speedSlider);
        table.row().height(160).width(drawerSize - padding);
        table.add(speedLabel);

        stage.addActor(table);
    }

    protected Label getLabel(String text) {
        Label lbl = new Label(text, skin);
        lbl.setSize(CELL_SIZE_WIDTH, CELL_SIZE_HEIGHT - CELL_SIZE_WIDTH);
        lbl.setAlignment(Align.bottom);
        lbl.setFontScale(2.5f);
        return lbl;
    }

    abstract void playSimulation(double amount, double time, double rate);


    private void displayDialog() {
        Dialog dialog = new Dialog("Warning", skin, "default") {
            public void result(Object obj) {
            }
        };
        dialog.text("ENTER VALUE BETWEEN 1000 KN AND 5000000 KN!");
        dialog.button("Ok", false);
        dialog.show(stage);
        dialog.setPosition(vp.getScreenWidth(), vp.getScreenHeight());
        dialog.setModal(true);
        dialog.setSize(vp.getScreenWidth(), vp.getScreenHeight() * 0.10f);
    }

}