package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MonstersRenderer {
	private SpriteBatch batch;
	private Texture[] monsterImg = new Texture[10];
	private String fileMonsters[] = {"monster06R.fw.png","monster06L.fw.png"};
	private World world;
	
	public MonstersRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.batch = batch;
		
		for(int i=0; i<2; i++) {
			monsterImg[i] = new Texture(fileMonsters[i]);
		}		
	}
	
	public void render(float delta) {
		int[] monstersProperty = new int[5];
		
		for (int r = 0; r < world.getMonsters().getMapHeight(); r++) {
			 for (int c = 0; c < world.getMonsters().getMapWidth(); c++) {				
				if (world.getMonsters().hasMonsterAt(r, c)){
					
					if (world.getMonsters().createdMonsterAt(r, c)){
						world.getMonsters().genMonsters(r, c);
					}
					
					monstersProperty = world.getMonsters().getMonsters(r, c);
					batch.begin();
					batch.draw(monsterImg[0], monstersProperty[1]-monstersProperty[0]/2, monstersProperty[2]-monstersProperty[0]/2, monstersProperty[0],monstersProperty[0]);
					batch.end();
				}
			 }
		}
    }
}
