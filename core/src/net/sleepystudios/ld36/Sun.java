package net.sleepystudios.ld36;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Sun {
	Sprite sprite;
	int FW = 32, FH = 32;
	float angle;
	Circle circle;
	ShapeRenderer sr;
	
	public Sun() {
		sprite = new Sprite(new Texture("sun.png"));
		sr = new ShapeRenderer();
	}
	
	public void render(SpriteBatch batch) {
		angle+=1f;
		
		float x = 200;
		float y = 200;
		
		float rad = FW/2;
		float cx = Gdx.graphics.getWidth()/2;
		float cy = Gdx.graphics.getHeight()/2;
		
		circle = new Circle((float) (cx + x * Math.cos(Math.toRadians(angle))), 
				(float) (cy + y * Math.sin(Math.toRadians(angle))), rad);
		
		batch.end();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		sr.circle(circle.x, circle.y, circle.radius);
		sr.line(new Vector2(circle.x, circle.y), new Vector2(LD36.player.circle.x, LD36.player.circle.y));
		sr.end();
		batch.begin();
		
		batch.draw(sprite, circle.x-FW/2, circle.y-FH/2, FW/2, FH/2, FW, FH, 1f, 1f, angle);
	}
}
