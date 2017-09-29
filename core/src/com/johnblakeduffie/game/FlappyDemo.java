package com.johnblakeduffie.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.johnblakeduffie.game.sprites.Bird;
import com.johnblakeduffie.game.states.GameStateManager;
import com.johnblakeduffie.game.states.MenuState;
import com.johnblakeduffie.game.states.PlayState;
import com.johnblakeduffie.game.tools.ScrollingBackground;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Keyhole";
	private GameStateManager gsm;
	private SpriteBatch sb;
	private SpriteBatch gameOverScore;

	public ScrollingBackground scrollingBackground;      //should only be available in playstate

	
	@Override
	public void create () {
		sb = new SpriteBatch();
		gameOverScore = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		this.scrollingBackground = new ScrollingBackground();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb, gameOverScore);

	}

}
