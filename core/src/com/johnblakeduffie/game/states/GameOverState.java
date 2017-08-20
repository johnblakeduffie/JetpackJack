package com.johnblakeduffie.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by johnblakeduffie on 8/14/17.
 */


public class GameOverState extends State{
    private Texture background;
    private Texture playBtn;
    public GameOverState(GameStateManager gsm) {
        super(gsm);
        //background = new Texture("menuScreen.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //sb.draw(background, -520,-50, 1050, 850);
        Gdx.gl.glClearColor(.135f, .206f, .235f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.draw(playBtn, 380, 800);
        sb.end();
    }

    @Override
    public void dispose(){
        //background.dispose();
        playBtn.dispose();
    }
}
