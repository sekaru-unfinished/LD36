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
		
		float x = 150;
		float y = 150;
		
		float rad = FW/2;
		float cx = Gdx.graphics.getWidth()/2-x;
		float cy = Gdx.graphics.getHeight()/2-y;
		
		circle = new Circle(getPosition(new Vector2(cx, cy), rad, angle).x, 
				getPosition(new Vector2(cx, cy), rad, angle).y, rad);
		
		batch.end();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		sr.circle(circle.x, circle.y, circle.radius);
		sr.end();
		batch.begin();
		
		batch.draw(sprite, x, y, cx, cy, FW, FH, 1f, 1f, angle);
	}
	
	public Vector2 getPosition(Vector2 center, float radius, float angle) {
		angle-=90f;
		
		Vector2 p = new Vector2((float) (center.x + radius * Math.cos(Math.toRadians(angle))),
	    (float) (center.y + radius* Math.sin(Math.toRadians(angle))));

	    return p;
	}
}
