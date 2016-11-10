package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ArrowRenderer {
	private Texture arrowImg, ctrlImg, arrowBehavirorImg, ctrlBehavirorImg;
	private TextureRegion arrowRegion, arrowBehavirorRegion, ctrlRegion, ctrlBehavirorRegion;
	private int changeImg = 0;
	SpriteBatch batch;
	World world;

	public ArrowRenderer(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		ctrlImg = new Texture("ctrl.fw.png");
		arrowImg = new Texture("arrow.fw.png");
		arrowBehavirorImg = new Texture("arrowBehaviror.fw.png");
		ctrlBehavirorImg = new Texture("ctrlBehaviror.fw.png");
		ctrlRegion = new TextureRegion(ctrlImg);
		ctrlBehavirorRegion = new TextureRegion(ctrlBehavirorImg);
		arrowRegion = new TextureRegion(arrowImg);
		arrowBehavirorRegion = new TextureRegion(arrowBehavirorImg);
	}
	
	public void render() {
		world.getArrow().genArrow();
        int[] arrow = world.getArrow().getArrow();
        int[] ctrl = world.getArrow().getCtrl();
		batch.begin();    
        for(int j = 0, i = 0, k=0; j < 8; j++) {
        	if(j % 2 == 0) {
        		if(arrow[i] != 99) {
        			if(changeImg % 2 == 0) {
                		batch.draw(arrowRegion, 40+(j*60)+(j*10), 40, 30, 30, 60, 60, 1, 1, arrow[i]);
                	} else {
                		batch.draw(arrowBehavirorRegion, 40+(j*60)+(j*10), 40, 30, 30, 60, 60, 1, 1, arrow[i]);
                	}
            	}
        		i++;
        	} else {
        		if(ctrl[k] != 0){
	        		if(changeImg % 2 == 0) {
	            		batch.draw(ctrlRegion, 40+(j*60)+(j*10), 40, 30, 30, 60, 60, 1, 1, 0);
	            	} else {
	            		batch.draw(ctrlBehavirorRegion, 40+(j*60)+(j*10), 40, 30, 30, 60, 60, 1, 1, 0);
	            	}
        		}
        		k++;
        	}
        }
        changeImg++;
        batch.end();
    }
}
