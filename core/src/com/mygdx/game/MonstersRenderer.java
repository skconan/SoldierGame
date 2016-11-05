package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MonstersRenderer {
	private Monsters monsters = new Monsters();
	private SpriteBatch batch;
//	private Texture[][] monsterImg2D ;
	private Texture[] monsterImg = new Texture[10];
	private Texture[] monsterImgAction = new Texture[10];
	private int[][] monsterSize ;
	private float[][] monsterMove;
	private String fileMonsters[] = {"monster06R.fw.png","monster06L.fw.png"};
	private String fileMonstersAction[] = {"monster06-1.fw.png"};
	private Random rand = new Random();

	public MonstersRenderer(SpriteBatch batch) {
		this.batch = batch;
//		monsterImg2D = new Texture[monsters.getHeight()][monsters.getWidth()];
		monsterMove = new float[monsters.getHeight()][monsters.getWidth()];
		monsterSize = new int[monsters.getHeight()][monsters.getWidth()];
		
		for(int i=0; i<2; i++) {
			monsterImg[i] = new Texture(fileMonsters[i]);
		}
		for(int i=0; i<1; i++) {
			monsterImgAction[i] = new Texture(fileMonstersAction[i]);
		}
	}
	
	public void render(float delta) {
		monsters.update();
		for (int r = 0; r < monsters.getHeight(); r++) {
			 for (int c = 0; c < monsters.getWidth(); c++) {				
				if (monsters.hasMonsterAt(r, c)){
					monsterMove[r][c] += rand.nextInt(80)/50;	
					
					if (monsters.createdMonsterAt(r, c)){
						monsterSize[r][c] = (int) (monsters.getSize());
					}
					
					if(monsterSize[r][c] > 100) {
						monsterSize[r][c] = (int) (monsterSize[r][c] - monsterMove[r][c]/50);
					} else {
						monsterSize[r][c] = (int) (monsterSize[r][c] + monsterMove[r][c]/50);
					}
				
					batch.begin();
					monsters.getPosition(r, c, (c*100+monsterSize[r][c]/2)+monsterMove[r][c], (4-r)*100+monsterSize[r][c]/2-monsterMove[r][c], monsterSize[r][c]);
					batch.draw(monsterImg[0], (c*100)+monsterMove[r][c], (4-r)*100-monsterMove[r][c], monsterSize[r][c], monsterSize[r][c]);
					batch.end();
				}
			 }
		}
    }
}
