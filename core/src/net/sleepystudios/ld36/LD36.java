package net.sleepystudios.ld36;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LD36 extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;
	Sun sun;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player();
		sun = new Sun();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.render(batch);
		sun.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
