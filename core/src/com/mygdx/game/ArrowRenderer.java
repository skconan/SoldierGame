package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ArrowRenderer {
	private Texture arrowImg, arrowBehavirorImg, pressKeyImg;
	private TextureRegion arrowRegion, arrowBehavirorRegion;
	private SpriteBatch batch;
	private World world;
	private int arrowSize = 60;
	private int changeImg = 0;

	public ArrowRenderer(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		arrowImg = new Texture("arrow.fw.png");
		pressKeyImg = new Texture("pressKey.fw.png");
		arrowBehavirorImg = new Texture("arrowBehaviror.fw.png");
		arrowRegion = new TextureRegion(arrowImg);
		arrowBehavirorRegion = new TextureRegion(arrowBehavirorImg);
	}
	
	public void render() {
		world.getArrow().genArrow();
        int[] arrow = world.getArrow().getArrowKey();
		batch.begin();    
        for(int i = 0; i < 4; i++) {
        	if(arrow[i] != 99) {
    			if(changeImg % 2 == 0) {
            		batch.draw(arrowRegion, SoldierGame.WIDTH/3 + (i*arrowSize) + (i*10), SoldierGame.HEIGHT - 80, arrowSize/2, arrowSize/2, arrowSize, arrowSize, 1, 1, arrow[i]);
            	} else {
            		batch.draw(arrowBehavirorRegion, SoldierGame.WIDTH/3 + (i*arrowSize) + (i*10), SoldierGame.HEIGHT - 80, arrowSize/2, arrowSize/2, arrowSize, arrowSize, 1, 1, arrow[i]);
            	}
        	}
        }
        if(world.getBullet() <= 0) {
        	batch.draw(pressKeyImg, SoldierGame.WIDTH/2 - 175, SoldierGame.HEIGHT - 200);
        }
        changeImg++;
        batch.end();
    }
}
