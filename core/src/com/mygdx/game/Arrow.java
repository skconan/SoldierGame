package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Arrow {
	private int[] arrow = new int[4];
	private int[] ctrl = new int[4];
	private Random random = new Random();
	private boolean checkGenArrow;
	private int indexArrow = 0;
	private int indexCtrl = 0;
	private World world;
	private int checkPress = 1;
	
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
				ctrl[i] = 1;
			}
			indexArrow = 0;
			indexCtrl = 0;
			checkPress = 1;
			checkGenArrow = false;
		}
	}
	
	public int[] getArrow() {
		return arrow;
	}
	
	public int[] getCtrl() {
		return ctrl;
	}
	
	private void pressKeyboard() {
		if(indexCtrl == 4) {
			checkGenArrow = true;
			genArrow();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && checkPress == 1 && !Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
			checkPress(0, arrow[indexArrow]);
        } else if(Gdx.input.isKeyPressed(Keys.UP) && checkPress == 1 && !Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
			checkPress(90, arrow[indexArrow]);	
        } else if(Gdx.input.isKeyPressed(Keys.LEFT) && checkPress == 1 && !Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
			checkPress(180, arrow[indexArrow]);	
        } else if(Gdx.input.isKeyPressed(Keys.DOWN) && checkPress == 1 && !Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)) {
			checkPress(270, arrow[indexArrow]);	
        } else if(Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT) && checkPress == 0) {
			checkPress = 1;
			ctrl[indexCtrl++] = 0;
		}
	}
	
	public void update() {
		pressKeyboard();
	}
	
	private void checkPress(int key,int arrow1) {
		if(key == arrow1) {
			arrow[indexArrow++] = 99;
			world.increaseBullet();
			checkPress = 0;
		} else {
			world.decreaseBullet();
		}
	}
}
