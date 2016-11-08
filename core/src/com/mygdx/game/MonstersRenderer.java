package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MonstersRenderer {
	private Monsters monsters;
	private SpriteBatch batch;
//	private Texture[][] monsterImg2D ;
	private Texture[] monsterImg = new Texture[10];
//	private Texture[] monsterImgAction = new Texture[10];
	private int[][] monsterSize ;
	public float[][] monsterMove;
	private String fileMonsters[] = {"monster06R.fw.png","monster06L.fw.png"};
//	private String fileMonstersAction[] = {"monster06-1.fw.png"};
	private Random rand = new Random();

	public MonstersRenderer(SpriteBatch batch, Monsters monsters) {
		this.batch = batch;
//		monsterImg2D = new Texture[monsters.getHeight()][monsters.getWidth()];
		
		for(int i=0; i<2; i++) {
			monsterImg[i] = new Texture(fileMonsters[i]);
		}
		
		this.monsters = monsters;
		
	}
	
	public void render(float delta) {
		monsters.update();
		int[] monstersProperty = new int[5];
		for (int r = 0; r < monsters.getMapHeight(); r++) {
			 for (int c = 0; c < monsters.getMapWidth(); c++) {				
				if (monsters.hasMonsterAt(r, c)){
					
					if (monsters.createdMonsterAt(r, c)){
						monsters.genMonsters(r, c);
					}
					
					monstersProperty = monsters.getMonsters(r, c);
					batch.begin();
//					monsters.getPosition(r, c, (c*100+monsterSize[r][c]/2)+monsterMove[r][c], (4-r)*100+monsterSize[r][c]/2-monsterMove[r][c], monsterSize[r][c]);
//					
					batch.draw(monsterImg[0], monstersProperty[1]-monstersProperty[0]/2, monstersProperty[2]-monstersProperty[0]/2, monstersProperty[0],monstersProperty[0]);
					batch.end();
				}
			 }
		}
    }
}
