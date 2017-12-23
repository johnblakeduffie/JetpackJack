package com.johnblakeduffie.game.tools;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by johnblakeduffie on 9/7/17.
 */

public class ScrollingBackground{
    Texture image1, image2;
    float Image1_y, Image2_y;
    int speed;

    public ScrollingBackground() {
        image1 = new Texture("JetpackJack_stars.png");
        image2 = new Texture("JetpackJack_stars_2.png");

        Image1_y = 0;
        Image2_y = image1.getHeight();


    }

    public void updateAndRender (float deltaTime, SpriteBatch sb){

        Image1_y -= speed * deltaTime;
        Image2_y -= speed * deltaTime;

        if (Image1_y == -1600){
            Image1_y = image2.getHeight();
        }

        if (Image2_y == -1600){
            Image2_y = image1.getHeight();
        }

        sb.draw(image1, 0, Image1_y, 1200, 1800);
        sb.draw(image2, image1.getHeight(), Image2_y, 1200, 1800);
    }
}
