package com.johnblakeduffie.game.sprites;

/**
 * Created by johnblakeduffie on 8/1/17.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Tube {
    private Texture leftAsteroid , rightAsteroid;
    public Vector2 posLeftAsteroid, posRightAsteroid;
    private float yTop = 1600;
    public int AsteroidRandInt;
    public int Asteroid_X, Asteroid_2_X;
    public int AsteroidSpeed = 3;
    public AtomicInteger score = new AtomicInteger();
    private int asteroidSpeed = 5;
    private int levelInt = 0;

    public Tube(float y) {
        leftAsteroid = new Texture("JetpackJack_Asteroid_1.png");
        rightAsteroid = new Texture("JetpackJack_Asteroid_2.png");
        posLeftAsteroid = new Vector2(Asteroid_X, yTop);
        posRightAsteroid = new Vector2(Asteroid_2_X, yTop);
        //posLeftAsteroid = new Vector2(50, yTop);
        //posRightAsteroid = new Vector2(800, yTop);

    }


    public Texture getLeftAsteroid() {
        return leftAsteroid;
    }

    public Texture getRightAsteroid() {
        return rightAsteroid;
    }


    public String getScore() {
        return String.valueOf(score.intValue());
    }


    public Vector2 getPosLeftAsteroid() {
        posLeftAsteroid.y = computeYPos(posLeftAsteroid.y);
        return posLeftAsteroid;
    }

    public Vector2 getPosRightAsteroid() {
        posRightAsteroid.y = computeYPos(posRightAsteroid.y);
        return posRightAsteroid;
    }


    public float computeYPos(float y) {
        //if levelInt is a multiple of 25, then increase the asteroidSpeed by the multiple of 25

        if (levelInt % 25 == 0)
        {
            asteroidSpeed += levelInt/25 ;
        }

        float newY = y - asteroidSpeed;


        if (newY <= 0) {
            newY = yTop;

          levelInt ++;

            changeAsteroid();

            AsteroidRandInt = 1 + (int)(Math.random() * 6);

            score.incrementAndGet();
        }
        return newY;

    }

    public void changeAsteroid() {


            if (AsteroidRandInt == 1) {

                Asteroid_X = 100;
                Asteroid_2_X = 500;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                rightAsteroid = new Texture ("JetpackJack_Asteroid_3and4.png");



            } else if (AsteroidRandInt == 2) {

                Asteroid_X = 200;
                Asteroid_2_X = 600;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                rightAsteroid = new Texture ("JetpackJack_Asteroid_2.png");



            } else if (AsteroidRandInt == 3) {

                Asteroid_X = 200;
                Asteroid_2_X = 670;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                rightAsteroid = new Texture ("JetpackJack_Asteroid_1.png");



            } else if (AsteroidRandInt == 4) {

                Asteroid_X = 60;
                Asteroid_2_X = 700;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                rightAsteroid = new Texture ("JetpackJack_Asteroid_3and4.png");



            } else if (AsteroidRandInt == 5) {

                Asteroid_X = 0;
                Asteroid_2_X = 500;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                rightAsteroid = new Texture ("JetpackJack_Asteroid_2.png");


            } else if (AsteroidRandInt == 6) {

                Asteroid_X = 100;
                Asteroid_2_X = 800;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                rightAsteroid = new Texture ("JetpackJack_Asteroid_2.png");



            }


    }




}
