package com.mygdx.test;

import com.badlogic.gdx.Game;

public class DGame extends Game {

    @Override
    public void create() {
        this.setScreen(new DLevelMainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
