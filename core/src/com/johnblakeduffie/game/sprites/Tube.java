package com.johnblakeduffie.game.sprites;

/**
 * Created by johnblakeduffie on 8/1/17.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Tube {
    private Texture leftTube , rightTube;
    private Vector2 posLeftTube, posRightTube;
    private Rectangle boundsLeft, boundsRight;
    private float yTop = 1600;
    Random rand = new Random();
    private int tubeRandInt;
    public AtomicInteger score = new AtomicInteger();

    public Tube(float y) {
        leftTube = new Texture("bird.png");
        rightTube = new Texture("bird.png");
        posLeftTube = new Vector2(-300, yTop);
        posRightTube = new Vector2(350, yTop);

        boundsLeft = new Rectangle(posLeftTube.x, posLeftTube.y, leftTube.getWidth(), leftTube.getHeight());
        boundsRight = new Rectangle(posRightTube.x, posRightTube.y, rightTube.getWidth(), rightTube.getHeight());
    }
    

    public Texture getLeftTube() {
        return leftTube;
    }

    public Texture getRightTube() {
        return rightTube;
    }

    public String getScore() {
        return String.valueOf(score.intValue());
    }


    public Vector2 getPosLeftTube() {
        posLeftTube.y = computeYPos(posLeftTube.y);
        return posLeftTube;
    }

    public Vector2 getPosRightTube() {
        posRightTube.y = computeYPos(posRightTube.y);
            return posRightTube;
        }

    private float computeYPos(float y) {
        float newY = y - 5;
        if (newY <= 0) {
            newY = yTop;
            changeTubeGap();
            score.incrementAndGet();
        }
        return newY;

    }

    public void changeTubeGap() {


        tubeRandInt = 1 + (int)(Math.random() * 3);

            if (tubeRandInt == 1) {

                posLeftTube = new Vector2(-300, yTop);   //LEFT GAP
                posRightTube = new Vector2(350, yTop);
                boundsLeft.setPosition(posLeftTube.x, posLeftTube.y);
                boundsRight.setPosition(posRightTube.x, posRightTube.y);
                tubeRandInt = 1 + (int)(Math.random() * 3);


            } else if (tubeRandInt == 2) {                //RIGHT GAP
                posLeftTube = new Vector2(100, yTop);
                posRightTube = new Vector2(750, yTop);
                boundsLeft.setPosition(posLeftTube.x, posLeftTube.y);
                boundsRight.setPosition(posRightTube.x, posRightTube.y);
                tubeRandInt = 1 + (int)(Math.random() * 3);


            } else if (tubeRandInt == 3) {
                posLeftTube = new Vector2(-150, yTop);   //MIDDLE GAP
                posRightTube = new Vector2(500, yTop);
                boundsLeft.setPosition(posLeftTube.x, posLeftTube.y);
                boundsRight.setPosition(posRightTube.x, posRightTube.y);
                tubeRandInt = 1 + (int)(Math.random() * 3);

            }


    }

    public boolean collides(Rectangle rectangle){
        return rectangle.overlaps(boundsLeft) || rectangle.overlaps(boundsRight);
    }




}
