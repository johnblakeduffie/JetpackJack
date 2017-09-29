package com.johnblakeduffie.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;
/**
 * Created by johnblakeduffie on 7/15/17.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);

    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb, SpriteBatch gameOverScore){
        states.peek().render(sb);
    }
}
