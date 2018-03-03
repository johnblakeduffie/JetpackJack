package com.johnblakeduffie.game.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.johnblakeduffie.game.FlappyDemo;
import com.johnblakeduffie.game.sprites.Bird;
import com.johnblakeduffie.game.sprites.Tube;
import com.sun.org.apache.regexp.internal.RE;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Contact;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.text.View;


/**
 * Created by johnblakeduffie on 7/16/17.
 */

public class PlayState extends State {

    private Texture bg;
    private Sprite player;
    private Sprite backGround;
    private Sprite progressBar;
    private Sprite Lazer;
    private Sprite Asteroid;
    private Rectangle playerBounds;
    private Rectangle AsteroidBounds, rightViewBounds, leftViewBounds, lazerBounds;
    private int lazerPos;
    private Texture jack;
    private Texture textureProgressBar;
    private Texture blackSquare;
    private Texture shootButton, shootButtonDown;
    private boolean shootButtonPressed;
    private Texture redLazer;
    private int highScore;
    private int weaponInt;
    public double newScore = 0;
    //private Rectangle ButtonLeft;
    private float gravity;
    private Tube tube;
    private Texture greenSquare;

    private Texture redJack;
    private Texture purpleJack;
    private Texture goldJack;

    Music playStateMusic;
    BitmapFont font;
    public int levelInt = 0;
    public int AsteroidSpeedIncrementer = 0;


