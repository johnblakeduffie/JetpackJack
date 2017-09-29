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

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Tube {
    private Texture leftAsteroid, rightAsteroid;
    public Vector2 posLeftAsteroid, posRightAsteroid;
    private float yTop = 1600;
    public int AsteroidRandInt;
    public int Asteroid_X = 0, Asteroid_2_X = 800;
    public int progressSize;
    public int score;
    public int nextUnlock;
    private int asteroidSpeed = 5;


    public Tube(float y) {
        leftAsteroid = new Texture("JetpackJack_Asteroid_1.png");
        rightAsteroid = new Texture("JetpackJack_Asteroid_2.png");

        posLeftAsteroid = new Vector2(Asteroid_X, yTop);
        posRightAsteroid = new Vector2(Asteroid_2_X, yTop);


    }

    public Texture getLeftAsteroid() {
        return leftAsteroid;
    }


    //public String getScore() {
        //return String.valueOf(Math.round(score));
    //}

    public int getScore() {return score;}

    public int getAsteroid_X() {
        return Asteroid_X;
    }

    public Vector2 getPosLeftAsteroid() {
        posLeftAsteroid.y = computeYPos(posLeftAsteroid.y);
        return posLeftAsteroid;
    }


    public int getProgressSize() {
        return progressSize;
    }


    public float computeYPos(float y) {


        float newY = y - asteroidSpeed;


        if (newY <= -400) {

            progressSize = progressSize + 415/50;

            /*                   ^^^^adds to height of progress bar^^^^
            Change 50 to an int variable (nextUnlock) that is set depending on the BEST SCORE you have
            Example: best score of 50 -> int variable (nextUnlock) is 100
            */

            score = score + 1;

            AsteroidRandInt = 1 + (int)(Math.random() * 6);

            changeAsteroid();

            newY = yTop;


        }
        return newY;

    }

    public void changeAsteroid() {

        //if score is a multiple of 25, then increase the asteroidSpeed by the multiple of 25


        if (score <= 19)
            if (score % 4 == 0) {
                asteroidSpeed += score / 7;
            }


            if (AsteroidRandInt == 1) {

                Asteroid_X = 250;
                //Asteroid_2_X = 1500;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                //rightAsteroid = new Texture ("JetpackJack_Asteroid_3and4.png");
                //System.out.println("1");


            } else if (AsteroidRandInt == 2) {

                Asteroid_X = 550;
                //Asteroid_2_X = 650;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                //rightAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                //System.out.println("2");


            } else if (AsteroidRandInt == 3) {

                Asteroid_X = 500;
                //Asteroid_2_X = 1570;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_1.png");
                //rightAsteroid = new Texture ("JetpackJack_Asteroid_1.png");
                //System.out.println("3");


            } else if (AsteroidRandInt == 4) {

                Asteroid_X = 60;
                //Asteroid_2_X = 1500;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_3and4.png");
                //rightAsteroid = new Texture ("JetpackJack_Asteroid_3and4.png");
                //System.out.println("4");


            } else if (AsteroidRandInt == 5) {

                Asteroid_X = 720;
                //Asteroid_2_X = 720;
                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                //rightAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                //System.out.println("5");

            } else if (AsteroidRandInt == 6) {

                Asteroid_X = 0;
                Asteroid_2_X = 800;

                leftAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                rightAsteroid = new Texture ("JetpackJack_Asteroid_2.png");
                //System.out.println("6");


            }


    }




}
