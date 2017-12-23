package com.johnblakeduffie.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.johnblakeduffie.game.sprites.Tube;


/**
 * Created by johnblakeduffie on 8/14/17.
 */

public class GameOverState extends State{
    private Texture background;
    private Texture playBtn;
    private Tube tube;
    private Sprite playerGameOver;
    private Texture jack;
    private float XPos;
    private Vector2 posPlayerGameOver;
    public int opacityValue;
    public Matrix4 textRotation;
    private int highScore;
    BitmapFont font;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("JetpackJack_GameOver.png");
        //playBtn = new Texture("playBtn.png");
        tube = new Tube(0);
        jack = new Texture("JetpackJackCharacter.png");

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(5);

        posPlayerGameOver = new Vector2(XPos, 200);

        playerGameOver = new Sprite(jack);
        playerGameOver.setRotation(75);
        playerGameOver.setPosition(-100, 200);
        playerGameOver.setSize(190, 330);

        textRotation = new Matrix4();
        textRotation.setToRotation( new Vector3(-80, -150, 0), 180);

        //Get highscore from save file
        Preferences prefs = Gdx.app.getPreferences("JetpackJack");
        this.highScore = prefs.getInteger("highscore", 0);

        //Check if score beats highscore
        if (tube.getScore() > highScore){
            prefs.putInteger("highscore", tube.getScore());
            prefs.flush();
        }
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

    public float computeXPos(float x) {

        float XPos = x + 50;
        return XPos;
    }

    public int opacityValue() {

        if (opacityValue <= 500){
            opacityValue += 50;
        }

        if (opacityValue >= 500){
            opacityValue -= 50;
        }

        return opacityValue;
    }


    public Vector2 getPosPlayerGameOver() {
        posPlayerGameOver.x = computeXPos(posPlayerGameOver.x);
        return posPlayerGameOver;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, -80, -150, 1150, 1850);
        Gdx.gl.glClearColor(.135f, .206f, .235f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        opacityValue = 0;
        sb.draw(playerGameOver, getPosPlayerGameOver().x, 200, 190, 330);


        sb.end();


        //gameOverScore.setTransformMatrix(textRotation);
        //gameOverScore.begin();
        //font.draw(gameOverScore, tube.getScore(), 600, 1070);
        //gameOverScore.end();

    }



    @Override
    public void dispose(){
        background.dispose();
        //playBtn.dispose();
    }




}
