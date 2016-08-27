package net.sleepystudios.ld36;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class Player {
	float x, y, animSpeed = 0.1f, animTmr, angle;
	int FW = 32, FH = 32;
	Animation anim, legs;
	boolean moving;
	Circle circle;
	ShapeRenderer sr;
	
	public Player() {
		anim = new Animation(animSpeed, AnimGenerator.gen("player.png", FW, FH));
		legs = new Animation(animSpeed*2, AnimGenerator.gen("legs.png", FW, FH));
		sr = new ShapeRenderer();
		move(0, 0);
	}
	
	public void render(SpriteBatch batch) {
		float delta = Gdx.graphics.getDeltaTime();
		animTmr += delta;
		
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
    	//LD36.camera.unproject(mouse);
    	float yDist = y - mouse.y + 16f;
    	float xDist = x - mouse.x + 16f;
    	
    	angle = (float) (Math.toDegrees(Math.atan2(yDist, xDist))+90f);
    	if(angle < 0) angle += 360;
		
		batch.draw(legs.getKeyFrame(animTmr, moving), x, y, FW/2, FH/2, FW, FH, 1f, 1f, angle);
		batch.draw(anim.getKeyFrame(animTmr), x, y, FW/2, FH/2, FW, FH, 1f, 1f, angle);
		
		batch.end();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		sr.circle(circle.x, circle.y, circle.radius);
		sr.end();
		batch.begin();
		
		float trueSpeed = 200 * delta;
		moving = false;
		
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			move(x, y + trueSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
        	move(x - trueSpeed, y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
        	move(x, y - trueSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
        	move(x + trueSpeed, y);
        }
        if(!moving) animTmr = 0;
	}
	
	public void move(float x, float y) {
		this.x = x;
		this.y = y;
		moving = true;
		
		circle = new Circle(x+FW/2, y+FH/2, 12);
	}
}
