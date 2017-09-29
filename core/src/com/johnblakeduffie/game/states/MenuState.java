package com.johnblakeduffie.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.johnblakeduffie.game.FlappyDemo;


/**
 * Created by johnblakeduffie on 7/15/17.
 */

public class MenuState extends State{
    //cam.setToOrtho(false, FlappyDemo.WIDTH * 2, FlappyDemo.HEIGHT * 2);
    private Texture background;
    private Texture playBtn;
    Music menuStateMusic;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("JetpackJackmenuScreen.png");
        //playBtn = new Texture("playBtn.png");
        menuStateMusic = Gdx.audio.newMusic(Gdx.files.internal("In Downhole.mp3"));
        menuStateMusic.setLooping(true);
        menuStateMusic.setVolume(.5f);
        menuStateMusic.play();

    }

    @Override
    public void handleInput() {
        //if play game pressed, start a game with the default red lazer gun
        //if choose weapon pressed, set a new state for the weapon choice
        //the background of weapon choice state depends on the weapon you have unlocked that is saved in game data
        //when you click a weapon, start a game with the chosen weapon

            if (Gdx.input.justTouched()) {
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
        //sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, -192, -150, 1740, 2820);
        //sb.draw(playBtn,  (FlappyDemo.WIDTH / 2) - (playBtn.getWidth() / 2), FlappyDemo.HEIGHT / 3);
        sb.end();


    }

    @Override
    public void dispose(){
        background.dispose();
        //playBtn.dispose();
        menuStateMusic.dispose();
    }
}
