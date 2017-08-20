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
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.johnblakeduffie.game.FlappyDemo;
import com.johnblakeduffie.game.sprites.Bird;
import com.johnblakeduffie.game.sprites.Tube;
import com.sun.org.apache.regexp.internal.RE;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Contact;


/**
 * Created by johnblakeduffie on 7/16/17.
 */

public class PlayState extends State {
    private Texture bg;
    private Sprite player;
    private Sprite backGround;
    private Rectangle playerBounds;
    private Rectangle leftAsteroidBounds, rightAsteroidBounds, rightViewBounds;
    private Texture jack;
    private int backGround_y = - 50;
    private Tube tube;

    //private Rectangle buttonLeft, buttonMiddle, buttonRight; //BUTTONS TO MOVE
    BitmapFont font;
    public int levelInt = 0;
    public int AsteroidSpeedIncrementer = 0;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH * 2, FlappyDemo.HEIGHT * 2);
        bg = new Texture("JetpackJack_stars.png");
        jack = new Texture("JetpackJackCharacter.png");

        player = new Sprite(jack);
        player.setRotation(0);
        player.setPosition(180, 200);
        player.setSize(190, 330);

        tube = new Tube(0);


        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(10);
        //buttonLeft = new Rectangle(0,0  ,FlappyDemo.WIDTH / 3, FlappyDemo.HEIGHT );


        backGround = new Sprite(bg);
        backGround.setSize(1050, 2500);
        backGround.setPosition(-30, backGround_y);

    }


    @Override
    protected void handleInput() {


       // if (!playerBounds.overlaps(leftViewBounds)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player.translateX(-26f);
            }
            if (!playerBounds.overlaps(rightViewBounds)) {
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    player.translateX(26f);
                }
            }
        }



    public void GameOver() {
        gsm.set(new GameOverState(gsm));
    }


    @Override
    public void update(float dt) {
        handleInput();


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        backGround_y -= 5;
        backGround.draw(sb);

        //sb.draw(bg, -70, -50, 580, 880);
        player.draw(sb);



        sb.draw(tube.getLeftAsteroid(), tube.getPosLeftAsteroid().x, tube.getPosLeftAsteroid().y);


        sb.draw(tube.getRightAsteroid(), tube.getPosRightAsteroid().x, tube.getPosRightAsteroid().y);




        playerBounds = new Rectangle(player.getX() + 95, player.getY(), player.getWidth() - 180, player.getHeight() - 40);
        leftAsteroidBounds = new Rectangle(tube.getPosLeftAsteroid().x,
                tube.getPosLeftAsteroid().y,
                tube.getLeftAsteroid().getWidth(),
                tube.getLeftAsteroid().getHeight());
        rightAsteroidBounds = new Rectangle(tube.getPosRightAsteroid().x,
                tube.getPosRightAsteroid().y,
                tube.getRightAsteroid().getWidth(),
                tube.getRightAsteroid().getHeight());

        //leftViewBounds = new Rectangle(0, 0, 10, 800);
        rightViewBounds = new Rectangle(865, 0, 100, 800);


        if (playerBounds.overlaps(leftAsteroidBounds) || (playerBounds.overlaps(rightAsteroidBounds))) {

            GameOver();

        }

        font.draw(sb, tube.getScore(),
                (450),
                (1500));


        sb.end();
    }

    public void speedUpGame() {
        tube.AsteroidSpeed += 3;
    }


    @Override
    public void dispose() {

    }

}
