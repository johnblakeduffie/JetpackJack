package com.johnblakeduffie.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.johnblakeduffie.game.FlappyDemo;
import com.johnblakeduffie.game.sprites.Bird;
import com.johnblakeduffie.game.sprites.Tube;
import com.sun.org.apache.regexp.internal.RE;


/**
 * Created by johnblakeduffie on 7/16/17.
 */

public class PlayState extends State {
    private Texture bg;
    private Sprite rectangle;
    private Sprite backGround;
    private Rectangle bounds;
    private Texture bird;
    private Tube tube;
    //private Rectangle buttonLeft, buttonMiddle, buttonRight; //BUTTONS TO MOVE
     BitmapFont font;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH * 2, FlappyDemo.HEIGHT * 2);
        bg = new Texture("jetpackGameMountainBG.jpg");
        bird = new Texture("JetpackJackCharacter.png");

        //bounds = new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);
        //buttonLeft = new Rectangle(0,0  ,FlappyDemo.WIDTH / 3, FlappyDemo.HEIGHT );


        backGround = new Sprite(bg);
        backGround.setSize(1000, 1600);

        rectangle = new Sprite(bird);
        rectangle.setRotation(0);
        rectangle.setPosition(180, 200);
        rectangle.setSize(190, 330);

        tube = new Tube(0);
    }

    public Rectangle getBounds(){
        return bounds;
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            //changeSize();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            rectangle.translateX(-18f);
        }   else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            rectangle.translateX(18f);
        }


        //rectange.translateX(5f) ---TO MOVE THE SPRITE
    }



    public void changeSize() {
        if (rectangle.getWidth() == 600) {
            rectangle.setSize(230, 150);
            rectangle.setPosition(350, 500);
        }
        else {
            rectangle.setSize(600, 150);
            rectangle.setPosition(180, 500);
        }

    }


    public void GameOver(){

    }

    @Override
    public void update(float dt) {
     handleInput();

        //if(tube.collides(rectangle.getBounds())){
            //gsm.set(new PlayState(gsm));
        //}

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        backGround.draw(sb);
        rectangle.draw(sb);
        sb.draw(tube.getLeftTube(), tube.getPosLeftTube().x, tube.getPosLeftTube().y);
        sb.draw(tube.getRightTube(), tube.getPosRightTube().x, tube.getPosRightTube().y);
        //font.draw(sb, tube.getScore(),
                //(rectangle.getX() + 30 ) ,
                //(rectangle.getY() ));
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
