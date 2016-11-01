package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MonstersRenderer {
	private Monsters monster;
	private SpriteBatch batch;
	private int ct = 0;
	private Texture[][] monsterImg ;
	private String[][] monsterName ;
	private int[][] monsterSize ;
	
	public MonstersRenderer(SpriteBatch batch, Monsters monsters){
		this.monster = monsters;
		this.batch = batch;
		monsterImg = new Texture[monster.getHeight()][monster.getWidth()];
		monsterName = new String[monster.getHeight()][monster.getWidth()];
		monsterSize = new int[monster.getHeight()][monster.getWidth()];
	}
	
	public void render() {
		batch.begin();		
		 for(int r = 0; r < monster.getHeight(); r++) {
			 for(int c = 0; c < monster.getWidth(); c++) {		
				 if(monster.hasMonsterAt(r, c)){
					if(monster.createdMonsterAt(r, c)){
						monsterImg[r][c] = new Texture(monster.getName());
						monsterSize[r][c] = monster.getSize();
					}
					batch.draw(monsterImg[r][c], c*50+monsterSize[r][c]/2, (7-r)*50-monsterSize[r][c]/2, monsterSize[r][c], monsterSize[r][c]);
				 }
			 }
		}		
        batch.end();
    }
}
