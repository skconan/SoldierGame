package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Arrow {
	private int[] arrow = new int[4];
	private Random random = new Random();
	private boolean checkGenArrow;
	private int indexArrow = 0;
	private World world;
	
	public Arrow(World world) {
		this.world = world;
		checkGenArrow = true;
	}
	
	public void genArrow() {
		int rand;
		if(checkGenArrow) {
			for(int i = 0; i < arrow.length; i++) {
				rand = random.nextInt(3);
				arrow[i] = rand*90;
			}
			indexArrow = 0;
			checkGenArrow = false;
		}
	}
	
	public int[] getArrow() {
		return arrow;
	}
	
	private void pressKeyboard() {
		if(indexArrow == 4) {
			checkGenArrow = true;
			genArrow();
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			checkPress(0, arrow[indexArrow]);
        } else if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			checkPress(90, arrow[indexArrow]);	
        } else if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			checkPress(180, arrow[indexArrow]);	
        } else if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			checkPress(270, arrow[indexArrow]);	
        }
	}
	
	public void update() {
		pressKeyboard();
	}
	
	private void checkPress(int key,int arrow1) {
		if(key == arrow1) {
			arrow[indexArrow++] = 99;
			world.increaseBullet();
		} else {
			world.decreaseBullet();
		}
	}
}
