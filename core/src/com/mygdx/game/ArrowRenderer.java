package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ArrowRenderer {
	private Texture arrowImg, arrowBehavirorImg;
	private TextureRegion arrowRegion, arrowBehavirorRegion;
	private int changeImg = 0;
	SpriteBatch batch;
	World world;

	public ArrowRenderer(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		arrowImg = new Texture("arrow.fw.png");
		arrowBehavirorImg = new Texture("arrowBehaviror.fw.png");
		arrowRegion = new TextureRegion(arrowImg);
		arrowBehavirorRegion = new TextureRegion(arrowBehavirorImg);
	}
	
	public void render() {
		world.getArrow().genArrow();
        int[] arrow = world.getArrow().getArrow();
		batch.begin();    
        for(int i = 0; i < 4; i++) {
        	if(arrow[i] != 99) {
    			if(changeImg % 2 == 0) {
            		batch.draw(arrowRegion, SoldierGame.WIDTH/3 + (i*60) + (i*10), 40, 30, 30, 60, 60, 1, 1, arrow[i]);
            	} else {
            		batch.draw(arrowBehavirorRegion, SoldierGame.WIDTH/3 + (i*60) + (i*10), 40, 30, 30, 60, 60, 1, 1, arrow[i]);
            	}
        	}
        }
        changeImg++;
        batch.end();
    }
}