    Texture image1, image2;
    float Image1_y, Image2_y;
    int speed = 300;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH * 2, FlappyDemo.HEIGHT * 2);


        bg = new Texture("JetpackJack_stars.png");

        blackSquare = new Texture("bird.png");
        greenSquare = new Texture("GreenSquare.png");

        shootButton = new Texture("JetpackJack_Shoot_Button_Down.png");
        shootButtonDown = new Texture("JetpackJack_Shoot_Button.png");

        redLazer = new Texture("JetpackJack_Starter_Red_gunshot.png");
        Lazer = new Sprite(redLazer);

        playStateMusic = Gdx.audio.newMusic(Gdx.files.internal("The Most Beautiful 120 BPM NES.mp3"));
        playStateMusic.setLooping(true);
        playStateMusic.setVolume(.5f);
        playStateMusic.play();

        textureProgressBar = new Texture("JetpackJack_GameProgressBar.png");
        progressBar = new Sprite(textureProgressBar);
        progressBar.setSize(2000, 1900);
        progressBar.setPosition(50,50);

        //Get highscore from save file
        Preferences prefs = Gdx.app.getPreferences("JetpackJack");
        this.highScore = prefs.getInteger("highscore", 0);

        redJack = new Texture("JetpackJackCharacter_flying_Red.png");
        purpleJack = new Texture("JetpackJackCharacter_flying_Purple.png");
        goldJack = new Texture("JetpackJackCharacter_flying_Gold.png");


        //Check to see which Jack Texture to use with the highscore the user has
        if (highScore < 10) {
            player = new Sprite(redJack);
        }
        if (highScore > 10 && highScore < 20){
            player = new Sprite(purpleJack);
        }
        if (highScore > 20){
            player = new Sprite(goldJack);
        }


        player.setRotation(75);
        player.setPosition(-270, 550);
        player.setSize(200, 250);

        tube = new Tube(0);

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(5);
        font.isFlipped();

        image1 = new Texture("JetpackJack_stars.png");
        image2 = new Texture("JetpackJack_stars_2.png");

        Image1_y = -29;
        Image2_y = 1600;





    }




    @Override
    protected void handleInput() {
        //shootButton coordinates (x , y , width , height): 580, 30, 350, 350
        if (!(Gdx.input.getX() > 300 && Gdx.input.getX() < 450 && Gdx.input.getY() > 650 && Gdx.input.getY() < 760)) {  //this is the square with the button to shoot


            if (Gdx.input.justTouched()) {
                gravity = -55f;
                player.translateX(gravity);
                gravity = -10f;
                //playerJumping = true;
            } else if (!Gdx.input.justTouched()) {
                player.translateX(gravity);
                gravity += .6f;
                //playerJumping = false;

            }


        }

        if (Gdx.input.isTouched()){
            if (Gdx.input.getX() > 300 && Gdx.input.getX() < 450 && Gdx.input.getY() > 650 && Gdx.input.getY() < 760) {
                shootButtonPressed = true;
                tube.changeAsteroid();

            }

            else {
                shootButtonPressed = false;
            }

        }

    }


    public void GameOver() {
        dispose();
        gsm.set(new GameOverState(gsm));
    }



    @Override
    public void update(float dt) {
        handleInput();

        Image1_y -= speed * dt;
        Image2_y -= speed * dt;

    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();

        //SCROLLING BACKGROUND
        //If the first image has completely passed the screen, reposition to front;
        if (Image1_y <= -1729) {
            Image1_y = 1600;
        }
        //If the second image has completely passed the screen, reposition to front;
        if (Image2_y <= -1729) {
            Image2_y = 1600;
        }
            sb.draw(image1, -100, Image1_y, 1200, 1800);
            sb.draw(image2, -100, Image2_y, 1200, 1800);

        //DRAW PLAYER
        player.draw(sb);

        //BUTTONS TO SHOOT
        sb.draw(shootButton, 580, 30, 350, 350);

        //PLAYER COLLISION BOUNDARY
        playerBounds = new Rectangle(player.getX() + 650, player.getY() - 50, player.getWidth(), player.getHeight() -200);

        //DRAW THE LAZERS
        Lazer.setPosition(player.getX() + 688, 620);
        Lazer.setSize(100, 1000);

        //COLLISION BOUNDARY FOR LAZER
        lazerBounds = new Rectangle(Lazer.getX(),
                Lazer.getY(),
                Lazer.getWidth(),
                Lazer.getHeight());

        if (shootButtonPressed){
                Lazer.draw(sb);
                sb.draw(shootButtonDown, 580, 30, 350, 350);

            //LAZER/ASTEROID COLLISION
            if (lazerBounds.overlaps(AsteroidBounds)) {
                System.out.println("shot the asteroid");
                tube.posLeftAsteroid.y -= 1800;
                //move asteroid below the screen so tube.changeAsteroid() is called
                //when tube.changeAsteroid() is called, the asteroid changes position, texture

            }

        }

        //DRAW THE ASTEROIDS
        Asteroid = new Sprite(tube.getLeftAsteroid());
        Asteroid.setPosition(tube.getAsteroid_X(), tube.getPosLeftAsteroid().y);
        Asteroid.draw(sb);

        //DRAW AND UPDATE PROGRESSBAR
        progressBar.draw(sb);
        sb.draw(greenSquare, 94, 90, 63, tube.getProgressSize());

        AsteroidBounds = new Rectangle(tube.getAsteroid_X(),
                    tube.getPosLeftAsteroid().y ,
                    tube.getLeftAsteroid().getWidth(),
                    tube.getLeftAsteroid().getHeight());



        //                                    --- COLLISION DETECTION VISUALIZED---


        /*sb.draw(blackSquare, player.getX() + 650, player.getY(),  player.getWidth() , player.getHeight() -200 );

        sb.draw(blackSquare, tube.getAsteroid_X() ,
                tube.getPosLeftAsteroid().y ,
                tube.getLeftAsteroid().getWidth(),
                tube.getLeftAsteroid().getHeight() );*/


        //                                   --- COLLISION DETECTION VISUALIZED---



        //IF THEY COLLIDE THEN CALL GAME OVER STATE
        if (playerBounds.overlaps(AsteroidBounds) || player.getX() >= 900 || player.getX() <= -650) {

            GameOver();
            //font.draw(Integer.toString(tube.getScore()));

        }

        sb.end();

    }



    @Override
    public void dispose() {
      playStateMusic.dispose();
    }

}
