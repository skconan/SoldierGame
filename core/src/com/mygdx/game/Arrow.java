package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Arrow {
	private Texture arrowImg, arrowBehavirorImg, pressKeyImg;
	private TextureRegion arrowRegion, arrowBehavirorRegion;
	private SpriteBatch batch;
	private Random random = new Random();
	private World world;
	private int[] arrow = new int[4];
	private int arrowSize = 60;
	private int changeImg = 0;
	private int indexArrow;
	
	
	public Arrow(SpriteBatch batch, World world) {
		this.world = world;	
		this.batch = batch;
		indexArrow = 0;
		arrowImg = new Texture("arrow.fw.png");
		pressKeyImg = new Texture("pressKey.fw.png");
		arrowBehavirorImg = new Texture("arrowBehaviror.fw.png");
		arrowRegion = new TextureRegion(arrowImg);
		arrowBehavirorRegion = new TextureRegion(arrowBehavirorImg);
	}
	
	public void update() {
		if(indexArrow == 4) {
			genArrow();
		}
	}
	
	public void render() {
		batch.begin();    
        for(int i = indexArrow; i < 4; i++) {
        	if(changeImg % 2 == 0) {
            	batch.draw(arrowRegion, SoldierGame.WIDTH/3 + (i*arrowSize) + (i*10)
            			, SoldierGame.HEIGHT - 80, arrowSize/2, arrowSize/2, arrowSize, arrowSize, 1, 1, arrow[i]);
            } else {
            	batch.draw(arrowBehavirorRegion, SoldierGame.WIDTH/3 + (i*arrowSize) + (i*10)
            			, SoldierGame.HEIGHT - 80, arrowSize/2, arrowSize/2, arrowSize, arrowSize, 1, 1, arrow[i]);
            }
        }
        if(world.bullet <= 0) {
        	batch.draw(pressKeyImg, SoldierGame.WIDTH/2 - 175, SoldierGame.HEIGHT - 200);
        }
        changeImg++;
        batch.end();
    }
	
	public void checkPress(int key) {
		if(key == arrow[indexArrow]) {
			world.increaseBullet();
			indexArrow++;
		} else {
			world.decreaseBullet();
		}
	}

	private void genArrow() {
		int rand;
		for(int i = 0; i < arrow.length; i++) {
			rand = random.nextInt(3);
			arrow[i] = rand*90;
		}
		indexArrow = 0;
	}
	
}
