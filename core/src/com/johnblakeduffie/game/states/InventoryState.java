package com.johnblakeduffie.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.johnblakeduffie.game.FlappyDemo;

/**
 * Created by johnblakeduffie on 10/2/17.
 */

public class InventoryState extends State{
    private Texture redUnlock;
    private Texture purpleUnlock;
    private Texture goldUnlock;
    private Sprite inventory;
    private int highScore;

    public InventoryState(GameStateManager gsm) {
        super(gsm);
        redUnlock = new Texture("JetpackJack_Weapon_Choice_Red_Only.png");
        purpleUnlock = new Texture("JetpackJack_Weapon_Choice_RedAndPurple.png");
        goldUnlock = new Texture("JetpackJack_Weapon_Choice_ALLUNLOCKS.png");

        //Get highscore from save file
        Preferences prefs = Gdx.app.getPreferences("JetpackJack");
        this.highScore = prefs.getInteger("highscore", 0);

        //Check to see which Jack Texture to use with the highscore the user has
        if (highScore < 10) {
            inventory = new Sprite(redUnlock);
        }
        if (highScore > 10 && highScore < 20){
            inventory = new Sprite(purpleUnlock);
        }
        if (highScore > 20){
            inventory = new Sprite(goldUnlock);
        }


    }

    @Override
    public void handleInput(){
        //Check which weapon user picks, then start a PlayState using that weapon
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(inventory, -192, -150, 800, 1600);
        sb.end();

        if (highScore < 10) {
            inventory = new Sprite(redUnlock);
            if (Gdx.input.getX() <= 100 && Gdx.input.getX() >= 300 && Gdx.input.getY() >= 300 && Gdx.input.getY() <= 400){
                if (Gdx.input.isTouched()){
                    gsm.set(new PlayState(gsm));
                }
            }
        }
        if (highScore > 10 && highScore < 20){
            inventory = new Sprite(purpleUnlock);
            if (Gdx.input.getX() <= 100 && Gdx.input.getX() >= 300 && Gdx.input.getY() >= 300 && Gdx.input.getY() <= 400){
                if (Gdx.input.isTouched()){
                    gsm.set(new PlayState(gsm));
                }
            }

            if (Gdx.input.getX() <= 200 && Gdx.input.getX() >= 400 && Gdx.input.getY() >= 300 && Gdx.input.getY() <= 400){
                if (Gdx.input.isTouched()){
                    gsm.set(new PlayState(gsm));
                }
            }
        }
        if (highScore > 20){
            inventory = new Sprite(goldUnlock);

            if (Gdx.input.isTouched()){
                    if (Gdx.input.getX() <= 100 && Gdx.input.getX() >= 300 && Gdx.input.getY() >= 300 && Gdx.input.getY() <= 400){
                    gsm.set(new PlayState(gsm));
                }
            }

            if (Gdx.input.isTouched()){
                    if (Gdx.input.getX() <= 200 && Gdx.input.getX() >= 400 && Gdx.input.getY() >= 300 && Gdx.input.getY() <= 400){
                    gsm.set(new PlayState(gsm));
                }
            }

            if (Gdx.input.isTouched()){
                    if (Gdx.input.getX() <= 300 && Gdx.input.getX() >= 500 && Gdx.input.getY() >= 300 && Gdx.input.getY() <= 400){
                    gsm.set(new PlayState(gsm));
                }
            }
        }


    }


    @Override
    public void dispose(){
        //inventory.dispose();
        //playBtn.dispose();
        //menuStateMusic.dispose();
    }
}
