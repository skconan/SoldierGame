package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MonstersRenderer {
	private SpriteBatch batch;
	private Texture[] monsterImg = new Texture[10];
	Texture monsterImgRender;
	private String fileMonsters[] = {"monster00.fw.png", "monster01.fw.png", "monster02.fw.png", "boss.fw.png"};
	private World world;
	private Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/ghostComing.mp3"));
	private BitmapFont font;
	
	public MonstersRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.batch = batch;
		for(int i=0; i < fileMonsters.length; i++) {
			monsterImg[i] = new Texture(fileMonsters[i]);
		}
		font = new BitmapFont();
		sound.loop();
	}
	
	public void render() {
		
		int[] monstersProperty = new int[5];
		for (int r = 0; r < world.getMonsters().getMapHeight(); r++) {
			 for (int c = 0; c < world.getMonsters().getMapWidth(); c++) {				
				if (world.getMonsters().hasMonsterAt(r, c)) {
					if (world.getMonsters().createdMonsterAt(r, c)) {
						world.getMonsters().genMonsters(r, c);
					}
					if(world.getScore() < 20) {
						monsterImgRender = monsterImg[0];
					} else if (world.getScore() < 40) {
						monsterImgRender = monsterImg[1];
					} else if (world.getScore() < 60) {
						monsterImgRender = monsterImg[2];
					} else {
						monsterImgRender = monsterImg[3];
					}
					monstersProperty = world.getMonsters().getMonsters(r, c);
					world.getSoldier().hitMonsters(monstersProperty[1], monstersProperty[2]);
					batch.begin();
					batch.draw(monsterImgRender, monstersProperty[1]-monstersProperty[0]/2, monstersProperty[2]-monstersProperty[0]/2, monstersProperty[0],monstersProperty[0]);
					font.draw(batch, "Blood : " + monstersProperty[3], monstersProperty[1]-monstersProperty[0]/2, monstersProperty[2]+monstersProperty[0]/2 + 10);
					batch.end();
				}
			 }
		}
    }
}
